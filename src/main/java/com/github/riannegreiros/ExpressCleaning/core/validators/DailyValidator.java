package com.github.riannegreiros.ExpressCleaning.core.validators;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.adapters.AddressService;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.exceptions.AddressServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DailyValidator {
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepository userRepository;

    public void validate(Daily daily) {
        validateEndDate(daily);
    }

    private void validateEndDate(Daily daily) {
        var attendanceDate = daily.getAttendanceDate();
        var attendanceTime = daily.getAttendanceTime();
        var endDate = attendanceDate.plusHours(attendanceTime);
        var dateLimit = attendanceDate.withHour(22).withMinute(0).withSecond(0);

        if (endDate.isAfter(dateLimit)) {
            var message = "it can't be after 10pm";
            var fieldError = new FieldError(daily.getClass().getName(), "attendanceDate", daily.getAttendanceDate(), false, null, null, message);

            throw new ValidationException(message, fieldError);
        }

        validateAttendanceTime(daily);
    }

    private void validateAttendanceTime(Daily daily) {
        var attendanceTime = daily.getAttendanceTime();
        var totalTime = calculateTotalTime(daily);

        if (!attendanceTime.equals(totalTime)) {
            var message = "values do not match";
            var fieldError = new FieldError(daily.getClass().getName(), "attendanceTime", daily.getAttendanceTime(), false, null, null, message);

            throw new ValidationException(message, fieldError);
        }

        validatePrice(daily);
    }

    private void validatePrice(Daily daily) {
        var price = daily.getPrice();
        var valorTotal = calculateTotalValue(daily);

        if (price.compareTo(valorTotal) != 0) {
            var message = "values do not match";
            var fieldError = new FieldError(daily.getClass().getName(), "price", daily.getPrice(), false, null, null, message);

            throw new ValidationException(message, fieldError);
        }

        validateZipCode(daily);
    }

    private void validateZipCode(Daily daily) {
        var zipCode = daily.getZipCode();

        try {
            addressService.getAddressByZipCode(zipCode);
        } catch (AddressServiceException exception) {
            var message = exception.getLocalizedMessage();
            var fieldError = new FieldError(daily.getClass().getName(), "cep", daily.getZipCode(), false, null, null, message);

            throw new ValidationException(message, fieldError);
        }

        validateIbgeCode(daily);
    }

    private void validateIbgeCode(Daily daily) {
        var cep = daily.getZipCode();
        var ibgeCode = daily.getIbgeCode();
        var validIbgeCode = addressService.getAddressByZipCode(cep).getIbge();

        if (!ibgeCode.equals(validIbgeCode)) {
            var message = "invalid ibge code";
            var fieldError = new FieldError(daily.getClass().getName(), "ibgeCode", daily.getIbgeCode(), false, null, null, message);

            throw new ValidationException(message, fieldError);
        }

        validateAvailability(daily);
    }

    private void validateAvailability(Daily daily) {
        var ibgeCode = daily.getIbgeCode();
        var availability = userRepository.existsByCitiesServedIbgeCode(ibgeCode);
        if (!availability) {
            var message = "there are no cleaners at the zip code provided";
            var fieldError = new FieldError(daily.getClass().getName(), "cep", daily.getZipCode(), false, null, null, message);

            throw new ValidationException(message, fieldError);
        }

        validateAttendanceDate(daily);
    }

    private void validateAttendanceDate(Daily daily) {
        var attendanceDate = daily.getAttendanceDate();
        var minimumDate = LocalDateTime.now().plusHours(48);

        if (attendanceDate.isBefore(minimumDate)) {
            var message = "must be at least 48 hours old from the current date";
            var fieldError = new FieldError(daily.getClass().getName(), "attendanceDate", daily.getAttendanceDate(), false, null, null, message);

            throw new ValidationException(message, fieldError);
        }
    }

    private BigDecimal calculateTotalValue(Daily daily) {
        var service = daily.getService();
        var minimumValue = service.getMinValue();

        var valueBedroom = calculateRoomValue(service.getBedroomValue(), daily.getBedroomNum());
        var valueLivingRoom = calculateRoomValue(service.getLivingRoomValue(), daily.getLivingRoomNum());
        var valueKitchen = calculateRoomValue(service.getKitchenValue(), daily.getkitchenNum());
        var valueBathroom = calculateRoomValue(service.getBathroomValue(), daily.getBathroomNum());
        var valueBackyard = calculateRoomValue(service.getBackyardValue(), daily.getBackyardNum());
        var valueOther = calculateRoomValue(service.getOthersValue(), daily.getOtherNum());

        var valorTotal = valueBedroom.add(valueLivingRoom)
                .add(valueBathroom)
                .add(valueKitchen)
                .add(valueBackyard)
                .add(valueOther);

        if (valorTotal.compareTo(minimumValue) < 0) {
            return minimumValue;
        }
        return valorTotal;
    }

    private BigDecimal calculateRoomValue(BigDecimal valueRoom, Integer quantityOfRooms) {
        return valueRoom.multiply(new BigDecimal(quantityOfRooms));
    }

    private Integer calculateTotalTime(Daily daily) {
        var service = daily.getService();

        int totalTime = 0;
        totalTime += daily.getBedroomNum() * service.getBedroomHours();
        totalTime += daily.getLivingRoomNum() * service.getLivingRoomHours();
        totalTime += daily.getkitchenNum() * service.getKitchenHours();
        totalTime += daily.getBathroomNum() * service.getBathroomHours();
        totalTime += daily.getBackyardNum() * service.getBackyardHours();
        totalTime += daily.getOtherNum() * service.getOthersHours();

        return totalTime;
    }
}

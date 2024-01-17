package com.github.riannegreiros.ExpressCleaning.core.services.checkdistance.providers;

import com.github.riannegreiros.ExpressCleaning.core.services.checkcity.exceptions.CheckCityServiceException;
import com.github.riannegreiros.ExpressCleaning.core.services.checkdistance.adapters.CheckDistanceService;
import com.github.riannegreiros.ExpressCleaning.core.services.checkdistance.dtos.DistanceResponse;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElementStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMatrixService implements CheckDistanceService {
    @Value("${googleMatrix.apiKey}")
    private String apiKey;

    @Override
    public DistanceResponse calculateDistanceBetweenTwoZipCodes(String origin, String destination) {
        var formattedOrigin = formatZipCode(origin);
        var formattedDestination = formatZipCode(destination);

        var distanceMatrixApiRequest = getDistanceMatrixApiRequest();
        var distanceMatrix = getDistanceMatrix(formattedOrigin, formattedDestination, distanceMatrixApiRequest);

        return distanceMatrixToDistanceResponse(distanceMatrix);
    }

    private DistanceResponse distanceMatrixToDistanceResponse(DistanceMatrix distanceMatrix) {
        validateDistanceMatrix(distanceMatrix);

        return new DistanceResponse.Builder()
                .origin(distanceMatrix.originAddresses[0])
                .destination(distanceMatrix.destinationAddresses[0])
                .kmDistance(distanceMatrix.rows[0].elements[0].distance.inMeters / 1000.0)
                .build();
    }

    private DistanceMatrix getDistanceMatrix(String origin, String destination, DistanceMatrixApiRequest request) {
        try {
            return request.origins(origin)
                    .destinations(destination)
                    .await();
        } catch (ApiException | InterruptedException | IOException exception) {
            throw new CheckCityServiceException(exception.getLocalizedMessage());
        }
    }

    private DistanceMatrixApiRequest getDistanceMatrixApiRequest() {
        return DistanceMatrixApi.newRequest(getGeoApiContext());
    }

    private GeoApiContext getGeoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    private void validateDistanceMatrix(DistanceMatrix distanceMatrix) {
        var rows = distanceMatrix.rows;
        if (rows.length == 0) {
            throw new CheckCityServiceException("Query error");
        }

        var elements = rows[0].elements;
        if (elements.length == 0) {
            throw new CheckCityServiceException("Query error");
        }

        var status = elements[0].status;
        if (!status.equals(DistanceMatrixElementStatus.OK)) {
            throw new CheckCityServiceException("Query error");
        }
    }

    private String formatZipCode(String zipCode) {
        validateZipCode(zipCode);

        var stringBuilder = new StringBuilder(zipCode);
        stringBuilder.insert(5, "-");
        return stringBuilder.toString();
    }

    private void validateZipCode(String zipCode) {
        if (zipCode.length() != 8) {
            throw new IllegalArgumentException("The zip code must have eight digits");
        }

        if (!zipCode.matches("[0-9]+")) {
            throw new IllegalArgumentException("The zip code should only have numbers");
        }
    }
}

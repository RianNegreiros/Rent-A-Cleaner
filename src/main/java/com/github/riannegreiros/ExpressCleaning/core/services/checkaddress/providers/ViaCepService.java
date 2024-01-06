package com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.providers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.adapters.AddressService;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.dto.AddressResponse;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.exceptions.AddressServiceException;

@Service
public class ViaCepService implements AddressService {
  private static final String URL_TEMPLATE = "http://viacep.com.br/ws/{zipCode}/json/";
  private final RestTemplate restTemplate = new RestTemplate();

  @Override
  public AddressResponse getAddressByZipCode(String zipCode) throws AddressServiceException {
    var url = UriComponentsBuilder.fromUriString(URL_TEMPLATE)
        .buildAndExpand(zipCode)
        .toString();

    ResponseEntity<AddressResponse> response;
    try {
      response = restTemplate.getForEntity(url, AddressResponse.class);
    } catch (HttpClientErrorException.BadRequest e) {
      throw new AddressServiceException("The zip code entered is invalid");
    }

    if (response.getBody().getZipCode() == null) {
      throw new AddressServiceException("The zip code entered was not found");
    }

    return response.getBody();
  }
}

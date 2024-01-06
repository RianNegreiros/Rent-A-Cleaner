package com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.adapters;

import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.dto.AddressResponse;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.exceptions.AddressServiceException;

public interface AddressService {
  AddressResponse getAddressByZipCode(String zipCode) throws AddressServiceException;
}

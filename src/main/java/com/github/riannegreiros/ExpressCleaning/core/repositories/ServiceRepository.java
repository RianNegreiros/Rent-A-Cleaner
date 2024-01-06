package com.github.riannegreiros.ExpressCleaning.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.riannegreiros.ExpressCleaning.core.models.ServiceModel;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {

}

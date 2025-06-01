package com.practice.user_service.repositories;

import com.practice.user_service.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByUserId(Long userId);
    Address findByStreetAndCityAndStateAndZipCode(String street, String city, String state, String zipCode);
}

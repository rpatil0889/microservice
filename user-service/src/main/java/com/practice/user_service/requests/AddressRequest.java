package com.practice.user_service.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}

package com.practice.user_service.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    // Additional fields can be added as needed
}

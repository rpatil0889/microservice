package com.practice.user_service.requests;

import com.practice.user_service.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private GenderEnum gender;
    private String mobileNumber;
    private List<AddressRequest> address;
    private List<String> role;
}

package com.practice.user_service.responses;

import com.practice.user_service.enums.GenderEnum;
import com.practice.user_service.requests.AddressRequest;
import com.practice.user_service.requests.RoleRequest;
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
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private GenderEnum gender;
    private String mobileNumber;
    private List<AddressResponse> address;
    private List<RoleRequest> role;
}

package com.rh.backend.payload.ApiResponse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginResponse {
    private String userId;
    private String fullName;

    public LoginResponse(String userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }
}

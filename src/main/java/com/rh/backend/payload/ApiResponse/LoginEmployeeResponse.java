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
public class LoginEmployeeResponse {
    private String employeeId;

    public LoginEmployeeResponse(String employeeId) {
        this.employeeId = employeeId;
    }
}

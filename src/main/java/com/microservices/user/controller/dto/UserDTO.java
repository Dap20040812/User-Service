package com.microservices.user.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;
}

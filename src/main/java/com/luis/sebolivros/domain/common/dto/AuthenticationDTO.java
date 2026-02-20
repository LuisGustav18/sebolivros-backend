package com.luis.sebolivros.domain.common.dto;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(String email, String senha){

}
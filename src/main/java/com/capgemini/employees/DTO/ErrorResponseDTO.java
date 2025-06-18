package com.capgemini.employees.DTO;

import lombok.Getter;
import lombok.Setter;


public class ErrorResponseDTO {
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

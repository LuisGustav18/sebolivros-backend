package com.luis.sebolivros.exceptions.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandardError {

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationErrors() {
        super();
    }

    public ValidationErrors(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String message) {
        this.erros.add(new FieldMessage(fieldName, message));
    }

    public void addError(FieldMessage error) {
        this.erros.add(error);
    }
}

package de.nmadev.notes.web;

import java.io.Serializable;

public class Response implements Serializable {

    private boolean isError;
    private String errorMessage;

    public Response() {}

    public Response(boolean isError) {
        this.isError = isError;
    }

    public Response(boolean isError, String errorMessage) {
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

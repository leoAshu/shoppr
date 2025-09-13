package com.leo.shoppr.response;

public class CustomResponse<T> {

    private ResponseStatus status;
    private String message;
    private T data;

    protected CustomResponse() {
    }

    public CustomResponse(ResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {

        private ResponseStatus status;
        private String message;
        private T data;

        public Builder<T> status(ResponseStatus status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public CustomResponse<T> build() {
            return new CustomResponse<T>(status, message, data);
        }

    }

}

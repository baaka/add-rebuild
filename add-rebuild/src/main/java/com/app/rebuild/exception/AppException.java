package com.app.rebuild.exception;

public class AppException extends Exception {

    public AppException(String message) {
        super(message);
    }

    public AppException(Type type) {
        super("General Error, Contact Administrator...");
    }

    public AppException(Type type, String message) {
        super(message);
        this.type = type;
    }

    private Type type;

    public enum Type {
        GENERAL_ERROR("GENERAL_ERROR"),
        FIELD_REQUIRED("FIELD_REQUIRED"),
        INVALID_VALUE("INVALID_VALUE"),
        FORBIDDEN("FORBIDDEN");

        private final String code;

        Type(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "AddRebuildException: " + getMessage();
    }
}

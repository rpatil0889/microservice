package com.practice.user_service.enums;

public enum UserStatusEnum {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended"),
    DELETED("Deleted");

    private final String status;

    UserStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

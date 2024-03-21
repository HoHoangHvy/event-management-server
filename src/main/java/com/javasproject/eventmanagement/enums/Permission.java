package com.javasproject.eventmanagement.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    EVENT_READ("event:read"),
    EVENT_WRITE("event:write"),
    EVENT_DELETE("event:delete"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_DELETE("user:delete"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write")  ,
    EMPLOYEE_DELETE("employee:delete")  ,
    CONTRACT_READ("contract:read"),
    CONTRACT_WRITE("contract:write"),
    CONTRACT_DELETE("contract:delete"),
    PAYMENT_READ("payment:read"),
    PAYMENT_WRITE("payment:write"),
    PAYMENT_DELETE("payment:delete"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    CUSTOMER_DELETE("customer:delete"),
    FACILITY_READ("facility:read"),
    FACILITY_WRITE("facility:write"),
    FACILITY_DELETE("facility:delete"),
    DISH_READ("dish:read"),
    DISH_WRITE("dish:write"),
    DISH_DELETE("dish:delete"),
    ;

    private final String permission;
}

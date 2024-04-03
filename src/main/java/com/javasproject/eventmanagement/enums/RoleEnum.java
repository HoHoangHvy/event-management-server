package com.javasproject.eventmanagement.enums;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {
    ADMIN(
            Set.of(
                    Permission.EVENT_READ,
                    Permission.EVENT_WRITE,
                    Permission.EVENT_DELETE,
                    Permission.USER_READ,
                    Permission.USER_WRITE,
                    Permission.USER_DELETE,
                    Permission.EMPLOYEE_READ,
                    Permission.EMPLOYEE_WRITE,
                    Permission.EMPLOYEE_DELETE,
                    Permission.CONTRACT_READ,
                    Permission.CONTRACT_WRITE,
                    Permission.CONTRACT_DELETE,
                    Permission.PAYMENT_READ,
                    Permission.PAYMENT_WRITE,
                    Permission.PAYMENT_DELETE,
                    Permission.CUSTOMER_READ,
                    Permission.CUSTOMER_WRITE,
                    Permission.CUSTOMER_DELETE
            )
    ),
    MANAGER (
            Set.of(
                    Permission.EVENT_READ,
                    Permission.EVENT_WRITE,
                    Permission.EVENT_DELETE,
                    Permission.USER_READ,
                    Permission.USER_WRITE,
                    Permission.USER_DELETE,
                    Permission.EMPLOYEE_READ,
                    Permission.EMPLOYEE_WRITE,
                    Permission.EMPLOYEE_DELETE,
                    Permission.CONTRACT_READ,
                    Permission.CONTRACT_WRITE,
                    Permission.CONTRACT_DELETE,
                    Permission.PAYMENT_READ,
                    Permission.PAYMENT_WRITE,
                    Permission.PAYMENT_DELETE,
                    Permission.CUSTOMER_READ,
                    Permission.CUSTOMER_WRITE,
                    Permission.CUSTOMER_DELETE
            )
    ),
    USER (
            Set.of(
                    Permission.EVENT_READ,
                    Permission.EVENT_WRITE,
                    Permission.CUSTOMER_READ,
                    Permission.CUSTOMER_WRITE,
                    Permission.CUSTOMER_DELETE,
                    Permission.PAYMENT_READ,
                    Permission.PAYMENT_WRITE,
                    Permission.PAYMENT_DELETE,
                    Permission.FACILITY_READ,
                    Permission.FACILITY_WRITE,
                    Permission.FACILITY_DELETE,
                    Permission.DISH_READ,
                    Permission.DISH_WRITE,
                    Permission.DISH_DELETE
            )
    ),
    ;

    private final Set<Permission> permissions;

//    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>(getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.name()))
//                .toList());
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return authorities;
//    }
}

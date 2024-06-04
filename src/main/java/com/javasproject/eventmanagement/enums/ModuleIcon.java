package com.javasproject.eventmanagement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ModuleIcon {
    Home("cilHome"),
    Payments("cilMoney"),
    Contracts("cilFile"),
    Customers("cilGroup"),
    Departments("cilIndustry"),
    Dishes("cilFastfood"),
    Employees("cilUser"),
    Events("cilInstitution"),
    Facilities("cilLineWeight"),
    Tasks("cilTask"),
    Thirdparties("cilHandshake"),
    Notifications("cilBell"),
    Requests("cilCopy"),
    Resources("cilFactory"),
    News("cilFax"),
    ResourceBooking("cilFax"),
    EventBooking("cilFax"),
    Halls("cilFax"),
    ;
    private final String icon;
}

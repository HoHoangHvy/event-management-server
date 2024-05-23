package com.javasproject.eventmanagement.configuration;

import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.service.EmployeeService;
import com.javasproject.eventmanagement.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class Scheduler {
    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 500000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private NotificationService notiService;
    @Scheduled(fixedRate = 5000000)
    public void sendBirthDateNoti() {
        this.employeeService.getAllEmployeeBirthday().forEach(employee -> {
            log.info("Happy birthday to " + employee.getName());
            NotificationCreationRequest notiRequest = new NotificationCreationRequest();
            notiRequest.setName("Happy birthday to " + employee.getName());
            notiRequest.setContent("We glad to celebrate your birthday with you!");
            notiRequest.setType("Birthday");
            notiRequest.setParentType("Notifications");
            notiRequest.setParentId("1");
            notiRequest.setEmployeeId(employee.getId());

            this.notiService.createNotification(notiRequest);
        });
    }
}
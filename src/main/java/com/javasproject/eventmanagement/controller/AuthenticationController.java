package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.AuthenticationRequest;
import com.javasproject.eventmanagement.dto.request.ChangePasswordRequest;
import com.javasproject.eventmanagement.dto.request.IntrospectRequest;
import com.javasproject.eventmanagement.dto.response.AuthenticationResponse;
import com.javasproject.eventmanagement.dto.response.IntrospectResponse;
import com.javasproject.eventmanagement.dto.response.UserResponse;
import com.javasproject.eventmanagement.enums.ModuleIcon;
import com.javasproject.eventmanagement.service.AuthenticationService;
import com.javasproject.eventmanagement.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }
    UserService userService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/me")
    public ApiResponse<Map<String, Object>> getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        UserResponse userResponse = userService.getUserByUserName(currentUsername);

        var permissions = userResponse.getRole().getPermission();
        ArrayList<Map<String, String>> moduleList = new ArrayList<Map<String, String>>();

        moduleList.add(Map.of("moduleName", "Home", "route", "/dashboard", "icon", getIconByModuleName("Home")));
        for (Map.Entry<String, Map<String, Boolean>> entry : userResponse.getRole().getPermission().entrySet()) {
            String k = entry.getKey();
            Map<String, Boolean> v = entry.getValue();
            for(Map.Entry<String, Boolean> entry2 : v.entrySet()){
                if(entry2.getKey().equals("VIEW") && entry2.getValue() && !k.equals("Roles") && !k.equals("Users") && !k.equals("EventDetails")) {
                    Map<String, String> module = new HashMap<>();
                    module.put("moduleName", k);
                    if(k == "News") {
                        module.put("route", "base/new-feed");
                    } else module.put("route", "base/list/" + k.toLowerCase());
                    module.put("icon", getIconByModuleName(k));
                    moduleList.add(module);
                }
            }
        }

        return ApiResponse.<Map<String, Object>>builder()
                .data(Map.of("user", userResponse, "permissions", permissions, "moduleList", moduleList))
                .build();
    }
    public String getIconByModuleName(String moduleName) {
        try {
            ModuleIcon moduleIcon = ModuleIcon.valueOf(moduleName);
            return moduleIcon.getIcon();
        } catch (IllegalArgumentException e) {
            System.err.println("No ModuleIcon with name " + moduleName);
            return null;
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/change-password")
    public ApiResponse<Boolean> changePassword(@RequestBody ChangePasswordRequest request){
        return ApiResponse.<Boolean>builder()
                .data(userService.changePassword(request))
                .build();
    }
}

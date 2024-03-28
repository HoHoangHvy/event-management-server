package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.entity.ThirdParty;
import com.javasproject.eventmanagement.service.ThirdPartyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/thirdparties")
public class ThirdPartyController {
    @Autowired
    ThirdPartyService thirdPartyService;
    @PostMapping
    public ApiResponse<ThirdParty> createThirdParty(@RequestBody ThirdParty thirdParty){
        return ApiResponse.<ThirdParty>builder()
                .data(thirdPartyService.upsert(thirdParty))
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ThirdParty> getThirdPartyById(@PathVariable String id){
        String message = "Successfully get the third party";
        ThirdParty thirdParty = thirdPartyService.getById(id);
        if (thirdParty == null){
            message = "Not found this third party";
        }
        return ApiResponse.<ThirdParty>builder()
                .data(thirdParty)
                .message(message)
                .build();
    }
    @GetMapping
    public ApiResponse<List<ThirdParty>> getAllThirdParties(){
        String message = "Successfully get the third party list";
        List<ThirdParty> thirdPartyList = thirdPartyService.getThirdPartyList();
        if(thirdPartyList.isEmpty()){
            message = "ThirdParty list is empty";

        }
        return ApiResponse.<List<ThirdParty>>builder()
                .data(thirdPartyList)
                .message(message)
                .build();
    }
    @PutMapping
    public ApiResponse<ThirdParty> updateThirdParty(@RequestBody  ThirdParty thirdParty){
        return ApiResponse.<ThirdParty>builder()
                .data(thirdPartyService.upsert(thirdParty))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteThirdParty(@PathVariable String id){
        String message = "Delete successfully";
        Boolean status = thirdPartyService.deleteById(id);
        if (!status){
            message = "Delete fail! Not found this record";
        }
        return ApiResponse.<String>builder()
                .message(message)
                .build();
    }

}

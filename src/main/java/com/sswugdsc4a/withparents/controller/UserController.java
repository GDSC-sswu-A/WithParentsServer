package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.dto.user.*;
import com.sswugdsc4a.withparents.dto.request.user.CreateFamilyRequest;
import com.sswugdsc4a.withparents.dto.request.user.ModifyUserInfoRequest;
import com.sswugdsc4a.withparents.dto.request.user.SetLocationInfoRequest;
import com.sswugdsc4a.withparents.entity.LastApiCallTime;
import com.sswugdsc4a.withparents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/modifyUserInfo")
    public UserDTO modifyUserInfo(
            @RequestBody ModifyUserInfoRequest body
    ){
        return userService.modifyUserInfo(
                body.getNickname(),
                body.getFamilyId(),
                body.getFamilyPassword(),
                body.getIsParent()
        );
    }

    @PostMapping("/api/user/createFamily")
    public FamilyDTO createFamily(
            @RequestBody CreateFamilyRequest body
    ){
        return userService.createFamily(body.getPassword());
    }

    @GetMapping("/api/user/getUserInfo")
    public UserDTO getUserInfo(){
        return UserDTO.entityToDTO(userService.getUser());
    }

    @PostMapping("/api/user/setLocationInfo")
    public LocationInfoDTO setLocationInfo(
            @RequestBody SetLocationInfoRequest body
            ){
        return userService.setLocationInfo(
                body.getLatitude(),
                body.getLatitude());
    }

    @GetMapping("/api/user/getLocationInfo")
    public List<LocationAndNicknameDTO> getLocationInfo(){
        return userService.getLocationInfo();
    }

    @GetMapping("/api/user/getFamilyMemberList")
    public List<SimpleUserInfoDTO> getFamilyMemberIdList(){
        return userService.getFamilyMemberList();
    }


    @GetMapping("/api/user/getHomeInfo")
    public HomeInfoDTO getHomeInfo() {
        return userService.getHomeInfo();
    }

    @GetMapping("/api/user/getParentsLastApiCallTime")
    public List<LastApiCallTime> getParentsLastApiCallTime(){
        return userService.getParentsLastApiCallTime();
    }

}

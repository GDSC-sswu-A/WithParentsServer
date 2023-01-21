package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.dto.user.FamilyDTO;
import com.sswugdsc4a.withparents.dto.dto.user.UserDTO;
import com.sswugdsc4a.withparents.dto.request.user.CreateFamilyRequest;
import com.sswugdsc4a.withparents.dto.request.user.ModifyUserInfoRequest;
import com.sswugdsc4a.withparents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}

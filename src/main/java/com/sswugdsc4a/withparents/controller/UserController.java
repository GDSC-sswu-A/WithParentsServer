package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.dto.user.UserDTO;
import com.sswugdsc4a.withparents.dto.request.user.ModifyUserInfoRequest;
import com.sswugdsc4a.withparents.service.UserService;
import lombok.RequiredArgsConstructor;
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

}

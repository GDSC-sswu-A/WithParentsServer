package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

}

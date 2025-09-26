package com.exam.controller;

import com.exam.dto.GeneralResponseDto;
import com.exam.service.DmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/dms")
public class DmsController {

    private final DmsService dmsService;


    public DmsController(DmsService dmsService) {
        this.dmsService = dmsService;
    }

    @PostMapping("/upload")
    public ResponseEntity<GeneralResponseDto> uploadDocument(@RequestParam("file") MultipartFile file){
        return dmsService.uploadDocument(file);
    }
}

package com.exam.service;

import com.exam.dto.GeneralResponseDto;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

public interface DmsService {

    ResponseEntity<GeneralResponseDto> uploadDocument(@RequestParam("file") MultipartFile file);

}

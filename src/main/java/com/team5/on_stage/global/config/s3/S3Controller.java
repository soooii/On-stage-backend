package com.team5.on_stage.global.config.s3;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Tag(name = "S3Controller", description = "AWS S3 이미지 업로드를 관리하는 컨트롤러")
@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Uploader s3Uploader;

    @Hidden
    @GetMapping("/test")
    public String index() {
        return "test";
    }

    @Operation(summary = "S3 이미지 업로드", description = "AWS S3에 이미지를 업로드합니다.")
    @Parameter(name = "data", description = "업로드할 이미지 파일")
    @PostMapping("/s3/image/upload")
    @ResponseBody
    public String upload(
            @RequestParam("data") MultipartFile multipartFile
    ) throws IOException {
        return s3Uploader.upload(multipartFile, "album");
    }
}

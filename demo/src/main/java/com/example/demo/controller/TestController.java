package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class TestController {

  com.example.demo.services.FileProviderServiceImpl fileProviderService = new com.example.demo.services.FileProviderServiceImpl();
  @PostMapping("/excel")
  public String convertExcelToJson(@RequestParam(value = "filePath", required = false) final String filePath )throws IOException {
    return fileProviderService.excelToJson(filePath);
  }










}

package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@CrossOrigin

@RestController
public class UploadController {
    com.example.demo.services.FileProviderServiceImpl fileProviderService =
            new com.example.demo.services.FileProviderServiceImpl();

    @RequestMapping(value = "/upload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> convertExcelToJSon2(

            @RequestParam("file") MultipartFile file) throws IOException {
        String filePath="C:/work/"+file.getOriginalFilename();
        File convertFile = new File(filePath);
        convertFile.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(convertFile)){
            fout.write(file.getBytes());
        }
        catch (Exception exe){
            exe.printStackTrace();
        }

        return ResponseEntity.ok(fileProviderService.excelToJson(filePath));
    }
}

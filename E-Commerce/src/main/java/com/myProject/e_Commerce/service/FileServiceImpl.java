package com.myProject.e_Commerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
   public String uploadImage(String path, MultipartFile imageFile) throws IOException {
        //File name of current/original file
        String originalFileName =imageFile.getOriginalFilename();

        //Generate a unique file
        String randomId= UUID.randomUUID().toString();
        //Ex: mat.Jpg --> 1234 --> 1234.Jpg
        String fileName=randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        String filePath=path+ File.separator+fileName;
        //Check if path Exist and create
        File folder= new File(path);
        if(!folder.exists()) {
            folder.mkdir();
        }

        //Upload to server
        Files.copy(imageFile.getInputStream(), Paths.get(filePath));

        //Return file name
        return fileName;
    }
}

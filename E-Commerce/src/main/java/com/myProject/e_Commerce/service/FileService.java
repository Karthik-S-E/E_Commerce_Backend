package com.myProject.e_Commerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
   String uploadImage(String path, MultipartFile imageFile) throws IOException ;
}

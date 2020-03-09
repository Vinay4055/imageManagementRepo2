package com.nagarro.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagarro.entity.Image;
import com.nagarro.entity.User;
@Repository
public interface ImageRepository {
public float totalSizeOfUploadedImages(int id);

public void deleteImageById(int id);
public void editImageSourceById(byte[] buffer,int id);
public String uploadImage(MultipartFile file,
        RedirectAttributes redirectAttributes) throws IOException;

}

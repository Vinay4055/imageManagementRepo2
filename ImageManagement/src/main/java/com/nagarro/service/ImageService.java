package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.ImageDao;
import com.nagarro.entity.Image;
@Service
public class ImageService  { 

@Autowired
private ImageDao imageDao;

public void save(Image image) {
	
	imageDao.save(image);
}
public List<Image> getImage(int userId) {
	return imageDao.findImage(userId);
}
public void updateImageName(String name,int id) {
	imageDao.updateImageName(name, id);
}
public void updateImageSource(byte[] image,int id) {
	imageDao.updateImageSource(image, id);
}

}
package com.nagarro.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagarro.dao.ImageDao;
import com.nagarro.entity.Image;
import com.nagarro.entity.User;
import com.nagarro.repository.ImageRepository;
import com.nagarro.service.UserService;

@Service
public class ImageUtility {
	@Autowired
	Image image;

	@Autowired
	private ImageDao imageDao;
	@Autowired
	private UserService userService;
	
	public void saveImage(MultipartFile file) throws IOException {
		image.setName(file.getOriginalFilename());// If Opera/IE Browser Will be used this will return path
													// along with name
		image.setImage(file.getBytes());
		image.setSize(file.getSize());
		List<Image> imageList = new ArrayList<>();
		imageList.add(image);
		User user = userService.getUser();
		image.setUser(user);
		
		imageDao.save(image);

	}

	public void editImageSourceById(byte[] buffer, int id) {

		Image image = imageDao.findById(id).get();

		image.setImage(buffer);
		//imageDao.updateImageSource(buffer, id); //Not Working Why Ask From Mentor
		
		imageDao.save(image);

	}
	
	public boolean validateTotalUploadImageSize(MultipartFile file,RedirectAttributes redirectAttributes,User user,ImageRepository imageRepository) {
		if(((imageRepository.totalSizeOfUploadedImages(user.getUserId()))/(1024*1024)+(file.getSize()/(1024.0*1024.0)))<10.0) {
			return true;
		}
		else {
			System.out.println("Inside Else Of Utili");
			redirectAttributes.addFlashAttribute("TotalImageSizeExceed","The Total Image Size exceeds The Size Limit Of 10Mb");
			return false;
		}
			
		
	}
}

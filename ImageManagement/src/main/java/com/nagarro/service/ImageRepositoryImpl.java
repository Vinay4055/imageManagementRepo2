package com.nagarro.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagarro.dao.ImageDao;
import com.nagarro.entity.Image;
import com.nagarro.entity.User;
import com.nagarro.repository.ImageRepository;
import com.nagarro.utility.ImageUtility;

@Service
public class ImageRepositoryImpl implements ImageRepository {

	@Autowired
	private ImageUtility imageUtil;
	@Autowired
	private ImageDao imageDao;

	@Override
	public float totalSizeOfUploadedImages(int userId) {

		return imageDao.findTotalSizeOfImage(userId);

	}

	@Override
	public void deleteImageById(int id) {
		imageDao.deleteById(id);

	}

	@Override
	public void editImageSourceById(byte[] buffer, int id) {
		imageUtil.editImageSourceById(buffer, id);

	}

	@Override
	public String uploadImage(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		imageUtil.saveImage(file);
		redirectAttributes.addFlashAttribute("message", "File Uploaded");
		return "redirect:uploadStatus";

	}

}

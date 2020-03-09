package com.nagarro.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagarro.entity.Image;
import com.nagarro.entity.User;
import com.nagarro.repository.ImageRepository;
import com.nagarro.service.ImageService;
import com.nagarro.service.UserService;
import com.nagarro.utility.ImageUtility;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ImageService imageService;
	@Autowired
	ImageUtility imageUtility;

	@GetMapping("/")
	public String display(Model map) {
		User user = userService.getUser();
		int userId = user.getUserId();
		map.addAttribute("images", imageService.getImage(userId));
		float size = imageRepository.totalSizeOfUploadedImages(userId);
		map.addAttribute("totalSizeOfUploadedImages", Math.round((size / (1024 * 1024)) * 100.0) / 100.0);
		return "index";
	}

	@PostMapping("/upload")
	public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		User user = userService.getUser();
		if (imageUtility.validateTotalUploadImageSize(file, redirectAttributes, user, imageRepository)) {
			imageRepository.uploadImage(file, redirectAttributes);
			return "redirect:/";
		} else
		{	
			return "redirect:/";
		}
		}

	@PostMapping("/updateImage")
	public String updateImage(@RequestParam(name = "imageId") String imageId, Model map) {
		map.addAttribute("imageId", imageId);
		return "update";

	}

	@PostMapping("/doUpdate")
	public String doUpdateImage(@RequestParam("file") MultipartFile file, HttpSession session,  RedirectAttributes redirectAttributes)
			throws IOException {
		User user = userService.getUser();
		int imageId = Integer.parseInt((String) session.getAttribute("imageId"));
		if (imageUtility.validateTotalUploadImageSize(file,redirectAttributes, user, imageRepository)) {
			imageRepository.editImageSourceById(file.getBytes(), imageId);

			return "redirect:/";
		} else
			return "redirect:/";

	}

	@PostMapping("/deleteImage")
	public String deleteImage(@RequestParam("imageId") String imageId) {
		imageRepository.deleteImageById(Integer.parseInt(imageId));
		return "redirect:/project/";
	}

}

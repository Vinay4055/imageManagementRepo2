package com.nagarro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("exception","The Image Size exceeds The Size Limit Of 1Mb");
        System.out.println("Exception Occures "+e.getCause().getMessage());
        return "redirect:/project/";

    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(
      MaxUploadSizeExceededException exc, 
      HttpServletRequest request,
      HttpServletResponse response,
      RedirectAttributes redirectAttributes) {
  
       
    	redirectAttributes.addFlashAttribute("exception", "File too large!");
        return "redirect:/project/";
    }

}

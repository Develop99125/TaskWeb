package com.kravchenko.spring.mvc.controller;

import com.kravchenko.spring.mvc.entity.Image;
import com.kravchenko.spring.mvc.entity.PaginationInfo;
import com.kravchenko.spring.mvc.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping("/")
    public String redirectFromMain0(){
        return "redirect:/register";
    }

    @RequestMapping("/images")
    public String redirectFromMain(){
        return "redirect:/images/page/1";
    }

    @RequestMapping("/my")
    public String redirectFromMainToMy(){
        return "redirect:/my/images/page/1";
    }

    @RequestMapping("/images/page/{id}")
    public String getTenImages(@PathVariable("id") int currentPage, Model model, Principal principal){
        if(currentPage == 0) currentPage = 1;
        PaginationInfo paginationInfo = imageService.getPagInfo(currentPage, null);
        List<Image> images = imageService.getTenImages(null, paginationInfo.getCurrentPage());
        model.addAttribute("images", images);
        model.addAttribute("pagInfo", paginationInfo);
        model.addAttribute("redirect", "images");
        model.addAttribute("username", principal.getName());
        return "images_page";
    }

    @RequestMapping("/my/images/page/{id}")
    public String getMyTenImages(@PathVariable("id") int currentPage, Model model, Principal principal){
        if(currentPage == 0) currentPage = 1;
        PaginationInfo paginationInfo = imageService.getPagInfo(currentPage, principal.getName());
        List<Image> images = imageService.getTenImages(principal.getName(), paginationInfo.getCurrentPage());
        model.addAttribute("images", images);
        model.addAttribute("pagInfo", paginationInfo);
        model.addAttribute("redirect", "my");
        model.addAttribute("username", principal.getName());
        return "my_images_page";
    }

    @RequestMapping("/images/add")
    public String addNewImage(){
        return "add_image_page";
    }

    @RequestMapping(value = "/images/saveImage", method = RequestMethod.POST)
    public String saveNewImage(@RequestParam("file") MultipartFile file, Principal principal){
        imageService.createNewImage(file, principal.getName());
        return "redirect:/my";
    }

    @RequestMapping("/images/delete/{id}/{redirect}")
    public String deleteImage(@PathVariable("id") int imageID, @PathVariable("redirect") String redirectTo, Principal principal){
        imageService.deleteImage(imageID, principal.getName());
        return "redirect:/" + redirectTo;
    }
}

package com.kravchenko.spring.mvc.service;

import com.kravchenko.spring.mvc.entity.Image;
import com.kravchenko.spring.mvc.entity.PaginationInfo;
import com.kravchenko.spring.mvc.entity.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    public List<Image> getTenImages(String userID, int currentPage);
    public PaginationInfo getPagInfo(int currentPage, String userID);

    public void createNewImage(MultipartFile file, String username);
    public void deleteImage(int id, String username);
}

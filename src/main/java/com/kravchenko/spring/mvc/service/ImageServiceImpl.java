package com.kravchenko.spring.mvc.service;

import com.kravchenko.spring.mvc.dao.ImageDAO;
import com.kravchenko.spring.mvc.entity.Image;
import com.kravchenko.spring.mvc.entity.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{


    private ImageDAO imageDAO;

    @Autowired
    public ImageServiceImpl(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Override
    @Transactional
    public List<Image> getTenImages(String userID, int currentPage) {
        return imageDAO.getTenImages(userID, currentPage);
    }

    @Override
    @Transactional
    public PaginationInfo getPagInfo(int currentPage, String userID) {
        return imageDAO.getPagInfo(currentPage, userID);
    }

    @Override
    public void createNewImage(MultipartFile file, String username) {
        imageDAO.createNewImage(file, username);
    }

    @Override
    public void deleteImage(int id, String username) {
        imageDAO.deleteImage(id, username);
    }
}

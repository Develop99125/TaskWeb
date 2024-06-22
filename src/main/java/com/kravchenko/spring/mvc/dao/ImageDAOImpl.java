package com.kravchenko.spring.mvc.dao;

import com.kravchenko.spring.mvc.entity.Image;
import com.kravchenko.spring.mvc.entity.PaginationInfo;
import com.kravchenko.spring.mvc.entity.User;
import com.kravchenko.spring.mvc.service.ImageService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Repository
public class ImageDAOImpl implements ImageDAO {

    private SessionFactory sessionFactory;
    private ApplicationContext appContext;

    @Autowired
    public ImageDAOImpl(SessionFactory sessionFactory, ApplicationContext appContext) {
        this.sessionFactory = sessionFactory;
        this.appContext = appContext;
    }

    @Override
    @Transactional
    public List<Image> getTenImages(String userID, int currentPage) {
        int pageSize = 10;
        Session session = sessionFactory.getCurrentSession();
        Query<Image> query;
        if(userID == null){
            query = session.createQuery("from images i order by i.id desc", Image.class);
        } else{
            query = session.createQuery("from images i where i.user.username = :userID order by i.id desc", Image.class);
            query.setParameter("userID", userID);
        }
        if (currentPage > 0) {
            query.setFirstResult((currentPage - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.getResultList();
    }

    @Override
    @Transactional
    public PaginationInfo getPagInfo(int currentPage, String userID) {
        int totalPages, pageSize = 10;

        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Long> countOfPagesQuery;
        if(userID != null){
            countOfPagesQuery = session.createQuery("select count (i.id) from images i where i.user.username = :userID", Long.class);
            countOfPagesQuery.setParameter("userID", userID);
        } else{
            countOfPagesQuery = session.createQuery("select count (i.id) from images i", Long.class);
        }

        int countOfImages = countOfPagesQuery.getSingleResult().intValue();
        if(countOfImages == 0){
            PaginationInfo paginationInfo = appContext.getBean("paginationInfo", PaginationInfo.class);
            paginationInfo.getPages().clear();
            return paginationInfo;
        }

        if(countOfImages % pageSize == 0) {
            totalPages = (countOfImages / pageSize);
        } else {
            totalPages = (countOfImages / pageSize) + 1;
        }

        if(currentPage < 1) {
            currentPage = 1;
        }
        if(currentPage > totalPages){
            currentPage = totalPages;
        }

        PaginationInfo paginationInfo = appContext.getBean("paginationInfo", PaginationInfo.class);
        paginationInfo.setTotalPages(totalPages);
        paginationInfo.setLastPage(pageSize);
        paginationInfo.setCurrentPage(currentPage);
        paginationInfo.setPageSize(pageSize);
        paginationInfo.generateInfo();

        return paginationInfo;
    }

    @Override
    @Transactional
    public void createNewImage(MultipartFile file, String username) {
        Session session = sessionFactory.getCurrentSession();
        Image image = new Image();

        try {
            image.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        User user = new User();
        user.setUsername(username);
        image.setUser(user);
        session.save(image);
    }

    @Override
    @Transactional
    public void deleteImage(int id, String username) {
        Session session = sessionFactory.getCurrentSession();
        if(!isAdmin(username)){
            if (!isOwner(id, username)) return;
        }
        Query<Image> query = session.createQuery("delete from images where id = :imageID");
        query.setParameter("imageID", id);
        query.executeUpdate();
    }

    private boolean isAdmin(String username){
        Session session = sessionFactory.getCurrentSession();
        Query<String> query = session.createQuery("select a.authority from authorities a where a.username = :username", String.class);
        query.setParameter("username", username);
        String result = query.getSingleResult();
        return result.equals("ROLE_ADMIN");
    }

    private boolean isOwner(int id, String username){
        Session session = sessionFactory.getCurrentSession();
        Query<String> query = session.createQuery("select i.user.username from images i where id = :imageID", String.class);
        query.setParameter("imageID", id);
        String result = query.getSingleResult();
        return result.equals(username);
    }
}

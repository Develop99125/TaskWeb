package com.kravchenko.spring.mvc.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class PaginationInfo {
    private List<Integer> pages;
    private int startPage;
    private int lastPage;
    private int currentPage;
    private int pageSize;

    private int totalPages;


    public PaginationInfo() {
        this.pages = new ArrayList<>();
    }


    public PaginationInfo(int pageSize, int currentPage, int lastPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.lastPage = lastPage;
    }

    public void generateInfo(){
        if(currentPage == 1) {  // Кнопка переключения в начало списка страниц
            startPage = 1;
            lastPage = pageSize;
            generateList();
            return;
        }
        if(currentPage == totalPages){ // кнопка переключения в конец списка страниц
            startPage = totalPages - pageSize + 1;
            if (startPage < 1) startPage = 1;
            lastPage = currentPage;
            generateList();
            return;
        }

        if(currentPage < startPage || currentPage > startPage + pageSize - 1){
            if(currentPage < 1) startPage = 1;
            else startPage = currentPage;
            lastPage = startPage + pageSize + 1;
            if(lastPage > totalPages) lastPage = totalPages;
            generateList();
        }
    }

    private void generateList(){
        pages.clear();
        for(int i = 0; i + startPage <= lastPage && i + startPage <= totalPages && i < pageSize; i++){
            pages.add(startPage + i);
        }
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

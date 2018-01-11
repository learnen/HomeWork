package com.onlywd.category.service;

import com.onlywd.category.dao.CategoryDao;
import com.onlywd.category.domain.Category;

import java.util.List;

public class CategoryService {
    public List<Category> findAll(){
        CategoryDao categoryDao = new CategoryDao();
        List<Category> result = categoryDao.queryAll();
        return result;
    }
}

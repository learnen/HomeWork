package com.onlywd.admin.category.service;

import com.onlywd.admin.category.service.exception.BookCountNotZeroException;
import com.onlywd.category.dao.CategoryDao;
import com.onlywd.category.domain.Category;

import java.util.List;
import java.util.UUID;

public class AdminCategoryService {
    private static CategoryDao categoryDao = new CategoryDao();;
    public static List<Category> findAll(){
        List<Category> categories = categoryDao.queryAll();
        return categories;
    }

    public static void add(String cname){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        categoryDao.insertCategory(uuid,cname);
    }

    public static int delete(String cid) throws BookCountNotZeroException {
        int i = categoryDao.deleteByCid(cid);
        return i;
    }

    public static Category findOneByCid(String cid){
        Category category = categoryDao.queryByCid(cid);
        return category;
    }

    public static void edit(String cid , String cname){
        categoryDao.update(cid,cname);
    }

}

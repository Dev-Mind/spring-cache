package com.devmind.cache.repository;


import com.devmind.cache.model.Category;

public class CustomRepository {


    public Category findCategory(){
        //Wait 2 secondes to simulate a long request
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return new Category();
        }
        return new Category().setName("Spring");
    }
}

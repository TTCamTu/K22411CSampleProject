package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListCategory implements Serializable {
    private ArrayList<Category> categories;

    public ListCategory() {
        categories = new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void generate_sample_dataset() {
        Random random = new Random();
        String[] categoryNames = {"Electronics", "Clothing", "Books", "Food", "Furniture"};
        for (int i = 1; i <= 5; i++) {
            int id = i;
            String name = categoryNames[i - 1];
            Category category = new Category(id, name);
            addCategory(category);
        }
    }
}
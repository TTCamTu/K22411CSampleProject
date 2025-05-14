package com.example.connectors;

import com.example.models.Category;
import com.example.models.ListCategory;

import java.util.ArrayList;

public class CategoryConnector {
    ListCategory listCategory;

    public CategoryConnector() {
        listCategory = new ListCategory();
        listCategory.generate_sample_dataset();
    }

    public ArrayList<Category> get_all_category() {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory = new ListCategory();
        }
        return listCategory.getCategories();
    }

    public ArrayList<Category> get_customers_by_provider(String provider) {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory = new ListCategory();
        }
        ArrayList<Category> results = new ArrayList<>();
        for (Category cate : listCategory.getCategories()) {
            if (cate.getPhone().startsWith(provider)) {
                results.add(cate);
            }
        }
        return results;
    }
}

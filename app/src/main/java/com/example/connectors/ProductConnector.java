package com.example.connectors;

import com.example.models.ListProduct;
import com.example.models.Product;

import java.util.ArrayList;

public class ProductConnector {
    ListProduct listProduct;

    public ProductConnector() {
        listProduct = new ListProduct();
        listProduct.generate_sample_dataset();
    }

    public ArrayList<Product> get_all_products() {
        if (listProduct == null) {
            listProduct = new ListProduct();
        }
        return listProduct.getProducts();
    }
}

package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListProduct implements Serializable {
    private ArrayList<Product> products;

    public ListProduct() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void generate_sample_dataset() {
        Random random = new Random();
        String[] productPrefixes = {"Phone", "Shirt", "Book", "Snack", "Chair"};
        String[] descriptions = {"High quality", "Brand new", "Best seller", "Limited edition", "Eco-friendly"};

        for (int i = 1; i <= 50; i++) {
            int id = i;
            String name = productPrefixes[random.nextInt(productPrefixes.length)] + i;
            int quantity = random.nextInt(100) + 1; // Số lượng từ 1 đến 100
            double price = 10 + random.nextDouble() * 490; // Giá từ 10 đến 500
            int cate_id = random.nextInt(5) + 1; // cate_id từ 1 đến 5 (tương ứng với ListCategory)
            String description = descriptions[random.nextInt(descriptions.length)];

            Product product = new Product(id, name, quantity, price, cate_id, description);
            addProduct(product);
        }
    }
}
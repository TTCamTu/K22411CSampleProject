package com.example.myapp;

import com.example.models.Products;

import java.io.Serializable;
import java.util.ArrayList;

public class ListProducts implements Serializable {
    private ArrayList<Products> products;

    public ListProducts() {
        products = new ArrayList<>();

        products.add(new Products(
                1,
                "SP001",
                "Smiski Work Series",
                999.99,
                "https://img.lazcdn.com/g/ff/kf/S2a594be8f5fe4d2b928bcd4e94465db8j.jpg_720x720q80.jpg_.webp"
        ));
        products.add(new Products(
                2,
                "SP002",
                "Smiski Hippers Serires",
                799.99,
                "https://img.lazcdn.com/g/ff/kf/Se030675398b54c8ab46483752c4aa4e8e.jpg_720x720q80.jpg_.webp"
        ));
        products.add(new Products(
                3,
                "SP003",
                "Smiski Sonny",
                1199.99,
                "https://img.lazcdn.com/g/ff/kf/Scbe62d0eec9d4901969f2a4d66418365N.jpg_720x720q80.jpg_.webp"
        ));
        products.add(new Products(
                4,
                "SP004",
                "Sony WH-1000XM5 Headphones",
                349.99,
                "https://cf.shopee.vn/file/sg-11134201-23020-3a4b2c4n0tnv64"
        ));
        products.add(new Products(
                5,
                "SP005",
                "Nintendo Switch OLED",
                349.99,
                "https://cf.shopee.vn/file/sg-11134201-22120-4b5c3d5o0tnv64"
        ));
        products.add(new Products(
                6,
                "SP006",
                "Smiski Mini Blind Box",
                699.99,
                "https://img.lazcdn.com/g/p/180938da132427a3a80b402886a3164a.jpg_720x720q80.jpg_.webp"
        ));
        products.add(new Products(
                7,
                "SP007",
                "Glow Smiski",
                399.99,
                "https://img.lazcdn.com/g/p/37e334f58f6e3b497e2ce6da20aa898b.jpg_720x720q80.jpg_.webp"
        ));
        products.add(new Products(
                8,
                "SP008",
                "Angel Smiski",
                1299.99,
                "https://img.lazcdn.com/g/ff/kf/Sdebda2ef6f0443979536eb9e57ff3111d.jpg_720x720q80.jpg_.webp"
        ));
        products.add(new Products(
                9,
                "SP009",
                "Blue Smiski",
                879.99,
                "https://img.lazcdn.com/g/p/482361243ae7b5af2cc126b07a20fb49.jpg_720x720q80.jpg_.webp"
        ));
        products.add(new Products(
                10,
                "SP010",
                "Smiski Full Series",
                179.99,
                "https://img.lazcdn.com/g/p/ff2f3afaabaefcfc65c8e631063a1e80.jpg_720x720q80.jpg_.webp"
        ));
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    // Phương thức để thêm sản phẩm
    public void addProduct(Products product) {
        products.add(product);
    }

    // Phương thức để xóa sản phẩm
    public void removeProduct(Products product) {
        products.remove(product);
    }
}
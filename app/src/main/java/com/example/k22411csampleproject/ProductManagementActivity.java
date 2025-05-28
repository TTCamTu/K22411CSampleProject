package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Category;
import com.example.models.ListCategory;
import com.example.models.Product;

import java.util.ArrayList;

public class ProductManagementActivity extends AppCompatActivity {
    Spinner spinnerCategory;
    ArrayAdapter<Category> adapterCategory;
    ListCategory listCategory;

    ListView lvProduct;
    ArrayAdapter<Product> adapterProduct;
    ArrayList<Product> allProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category c = adapterCategory.getItem(i);
                displayProductsByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product p = adapterProduct.getItem(position);
                displayProductDetailActivity(p);
            }
        });
    }

    private void displayProductDetailActivity(Product p) {
        Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
        intent.putExtra("selected_product", p);
        startActivity(intent);
    }

    private void displayProductsByCategory(Category c) {
        adapterProduct.clear();
        adapterProduct.addAll(c.getProducts());
        adapterProduct.notifyDataSetChanged();
    }

    private void addViews() {
        spinnerCategory = findViewById(R.id.spinnerCategory);
        adapterCategory = new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory = new ListCategory();
        listCategory.generate_sample_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct = findViewById(R.id.lvProduct);
        adapterProduct = new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);

        allProducts = new ArrayList<>();
        for (Category c : listCategory.getCategories()) {
            allProducts.addAll(c.getProducts());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_new_product) {
            Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
            startActivityForResult(intent, 400);
            return true;
        } else if (id == R.id.menu_list_products_new) {
            Toast.makeText(ProductManagementActivity.this, R.string.msg_broadcast_advertising, Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_list_products_about) {
            Toast.makeText(ProductManagementActivity.this, R.string.msg_help, Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 400 && resultCode == 600 && data != null) {
            Product p = (Product) data.getSerializableExtra("NEW_PRODUCT");
            if (p != null) {
                processSaveProduct(p);
            }
        }
    }

    private void processSaveProduct(Product p) {
        for (Product prod : allProducts) {
            if (prod.getId() == p.getId()) {
                Toast.makeText(this, "Sản phẩm đã tồn tại (ID trùng)", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        allProducts.add(p);
        // Thêm sản phẩm vào danh mục tương ứng
        for (Category c : listCategory.getCategories()) {
            if (c.getId() == p.getCate_id()) {
                c.addProduct(p);
                break;
            }
        }
        // Cập nhật ListView theo danh mục hiện tại
        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        if (selectedCategory != null) {
            displayProductsByCategory(selectedCategory);
        }
        adapterProduct.notifyDataSetChanged();
    }
}
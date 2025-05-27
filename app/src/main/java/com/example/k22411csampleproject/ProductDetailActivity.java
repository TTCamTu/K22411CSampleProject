package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Product;

public class ProductDetailActivity extends AppCompatActivity {
    EditText edtProductID, edtProductName, edtProductQuantity, edtProductPrice,
            edtProductCateID, edtProductDescription, edtProductImageID;
    Button btn_product_new, btn_product_save, btn_product_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        addViews();
        addEvents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edtProductID = findViewById(R.id.edtProductID);
        edtProductName = findViewById(R.id.edtProductName);
        edtProductQuantity = findViewById(R.id.edtProductQuantity);
        edtProductPrice = findViewById(R.id.edtProductPrice);
        edtProductCateID = findViewById(R.id.edtProductCateID);
        edtProductDescription = findViewById(R.id.edtProductDescription);
        edtProductImageID = findViewById(R.id.edtProductImageID);
        btn_product_new = findViewById(R.id.btn_product_new);
        btn_product_save = findViewById(R.id.btn_product_save);
        btn_product_remove = findViewById(R.id.btn_product_remove);

        displayInfo();
    }

    private void addEvents() {
        btn_product_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSaveProduct();
            }
        });

        btn_product_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });

        btn_product_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetailActivity.this, "Chức năng xóa chưa được triển khai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processSaveProduct() {
        try {
            if (edtProductID.getText().toString().isEmpty() ||
                    edtProductName.getText().toString().isEmpty() ||
                    edtProductQuantity.getText().toString().isEmpty() ||
                    edtProductPrice.getText().toString().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin bắt buộc (ID, Name, Quantity, Price)", Toast.LENGTH_SHORT).show();
                return;
            }

            Product p = new Product();
            p.setId(Integer.parseInt(edtProductID.getText().toString()));
            p.setName(edtProductName.getText().toString());
            p.setQuantity(Integer.parseInt(edtProductQuantity.getText().toString()));
            p.setPrice(Double.parseDouble(edtProductPrice.getText().toString()));
            p.setCate_id(edtProductCateID.getText().toString().isEmpty() ? 0 : Integer.parseInt(edtProductCateID.getText().toString()));
            p.setDescription(edtProductDescription.getText().toString());
            p.setImage_id(edtProductImageID.getText().toString().isEmpty() ? 0 : Integer.parseInt(edtProductImageID.getText().toString()));

            Intent intent = getIntent();
            intent.putExtra("NEW_PRODUCT", p);
            setResult(600, intent);
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập đúng định dạng số cho ID, Quantity, Price, Category ID, Image ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        edtProductID.setText("");
        edtProductName.setText("");
        edtProductQuantity.setText("");
        edtProductPrice.setText("");
        edtProductCateID.setText("");
        edtProductDescription.setText("");
        edtProductImageID.setText("");
    }

    private void displayInfo() {
        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("selected_product");
        if (p == null) return;
        edtProductID.setText(String.valueOf(p.getId()));
        edtProductName.setText(p.getName());
        edtProductQuantity.setText(String.valueOf(p.getQuantity()));
        edtProductPrice.setText(String.valueOf(p.getPrice()));
        edtProductCateID.setText(String.valueOf(p.getCate_id()));
        edtProductDescription.setText(p.getDescription());
        edtProductImageID.setText(String.valueOf(p.getImage_id()));
    }
}
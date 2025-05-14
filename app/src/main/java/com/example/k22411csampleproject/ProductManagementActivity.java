package com.example.k22411csampleproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.connectors.ProductConnector;
import com.example.models.Product;

public class ProductManagementActivity extends AppCompatActivity {
    ListView lvProduct;
    ArrayAdapter<Product> adapter;
    ProductConnector connector;

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
        addEvent();
    }

    private void addViews() {
        lvProduct = findViewById(R.id.lvProduct);
        adapter = new ArrayAdapter<>(ProductManagementActivity.this, android.R.layout.simple_list_item_1);
        connector = new ProductConnector();
        adapter.addAll(connector.get_all_products());
        lvProduct.setAdapter(adapter);
    }

    private void addEvent() {
        lvProduct.setOnItemLongClickListener((parent, view, i, l) -> {
            Product selected = adapter.getItem(i);
            adapter.remove(selected);
            return true; // Return true to consume the long click event
        });
    }
}
package com.example.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.PaymentMethodAdapter;
import com.example.connectors.PaymentMethodConnector;

public class PaymentMethodActivity extends AppCompatActivity {

    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;
    PaymentMethodConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);

        addView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addView() {
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
        adapter = new PaymentMethodAdapter(PaymentMethodActivity.this, R.layout.item_payment_method);
        lvPaymentMethod.setAdapter(adapter);

        // Khởi tạo PaymentMethodConnector và lấy dữ liệu từ SQLite
        connector = new PaymentMethodConnector(this);
        adapter.addAll(connector.getAllPaymentMethodsFromDB());
    }
}
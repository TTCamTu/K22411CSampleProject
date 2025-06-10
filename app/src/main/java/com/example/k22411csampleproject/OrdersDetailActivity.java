package com.example.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.OrdersDetailAdapter;
import com.example.connectors.OrdersDetailConnector;
import com.example.connectors.SQLiteConnector;
import com.example.models.OrderDetails;

import java.util.ArrayList;

public class OrdersDetailActivity extends AppCompatActivity {

    ListView lvOrderDetails;
    OrdersDetailAdapter adapter;
    int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_detail);

        // Lấy OrderId từ Intent
        orderId = getIntent().getIntExtra("orderId", -1);
        if (orderId == -1) {
            finish(); // Kết thúc nếu không có OrderId hợp lệ
            return;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvOrderDetails = findViewById(R.id.lvOrderDetails);
        adapter = new OrdersDetailAdapter(this, R.layout.item_order_detail);
        lvOrderDetails.setAdapter(adapter);

        SQLiteConnector connector = new SQLiteConnector(this);
        OrdersDetailConnector odc = new OrdersDetailConnector();
        ArrayList<OrderDetails> details = odc.getOrderDetailsByOrderId(connector.openDatabase(), orderId);
        adapter.addAll(details);
    }
}
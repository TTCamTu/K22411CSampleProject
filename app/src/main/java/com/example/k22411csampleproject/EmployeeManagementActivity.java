package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.ListEmployee;

public class EmployeeManagementActivity extends AppCompatActivity {
    private RecyclerView rvEmployees;
    private EmployeeAdapter adapter;
    private ListEmployee listEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_management);
        // Khởi tạo ListEmployee và tạo dữ liệu giả lập
        listEmployee = new ListEmployee();
        listEmployee.gen_dataset();
//
//        // Khởi tạo RecyclerView
//        rvEmployees = findViewById(R.id.rv_employees);
//        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new EmployeeAdapter(listEmployee.getEmployees());
//        rvEmployees.setAdapter(adapter);

        // Xử lý insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void open_employee_healthcare(View view) {
        Intent intent= new Intent(EmployeeManagementActivity.this, EmployeeHealthCareActivity.class);
        startActivity(intent);
    }
}
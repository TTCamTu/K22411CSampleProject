package com.example.connectors;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodConnector {
    private SQLiteConnector sqliteConnector;

    public PaymentMethodConnector(Activity context) {
        sqliteConnector = new SQLiteConnector(context);
    }

    /**
     * Lấy toàn bộ dữ liệu từ bảng Payment trong SQLite
     * @return ArrayList<PaymentMethod>
     */
    public ArrayList<PaymentMethod> getAllPaymentMethodsFromDB() {
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
        SQLiteDatabase database = sqliteConnector.openDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM Payment", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                PaymentMethod pm = new PaymentMethod();
                pm.setId(id);
                pm.setName(name);
                pm.setDescription(description);
                paymentMethods.add(pm);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý lỗi nếu bảng hoặc cột không tồn tại
        }
        return paymentMethods;
    }
}
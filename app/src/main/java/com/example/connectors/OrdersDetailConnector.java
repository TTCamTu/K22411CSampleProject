package com.example.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.OrderDetails;

import java.util.ArrayList;

public class OrdersDetailConnector {
    /**
     * Lấy danh sách chi tiết đơn hàng dựa trên OrderId
     * @param database SQLiteDatabase
     * @param orderId int
     * @return ArrayList<OrderDetails>
     */
    public ArrayList<OrderDetails> getOrderDetailsByOrderId(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetails> details = new ArrayList<>();
        String sql = "SELECT Id, OrderId, ProductId, Quantity, Price, Discount, VAT, TotalValue " +
                "FROM OrderDetails WHERE OrderId = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(orderId)});
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int orderIdFromDB = cursor.getInt(1);
                int productId = cursor.getInt(2);
                int quantity = cursor.getInt(3);
                double price = cursor.getDouble(4);
                double discount = cursor.getDouble(5);
                double vat = cursor.getDouble(6);
                double totalValue = cursor.getDouble(7);

                OrderDetails detail = new OrderDetails();
                detail.setId(id);
                detail.setOrderId(orderIdFromDB);
                detail.setProductID(productId);
                detail.setQuantity(quantity);
                detail.setPrice(price);
                detail.setDiscount(discount);
                detail.setVAT(vat);
                detail.setTotalValue(totalValue);
                details.add(detail);
            }
        } finally {
            cursor.close();
        }
        return details;
    }
}
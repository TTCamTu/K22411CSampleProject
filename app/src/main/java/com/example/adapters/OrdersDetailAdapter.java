package com.example.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411csampleproject.R;
import com.example.models.OrderDetails;

public class OrdersDetailAdapter extends ArrayAdapter<OrderDetails> {
    Activity context;
    int resource;

    public OrdersDetailAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView txtProductId = item.findViewById(R.id.txtProductId);
        TextView txtQuantity = item.findViewById(R.id.txtQuantity);
        TextView txtPrice = item.findViewById(R.id.txtPrice);
        TextView txtDiscount = item.findViewById(R.id.txtDiscount);
        TextView txtVat = item.findViewById(R.id.txtVat);
        TextView txtTotalValue = item.findViewById(R.id.txtTotalValue);

        OrderDetails detail = getItem(position);
        txtProductId.setText(txtProductId.getText() + String.valueOf(detail.getProductID()));
        txtQuantity.setText(txtQuantity.getText() + String.valueOf(detail.getQuantity()));
        txtPrice.setText(txtPrice.getText() + String.valueOf(detail.getPrice()) + " VNĐ");
        txtDiscount.setText(txtDiscount.getText() + String.valueOf(detail.getDiscount()) + "%");
        txtVat.setText(txtVat.getText() + String.valueOf(detail.getVAT()) + "%");
        txtTotalValue.setText(txtTotalValue.getText() + String.valueOf(detail.getTotalValue()) + " VNĐ");

        return item;
    }
}
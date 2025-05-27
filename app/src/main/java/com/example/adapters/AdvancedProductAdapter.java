package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411csampleproject.R;
import com.example.models.Product;

public class AdvancedProductAdapter extends ArrayAdapter <Product> {

    Activity context;
    int resource;
    public AdvancedProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        // nhân bản giao diện theo từng position mà dữ liệu được duyệt qua
        View item=inflater.inflate(this.resource,null);
        //lúc này toàn bộ view nawfm trong layout resource (item_advanced_product) sẽ được mô hình hóa hướng đối tượng và ược quản lý t biến item
        // muốn truy xuất tới các view con trong nó thì phải thông qua biến item
        ImageView imgProduct=item.findViewById(R.id.imgProduct);
        TextView txtProductID=item.findViewById(R.id.txtProductID);
        TextView txtProductName=item.findViewById(R.id.txtProductName);
        TextView txtProductQuantity=item.findViewById(R.id.txtProductQuantity);
        TextView txtProductPrice=item.findViewById(R.id.txtProductPrice);
        ImageView imgCart=item.findViewById(R.id.imgCart);

        //lấy mô hình đối tượng đang được nhân bản ở vị tr position (đối soos 1)
        Product p=this.getItem(position);
        //rải dữ liệu của product lên giao diện trong item
        imgProduct.setImageResource(p.getImage_id());
        txtProductID.setText(p.getId()+"");
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(p.getQuantity()+"");
        txtProductPrice.setText(p.getPrice()+"VND");

        //xử lý nhấn vào nút mua
        return item;
    }
}

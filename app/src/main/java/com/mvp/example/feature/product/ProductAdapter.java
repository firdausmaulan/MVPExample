package com.mvp.example.feature.product;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.example.R;
import com.mvp.example.model.product.Product;
import com.mvp.example.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> listProduct = new ArrayList<>();

    void addList(List<Product> products) {
        listProduct = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product, parent, false);
        return new ViewHolder(itemView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvProduct;

        ViewHolder(View view) {
            super(view);
            ivProduct = view.findViewById(R.id.iv_product);
            tvProduct = view.findViewById(R.id.tv_product);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = listProduct.get(position);
        ImageLoader.load(product.getPhoto(), holder.ivProduct);
        holder.tvProduct.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }
}
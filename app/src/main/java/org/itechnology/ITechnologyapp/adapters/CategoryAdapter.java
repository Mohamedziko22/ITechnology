package org.itechnology.ITechnologyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.itechnology.ITechnologyapp.databinding.ItemRecyclerBinding;
import org.itechnology.ITechnologyapp.models.CategoryModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {
    Context mContext;
    ArrayList<CategoryModel> arrayList;
    OnClickListener listener;

    public CategoryAdapter(Context mContext, ArrayList<CategoryModel> arrayList, OnClickListener listener) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(arrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setList(ArrayList<CategoryModel> list) {
        arrayList.clear();
        arrayList.addAll(list);
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemRecyclerBinding binding;

        public Holder(@NonNull ItemRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CategoryModel model, int position) {
            binding.txtDate.setText(model.getCreatedDate());
            binding.txtDesc.setText(model.getDescAr());
            binding.txtName.setText(model.getNameAr());
            binding.btnApproval.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickApproval(model, position);
                }
            });
            binding.btnDecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickDecline(model, position);
                }
            });
        }
    }

    public interface OnClickListener {
        void onClickApproval(CategoryModel model, int position);

        void onClickDecline(CategoryModel model, int position);
    }
}

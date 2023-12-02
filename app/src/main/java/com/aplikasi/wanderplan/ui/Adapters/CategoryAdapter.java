package com.aplikasi.wanderplan.ui.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aplikasi.wanderplan.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<String> dataList;

    RecyclerView receiptRv, categoryRv;

    final Button[] stateCategory = {null, null}; // {previous, current}

    public String getCurrentCategory(){
        if(stateCategory[1] != null)
            return stateCategory[1].getText().toString();
        return "";
    }

    public CategoryAdapter(List<String> dataList, RecyclerView receiptRv, RecyclerView categoryRv) {
        this.dataList = dataList;
        this.receiptRv = receiptRv;
        this.categoryRv = categoryRv;
    }

    public void updateData(List<String> updatedCategory){
        this.dataList = updatedCategory;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.AfterLoginActivity,parent,false);
//        ViewHolder viewHolder= new ViewHolder();
//        return viewHolder;
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        String btnText = dataList.get(position).toString();
        holder.btnCategory.setText(btnText);

        if(btnText.toLowerCase().contains("semua")){
            stateCategory[0] = holder.btnCategory;
            stateCategory[1] = holder.btnCategory;

            stateCategory[1].setTextAppearance(R.style.btn_accent_light_rounded);
            stateCategory[1].setBackground(ResourcesCompat.getDrawable(holder.context.getResources(), R.drawable.btn_accent_light, null));
        }

        holder.btnCategory.setOnClickListener(view -> {
            receiptRv.scrollToPosition(0);

            stateCategory[0] = stateCategory[1];
            stateCategory[1] = holder.btnCategory;

            stateCategory[0].setTextAppearance(R.style.btn_accent_outline_rounded);
            stateCategory[0].setBackground(ResourcesCompat.getDrawable(holder.context.getResources(), R.drawable.btn_accent_outline, null));


            stateCategory[1].setTextAppearance(R.style.btn_accent_light_rounded);
            stateCategory[1].setBackground(ResourcesCompat.getDrawable(holder.context.getResources(), R.drawable.btn_accent_light, null));
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        Button btnCategory;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
        }
    }
}
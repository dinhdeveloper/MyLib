package com.canhdinh.mylib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.canhdinh.lib.helper.MyToast;
import com.canhdinh.lib.loadingbutton.ButtonLoading;

import java.util.List;

public class OnClickAdapter extends RecyclerView.Adapter<OnClickAdapter.ViewHolder> {


    public OnClickAdapter(Context context, List<String> string) {
        this.context = context;
        this.string = string;
    }

    Context context;
    List<String> string;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String aa = string.get(position);
        holder.loadingbutton.setText(aa);
    }

    @Override
    public int getItemCount() {
        return string.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        ButtonLoading loadingbutton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            loadingbutton = itemView.findViewById(R.id.loadingbutton);

            loadingbutton.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener() {
                @Override
                public void onClick() {
                    MyToast.show(context,"onClick");
                }

                @Override
                public void onStart() {
                    MyToast.show(context,"onStart");
                }

                @Override
                public void onFinish() {
                    MyToast.show(context,"onFinish");
                }
            });
        }
    }
}

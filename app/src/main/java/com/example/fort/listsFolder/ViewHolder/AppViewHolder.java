package com.example.fort.listsFolder.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fort.R;
import com.example.fort.listsFolder.Interface.AppOnClickListener;

public class AppViewHolder extends RecyclerView.ViewHolder {

    public ImageView icons_app,lock_app;
    public TextView name_app;
    private AppOnClickListener appOnClickListener;

    public void setAppOnClickListener(AppOnClickListener appOnClickListener) {
        this.appOnClickListener = appOnClickListener;
    }

    public AppViewHolder(@NonNull View itemView) {
        super(itemView);
        icons_app = itemView.findViewById(R.id.icons_apps);
        lock_app = itemView.findViewById(R.id.lock_app);
        name_app = itemView.findViewById(R.id.apps_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appOnClickListener.selectApp(getAdapterPosition());
            }
        });

    }
}

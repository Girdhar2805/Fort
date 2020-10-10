package com.example.fort.listsFolder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fort.R;
import com.example.fort.listsFolder.Interface.AppOnClickListener;
import com.example.fort.listsFolder.Model.AppItem;
import com.example.fort.listsFolder.ViewHolder.AppViewHolder;
import com.example.fort.listsFolder.utils.Utils;

import java.util.List;

public class AppAdapters extends RecyclerView.Adapter<AppViewHolder> {

    private Context context;
    private List<AppItem> appItemList;
    private Utils utils;

    public AppAdapters(Context context, List<AppItem> appItemList) {
        this.context = context;
        this.appItemList = appItemList;
        this.utils = new Utils(context);
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layput_apps, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AppViewHolder holder, int position) {
        holder.name_app.setText(appItemList.get(position).getName());
        holder.icons_app.setImageDrawable(appItemList.get(position).getIcons());

        final String pk = appItemList.get(position).getPackagename();

        if (utils.isLock(pk)) {
            holder.lock_app.setImageResource(R.drawable.lockred);
        } else {
            holder.lock_app.setImageResource(R.drawable.lock);
        }
        holder.setAppOnClickListener(new AppOnClickListener() {
            @Override
            public void selectApp(int pos) {
                if(utils.isLock(pk))
                {
                    holder.lock_app.setImageResource(R.drawable.lock);
                    utils.unlock(pk);
                }
                else
                {
                    holder.lock_app.setImageResource(R.drawable.lockred);
                    utils.lock(pk);
                }

            }
        });
        {

        }

    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}

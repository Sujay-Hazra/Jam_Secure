package com.example.jamsecure;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HelperAdapter extends RecyclerView.Adapter {
    List<FetchData> fetchDataList;

    public HelperAdapter(List<FetchData> fetchDataList) {
        this.fetchDataList = fetchDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.jamrooms,parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ViewHolderClass viewHolderClass=(ViewHolderClass)holder;
        FetchData fetchData=fetchDataList.get(position);
        viewHolderClass.name.setText(fetchData.getName());
        viewHolderClass.jamr.setText(fetchData.getJamrate());
        viewHolderClass.mob.setText(fetchData.getMob());

     }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name,jamr,mob;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            jamr=itemView.findViewById(R.id.jamrate);
            mob=itemView.findViewById(R.id.mob);
        }
    }
}

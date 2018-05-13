package com.erprakash.chatui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<MessageAdapter> list = Collections.emptyList();
    Context context;

    public RecyclerViewAdapter(List<MessageAdapter> list,Context context) {
        this.list = list ;
        this.context = context;
    }
    
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.message.setText(list.get(position).getMessageSent());
        holder.arrowImage.setImageResource(list.get(position).getImageArrowId());
        holder.imageView.setImageResource(list.get(position).getImageID());
    }




    
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, MessageAdapter data) {
        list.add(position, data);
        }



    // Remove a RecyclerView item containing a specified Data object
    public void remove(MessageAdapter data) {
        int position = list.indexOf(data);
        list.remove(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView message    ;
        ImageView imageView , arrowImage;

        ViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            arrowImage = itemView.findViewById(R.id.imageView1);
        }
    }
}
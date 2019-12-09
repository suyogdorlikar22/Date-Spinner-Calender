package com.suyog.sanpworkassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DateListAdapter extends RecyclerView.Adapter<DateListAdapter.ViewHolder> {

    private Context context;
    ArrayList<DateList> articleArrayList;

    public DateListAdapter(Context context, ArrayList<DateList> articleArrayList) {
        this.context = context;
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public DateListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateListAdapter.ViewHolder viewHolder, int i) {
        final DateList article=articleArrayList.get(i);
        viewHolder.tvTitle.setText(article.getDate());
//        viewHolder.tvAuthorAndPublishedAt.setText("-"+article.getAuthor() +" | "+"Piblishetd At: "+article.getPublishedAt());
//        viewHolder.tvDescription.setText(article.getDescription());
//        Glide.with(context)
//                .load(article.getUrlToImage())
//                .into(viewHolder.imgViewCover);

        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DateShowActivity.class);
                intent.putExtra("Date",article.getDate());
                context.startActivity(intent);
                Toast.makeText(context,article.getDate(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=(TextView) itemView.findViewById(R.id.tvDate);

        }
    }
}
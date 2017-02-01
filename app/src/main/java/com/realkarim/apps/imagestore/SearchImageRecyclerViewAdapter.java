package com.realkarim.apps.imagestore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.realkarim.apps.imagestore.getty.models.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Karim Mostafa on 2/1/17.
 */

public class SearchImageRecyclerViewAdapter extends RecyclerView.Adapter<SearchImageRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Image> imageLists;
    private Context context;

    public SearchImageRecyclerViewAdapter(Context context){
        imageLists = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_search_recyclerview_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load(imageLists.get(position).getDisplaySizes().get(0).getUri())
                .error(R.drawable.photo_not_available)
                .placeholder(R.drawable.loading)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }

    public void addPage(ArrayList<Image> newPage){
        imageLists.addAll(newPage);
        this.notifyDataSetChanged();
    }
}

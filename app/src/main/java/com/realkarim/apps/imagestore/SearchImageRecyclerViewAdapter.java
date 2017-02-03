package com.realkarim.apps.imagestore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.realkarim.apps.imagestore.getty.models.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Karim Mostafa on 2/1/17.
 */

public abstract class SearchImageRecyclerViewAdapter extends RecyclerView.Adapter<SearchImageRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Image> imageLists;
    private Context context;

    private Integer page;
    private Integer pageSize;
    private LayoutInflater layoutInflater;
    private String TAG = getClass().getName();

    PopupWindow pw;

    public SearchImageRecyclerViewAdapter(Context context) {
        imageLists = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        page = 1;
        pageSize = 10;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_search_recyclerview_item, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = viewHolder.getAdapterPosition();
                if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION)
                    showPopupWindow(v, position);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load(imageLists.get(position).getDisplaySizes().get(0).getUri())
                .error(R.drawable.photo_not_available)
                .placeholder(R.drawable.loading)
                .into(holder.image);

        holder.imageTitle.setText(imageLists.get(position).getTitle());
        holder.imageID.setText("ID: " + imageLists.get(position).getId());

        // load more when reaching the end of the list
        if (position == imageLists.size() - 1) {
            page++;
            pageSize *= 2;
            loadMore(page, pageSize);
        }
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView imageTitle;
        public TextView imageID;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            imageTitle = (TextView) v.findViewById(R.id.image_title);
            imageID = (TextView) v.findViewById(R.id.image_id);
        }
    }

    public void addPage(ArrayList<Image> newPage) {
        imageLists.addAll(newPage);
        this.notifyDataSetChanged();
    }

    public void clearList() {
        imageLists.clear();
        page = 1;
        pageSize = 10;
        // notifyDataSetChanged() will be called when the next page is added
    }

    private void showPopupWindow(View v, Integer position) {
        try {
            //Inflate the view from a predefined XML layout
            View layout = layoutInflater.inflate(R.layout.recyclerview_item_popup,
                    ((ViewGroup) v.getParent()), false);

            // create PopupWindow
            final PopupWindow pw = new PopupWindow(
                    layout,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            // display the popup in the center
            pw.showAtLocation(v.getRootView(), Gravity.CENTER, 0, 0);

            TextView title = (TextView) layout.findViewById(R.id.image_title);
            TextView caption = (TextView) layout.findViewById(R.id.image_caption);
            Button dismiss = (Button) layout.findViewById(R.id.dismiss);

            title.setText("Title: " + imageLists.get(position).getTitle());
            caption.setText(imageLists.get(position).getCaption());
            dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pw.dismiss();
                }
            });

        } catch (Exception e) {
            Toast.makeText(context, "Error showing Caption!", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    public abstract void loadMore(Integer nextPage, Integer nextPageSize);

}

package com.realkarim.apps.imagestore;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindDimen;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Karim Mostafa on 1/31/17.
 */

public class SearchFragment extends Fragment implements SearchContract.View, EditText.OnEditorActionListener {

    @BindView(R.id.search_box)
    EditText searchBox;

    @BindView(R.id.image_recycler_view)
    RecyclerView imageRecyclerView;

    @BindInt(R.integer.recycler_view_item_width)
    int recycler_view_item_width;

    RecyclerView.LayoutManager mLayoutManager;
    SearchImageRecyclerViewAdapter searchImageRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        // get screen width to calculate number of columns
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        // use a grid layout manager
        mLayoutManager = new GridLayoutManager(getActivity(), pxToDp(width)/recycler_view_item_width);
        imageRecyclerView.setLayoutManager(mLayoutManager);

        // set adapter
        searchImageRecyclerViewAdapter = new SearchImageRecyclerViewAdapter();
        imageRecyclerView.setAdapter(searchImageRecyclerViewAdapter);

        searchBox.setOnEditorActionListener(this);

        return view;
    }

    @Override
    public void onPageReceived(ArrayList arrayList) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId){
            case EditorInfo.IME_ACTION_SEARCH:
                search();
                break;
        }
        return false;
    }

    public void search(){
        // hide keyboard if still visible
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

        // search
        showMessage("Search " + searchBox.getText());
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
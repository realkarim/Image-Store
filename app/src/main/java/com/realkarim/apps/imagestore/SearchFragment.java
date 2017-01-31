package com.realkarim.apps.imagestore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Karim Mostafa on 1/31/17.
 */

public class SearchFragment extends Fragment implements SearchContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onPageReceived(ArrayList arrayList) {

    }

    @Override
    public void showMessage(String message) {

    }
}

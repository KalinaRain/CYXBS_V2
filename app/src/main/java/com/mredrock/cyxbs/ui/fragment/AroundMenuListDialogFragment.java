package com.mredrock.cyxbs.ui.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AroundMenuListDialogFragment extends Fragment {


    public AroundMenuListDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_around_menu_list_dialog, container, false);
    }


}

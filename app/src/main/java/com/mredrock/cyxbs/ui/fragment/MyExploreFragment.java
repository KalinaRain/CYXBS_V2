package com.mredrock.cyxbs.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.ui.activity.AroundActivity;
import com.mredrock.cyxbs.ui.activity.BoardActivity;
import com.mredrock.cyxbs.ui.activity.ClassTalkActivity;
import com.mredrock.cyxbs.ui.activity.BBSActivity;
import com.mredrock.cyxbs.ui.activity.MapActivity;
import com.mredrock.cyxbs.ui.activity.PortalActivity;
import com.mredrock.cyxbs.ui.activity.SecretActivity;
import com.mredrock.cyxbs.ui.activity.WhatToEatActivity;

import static android.view.View.OnClickListener;

public class MyExploreFragment extends Fragment implements OnClickListener{

    public MyExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_explore, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        int start = R.id.tv_portal;
        for(int i = 0;i < 8;i++){
            view.findViewById(start+i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_portal:
                startActivity(new Intent(getActivity(), PortalActivity.class));
                break;
            case R.id.tv_activity_board:
                startActivity(new Intent(getActivity(),BoardActivity.class));
                break;
            case R.id.tv_around:
                startActivity(new Intent(getActivity(), AroundActivity.class));
                break;
            case R.id.tv_map:
                startActivity(new Intent(getActivity(), MapActivity.class));
                break;
            case R.id.tv_eat:
                startActivity(new Intent(getActivity(), WhatToEatActivity.class));
                break;
            case R.id.tv_community:
                startActivity(new Intent(getActivity(), BBSActivity.class));
                break;
            case R.id.tv_class_talk:
                startActivity(new Intent(getActivity(), ClassTalkActivity.class));
                break;
            case R.id.tv_secret:
                startActivity(new Intent(getActivity(), SecretActivity.class));
                break;
        }
    }
}

package com.mredrock.cyxbs.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2014/11/10.
 */
public class NavListSettingsAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Integer> mListString;

    public NavListSettingsAdapter(Context context, int[] options) {
        mListString = new ArrayList<Integer>();
        for(int i = 0 ; i < options.length ;i++) {
            mListString.add(options[i]);
        }
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mListString.size();
    }

    @Override
    public Object getItem(int position) {
        return mListString.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_navigation_settings, parent, false);
            viewHolder.tv = (TextView)convertView.findViewById(R.id.tv_nav_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(position);
        return convertView;
    }

    private class ViewHolder{
        private TextView tv;
        void setData(int position){
            tv.setText(mListString.get(position));
        }
    }

}

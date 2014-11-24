package com.mredrock.cyxbs.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import static com.mredrock.cyxbs.util.LogUtils.LOGD;

/**
 * Created by David on 2014/11/10.
 */
public class NavListOptionsAdapter extends BaseAdapter {
    private static final String TAG = LogUtils.makeLogTag(NavListOptionsAdapter.class);

    private LayoutInflater mInflater;

    private List<Integer> mListString;
    private int[] mImgId;

    public static int selectedItem = -1;

    public NavListOptionsAdapter(Context context, int[] options, int[] iv) {
        mImgId = new int[iv.length];

        mListString = new ArrayList<Integer>();
        for(int i = 0 ; i < options.length ;i++) {
            mListString.add(options[i]);
        }
        for (int k = 0; k < iv.length; k++) {
            mImgId[k] = iv[k];
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
            convertView = mInflater.inflate(R.layout.item_navigation_options, parent, false);
            viewHolder.tv = (TextView)convertView.findViewById(R.id.tv_nav_item);
            viewHolder.iv = (ImageView)convertView.findViewById(R.id.iv_nav_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(position);

        if (position == selectedItem) {
            convertView.setBackgroundColor(Color.parseColor("#EDEDED"));
        }else{
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    private class ViewHolder{
        private ImageView iv;
        private TextView tv;
        void setData(int position){
            tv.setText(mListString.get(position));
            iv.setImageResource(mImgId[position]);
        }
    }

    public void setSelectItem(int selectedItem) {
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }
}

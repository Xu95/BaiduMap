package com.example.xu.baidumap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Xu on 2016/10/17.
 */
public class Adapter extends BaseAdapter{
    private static final int TYPE1 = 0;
    private Context mContext;
    private ArrayList<Friends> mData;

    public Adapter(){}

    public Adapter(Context mContext,ArrayList<Friends> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof Friends) {
            return TYPE1;
        }
        else{return super.getItemViewType(position);}
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {return mData.size();}

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.friends_list_item, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name_cell);
            holder.delete = (Button) convertView.findViewById(R.id.delete_button_cell);
            convertView.setTag(R.id.Tag_1, holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag(R.id.Tag_1);
        }
        Object o = mData.get(position);
        final Friends a = (Friends) o;
        if (a != null) {
            holder.name.setText(a.getname());
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.remove(position);
                notifyDataSetChanged();
                Edit.num--;
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        Button delete;
    }
}

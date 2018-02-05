package com.example.dylan.alcock_subbook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dylan on 2018-02-03.
 */

public class SubscriptionListAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Subscription> subscriptionArrayList;

    public SubscriptionListAdapter(Context mContext, ArrayList<Subscription> subscriptionArrayList) {
        this.mContext = mContext;
        this.subscriptionArrayList = subscriptionArrayList;
    }


    @Override
    public int getCount() {
        return subscriptionArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return subscriptionArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.sub_list_item, null);
        TextView subName = v.findViewById(R.id.textName);
        TextView subDate = v.findViewById(R.id.textDate);
        TextView subCharge = v.findViewById(R.id.textCharge);

        subName.setText(subscriptionArrayList.get(position).getName());
        subDate.setText(subscriptionArrayList.get(position).getDate());
        subCharge.setText("$" + String.format("%.2f", subscriptionArrayList.get(position).getCharge()));

        v.setTag(subscriptionArrayList.get(position).getName());
        return v;
    }
}

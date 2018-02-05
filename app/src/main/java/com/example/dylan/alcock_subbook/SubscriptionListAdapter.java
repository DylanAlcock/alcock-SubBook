/*
 * Subscription List Adapter
 *
 * February 5, 2018
 *
 * Copyright (c) 2018 Dylan Alcock, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 *  You can find a copy of the license on this project, Otherwise please contact alcock@ualberta.ca
 */

package com.example.dylan.alcock_subbook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Purpose:
 * Changes how a tweet is represented in a list view.
 *
 * Design Rationale:
 * Comment field took up to much space in the list view so made one without it.
 *
 * @author Dylan
 * @version 1.5
 * @see Subscription
 */
public class SubscriptionListAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Subscription> subscriptionArrayList;

    /**
     * Purpose:
     *
     *
     * @param mContext
     * @param subscriptionArrayList
     */
    public SubscriptionListAdapter(Context mContext, ArrayList<Subscription> subscriptionArrayList) {
        this.mContext = mContext;
        this.subscriptionArrayList = subscriptionArrayList;
    }

    /**
     * Purpose:
     * gets the size of the subscription list
     *
     * @return int of how long the subscription list is
     */
    @Override
    public int getCount() {
        return subscriptionArrayList.size();
    }

    /**
     * Purpose:
     * Gets the position of an object in the list view
     *
     * @param position int of what position the sub is at
     * @return subscription object at specified position
     */
    @Override
    public Object getItem(int position) {
        return subscriptionArrayList.get(position);
    }

    /**
     * Purpose:
     * Gets the id of the position
     *
     * @param position int of where the object is in the list
     * @return long of where the object is
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Purpose:
     * Gets the view of subscription list and add the new layout to it.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return new view with sub list item layout of the list view
     */
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

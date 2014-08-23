package com.corridor9design.mfdtailboard.todo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.corridor9design.mfdtailboard.R;

import java.util.List;

/**
 * MFDTailboard: All in one fire department management application.
 * Created on 8/23/14.
 * <p/>
 * Copyright (C) 2014  Andrew Buskov
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class TodoListAdapter extends ArrayAdapter {

    private Context mContext;

    public TodoListAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.mContext = context;
    }

    /**
     * Holder for the list items.
     */

    private class ViewHolder {
        TextView mTitleText;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        TodoListItem mItem = (TodoListItem) getItem(position);
        View mViewToUse = null;

        // This block exists to inflate the list item conditionally based
        // on whether we want to support grid or list view.

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        mViewToUse = mInflater.inflate(R.layout.todo_list_item, null);
        if (convertView == null) {
            mHolder = new ViewHolder();
            mHolder.mTitleText = (TextView) mViewToUse.findViewById(R.id.todo_item);
            mViewToUse.setTag(mHolder);
        } else {
            mViewToUse = convertView;
            mHolder = (ViewHolder) mViewToUse.getTag();
        }

        mHolder.mTitleText.setText(mItem.getItemTitle());
        return mViewToUse;
    }
}

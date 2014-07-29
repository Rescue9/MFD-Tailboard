package com.corridor9design.mfdtailboard.todo;

import android.widget.TextView;

import com.leocardz.aelv.library.AelvListViewHolder;

/**
 * Created by rescue on 7/29/14.
 */
public class ListViewHolder extends AelvListViewHolder {
    public TextView textView;

    public ListViewHolder(TextView textView) {
        super();
        this.textView = textView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}

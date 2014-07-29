package com.corridor9design.mfdtailboard.todo;

import com.corridor9design.mfdtailboard.R;
import com.leocardz.aelv.library.AelvListItem;

/**
 * Created by rescue on 7/29/14.
 */
public class ListItem extends AelvListItem{

    private String text;
    private int drawable;

    public ListItem(String text) {
        super();
        this.text = text;
        this.drawable = R.drawable.ic_list_chevron_bottom_10dp_light;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

}

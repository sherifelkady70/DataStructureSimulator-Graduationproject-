package com.must.datastructuressimulator;
public class ActivityItem {

    public int drawable;
    public String text;
    public String activityClassName;

    public ActivityItem(String activityClassName, String text, int drawable)
    {
        this.activityClassName = activityClassName;
        this.text = text;
        this.drawable = drawable;

    }

}

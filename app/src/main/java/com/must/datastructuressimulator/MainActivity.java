package com.must.datastructuressimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    List<ActivityItem> activityItems;
    ImageView imageView;
    TextView textView;

    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHelper.hideActivityStatusBar(getWindow());
        setContentView(R.layout.activity_main);

        context = this;

        activityItems = new ArrayList<>();
        //Adding class Items to simulate
        activityItems.add(new ActivityItem(StackActivity.class.getName(), "Stack", R.drawable.ds_stack));
        activityItems.add(new ActivityItem(QueueActivity.class.getName(), "Queue", R.drawable.ds_queue));
        activityItems.add(new ActivityItem(LinkedListActivity.class.getName(), "Linked List", R.drawable.ds_queue));
        activityItems.add(new ActivityItem(TreesActivity.class.getName(), "Trees", R.drawable.ds_trees));

        linearLayout = findViewById(R.id.ll_home);

        int width = (int) UIHelper.dpToPx(context, Constants.ACTIVITY_ITEM_WIDTH);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,
                ViewGroup.LayoutParams.MATCH_PARENT);

        for (ActivityItem activityItem : activityItems)
        {
            View topView = getLayoutInflater().inflate(R.layout.activity_item, null);
            imageView = topView.findViewById(R.id.iv_item_background);
            textView = topView.findViewById(R.id.tv_item_value);

            imageView.setImageDrawable(ContextCompat.getDrawable(this, activityItem.drawable));
            textView.setText(activityItem.text);

            layoutParams.setMargins(Constants.MARGIN_SIZE_16, 0, Constants.MARGIN_SIZE_16, 0);
            topView.setLayoutParams(layoutParams);

            linearLayout.addView(topView);

            topView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Class<?> aClass = Class.forName(activityItem.activityClassName);
                        startActivity(new Intent(MainActivity.this, aClass));
                    }
                    catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });



        }


    }

}
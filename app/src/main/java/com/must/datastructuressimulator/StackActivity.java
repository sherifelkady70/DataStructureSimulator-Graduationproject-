package com.must.datastructuressimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StackActivity extends AppCompatActivity {

    Context context;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHelper.hideActivityStatusBar(getWindow());
        setContentView(R.layout.activity_main);

        context = this;
        linearLayout = findViewById(R.id.ll_home);

        List<ActivityItem> activityItems = new ArrayList<>();
        activityItems.add(new ActivityItem(ArrayBasedStackActivity.class.getName(), "Array-Based", R.drawable.ds_stack));
        activityItems.add(new ActivityItem(LinkedBasedStackActivity.class.getName(), "Linked-Based", R.drawable.ds_stack));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) UIHelper.dpToPx(this, Constants.ACTIVITY_ITEM_WIDTH),
                ViewGroup.LayoutParams.MATCH_PARENT);

        layoutParams.setMargins(Constants.MARGIN_SIZE_16, 0, Constants.MARGIN_SIZE_16, 0);


        for (ActivityItem activityItem : activityItems)
        {
            View item = getLayoutInflater().inflate(R.layout.activity_item, null);
            ImageView background = item.findViewById(R.id.iv_item_background);
            TextView title = item.findViewById(R.id.tv_item_value);
            title.setText(activityItem.text);
            background.setImageDrawable(ContextCompat.getDrawable(this, activityItem.drawable));
            item.setLayoutParams(layoutParams);
            linearLayout.addView(item);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class<?> aClass = null;
                    try {
                        aClass = Class.forName(activityItem.activityClassName);
                        startActivity(new Intent(StackActivity.this, aClass));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }catch (ActivityNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }





    }
}
package com.must.datastructuressimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class QueueActivity extends AppCompatActivity {


   /* Context context;
    TableRow tr_parent;
    TextView tv_value;
    EditText input;
    Button btn_add, btn_remove;
    int ind = 0;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHelper.hideActivityStatusBar(getWindow());
        setContentView(R.layout.activity_main);

       /* context = this;
        tr_parent = findViewById(R.id.tr_parent);
        input = findViewById(R.id.tv_value_input);
        btn_add = findViewById(R.id.btn_add);
        btn_remove = findViewById(R.id.btn_remove);

        int width = (int) dpToPx(context,250);
        TableRow.LayoutParams  layoutParams = new TableRow.LayoutParams(width);
        layoutParams.setMargins(15 ,15, 15, 15);
        tr_parent.setLayoutParams(layoutParams);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = input.getText().toString();
                        Toast toast = Toast.makeText(context, value, Toast.LENGTH_LONG);
                        toast.show();

                View node = getLayoutInflater().inflate(R.layout.layout_queue_node, null);
                tv_value = node.findViewById(R.id.tv_value);
                //tv_value.setTextSize(5);
                tv_value.setText(value);
                tr_parent.addView(node, 450, 450);



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ViewAnimator.animate(node).slideBottom().duration(1000).start();
                        System.out.println("VALUE: " + value);
                    }
                });
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tr_parent.removeViewAt(0);
                    }
                });
            }
        });



*/

        LinearLayout linearLayout = findViewById(R.id.ll_home);
        int width = (int) dpToPx(this,  Constants.ACTIVITY_ITEM_WIDTH);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,
                ViewGroup.LayoutParams.MATCH_PARENT);

        layoutParams.setMargins(15, 0,15, 0);
        List<ActivityItem> activityItems = new LinkedList<>();
        activityItems.add(new ActivityItem(ArrayBasedQueueActivity.class.getName(), "Array-Based", R.drawable.ds_queue ));
        activityItems.add(new ActivityItem(LinkedBasedQueueActivity.class.getName(), "Linked-Based", R.drawable.ds_queue ));

        for (ActivityItem activityItem : activityItems)
        {
            View topView = getLayoutInflater().inflate(R.layout.activity_item, null);
            ImageView imageView = topView.findViewById(R.id.iv_item_background);

            TextView text = topView.findViewById(R.id.tv_item_value);

            imageView.setImageDrawable(ContextCompat.getDrawable(this, activityItem.drawable));
            text.setText(activityItem.text);

            topView.setLayoutParams(layoutParams);

            linearLayout.addView(topView);

            topView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Class<?> aClass = Class.forName(activityItem.activityClassName);
                        startActivity(new Intent(QueueActivity.this, aClass));
                    }catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    catch (ActivityNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static float dpToPx(Context context, int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
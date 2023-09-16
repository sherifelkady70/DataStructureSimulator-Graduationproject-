package com.must.datastructuressimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class LinkedBasedQueueActivity extends AppCompatActivity {


    Context context;
    TableRow tr_parent;
    TextView tv_value;
    EditText input;
    ImageView arrow;
    Button btn_add, btn_remove;
    int ind = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHelper.hideActivityStatusBar(getWindow());
        setContentView(R.layout.activity_queue);


        context = this;
        tr_parent = findViewById(R.id.tr_parent);
        input = findViewById(R.id.tv_value_input);
        btn_add = findViewById(R.id.btn_add);
        btn_remove = findViewById(R.id.btn_remove);

        int width = (int) dpToPx(context,250);
        TableRow.LayoutParams  layoutParams = new TableRow.LayoutParams(width);
        layoutParams.setMargins(15 ,15, 15, 15);
        layoutParams.gravity = Gravity.CENTER;
        tr_parent.setLayoutParams(layoutParams);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = input.getText().toString();
                /*Toast toast = Toast.makeText(context, value, Toast.LENGTH_LONG);
                toast.show();*/

                View node = getLayoutInflater().inflate(R.layout.layout_queue_node, null);
                tv_value = node.findViewById(R.id.tv_value);
                //tv_value.setTextSize(5);
                tv_value.setText(value);
                arrow = new ImageView(context);
                arrow.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.node_arrow));
                tr_parent.addView(node, 450, 450);
                tr_parent.addView(arrow, 450, 450);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ViewAnimator.animate(node).slideBottom().andAnimate(arrow).slideLeft().duration(1000).start();
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


    }

    public static float dpToPx(Context context, int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
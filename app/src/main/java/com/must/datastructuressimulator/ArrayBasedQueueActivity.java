package com.must.datastructuressimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.must.datastructuressimulator.ds.QueueArrayBased;

public class ArrayBasedQueueActivity extends AppCompatActivity implements DialogInputFragment.onSubmitClickListener {

    QueueArrayBased<Integer> queue;
    DialogInputFragment dialogInput;
    StringBuilder a;

    Boolean getLocation = false;
    float step, step1;
    int MaxQueue;
    Context context;
    TableRow tr_array,
            tr_indices,

            tr_queue;



    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_minus, btn_del;
    TextView tv_queue_entry;

    View view;
    TableRow.LayoutParams layoutParams;
    TextView tv_head,
            tv_tail;

    TextView tv_operation,
            tv_value,
            tv_max_queue,
            tv_queue_size,
            tv_size,
            tv_info;
    ImageView iv_head_arrow,
            iv_tail_arrow;
    View arrayItem;
    DisplayMetrics displayMetrics;
    int windowWidth, windowHeight;

    int queueElementWidth,
            queueElementHeight;

    DrawerLayout dl_main;
    ViewStub vs_main;
    ViewStub vs_menu_right;

    EditText et_enqueue;
    Button btn_enqueue, btn_dequeue, btn_clearQueue;
    ImageButton btn_closeMenu;
    ImageButton btn_menu;
    final int LAYOUT_MAIN = R.layout.activity_queue_array_based;
    final int LAYOUT_RIGHT = R.layout.layout_queue_manipulations;

    View main_content;
    View controls;


    Toast info;

    @Override
    public void onClickedCreateButton(String size) {

        Toast toast;

        try
        {
            MaxQueue = Integer.parseInt(size);

            if(MaxQueue >= 1 && MaxQueue <= 20)
            {
                queue = new QueueArrayBased<Integer>(MaxQueue);
                String max_queue = String.format(tv_max_queue.getText().toString() + " " + MaxQueue);
                tv_max_queue.setText(max_queue);
                for (int i = 0; i < MaxQueue; ++i)
                {
                    arrayItem = getLayoutInflater().inflate(R.layout.array_item, null);
                    tr_array.addView(arrayItem, queueElementWidth, queueElementHeight);

                    TextView tv_index = new TextView(context);

                    tv_index.setText(String.valueOf(i));
                    tv_index.setTextSize(Constants.TEXT_SIZE_15);
                    tv_index.setGravity(Gravity.CENTER);
                    tv_index.setTypeface(tv_index.getTypeface(), Typeface.BOLD);
                    tv_index.setTextColor(getResources().getColor(R.color.black));

                    tr_indices.addView(tv_index, queueElementWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
                }

                float x = (float)(iv_head_arrow.getWidth() / 2);
                tv_head.setX(tv_head.getX() + (float)queueElementWidth/ 2 - x );
                iv_head_arrow.setX(iv_head_arrow.getX() + (float)queueElementWidth / 2 - x);

                tv_tail.setX(tv_tail.getX() + (float)queueElementWidth / 2 - x);
                iv_tail_arrow.setX(iv_tail_arrow.getX() + (float)queueElementWidth / 2 - x);

                dialogInput.dismiss();
            }
            else
            {
                toast = Toast.makeText(this, "Queue Size Can't Be " + MaxQueue, Toast.LENGTH_LONG);
                toast.show();
            }

        }catch (NumberFormatException e)
        {
            toast = Toast.makeText(context, "Queue Size Can't Be Empty", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHelper.hideActivityStatusBar(getWindow());
        setContentView(R.layout.layout_ds_basic);

        context = this;

        dialogInput = DialogInputFragment.newInstance("Provide Queue Size", R.drawable.ic_info);
        dialogInput.setCancelable(false);
        dialogInput.show(getSupportFragmentManager(), null);


        dl_main = findViewById(R.id.dl_main);
        vs_main = findViewById(R.id.vs_main);
        vs_menu_right = findViewById(R.id.vs_menu_right);
        vs_main.setLayoutResource(LAYOUT_MAIN);
        vs_menu_right.setLayoutResource(LAYOUT_RIGHT);

        main_content = vs_main.inflate();
        controls = vs_menu_right.inflate();

        tr_array = main_content.findViewById(R.id.array);
        tr_indices = main_content.findViewById(R.id.indices);

        tr_queue = main_content.findViewById(R.id.tr_queue);
        tr_queue.setPadding(3, 3, 3,3);
        layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(2, 2, 2, 2);
        tv_operation = main_content.findViewById(R.id.tv_operation_type);
        tv_value = main_content.findViewById(R.id.tv_value);
        tv_head = main_content.findViewById(R.id.tv_head);
        tv_tail = main_content.findViewById(R.id.tv_top);
        tv_max_queue = main_content.findViewById(R.id.tv_max_queue);
        tv_queue_size = main_content.findViewById(R.id.tv_queue_size);
        tv_size = main_content.findViewById(R.id.tv_size);
        iv_head_arrow = main_content.findViewById(R.id.iv_head_arrow);
        iv_tail_arrow = main_content.findViewById(R.id.iv_top_arrow);
        btn_menu = main_content.findViewById(R.id.btn_menu);

        et_enqueue = controls.findViewById(R.id.et_enqueue);
        btn_enqueue = controls.findViewById(R.id.btn_push);
        btn_dequeue = controls.findViewById(R.id.btn_pop);
        btn_clearQueue = controls.findViewById(R.id.btn_clear);
        btn_closeMenu = controls.findViewById(R.id.btn_closemenu);


        btn_0 = controls.findViewById(R.id.btn_0);
        btn_1 = controls.findViewById(R.id.btn_1);
        btn_2 = controls.findViewById(R.id.btn_2);
        btn_3 = controls.findViewById(R.id.btn_3);
        btn_4 = controls.findViewById(R.id.btn_4);
        btn_5 = controls.findViewById(R.id.btn_5);
        btn_6 = controls.findViewById(R.id.btn_6);
        btn_7 = controls.findViewById(R.id.btn_7);
        btn_8 = controls.findViewById(R.id.btn_8);
        btn_9 = controls.findViewById(R.id.btn_9);
        btn_minus = controls.findViewById(R.id.btn_minus);
        btn_del = controls.findViewById(R.id.btn_del);

        tv_info = main_content.findViewById(R.id.tv_info);


        displayMetrics = new DisplayMetrics();

        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        windowWidth = displayMetrics.widthPixels;
        windowHeight = displayMetrics.heightPixels;



        queueElementWidth = (int)UIHelper.dpToPx(context, Constants.ARRAY_ELEMENT_WIDTH);
        queueElementHeight = (int)UIHelper.dpToPx(context, Constants.ARRAY_ELEMENT_HEIGHT);

        tv_max_queue.setTextSize(Constants.TEXT_SIZE_20);
        String queue_size = String.format(tv_queue_size.getText().toString() + " ");
        tv_queue_size.setText(queue_size);
        tv_size.setText("0");


        tv_head.setTextSize(Constants.TEXT_SIZE_15);
        tv_tail.setTextSize(Constants.TEXT_SIZE_15);
        tv_queue_size.setTextSize(Constants.TEXT_SIZE_20);
        tv_operation.setTextSize(Constants.TEXT_SIZE_20);
        tv_value.setTextSize(Constants.TEXT_SIZE_20);
        tv_size.setTextSize(Constants.TEXT_SIZE_20);
        tv_head.setTypeface(tv_head.getTypeface(), Typeface.BOLD);
        tv_value.setText(" ");


        et_enqueue.setShowSoftInputOnFocus(false);

        final int []valueTextViewLocation = new int[2];

        //Enqueue animation operation
        btn_enqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String val = et_enqueue.getText().toString().trim();

                        int integerValue = 0;
                        step = tv_tail.getTranslationX() + queueElementWidth;

                        try {
                            integerValue = Integer.parseInt(val);
                        }catch (NumberFormatException e)
                        {
                            e.printStackTrace();
                        }

                        if(!val.isEmpty())
                        {
                            String value = String.valueOf(integerValue).substring(0, val.length() <= 3 ? val.length() : 4);
                            tv_info.setText("enqueuing: " + value);

                            tv_operation.setTextSize(Constants.TEXT_SIZE_20);
                            tv_value.setTextSize(Constants.TEXT_SIZE_20);

                            tv_operation.setText(R.string.enqueuing);
                            tv_value.setText(String.valueOf(integerValue));

                            if(!getLocation) {
                                getLocation = true;
                                tv_value.getLocationOnScreen(valueTextViewLocation);
                            }

                            try {

                                int []location = new int[2];
                                queue.enqueue(integerValue);
                                
                                int rear;
                                if(queue.getRear() == 0) {
                                    rear = MaxQueue - 1;
                                    step = tv_tail.getTranslationX() - queueElementWidth * (MaxQueue - 1);
                                }
                                else
                                    rear = queue.getRear() - 1;


                                
                                tv_queue_entry =  tr_array.getChildAt(rear).findViewById(R.id.tv_item_value);
                                tv_queue_entry.setTextSize(Constants.TEXT_SIZE_20);
                                tv_queue_entry.getLocationOnScreen(location);




                                float offset = (queueElementWidth / (float)4);

                                Path path = new Path();

                                path.moveTo(valueTextViewLocation[0], valueTextViewLocation[1]);
                                path.lineTo(location[0] - offset, location[1] - offset);
                                tv_operation.setVisibility(View.VISIBLE);
                                tv_value.setVisibility(View.VISIBLE);


                                ViewAnimator.animate(tv_value).bounceIn().duration(Constants.ANIMATION_SPEED).start();
                                ViewAnimator.animate(tv_value).path(path).duration(Constants.ANIMATION_SPEED).start();

                                int finalIntegerValue = integerValue;
                                tv_value.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_operation.setVisibility(View.INVISIBLE);
                                        tv_value.setVisibility(View.INVISIBLE);
                                        tv_queue_entry.setText(String.valueOf(finalIntegerValue));


                                        View queue_entry = getLayoutInflater().inflate(R.layout.queue_item, null);
                                        TextView queueValue = queue_entry.findViewById(R.id.tv_item_value);
                                        queueValue.setText(String.valueOf(finalIntegerValue));

                                        ViewAnimator.animate(queue_entry).slideRight().duration(Constants.ANIMATION_SPEED).
                                                start();

                                        tr_queue.addView(queue_entry, queueElementWidth, queueElementHeight);
                                        //ViewAnimator.animate(tv_queue_entry).slideTop().duration(Constants.ANIMATION_SPEED).start();
                                    }
                                }, Constants.ANIMATION_SPEED);


                                tv_tail.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        ViewAnimator.animate(tv_tail).translationX(step)
                                                .duration(Constants.ANIMATION_SPEED).start();

                                        ViewAnimator.animate(iv_tail_arrow).translationX(step)
                                                .duration(Constants.ANIMATION_SPEED).start();
                                    }
                                }, Constants.ANIMATION_SPEED);

                                tv_size.postDelayed(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                tv_size.setText(String.valueOf(queue.size()));
                                                ViewAnimator.animate(tv_size).bounce().duration(Constants.ANIMATION_SPEED).start();
                                                info = Toast.makeText(context, "Done", Toast.LENGTH_SHORT);
                                                info.show();
                                            }
                                        }, Constants.ANIMATION_SPEED * 2L
                                );



                            }catch (RuntimeException e)
                            {
                                Toast toast = Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT);
                                toast.show();
                            }

                        }
                        else
                        {
                            Toast toast = Toast.makeText(context, "Value Can't Be Empty", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }
                });

                et_enqueue.setText("");
                a = new StringBuilder();
                dl_main.closeDrawer(GravityCompat.END);


            }
        });


        //Dequeue animation operation
        btn_dequeue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                step1 = tv_head.getTranslationX() + queueElementWidth;

                try
                {

                    queue.dequeue();
                    if(queue.getFront() != 0) {
                        view = tr_array.getChildAt(queue.getFront() - 1);
                    }
                    else {
                        view = tr_array.getChildAt(MaxQueue - 1);
                        step1 = tv_head.getTranslationX() - queueElementWidth * (MaxQueue - 1);
                    }
                    tv_queue_entry = view.findViewById(R.id.tv_item_value);

                    tv_info.setText("Dequeued value: " + tv_queue_entry.getText().toString());

                    tv_operation.setVisibility(View.VISIBLE);
                    tv_value.setVisibility(View.VISIBLE);


                    tv_operation.setText(R.string.dequeue);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_value.setX(valueTextViewLocation[0]);
                            tv_value.setY(valueTextViewLocation[1]);
                            tv_value.setText(tv_queue_entry.getText().toString());
                            Path path = new Path();
                            int []a = new int[2];
                            tv_queue_entry.getLocationOnScreen(a);

                            tv_queue_entry.setText("");

                            //path.lineTo(startPoint[0], startPoint[1]);
                            //ViewAnimator.animate(tv_queue_entry).path(path).duration(Constants.ANIMATION_SPEED).start();

                            tv_head.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    ViewAnimator.animate(tv_head).translationX(step1).
                                            duration(Constants.ANIMATION_SPEED).start();
                                    ViewAnimator.animate(iv_head_arrow).translationX(step1).
                                            duration(Constants.ANIMATION_SPEED).start();




                                    ViewAnimator.animate(tr_queue.getChildAt(0)).fadeIn().duration(Constants.ANIMATION_SPEED)
                                            .start();



                                    final Handler handler = new Handler();
                                    int delay = 0;

                                    if(queue.size() > 2) {
                                        for (int i = 0; i < queue.size() - 1; ++i) {
                                            View rootView1 = tr_queue.getChildAt(i);
                                            View rootView2 = tr_queue.getChildAt(i + 1);
                                            TextView v1 = rootView1.findViewById(R.id.tv_item_value);
                                            TextView v2 = rootView2.findViewById(R.id.tv_item_value);
                                            delay += Constants.ANIMATION_SPEED / queue.size();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    rootView1.setVisibility(View.INVISIBLE);
                                                    v1.setText(v2.getText());
                                                    rootView2.setVisibility(View.INVISIBLE);
                                                    rootView1.setVisibility(View.VISIBLE);
                                                }
                                            }, delay);

                                        }

                                        tr_queue.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                tr_queue.getChildAt(queue.size() - 2).setVisibility(View.VISIBLE);
                                                tr_queue.removeViewAt(queue.size() - 1);
                                            }
                                        }, delay + Constants.ANIMATION_SPEED / queue.size());

                                    }
                                    else
                                    {
                                        tr_queue.removeViewAt(0);
                                    }




                                    tv_operation.setVisibility(View.INVISIBLE);
                                    tv_value.setVisibility(View.INVISIBLE);


                                }
                            }, Constants.ANIMATION_SPEED);


                            tv_size.postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            tv_size.setText(String.valueOf(queue.size()));
                                            ViewAnimator.animate(tv_size).bounce().duration(Constants.ANIMATION_SPEED).start();
                                            info = Toast.makeText(context, "Done", Toast.LENGTH_SHORT);
                                            info.show();
                                        }
                                    }, Constants.ANIMATION_SPEED * 2L
                            );
                        }
                    });

                }catch (RuntimeException e)
                {
                    Toast toast = Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }

                dl_main.closeDrawer(GravityCompat.END);
            }

        });


        btn_clearQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = tr_array.getChildAt(0).getX();
                float xHeadPosition = tr_array.getChildAt(queue.getFront()).getX();

                float xArrowPosition = tr_array.getChildAt(queue.getRear()).getX();

                float headDistance = xHeadPosition - x;
                float arrowDistance = xArrowPosition - x;

                ViewAnimator.animate(tv_head).translationX(tv_head.getTranslationX() - headDistance).andAnimate(iv_head_arrow)
                        .translationX(tv_head.getTranslationX() - headDistance).duration(Constants.ANIMATION_SPEED).start();
                ViewAnimator.animate(tv_tail).translationX(tv_tail.getTranslationX() - arrowDistance).andAnimate(iv_tail_arrow)
                        .translationX(tv_tail.getTranslationX() - arrowDistance).duration(Constants.ANIMATION_SPEED).start();

                queue.clear();
                tr_queue.removeAllViews();

                tv_size.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv_size.setText("0");
                        ViewAnimator.animate(tv_size).bounce().duration(Constants.ANIMATION_SPEED).start();
                    }
                }, Constants.ANIMATION_SPEED);
                dl_main.closeDrawer(GravityCompat.END);
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_main.openDrawer(GravityCompat.END);
            }
        });

        btn_closeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_main.closeDrawer(GravityCompat.END);
            }
        });

        a = new StringBuilder();
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("0");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.length() < 4)
                {
                    a.append("1");
                    et_enqueue.setText(a.toString());
                    et_enqueue.setSelection(a.toString().length());
                }

            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("2");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("3");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("4");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("5");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("6");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("7");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("8");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("9");
                et_enqueue.setText(a.toString());
                et_enqueue.setSelection(a.toString().length());

            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.length() == 0) {
                    a.append("-");
                    et_enqueue.setText(a.toString());
                    et_enqueue.setSelection(a.toString().length());
                }
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(a.length() != 0) {
                    a.deleteCharAt(a.length() - 1);
                    et_enqueue.setText(a.toString());
                    et_enqueue.setSelection(a.toString().length());
                }

            }
        });

    }
}
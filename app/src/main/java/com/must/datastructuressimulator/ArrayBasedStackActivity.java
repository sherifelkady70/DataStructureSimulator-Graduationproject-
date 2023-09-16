package com.must.datastructuressimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.gridlayout.widget.GridLayout;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.must.datastructuressimulator.ds.ArrayBasedStack;

public class ArrayBasedStackActivity extends AppCompatActivity implements DialogInputFragment.onSubmitClickListener {

    Context context;

    DialogInputFragment dialogInputFragment;

    int MaxStack;
    DrawerLayout dl_main;
    View main_content;
    View controls;

    ViewStub vs_content;
    ViewStub vs_right;

    ArrayBasedStack<Integer> stack;

    View arrayItem;
    TextView tv_max_stack,
             tv_stack_size,
             tv_size,
             tv_operation_type,
             tv_value,
             tv_top,
             tv_info;


    ScrollView stackUserViewScrollView;
    ImageView iv_top_arrow;
    TableRow tr_array,
             tr_indices;

    EditText et_push;

    Button btn_push,
           btn_pop,
           btn_clear,
           btn_peek;

    ImageButton btn_menu,
                btn_closeMenu;


    int stackElementWidth,
        stackElementHeight;

    DisplayMetrics displayMetrics;
    int windowWidth,
        windowHeight;
    float step;

    boolean getLocation = false;

    int[] valueTextViewLocation;

    TextView tv_stack_entry;

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_minus, btn_del;

    Toast info;
    StringBuilder a;

    LinearLayout userView_Stack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHelper.hideActivityStatusBar(getWindow());
        setContentView(R.layout.layout_ds_basic);

        context = this;
        
        dialogInputFragment = DialogInputFragment.newInstance("Provide Stack Size", R.drawable.ic_info);
        dialogInputFragment.setCancelable(false);
        dialogInputFragment.show(getSupportFragmentManager(), null);
        dl_main = findViewById(R.id.dl_main);
        vs_content = findViewById(R.id.vs_main);
        vs_right = findViewById(R.id.vs_menu_right);

        vs_content.setLayoutResource(R.layout.activity_array_based_stack);
        vs_right.setLayoutResource(R.layout.layout_stack_manipulations);

        main_content = vs_content.inflate();
        controls = vs_right.inflate();

        tv_max_stack = main_content.findViewById(R.id.tv_max_queue);

        tv_stack_size = main_content.findViewById(R.id.tv_queue_size);
        tv_size = main_content.findViewById(R.id.tv_size);

        tv_operation_type = main_content.findViewById(R.id.tv_operation_type);
        tv_value = main_content.findViewById(R.id.tv_value);

        tv_top = main_content.findViewById(R.id.tv_top);
        iv_top_arrow = main_content.findViewById(R.id.iv_top_arrow);

        tr_array = main_content.findViewById(R.id.array);
        tr_indices = main_content.findViewById(R.id.indices);

        btn_menu = main_content.findViewById(R.id.btn_menu);

        userView_Stack = main_content.findViewById(R.id.ll_userViewStack);

        et_push = controls.findViewById(R.id.et_push);
        btn_push = controls.findViewById(R.id.btn_push);
        btn_pop = controls.findViewById(R.id.btn_pop);
        btn_peek = controls.findViewById(R.id.btn_peek);
        btn_clear = controls.findViewById(R.id.btn_clear);

        btn_closeMenu = controls.findViewById(R.id.btn_closemenu);

        tv_info = main_content.findViewById(R.id.tv_info);

        stackUserViewScrollView = main_content.findViewById(R.id.ScrollView_StackUserView);

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
        
        
        

        int aa = (int) UIHelper.dpToPx(context, 75);
        int bb = (int) UIHelper.dpToPx(context, 55);

        stackElementWidth = (int) UIHelper.dpToPx(context, Constants.ARRAY_ELEMENT_WIDTH);
        stackElementHeight = (int) UIHelper.dpToPx(context, Constants.ARRAY_ELEMENT_HEIGHT);

        tv_max_stack.setTextSize(Constants.TEXT_SIZE_20);
        tv_size.setTextSize(Constants.TEXT_SIZE_20);
        tv_operation_type.setTextSize(Constants.TEXT_SIZE_20);
        tv_value.setTextSize(Constants.TEXT_SIZE_20);
        tv_stack_size.setTextSize(Constants.TEXT_SIZE_20);

        tv_top.setTextSize(Constants.TEXT_SIZE_15);

        displayMetrics = new DisplayMetrics();

        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        windowWidth = displayMetrics.widthPixels;
        windowHeight = displayMetrics.heightPixels;



        stackElementWidth = (int)UIHelper.dpToPx(context, Constants.ARRAY_ELEMENT_WIDTH);
        stackElementHeight = (int)UIHelper.dpToPx(context, Constants.ARRAY_ELEMENT_HEIGHT);

        final int []valueTextViewLocation = new int[2];

        et_push.setShowSoftInputOnFocus(false);

        btn_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String val = et_push.getText().toString().trim();
                String value = val.substring(0, val.length() <= 3 ? val.length() : 4);
                step = tv_top.getTranslationX() + stackElementWidth;

                int integerValue = 0;
                try {
                    integerValue = Integer.parseInt(value);
                }catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }

                if(!value.isEmpty())
                {
                    tv_info.setText("Pushing: " + value);

                    tv_operation_type.setTextSize(Constants.TEXT_SIZE_20);
                    tv_value.setTextSize(Constants.TEXT_SIZE_20);

                    tv_operation_type.setText("Pushing: ");
                    tv_value.setText(value);


                    if(!getLocation) {
                        getLocation = true;

                        tv_value.getLocationOnScreen(valueTextViewLocation);
                    }

                    try {

                        int []location = new int[2];
                        stack.push(integerValue);
                        tv_stack_entry = tr_array.getChildAt(stack.size() - 1).findViewById(R.id.tv_item_value);
                        tv_stack_entry.setTextSize(Constants.TEXT_SIZE_20);
                        tv_stack_entry.getLocationOnScreen(location);

                        float offset = (stackElementWidth / (float)4);

                        Path path = new Path();

                        path.moveTo(valueTextViewLocation[0], valueTextViewLocation[1]);
                        path.lineTo(location[0] - offset, location[1] - offset);
                        tv_operation_type.setVisibility(View.VISIBLE);
                        tv_value.setVisibility(View.VISIBLE);


                        ViewAnimator.animate(tv_value).bounceIn().duration(Constants.ANIMATION_SPEED).start();
                        ViewAnimator.animate(tv_value).path(path).duration(Constants.ANIMATION_SPEED).start();


                        View stackItem = getLayoutInflater().inflate(R.layout.stack_item, null);
                        stackItem.setPadding(5,5,5,5);

                        TextView vt = stackItem.findViewById(R.id.tv_item_value);
                        vt.setText(value);
                        vt.setTypeface(Typeface.DEFAULT_BOLD);
                        vt.setTextSize(20);


                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT);

                        layoutParams.width = aa;
                        layoutParams.height = bb;
                        layoutParams.setMargins(0,1,0,1);

                        ViewAnimator.animate(stackItem).slideTop().duration(Constants.ANIMATION_SPEED).start();

                        userView_Stack.addView(stackItem, MaxStack - stack.size(), layoutParams);
                        stackUserViewScrollView.fullScroll(ScrollView.FOCUS_UP);

                        tv_value.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tv_operation_type.setVisibility(View.INVISIBLE);
                                tv_value.setVisibility(View.INVISIBLE);
                                tv_stack_entry.setText(value);


                                View stack_entry = getLayoutInflater().inflate(R.layout.queue_item, null);
                                TextView vvv = stack_entry.findViewById(R.id.tv_item_value);
                                vvv.setText(value);

                                ViewAnimator.animate(stack_entry).slideRight().duration(Constants.ANIMATION_SPEED).
                                        start();

                                //ViewAnimator.animate(tv_queue_entry).slideTop().duration(Constants.ANIMATION_SPEED).start();
                            }
                        }, Constants.ANIMATION_SPEED);


                        tv_top.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                ViewAnimator.animate(tv_top).translationX(step)
                                        .duration(Constants.ANIMATION_SPEED).start();

                                ViewAnimator.animate(iv_top_arrow).translationX(step)
                                        .duration(Constants.ANIMATION_SPEED).start();
                            }
                        }, Constants.ANIMATION_SPEED);

                        tv_size.postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_size.setText(String.valueOf(stack.size()));
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

                    a = new StringBuilder();
                    et_push.setText("");

                }
                else
                {
                    Toast toast = Toast.makeText(context, "Value Can't Be Empty", Toast.LENGTH_SHORT);
                    toast.show();
                }

                dl_main.closeDrawer(GravityCompat.END);
            }
        });

        btn_closeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_main.closeDrawer(GravityCompat.END);
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_main.openDrawer(GravityCompat.END);
            }
        });



        a = new StringBuilder();
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("0");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("1");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("2");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("3");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("4");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("5");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("6");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("7");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("8");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.append("9");
                et_push.setText(a.toString());
                et_push.setSelection(a.toString().length());

            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.length() == 0) {
                    a.append("-");
                    et_push.setText(a.toString());
                    et_push.setSelection(a.toString().length());
                }
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(a.length() != 0) {
                    a.deleteCharAt(a.length() - 1);
                    et_push.setText(a.toString());
                    et_push.setSelection(a.toString().length());
                }

            }
        });
        
    }


    @Override
    public void onClickedCreateButton(String size) {

        Toast toast;

        try
        {
            MaxStack = Integer.parseInt(size);

            if(MaxStack >= 1 && MaxStack <= 20)
            {
                stack = new ArrayBasedStack<>(MaxStack);
                String max_queue = String.format(tv_max_stack.getText().toString() + " " + MaxStack);
                tv_max_stack.setText(max_queue);


                for (int i = 0; i < MaxStack + 1; ++i)
                {
                    arrayItem = getLayoutInflater().inflate(R.layout.array_item, null);
                    tr_array.addView(arrayItem, stackElementWidth, stackElementHeight);

                    userView_Stack.addView(new TextView(context), i);

                    TextView tv_index = new TextView(context);

                    tv_index.setText(String.valueOf(i));
                    tv_index.setTextSize(Constants.TEXT_SIZE_15);
                    tv_index.setGravity(Gravity.CENTER);
                    tv_index.setTypeface(tv_index.getTypeface(), Typeface.BOLD);
                    tv_index.setTextColor(getResources().getColor(R.color.black));

                    tr_indices.addView(tv_index, stackElementWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                tr_array.getChildAt(MaxStack).setVisibility(View.INVISIBLE);
                tr_indices.getChildAt(MaxStack).setVisibility(View.INVISIBLE);


                float x = (float)(iv_top_arrow.getWidth() / 2);
                tv_top.setX(tv_top.getX() + (float)stackElementWidth/ 2 - x );
                iv_top_arrow.setX(iv_top_arrow.getX() + (float)stackElementWidth / 2 - x);

                dialogInputFragment.dismiss();
            }
            else
            {
                toast = Toast.makeText(this, "Stack Size Can't Be " + MaxStack, Toast.LENGTH_LONG);
                toast.show();
            }

        }catch (NumberFormatException e)
        {
            toast = Toast.makeText(context, "Stack Size Can't Be Empty", Toast.LENGTH_SHORT);
            toast.show();
        }



    }
}
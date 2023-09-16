package com.must.datastructuressimulator;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogInputFragment extends androidx.fragment.app.DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "title";
    //private static final String ARG_MESSAGE = "message";
    private static final String ARG_ICON = "icon";

    private onSubmitClickListener submitClickListener;





    // TODO: Rename and change types of parameters
    private String message,
                   title;

    private int icon;

    public DialogInputFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DialogInputFragment newInstance(String title, int icon) {
        DialogInputFragment fragment = new DialogInputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        //args.putString(ARG_MESSAGE, message);
        args.putInt(ARG_ICON, icon);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            //message = getArguments().getString(ARG_MESSAGE);
            icon = getArguments().getInt(ARG_ICON);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof onSubmitClickListener)
        {
            submitClickListener = (onSubmitClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        submitClickListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.layout_dialog_input, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        ImageView iv_icon = view.findViewById(R.id.icon);
        iv_icon.setImageResource(icon);
        EditText et_size = view.findViewById(R.id.et_size);
        Button btn_create = view.findViewById(R.id.btn_create);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitClickListener.onClickedCreateButton(et_size.getText().toString());
            }
        });
    }

    public interface onSubmitClickListener
    {
        void onClickedCreateButton(String size);
    }
}
package com.must.datastructuressimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LinkedListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHelper.hideActivityStatusBar(getWindow());
        setContentView(R.layout.activity_linked_list);
    }
}
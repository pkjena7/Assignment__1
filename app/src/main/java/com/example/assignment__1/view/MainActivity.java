package com.example.assignment__1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.assignment__1.R;
import com.example.assignment__1.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
package com.example.tutorialmvvmapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.tutorialmvvmapp1.ViewModel.MainViewModel;
import com.example.tutorialmvvmapp1.databinding.ActivityMainBinding;
import com.example.tutorialmvvmapp1.model.Category;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private ArrayList<Category> categoryArrayList;
    private ActivityMainBinding activityMainBinding;
    private MainClickHandler mainClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainClickHandler = new MainClickHandler();
        activityMainBinding.setClickHandlers(mainClickHandler);

    }

    public class MainClickHandler{
        public  void onFABClicked(View view){
            Toast.makeText(getApplicationContext(), "FAB CLICKED", Toast.LENGTH_SHORT).show();
        }
    }
}
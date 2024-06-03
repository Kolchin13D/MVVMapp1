package com.example.tutorialmvvmapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.tutorialmvvmapp1.ViewModel.MainViewModel;
import com.example.tutorialmvvmapp1.databinding.ActivityMainBinding;
import com.example.tutorialmvvmapp1.model.Category;
import com.example.tutorialmvvmapp1.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private ArrayList<Category> categoryArrayList;
    private ActivityMainBinding activityMainBinding;
    private MainClickHandler mainClickHandler;
    private Category selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainClickHandler = new MainClickHandler();
        activityMainBinding.setClickHandlers(mainClickHandler);

        mainViewModel.getAllCategoies().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryArrayList = (ArrayList<Category>) categories;

                        for(Category c:categories){

                            Log.i("TAG", c.getCategoryName());
                        }
                        showOnSpinner();
            }
        });
    }

    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                categoryArrayList
        );
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    public class MainClickHandler {
        public void onFABClicked(View view) {
            Toast.makeText(getApplicationContext(), "FAB CLICKED", Toast.LENGTH_SHORT).show();
        }

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id){
            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String message = "id is: " + selectedCategory.getId() +
                    " \n name is " + selectedCategory.getCategoryName();

            Toast.makeText(parent.getContext(), " "+message, Toast.LENGTH_SHORT).show();

        }
    }
}
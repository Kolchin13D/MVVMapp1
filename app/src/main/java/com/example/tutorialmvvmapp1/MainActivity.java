package com.example.tutorialmvvmapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tutorialmvvmapp1.ViewModel.MainViewModel;
import com.example.tutorialmvvmapp1.databinding.ActivityMainBinding;
import com.example.tutorialmvvmapp1.model.Category;
import com.example.tutorialmvvmapp1.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_COURSE_REQUEST_CODE = 1;
    private static final int EDIT_COURSE_REQUEST_CODE = 2;
    private MainViewModel mainViewModel;
    private ArrayList<Category> categoryArrayList;
    private ActivityMainBinding activityMainBinding;
    private MainClickHandler mainClickHandler;
    private Category selectedCategory;

    //  recyclerview
    private RecyclerView courseRecyclerView;
    private  CourseAdapter courseAdapter;
    private ArrayList<Course> coursesList;
    private int selectedCourseID;

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

    public void LoadCoursesArrayList(int categoryID){
        mainViewModel.getCourseLiveData(categoryID).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                coursesList = (ArrayList<Course>) courses;
                LoadRecyclerView();
            }
        });
    }

    private void LoadRecyclerView() {
        courseRecyclerView = activityMainBinding.secondaryLayout.recycler;
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseRecyclerView.setHasFixedSize(true);
        courseAdapter = new CourseAdapter();
        courseRecyclerView.setAdapter(courseAdapter);

        courseAdapter.setCourses(coursesList);

        //  edit course
        courseAdapter.setListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                selectedCourseID = course.getCourseID();

                Intent intent = new Intent(MainActivity.this, AddAndEdit.class);
                intent.putExtra(AddAndEdit.COURSE_ID, selectedCourseID);
                intent.putExtra(AddAndEdit.COURSE_NAME, course.getCourseName());
                intent.putExtra(AddAndEdit.COURsE_PRICE, course.getCoursePrice());

                startActivityForResult(intent, EDIT_COURSE_REQUEST_CODE);

            }
        });

        //  delete  course
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Course courseToDelete = coursesList.get(viewHolder.getAdapterPosition());
                mainViewModel.deleteCourse(courseToDelete);
            }
        }).attachToRecyclerView(courseRecyclerView);

    }

    public class MainClickHandler {
        public void onFABClicked(View view) {

            //  cteate course
            Intent intent = new Intent(MainActivity.this, AddAndEdit.class);
            startActivityForResult(intent,  ADD_COURSE_REQUEST_CODE);

        }

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id){
            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String message = "id is: " + selectedCategory.getId() +
                    " \n name is " + selectedCategory.getCategoryName();

            Toast.makeText(parent.getContext(), " "+message, Toast.LENGTH_SHORT).show();

            LoadCoursesArrayList(selectedCategory.getId());
        }
    }
}
package com.example.tutorialmvvmapp1.ViewModel;

import android.app.Application;
import android.util.AndroidException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tutorialmvvmapp1.model.Category;
import com.example.tutorialmvvmapp1.model.Course;
import com.example.tutorialmvvmapp1.model.CourseRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    // repository
    private CourseRepository  repository;

    // live data
    private LiveData<List<Category>> categoryLiveData;
    private LiveData<List<Course>> courseLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new CourseRepository(application);
    }

    public LiveData<List<Category>> getAllCategoies(){
        categoryLiveData = repository.getCategories();
        return categoryLiveData;
    }

    public LiveData<List<Course>> getCourseLiveData(int categoryID){
        courseLiveData = repository.getCourses(categoryID);
        return  courseLiveData;
    }

    public void addNewCourse(Course course){
        repository.insertCourse(course);
    }

    public void deleteCourse(Course course){
        repository.deleteCourse(course);
    }

    public void updateCourse(Course course){
        repository.updateCourse(course);
    }
}

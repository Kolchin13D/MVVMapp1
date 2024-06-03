package com.example.tutorialmvvmapp1.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {

    private CategoryDAO categoryDAO;
    private CourseDAO courseDAO;
    private LiveData<List<Category>> categories;
    private LiveData<List<Course>> courses;

    public CourseRepository(Application application) {

        CourseDB courseDB = CourseDB.getInstance(application);
        categoryDAO = courseDB.categoryDAO();
        courseDAO = courseDB.courseDAO();

    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Course>> getCourses(int category_id) {
        return courseDAO.getCourses(category_id);
    }

    private void insertCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                categoryDAO.insert(category);
            }
        });

    }

    public void insertCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                courseDAO.insert(course);

                // Do after background execution is done - post execution
            }
        });

    }

    public void deleteCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                categoryDAO.delete(category);

                // Do after background execution is done - post execution
            }
        });
    }

    public void deleteCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                courseDAO.delete(course);

                // Do after background execution is done - post execution
            }
        });
    }

    public void updateCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                categoryDAO.update(category);

                // Do after background execution is done - post execution
            }
        });
    }

    public void updateCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                courseDAO.update(course);

                // Do after background execution is done - post execution
            }
        });
    }
}

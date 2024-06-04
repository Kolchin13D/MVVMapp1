package com.example.tutorialmvvmapp1.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Course.class}, version = 1)
public abstract class CourseDB extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();
    public abstract CourseDAO courseDAO();

    private static CourseDB instance;

    public static synchronized CourseDB getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CourseDB.class, "courses_DB")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    // callback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //  insert data when DB create
            InitializeData();
        }
    };

    private static void InitializeData() {

        CourseDAO courseDAO = instance.courseDAO();
        CategoryDAO categoryDAO = instance.categoryDAO();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                //  categories
                Category category1 = new Category();
                category1.setCategoryName("Front end");
                category1.setCategoryDesc("Web development inteface");

                Category category2 = new Category();
                category2.setCategoryName("Back end");
                category2.setCategoryDesc("Web development database");

                categoryDAO.insert(category1);
                categoryDAO.insert(category2);

                //  courses
                Course course1 = new Course();
                course1.setCourseName("HTML");
                course1.setCoursePrice("100$");
                course1.setCourseID(1);

                Course course2 = new Course();
                course2.setCourseName("CSS");
                course2.setCoursePrice("70$");
                course2.setCourseID(1);

                Course course3 = new Course();
                course3.setCourseName("PHP");
                course3.setCoursePrice("150$");
                course3.setCourseID(2);

                Course course4 = new Course();
                course4.setCourseName("JAVA");
                course4.setCoursePrice("170$");
                course4.setCourseID(2);

                courseDAO.insert(course1);
                courseDAO.insert(course2);
                courseDAO.insert(course3);
                courseDAO.insert(course4);

            }
        });
    }
}

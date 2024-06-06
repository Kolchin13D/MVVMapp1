package com.example.tutorialmvvmapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.tutorialmvvmapp1.databinding.ActivityAddAndEditBinding;
import com.example.tutorialmvvmapp1.databinding.ActivityMainBinding;
import com.example.tutorialmvvmapp1.model.Course;

public class AddAndEdit extends AppCompatActivity {

    private Course course;
    public static final String COURSE_ID = "courseID";
    public static final String COURSE_NAME = "courseName";
    public static final String COURsE_PRICE = "coursePrice";
    private ActivityAddAndEditBinding activityAddAndEditBinding;
    private AddAndEditClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_and_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        course = new Course();
        activityAddAndEditBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_and_edit);
        activityAddAndEditBinding.setCourse(course);

        clickHandler = new AddAndEditClickHandler(this);
        activityAddAndEditBinding.setClickHandler(clickHandler);

        Intent intent = getIntent();
        if (intent.hasExtra(COURSE_ID)){

            // recycler clicked
            setTitle("Edit course");
            course.setCourseName(intent.getStringExtra(COURSE_NAME));
            course.setCoursePrice(intent.getStringExtra(COURsE_PRICE));

        }else {

            //  fab clicked
            setTitle("Create new course");
        }

    }

    public class AddAndEditClickHandler{
        Context context;

        public AddAndEditClickHandler(Context context) {
            this.context = context;
        }

        public void onSubmit(View view){
            if(course.getCourseName() == null) {
                Toast.makeText(context, "Name is empty", Toast.LENGTH_SHORT).show();
            } else if (course.getCoursePrice() == null) {
                Toast.makeText(context, "Price is empty", Toast.LENGTH_SHORT).show();
            } else{
                Intent intent = new Intent();
                intent.putExtra(COURSE_NAME, course.getCourseName());
                intent.putExtra(COURsE_PRICE, course.getCoursePrice());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
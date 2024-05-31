package com.example.tutorialmvvmapp1.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.tutorialmvvmapp1.BR;

@Entity(tableName = "course_table", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id", childColumns = "category_id", onDelete = CASCADE))
public class Course extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "column_id")
    private  int courseID;

    @ColumnInfo(name = "course_name")
    private String courseName;

    @ColumnInfo(name = "course_price")
    private String coursePrice;

    @ColumnInfo(name = "category_id")
    private int categoryID;

    public Course(int courseID, String courseName, String coursePrice, int categoryID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.categoryID = categoryID;
    }

    @Bindable
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
        notifyPropertyChanged(BR.courseID);
    }

    @Bindable
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
        notifyPropertyChanged(BR.courseName);
    }

    @Bindable
    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
        notifyPropertyChanged(BR.coursePrice);
    }

    @Bindable
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
        notifyPropertyChanged(BR.categoryID);
    }
    @Ignore
    public Course() {
    }
}

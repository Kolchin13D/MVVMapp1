package com.example.tutorialmvvmapp1.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.tutorialmvvmapp1.BR;

@Entity(tableName =  "categories_table")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "category_name")
    private String categoryName;

    @ColumnInfo(name = "category_desc")
    private String categoryDesc;

    @Ignore
    public Category() {
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(BR.categoryName);
    }

    @Bindable
    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
        notifyPropertyChanged(BR.categoryDesc);
    }

    public Category(int id, String categoryName, String categoryDesc) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }
}

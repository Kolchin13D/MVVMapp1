package com.example.tutorialmvvmapp1;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.tutorialmvvmapp1.model.Course;

import java.util.ArrayList;

public class CourseDiffCallback extends DiffUtil.Callback {

    ArrayList<Course> OldCourseArrayList;
    ArrayList<Course> NewCourseArrayList;

    public CourseDiffCallback(ArrayList<Course> oldCourseArrayList, ArrayList<Course> newCourseArrayList) {
        OldCourseArrayList = oldCourseArrayList;
        NewCourseArrayList = newCourseArrayList;
    }

    @Override
    public int getOldListSize() {
        return OldCourseArrayList == null ? 0:OldCourseArrayList.size();
    }

    @Override
    public int getNewListSize() {
        return NewCourseArrayList == null ? 0:NewCourseArrayList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return OldCourseArrayList.get(oldItemPosition).getCourseID() ==
                NewCourseArrayList.get(newItemPosition).getCourseID();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return OldCourseArrayList.get(oldItemPosition).equals(NewCourseArrayList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}

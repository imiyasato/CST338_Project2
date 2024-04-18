package com.liaichi.gatracker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.database.entities.Course;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.databinding.ActivityCreateCourseBinding;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
class CreateCourseActivityTest {
    private CreateCourseActivity activity;
    private GATRepository repository;
    private ActivityCreateCourseBinding mockBinding;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
       activity = new CreateCourseActivity();
       repository = mock(GATRepository.class);
       activity.repository = repository;

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    public void createCourse() {
        LiveData<User> mockLiveData = mock(LiveData.class);
        User mockUser = new User("teacher3", "teacher3");

        when(repository.getUserByUserName("teacher3")).thenReturn(mockLiveData);

        activity.insertNewCourse();

        mockLiveData.observeForever(new Observer<User>() {
            @Override
            public void onChanged(User user) {
                assertEquals("CST 338", activity.getCourseName());
                assertEquals("teacher3", activity.getCourseManager());
            }
        });
    }
}
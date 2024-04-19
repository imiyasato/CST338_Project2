package com.liaichi.gatracker;

import static org.junit.Assert.assertEquals;

import com.liaichi.gatracker.database.entities.Course;

import org.junit.Before;
import org.junit.Test;

/**
 * Kelia Smith's Grade Assignment Tracker tests
 */
public class CourseTest {
  private Course course;

  @Before
  public void setUp() {
    course = new Course("338", 2);
  }

  @Test
  public void setCourse() {
    course.setCourseName("300");
    course.setManagerId(4);
    course.setCourseId(1);

    assertEquals("300", course.getCourseName());
    assertEquals(4, course.getManagerId());
    assertEquals(1, course.getCourseId());
  }

  @Test
  public void setCourseName() {
    course.setCourseName("300");
    assertEquals("300", course.getCourseName());
  }

  @Test
  public void setManagerId() {
    course.setManagerId(6);
    assertEquals(6, course.getManagerId());
  }
  @Test
  public void getManagerId() {
    assertEquals(2, course.getManagerId());
  }


}
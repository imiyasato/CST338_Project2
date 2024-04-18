package com.liaichi.gatracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.liaichi.gatracker.database.entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class IchiroTests {
  User testUser;
  String testUsername;
  String testPassword;

  @Before
  public void setUp() {
    System.out.println("Setting up User");
    testUsername = "toriyama";
    testPassword = "dragonball";
    System.out.println("Setting name to " + testUsername + " and password to " + testPassword);
    testUser = new User(testUsername,testPassword);
  }

  @After
  public void tearDown() {
    System.out.println("Tearing down User");
    testUser = null;
    testUsername = null;
    testPassword = null;
  }


  @Test
  public void getUsername() {
    assertNotEquals("fujimoto", testUser.getUsername());
    assertEquals("toriyama", testUser.getUsername());
  }

  @Test
  public void setUsername() {
    System.out.println("Changing username to himekawa");
    testUser.setUsername("himekawa");
    assertNotEquals("toriyama",testUser.getUsername());
    assertEquals("himekawa",testUser.getUsername());
  }

}
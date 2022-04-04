package src.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;
import src.*;
public class AskerTest {
    //both askForStudetID and getStudent Id is tested 
    @Test
    public void TestStudent()
    {
        //Create a list of student
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("S101312", "Cercy Lannister"));
        studentList.add(new Student("S102732", "Harry Potter", "8/28/2001"));
        studentList.add(new Student("S103821", "Jon Snow"));
        studentList.add(new Student("S102192", "Tyrion Lannister", "6/5/2000"));
        studentList.add(new Student("S111322", "Eddard Stark"));
        //innput to scanner
        ByteArrayInputStream inputStream = new ByteArrayInputStream("S101312\nS102192\nS111322".getBytes());
        Scanner s=new Scanner(inputStream);
        //assert
        assertEquals("S101312", Asker.askForStudentID(s, studentList).getId());
        assertEquals("S102192", Asker.askForStudentID(s, studentList).getId());
        //input to scanner
        //assert false when it give a different student id
        assertFalse("Case incorrect student id is give",Asker.askForStudentID(s, studentList).getId()=="S103821");
        s.close();
    }

    @Test
    public void TestCourse()
    {
        //Create Course List
        ArrayList<Course> courseList = new ArrayList<Course>();
        courseList.add(new Course("D001", "Adapted Thaumatergy"));
        courseList.add(new Course("D002", "Celestial Witchery", 22));
        courseList.add(new Course("D003", "Black Herbs", 33));
        courseList.add(new Course("D004", "Demonic Animation"));
        courseList.add(new Course("D005", "Rudimentary Necromancy", 22));
         //innput to scanner
         ByteArrayInputStream inputStream = new ByteArrayInputStream("D000\nD001\nD002".getBytes());
         Scanner s=new Scanner(inputStream);
         assertEquals("D001", Asker.askForCourseID(s, courseList).getId());//D000 nont exit-> jump to D001
         assertEquals("D002", Asker.askForCourseID(s, courseList).getId());
         s.close();
    }
    @Test
    public void TestSem()
    {
        // Create Semester
        ArrayList<String> semList = new ArrayList<String>();
        semList.add("282AoC");// The Age of chaos
        semList.add("392SA");// The Sorcery Ages
        semList.add("123EoT");// The Era of Trust
        semList.add("82AoT");// The Aeon of Titans
        semList.add("99EoD");// The Era of Darkness
        semList.add("281AoC");// The Age of chaos
        //innput to scanner
        ByteArrayInputStream inputStream = new ByteArrayInputStream("281AoC\n2022\n99EoD\n82AoT".getBytes());
        Scanner s=new Scanner(inputStream);
        assertEquals("281AoC", Asker.askForSemesterID(s, semList));
        assertEquals("99EoD", Asker.askForSemesterID(s, semList));//2022 does not exist in the list so it move to 99EoD
        assertEquals("82AoT", Asker.askForSemesterID(s, semList));
        s.close();
    }
    @Test
    public void TestSelection()
    {
        //test selection with parameter of 10
        ByteArrayInputStream inputStream = new ByteArrayInputStream("11\n-1\n3\n-2\n4".getBytes());
        Scanner s=new Scanner(inputStream);
        assertEquals(3, Asker.askForSelection(s, 10));
        assertEquals(4, Asker.askForSelection(s, 10));//only3 and 4 are valid in the input stream
    }
    @Test
    public void TestPath()
    {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("the green path\n\n\nthe cross road".getBytes());
        Scanner s=new Scanner(inputStream);
        assertEquals("the green path", Asker.askForPath(s));
        assertEquals("the cross road", Asker.askForPath(s));//ignonre the "" 
    }
}

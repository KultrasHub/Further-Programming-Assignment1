package src.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import src.*;

public class MainMenuTest {
    // Test adding component
    @Test
    public void TestAddingComponent() {
        // Create a list of student
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("S101312", "Cercy Lannister"));
        studentList.add(new Student("S102732", "Harry Potter", "8/28/2001"));
        studentList.add(new Student("S103821", "Jon Snow"));
        studentList.add(new Student("S102192", "Tyrion Lannister", "6/5/2000"));
        studentList.add(new Student("S111322", "Eddard Stark"));
        // course
        ArrayList<Course> courseList = new ArrayList<Course>();
        courseList.add(new Course("D001", "Adapted Thaumatergy"));
        courseList.add(new Course("D002", "Celestial Witchery", 22));
        courseList.add(new Course("D003", "Black Herbs", 33));
        courseList.add(new Course("D004", "Demonic Animation"));
        courseList.add(new Course("D005", "Rudimentary Necromancy", 22));
        // Create Semester
        ArrayList<String> semList = new ArrayList<String>();
        semList.add("282AoC");// The Age of chaos
        semList.add("392SA");// The Sorcery Ages
        semList.add("123EoT");// The Era of Trust
        semList.add("82AoT");// The Aeon of Titans
        semList.add("99EoD");// The Era of Darkness
        semList.add("281AoC");// The Age of chaos
        //sys
        EnrolmentSystem s=new EnrolmentSystem();
        MainMenu.getMainMenu().addEnrolmentSystem(s).addStudentList(studentList).addCourseList(courseList).addSemList(semList);
        // assert by checking size of component
        assertEquals(5, MainMenu.getMainMenu().getStudentList().size());
        assertEquals(5,MainMenu.getMainMenu().getCourseList().size());
        assertEquals(6,MainMenu.getMainMenu().getSemList().size());
        assertNotNull(MainMenu.getMainMenu().getSystem());
    }
}

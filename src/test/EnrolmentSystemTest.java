package src.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;
import src.*;

public class EnrolmentSystemTest {
    // get sample
    private ArrayList<Student> getSampleStudent() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("S101312", "Cercy Lannister"));
        studentList.add(new Student("S102732", "Harry Potter", "8/28/2001"));
        studentList.add(new Student("S103821", "Jon Snow"));
        studentList.add(new Student("S102192", "Tyrion Lannister", "6/5/2000"));
        studentList.add(new Student("S111322", "Eddard Stark"));
        return studentList;
    }

    private ArrayList<Course> getSampleCourse(ArrayList<String> semList) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        courseList.add(new Course("D001", "Adapted Thaumatergy")
                .addSemester(semList.get(0))
                .addSemester(semList.get(2))
                .addSemester(semList.get(3)));
        courseList.add(new Course("D002", "Celestial Witchery", 22)
                .addSemester(semList.get(2))
                .addSemester(semList.get(3))
                .addSemester(semList.get(4))
                .addSemester(semList.get(5)));
        courseList.add(new Course("D003", "Black Herbs", 33)
                .addSemester(semList.get(0))
                .addSemester(semList.get(2)));
        courseList.add(new Course("D004", "Demonic Animation")
                .addSemester(semList.get(2))
                .addSemester(semList.get(5)));
        courseList.add(new Course("D005", "Rudimentary Necromancy", 22)
                .addSemester(semList.get(1))
                .addSemester(semList.get(3)));
        courseList.add(new Course("D006", "Dwarven Studies")
                .addSemester(semList.get(0))
                .addSemester(semList.get(1))
                .addSemester(semList.get(4)));
        return courseList;
    }

    private ArrayList<String> getSampleSem() {
        // Create Semester
        ArrayList<String> semList = new ArrayList<String>();
        semList.add("282AoC");// The Age of chaos
        semList.add("392SA");// The Sorcery Ages
        semList.add("123EoT");// The Era of Trust
        semList.add("82AoT");// The Aeon of Titans
        semList.add("99EoD");// The Era of Darkness
        semList.add("281AoC");// The Age of chaos
        return semList;
    }

    @Test
    public void CheckAdd() {
        EnrolmentSystem sys = new EnrolmentSystem();
        ArrayList<Student> sList = getSampleStudent();
        ArrayList<String> semList = getSampleSem();
        ArrayList<Course> cList = getSampleCourse(semList);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "S101312\nD001\n282AoC\nS12312312\nD003\n99EoD\nS102732\nD007\nD003\n281AoC\n123EoT".getBytes());
        Scanner s = new Scanner(inputStream);
        sys.add(s, sList, cList, semList);
        sys.add(s, sList, cList, semList);
        // should have 2 students
        assertEquals("S101312", sys.getEnrolment().get(0).getStudent().getId());
        assertEquals("S102732", sys.getEnrolment().get(1).getStudent().getId());
        assertEquals("D003", sys.getEnrolment().get(1).getCourse().getId());
    }

    @Test
    public void CheckUpdate() {
        EnrolmentSystem sys = new EnrolmentSystem();
        ArrayList<Student> sList = getSampleStudent();
        ArrayList<String> semList = getSampleSem();
        ArrayList<Course> cList = getSampleCourse(semList);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "S101312\n282AoC\n0\nD001\n282AoC\nS12312312\nD002\n99EoD\nS102732\n392SA\n0\nD007\nD005\n281AoC\n123EoT\nS101312\n282AoC\n1\n0"
                        .getBytes());

        Scanner s = new Scanner(inputStream);
        sys.update(s, sList, cList, semList);
        sys.update(s, sList, cList, semList);
        // should have 2 students
        assertEquals("S101312", sys.getEnrolment().get(0).getStudent().getId());
        assertEquals("S102732", sys.getEnrolment().get(1).getStudent().getId());
        assertEquals("D005", sys.getEnrolment().get(1).getCourse().getId());
        // next update will delete an enrolment
        sys.update(s, sList, cList, semList);
        assertEquals("S102732", sys.getEnrolment().get(0).getStudent().getId());// this student is move up from index 1
                                                                                // to 0
        assertEquals(1, sys.getEnrolment().size());
    }

    @Test
    public void TestDelete() {
        EnrolmentSystem sys = new EnrolmentSystem();
        ArrayList<Student> sList = getSampleStudent();
        ArrayList<String> semList = getSampleSem();
        ArrayList<Course> cList = getSampleCourse(semList);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "S101312\nD001\n282AoC\nS12312312\nD003\n99EoD\nS101312\nD007\nD003\n281AoC\n123EoT\nS101312\n1\nS101312\n0"
                        .getBytes());
        Scanner s = new Scanner(inputStream);
        // must have 2 enrolmennt here
        sys.add(s, sList, cList, semList);
        sys.add(s, sList, cList, semList);
        assertEquals(2, sys.getEnrolment().size());
        // remove 1
        sys.delete(s, sList, cList, semList);
        // should have 1 enrolmennt
        assertEquals(1, sys.getEnrolment().size());
        // the remaining enrolmet course is D001
        assertEquals("D001", sys.getEnrolment().get(0).getCourse().getId());
        // remove the last
        sys.delete(s, sList, cList, semList);
        // should have no left
        assertEquals(0, sys.getEnrolment().size());
    }

    @Test
    public void CheckGetOne() {
        EnrolmentSystem sys = new EnrolmentSystem();
        ArrayList<Student> sList = getSampleStudent();
        ArrayList<String> semList = getSampleSem();
        ArrayList<Course> cList = getSampleCourse(semList);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "S101312\nD001\n282AoC\nS12312312\nD003\n99EoD\nS101312\nD007\nD003\n281AoC\n123EoT\nS101312\n1\nS101312\n0"
                        .getBytes());
        Scanner s = new Scanner(inputStream);
        // must have 2 enrolmennt here
        sys.add(s, sList, cList, semList);
        sys.add(s, sList, cList, semList);
        // assert
        StudentEnrolment se = sys.getOne(sList.get(0));// S101312
        assertEquals("D001", se.getCourse().getId());
        assertEquals("282AoC", se.getSem());
        assertNull(sys.getOne(sList.get(1)));// this student has no enrolment
    }

    @Test
    public void CheckGetAllStudentInCourse() {
        EnrolmentSystem sys = new EnrolmentSystem();
        ArrayList<Student> sList = getSampleStudent();
        ArrayList<String> semList = getSampleSem();
        ArrayList<Course> cList = getSampleCourse(semList);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "S101312\nD001\n282AoC\nS101312\nD003\n123EoT\nS102732\nD001\nS101312\n282AoC".getBytes());
        Scanner s = new Scanner(inputStream);
        // must have 3 enrolmennt here
        sys.add(s, sList, cList, semList);
        sys.add(s, sList, cList, semList);
        sys.add(s, sList, cList, semList);
        ArrayList<StudentEnrolment> seList = sys.getAll(cList.get(0), "282AoC");
        // should have 2 studennts
        assertEquals(2, seList.size());
        assertEquals("S101312", seList.get(0).getStudent().getId());
        assertEquals("S102732", seList.get(1).getStudent().getId());
    }

    @Test
    public void CheckGetAllCourseOfStudent() {
        EnrolmentSystem sys = new EnrolmentSystem();
        ArrayList<Student> sList = getSampleStudent();
        ArrayList<String> semList = getSampleSem();
        ArrayList<Course> cList = getSampleCourse(semList);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "S101312\nD001\n282AoC\nS101312\nD003\n282AoC\nS101312\nD006\nS101312\n282AoC".getBytes());
        Scanner s = new Scanner(inputStream);
        // must have 3 enrolmennt here
        sys.add(s, sList, cList, semList);
        sys.add(s, sList, cList, semList);
        sys.add(s, sList, cList, semList);
        ArrayList<StudentEnrolment> seList = sys.getAll(sList.get(0), "282AoC");
        //should have 3 course whenn check first studenntn
        assertEquals(3, seList.size());
        assertEquals("D001", seList.get(0).getCourse().getId());
        assertEquals("D003", seList.get(1).getCourse().getId());
        assertEquals("D006", seList.get(2).getCourse().getId());
    }
}

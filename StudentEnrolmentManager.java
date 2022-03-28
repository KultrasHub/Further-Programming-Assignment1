import java.util.ArrayList;
import java.util.Scanner;

public interface StudentEnrolmentManager {
    
    public void add(Scanner s,ArrayList<Student>sList,ArrayList<Course> cList,ArrayList<String> semList);
    public void add(Scanner s,Student std,ArrayList<Course> cList,String sem);
    public void update(Scanner s,ArrayList<Student>sList,ArrayList<Course> cList,ArrayList<String> semList);
    public void delete(ArrayList<StudentEnrolment> list,StudentEnrolment enrolment);
    //public void getOne
    public StudentEnrolment getOne(Student s);
    //Get all Student in 1 course
    public ArrayList<StudentEnrolment> getAll(Course c,String sem);
    //Get all course of 1 student
    public ArrayList<StudentEnrolment> getAll(Student s,String sem);
    //print all Student in 1 course
    public void printAll(Scanner s, ArrayList<Course> cList,ArrayList<String> sem);
    //print all Course of 1 student
    public void printAll(ArrayList<Student> sList,Scanner s,ArrayList<String> semList);
}

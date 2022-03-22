import java.util.ArrayList;
import java.util.Scanner;

public interface StudentEnrolmentManager {
    
    public void add(Scanner s,ArrayList<Student>sList,ArrayList<Course> cList,ArrayList<String> semList);
    public void update(Scanner s,ArrayList<Student>sList,ArrayList<Course> cList,ArrayList<String> semList);
    public void delete(ArrayList<StudentEnrolment> list,StudentEnrolment enrolment);
    //public void getOne();
    //public void getAll();
}

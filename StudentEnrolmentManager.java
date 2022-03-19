import java.util.ArrayList;

public interface StudentEnrolmentManager {
    
    public void add(ArrayList<StudentEnrolment> list,StudentEnrolment enrolment);
    public void update(ArrayList<StudentEnrolment> list,StudentEnrolment enrolment);
    public void delete(ArrayList<StudentEnrolment> list,StudentEnrolment enrolment);
    //public void getOne();
    //public void getAll();
}

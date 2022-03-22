import java.util.ArrayList;
import java.util.Date;

public class Student
{
    private String id;
    private String name;
    private Date birthDate;
    //private ArrayList<Course> courses=new ArrayList<Course>();
    //Setter
    public String getId()
    {
        return id;
    }
    @Override
    public String toString() {
        return "Student: "+id+ " - " +name+ " - "+birthDate;
    }
}
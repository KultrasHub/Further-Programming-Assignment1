import java.util.ArrayList;
import java.util.Comparator;

public class Course {
    private String id;
    private String name;
    private int numberOfCredit;
    //Course 
    public Course(String id,String name,int credit)
    {
        this.id=id;
        this.name=name;
        numberOfCredit=credit;
    }
    public Course(String id,String name)
    {
        this.id=id;
        this.name=name;
        numberOfCredit=0;
    }
    //private ArrayList<Student> students=new ArrayList<Student>();
    public String getId()
    {
        return id;
    }
    @Override
    public String toString() {
        return "Course: "+id+" - "+name;
    }
}

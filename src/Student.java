package src;
public class Student
{
    private String id;
    private String name;
    private String birthDate;
    //Constructor
    public Student (String id, String name,String birthday)
    {
        this.id=id;
        this.name=name;
        this.birthDate=birthday;
    }
    //constructor with empty birthday
    public Student(String id,String name)
    {
        this.id=id;
        this.name=name;
        birthDate="########";
    }
    //private ArrayList<Course> courses=new ArrayList<Course>();
    //Setter
    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getBirthDay()
    {
        return birthDate;
    }
    @Override
    public String toString() {
        return "Student: "+id+ " - " +name+ " - "+birthDate;
    }
    public String toCSVString()
    {
        return id+","+name+","+birthDate+"\n";
    }
}
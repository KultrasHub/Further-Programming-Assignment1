import java.util.ArrayList;

public class Course {
    private String id;
    private String name;
    private int numberOfCredit;
    private ArrayList<String> semList;
    //Course 
    public Course(String id,String name,int credit)
    {
        this.id=id;
        this.name=name;
        numberOfCredit=credit;
        semList=new ArrayList<String>();
    }
    public Course(String id,String name)
    {
        this.id=id;
        this.name=name;
        numberOfCredit=0;
        semList=new ArrayList<String>();
    }
    //Builder to add semester
    public Course addSemester(String sem)
    {
        //check if sem exist in semList
        for(int i=0;i<semList.size();i++)
        {
            if(sem.equals(semList.get(i)))
            {
                //the sem already exits inn semList
                return this;
            }
        }
        semList.add(sem);
        return this;
    }
    //Check if course contains a sem
    public boolean checkSem(String sem)
    {
         //check if sem exist in semList
         for(int i=0;i<semList.size();i++)
         {
             if(sem.equals(semList.get(i)))
             {
                 //the sem already exits inn semList
                 return true;
             }
         }
         return false;
    }
    //private ArrayList<Student> students=new ArrayList<Student>();
    public String getId()
    {
        return id;
    }
    public ArrayList<String> getSem()
    {
        return semList;
    }
    @Override
    public String toString() {
        return "Course: "+id+" - "+name;
    }
}

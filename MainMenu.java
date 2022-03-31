import java.util.*;

public class MainMenu {
    private static MainMenu instance;
    //property
    ArrayList<Student> sList;
    ArrayList<Course> cList;
    ArrayList<String> semList;
    EnrolmentSystem sys;
    private MainMenu()
    {
        //nothign to see here
    }
    public static MainMenu getMainMenu()
    {
        if(instance==null)
        {
            instance=new MainMenu();
        }
        return instance;
    }
    //buider
    public MainMenu addEnrolmentSystem(EnrolmentSystem sys)
    {
        this.sys=sys;
        return instance;
    }
    public MainMenu addStudentList(ArrayList<Student> std)
    {
        sList=std;
        return instance;
    }
    public MainMenu addCourseList(ArrayList<Course> cs)
    {
        cList=cs;
        return instance;
    }
    public MainMenu addSemList(ArrayList<String> sems)
    {
        semList=sems;
        return instance;
    }
    //Display main menu
    public void displayMainMenu(Scanner s)
    {
        boolean programFinish=false;
        while(!programFinish)
        {
        //display list of available options
        System.out.println("----------------------------");
        System.out.println("Select an option below:");
        System.out.println("0. Add a new enrolment to system ");
        System.out.println("1. Update an enrolment of a student in a semester");
        System.out.println("2. Delete an enrolment from a student enrolment");
        //System.out.println("3. Get the first enrolment of a student");//get one
        System.out.println("3. Print all enrolment");
        System.out.println("4. Print all courses of a student in a semester");
        System.out.println("5. Print all student of a course in a semester");
        System.out.println("6. Print all courses offered in a semester");
        System.out.println("7. Exit Program");
        System.out.println("Enter an id of option:");
        int opt=Asker.askForSelection(s, 7);
        if(opt==0)
        {
            //adding a new enrolment
            sys.add(s, sList, cList, semList);
        }
        else if(opt==1)
        {
            sys.update(s, sList, cList, semList);
        }
        else if(opt==2)
        {
            sys.delete(s, sList, cList, semList);
        }
        else if(opt==3)
        {
            sys.printAll();
        }
        else if(opt==4)
        {
            //print all courses of a student
            sys.printAll(sList, s, semList);
        }
        else if(opt==5)
        {
            //print all student in a course
            sys.printAll(s, cList, semList);
        }
        else if(opt==6)
        {
            //print all course offered in a sem
            sys.displayCoursesOfSem(s, cList, semList);
        }
        else if(opt==7)
        {
            programFinish=true;
        }
        //program exit at option 7
    }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Asker {
    //asking 
    public static Student askForStudentID(Scanner s,ArrayList<Student> sList )
    {
        //ask for student id
        System.out.println("Enter Student ID:");
        String studentID=s.nextLine();
        //check if Student exist in List
        int result=getStudentId(sList, studentID);
        if(result==-1)
        {
            //the id doesn't match any student in the list
            System.out.println("You student input doesn't exist in out data:");
            System.out.println("----------------------------");
            //provide option
            System.out.println("Please retry:(Enter 'exit' to return to main menu)");
            //
            askForStudentID(s, sList);
        }
        else if(result==-2)
        {
            //user want to exit
        }
        //return
        return sList.get(result);
    }
    //Check if student id exist in List
    private static int getStudentId(ArrayList<Student> sList,String sId)
    {
        //check if user want to exit
        if(sId=="exit")
        {
            //user want to exit
            return -2;
        }
        for(int i=0;i<sList.size();i++)
        {
            if(sId==sList.get(i).getId())
            {
                //this is the match student
                return i;
            }
        }
        
        return -1;//student doesn't exist in the give list
    } 
    //course
    public static Course askForCourseID(Scanner s,ArrayList<Course> cList )
    {
        //ask for student id
        System.out.println("Enter Course ID:");
        String cID=s.nextLine();
        //check if Student exist in List
        int result=getCourseId(cList, cID);
        if(result==-1)
        {
            //the id doesn't match any student in the list
            System.out.println("You scourse id doesn't exist in out data:");
            System.out.println("----------------------------");
            //provide option
            System.out.println("Please retry:(Enter 'exit' to return to main menu)");
            //
            askForCourseID(s, cList);
        }
        else if(result==-2)
        {
            //user want to exit
        }
        //return
        return cList.get(result);
    }
    //Check if course id exist in List
    private static int getCourseId(ArrayList<Course> cList,String cId)
    {
        //check if user want to exit
        if(cId=="exit")
        {
            //user want to exit
            return -2;
        }
        for(int i=0;i<cList.size();i++)
        {
            if(cId==cList.get(i).getId())
            {
                //this is the match student
                return i;
            }
        }
        
        return -1;//student doesn't exist in the give list
    } 
    //Semester
    //course
    public static String askForSemesterID(Scanner s,ArrayList<String> semList )
    {
        //ask for student id
        System.out.println("Enter Semester ID:");
        String semID=s.nextLine();
        //check if Student exist in List
        int result=getSemesterId(semList, semID);
        if(result==-1)
        {
            //the id doesn't match any student in the list
            System.out.println("You Semester id doesn't exist in out data:");
            System.out.println("----------------------------");
            //provide option
            System.out.println("Please retry:(Enter 'exit' to return to main menu)");
            //
            askForSemesterID(s, semList);
        }
        else if(result==-2)
        {
            //user want to exit
        }
        //return the sem
        return semList.get(result);
    }
    //Check if course id exist in List
    private static int getSemesterId(ArrayList<String> semList,String semId)
    {
        //check if user want to exit
        if(semId=="exit")
        {
            //user want to exit
            return -2;
        }
        for(int i=0;i<semList.size();i++)
        {
            if(semId==semList.get(i))
            {
                //this is the match student
                return i;
            }
        }
        
        return -1;//student doesn't exist in the give list
    } 
    //Select an option
    public static int askForSelection(Scanner s,int selectionSize)
    {
        System.out.println("Enter Your Option:");
        String opt=s.nextLine();
        //check for null
        if(opt==null||opt.equals(""))
        {
            //empty input
            System.out.println("You mustn't leave your option empty");
            System.out.println("----------------------------");
            System.out.println("Please retry enter an valid option:");
            //retry
            return askForSelection(s, selectionSize);
        }
        //parse to int
        try {
            int value =Integer.parseInt(opt);
            if(value<selectionSize&&value>=0)
            {
                //valid option
                return value;
            }
            else{
                //invalid
                System.out.println("Your option is invalid");
                System.out.println("----------------------------");
                System.out.println("Please retry enter an correct option:");
                return askForSelection(s, selectionSize);
            }
        }
        catch(NumberFormatException e){
            //option is not valid
            System.out.println("You must input the option in positive number only");
            System.out.println("----------------------------");
            System.out.println("Please retry enter an valid option:");
            return askForSelection(s, selectionSize);
        }
    }
}

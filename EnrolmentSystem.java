import java.util.ArrayList;
import java.util.Scanner;

public class EnrolmentSystem implements StudentEnrolmentManager {
    
    public static void main(String[] args) {
        //InitScanner
        Scanner scanner=new Scanner(System.in);//using 2 scanners made program result in error( only happen in my vscode I think) 
        //initualize list to keep all enrolment
        //ArrayList<StudentEnrolment> enrolmentList=new ArrayList<StudentEnrolment>();
        //Enroll(scanner);
        //close scanner
        scanner.close();
        
    }

    //Condition checking
    //check if an enrolment has already existed in list
    private static boolean checkExisted(ArrayList<StudentEnrolment> list, StudentEnrolment enrolment) 
    {
        for(int i=0;i<list.size();i++)
        {
            if(enrolment.compare(list.get(i)))//compare by component rather than refernece
            {
                //the current enrolment has already existed
                System.out.println("The enrolment: "+enrolment+ " has already existed in the enrolment data");
                return false;
            }
        }
        return true;
    }
    //Enroll a Student
    public static void Enroll(Scanner s,ArrayList<Student>sList,ArrayList<Course> cList,ArrayList<String> semList)
    {
        System.out.println("----------------------------");
        //ask for student id
        Student student=Asker.askForStudentID(s, sList);
        //ask for semester id
        Course course=Asker.askForCourseID(s, cList);
        //ask for course id
        String sem=Asker.askForSemesterID(s, semList);
    }
    //Functionality
    @Override
    public void add(ArrayList<StudentEnrolment> list, StudentEnrolment enrolment) {
        //check if enrolment has addable
        if(checkExisted(list, enrolment)){
            list.add(enrolment);
        }
        else{
            //fail to add enrolment
            System.out.println("Fail to add enrolment to enrolment data");
        }
    }

    @Override
    public void update(ArrayList<StudentEnrolment> list, StudentEnrolment enrolment) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(ArrayList<StudentEnrolment> list, StudentEnrolment enrolment) {
        // TODO Auto-generated method stub
        //loop through list and delete the enrolment
        for(int i=0;i<list.size();i++)
        {
            if(enrolment.compare(list.get(i)))
            {
                //this is the enrolment to be deleted
                list.remove(i);
                return;
            }
        }
        //enrolment cannot be found in list
        System.out.println("Enrolment cannot be found in enrolment data");
    }
    

}

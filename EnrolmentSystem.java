import java.util.ArrayList;
import java.util.Scanner;

public class EnrolmentSystem implements StudentEnrolmentManager {
    private ArrayList<StudentEnrolment> enrolmentList;
    //Constructor
    public EnrolmentSystem()
    {
        enrolmentList=new ArrayList<StudentEnrolment>();
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
    //Functionality
    @Override
    public void add(Scanner s,ArrayList<Student>sList,ArrayList<Course> cList,ArrayList<String> semList) {
        //Asking for info and extract data
        System.out.println("----------------------------");
        //ask for student id
        Student student=Asker.askForStudentID(s, sList);
        //ask for semester id
        Course course=Asker.askForCourseID(s, cList);
        //ask for course id
        String sem=Asker.askForSemesterID(s, semList);
        //Create enrolment
        StudentEnrolment se=new StudentEnrolment(student, course, sem);
        //check if enrolment has addable
        if(checkExisted(enrolmentList, se)){
            enrolmentList.add(se);
        }
        else{
            //fail to add enrolment
            System.out.println("Fail to add enrolment to enrolment data");
        }
    }

    @Override
    public void update(Scanner s,ArrayList<Student>sList,ArrayList<Course> cList,ArrayList<String> semList) {
        //display a list of student and ask to select a student
        for(int i=0;i<sList.size();i++)
        {
            System.out.println(i+". "+sList.get(i).toString());
        }
        int selected=Asker.askForSelection(s, sList.size());
        //get student id 
        Student student=sList.get(selected);
        //ask for semester
        String sem=Asker.askForSemesterID(s, semList);
        //print courses of the selected student
        int count=0;//
        for(int i=0;i<enrolmentList.size();i++)
        {
            if(enrolmentList.get(i).compare(student,sem)){
                //match student
                //print course
                System.out.println(count+". "+ enrolmentList.get(i).getCourse().toString());
                count++;
            }
        }
        //ask for option 
        System.out.println("0. Add a course");
        System.out.println("1. Delete a course");
        int option=Asker.askForSelection(s, 2);//2 option
        if(option==0)
        {
            //adding a course
            //should Display course that is not in the student 
        }
        else if(option==1)
        {
            //Delete a course
            
        }
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
    
    @Override
    public ArrayList<StudentEnrolment> getAll(Course c, String sem) {
        ArrayList<StudentEnrolment> list=new ArrayList<StudentEnrolment>();
        //find all student of has course of sem
        for(int i=0;i<enrolmentList.size();i++)
        {
            //compare course and sem
            if(enrolmentList.get(i).compare(c, sem))
            {
                //matched sem
                list.add(enrolmentList.get(i));
            }
        }
        return list;
    }

    @Override
    public ArrayList<StudentEnrolment> getAll(Student s, String sem) {
        ArrayList<StudentEnrolment> list=new ArrayList<StudentEnrolment>();
        //find all student of has course of sem
        for(int i=0;i<enrolmentList.size();i++)
        {
            //compare course and sem
            if(enrolmentList.get(i).compare(s, sem))
            {
                //matched sem
                list.add(enrolmentList.get(i));
            }
        }
        return list;
    }

    @Override
    public void printAll(Scanner s, ArrayList<Course> cList, ArrayList<String> semList) {
        System.out.println("----------------------------");
        //ask for Course
        Course c=Asker.askForCourseID(s, cList);
        //ask for sem
        String sem=Asker.askForSemesterID(s, semList);
        //find all student of has course of sem
        System.out.println("Students in "+c.toString()+" in "+sem);
        for(int i=0;i<enrolmentList.size();i++)
        {
            //compare course and sem
            if(enrolmentList.get(i).compare(c, sem))
            {
                //print
                System.out.println(enrolmentList.get(i).getStudent().toString());
            }
        }
        //ask if user want to save into csv
        System.out.println("----------------------------");
        System.out.println("Do you want to save report to CSV file?");
        System.out.println("0.Yes");
        System.out.println("1.No");
        int chosen=Asker.askForSelection(s, 2);
        if(chosen==0)
        {
            //use get all to get data and save data to csv
        }
    }

    @Override
    public void printAll(ArrayList<Student> sList, Scanner s, ArrayList<String> semList) {
        System.out.println("----------------------------");
        //ask for student
        Student student=Asker.askForStudentID(s, sList);
        //ask for sem
        String sem=Asker.askForSemesterID(s, semList);
        //find all course of the student
        System.out.println("course of "+ student.toString()+" in "+ sem);
        for(int i=0;i<enrolmentList.size();i++)
        {
            //compare student and sem
            if(enrolmentList.get(i).compare(student, sem))
            {
                //matched
                System.out.println(enrolmentList.get(i).getCourse().toString());
            }
        }
         //ask if user want to save into csv
         System.out.println("----------------------------");
         System.out.println("Do you want to save report to CSV file?");
         System.out.println("0.Yes");
         System.out.println("1.No");
         int chosen=Asker.askForSelection(s, 2);
         if(chosen==0)
         {
             //use get all to get data and save data to csv
         }
    }
    

}

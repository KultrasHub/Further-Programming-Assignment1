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
    public static void main(String[] args) {
        //Initualize enrolment 
        EnrolmentSystem enrolment=new EnrolmentSystem();
        //Create Students
        Student[] studentList=new Student[10];
        studentList[0]=new Student("S101312", "Cercy Lannister");
        studentList[1]=new Student("S102732", "Harry Potter","8/28/2001" );
        studentList[2]=new Student("S103821", "Jon Snow");
        studentList[3]=new Student("S102192", "Tyrion Lannister","6/5/2000");
        studentList[4]=new Student("S111322", "Eddard Stark");
        studentList[5]=new Student("S290382", "Sansa Stark");
        studentList[6]=new Student("S287423", "Daenerys Targaryen","8/28/2001");
        studentList[7]=new Student("S189742", "Brienne Of Tarth","3/4/2000 ");
        studentList[8]=new Student("S012302", "Petyr Baelish");
        studentList[9]=new Student("S928323", "Stannis Baratheon");
        //Create Semester
        String[] semList=new String[6]; 
        semList[0]="282AoC";//The Age of chaos
        semList[1]="392SA";//The Sorcery Ages
        semList[2]="123EoT";//The Era of Trust
        semList[3]="82AoT";//The Aeon of Titans
        semList[4]="99EoD";//The Era of Darkness
        semList[5]="281AoC";//The Age of chaos
        //
        //Create Course
        Course[] courseList=new Course[14];
        courseList[0]=new Course("D001","Adapted Thaumatergy")
            .addSemester(semList[0])
            .addSemester(semList[2])
            .addSemester(semList[3]);
        courseList[1]=new Course("D002","Celestial Witchery",22)
            .addSemester(semList[2])
            .addSemester(semList[3])
            .addSemester(semList[4])
            .addSemester(semList[5]);
        courseList[2]=new Course("D003","Black Herbs",33)
            .addSemester(semList[0])
            .addSemester(semList[2]);
        courseList[3]=new Course("D004","Demonic Animation")
            .addSemester(semList[2])
            .addSemester(semList[5]);
        courseList[4]=new Course("D005","Rudimentary Necromancy",22)
            .addSemester(semList[1])
            .addSemester(semList[3]);
        courseList[5]=new Course("D006","Dwarven Studies")
            .addSemester(semList[0])
            .addSemester(semList[1])
            .addSemester(semList[4]);
        courseList[6]=new Course("D007","Unholy Occultism",55)
            .addSemester(semList[0])
            .addSemester(semList[3])
            .addSemester(semList[4])
            .addSemester(semList[5]);
        courseList[7]=new Course("D008","White Enchantment")
            .addSemester(semList[0])
            .addSemester(semList[3]);
        courseList[8]=new Course("D009","Lycantthropic Exorsism",22)
            .addSemester(semList[1]);
        courseList[9]=new Course("D010","Inappropriate Summoning",22)
            .addSemester(semList[2])
            .addSemester(semList[5]);
        courseList[10]=new Course("D011","Forbidden Artifacts")
            .addSemester(semList[4]);
        courseList[11]=new Course("D012","Progressive Writings",33)
            .addSemester(semList[3]);
        courseList[12]=new Course("D013","Effective Symbolism")
            .addSemester(semList[2])
            .addSemester(semList[1]);
        courseList[13]=new Course("D013","Contemporary Magic",11)
            .addSemester(semList[0]);
    } 

}

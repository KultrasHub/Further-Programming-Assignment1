package src;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EnrolmentSystem implements StudentEnrolmentManager {
    private ArrayList<StudentEnrolment> enrolmentList;

    // Constructor
    public EnrolmentSystem() {
        enrolmentList = new ArrayList<StudentEnrolment>();
    }

    // Condition checking
    // check if an enrolment has already existed in list
    private boolean checkExisted(ArrayList<StudentEnrolment> list, StudentEnrolment enrolment) {
        for (int i = 0; i < list.size(); i++) {
            if (enrolment.compare(list.get(i)))// compare by component rather than refernece
            {
                // the current enrolment has already existed
                System.out.println("The enrolment: " + enrolment + " has already existed in the enrolment data");
                return false;
            }
        }
        return true;
    }

    // for testing purpose
    public ArrayList<StudentEnrolment> getEnrolment() {
        return enrolmentList;
    }

    // Functionality
    @Override
    public void add(Scanner s, ArrayList<Student> sList, ArrayList<Course> cList, ArrayList<String> semList) {
        // Asking for info and extract data
        System.out.println("----------------------------");
        // ask for student id
        Student student = Asker.askForStudentID(s, sList);
        if (student == null) {
            System.out.println("There may be no student in the given List");
            return;//
        }
        // ask for Course id
        Course course = Asker.askForCourseID(s, cList);
        if (course == null) {
            System.out.println("There may be no courses in the given List");
            return;//
        }
        // ask for Semester id
        System.out.println("Semester available for " + course.toString());
        String sem = Asker.askForSemesterID(s, course.getSem());
        if (sem == null) {
            System.out.println("There may be no semesters in the given List");
            return;//
        }
        // Create enrolment
        StudentEnrolment se = new StudentEnrolment(student, course, sem);
        // check if enrolment has addable
        if (checkExisted(enrolmentList, se)) {
            enrolmentList.add(se);
        } else {
            // fail to add enrolment
            System.out.println("Fail to add enrolment to enrolment data");
        }
    }

    // add new courses to a student within a semester
    @Override
    public void add(Scanner s, Student std, ArrayList<Course> cList, String sem) {
        // Asking for info and extract data
        System.out.println("----------------------------");
        // display course that offers in the given semester
        System.out.println("Courses offered in Semester: " + sem);
        ArrayList<Course> temp = new ArrayList<Course>();
        for (int i = 0; i < cList.size(); i++) {
            if (cList.get(i).checkSem(sem)) {
                // this course is available in the given sem
                temp.add(cList.get(i));
            }
        }
        // get course
        Course course = Asker.askForCourseID(s, temp);
        if (course == null) {
            System.out.println("There may be no courses that is offered in the given semester");
            return;//
        }
        StudentEnrolment se = new StudentEnrolment(std, course, sem);
        // check if enrolment has addable
        if (checkExisted(enrolmentList, se)) {
            enrolmentList.add(se);
        } else {
            // fail to add enrolment
            System.out.println("Fail to add enrolment to enrolment data");
        }
    }

    @Override
    public void update(Scanner s, ArrayList<Student> sList, ArrayList<Course> cList, ArrayList<String> semList) {
        // get student id
        Student student = Asker.askForStudentID(s, sList);
        if (student == null) {
            System.out.println("There may be no student in the given List");
            return;//
        }
        // ask for semester
        String sem = Asker.askForSemesterID(s, semList);
        if (sem == null) {
            System.out.println("There may be no semester in the given List");
            return;//
        }
        System.out.println("Courses of Student " + student.getId() + " in " + sem);
        //
        ArrayList<Course> enrolled = new ArrayList<Course>();
        // print courses of the selected student
        int count = 0;//
        for (int i = 0; i < enrolmentList.size(); i++) {
            if (enrolmentList.get(i).compare(student, sem)) {
                // match student
                enrolled.add(enrolmentList.get(i).getCourse());
                // print course
                System.out.println(count + ". " + enrolmentList.get(i).getCourse().toString());
                count++;
            }
        }
        // ask for option
        System.out.println("----------------------------");
        System.out.println("0. Add a course to student enrolment");
        System.out.println("1. Delete a course from student enrolment");
        int option = Asker.askForSelection(s, 2);// 2 option
        if (option == 0) {
            // adding a course
            add(s, student, cList, sem);
        } else if (option == 1) {
            if (enrolled.size() == 0) {
                System.out.println("Student: " + student.getId() + " has no course in sem " + sem);
                return;// no course to remove
            }
            // Delete a course
            delete(s, student, enrolled, sem);
        }
        enrolled.clear();
    }

    @Override
    public void delete(Scanner s, Student std, ArrayList<Course> cList, String sem) {
        //
        // Asking for info and extract data
        System.out.println("----------------------------");
        // display course Student selected in the given semester
        // print the courses
        for (int i = 0; i < cList.size(); i++) {
            System.err.println(i + ". " + cList.get(i).toString());
        }
        System.out.println("Select the course you want to remove from " + std.toString() + " enrollment");
        int selected = Asker.askForSelection(s, cList.size());
        if (selected < 0) {
            System.out.println("the student has no courses to display in sem: " + sem);
        }
        // selected is the index to remove an enrollment
        enrolmentList.remove(selected);
    }

    @Override
    public void delete(Scanner s, ArrayList<Student> sList, ArrayList<Course> cList, ArrayList<String> semList) {
        // Asking for info and extract data
        System.out.println("----------------------------");
        // ask for a student
        System.out.println("Pick a student whose enrolment you want to remove:");
        Student std = Asker.askForStudentID(s, sList);
        if (std == null) {
            System.out.println("your student list is empty");
            return;
        }
        // display all courses that this student has enrolled in
        int count = 0;
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < enrolmentList.size(); i++) {
            if (enrolmentList.get(i).compare(std)) {
                // this is enrolment of the student
                System.out.println(count + ". " + enrolmentList.get(i).toString());
                count++;
                // add inndex
                indexList.add(i);
            }
        }
        System.out.println("Please select the enrollment you wish to remove:");
        int opt = Asker.askForSelection(s, count);
        if (opt < 0) {
            System.out.println("the student has not enrolled in our system!");
            return;
        }
        // remove the enrolment at index
        enrolmentList.remove((int) indexList.get(opt));

    }

    @Override
    public StudentEnrolment getOne(Student s) {
        // get the first enrollment of the student
        for (int i = 0; i < enrolmentList.size(); i++) {
            if (enrolmentList.get(i).compare(s)) {
                return enrolmentList.get(i);
            }
        }
        // cannot find any enrolment of the student
        return null;
    }

    @Override
    public ArrayList<StudentEnrolment> getAll(Course c, String sem) {
        ArrayList<StudentEnrolment> list = new ArrayList<StudentEnrolment>();
        // find all student of has course of sem
        for (int i = 0; i < enrolmentList.size(); i++) {
            // compare course and sem
            if (enrolmentList.get(i).compare(c, sem)) {
                // matched sem
                list.add(enrolmentList.get(i));
            }
        }
        return list;
    }

    @Override
    public ArrayList<StudentEnrolment> getAll(Student s, String sem) {
        ArrayList<StudentEnrolment> list = new ArrayList<StudentEnrolment>();
        // find all student of has course of sem
        for (int i = 0; i < enrolmentList.size(); i++) {
            // compare course and sem
            if (enrolmentList.get(i).compare(s, sem)) {
                // matched sem
                list.add(enrolmentList.get(i));
            }
        }
        return list;
    }

    // print all enrollment in all sem
    public void printAll() {
        System.out.println("----------------------------");
        System.out.println("All enrolment");
        for (int i = 0; i < enrolmentList.size(); i++) {
            System.out.println(enrolmentList.get(i).toString());
        }
    }

    // print all students of a courses in a sem
    public void printAll(Scanner s, ArrayList<Course> cList, ArrayList<String> semList) {
        System.out.println("----------------------------");
        System.out.println("Display all student of a course");
        // ask for Course
        Course c = Asker.askForCourseID(s, cList);
        if (c == null) {
            System.out.println("There may be no courses in the given List");
            return;//
        }
        // ask for sem
        String sem = Asker.askForSemesterID(s, semList);
        if (sem == null) {
            System.out.println("There may be no semesters in the given List");
            return;//
        }
        // find all student of has course of sem
        System.out.println("Students in " + c.toString() + " in " + sem);
        // array of student
        ArrayList<Student> students = new ArrayList<Student>();
        for (int i = 0; i < enrolmentList.size(); i++) {
            // compare course and sem
            if (enrolmentList.get(i).compare(c, sem)) {
                // print
                System.out.println(enrolmentList.get(i).getStudent().toString());
                students.add(enrolmentList.get(i).getStudent());
            }
        }
        // ask if user want to save into csv
        System.out.println("----------------------------");
        System.out.println("Do you want to save report to CSV file?");
        System.out.println("0.Yes");
        System.out.println("1.No");
        int chosen = Asker.askForSelection(s, 2);
        if (chosen == 0) {
            // use get all to get data and save data to csv
            writeStudentDataToCSV(s, students);
        }
    }

    // print all courses of a student in a sem
    public void printAll(ArrayList<Student> sList, Scanner s, ArrayList<String> semList) {
        System.out.println("----------------------------");
        System.out.println("Display all courses of a student");
        // ask for student
        Student student = Asker.askForStudentID(s, sList);
        if (student == null) {
            System.out.println("There may be no students in the given List");
            return;//
        }
        // ask for sem
        String sem = Asker.askForSemesterID(s, semList);
        if (sem == null) {
            System.out.println("There may be no sem in the given List");
            return;//
        }
        // find all course of the student
        System.out.println("course of " + student.toString() + " in " + sem);
        // Save courses to an array List
        ArrayList<Course> courses = new ArrayList<Course>();
        for (int i = 0; i < enrolmentList.size(); i++) {
            // compare student and sem
            if (enrolmentList.get(i).compare(student, sem)) {
                // matched
                System.out.println(enrolmentList.get(i).getCourse().toString());
                courses.add(enrolmentList.get(i).getCourse());
            }
        }
        // ask if user want to save into csv
        System.out.println("----------------------------");
        System.out.println("Do you want to save report to CSV file?");
        System.out.println("0.Yes");
        System.out.println("1.No");
        int chosen = Asker.askForSelection(s, 2);
        if (chosen == 0) {
            // use get all to get data and save data to csv
            writeDataToCSV(s, courses);
        }
    }

    // print all courses that is offered in a sem
    public void displayCoursesOfSem(Scanner s, ArrayList<Course> cList, ArrayList<String> semList) {

        System.out.println("----------------------------");
        // ask for sem
        String sem = Asker.askForSemesterID(s, semList);
        if (sem == null) {
            System.out.println("There may be no sem in the given List");
            return;//
        }
        System.out.println("Courses Offered in Semester: " + sem);
        // display all courses that is available for this sem
        ArrayList<Course> courses = new ArrayList<Course>();
        for (int i = 0; i < cList.size(); i++) {
            if (cList.get(i).checkSem(sem)) {
                // this course is offered for this semester
                System.out.println(cList.get(i).toString());
                // add
                courses.add(cList.get(i));
            }
        }
        // ask if user want to save into csv
        System.out.println("----------------------------");
        System.out.println("Do you want to save report to CSV file?");
        System.out.println("0.Yes");
        System.out.println("1.No");
        int chosen = Asker.askForSelection(s, 2);
        if (chosen == 0) {
            // use get all to get data and save data to csv
            writeDataToCSV(s, courses);
        }
    }

    private void writeStudentDataToCSV(Scanner s, ArrayList<Student> sList) {
        // ask file name
        String filePath = Asker.askForPath(s);
        // create file object at the file placed location
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < sList.size(); i++) {
                writer.append(sList.get(i).toCSVString());
            }
            writer.flush();
            writer.close();
            System.out.println("Saving complete");
            System.out.println("Your data is save in:" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeDataToCSV(Scanner s, ArrayList<Course> cList) {
        // ask file name
        String filePath = Asker.askForPath(s);
        // create file object at the file placed location
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < cList.size(); i++) {
                writer.append(cList.get(i).toCSVString());
            }
            writer.flush();
            writer.close();
            System.out.println("Saving complete");
            System.out.println("Your data is save in:" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Scanner
        Scanner s = new Scanner(System.in);
        // Initualize enrolment
        EnrolmentSystem enrolment = new EnrolmentSystem();
        // Create Students
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("S101312", "Cercy Lannister"));
        studentList.add(new Student("S102732", "Harry Potter", "8/28/2001"));
        studentList.add(new Student("S103821", "Jon Snow"));
        studentList.add(new Student("S102192", "Tyrion Lannister", "6/5/2000"));
        studentList.add(new Student("S111322", "Eddard Stark"));
        studentList.add(new Student("S290382", "Sansa Stark"));
        studentList.add(new Student("S287423", "Daenerys Targaryen", "8/28/2001"));
        studentList.add(new Student("S189742", "Brienne Of Tarth", "3/4/2000 "));
        studentList.add(new Student("S012302", "Petyr Baelish"));
        studentList.add(new Student("S928323", "Stannis Baratheon"));
        // Create Semester
        ArrayList<String> semList = new ArrayList<String>();
        semList.add("282AoC");// The Age of chaos
        semList.add("392SA");// The Sorcery Ages
        semList.add("123EoT");// The Era of Trust
        semList.add("82AoT");// The Aeon of Titans
        semList.add("99EoD");// The Era of Darkness
        semList.add("281AoC");// The Age of chaos
        //
        // Create Course
        ArrayList<Course> courseList = new ArrayList<Course>();
        courseList.add(new Course("D001", "Adapted Thaumatergy")
                .addSemester(semList.get(0))
                .addSemester(semList.get(2))
                .addSemester(semList.get(3)));
        courseList.add(new Course("D002", "Celestial Witchery", 22)
                .addSemester(semList.get(2))
                .addSemester(semList.get(3))
                .addSemester(semList.get(4))
                .addSemester(semList.get(5)));
        courseList.add(new Course("D003", "Black Herbs", 33)
                .addSemester(semList.get(0))
                .addSemester(semList.get(2)));
        courseList.add(new Course("D004", "Demonic Animation")
                .addSemester(semList.get(2))
                .addSemester(semList.get(5)));
        courseList.add(new Course("D005", "Rudimentary Necromancy", 22)
                .addSemester(semList.get(1))
                .addSemester(semList.get(3)));
        courseList.add(new Course("D006", "Dwarven Studies")
                .addSemester(semList.get(0))
                .addSemester(semList.get(1))
                .addSemester(semList.get(4)));
        courseList.add(new Course("D007", "Unholy Occultism", 55)
                .addSemester(semList.get(0))
                .addSemester(semList.get(3))
                .addSemester(semList.get(4))
                .addSemester(semList.get(5)));
        courseList.add(new Course("D008", "White Enchantment")
                .addSemester(semList.get(0))
                .addSemester(semList.get(3)));
        courseList.add(new Course("D009", "Lycantthropic Exorsism", 22)
                .addSemester(semList.get(1)));
        courseList.add(new Course("D010", "Inappropriate Summoning", 22)
                .addSemester(semList.get(2))
                .addSemester(semList.get(5)));
        courseList.add(new Course("D011", "Forbidden Artifacts")
                .addSemester(semList.get(4)));
        courseList.add(new Course("D012", "Progressive Writings", 33)
                .addSemester(semList.get(3)));
        courseList.add(new Course("D013", "Effective Symbolism")
                .addSemester(semList.get(2))
                .addSemester(semList.get(1)));
        courseList.add(new Course("D014", "Contemporary Magic", 11)
                .addSemester(semList.get(0)));

        // Create Main menu
        MainMenu.getMainMenu().addEnrolmentSystem(enrolment)
                .addStudentList(studentList)
                .addCourseList(courseList)
                .addSemList(semList);
        // display main menu
        MainMenu.getMainMenu().displayMainMenu(s);

        s.close();
    }

}

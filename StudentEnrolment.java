public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;
    public StudentEnrolment(Student s,Course c,String sem)
    {
        student=s;
        course=c;
        semester=sem;
    }
    //compare
    public boolean compare(StudentEnrolment se) {
        if(se==null)
        {
            return false;
        }
        //2 enrolment is similar if student,enrolment,semester is similar
        if(se.student==student&&se.course==course&&se.semester==semester)
        {
            return true;//similar
        }
        else{
            return false;
        }
    }
    //compare student
    public boolean compare(Student s,String sem) {
        if(s==null)
        {
            return false;
        }
        if(s==student)
        {
            if(semester==sem)
            {
            return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    //getter
    public Course getCourse(){
        return course;
    }
}
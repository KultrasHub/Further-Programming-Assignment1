public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;
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
}

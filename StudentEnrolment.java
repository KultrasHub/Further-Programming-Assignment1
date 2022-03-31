public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Student s, Course c, String sem) {
        student = s;
        course = c;
        semester = sem;
    }

    // compare
    public boolean compare(StudentEnrolment se) {
        if (se == null) {
            return false;
        }
        // 2 enrolment is similar if student,enrolment,semester is similar
        if (se.student == student && se.course == course && se.semester.equals(semester)) {
            return true;// similar
        } else {
            return false;
        }
    }

    public boolean compare(Student s, String sem) {
        if (s == null) {
            return false;
        }
        if (s == student) {
            if (semester.equals(sem)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean compare(Course c, String sem) {
        if (c == null) {
            return false;
        }
        // check course
        if (c == course) {
            // check sem
            if (sem.equals(semester)) {
                return true;
            }
        }
        return false;
    }

    public boolean compare(Student s) {
        if (s == null) {
            return false;
        }
        if (s == student) {
            return true;
        } else {
            return false;
        }
    }

    // getter
    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return student.toString()+" of "+course.toString()+" in "+semester;
    }
}

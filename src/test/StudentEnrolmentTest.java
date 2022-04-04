package src.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import src.*;
public class StudentEnrolmentTest {
    @Test
    public void TestConstructor()
    {
        Student s=new Student("S123", "Tywin Lannister");
        Course c=new Course("D111", "Teleport");
        String sem="123A";
        StudentEnrolment se=new StudentEnrolment(s,c,sem);
        //check
        assertEquals(s.getId(), se.getStudent().getId());
        assertEquals(c.getName(), se.getCourse().getName());
    }
    //check Comparing
    @Test 
    public void TestCompare()
    {
        Student s=new Student("S123", "Tywin Lannister");
        Course c=new Course("D111", "Teleport");
        String sem="123A";
        StudentEnrolment se1=new StudentEnrolment(s,c,sem);
        Student s1=new Student("S125", "Aery Targerion");
        Course c2=new Course("D121", "Hack and Slash");
        StudentEnrolment se2=new StudentEnrolment(s1,c,sem);
        StudentEnrolment se3=new StudentEnrolment(s,c,sem);
        //compare by enrolment
        assertTrue( se1.compare(se3));
        assertFalse(se1.compare(se2));
        //compare by student and sem
        assertTrue(se1.compare(s, sem));
        assertFalse(se2.compare(s, sem));
        //compare by course and sem
        assertTrue( se1.compare(c, sem));
        assertFalse( se1.compare(c2, sem));
        //compare by student
        assertTrue(se1.compare(s));
        assertFalse(se1.compare(s1));
    }
    @Test
    public void TestEnrolmentString()
    {
        Student s=new Student("S123", "Tywin Lannister");
        Course c=new Course("D111", "Teleport");
        String sem="123A";
        StudentEnrolment se1=new StudentEnrolment(s,c,sem);
        assertEquals(s.toString()+" of "+c.toString()+" in "+sem, se1.toString());
    }
}

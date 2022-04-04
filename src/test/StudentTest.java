package src.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import src.*;
public class StudentTest {
    @Test
    public void TestConstructor()
    {
        Student s1=new Student("S123", "Tywin Lannister");
        Student s2=new Student("S132", "Droggo","13/8/2200");
        assertEquals("S123", s1.getId());
        assertFalse(s2.getId()=="S123");
        //check name
        assertEquals("Tywin Lannister",s1.getName());
        //check birthDay
        assertEquals("########", s1.getBirthDay());
        assertFalse( s2.getBirthDay()=="########");
    }
    //check to string
    @Test
    public void TestStudentString()
    {
        Student s1=new Student("S123", "Tywin Lannister");
        assertEquals("Student: S123 - Tywin Lannister - ########", s1.toString());
        assertEquals("S123,Tywin Lannister,########\n", s1.toCSVString());
    }
}

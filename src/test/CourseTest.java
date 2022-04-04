package src.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import src.*;
public class CourseTest {
    //why check constructor?
    //assignment requires AT LEAST 100% code coverage :(
    @Test
    public void CheckConstructor()
    {
        Course c=new Course("D111", "Teleport");
        Course c2=new Course("D112", "Mind Control",15);
        //checking include constructor, getter
        assertEquals("D111", c.getId());
        assertEquals("D112", c2.getId());
        assertEquals("Teleport", c.getName());
        assertEquals(15, c2.getCredit());
        assertFalse(c.getId()=="D001");
    }
    //check add sem
    @Test
    public void CheckAddSem()
    {
        Course c=new Course("D111", "Teleport")
        .addSemester("2019B")
        .addSemester("2020C");
        //check
        assertEquals("2019B", c.getSem().get(0));
        assertEquals("2020C", c.getSem().get(1));
        assertFalse(c.getSem().get(0)=="1010");
    }
    //check if a sem exist in the course
    @Test 
    public void CheckSemInCourse()
    {
        Course c=new Course("D111", "Teleport")
        .addSemester("2019B")
        .addSemester("2020C")
        .addSemester("1919");
        //check
        assertTrue(c.checkSem("2019B"));//this sem is contained in c
        assertFalse( c.checkSem("3333"));// this sem is not contained in c
    }
    @Test
    public void CheckCourseString()
    {
        Course c=new Course("D111", "Teleport");
        assertEquals("Course: D111 - Teleport", c.toString());
        assertEquals("D111,Teleport,0\n", c.toCSVString());
    }
}

public class Main {
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
       //Create Course
       Course[] courseList=new Course[14];
       courseList[0]=new Course("D001","Adapted Thaumatergy");
       courseList[1]=new Course("D002","Celestial Witchery",22);
       courseList[2]=new Course("D003","Black Herbs",33);
       courseList[3]=new Course("D004","Demonic Animation");
       courseList[4]=new Course("D005","Rudimentary Necromancy",22);
       courseList[5]=new Course("D006","Dwarven Studies");
       courseList[6]=new Course("D007","Unholy Occultism",55);
       courseList[7]=new Course("D008","White Enchantment");
       courseList[8]=new Course("D009","Lycantthropic Exorsism",22);
       courseList[9]=new Course("D010","Inappropriate Summoning",22);
       courseList[10]=new Course("D011","Forbidden Artifacts");
       courseList[11]=new Course("D012","Progressive Writings",33);
       courseList[12]=new Course("D013","Effective Symbolism");
       courseList[13]=new Course("D013","Contemporary Magic",11);
       //Create Semester
       String[] semList=new String[6]; 
       semList[0]="282AoC";//The Age of chaos
       semList[1]="392SA";//The Sorcery Ages
       semList[2]="123EoT";//The Era of Trust
       semList[3]="82AoT";//The Aeon of Titans
       semList[4]="99EoD";//The Era of Darkness
       semList[3]="281AoC";//The Age of chaos
       //
   } 
}

import org.joda.time.DateTime;
import java.util.ArrayList;

public class MainDriver {

	static Student student1, student2, student3, student4, student5;
	static ArrayList<Student> listOfStudents;
	static ArrayList<CourseProgramme> courses;
	static ArrayList<Module> modules;
	static Module mod1, mod2, mod3,mod4;
	static CourseProgramme course1,course2;
	
	public static void main(String args[]) {
			
		// students
		student1 = new Student("Aparna Pandey", 21, "23/01/1999", 12345678 );
		student2 = new Student("Evan Matthews", 17, "12/03/2003", 19999999);
		student3 = new Student("Jhon Doe", 24, "22/33/1993", 87654321);
		student4 = new Student("Patrick Green", 20, "33/33/2002", 101010101);
		student5 = new Student("Bob Dylan", 29, "01/11/1988", 92929292);
		
		//list of students (ALL)
		listOfStudents = new ArrayList<Student>();
		listOfStudents.add(student1);
		listOfStudents.add(student2);
		listOfStudents.add(student3);
		listOfStudents.add(student4);
		listOfStudents.add(student5);
			
		//Modules
		mod1 = new Module("Software Engineering 3", "CT4117");
		mod2 = new Module("Machine Learning", "CT4101");
		mod3 = new Module("Advanced Maths", "MT301");
		mod4 = new Module("Euclidiean Geometry", "EG231");
		
		//list of modules (ALL)
		modules = new ArrayList<Module>();
		modules.add(mod1);
		modules.add(mod2);
		modules.add(mod3);
		modules.add(mod4);

		//start & end dates
		DateTime startDate1, endDate1, startDate2,endDate2;
		
		startDate1 = new DateTime("2020-02-12");
		endDate1 = new DateTime("2020-12-18");
		
		startDate2 = new DateTime("2021-12-12");
		endDate2 = new DateTime("2022-1-10");
						    
		//Courses 
		course1 = new CourseProgramme("4BCT", startDate1, endDate1);
		course2 = new CourseProgramme("2MAC", startDate2, endDate2);
		
		//list of courses (ALL)
		courses = new ArrayList<CourseProgramme>();
		courses.add(course1);
		courses.add(course2);
		
		//add students to modules and vice-versa
		addStudentModuleRegistration();
		
		
		//Add Modules & Students to Course(s)
		addCourseRegistration();
		
		
		printOverview(courses);
		
		printCourseList(courses);
		
		printStudentDetails(listOfStudents);

	}
	
	public static void addStudentModuleRegistration() {
		
		//(MODULE 1)
		student1.addModuleRegistered(mod1);
        student2.addModuleRegistered(mod1);
        student3.addModuleRegistered(mod1);
        student4.addModuleRegistered(mod1);
        mod1.addStudent(student1);
        mod1.addStudent(student2);
        mod1.addStudent(student3);
        mod1.addStudent(student4);
        
        //(MODULE 2)  
        student1.addModuleRegistered(mod2);
        student5.addModuleRegistered(mod2);
        mod2.addStudent(student1);
        mod2.addStudent(student5);
        
        //(MODULE 3)
        student2.addModuleRegistered(mod3);
        student3.addModuleRegistered(mod3);
        student5.addModuleRegistered(mod3);
        mod3.addStudent(student2);
        mod3.addStudent(student3);
        mod3.addStudent(student5);
        
        //(MODULE 4)
        student2.addModuleRegistered(mod4);
        mod4.addStudent(student2);	
	}
	
	public static void addCourseRegistration() {
		
        //(COURSE 1) 
        course1.setlistOfStudentsEnrolled(listOfStudents); //all students in this course
        course1.addModule(mod1);
        course1.addModule(mod2);
        for (Student s: listOfStudents) {
            s.addCourseRegistered(course1);
        }
        mod1.addAssociatedCourse(course1);
        mod2.addAssociatedCourse(course1);
        
        //(COURSE 2) 
        course2.addStudent(student2);
        course2.addStudent(student3);
        course2.addStudent(student5);
        course2.addModule(mod3);
        course2.addModule(mod4);
        student2.addCourseRegistered(course2);
        student3.addCourseRegistered(course2);
        student5.addCourseRegistered(course2);
        mod3.addAssociatedCourse(course2);
        mod4.addAssociatedCourse(course2);		

	}

	public static void printOverview(ArrayList<CourseProgramme> courses) {
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\tStudent Registration System Overview: 2 courses, 4 modules, 5 Students ");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────\n");	    
	    for(CourseProgramme c: courses) {
	    	System.out.println(c + "\n"); 
	    }
	}
	
	public static void printCourseList(ArrayList<CourseProgramme> courses) {
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\tCourse List & its Details: 2 courses");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────\n");	    
		String output="";
		for(CourseProgramme c: courses) {
			output += "Course Name: " + c.getCourseName()+ "\tStart Date: "+ c.getStartDate().toString("yyyy-MM-dd")+ "\tEndDate: "+ c.getEndDate().toString("yyyy-MM-dd") +  "\nModules in Course: \n";
			for(Module m: c.getlistOfModules()) {
				output+= "-"+ m.getModuleName()+  " or " + m.getModuleID() + "\n";
			}
			output += "\n\n";
		}
		System.out.println(output);
	}
	
	public static void printStudentDetails(ArrayList<Student> listOfStudents) {
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\tStudent Information: Username, Modules and Course(s):");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────\n");
		String output="";
		for(Student s: listOfStudents) {
			output += "Name: " + s.getStudentName()+ "\tUsername: "+ s.getUserName()+ "\tStudent ID: "+ s.getStudentID() +  "\nModules Registered: \n";
			for(Module m: s.getModulesRegistered()) {
				output+= "-"+ m.getModuleName()+  " or " + m.getModuleID() + "\n";
			}
			output += "\n\n";
		}
		System.out.println(output);
	}
}

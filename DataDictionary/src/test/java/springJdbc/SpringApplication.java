package springJdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        StudentJDBCTemplate studentJDBCTemplate =
                (StudentJDBCTemplate)context.getBean("studentJDBCTemplate");
        callTest(studentJDBCTemplate);
//        jdbcTest(studentJDBCTemplate);
    }

    private static void jdbcTest(StudentJDBCTemplate studentJDBCTemplate) {
        System.out.println("------Records Creation--------" );
        studentJDBCTemplate.create("Zara", 11);
        studentJDBCTemplate.create("Nuha", 2);
        studentJDBCTemplate.create("Ayan", 15);
        System.out.println("------Listing Multiple Records--------" );
        List<Student> students = studentJDBCTemplate.listStudents();
        for (Student record : students) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.println(", Age : " + record.getAge());
        }
        System.out.println("----Updating Record with ID = 2 -----" );
        studentJDBCTemplate.update(2, 20);
        System.out.println("----Listing Record with ID = 2 -----" );
        Student student = studentJDBCTemplate.getStudent(2);
        System.out.print("ID : " + student.getId() );
        System.out.print(", Name : " + student.getName() );
        System.out.println(", Age : " + student.getAge());
    }

    private static void callTest(StudentJDBCTemplate studentJDBCTemplate) {
        Student student = studentJDBCTemplate.getStudentByProcedure(1);
        System.out.print("ID : " + student.getId() );
        System.out.print(", Name : " + student.getName() );
        System.out.println(", Age : " + student.getAge());
    }
}

package springJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentJDBCTemplate {
   @Autowired
   private DriverManagerDataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   private SimpleJdbcCall jdbcCall;
   public void setDataSource(DriverManagerDataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
      setDataSource2(dataSource);
   }
   public void setDataSource2(DriverManagerDataSource dataSource) {
      this.jdbcCall =  new SimpleJdbcCall(dataSource).
              withProcedureName("Student");
   }
   public void create(String name, Integer age) {
      String SQL = "insert into Student (name, age) values (?, ?)";     
      jdbcTemplateObject.update( SQL, name, age);
      System.out.println("Created Record Name = " + name + " Age = " + age);
      return;
   }
   public Student getStudent(Integer id) {
      String SQL = "select * from Student where id = ?";
      Student student = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{id}, new StudentMapper());
      return student;
   }
   public Student getStudentByProcedure(Integer id) {
      Map<String, Object> in = new HashMap<>();
      in.put("in_id",id);
      Map<String, Object> out = jdbcCall.execute(in);
      Student student = new Student();
      student.setAge((Integer) out.get("out_age"));
      student.setName((String) out.get("out_name"));
      return student;
   }
   public List<Student> listStudents() {
      String SQL = "select * from Student";
      List <Student> students = jdbcTemplateObject.query(SQL, 
                                new StudentMapper());
      return students;
   }
   public void delete(Integer id){
      String SQL = "delete from Student where id = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with ID = " + id );
      return;
   }
   public void update(Integer id, Integer age){
      String SQL = "update Student set age = ? where id = ?";
      jdbcTemplateObject.update(SQL, age, id);
      System.out.println("Updated Record with ID = " + id );
      return;
   }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateJDBC {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://43.254.45.15:3306/test", "root", "RuQu123+");
        String sql = "select * from `4e0287f3545c493ca9d7d5507cc06377_elem_elemparam_map` limit 10;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+"--"+resultSet.getString(2));
        }
    }
}

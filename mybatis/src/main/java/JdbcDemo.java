import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @ClassName JdbcDemo
 * @Description jdbc问题总结
 * @Author liyulong
 * @Date 2020/12/15 18:21
 * @Version 1.0
 **/
public class JdbcDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet set = null;
        try {
            // 1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.创建数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yingyong?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "root");
            // 3.定义sql语句
            String sql = "select * from user where id = ?";
            // 4.创建statement对象
            pstm = conn.prepareStatement(sql);
            // 5.设置参数
            pstm.setInt(1, 1);
            // 6.执行
            set = pstm.executeQuery();
            // 7.处理结果集
            while (set.next()) {
                System.out.println("id:" + set.getInt("id") + ",名称:" + set.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8.释放资源
            try {
                if (set != null) set.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

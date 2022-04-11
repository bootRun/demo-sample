package com.demo.sample;

import java.sql.*;

public class DbTest {

    /**
     * 使用原生态的jdbc进行数据库连接。
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        //Connection 接口，与特定数据库的连接。
        Connection connection=null;
        //预编译对象
        PreparedStatement preparedStatement=null;
        //结果集
        ResultSet resultSet=null;
        try {
            //加载驱动：这里加载的是mysql驱动。mysql的驱动是：com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");

            connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo_sample?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true",
                    "root", "root");
            //获取PreparedStatement对象：
            String sql="SELECT * from test_user where address=?";
            preparedStatement=connection.prepareStatement(sql);
            //绑定参数：
            preparedStatement.setString(1, "北京市");
            //执行查询，查询出结果。
            resultSet=preparedStatement.executeQuery();


            connection.commit();

            while(resultSet.next()){
                System.out.println("用户名是："+resultSet.getString("username")+"\n"+"性别是："+resultSet.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            /**
             * 关闭数据库连接。
             * 关闭的顺序是：ResultSet,PreparedStatement,Connection.
             */
            if (resultSet!=null) {

                resultSet.close();//关闭结果集
            }
            if (preparedStatement!=null) {

                preparedStatement.close();//关闭预编译对象
            }
            if (connection!=null) {

                connection.close();//关闭连接。
            }

        }
    }


}

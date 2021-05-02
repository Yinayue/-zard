package com.example.demo.util.search;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SqlConnection {

    private static final String URL = Constants.URL;
    private static final String NAME = Constants.NAME;
    private static final String PASSWORD = Constants.PASSWORD;
    Connection conn = null;

    public void TheSqlConnection(){
        //加载驱动
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("未能成功加载驱动程序,请检查是否导入驱动程序!");
            e.printStackTrace();
        }

        try{
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("获取数据库链接成功");

        }catch (SQLException e){
            System.out.println("获取数据库连接失败");
            e.printStackTrace();
        }


    }

    public List<HouseBean> selectAll(){
        List<HouseBean> houseBeanList = new ArrayList<HouseBean>();

        try{
            ResultSet rs = null;
            Statement stmt = null;
            Connection connection = conn;

            String sql ="select * FROM housesen";

            stmt =connection.createStatement();
            rs=stmt.executeQuery(sql);

            //这里只保留要检索的部分就行
            while (rs.next()){
                String address = rs.getString(7);
                //这里这里1
//                String remark = rs.getString();
//                HouseBean houseBean = new HouseBean(address,remark);
                HouseBean houseBean = new HouseBean(address);
                houseBeanList.add(houseBean);
            }
//            while(rs.next()) {
//                //这里只保留要检索的部分就行
//                int id = rs.getInt(1);
//                int seller_id = rs.getInt(2);
//                Timestamp launch_date = rs.getTimestamp(3);
//                String address = rs.getString(4);
//                double size = rs.getDouble(5);
//                BigDecimal price = rs.getBigDecimal(6);
//                int has_been_sold = rs.getInt(7);
//                int current_holder_id = rs.getInt(8);
//                int second_hand = rs.getInt(9);
//                String rooms_type = rs.getString(10);
//                int floor = rs.getInt(11);
//                int age = rs.getInt(12);
//                int decoration = rs.getInt(13);
//                int house_type = rs.getInt(14);
//                int property = rs.getInt(15);
//                int face = rs.getInt(16);
//                String remarks = rs.getString(17);
//                int delete_flag = rs.getInt(18);
//
//                if (remarks == null) {
//                    remarks = "";
////                    System.out.println(remarks + " " + remarks.getClass().toString());
//                }
//
//                HouseBean houseBean = new HouseBean(id,seller_id,launch_date,address,size,price,
//                        has_been_sold,current_holder_id,second_hand,rooms_type,floor,age,decoration,house_type,property,
//                        face,remarks,delete_flag);
//
//                houseBeanList.add(houseBean);
//
////                System.out.println(houseBean.toString());
//
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //数据库打开后要关闭
        if(conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
                conn = null;
            }
        }
            return houseBeanList;

    }


}

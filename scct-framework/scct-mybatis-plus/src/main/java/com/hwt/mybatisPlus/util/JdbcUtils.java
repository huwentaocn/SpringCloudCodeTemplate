package com.hwt.mybatisPlus.util;

import com.baomidou.mybatisplus.annotation.DbType;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;

/**
 * @Description JDBC工具类
 * @Date 2023/11/8 15:51 星期三
 * @Author Hu Wentao
 */
public class JdbcUtils {

    /**
     * 判断连接是否正确
     *
     * @param url      数据源连接
     * @param username 账号
     * @param password 密码
     * @return 是否正确
     */
    public static boolean isConnectionOK(String url, String username, String password) {
        try (Connection ignored = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 获得 URL 对应的 DB 类型
     *
     * @param url URL
     * @return DB 类型
     */
    public static DbType getDbType(String url) {
        String name = com.alibaba.druid.util.JdbcUtils.getDbType(url, null);
        return DbType.getDbType(name);
    }

    /**
     * 执行sql文件
     *
     * @param inputStream
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static boolean executeSqlFile(InputStream inputStream, String url, String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder sqlStatements = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                // 忽略注释行和空行
                if (!line.startsWith("--") && !line.isEmpty()) {
                    sqlStatements.append(line.trim());

                    // 如果语句以分号结尾，则执行该语句
                    if (line.trim().endsWith(";")) {
                        String sql = sqlStatements.toString();
                        statement.executeUpdate(sql);
                        sqlStatements.setLength(0);  // 清空语句缓冲区
                    }
                }
            }

            // 最后执行还没以分号结尾的语句
            if (sqlStatements.length() > 0) {
                String sql = sqlStatements.toString();
                statement.executeUpdate(sql);
            }

            // 关闭Statement和Connection对象
            statement.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 执行sql
     *
     * @param url
     * @param username
     * @param password
     * @param sql
     * @return
     */
    public static boolean executeSql(String url, String username, String password, String sql) {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 创建数据库
     *
     * @param url
     * @param username
     * @param password
     * @param dbName
     * @return
     */
    public static boolean createDatabase(String url, String username, String password, String dbName) {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String sql = "CREATE DATABASE `" + dbName + "`";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检测数据库是否为空
     *
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static boolean isDatabaseEmpty(String url, String username, String password) {
        boolean isEmpty = false;

        try {
            // 创建数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);

            // 创建数据库元数据对象
            DatabaseMetaData metaData = connection.getMetaData();

            // 获取数据库中的表的数量
            ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
            int tableCount = 0;
            while (resultSet.next()) {
                tableCount++;
                break; // 只需检查是否存在表，不需遍历所有表
            }

            // 判断是否为空库
            if (tableCount == 0) {
                isEmpty = true;
            }

            // 关闭连接
            connection.close();
        } catch (SQLException e) {
            System.out.println("发生数据库错误：" + e.getMessage());
        }
        return isEmpty;
    }


    public static void main(String[] args) {
//        String url = "jdbc:mysql://127.0.0.1:3306/test1?allowMultiQueries=true&useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true&nullCatalogMeansCurrent=true";
        String url = "jdbc:mysql://127.0.0.1:3306/wx_cloud?useUnicode=true&characterEncoding=UTF-8&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true";
        boolean root = isDatabaseEmpty(url, "root", "123456");
        System.out.println(root);
    }

}


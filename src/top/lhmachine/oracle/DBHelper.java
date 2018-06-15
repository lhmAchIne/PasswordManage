package top.lhmachine.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import top.lhmachine.login.PassWord;

public class DBHelper{
	// 定义连接所需的字符串
    private static String USERNAMR = "scott";
    private static String PASSWORD = "tiger";
    private static String DRVIER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    
    private static String CREATE_TABLE = "CREATE TABLE ps_manage("
    		+ "webname varchar(20) primary key not null,"
    		+ "username varchar(20) not null,"
    		+ "userpass varchar(20) not null)";
    
    Connection connection = null;			// 创建一个数据库连接
    PreparedStatement pstm = null;			// 创建预编译语句对象，一般都是用这个而不用Statement
    ResultSet rs = null;					// 创建一个结果集对象
    
    /**
     * 获取Connection对象
     */
    private Connection getConnection() {
        try {
            Class.forName(DRVIER);
            connection = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
            System.out.println("成功连接数据库");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    /**
     * 释放资源
     */
    private void ReleaseResource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 构造器，如果表不存在，则创建
     */
    public DBHelper() {
    	connection = getConnection();
        String sql = "SELECT count(*) FROM User_Tables WHERE table_name='PS_MANAGE'";
        int exist_symbol = 0;
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                exist_symbol = rs.getInt(1);
            }
            if (exist_symbol == 0) {
            	System.out.println("数据表不存在，开始创建...");
            	pstm.execute(CREATE_TABLE);
            	System.out.println("创建成功...");
            }else {
            	System.out.println("已存在数据表!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
    
    /**
     * 查询全部数据
     */
    public List<PassWord> selectData() {
    	connection = getConnection();
        String sql = "select * from ps_manage";
        List<PassWord> list = new ArrayList<>();
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String webname = rs.getString("webname");
                String username = rs.getString("username");
                String userpass = rs.getString("userpass");
                PassWord passWord = new PassWord(webname, username, userpass);
                list.add(passWord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return list;
    }

    /**
     * 查询指定数据
     */
    public PassWord selectData(String webname) {
    	connection = getConnection();
        String sql = "select * from ps_manage where webname = '"+webname+"'";
        PassWord passWord = null;
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String userpass = rs.getString("userpass");
                passWord = new PassWord(webname, username, userpass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return passWord;
    }

    /**
     * 删除数据
     */
    public Boolean deleteData(String webname) {
    	Boolean result = false;
    	connection = getConnection();
        String sql = "delete from ps_manage where webname = '"+webname+"'";
        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return result;
    }

    /**
     * 修改数据
     */
    public Boolean updateData(String webname, String username, String userpass) {
    	Boolean result = false;
    	connection = getConnection();
        String sql = "update ps_manage SET userpass = '"+userpass+"' where webname = '"+webname+"' AND username = '"+username+"'";
        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return result;
	}

    /**
     * 增加数据
     */
    public Boolean insertData(PassWord passWord) {
    	Boolean result = false;
    	connection = getConnection();
        String sql = "insert into ps_manage (webname, username, userpass) VALUES ('"+passWord.getWebName()+"', '"+passWord.getUserName()+"', '"+passWord.getPassWord()+"')";
        try {
            pstm = connection.prepareStatement(sql);
            pstm.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return result;
    }
}
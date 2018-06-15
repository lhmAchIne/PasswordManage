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
	// ��������������ַ���
    private static String USERNAMR = "scott";
    private static String PASSWORD = "tiger";
    private static String DRVIER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    
    private static String CREATE_TABLE = "CREATE TABLE ps_manage("
    		+ "webname varchar(20) primary key not null,"
    		+ "username varchar(20) not null,"
    		+ "userpass varchar(20) not null)";
    
    Connection connection = null;			// ����һ�����ݿ�����
    PreparedStatement pstm = null;			// ����Ԥ����������һ�㶼�������������Statement
    ResultSet rs = null;					// ����һ�����������
    
    /**
     * ��ȡConnection����
     */
    private Connection getConnection() {
        try {
            Class.forName(DRVIER);
            connection = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
            System.out.println("�ɹ��������ݿ�");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    /**
     * �ͷ���Դ
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
     * ����������������ڣ��򴴽�
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
            	System.out.println("���ݱ����ڣ���ʼ����...");
            	pstm.execute(CREATE_TABLE);
            	System.out.println("�����ɹ�...");
            }else {
            	System.out.println("�Ѵ������ݱ�!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
    
    /**
     * ��ѯȫ������
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
     * ��ѯָ������
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
     * ɾ������
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
     * �޸�����
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
     * ��������
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
package top.lhmachine.main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import top.lhmachine.oracle.DBHelper;

public class SelectAll extends JFrame implements ActionListener{
	/**
	 * @author lhmachine
	 * 修改密码
	 */
	
	private JLabel jl1, jl2, jl3 = null;
	private JList<String> jList1, jList2, jList3 = null;
	private DBHelper dbHelper = new DBHelper();
	private JButton jb_back, jb_finish = null;
	private JPanel jp1, jp2, jp3 = null;
	
	public SelectAll() {
		jl1 = new JLabel("网站名");
		jl2 = new JLabel("用户名");
		jl3 = new JLabel("密码");
		jList1 = new JList<String>();
		jList2 = new JList<String>();
		jList3 = new JList<String>();
		jb_back = new JButton("返回目录");
		jb_finish = new JButton("退出系统");
		
		//增加按钮监听
		jb_back.addActionListener(this);
		jb_finish.addActionListener(this);
		
		jp1 = new JPanel(new GridLayout(1, 3));
		jp2 = new JPanel(new GridLayout(1, 3));
		jp3 = new JPanel();
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp2.add(jList1);
		jp2.add(jList2);
		jp2.add(jList3);
		jp3.add(jb_back);
		jp3.add(jb_finish);
		
		//设置主面板
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setLayout(new GridLayout(4, 1));
		this.setTitle("网站密码管理系统");          
		this.setSize(400,300);         
        this.setLocation(400, 300);          
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
        
        List<PassWord> list = new ArrayList<>();
        list = dbHelper.selectData();
        if (list.size() == 0) {
        	jList1.setListData(new String[] {"暂无数据"});
        	jList2.setListData(new String[] {"暂无数据"});
        	jList3.setListData(new String[] {"暂无数据"});
        }else {
        	String[] webname = new String[list.size()];
        	String[] username = new String[list.size()];
        	String[] userpass = new String[list.size()];
        	for (int i=0; i<list.size(); i++) {
        		webname[i] = list.get(i).getWebName();
        		username[i] = list.get(i).getUserName();
        		userpass[i] = list.get(i).getPassWord();
        	}
        	jList1.setListData(webname);
        	jList2.setListData(username);
        	jList3.setListData(userpass);
        }
	}
	
	public void actionPerformed(ActionEvent e) {
		//监听事件回调函数
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "返回目录") {
			//返回目录
			dispose();
			MainMenu mainMenu = new MainMenu();	
		}else {
			//退出系统
			dispose();
		}
	}
	
}
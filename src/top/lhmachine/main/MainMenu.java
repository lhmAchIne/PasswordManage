package top.lhmachine.main;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainMenu extends JFrame implements ActionListener{
	/**
	 * @function 主界面
	 * @author lhmachine
	 * @date 2018-05-11
	 */
	
	//声明控件
	private JButton jb_select, jb_insert, jb_update, jb_delete, jb_finish, jb_all = null;
	private JLabel jl1 = null;
	private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7 = null;
	
	private ArrayList<PassWord> list = new ArrayList<>();	//存储列表
	private Boolean data_symbol = false;					//数据存在标志
	
	public MainMenu() {
		//创建按钮
		jb_insert = new JButton("增加密码");
		jb_select = new JButton("查询密码");
		jb_all = new JButton("查询全部");
		jb_update = new JButton("修改密码");
		jb_delete = new JButton("删除密码");
		jb_finish = new JButton("退出系统");
		
		//设置按钮监听
		jb_insert.addActionListener(this);
		jb_select.addActionListener(this);
		jb_all.addActionListener(this);
		jb_finish.addActionListener(this);
		jb_update.addActionListener(this);
		jb_delete.addActionListener(this);
		
		jl1 = new JLabel("功能菜单");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
        
        jp1.add(jl1);
        jp2.add(jb_select);
        jp3.add(jb_all);
        jp4.add(jb_insert);
        jp5.add(jb_update);
        jp6.add(jb_delete);
        jp7.add(jb_finish);

		//设置主面板
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		
		this.setLayout(new GridLayout(7, 1));
		this.setTitle("网站密码管理系统");          
		this.setSize(300,300);         
        this.setLocation(300, 300);          
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
        
        
	}
	
	public void actionPerformed(ActionEvent e) {
		//监听事件回调函数
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "查询密码") {
			dispose();
			SelectPassword selectPassword = new SelectPassword();	
		}else if (e.getActionCommand() == "查询全部"){
			dispose();
			SelectAll selectAll = new SelectAll();
		}else if (e.getActionCommand() == "增加密码"){
			dispose();
			InsertPassword insertPassword = new InsertPassword();
		}else if (e.getActionCommand() == "修改密码"){
			dispose();
			UpdatePassword updatePassword = new UpdatePassword();
		}else if (e.getActionCommand() == "删除密码"){
			dispose();
			DeletePassword deletePassword = new DeletePassword();
		}else {
			dispose();
		}
	}
	
	
}
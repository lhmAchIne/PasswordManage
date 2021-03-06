package top.lhmachine.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import top.lhmachine.main.MainMenu;

public class PasswordManage extends JFrame implements ActionListener{
	/**
	 * @function: 实现登录界面
	 * @author: lhmachine
	 * @date: 2018-05-11
	 */
	
	//定义组件
	JButton jb_login, jb_back = null;
	JLabel jl_title, jl_pass = null;
	JPanel jp1,jp2, jp3 = null;
	JPasswordField jf_pass = null;
	
	public PasswordManage(){
		//创建组件
		jb_login = new JButton("登录");
		jb_back = new JButton("返回");
        jf_pass = new JPasswordField(10); 
		
		//设置监听
		jb_login.addActionListener(this);
		jb_back.addActionListener(this);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jl_title = new JLabel("欢迎进入网站密码管理系统");
		jl_pass = new JLabel("使用密码: ");
		
		jp1.add(jl_title);
		jp2.add(jl_pass);
		jp2.add(jf_pass);
		jp3.add(jb_login);
		jp3.add(jb_back);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setLayout(new GridLayout(3,1));
		this.setTitle("网站密码管理系统");          
        this.setSize(300,200);         
        this.setLocation(400, 200);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
	}
	
	public static void main(String[] args) {
		PasswordManage login = new PasswordManage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "登录") {
			login();
		}else {
			dispose();
		}
	}
	
	public void login() {
		if (jf_pass.getPassword() == null) {
			JOptionPane.showMessageDialog(null, "请输入使用密码!", "提示消息", JOptionPane.ERROR_MESSAGE);
		}else if(String.valueOf(jf_pass.getPassword()).equals("admin")) {
			dispose();
			MainMenu main = new MainMenu();
		}else {
			JOptionPane.showMessageDialog(null, "密码错误!", "提示消息", JOptionPane.ERROR_MESSAGE);
		}
	}
}
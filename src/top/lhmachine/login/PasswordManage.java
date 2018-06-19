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
	 * @function: ʵ�ֵ�¼����
	 * @author: lhmachine
	 * @date: 2018-05-11
	 */
	
	//�������
	JButton jb_login, jb_back = null;
	JLabel jl_title, jl_pass = null;
	JPanel jp1,jp2, jp3 = null;
	JPasswordField jf_pass = null;
	
	public PasswordManage(){
		//�������
		jb_login = new JButton("��¼");
		jb_back = new JButton("����");
        jf_pass = new JPasswordField(10); 
		
		//���ü���
		jb_login.addActionListener(this);
		jb_back.addActionListener(this);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jl_title = new JLabel("��ӭ������վ�������ϵͳ");
		jl_pass = new JLabel("ʹ������: ");
		
		jp1.add(jl_title);
		jp2.add(jl_pass);
		jp2.add(jf_pass);
		jp3.add(jb_login);
		jp3.add(jb_back);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setLayout(new GridLayout(3,1));
		this.setTitle("��վ�������ϵͳ");          
        this.setSize(300,200);         
        this.setLocation(400, 200);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
	}
	
	public static void main(String[] args) {
		PasswordManage login = new PasswordManage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "��¼") {
			login();
		}else {
			dispose();
		}
	}
	
	public void login() {
		if (jf_pass.getPassword() == null) {
			JOptionPane.showMessageDialog(null, "������ʹ������!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
		}else if(String.valueOf(jf_pass.getPassword()).equals("admin")) {
			dispose();
			MainMenu main = new MainMenu();
		}else {
			JOptionPane.showMessageDialog(null, "�������!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
		}
	}
}
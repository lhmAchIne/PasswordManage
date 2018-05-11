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

public class PasswordManage extends JFrame implements ActionListener{
	/**
	 * Function: ʵ�ֵ�¼����
	 * author: lhmachine
	 * date: 2018-05-11
	 */
	
	//�������
	JButton jb_login, jb_back = null;
	JLabel jl_user, jl_pass = null;
	JPanel jp1,jp2,jp3 = null;
	JTextField jf_user = null;
	JPasswordField jf_pass = null;
	
	public PasswordManage(){
		//�������
		jb_login = new JButton("��¼");
		jb_back = new JButton("����");
		jf_user = new JTextField(10);  
        jf_pass = new JPasswordField(10); 
		
		//���ü���
		jb_login.addActionListener(this);
		jb_back.addActionListener(this);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jl_user = new JLabel("�û���:");
		jl_pass = new JLabel("��    ��:");
		
		jp1.add(jl_user);
		jp1.add(jf_user);
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
		if (jf_user.getText().isEmpty() || jf_pass.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "�û��������벻����Ϊ��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
		}else if(jf_user.getText().toString().equals("admin") && jf_pass.getText().toString().equals("admin")) {
			dispose();
			MainMenu main = new MainMenu();
		}else {
			JOptionPane.showMessageDialog(null, "�û������������!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
		}
	}
}
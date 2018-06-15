package top.lhmachine.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import top.lhmachine.oracle.DBHelper;

public class InsertPassword extends JFrame implements ActionListener{
	/**
	 * @author lhmachine
	 * @version 1.0
	 */
	
	private JLabel jl1, jl2, jl3 = null;
	private JTextField jtf1, jtf2, jtf3 = null;
	private JPanel jp1, jp2, jp3, jp4 = null;
	private JButton jb1, jb2, jb3;
	private DBHelper dbHelper = new DBHelper();
	
	public InsertPassword() {
		//����label
		jl1 = new JLabel("��վ��:");
		jl2 = new JLabel("�û���:");
		jl3 = new JLabel("��    ��:");
		
		//�������
		jtf1 = new JTextField(20);
		jtf2 = new JTextField(20);
		jtf3 = new JTextField(20);
		
		//���ð�ť
		jb1 = new JButton("��������");
		jb2 = new JButton("����Ŀ¼");
		jb3 = new JButton("�˳�ϵͳ");
		//���ð�ť����
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		
		//����panel
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf1);
		jp2.add(jl2);
		jp2.add(jtf2);
		jp3.add(jl3);
		jp3.add(jtf3);
		jp4.add(jb1);
		jp4.add(jb2);
		jp4.add(jb3);
		
		//���������
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		this.setLayout(new GridLayout(4, 1));
		this.setTitle("��վ�������ϵͳ");          
		this.setSize(400,200);         
	    this.setLocation(400, 200);          
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
	    this.setVisible(true);  
	    this.setResizable(true); 
	}
	
	public void actionPerformed(ActionEvent e) {
		//�����¼��ص�����
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "����Ŀ¼") {
			//����Ŀ¼
			dispose();
			MainMenu mainMenu = new MainMenu();	
		}else if (e.getActionCommand() == "��������"){
			//��������
			if (jtf1.getText().isEmpty() || jtf2.getText().isEmpty() || jtf3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "��վ�����û��������벻����Ϊ��!", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			}else {
				String webname = jtf1.getText().toString();
				PassWord result = dbHelper.selectData(webname);
				if (result == null) {
					PassWord passWord = new PassWord(webname, jtf2.getText().toString(), jtf3.getText().toString());
					if (dbHelper.insertData(passWord) == true) {
						JOptionPane.showMessageDialog(null, "���ӳɹ�!", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "����ʧ��!", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "����վ��������!", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
				}
			}
		}else {
			//�˳�ϵͳ
			dispose();
		}
	}
	
}
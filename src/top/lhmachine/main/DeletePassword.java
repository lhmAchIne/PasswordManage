package top.lhmachine.main;

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

public class DeletePassword extends JFrame implements ActionListener{
	/**
	 * @author lhmachine
	 * ɾ������
	 */
	
	private JTextField jtf1, jtf2, jtf3 = null;
	private JButton jb_select, jb_back, jb_delete, jb_finish = null;
	private JLabel jl1, jl2, jl3 =null;
	private JPanel jp1, jp2, jp3, jp4 = null;
	private DBHelper dbHelper = new DBHelper();
	
	public DeletePassword() {
		/**
		 * ������
		 */
		//���ð�ť
		jb_select = new JButton("��ѯ");
		jb_delete = new JButton("ɾ������");
		jb_back = new JButton("����Ŀ¼");
		jb_finish = new JButton("�˳�ϵͳ");
		
		//���ð�ť����
		jb_select.addActionListener(this);
		jb_back.addActionListener(this);
		jb_finish.addActionListener(this);
		jb_delete.addActionListener(this);
		
		//������Ϣ
		jl1 = new JLabel("��վ��:");
		jl2 = new JLabel("�û���:");
		jl3 = new JLabel("��    ��:");
		jtf1 = new JTextField(14);
		jtf2 = new JTextField(20);
		jtf3 = new JTextField(20);
		
		//����panel
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		//ÿһ��panel���ӿؼ�
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb_select);
		jp2.add(jl2);
		jp2.add(jtf2);
		jp3.add(jl3);
		jp3.add(jtf3);
		jp4.add(jb_delete);
		jp4.add(jb_back);
		jp4.add(jb_finish);
		
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
		}else if (e.getActionCommand() == "��ѯ"){
			//��ѯ����
			if (jtf1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "�������ѯ��վ!", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			}else {
				PassWord result = dbHelper.selectData(jtf1.getText().toString());
				if (result == null) {
					JOptionPane.showMessageDialog(null, "���޸���վ����!", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
				}else {
					jtf2.setText(result.getUserName());
					jtf3.setText(result.getPassWord());
				}
			}
		}else if (e.getActionCommand() == "ɾ������"){
			//ɾ������
			if (jtf1.getText().isEmpty() || jtf2.getText().isEmpty() || jtf3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���Ȳ�ѯ����!", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			}else {
				if (dbHelper.deleteData(jtf1.getText().toString())) {
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�!", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					jtf1.setText("");
					jtf2.setText("");
					jtf3.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��!", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}else {
			//�˳�ϵͳ
			dispose();
		}
	}
	
}
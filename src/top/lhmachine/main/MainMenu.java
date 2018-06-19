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
	 * @function ������
	 * @author lhmachine
	 * @date 2018-05-11
	 */
	
	//�����ؼ�
	private JButton jb_select, jb_insert, jb_update, jb_delete, jb_finish, jb_all = null;
	private JLabel jl1 = null;
	private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7 = null;
	
	private ArrayList<PassWord> list = new ArrayList<>();	//�洢�б�
	private Boolean data_symbol = false;					//���ݴ��ڱ�־
	
	public MainMenu() {
		//������ť
		jb_insert = new JButton("��������");
		jb_select = new JButton("��ѯ����");
		jb_all = new JButton("��ѯȫ��");
		jb_update = new JButton("�޸�����");
		jb_delete = new JButton("ɾ������");
		jb_finish = new JButton("�˳�ϵͳ");
		
		//���ð�ť����
		jb_insert.addActionListener(this);
		jb_select.addActionListener(this);
		jb_all.addActionListener(this);
		jb_finish.addActionListener(this);
		jb_update.addActionListener(this);
		jb_delete.addActionListener(this);
		
		jl1 = new JLabel("���ܲ˵�");
		
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

		//���������
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		
		this.setLayout(new GridLayout(7, 1));
		this.setTitle("��վ�������ϵͳ");          
		this.setSize(300,300);         
        this.setLocation(300, 300);          
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
        
        
	}
	
	public void actionPerformed(ActionEvent e) {
		//�����¼��ص�����
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "��ѯ����") {
			dispose();
			SelectPassword selectPassword = new SelectPassword();	
		}else if (e.getActionCommand() == "��ѯȫ��"){
			dispose();
			SelectAll selectAll = new SelectAll();
		}else if (e.getActionCommand() == "��������"){
			dispose();
			InsertPassword insertPassword = new InsertPassword();
		}else if (e.getActionCommand() == "�޸�����"){
			dispose();
			UpdatePassword updatePassword = new UpdatePassword();
		}else if (e.getActionCommand() == "ɾ������"){
			dispose();
			DeletePassword deletePassword = new DeletePassword();
		}else {
			dispose();
		}
	}
	
	
}
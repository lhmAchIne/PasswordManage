package top.lhmachine.login;
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
	private JButton jb_select, jb_insert, jb_finish = null;
	private JLabel jl1 = null;
	private JPanel jp1, jp2, jp3, jp4 = null;
	
	private ArrayList<PassWord> list = new ArrayList<>();	//�洢�б�
	private Boolean data_symbol = false;					//���ݴ��ڱ�־
	
	public MainMenu() {
		//������ť
		jb_insert = new JButton("��������");
		jb_select = new JButton("��ѯ����");
		jb_finish = new JButton("�˳�ϵͳ");
		
		//���ð�ť����
		jb_insert.addActionListener(this);
		jb_select.addActionListener(this);
		jb_finish.addActionListener(this);
		
		jl1 = new JLabel("���ܲ˵�");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		/*
		//����������
		File file = new File("data.txt");//ָ���ļ�·��
        if (file.exists()) {
        	data_symbol = true;
        	readdata();
        	String[] web = new String[list.size()];
        	for (int i=0; i<list.size(); i++) {
        		web[i] = list.get(i).getWebName();
        	}
        	jlist = new JList(web);
        }else {
        	data_symbol = false;
        	String[] temp = {"��������"};
        	jlist = new JList(temp);
        }
        jlist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (list.size() != 0) {
					jtf1.setText(list.get(jlist.getSelectedIndex()).getWebName());
					jtf2.setText(list.get(jlist.getSelectedIndex()).getUserName());
					jtf3.setText(list.get(jlist.getSelectedIndex()).getPassWord());
				}else {
					JOptionPane.showMessageDialog(MainMenu.this, "��������", "������Ϣ", JOptionPane.ERROR_MESSAGE);
				}
				
			}
    		
    	});
        jp1.add(jlist);*/
        
        jp1.add(jl1);
        jp2.add(jb_select);
        jp3.add(jb_insert);
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
		if(e.getActionCommand() == "��ѯ����") {
			dispose();
			SelectPassword selectPassword = new SelectPassword();	
		}else if (e.getActionCommand() == "��������"){
			dispose();
			InsertPassword insertPassword = new InsertPassword();
		}else {
			dispose();
		}
	}
	
	
}
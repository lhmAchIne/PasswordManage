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
	private JButton jb_add, jb_update, jb_finish = null;
	private JPanel jp1,jp2 = null;
	private JPanel jp2_1, jp2_2, jp2_3, jp2_4 = null;
	private JLabel jl1, jl2, jl3 = null;
	private JTextField jtf1, jtf2, jtf3 = null;
	private JList jlist;
	
	private ArrayList<PassWord> list = new ArrayList<>();	//�洢�б�
	private Boolean data_symbol = false;					//���ݴ��ڱ�־
	
	public MainMenu() {
		//������ť
		jb_add = new JButton("��������");
		jb_update = new JButton("�޸�����");
		jb_finish = new JButton("�˳�ϵͳ");
		
		//���ð�ť����
		jb_add.addActionListener(this);
		jb_update.addActionListener(this);
		jb_finish.addActionListener(this);
		
		//������������
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(1,1));
		jp2 = new JPanel();
		jp2.setLayout(new GridLayout(4,1));
		
		//�Ҳ����
		jl1 = new JLabel("��վ��:");
		jl2 = new JLabel("�û���:");
		jl3 = new JLabel("��    ��:");
		jtf1 = new JTextField(20);
		jtf2 = new JTextField(20);
		jtf3 = new JTextField(20);
		jp2_1 = new JPanel();
		jp2_2 = new JPanel();
		jp2_3 = new JPanel();
		jp2_4 = new JPanel();
		jp2_1.add(jl1);
		jp2_1.add(jtf1);
		jp2_2.add(jl2);
		jp2_2.add(jtf2);
		jp2_3.add(jl3);
		jp2_3.add(jtf3);
		jp2_4.add(jb_add);
		jp2_4.add(jb_update);
		jp2_4.add(jb_finish);
		jp2.add(jp2_1);
		jp2.add(jp2_2);
		jp2.add(jp2_3);
		jp2.add(jp2_4);
		
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
        jp1.add(jlist);
		
		//���������
		this.add(jp1);
		this.add(jp2);
		
		this.setLayout(new GridLayout(1,2));
		this.setTitle("��վ�������ϵͳ");          
        this.setSize(800,400);         
        this.setLocation(400, 200);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
        
        
	}
	
	public void actionPerformed(ActionEvent e) {
		//�����¼��ص�����
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "��������") {
			if (jtf1.getText().isEmpty() || jtf2.getText().isEmpty() || jtf3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "��վ�����û��������벻����Ϊ��", "������Ϣ", JOptionPane.ERROR_MESSAGE);
			}else {
				String webname = jtf1.getText().toString();
				String username = jtf2.getText().toString();
				String password = jtf3.getText().toString();
				PassWord p1 = new PassWord(webname, username, password);
				if (data_symbol == false || isExist(list, p1)==-1) {
					//������������ݻ���������û�и�����
					list.add(p1);
					writedata();
				}else {
					JOptionPane.showMessageDialog(this, "����վ�û����Ѵ���", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		}else if (e.getActionCommand() == "�޸�����"){
			if (jtf1.getText().isEmpty() || jtf2.getText().isEmpty() || jtf3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "���ȶ�ȡ����", "������Ϣ", JOptionPane.ERROR_MESSAGE);
			}else {
				//�ж��Ƿ������ݿ��д��ڵ�����
				String webname = jtf1.getText().toString();
				String username = jtf2.getText().toString();
				String password = jtf3.getText().toString();
				PassWord p1 = new PassWord(webname, username, password);
				if (isExist(list, p1) == -1) {
					JOptionPane.showMessageDialog(this, "�����ݲ����ڣ��������", "������Ϣ", JOptionPane.ERROR_MESSAGE);
				}else {
					//�޸�list
					list.get(isExist(list,p1)).setPassWord(password);
					try {
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"));
						oos.writeObject(list);
						oos.close();
						JOptionPane.showMessageDialog(this, "�޸ĳɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		}else {
			dispose();
		}
	}
	
	public void readdata() {
		/**
		 * ���ļ��ж�ȡ����
		 * @author lhmachine
		 * 2018-05-11
		 */
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt"));
			list = (ArrayList<PassWord>)ois.readObject();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writedata() {
		/**
		 * ���ļ���д������
		 * @author lhmachine
		 * 2018-05-11
		 */
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"));
			oos.writeObject(list);
			oos.close();
			JOptionPane.showMessageDialog(this, "���ӳɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			String[] web = new String[list.size()];
        	for (int i=0; i<list.size(); i++) {
        		web[i] = list.get(i).getWebName();
        	}
        	jlist.setListData(web);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int isExist(ArrayList<PassWord> l, PassWord p) {
		/**
		 * �жϸ���վ���û��������Ƿ����
		 * @author lhmachine
		 * 2018-05-11
		 */
		for (int i=0; i<l.size(); i++) {
			if (l.get(i).getWebName().equals(p.getWebName()) && l.get(i).getUserName().equals(p.getUserName())) {
				return i;
			}
		}
		return -1;
	}
}
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
	 * �޸�����
	 */
	
	private JLabel jl1, jl2, jl3 = null;
	private JList<String> jList1, jList2, jList3 = null;
	private DBHelper dbHelper = new DBHelper();
	private JButton jb_back, jb_finish = null;
	private JPanel jp1, jp2, jp3 = null;
	
	public SelectAll() {
		jl1 = new JLabel("��վ��");
		jl2 = new JLabel("�û���");
		jl3 = new JLabel("����");
		jList1 = new JList<String>();
		jList2 = new JList<String>();
		jList3 = new JList<String>();
		jb_back = new JButton("����Ŀ¼");
		jb_finish = new JButton("�˳�ϵͳ");
		
		//���Ӱ�ť����
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
		
		//���������
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setLayout(new GridLayout(4, 1));
		this.setTitle("��վ�������ϵͳ");          
		this.setSize(400,300);         
        this.setLocation(400, 300);          
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
        
        List<PassWord> list = new ArrayList<>();
        list = dbHelper.selectData();
        if (list.size() == 0) {
        	jList1.setListData(new String[] {"��������"});
        	jList2.setListData(new String[] {"��������"});
        	jList3.setListData(new String[] {"��������"});
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
		//�����¼��ص�����
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "����Ŀ¼") {
			//����Ŀ¼
			dispose();
			MainMenu mainMenu = new MainMenu();	
		}else {
			//�˳�ϵͳ
			dispose();
		}
	}
	
}
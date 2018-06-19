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
	 * 删除密码
	 */
	
	private JTextField jtf1, jtf2, jtf3 = null;
	private JButton jb_select, jb_back, jb_delete, jb_finish = null;
	private JLabel jl1, jl2, jl3 =null;
	private JPanel jp1, jp2, jp3, jp4 = null;
	private DBHelper dbHelper = new DBHelper();
	
	public DeletePassword() {
		/**
		 * 构造器
		 */
		//设置按钮
		jb_select = new JButton("查询");
		jb_delete = new JButton("删除密码");
		jb_back = new JButton("返回目录");
		jb_finish = new JButton("退出系统");
		
		//设置按钮监听
		jb_select.addActionListener(this);
		jb_back.addActionListener(this);
		jb_finish.addActionListener(this);
		jb_delete.addActionListener(this);
		
		//设置信息
		jl1 = new JLabel("网站名:");
		jl2 = new JLabel("用户名:");
		jl3 = new JLabel("密    码:");
		jtf1 = new JTextField(14);
		jtf2 = new JTextField(20);
		jtf3 = new JTextField(20);
		
		//设置panel
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		//每一个panel增加控件
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
		
		//设置主面板
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		this.setLayout(new GridLayout(4, 1));
		this.setTitle("网站密码管理系统");          
		this.setSize(400,200);         
        this.setLocation(400, 200);          
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
	}
	
	public void actionPerformed(ActionEvent e) {
		//监听事件回调函数
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "返回目录") {
			//返回目录
			dispose();
			MainMenu mainMenu = new MainMenu();	
		}else if (e.getActionCommand() == "查询"){
			//查询密码
			if (jtf1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入查询网站!", "提示消息", JOptionPane.WARNING_MESSAGE);
			}else {
				PassWord result = dbHelper.selectData(jtf1.getText().toString());
				if (result == null) {
					JOptionPane.showMessageDialog(null, "暂无该网站数据!", "提示消息", JOptionPane.WARNING_MESSAGE);
				}else {
					jtf2.setText(result.getUserName());
					jtf3.setText(result.getPassWord());
				}
			}
		}else if (e.getActionCommand() == "删除密码"){
			//删除密码
			if (jtf1.getText().isEmpty() || jtf2.getText().isEmpty() || jtf3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请先查询数据!", "提示消息", JOptionPane.WARNING_MESSAGE);
			}else {
				if (dbHelper.deleteData(jtf1.getText().toString())) {
					JOptionPane.showMessageDialog(null, "删除成功!", "提示消息", JOptionPane.INFORMATION_MESSAGE);
					jtf1.setText("");
					jtf2.setText("");
					jtf3.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "删除失败!", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}else {
			//退出系统
			dispose();
		}
	}
	
}
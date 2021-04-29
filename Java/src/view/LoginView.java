package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.peer.KeyboardFocusManagerPeer;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;


import model.CustomerModel;
import model.rec.CustomerVO;
import view.LoginView.LoginEventHandler;

public class LoginView extends JFrame {
	private JButton bRegist, bConfirm, bSearch, bPlayGame;
	private JLabel bg_login;
	private JLabel lbID, lbPW;
	private JPanel pLogin;
	private JTextField tfID, tfPW;
	
	private CustomerModel model;
	
	public LoginView() {
		initComponents();
		setTitle("ToTo");
		ImageIcon img = new ImageIcon("img/logo.png");
		setIconImage(img.getImage());
		setSize(400, 700);
		setLocation(700, 100); 
		setVisible(true);
	}

	private void initComponents() {
		LoginEventHandler evt = new LoginEventHandler();
		Font font = new Font("����ײ�äL", Font.BOLD, 18);
		
		try {
			model = new CustomerModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bg_login = new JLabel();
		pLogin = new JPanel();

		lbID = new JLabel();
		tfID = new JTextField();

		lbPW = new JLabel();
		tfPW = new JTextField();

		bRegist = new JButton();
		bConfirm = new JButton();
		bSearch = new JButton();
		bPlayGame = new JButton();

		setLayout(null);

		Icon icon_login = new ImageIcon("img/bg_login.jpg");
		bg_login.setIcon(icon_login);
		bg_login.setBounds(0, 0, 400, 700);

		pLogin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "LOGIN",
				TitledBorder.CENTER, TitledBorder.TOP, font, Color.WHITE));
		pLogin.setLayout(new GridLayout(0, 2, 10, 20));
		pLogin.setForeground(SystemColor.inactiveCaptionText);
		pLogin.setBackground(new Color(112, 128, 144));

		lbID.setText("���̵�");
		lbID.setFont(font);

		tfID.setFont(font);
		tfID.addFocusListener(evt);

		pLogin.add(lbID);
		pLogin.add(tfID);

		lbPW.setText("��й�ȣ");
		lbPW.setFont(font);

		tfPW.setFont(font);
		tfPW.setText("Password");
		tfPW.addActionListener(evt);
		tfPW.addFocusListener(evt);
		

		pLogin.add(lbPW);
		pLogin.add(tfPW);

		bPlayGame.setIcon(new ImageIcon("img/��������ӹ�ư1.png"));
		//bPlayGame.setText("���� ������");
		bPlayGame.setFont(font);
		bPlayGame.addActionListener(evt);

		bSearch.setIcon(new ImageIcon("img/ȸ������ã���ư.png"));
		//bSearch.setText("ID/PWã��");
		bSearch.setFont(font);
		bSearch.addActionListener(evt);
		
		bRegist.setIcon(new ImageIcon("img/ȸ�����Թ�ư.png"));
		bRegist.setText("ȸ������");
		bRegist.setFont(font);
		bRegist.addActionListener(evt);

		bConfirm.setIcon(new ImageIcon("img/�α��ι�ư.png"));
		//bConfirm.setText("�α���");
		bConfirm.setFont(font);
		bConfirm.addActionListener(evt);

		pLogin.add(bPlayGame);
		pLogin.add(bSearch);
		pLogin.add(bRegist);
		pLogin.add(bConfirm);

		pLogin.setBounds(45, 300, 300, 250);
		pLogin.setOpaque(false);
		add(pLogin);
		add(bg_login);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	class LoginEventHandler implements ActionListener, FocusListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// TODO Auto-generated method stub
				Object obj = e.getSource();
				if (obj == bConfirm || obj == tfPW) {
					if(model.checkLogin(tfID.getText(), tfPW.getText() )){
						setVisible(false);
						new Toto(tfID.getText());
					}else{
						JOptionPane.showMessageDialog(null, "���̵� Ȥ�� ��й�ȣ�� �ٸ��ϴ�.");
					}
				} else if (obj == bRegist) {
					new RegistView();
				} else if (obj == bPlayGame) {
					new SampleGame();
				} else if (obj == bSearch) {
					String name,tel;
					name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���");
					tel = JOptionPane.showInputDialog("����ó�� �Է��ϼ���");
					model.searchID(name,tel);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();

			if (obj == tfPW) {
				tfPW.setText("");
			}
		}
		@Override
		public void focusLost(FocusEvent e) {
			Object obj = e.getSource();
			if(obj == tfPW){
				tfID.requestFocus();
			}
		}
		

	}

	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new LoginView();

	}
	
	

	class RegistView extends JDialog {
		private JPanel pCustRegist = new JPanel();
		private JLabel lbCustID = new JLabel();
		private JTextField tfCustID = new JTextField();
		private JLabel lbCustPW = new JLabel();
		private JTextField tfCustPW= new JTextField();
		private JLabel lbCustExPW = new JLabel();
		private JTextField tfCustExPW= new JTextField();
		private JLabel lbCustName = new JLabel();
		private JTextField tfCustName= new JTextField();
		private JLabel lbCustTel  = new JLabel();
		private JTextField tfCustTel= new JTextField();
		private JLabel lbCustAdr  = new JLabel();
		private JTextField tfCustAdr= new JTextField();
		
		private JLabel lbCustAcc  = new JLabel();
		private JTextField tfCustAcc= new JTextField();
		
		private JLabel lbBankList  = new JLabel();
		private JComboBox cbBankList = new JComboBox();
		
		private JButton bIDCheck = new JButton();
		private JButton bCustCancel = new JButton();
		private JButton bCustRegist  = new JButton();

		public RegistView() throws SQLException {
			setLayout(null);
			setTitle("ȸ�����");
			Font font = new Font("����ײ�äL", Font.BOLD, 18);
			RegistEventHandler evt = new RegistEventHandler();
			
			pCustRegist.setLayout(new GridLayout(0, 2, 10, 20));
			pCustRegist.setOpaque(false);

			lbCustID.setText("���̵�");
			lbCustID.setFont(font);

			tfCustID.setFont(font);

			pCustRegist.add(lbCustID);
			pCustRegist.add(tfCustID);

			lbCustPW.setText("��й�ȣ");
			lbCustPW.setFont(font);
			
			tfCustPW.setFont(font);
			tfCustPW.setEditable(false);

			pCustRegist.add(lbCustPW);
			pCustRegist.add(tfCustPW);
			
			lbCustExPW.setText("ȯ���� ��й�ȣ");
			lbCustExPW.setFont(font);

			tfCustExPW.setFont(font);
			tfCustExPW.setEditable(false);
			
			pCustRegist.add(lbCustExPW);
			pCustRegist.add(tfCustExPW);

			lbCustName.setText("�̸�");
			lbCustName.setFont(font);

			tfCustName.setFont(font);
			tfCustName.setEditable(false);

			pCustRegist.add(lbCustName);
			pCustRegist.add(tfCustName);

			lbCustTel.setText("����ó");
			lbCustTel.setFont(font);

			tfCustTel.setFont(font);
			tfCustTel.setEditable(false);

			pCustRegist.add(lbCustTel);
			pCustRegist.add(tfCustTel);

			lbCustAdr.setText("�ּ�");
			lbCustAdr.setFont(font);

			tfCustAdr.setFont(font);
			tfCustAdr.setEditable(false);
			
			pCustRegist.add(lbCustAdr);
			pCustRegist.add(tfCustAdr);
			
			lbCustAcc.setText("���¹�ȣ");
			lbCustAcc.setFont(font);
			
			tfCustAcc.setFont(font);
			tfCustAcc.setEditable(false);

			pCustRegist.add(lbCustAcc);
			pCustRegist.add(tfCustAcc);
			

			lbBankList.setText("���༱��");
			lbBankList.setFont(font);
			
			ArrayList list = model.loadBankList();
			cbBankList = new JComboBox();
			for(Object item : list){
				cbBankList.addItem((String)item);
			}
			cbBankList.setFont(font);
			
			
			pCustRegist.add(lbBankList);
			pCustRegist.add(cbBankList);
			

			bCustCancel.setText("�� ��");
			bCustCancel.setFont(font);
			bCustCancel.setBounds(40,470,250,50);
			bCustCancel.addActionListener(evt);
			
			
			add(bCustCancel);

			bCustRegist.setText("�� ��");
			bCustRegist.setFont(font);
			bCustRegist.setBounds(290,470,250,50);
			bCustRegist.addActionListener(evt);

			add(bCustRegist);

			add(pCustRegist);
			pCustRegist.setBounds(40, 30, 500, 400);

			bIDCheck.setFont(font);
			bIDCheck.addActionListener(evt);
			bIDCheck.setText("�ߺ�Ȯ��");
			bIDCheck.setBounds(180,33 , 100 , 30);
			add(bIDCheck);
			
			setSize(600,600);
			setLocation(700,300);
			setModal(false);
			setVisible(true);

		}
		class RegistEventHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object obj = e.getSource();
				if(obj == bCustRegist || obj == tfCustAcc){
					CustomerVO vo = new CustomerVO();
					try {
						
						vo.setBank_code(model.getBankCode((String) cbBankList.getSelectedItem()));
						vo.setCust_accNum(tfCustAcc.getText());
						vo.setCust_adr(tfCustAdr.getText());
						vo.setCust_exPw(tfCustExPW.getText());
						vo.setCust_id(tfCustID.getText());
						vo.setCust_name(tfCustName.getText());
						vo.setCust_pw(tfCustPW.getText());
						vo.setCust_tel(tfCustTel.getText());
						
						model.regist(vo);
						setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (obj == bCustCancel) {
					setVisible(false);
				} else if (obj == bIDCheck) {
					if (!tfCustID.equals("")) {
						try {
							if (model.checkLogin(tfCustID.getText()))
								JOptionPane.showMessageDialog(null, "�����ϴ� ���̵��Դϴ�.");
							else{
								JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.");
								tfCustAcc.setEditable(true);
								tfCustAdr.setEditable(true);
								tfCustExPW.setEditable(true);
								tfCustName.setEditable(true);
								tfCustPW.setEditable(true);
								tfCustTel.setEditable(true);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���");
					}
				}
				
			}
			
		}
	}
	
	
	

}

package view;

import javax.swing.*;

import org.omg.CORBA.CurrentHolder;

import model.CouponModel;
import model.CustomerModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLIntegrityConstraintViolationException;

public class RechargeView extends JDialog {
	private JButton bClear;
	private final int PRICE_BTN_SIZE = 3; 
	private JButton[] bPrice = new JButton[PRICE_BTN_SIZE];
	private JButton bSubmit;
	private JButton bRcByAcc;
	private JButton bRcByPhone;
	private JLabel lbAfter;
	private JLabel lbBefore;
	private JLabel lbInput;
	private JLabel lbRcTitle;
	private JLabel lbTitle;
	private JPanel pButtons;
	private JPanel pInput;
	private JPanel pPrice;
	private JPanel pRcTitle;
	private JPanel pRcType;
	private JPanel pResult;
	private JPanel pSubmit;
	private JPanel pTitle;
	private JTextField tfAfter;
	private JTextField tfBefore;
	private JTextField tfInput;
	private int sumPrice = 0;
	private int myMoney = 0;
	private JTextField tfCoupon;
	private String user_ID;
	private JLabel lbCoupon;

	public RechargeView(String user_id) {
		this.user_ID = user_id;
		initComponents();
		setTitle("충전정보");
		setLocation(650, 280);
		setSize(400,470);
		setVisible(true);
	}

	private void initComponents() {
		RechargeEventHandler evt = new RechargeEventHandler();

		pTitle = new JPanel();
		lbTitle = new JLabel();
		pPrice = new JPanel();
		pInput = new JPanel();
		lbInput = new JLabel();
		tfInput = new JTextField();
		bClear = new JButton();
		pButtons = new JPanel();
		for(int i = 0 ; i < bPrice.length ; i ++){
			bPrice[i] = new JButton( String.format("+%,dP", 100 * (int)Math.pow(10,(i+1)) ));
			bPrice[i].addActionListener(evt);
			pButtons.add(bPrice[i]);
		}
		pResult = new JPanel();
		lbBefore = new JLabel();
		tfBefore = new JTextField();
		lbAfter = new JLabel();
		tfAfter = new JTextField();
		pRcType = new JPanel();
		pRcTitle = new JPanel();
		lbRcTitle = new JLabel();
		bRcByAcc = new JButton();
		bRcByPhone = new JButton();
		pSubmit = new JPanel();
		bSubmit = new JButton();
		tfCoupon = new JTextField();
		lbCoupon = new JLabel();
		

		setLayout(null);

		
		
		pTitle.setBackground(new Color(0, 102, 204));
		pTitle.setForeground(Color.WHITE);
		pTitle.setPreferredSize(new Dimension(400, 50));
		pTitle.setLayout(null);
		
		lbTitle.setFont(new Font("양재붓꽃채L", Font.BOLD, 30));
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setText("충전하기");
		pTitle.add(lbTitle);
		lbTitle.setBounds(130, 5, 144, 42);

		add(pTitle);
		pTitle.setBounds(0, 0, 400, 50);

		pPrice.setBackground(new Color(0, 102, 204));
		pPrice.setBorder(BorderFactory.createEtchedBorder());
		pPrice.setLayout(new GridLayout(0, 1));

		pInput.setBackground(new Color(0, 102, 204));
		pInput.setLayout(null);

		lbInput.setBackground(new Color(0, 102, 204));
		lbInput.setHorizontalAlignment(SwingConstants.RIGHT);
		lbInput.setText("충전할 금액");
		pInput.add(lbInput);
		lbInput.setBounds(20, 0, 80, 40);

		tfInput.setHorizontalAlignment(JTextField.RIGHT);
		tfInput.setText("금액을 입력해주세요");
		tfInput.setBorder(null);
		tfInput.setOpaque(false);
		tfInput.addKeyListener(evt);

		pInput.add(tfInput);
		tfInput.setBounds(80, 0, 230, 40);

		bClear.setText("X");
		pInput.add(bClear);
		bClear.setBounds(320, 5, 30, 30);
		bClear.addActionListener(evt);

		pPrice.add(pInput);

		pButtons.setBackground(new Color(0, 102, 204));
		pButtons.setLayout(new GridLayout(1, 0));

		
	

		pPrice.add(pButtons);

		add(pPrice);
		pPrice.setBounds(20, 60, 360, 80);

		pResult.setBackground(new Color(0, 102, 204));
		pResult.setBorder(BorderFactory.createEtchedBorder());
		pResult.setLayout(null);

		lbBefore.setBackground(new Color(0, 102, 204));
		lbBefore.setHorizontalAlignment(SwingConstants.RIGHT);
		lbBefore.setText("충전 전");
		pResult.add(lbBefore);
		lbBefore.setBounds(0, 0, 60, 40);

		tfBefore.setHorizontalAlignment(JTextField.RIGHT);
		try {
			CustomerModel model = new CustomerModel();
			tfBefore.setText(String.format("%,dP", model.getPoint(user_ID)));
			myMoney = model.getMoney(user_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfBefore.setBorder(null);
		tfBefore.setOpaque(false);
		tfBefore.setEditable(false);

		pResult.add(tfBefore);
		tfBefore.setBounds(60, 0, 120, 40);

		lbAfter.setBackground(new Color(0, 102, 204));
		lbAfter.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAfter.setText("충전 후");
		pResult.add(lbAfter);
		lbAfter.setBounds(180, 0, 60, 40);

		tfAfter.setHorizontalAlignment(JTextField.RIGHT);
		tfAfter.setText("0P");
		tfAfter.setBorder(null);
		tfAfter.setOpaque(false);
		tfAfter.setEditable(false);

		pResult.add(tfAfter);
		tfAfter.setBounds(240, 0, 120, 40);

		add(pResult);
		pResult.setBounds(20, 150, 360, 40);

		pRcType.setBackground(new Color(0, 102, 204));
		pRcType.setBorder(BorderFactory.createEtchedBorder());
		pRcType.setLayout(null);

		pRcTitle.setBackground(new Color(0, 102, 204));
		pRcTitle.setLayout(null);

		lbRcTitle.setFont(new Font("양재붓꽃채L", 0, 24));
		lbRcTitle.setText("결제정보");
		pRcTitle.add(lbRcTitle);
		lbRcTitle.setBounds(40, 5, 96, 29);

		pRcType.add(pRcTitle);
		pRcTitle.setBounds(0, 0, 360, 50);

		bRcByAcc.setText("계좌이체");
		bRcByAcc.addActionListener(evt);
		pRcType.add(bRcByAcc);
		bRcByAcc.setBounds(5, 50, 175, 40);
		bRcByAcc.setEnabled(false);

		bRcByPhone.setText("핸드폰결제");
		bRcByPhone.addActionListener(evt);
		pRcType.add(bRcByPhone);
		bRcByPhone.setBounds(180, 50, 175, 40);
		
		lbCoupon.setBackground(new Color(0, 102, 204));
		lbCoupon.setForeground(Color.WHITE);
		lbCoupon.setFont(new Font("양재붓꽃채L", Font.BOLD, 14));
		lbCoupon.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCoupon.setText("쿠폰번호");
		pRcType.add(lbCoupon);
		lbCoupon.setBounds(5, 100, 60, 40);
		
		
		tfCoupon.setText("쿠폰번호를 입력해주세요");
		tfCoupon.setHorizontalAlignment(JTextField.RIGHT);
		tfCoupon.setFont(new Font("양재붓꽃채L", Font.BOLD, 14));
		tfCoupon.setBackground(new Color(0, 102, 204));
		tfCoupon.setBorder(null);
		tfCoupon.addFocusListener(evt);
		tfCoupon.addKeyListener(evt);
		pRcType.add(tfCoupon);
		tfCoupon.setBounds(90, 100, 250, 40);

		add(pRcType);
		pRcType.setBounds(20, 200, 360, 150);
		
		

		pSubmit.setBackground(new Color(0, 102, 204));
		pSubmit.setLayout(new GridLayout(1, 0));

		bSubmit.setText("+충전하기");
		bSubmit.addActionListener(evt);
		pSubmit.add(bSubmit);

		add(pSubmit);
		pSubmit.setBounds(20, 360, 360, 60);

	}

	class RechargeEventHandler implements ActionListener,KeyListener,FocusListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == bSubmit) {
				if(tfCoupon.getText().length() != 0){
					if( !(tfCoupon.getText().charAt(0) >= '0' && tfCoupon.getText().charAt(0) <= '9')  ){
						tfCoupon.setText("");
					}
				}
				if (sumPrice  == 0)
					JOptionPane.showMessageDialog(null, "충전금액을 선택해주세요");
				else {
					try {
						CustomerModel model = new CustomerModel();
						double bonus = 0;
						if (!tfCoupon.getText().equals("")) {
							CouponModel cModel = new CouponModel();
							bonus = cModel.useCoupon(user_ID, Integer.parseInt(tfCoupon.getText()));
						}
						model.recharge(user_ID, sumPrice, bonus);
						model.getRecharge(user_ID, sumPrice);
						if(!bRcByAcc.isEnabled())
							model.deposit(user_ID,sumPrice);
						else{
							String tel = JOptionPane.showInputDialog("결제할 핸드폰번호를 입력하세요");
							model.rechargeByPhone(tel,sumPrice);
						}

						sumPrice = 0;
						tfInput.setText("0");
						tfBefore.setText(String.format("%,dP", model.getPoint(user_ID)));
						
					} catch (SQLIntegrityConstraintViolationException ex1) {
						JOptionPane.showMessageDialog(null, "보유 잔액이 부족합니다.");
					} catch (Exception ex2) {
						ex2.printStackTrace();
					}
				}
			}else if(obj == bRcByPhone){
				bRcByAcc.setEnabled(true);
				bRcByPhone.setEnabled(false);
			}else if(obj == bRcByAcc){
				bRcByAcc.setEnabled(false);
				bRcByPhone.setEnabled(true);
			}else if(obj == bClear){
				sumPrice = 0;
				tfInput.setText(String.format("%,d", sumPrice));
				tfAfter.setText(tfBefore.getText());
				tfInput.requestFocus();
			}
			for(int i = 0; i < bPrice.length; i++){
				if(obj == bPrice[i]){
					int addPrice = Integer.parseInt( bPrice[i].getText().substring(1, bPrice[i].getText().length()).replaceAll(",","").replaceAll("P","") );
					if(sumPrice + addPrice <= myMoney){
						sumPrice += addPrice;
						tfInput.setText(String.format("%,d", sumPrice));
						tfAfter.setText(String.format("%,dP",
								(Integer.parseInt(tfBefore.getText().replaceAll("P", "").replaceAll(",", ""))
										+ sumPrice)));
						tfInput.requestFocus();
					}else{
						JOptionPane.showMessageDialog(null, "보유 잔액보다 충전금액이 많습니다.");
					}
				}
			}
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == tfInput){
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					sumPrice = 0;
					tfInput.setText("0");
					tfAfter.setText(tfBefore.getText());
				} else if (!(tfInput.getText().charAt(0) >= '0' && tfInput.getText().charAt(0) <= '9')) {
					tfInput.setText("0");
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == tfCoupon){
				if(!(e.getKeyChar() >= '0' && e.getKeyChar() <='9')){
					tfCoupon.setText("");
				}
			}
			else if( !(e.getKeyChar() >= '0' && e.getKeyChar() <='9')){
				tfInput.setText("0");
			}else{
				int addPrice = Integer.parseInt(tfInput.getText().replaceAll(",", ""));
				if (sumPrice + addPrice <= myMoney) {
					sumPrice = addPrice;
					tfInput.setText(String.format("%,d", sumPrice));
					tfAfter.setText(String.format("%,dP",
							(Integer.parseInt(tfBefore.getText().replaceAll("P", "").replaceAll(",", "")) + sumPrice)));
					tfInput.requestFocus();
				} else {
					JOptionPane.showMessageDialog(null, "보유 잔액보다 충전금액이 많습니다.");
					tfInput.setText(tfInput.getText().substring(0,tfInput.getText().length()-1));
				}
				
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == tfCoupon){
				tfCoupon.setText("");
			}
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

	

	}

}


package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.LadderModel;
import model.rec.Ladder_BetVO;

//��ٸ���

public class LadderView extends JFrame implements Runnable {
	private JPanel jpBasket, jpBettingCart, jpLadder_choose, jpTitle, jpChoose1;
	private JTextField tfCust_price, tfBet_price, tfResult_price;
	private JLabel lbTitle, lbDate, lbBeforeBet, lbCustPrice, lbBetPrice, lbResultPrice;
	private JLabel lbBackground, lbCartLabel;
	private JLabel lbLadder1_1, lbLadder1;
	private JLabel lblNewLabel_1, lblNewLabel;
	private JLabel lbRLadder1, lbRLadder2, lbRLadder3, lbRLadder4;
	private JLabel lbLadder2;
	private JLabel lbSoccer1, lbSoccer2, lbSoccer3, lbSoccer4;
	private JButton jbHole, jbZzag, jbLadNum3, jbLadNum4, jbStartLeft, jbStartRight;
	private JButton jbVerse1, jbVerse2, jbVerse3;
	private JButton jbBetting;

	private boolean isStart = false; 
	private double double_bet_price; // ���߱ݾ�
	private int ladder_sw; // ��ٸ� ����ġ
	private int start_point; // ������
	private int x = 290; // ��ٸ� x��ǥ
	private int x2 = 540; // ��ٸ� x2��ǥ
	private int y = 61; // ��ٸ� y��ǥ
	private int result_price = 0; // ���߱ݾ�
	private int count = 0;// ��ư count

	private int int_bet_price, user_price;// ���ñݾ�,������� �����ݾ�
	private int button_result1, button_result2; // ����ư�� �����
	private int ladder_code; //��ٸ��ڵ�
	private String ladder_bet_YN, user_id, str;// ��ٸ����ô�÷����, �����id, �ð�
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	long time = System.currentTimeMillis();
	private Image img = Toolkit.getDefaultToolkit().createImage("");

	private Font fontP14 = new Font("����ײ�äL", Font.PLAIN, 14);
	private Font fontP12 = new Font("����ײ�üL", Font.PLAIN, 12);
	private Font fontB15 = new Font("����ײ�üL", Font.BOLD, 15);
	private Font fontB14 = new Font("����ײ�üL", Font.BOLD, 14);

	private int temp = 0;
	private int buttonCnt = 0;

	
	TitledBorder tb1 = new TitledBorder(new LineBorder(Color.WHITE));

	Ladder_BetVO lbVO;
	LadderModel lm;

	public LadderView(String user_id) {

		this.user_id = user_id;

		getContentPane().setLayout(null);

		JPanel ladder_image = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				if (img != null)
					if (ladder_sw == 1 || ladder_sw == 2)
						g.drawImage(img, x, y, this);
					else
						g.drawImage(img, x2, y, this);
			}
		};

		try {
			LadderModel lm = new LadderModel();

			user_price = lm.user_price(user_id);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageIcon img_logo = new ImageIcon("img/logo.png");
		setIconImage(img_logo.getImage());

		// Jpanel
		jpBasket = new JPanel();
		jpBettingCart = new JPanel();
		jpLadder_choose = new JPanel();
		jpTitle = new JPanel();
		jpChoose1 = new JPanel();

		// Jlabel
		lbBackground = new JLabel("");
		lbBeforeBet = new JLabel("");
		lbCustPrice = new JLabel("�����ݾ�");
		lbBetPrice = new JLabel("���ñݾ�");
		lbResultPrice = new JLabel("���߱ݾ�");
		lbCartLabel = new JLabel("Betting Cart");
		lbTitle = new JLabel("����� ��ٸ�");
		lbDate = new JLabel("��¥&�ð�");
		lbSoccer1 = new JLabel("");
		lbSoccer2 = new JLabel("");
		lbSoccer3 = new JLabel("");
		lbSoccer4 = new JLabel("");
		lbLadder2 = new JLabel("");
		lbLadder1_1 = new JLabel("");
		lbRLadder1 = new JLabel("");
		lbRLadder2 = new JLabel("");
		lbRLadder3 = new JLabel("");
		lbRLadder4 = new JLabel("");
		lbLadder1 = new JLabel("");
		lblNewLabel = new JLabel("");

		// JTextField
		tfCust_price = new JTextField(String.valueOf(user_price) + "��");
		tfBet_price = new JTextField();
		tfResult_price = new JTextField();

		// JButton
		jbBetting = new JButton("");
		jbHole = new JButton(" Ȧ                                 1.97");
		jbZzag = new JButton(" ¦                                 1.97");
		jbLadNum3 = new JButton(" 3��                               1.97");
		jbLadNum4 = new JButton(" 4��                               1.97");
		jbStartLeft = new JButton(" �� ���                          1.97");
		jbStartRight = new JButton(" �� ���                          1.97");
		jbVerse1 = new JButton("vs");
		jbVerse2 = new JButton("vs");
		jbVerse3 = new JButton("vs");

		ladder_image.setBorder(tb1);
		jpBasket.setBorder(tb1);
		jpLadder_choose.setBorder(tb1);
		jpTitle.setBorder(new LineBorder(new Color(0, 0, 0)));

		lbCustPrice.setFont(fontB14);
		lbBetPrice.setFont(fontB14);
		lbResultPrice.setFont(fontB14);
		lbCartLabel.setFont(fontB15);
		jbBetting.setFont(fontP12);
		lbTitle.setFont(fontB15);
		lbDate.setFont(fontP12);

		jpChoose1.setBorder(new LineBorder(new Color(0, 0, 0)));
		jpLadder_choose.setBorder(tb1);
		jpTitle.setBorder(new LineBorder(new Color(0, 0, 0)));

		lbCustPrice.setForeground(Color.WHITE);
		lbCustPrice.setBackground(Color.BLACK);
		lbBetPrice.setForeground(Color.WHITE);
		lbResultPrice.setForeground(Color.WHITE);
		lbBetPrice.setBackground(Color.BLACK);
		lbResultPrice.setBackground(Color.BLACK);
		jpBettingCart.setBackground(new Color(100, 149, 237));
		lbCartLabel.setForeground(Color.WHITE);
		lbCartLabel.setBackground(Color.BLACK);
		jbBetting.setBackground(new Color(255, 255, 255));
		jbBetting.setForeground(Color.WHITE);
		jpTitle.setBackground(new Color(100, 149, 237));
		lbTitle.setBackground(Color.BLACK);
		lbTitle.setForeground(Color.WHITE);

		lbBeforeBet.setIcon(new ImageIcon("img/��ٸ��������ΰ�.gif"));
		lbSoccer3.setIcon(new ImageIcon("img/Ȧ.png"));
		lbSoccer2.setIcon(new ImageIcon("img/�����౸��.png"));
		lbSoccer4.setIcon(new ImageIcon("img/¦.png"));
		lbLadder1_1.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
		lbLadder2.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
		lbSoccer1.setIcon(new ImageIcon("img/�����౸��.png"));
		lbBackground.setIcon(new ImageIcon("img/��ٸ����.png"));
		jbBetting.setIcon(new ImageIcon("img/��ٸ����ù�ư.png"));
		lbLadder1.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
		lblNewLabel.setIcon(new ImageIcon("img/����.png"));

		ladder_image.setBounds(12, 10, 899, 383);
		jpBasket.setBounds(923, 415, 299, 157);
		lbBeforeBet.setBounds(240, 112, 400, 176);
		lbCustPrice.setBounds(12, 43, 89, 17);
		lbBetPrice.setBounds(12, 78, 89, 17);
		lbResultPrice.setBounds(12, 113, 89, 17);
		lbSoccer1.setBounds(279, 62, 60, 52);
		lbSoccer2.setBounds(530, 62, 60, 52);
		lbSoccer3.setBounds(279, 290, 60, 52);
		lbSoccer4.setBounds(530, 290, 60, 52);
		lbLadder2.setBounds(548, 110, 20, 176);
		lbLadder1_1.setBounds(299, 110, 20, 176);
		lbRLadder1.setBounds(319, 150, 231, 15);
		lbRLadder2.setBounds(319, 185, 231, 15);
		lbRLadder3.setBounds(319, 220, 231, 15);
		lbRLadder4.setBounds(319, 258, 231, 15);
		lbBackground.setBounds(12, 20, 875, 338);
		tfCust_price.setBounds(88, 44, 142, 21);
		tfBet_price.setBounds(88, 78, 142, 21);
		tfResult_price.setBounds(88, 114, 142, 21);
		jpBettingCart.setBounds(1, 1, 297, 30);
		lbCartLabel.setBounds(5, 7, 110, 17);
		jbBetting.setBounds(240, 100, 47, 35);
		lbLadder1.setBounds(299, 123, 22, 140);
		jpLadder_choose.setBounds(12, 415, 899, 157);
		jpTitle.setBounds(1, 1, 897, 27);
		lbTitle.setBounds(31, 2, 128, 25);
		lblNewLabel.setBounds(5, 5, 20, 20);
		lbDate.setBounds(12, 49, 130, 15);
		jpChoose1.setBounds(174, 38, 657, 35);

		getContentPane().add(ladder_image);
		getContentPane().add(jpBasket);
		getContentPane().add(jpLadder_choose);

		ladder_image.add(lbBeforeBet);
		ladder_image.add(lbSoccer1);
		ladder_image.add(lbSoccer2);
		ladder_image.add(lbSoccer3);
		ladder_image.add(lbSoccer4);
		ladder_image.add(lbLadder2);
		ladder_image.add(lbLadder1_1);
		ladder_image.add(lbRLadder1);
		ladder_image.add(lbRLadder3);
		ladder_image.add(lbRLadder2);
		ladder_image.add(lbRLadder4);
		ladder_image.add(lbBackground);
		ladder_image.add(lbLadder1);
		jpBasket.add(lbCustPrice);
		jpBasket.add(lbBetPrice);
		jpBasket.add(lbResultPrice);
		jpBasket.add(tfCust_price);
		jpBasket.add(tfBet_price);
		jpBasket.add(tfResult_price);
		jpBasket.add(jpBettingCart);
		jpBasket.add(jbBetting);
		jpBettingCart.add(lbCartLabel);
		jpLadder_choose.add(jpTitle);
		jpTitle.add(lbTitle);
		jpTitle.add(lblNewLabel);
		jpLadder_choose.add(lbDate);
		jpLadder_choose.add(jpChoose1);

		lbCustPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lbBetPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lbResultPrice.setHorizontalAlignment(SwingConstants.LEFT);
		tfCust_price.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCartLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lbTitle.setHorizontalAlignment(SwingConstants.LEFT);

		tfCust_price.setEditable(false);
		tfResult_price.setEditable(false);
		tfResult_price.setEnabled(false);

		tfCust_price.setColumns(10);
		tfBet_price.setColumns(10);
		tfResult_price.setColumns(10);

		ladder_image.setLayout(null);
		jpBasket.setLayout(null);
		jpBettingCart.setLayout(null);
		jpLadder_choose.setLayout(null);
		jpTitle.setLayout(null);
		jpChoose1.setLayout(null);

		// �����ϱ��ư��������
		jbBetting.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (user_price != 0) {
					if (tfBet_price.getText().equals("") || tfResult_price.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "���ÿ� ���� ������ ��� �������ּ���!");
					} else {

						lbVO = new Ladder_BetVO();
						lbVO.setLadder_bet_price(Integer.parseInt(tfBet_price.getText()));
						System.out.println("�ݾ���!" + Integer.parseInt(tfBet_price.getText()));
						lbVO.setLadder_bet_prize(int_bet_price);
						lbVO.setCust_id(user_id);
						lbVO.setUser_price(user_price);

						// ���ٸ� �������� DB�� �����ϱ����ؼ�
						if (button_result1 == 1){
							ladder_code=1;
							lbVO.setLadder_code(ladder_code);
						}
						if(button_result1 == 2){
							ladder_code=2;
							lbVO.setLadder_code(ladder_code);
						}
							if(button_result1 == 3){
								ladder_code=3;
								lbVO.setLadder_code(ladder_code);
							}
								if(button_result1 == 4){
									ladder_code=4;
									lbVO.setLadder_code(ladder_code);
								}
									
								 if(button_result1 == 5 ){
									 ladder_code=5;
										lbVO.setLadder_code(ladder_code);
								 }
									 if(button_result1 == 6) {
											ladder_code=6;
											lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 1 && button_result2 == 3) {
							// Ȧ,..
							ladder_code = 7;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 1 && button_result2 == 4) {
							ladder_code = 8;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 1 && button_result2 == 5) {
							ladder_code = 9;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 1 && button_result2 == 6) {
							ladder_code = 10;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 2 && button_result2 == 3) {
							// ¦,..
							ladder_code = 11;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 2 && button_result2 == 4) {
							ladder_code = 12;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 2 && button_result2 == 5) {
							ladder_code = 13;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 2 && button_result2 == 6) {
							ladder_code = 14;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 3 && button_result2 == 2) {
							// 3��,..
							ladder_code = 15;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 3 && button_result2 == 1) {
							ladder_code = 16;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 3 && button_result2 == 5) {
							ladder_code = 17;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 3 && button_result2 == 6) {
							ladder_code = 18;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 4 && button_result2 == 2) {
							// 4��,..
							ladder_code = 19;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 4 && button_result2 == 1) {
							ladder_code = 20;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 4 && button_result2 == 5) {
							ladder_code = 21;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 4 && button_result2 == 6) {
							ladder_code = 22;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 5 && button_result2 == 2) {
							// �����,..
							ladder_code = 23;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 5 && button_result2 == 1) {
							ladder_code = 24;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 5 && button_result2 == 3) {
							ladder_code = 25;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 5 && button_result2 == 4) {
							ladder_code = 26;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 6 && button_result2 == 2) {
							// �����,..
							ladder_code = 27;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 6 && button_result2 == 1) {
							ladder_code = 28;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 6 && button_result2 == 3) {
							ladder_code = 29;
							lbVO.setLadder_code(ladder_code);
						}
						if (button_result1 == 6 && button_result2 == 4) {
							ladder_code = 30;
							lbVO.setLadder_code(ladder_code);
						}

						// ��ٸ�����
						ladder_sw = (int) (Math.random() * 4) + 1;

						img = Toolkit.getDefaultToolkit().createImage("img/soccerball.gif");
						lbBeforeBet.setIcon(new ImageIcon(""));

						isStart = true;
						if (ladder_sw == 1 || ladder_sw == 3) {// ��ٸ�4���϶�
							repaint();
							lbRLadder1.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
							lbRLadder2.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
							lbRLadder3.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
							lbRLadder4.setIcon(new ImageIcon("img/���λ�ٸ�.png"));

						}
						if (ladder_sw == 2 || ladder_sw == 4) {// ��ٸ�3���϶�
							repaint();
							lbRLadder1.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
							lbRLadder2.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
							lbRLadder3.setIcon(new ImageIcon("img/���λ�ٸ�.png"));
							lbRLadder4.setIcon(null);
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "�����ݾ��� �����ϴ�!");

				}
			}

		});

		// �� ��ư�� �ش��ϴ� �̺�Ʈ ���ۡ�
		// Ȧ��ư����������
		jbHole.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���ñݾ��� ���� �Է��ϼž��մϴ�!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "�����ݾ��� �ʰ��ϼ̽��ϴ� !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "��");
						button_result1 = 1;
						button_result2 = button_result1;
						list.add(button_result1);
						
						
						jbZzag.setEnabled(false);
						buttonCnt++;
						
						
						if (buttonCnt == 2) {

							if (temp == button_result1) {
								// �����ߴ� ��ư�� �ѹ��������ٸ�?
								JOptionPane.showMessageDialog(null, "�̹� �����ϼ̽��ϴ�!");

								buttonCnt = 1;
							} else {
								// �ٸ���ư�� �����ϰ�, �ι�°�� ��ư�� �����Ҷ�
								button_result1 = list.get(0);
								button_result2 = 1;
							
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "��");
								double_bet_price = result_price * 1.97;
								int_bet_price = (int) double_bet_price;
								jbHole.setEnabled(false);
								jbZzag.setEnabled(false);
								jbLadNum3.setEnabled(false);
								jbLadNum4.setEnabled(false);
								jbStartLeft.setEnabled(false);
								jbStartRight.setEnabled(false);
								jbVerse1.setEnabled(false);
								jbVerse2.setEnabled(false);
								jbVerse3.setEnabled(false);
								count++;
								System.out.println("1�� ��� :"+button_result1+"  2�����:"+button_result2);
							}

						}

						temp = button_result1;
						System.out.println(button_result1+"  "+button_result2);
					}

				}

			}

		});

		jbHole.setFont(fontB14);
		jbZzag.setFont(fontB14);
		jbVerse1.setFont(fontB14);

		jbVerse1.setForeground(Color.BLACK);
		jbHole.setBackground(Color.WHITE);
		jbZzag.setBackground(Color.WHITE);
		jbVerse1.setBackground(Color.WHITE);

		jbHole.setBounds(0, 0, 280, 35);
		jbZzag.setBounds(376, 0, 280, 35);
		jbVerse1.setBounds(278, 0, 99, 35);

		jpChoose1.add(jbHole);
		jpChoose1.add(jbZzag);
		jpChoose1.add(jbVerse1);

		jbHole.setHorizontalAlignment(SwingConstants.LEFT);
		jbZzag.setHorizontalAlignment(SwingConstants.LEFT);

		// ¦��ư
		jbZzag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���ñݾ��� ���� �Է��ϼž��մϴ�!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "�����ݾ��� �ʰ��ϼ̽��ϴ� !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "��");
						button_result1 = 2;
						button_result2=button_result1;
						list.add(button_result1);
						
					
						jbHole.setEnabled(false);
						buttonCnt++;

						if (buttonCnt == 2) {

							if (temp == button_result1) {
								// �����ߴ� ��ư�� �ѹ��������ٸ�?

								JOptionPane.showMessageDialog(null, "�̹� �����ϼ̽��ϴ�!");
								buttonCnt = 1;
							} else {
								// �ٸ���ư�� �����ϰ�, �ι�°�� ��ư�� �����Ҷ�
								button_result1=list.get(0);
								button_result2 = 2;
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "��");
								double_bet_price = result_price * 1.97;
								int_bet_price = (int) double_bet_price;
								jbHole.setEnabled(false);
								jbZzag.setEnabled(false);
								jbLadNum3.setEnabled(false);
								jbLadNum4.setEnabled(false);
								jbStartLeft.setEnabled(false);
								jbStartRight.setEnabled(false);
								jbVerse1.setEnabled(false);
								jbVerse2.setEnabled(false);
								jbVerse3.setEnabled(false);
								count++;
								System.out.println("1�� ��� :"+button_result1+"  2�����:"+button_result2);
							}

						}
						temp = button_result1;
						System.out.println(button_result1+"  "+button_result2);
					}

				}

			}

		});

		JPanel jpChoose2 = new JPanel();
		jpChoose2.setLayout(null);
		jpChoose2.setBorder(new LineBorder(new Color(0, 0, 0)));
		jpChoose2.setBounds(174, 72, 657, 35);
		jpLadder_choose.add(jpChoose2);

		jbLadNum3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���ñݾ��� ���� �Է��ϼž��մϴ�!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "�����ݾ��� �ʰ��ϼ̽��ϴ� !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "��");
						button_result1 = 3;
						list.add(button_result1);
									
				
						jbLadNum4.setEnabled(false);
						buttonCnt++;
				

						if (buttonCnt == 2) {

							if (temp == button_result1) {
								// �����ߴ� ��ư�� �ѹ��������ٸ�?
								JOptionPane.showMessageDialog(null, "�̹� �����ϼ̽��ϴ�!");
								buttonCnt = 1;

							} else {
								// �ٸ���ư�� �����ϰ�, �ι�°�� ��ư�� �����Ҷ�
								button_result1 = list.get(0);
								button_result2 = 3; 
						
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "��");
								double_bet_price = result_price * 1.97;
								int_bet_price = (int) double_bet_price;
								jbHole.setEnabled(false);
								jbZzag.setEnabled(false);
								jbLadNum3.setEnabled(false);
								jbLadNum4.setEnabled(false);
								jbStartLeft.setEnabled(false);
								jbStartRight.setEnabled(false);
								jbVerse1.setEnabled(false);
								jbVerse2.setEnabled(false);
								jbVerse3.setEnabled(false);
								count++;
								System.out.println(button_result1+"  "+button_result2);
							}

						}
						temp = button_result1;
						System.out.println(button_result1+"  "+button_result2);
	
					}

				}

			}
		});
		jbLadNum3.setFont(new Font("����ײ�üL", Font.BOLD, 14));
		jbLadNum3.setHorizontalAlignment(SwingConstants.LEFT);
		jbLadNum3.setBackground(Color.WHITE);
		jbLadNum3.setBounds(0, 0, 280, 35);
		jpChoose2.add(jbLadNum3);

		jbLadNum4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���ñݾ��� ���� �Է��ϼž��մϴ�!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "�����ݾ��� �ʰ��ϼ̽��ϴ� !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "��");
						button_result1 = 4;
						button_result2 = button_result1;
						list.add(button_result1);
					
				
						jbLadNum3.setEnabled(false);
						buttonCnt++;

						if (buttonCnt == 2) {
							if (temp == button_result1) {
								// �����ߴ� ��ư�� �ѹ��������ٸ�?
								JOptionPane.showMessageDialog(null, "�̹� �����ϼ̽��ϴ�!");
								buttonCnt = 1;
							} else {
								// �ٸ���ư�� �����ϰ�, �ι�°�� ��ư�� �����Ҷ�
								button_result2 = 4;
								button_result1 = list.get(0);
						

								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "��");
								double_bet_price = result_price * 1.97;
								int_bet_price = (int) double_bet_price;
								jbHole.setEnabled(false);
								jbZzag.setEnabled(false);
								jbLadNum3.setEnabled(false);
								jbLadNum4.setEnabled(false);
								jbStartLeft.setEnabled(false);
								jbStartRight.setEnabled(false);
								jbVerse1.setEnabled(false);
								jbVerse2.setEnabled(false);
								jbVerse3.setEnabled(false);
								count++;
								System.out.println("1�� ��� :"+button_result1+"  2�����:"+button_result2);
							}

						}
						temp = button_result1;
						System.out.println(button_result1+"  "+button_result2);

					}
				}

			}
		});
		jbLadNum4.setFont(new Font("����ײ�üL", Font.BOLD, 14));
		jbLadNum4.setHorizontalAlignment(SwingConstants.LEFT);
		jbLadNum4.setBackground(Color.WHITE);
		jbLadNum4.setBounds(376, 0, 280, 35);
		jpChoose2.add(jbLadNum4);

		jbVerse2.setForeground(Color.BLACK);
		jbVerse2.setFont(new Font("����ײ�üL", Font.BOLD, 14));
		jbVerse2.setBackground(Color.WHITE);
		jbVerse2.setBounds(278, 0, 99, 35);
		jpChoose2.add(jbVerse2);

		JPanel choose3 = new JPanel();
		choose3.setLayout(null);
		choose3.setBorder(new LineBorder(new Color(0, 0, 0)));
		choose3.setBounds(174, 104, 657, 35);
		jpLadder_choose.add(choose3);

		jbStartLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���ñݾ��� ���� �Է��ϼž��մϴ�!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "�����ݾ��� �ʰ��ϼ̽��ϴ� !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "��");
						button_result1 = 5;
						button_result2 = button_result1;
						list.add(button_result1);
						
					
						jbStartRight.setEnabled(false);
						buttonCnt++;
						if (buttonCnt == 2) {
							if (temp == button_result1) {
								// �����ߴ� ��ư�� �ѹ��������ٸ�?
								JOptionPane.showMessageDialog(null, "�̹� �����ϼ̽��ϴ�!");
								buttonCnt = 1;
							} else {
								// �ٸ���ư�� �����ϰ�, �ι�°�� ��ư�� �����Ҷ�
								button_result1 =list.get(0);
								button_result2 = 5;
								
								
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "��");
								double_bet_price = result_price * 1.97;
								int_bet_price = (int) double_bet_price;

								jbHole.setEnabled(false);
								jbZzag.setEnabled(false);
								jbLadNum3.setEnabled(false);
								jbLadNum4.setEnabled(false);
								jbStartLeft.setEnabled(false);
								jbStartRight.setEnabled(false);
								jbVerse1.setEnabled(false);
								jbVerse2.setEnabled(false);
								jbVerse3.setEnabled(false);
								count++;
								System.out.println("1�� ��� :"+button_result1+"  2�����:"+button_result2);

							}

						}

						temp = button_result1;
						System.out.println(button_result1+"  "+button_result2);
					}

				}

			}
		});

		jbStartLeft.setFont(new Font("����ײ�üL", Font.BOLD, 14));
		jbStartLeft.setHorizontalAlignment(SwingConstants.LEFT);
		jbStartLeft.setBackground(Color.WHITE);
		jbStartLeft.setBounds(0, 0, 280, 35);
		choose3.add(jbStartLeft);

		jbStartRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���ñݾ��� ���� �Է��ϼž��մϴ�!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "�����ݾ��� �ʰ��ϼ̽��ϴ� !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "��");
						button_result1 = 6;
						button_result2 = button_result1;
						list.add(button_result1);
				
						jbStartLeft.setEnabled(false);
						buttonCnt++;
						if (buttonCnt == 2) {
							if (temp == button_result1) {
								// �����ߴ� ��ư�� �ѹ��������ٸ�?
								JOptionPane.showMessageDialog(null, "�̹� �����ϼ̽��ϴ�!");
								buttonCnt = 1;
							} else {
								// �ٸ���ư�� �����ϰ�, �ι�°�� ��ư�� �����Ҷ�
								button_result2 = 6;
								button_result1=list.get(0);
				
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "��");
								double_bet_price = result_price * 1.97;
								int_bet_price = (int) double_bet_price;

								jbHole.setEnabled(false);
								jbZzag.setEnabled(false);
								jbLadNum3.setEnabled(false);
								jbLadNum4.setEnabled(false);
								jbStartLeft.setEnabled(false);
								jbStartRight.setEnabled(false);
								jbVerse1.setEnabled(false);
								jbVerse2.setEnabled(false);
								jbVerse3.setEnabled(false);
								count++;
								System.out.println(button_result1+"  "+button_result2);
							}
						}

						temp = button_result1;
						System.out.println(button_result1+"  "+button_result2);
					}

				}

			}
		});
		jbStartRight.setFont(new Font("����ײ�üL", Font.BOLD, 14));
		jbStartRight.setHorizontalAlignment(SwingConstants.LEFT);
		jbStartRight.setBackground(Color.WHITE);
		jbStartRight.setBounds(376, 0, 280, 35);
		choose3.add(jbStartRight);

		jbVerse3.setForeground(Color.BLACK);
		jbVerse3.setFont(new Font("����ײ�üL", Font.BOLD, 14));
		jbVerse3.setBackground(Color.WHITE);
		jbVerse3.setBounds(278, 0, 99, 35);
		choose3.add(jbVerse3);

		JPanel tutorialPanel = new JPanel();
		tutorialPanel.setBorder(new LineBorder(Color.WHITE));
		tutorialPanel.setBounds(923, 10, 299, 383);
		getContentPane().add(tutorialPanel);
		tutorialPanel.setLayout(null);

		JLabel tutorialImage = new JLabel("");
		tutorialImage.setIcon(new ImageIcon("img/��ٸ����丮��ΰ�.png"));
		tutorialImage.setBounds(1, 1, 297, 381);
		tutorialPanel.add(tutorialImage);

		setTitle("����� ��ٸ�");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1250, 619);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width / 2) - (this.getWidth() / 2), (dim.height / 2) - (this.getHeight() / 2));
		this.setLocationRelativeTo(null);

		setVisible(true);

		
		
	}

	@Override

	public void run() {
		// TODO Auto-generated method stub
		int x_sw = 1;
		int y_sw = -1;
		int speed = 1;

		try {
			lm = new LadderModel();
			while (true) {

				repaint();
				if (isStart) {

					Thread.sleep(5);
					time = System.currentTimeMillis();
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
					str = sdf.format(new Date(time));
					lbDate.setText(str);

					if (count == 0) { // ��ư 1���� ������ ��
						if (ladder_sw == 1) {
							// ���ʿ��� ����ϰ�, ��ٸ����� 4���� ��
							System.out.println(x + " : " + y);
							if ((x == 290 && y == 137) || (x == 548 && y == 137) || (x == 299 && y == 170)
									|| (x == 290 && y == 247) || (x == 548 && y == 201)) {
								y_sw *= -1;

							}
							if ((x == 548 && y == 170) || (x == 299 && y == 201) || (x == 548 && y == 247)) {
								x_sw *= -1;
								y_sw *= -1;
							}
							if (x == 290 && y == 299) {
								JOptionPane.showMessageDialog(null, "�̹�ȸ���� [Ȧ] �Դϴ� !");

							}
							if (x == 290 && y == 300) {
								if ((button_result1 == 5) || (button_result1 == 4) || (button_result1 == 1)) {
									JOptionPane.showMessageDialog(null, "�����Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type1(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "�������Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.l_ladder_type(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}

							}

						} else if (ladder_sw == 2) {
							// ���ʿ��� ����ϰ�, ��ٸ����� 3���ϋ�
							System.out.println(x + " : " + y);
							if ((x == 290 && y == 137) || (x == 548 && y == 137) || (x == 299 && y == 170)
									|| (x == 548 && y == 201)) {
								y_sw *= -1;
							}
							if ((x == 548 && y == 170) || (x == 299 && y == 201)) {
								x_sw *= -1;
								y_sw *= -1;
							}
							if (x == 548 && y == 299) {
								JOptionPane.showMessageDialog(null, "�̹�ȸ���� [¦] �Դϴ� !");

							}
							if (x == 548 && y == 300) {
								if ((button_result1 == 5) || (button_result1 == 3) || (button_result1 == 2)) {
									JOptionPane.showMessageDialog(null, "�����Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.w_ladder_type1(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "�������Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}

						}

						else if (ladder_sw == 3) { // �����ʿ��� ����ϰ�, ��ٸ����� 4���϶�
							if ((x2 == 540 && y == 137) || (x2 == 290 && y == 170) || (x2 == 548 && y == 201)
									|| (x2 == 290 && y == 247) || (x2 == 548 && y == 247)) {
								y_sw *= -1;
								x_sw *= -1;
							}
							if ((x2 == 290 && y == 137) || (x2 == 548 && y == 170) || (x2 == 290 && y == 201)) {
								y_sw *= -1;
							}
							if (x2 == 548 && y == 299) {
								JOptionPane.showMessageDialog(null, "�̹�ȸ���� [¦] �Դϴ� !");

							}
							if (x2 == 548 && y == 300) {
								if ((button_result1 == 6) || (button_result1 == 4) || (button_result1 == 2)) {
									JOptionPane.showMessageDialog(null, "�����Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.w_ladder_type1(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "�������Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}
						}

						else if (ladder_sw == 4) { // �����ʿ��� ����ϰ�, ��ٸ�����3���϶�
							if ((x2 == 540 && y == 137) || (x2 == 290 && y == 170) || (x2 == 548 && y == 201)
									|| (x2 == 548 && y == 247)) {
								y_sw *= -1;
								x_sw *= -1;
							}
							if ((x2 == 290 && y == 137) || (x2 == 548 && y == 170) || (x2 == 290 && y == 201)) {
								y_sw *= -1;
							}
							if (x2 == 290 && y == 299) {
								JOptionPane.showMessageDialog(null, "�̹�ȸ���� [Ȧ] �Դϴ� !");

							}
							if (x2 == 290 && y == 300) {
								if ((button_result1 == 6) || (button_result1 == 3) || (button_result1 == 1)) {
									JOptionPane.showMessageDialog(null, "�����Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.w_ladder_type1(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "�������Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}
						}

					}

					if (count > 0) {// ��ư 2���� ������ ��
						if (ladder_sw == 1) {
							// ���ʿ��� ����ϰ�, ��ٸ����� 4���϶�
							System.out.println(x + " : " + y);
							if ((x == 290 && y == 137) || (x == 548 && y == 137) || (x == 299 && y == 170)
									|| (x == 290 && y == 247) || (x == 548 && y == 201)) {
								y_sw *= -1;

							}
							if ((x == 548 && y == 170) || (x == 299 && y == 201) || (x == 548 && y == 247)) {
								x_sw *= -1;
								y_sw *= -1;
							}
							if (x == 290 && y == 299) {
								JOptionPane.showMessageDialog(null, "�̹�ȸ���� [Ȧ] �Դϴ� !");

							}
							if (x == 290 && y == 300) {
								if ((button_result1 == 1 && button_result2 == 4)
										|| (button_result1 == 1 && button_result2 == 5)
										|| (button_result1 == 4 && button_result2 == 1)
										|| (button_result1 == 4 && button_result2 == 5)
										|| (button_result1 == 5 && button_result2 == 1)
										|| (button_result1 == 5 && button_result2 == 4)) {
									JOptionPane.showMessageDialog(null, "�����Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type2(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "�������Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type2(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}

							}

						}

						else if (ladder_sw == 2) {
							// ���ʿ��� ����ϰ�, ��ٸ����� 3���ϋ�
							System.out.println(x + " : " + y);
							if ((x == 290 && y == 137) || (x == 548 && y == 137) || (x == 299 && y == 170)
									|| (x == 548 && y == 201)) {
								y_sw *= -1;
							}
							if ((x == 548 && y == 170) || (x == 299 && y == 201)) {
								x_sw *= -1;
								y_sw *= -1;
							}
							if (x == 548 && y == 299) {
								JOptionPane.showMessageDialog(null, "�̹�ȸ���� [¦] �Դϴ� !");

							}
							if (x == 548 && y == 300) {
								if ((button_result1 == 2 && button_result2 == 3)
										|| (button_result1 == 2 && button_result2 == 5)
										|| (button_result1 == 3 && button_result2 == 2)
										|| (button_result1 == 3 && button_result2 == 5)
										|| (button_result1 == 5 && button_result2 == 2)
										|| (button_result1 == 5 && button_result2 == 3)) {
									JOptionPane.showMessageDialog(null, "�����Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type2(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "�������Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type2(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}

						}

						else if (ladder_sw == 3) { // �����ʿ��� ����ϰ�, ��ٸ����� 4���϶�
							if ((x2 == 540 && y == 137) || (x2 == 290 && y == 170) || (x2 == 548 && y == 201)
									|| (x2 == 290 && y == 247) || (x2 == 548 && y == 247)) {
								y_sw *= -1;
								x_sw *= -1;
							}
							if ((x2 == 290 && y == 137) || (x2 == 548 && y == 170) || (x2 == 290 && y == 201)) {
								y_sw *= -1;
							}
							if (x2 == 548 && y == 299) {
								JOptionPane.showMessageDialog(null, "�̹�ȸ���� [¦] �Դϴ� !");

							}
							if (x2 == 548 && y == 300) {
								if ((button_result1 == 2 & button_result2 == 4)
										|| (button_result1 == 2 && button_result2 == 6)
										|| (button_result1 == 4 && button_result2 == 6)
										|| (button_result1 == 4 && button_result2 == 2)
										|| (button_result1 == 6 && button_result2 == 2)
										|| (button_result1 == 6 && button_result2 == 4)) {
									JOptionPane.showMessageDialog(null, "�����Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type2(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "�������Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type2(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}
						}

						else if (ladder_sw == 4) { // �����ʿ��� ����ϰ�, ��ٸ�����3���϶�
							if ((x2 == 540 && y == 137) || (x2 == 290 && y == 170) || (x2 == 548 && y == 201)
									|| (x2 == 548 && y == 247)) {
								y_sw *= -1;
								x_sw *= -1;
							}
							if ((x2 == 290 && y == 137) || (x2 == 548 && y == 170) || (x2 == 290 && y == 201)) {
								y_sw *= -1;
							}
							if (x2 == 290 && y == 299) {
								JOptionPane.showMessageDialog(null, "�̹�ȸ���� [Ȧ] �Դϴ� !");

							}
							if (x2 == 290 && y == 300) {
								if ((button_result1 == 1 && button_result2 == 3)
										|| (button_result1 == 1 && button_result2 == 6)
										|| (button_result1 == 3 && button_result2 == 1)
										|| (button_result1 == 3 && button_result2 == 6)
										|| (button_result1 == 6 && button_result2 == 1)
										|| (button_result1 == 6 && button_result2 == 3)) {
									JOptionPane.showMessageDialog(null, "�����Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type2(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "�������Դϴ� !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type2(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}
						}

					}

					if (y_sw == 1) {
						if (ladder_sw == 1 || ladder_sw == 2) {
							x += speed * x_sw;
						} else
							x2 += speed * x_sw;
					} else {
						y += speed;
					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}

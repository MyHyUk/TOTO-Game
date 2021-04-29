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

//사다리뷰

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
	private double double_bet_price; // 적중금액
	private int ladder_sw; // 사다리 스위치
	private int start_point; // 시작점
	private int x = 290; // 사다리 x좌표
	private int x2 = 540; // 사다리 x2좌표
	private int y = 61; // 사다리 y좌표
	private int result_price = 0; // 적중금액
	private int count = 0;// 버튼 count

	private int int_bet_price, user_price;// 배팅금액,사용자의 보유금액
	private int button_result1, button_result2; // 각버튼당 결과값
	private int ladder_code; //사다리코드
	private String ladder_bet_YN, user_id, str;// 사다리배팅당첨여부, 사용자id, 시간
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	long time = System.currentTimeMillis();
	private Image img = Toolkit.getDefaultToolkit().createImage("");

	private Font fontP14 = new Font("양재붓꽃채L", Font.PLAIN, 14);
	private Font fontP12 = new Font("양재붓꽃체L", Font.PLAIN, 12);
	private Font fontB15 = new Font("양재붓꽃체L", Font.BOLD, 15);
	private Font fontB14 = new Font("양재붓꽃체L", Font.BOLD, 14);

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
		lbCustPrice = new JLabel("보유금액");
		lbBetPrice = new JLabel("배팅금액");
		lbResultPrice = new JLabel("적중금액");
		lbCartLabel = new JLabel("Betting Cart");
		lbTitle = new JLabel("모두의 사다리");
		lbDate = new JLabel("날짜&시간");
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
		tfCust_price = new JTextField(String.valueOf(user_price) + "원");
		tfBet_price = new JTextField();
		tfResult_price = new JTextField();

		// JButton
		jbBetting = new JButton("");
		jbHole = new JButton(" 홀                                 1.97");
		jbZzag = new JButton(" 짝                                 1.97");
		jbLadNum3 = new JButton(" 3줄                               1.97");
		jbLadNum4 = new JButton(" 4줄                               1.97");
		jbStartLeft = new JButton(" 좌 출발                          1.97");
		jbStartRight = new JButton(" 우 출발                          1.97");
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

		lbBeforeBet.setIcon(new ImageIcon("img/사다리배팅전로고.gif"));
		lbSoccer3.setIcon(new ImageIcon("img/홀.png"));
		lbSoccer2.setIcon(new ImageIcon("img/시작축구공.png"));
		lbSoccer4.setIcon(new ImageIcon("img/짝.png"));
		lbLadder1_1.setIcon(new ImageIcon("img/세로사다리.png"));
		lbLadder2.setIcon(new ImageIcon("img/세로사다리.png"));
		lbSoccer1.setIcon(new ImageIcon("img/시작축구공.png"));
		lbBackground.setIcon(new ImageIcon("img/사다리배경.png"));
		jbBetting.setIcon(new ImageIcon("img/사다리배팅버튼.png"));
		lbLadder1.setIcon(new ImageIcon("img/가로사다리.png"));
		lblNewLabel.setIcon(new ImageIcon("img/동전.png"));

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

		// 배팅하기버튼눌렀을때
		jbBetting.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (user_price != 0) {
					if (tfBet_price.getText().equals("") || tfResult_price.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "배팅에 대한 정보를 모두 선택해주세요!");
					} else {

						lbVO = new Ladder_BetVO();
						lbVO.setLadder_bet_price(Integer.parseInt(tfBet_price.getText()));
						System.out.println("금액은!" + Integer.parseInt(tfBet_price.getText()));
						lbVO.setLadder_bet_prize(int_bet_price);
						lbVO.setCust_id(user_id);
						lbVO.setUser_price(user_price);

						// 어떤사다리 유형인지 DB에 전달하기위해서
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
							// 홀,..
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
							// 짝,..
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
							// 3줄,..
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
							// 4줄,..
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
							// 좌출발,..
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
							// 우출발,..
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

						// 사다리랜덤
						ladder_sw = (int) (Math.random() * 4) + 1;

						img = Toolkit.getDefaultToolkit().createImage("img/soccerball.gif");
						lbBeforeBet.setIcon(new ImageIcon(""));

						isStart = true;
						if (ladder_sw == 1 || ladder_sw == 3) {// 사다리4개일때
							repaint();
							lbRLadder1.setIcon(new ImageIcon("img/가로사다리.png"));
							lbRLadder2.setIcon(new ImageIcon("img/가로사다리.png"));
							lbRLadder3.setIcon(new ImageIcon("img/가로사다리.png"));
							lbRLadder4.setIcon(new ImageIcon("img/가로사다리.png"));

						}
						if (ladder_sw == 2 || ladder_sw == 4) {// 사다리3개일때
							repaint();
							lbRLadder1.setIcon(new ImageIcon("img/가로사다리.png"));
							lbRLadder2.setIcon(new ImageIcon("img/가로사다리.png"));
							lbRLadder3.setIcon(new ImageIcon("img/가로사다리.png"));
							lbRLadder4.setIcon(null);
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "보유금액이 없습니다!");

				}
			}

		});

		// 각 버튼에 해당하는 이벤트 시작↓
		// 홀버튼을눌렀을때
		jbHole.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "배팅금액을 먼저 입력하셔야합니다!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셨습니다 !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "원");
						button_result1 = 1;
						button_result2 = button_result1;
						list.add(button_result1);
						
						
						jbZzag.setEnabled(false);
						buttonCnt++;
						
						
						if (buttonCnt == 2) {

							if (temp == button_result1) {
								// 선택했던 버튼을 한번더누른다면?
								JOptionPane.showMessageDialog(null, "이미 선택하셨습니다!");

								buttonCnt = 1;
							} else {
								// 다른버튼을 선택하고, 두번째로 버튼을 선택할때
								button_result1 = list.get(0);
								button_result2 = 1;
							
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "원");
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
								System.out.println("1번 결과 :"+button_result1+"  2번결과:"+button_result2);
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

		// 짝버튼
		jbZzag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "배팅금액을 먼저 입력하셔야합니다!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셨습니다 !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "원");
						button_result1 = 2;
						button_result2=button_result1;
						list.add(button_result1);
						
					
						jbHole.setEnabled(false);
						buttonCnt++;

						if (buttonCnt == 2) {

							if (temp == button_result1) {
								// 선택했던 버튼을 한번더누른다면?

								JOptionPane.showMessageDialog(null, "이미 선택하셨습니다!");
								buttonCnt = 1;
							} else {
								// 다른버튼을 선택하고, 두번째로 버튼을 선택할때
								button_result1=list.get(0);
								button_result2 = 2;
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "원");
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
								System.out.println("1번 결과 :"+button_result1+"  2번결과:"+button_result2);
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
					JOptionPane.showMessageDialog(null, "배팅금액을 먼저 입력하셔야합니다!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셨습니다 !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "원");
						button_result1 = 3;
						list.add(button_result1);
									
				
						jbLadNum4.setEnabled(false);
						buttonCnt++;
				

						if (buttonCnt == 2) {

							if (temp == button_result1) {
								// 선택했던 버튼을 한번더누른다면?
								JOptionPane.showMessageDialog(null, "이미 선택하셨습니다!");
								buttonCnt = 1;

							} else {
								// 다른버튼을 선택하고, 두번째로 버튼을 선택할때
								button_result1 = list.get(0);
								button_result2 = 3; 
						
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "원");
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
		jbLadNum3.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
		jbLadNum3.setHorizontalAlignment(SwingConstants.LEFT);
		jbLadNum3.setBackground(Color.WHITE);
		jbLadNum3.setBounds(0, 0, 280, 35);
		jpChoose2.add(jbLadNum3);

		jbLadNum4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "배팅금액을 먼저 입력하셔야합니다!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셨습니다 !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "원");
						button_result1 = 4;
						button_result2 = button_result1;
						list.add(button_result1);
					
				
						jbLadNum3.setEnabled(false);
						buttonCnt++;

						if (buttonCnt == 2) {
							if (temp == button_result1) {
								// 선택했던 버튼을 한번더누른다면?
								JOptionPane.showMessageDialog(null, "이미 선택하셨습니다!");
								buttonCnt = 1;
							} else {
								// 다른버튼을 선택하고, 두번째로 버튼을 선택할때
								button_result2 = 4;
								button_result1 = list.get(0);
						

								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "원");
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
								System.out.println("1번 결과 :"+button_result1+"  2번결과:"+button_result2);
							}

						}
						temp = button_result1;
						System.out.println(button_result1+"  "+button_result2);

					}
				}

			}
		});
		jbLadNum4.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
		jbLadNum4.setHorizontalAlignment(SwingConstants.LEFT);
		jbLadNum4.setBackground(Color.WHITE);
		jbLadNum4.setBounds(376, 0, 280, 35);
		jpChoose2.add(jbLadNum4);

		jbVerse2.setForeground(Color.BLACK);
		jbVerse2.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
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
					JOptionPane.showMessageDialog(null, "배팅금액을 먼저 입력하셔야합니다!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셨습니다 !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "원");
						button_result1 = 5;
						button_result2 = button_result1;
						list.add(button_result1);
						
					
						jbStartRight.setEnabled(false);
						buttonCnt++;
						if (buttonCnt == 2) {
							if (temp == button_result1) {
								// 선택했던 버튼을 한번더누른다면?
								JOptionPane.showMessageDialog(null, "이미 선택하셨습니다!");
								buttonCnt = 1;
							} else {
								// 다른버튼을 선택하고, 두번째로 버튼을 선택할때
								button_result1 =list.get(0);
								button_result2 = 5;
								
								
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "원");
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
								System.out.println("1번 결과 :"+button_result1+"  2번결과:"+button_result2);

							}

						}

						temp = button_result1;
						System.out.println(button_result1+"  "+button_result2);
					}

				}

			}
		});

		jbStartLeft.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
		jbStartLeft.setHorizontalAlignment(SwingConstants.LEFT);
		jbStartLeft.setBackground(Color.WHITE);
		jbStartLeft.setBounds(0, 0, 280, 35);
		choose3.add(jbStartLeft);

		jbStartRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBet_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "배팅금액을 먼저 입력하셔야합니다!");
				} else {

					double_bet_price = Integer.parseInt(tfBet_price.getText()) * 1.97;
					int_bet_price = (int) double_bet_price;

					if (user_price < Integer.parseInt(tfBet_price.getText())) {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셨습니다 !");

					}
					if (user_price >= Integer.parseInt(tfBet_price.getText())) {
						result_price = int_bet_price;
						tfResult_price.setText(result_price + "원");
						button_result1 = 6;
						button_result2 = button_result1;
						list.add(button_result1);
				
						jbStartLeft.setEnabled(false);
						buttonCnt++;
						if (buttonCnt == 2) {
							if (temp == button_result1) {
								// 선택했던 버튼을 한번더누른다면?
								JOptionPane.showMessageDialog(null, "이미 선택하셨습니다!");
								buttonCnt = 1;
							} else {
								// 다른버튼을 선택하고, 두번째로 버튼을 선택할때
								button_result2 = 6;
								button_result1=list.get(0);
				
								tfResult_price.setText(String.valueOf((int) (result_price * 1.97)) + "원");
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
		jbStartRight.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
		jbStartRight.setHorizontalAlignment(SwingConstants.LEFT);
		jbStartRight.setBackground(Color.WHITE);
		jbStartRight.setBounds(376, 0, 280, 35);
		choose3.add(jbStartRight);

		jbVerse3.setForeground(Color.BLACK);
		jbVerse3.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
		jbVerse3.setBackground(Color.WHITE);
		jbVerse3.setBounds(278, 0, 99, 35);
		choose3.add(jbVerse3);

		JPanel tutorialPanel = new JPanel();
		tutorialPanel.setBorder(new LineBorder(Color.WHITE));
		tutorialPanel.setBounds(923, 10, 299, 383);
		getContentPane().add(tutorialPanel);
		tutorialPanel.setLayout(null);

		JLabel tutorialImage = new JLabel("");
		tutorialImage.setIcon(new ImageIcon("img/사다리듀토리얼로고.png"));
		tutorialImage.setBounds(1, 1, 297, 381);
		tutorialPanel.add(tutorialImage);

		setTitle("모두의 사다리");
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

					if (count == 0) { // 버튼 1개만 눌렀을 때
						if (ladder_sw == 1) {
							// 왼쪽에서 출발하고, 사다리수가 4개일 때
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
								JOptionPane.showMessageDialog(null, "이번회차는 [홀] 입니다 !");

							}
							if (x == 290 && y == 300) {
								if ((button_result1 == 5) || (button_result1 == 4) || (button_result1 == 1)) {
									JOptionPane.showMessageDialog(null, "적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type1(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "미적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.l_ladder_type(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}

							}

						} else if (ladder_sw == 2) {
							// 왼쪽에서 출발하고, 사다리수가 3개일떄
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
								JOptionPane.showMessageDialog(null, "이번회차는 [짝] 입니다 !");

							}
							if (x == 548 && y == 300) {
								if ((button_result1 == 5) || (button_result1 == 3) || (button_result1 == 2)) {
									JOptionPane.showMessageDialog(null, "적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.w_ladder_type1(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "미적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}

						}

						else if (ladder_sw == 3) { // 오른쪽에서 출발하고, 사다리수가 4개일때
							if ((x2 == 540 && y == 137) || (x2 == 290 && y == 170) || (x2 == 548 && y == 201)
									|| (x2 == 290 && y == 247) || (x2 == 548 && y == 247)) {
								y_sw *= -1;
								x_sw *= -1;
							}
							if ((x2 == 290 && y == 137) || (x2 == 548 && y == 170) || (x2 == 290 && y == 201)) {
								y_sw *= -1;
							}
							if (x2 == 548 && y == 299) {
								JOptionPane.showMessageDialog(null, "이번회차는 [짝] 입니다 !");

							}
							if (x2 == 548 && y == 300) {
								if ((button_result1 == 6) || (button_result1 == 4) || (button_result1 == 2)) {
									JOptionPane.showMessageDialog(null, "적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.w_ladder_type1(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "미적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}
						}

						else if (ladder_sw == 4) { // 오른쪽에서 출발하고, 사다리수가3개일때
							if ((x2 == 540 && y == 137) || (x2 == 290 && y == 170) || (x2 == 548 && y == 201)
									|| (x2 == 548 && y == 247)) {
								y_sw *= -1;
								x_sw *= -1;
							}
							if ((x2 == 290 && y == 137) || (x2 == 548 && y == 170) || (x2 == 290 && y == 201)) {
								y_sw *= -1;
							}
							if (x2 == 290 && y == 299) {
								JOptionPane.showMessageDialog(null, "이번회차는 [홀] 입니다 !");

							}
							if (x2 == 290 && y == 300) {
								if ((button_result1 == 6) || (button_result1 == 3) || (button_result1 == 1)) {
									JOptionPane.showMessageDialog(null, "적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.w_ladder_type1(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "미적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}
						}

					}

					if (count > 0) {// 버튼 2개만 눌렀을 때
						if (ladder_sw == 1) {
							// 왼쪽에서 출발하고, 사다리수가 4개일때
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
								JOptionPane.showMessageDialog(null, "이번회차는 [홀] 입니다 !");

							}
							if (x == 290 && y == 300) {
								if ((button_result1 == 1 && button_result2 == 4)
										|| (button_result1 == 1 && button_result2 == 5)
										|| (button_result1 == 4 && button_result2 == 1)
										|| (button_result1 == 4 && button_result2 == 5)
										|| (button_result1 == 5 && button_result2 == 1)
										|| (button_result1 == 5 && button_result2 == 4)) {
									JOptionPane.showMessageDialog(null, "적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type2(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "미적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type2(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}

							}

						}

						else if (ladder_sw == 2) {
							// 왼쪽에서 출발하고, 사다리수가 3개일떄
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
								JOptionPane.showMessageDialog(null, "이번회차는 [짝] 입니다 !");

							}
							if (x == 548 && y == 300) {
								if ((button_result1 == 2 && button_result2 == 3)
										|| (button_result1 == 2 && button_result2 == 5)
										|| (button_result1 == 3 && button_result2 == 2)
										|| (button_result1 == 3 && button_result2 == 5)
										|| (button_result1 == 5 && button_result2 == 2)
										|| (button_result1 == 5 && button_result2 == 3)) {
									JOptionPane.showMessageDialog(null, "적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type2(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "미적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type2(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}

						}

						else if (ladder_sw == 3) { // 오른쪽에서 출발하고, 사다리수가 4개일때
							if ((x2 == 540 && y == 137) || (x2 == 290 && y == 170) || (x2 == 548 && y == 201)
									|| (x2 == 290 && y == 247) || (x2 == 548 && y == 247)) {
								y_sw *= -1;
								x_sw *= -1;
							}
							if ((x2 == 290 && y == 137) || (x2 == 548 && y == 170) || (x2 == 290 && y == 201)) {
								y_sw *= -1;
							}
							if (x2 == 548 && y == 299) {
								JOptionPane.showMessageDialog(null, "이번회차는 [짝] 입니다 !");

							}
							if (x2 == 548 && y == 300) {
								if ((button_result1 == 2 & button_result2 == 4)
										|| (button_result1 == 2 && button_result2 == 6)
										|| (button_result1 == 4 && button_result2 == 6)
										|| (button_result1 == 4 && button_result2 == 2)
										|| (button_result1 == 6 && button_result2 == 2)
										|| (button_result1 == 6 && button_result2 == 4)) {
									JOptionPane.showMessageDialog(null, "적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type2(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "미적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");
									lm.l_ladder_type2(lbVO);
									lm.l_cust_point(lbVO);
									break;
								}
							}
						}

						else if (ladder_sw == 4) { // 오른쪽에서 출발하고, 사다리수가3개일때
							if ((x2 == 540 && y == 137) || (x2 == 290 && y == 170) || (x2 == 548 && y == 201)
									|| (x2 == 548 && y == 247)) {
								y_sw *= -1;
								x_sw *= -1;
							}
							if ((x2 == 290 && y == 137) || (x2 == 548 && y == 170) || (x2 == 290 && y == 201)) {
								y_sw *= -1;
							}
							if (x2 == 290 && y == 299) {
								JOptionPane.showMessageDialog(null, "이번회차는 [홀] 입니다 !");

							}
							if (x2 == 290 && y == 300) {
								if ((button_result1 == 1 && button_result2 == 3)
										|| (button_result1 == 1 && button_result2 == 6)
										|| (button_result1 == 3 && button_result2 == 1)
										|| (button_result1 == 3 && button_result2 == 6)
										|| (button_result1 == 6 && button_result2 == 1)
										|| (button_result1 == 6 && button_result2 == 3)) {
									JOptionPane.showMessageDialog(null, "적중입니다 !");
									img = Toolkit.getDefaultToolkit().createImage("");

									lm.w_ladder_type2(lbVO);
									lm.w_cust_point(lbVO);

									break;
								} else {
									JOptionPane.showMessageDialog(null, "미적중입니다 !");
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

package view;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import model.RacingModel;
import model.rec.AnimalVO;
import model.rec.CustomerVO;
import model.rec.RacingVO;
import model.rec.Racing_BetVO;
import oracle.sql.DATE;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Race extends JFrame implements Runnable {
	private JTextField textField;
	private JTextField txtBettingCart;
	private JTextField textField_1;
	private Image img;
	private Image img2;
	private Image img3;
	private Image img4;
	private Image img5;
	private Image img6;
	private Image img7;
	private Image img8;

	private ImageIcon icon = new ImageIcon("img/bg_racing.jpg");
	int x = 20;
	int y = 20;
	int z = 20;
	int w = 20;
	int havemoney;
	Racing_BetVO rb2;
	private JTextField textField_2;
	private JLabel lblNewLabel, lblNewLabel_8;
	int result; 
	String user_id;
	int start = 0;
	long time = System.currentTimeMillis();;
	ArrayList<AnimalVO> an2 = new ArrayList<AnimalVO>();
	AnimalVO[] vo = new AnimalVO[4];
	private boolean finish = false;

	public Race(String user_id) {
		ImageIcon img_logo = new ImageIcon("img/logo.png");
		setIconImage(img_logo.getImage());
		
		AnimalVO an = new AnimalVO();
		
         
		
	

		try {

			RacingModel rm = new RacingModel();

			an2 = rm.animal();

		} catch (Exception e) {
			e.getStackTrace();
		}

		for (int i = 0; i < 4; i++)
			vo[i] = an2.get(i);
				
		img = Toolkit.getDefaultToolkit().createImage("img/" + vo[0].getAni_name() + ".gif");
		img2 = Toolkit.getDefaultToolkit().createImage("img/" + vo[1].getAni_name() + ".gif");
		img3 = Toolkit.getDefaultToolkit().createImage("img/" + vo[2].getAni_name() + ".gif");
		img4 = Toolkit.getDefaultToolkit().createImage("img/" + vo[3].getAni_name() + ".gif");

	    
		try {
			this.user_id = user_id;

			Racing_BetVO rb = new Racing_BetVO();
			rb.setCust_id(user_id);

			CustomerVO cu = new CustomerVO();
			RacingModel rm = new RacingModel();

			havemoney = rm.racing_bet(user_id);

		} catch (Exception e) {
			// TODO: handle exception
		}

		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);

		JPanel racepanel = new JPanel() {

			public void paint(Graphics g) {
				super.paint(g);

				g.drawImage(img, x, 120, this);
				g.drawImage(img2, y, 220, this);
				g.drawImage(img3, z, 300, this);
				g.drawImage(img4, w, 380, this);
				setOpaque(false);

			}
		};

		racepanel.setBounds(23, 21, 1204, 544);
		getContentPane().add(racepanel);
		racepanel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(1239, 24, 327, 290);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtBettingCart = new JTextField();
		txtBettingCart.setBounds(1, 1, 325, 41);
		txtBettingCart.setEditable(false);
		txtBettingCart.setForeground(Color.WHITE);
		txtBettingCart.setFont(new Font("양재붓꽃체L", Font.BOLD, 17));
		txtBettingCart.setText("BETTING CART");
		txtBettingCart.setBackground(new Color(46, 139, 87));
		panel_1.add(txtBettingCart);
		txtBettingCart.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\uBCF4\uC720\uBA38\uB2C8");
		lblNewLabel_4.setBounds(12, 60, 75, 15);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("양재붓꽃체L", Font.BOLD, 16));
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("\uBC30\uD305\uAE08\uC561");
		lblNewLabel_5.setBounds(12, 109, 75, 15);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("양재붓꽃체L", Font.BOLD, 16));
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("\uC801\uC911\uAE08\uC561");
		lblNewLabel_6.setBounds(12, 155, 75, 15);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("양재붓꽃체L", Font.BOLD, 16));
		panel_1.add(lblNewLabel_6);

		lblNewLabel_8 = new JLabel("0");
		lblNewLabel_8.setBounds(118, 155, 107, 15);
		lblNewLabel_8.setForeground(Color.WHITE);
		panel_1.add(lblNewLabel_8);

		JButton btnNewButton = new JButton("");
		//btnNewButton.setIcon(new ImageIcon("C:\\Users\\user\\workspace\\toto\\img\\\uB808\uC774\uC2A4\uBC30\uD305\uD558\uAE30\uBC84\uD2BC.png"));
		btnNewButton.setIcon(new ImageIcon("img/레이스배팅하기버튼.png"));
		btnNewButton.setBounds(12, 186, 303, 91);

		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(46, 139, 87));
		btnNewButton.setFont(new Font("양재붓꽃체L", Font.BOLD, 22));
		panel_1.add(btnNewButton);

		textField_1 = new JTextField("");
		textField_1.setBounds(118, 106, 107, 26);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField(String.valueOf(havemoney));
		textField_2.setBounds(118, 60, 107, 21);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.WHITE));
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(251, 575, 805, 162);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("img/동전.png"));
		lblNewLabel_7.setBounds(12, 10, 20, 20);
		panel_2.add(lblNewLabel_7);

		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBackground(new Color(46, 139, 87));
		textField.setFont(new Font("양재붓꽃체L", Font.BOLD, 15));
		textField.setText("      \uACBD\uC8FC \uB808\uC774\uC2A4");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(1, 1, 803, 37);
		panel_2.add(textField);
		textField.setColumns(10);

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		String str = sdf.format(new Date(time));

		lblNewLabel = new JLabel(str);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("양재붓꽃체L", Font.BOLD, 15));
		lblNewLabel.setBounds(12, 57, 180, 15);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img/1등선택.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("양재붓꽃체L", Font.BOLD, 18));
		lblNewLabel_1.setBounds(315, 47, 216, 34);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel(String.valueOf(11 - vo[0].getAni_speed()));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBackground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("양재붓꽃체L", Font.BOLD, 12));
		lblNewLabel_3.setBounds(114, 10, 36, 15);

		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.WHITE);
		panel_3.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				try {
					if (havemoney >= Integer.parseInt(textField_1.getText())) {

						double y = 0;
						double a = 0;

						y = Double.parseDouble(lblNewLabel_3.getText());

						int c = Integer.parseInt(textField_1.getText());

						a = y * c;

						result = 1;

						lblNewLabel_8.setText(String.valueOf((int) a));
					} else {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셧습니다");
					}

				} catch (Exception gg) {
					JOptionPane.showMessageDialog(null, "배팅할 금액을 입력해주세요");
				}

			}
		});

		panel_3.add(lblNewLabel_3);

		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(124, 91, 162, 37);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("1번 " + vo[0].getAni_name());
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBackground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("양재붓꽃체L", Font.BOLD, 12));
		lblNewLabel_2.setBounds(12, 10, 82, 15);
		panel_3.add(lblNewLabel_2);

		JLabel label_1 = new JLabel(String.valueOf(11 - vo[1].getAni_speed()));
		label_1.setForeground(Color.BLACK);
		label_1.setBackground(Color.BLACK);
		label_1.setFont(new Font("양재붓꽃체L", Font.BOLD, 12));
		label_1.setBounds(114, 10, 36, 15);

		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {

					if (havemoney >= Integer.parseInt(textField_1.getText())) {
						double y = 0;
						double a = 0;

						y = Double.parseDouble(label_1.getText());

						int c = Integer.parseInt(textField_1.getText());

						a = y * c;

						result = 2;

						lblNewLabel_8.setText(String.valueOf((int) a));
					} else {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셧습니다");
					}

				} catch (Exception gg) {
					JOptionPane.showMessageDialog(null, "배팅할 금액을 입력해주세요");
				}

			}
		});
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(286, 91, 162, 37);
		panel_2.add(panel_4);

		JLabel label = new JLabel("2번 " + vo[1].getAni_name());
		label.setForeground(Color.BLACK);
		label.setBackground(Color.BLACK);
		label.setFont(new Font("양재붓꽃체L", Font.BOLD, 12));
		label.setBounds(12, 10, 82, 15);
		panel_4.add(label);

		panel_4.add(label_1);

		JLabel label_3 = new JLabel(String.valueOf(11 - vo[2].getAni_speed()));
		label_3.setForeground(Color.BLACK);
		label_3.setBackground(Color.BLACK);
		label_3.setFont(new Font("양재붓꽃체L", Font.BOLD, 12));
		label_3.setBounds(114, 10, 36, 15);

		JPanel panel_5 = new JPanel();
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					if (havemoney >= Integer.parseInt(textField_1.getText())) {
						double y = 0;
						double a = 0;

						y = Double.parseDouble(label_3.getText());

						int c = Integer.parseInt(textField_1.getText());

						a = y * c;

						result = 3;

						lblNewLabel_8.setText(String.valueOf((int) a));
					} else {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셧습니다");
					}
				} catch (Exception gg) {
					JOptionPane.showMessageDialog(null, "배팅할 금액을 입력해주세요");
				}

			}
		});
		panel_5.setBackground(Color.WHITE);
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(448, 91, 162, 37);
		panel_2.add(panel_5);

		JLabel label_2 = new JLabel("3번 " + vo[2].getAni_name());
		label_2.setForeground(Color.BLACK);
		label_2.setBackground(Color.BLACK);
		label_2.setFont(new Font("양재붓꽃체L", Font.BOLD, 12));
		label_2.setBounds(12, 10, 82, 15);
		panel_5.add(label_2);

		panel_5.add(label_3);

		JLabel label_5 = new JLabel(String.valueOf(11 - vo[3].getAni_speed()));
		label_5.setForeground(Color.BLACK);
		label_5.setBackground(Color.BLACK);
		label_5.setFont(new Font("양재붓꽃체L", Font.BOLD, 12));
		label_5.setBounds(114, 10, 36, 15);

		JPanel panel_6 = new JPanel();
		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (havemoney >= Integer.parseInt(textField_1.getText())) {
						double y = 0;
						double a = 0;

						y = Double.parseDouble(label_5.getText());

						int c = Integer.parseInt(textField_1.getText());

						a = y * c;

						result = 4;

						lblNewLabel_8.setText(String.valueOf((int) a));

					} else {
						JOptionPane.showMessageDialog(null, "보유금액을 초과하셧습니다");
					}
				} catch (Exception gg) {
					JOptionPane.showMessageDialog(null, "배팅할 금액을 입력해주세요");
				}

			}
		});

		panel_6.setBackground(Color.WHITE);
		panel_6.setLayout(null);
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(610, 91, 162, 37);
		panel_2.add(panel_6);

		JLabel label_4 = new JLabel("4번 " + vo[3].getAni_name());
		label_4.setForeground(Color.BLACK);
		label_4.setBackground(Color.BLACK);
		label_4.setFont(new Font("양재붓꽃체L", Font.BOLD, 12));
		label_4.setBounds(12, 10, 82, 15);
		panel_6.add(label_4);

		panel_6.add(label_5);

		JPanel panel = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);

				g.drawImage(icon.getImage(), 0, 0, null);

			}

		};

		btnNewButton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lblNewLabel_8.getText().equals("0")) {
					JOptionPane.showMessageDialog(null, "1등을 골라주세요");
				} else {
					start = 1;
					JOptionPane.showMessageDialog(null, "스타트!");
					Racing_BetVO rb = new Racing_BetVO();
					rb.setCust_id(user_id);

					if (result == 1) {
						rb.setAni_code(vo[0].getAni_code());
						rb.setRacing_bet_multiple(11 - vo[0].getAni_speed());
					} else if (result == 2) {
						rb.setAni_code(vo[1].getAni_code());
						rb.setRacing_bet_multiple(11 - vo[1].getAni_speed());
					} else if (result == 3) {
						rb.setAni_code(vo[2].getAni_code());
						rb.setRacing_bet_multiple(11 - vo[2].getAni_speed());
					} else if (result == 4) {
						rb.setAni_code(vo[3].getAni_code());
						rb.setRacing_bet_multiple(11 - vo[3].getAni_speed());
					}

					rb.setRacing_price(Integer.parseInt(textField_1.getText()));
					rb.setRacing_prize(Integer.parseInt(lblNewLabel_8.getText()));

					try {
						RacingModel rm = new RacingModel();

						rm.racingbetting(rb);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						RacingModel rm = new RacingModel();
						rb2 = new Racing_BetVO();

						rb2 = rm.racingcode(user_id);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		panel.setBounds(23, 21, 1204, 544);
		getContentPane().add(panel);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("경주 레이스");
		setSize(1622, 790);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width / 2) - (this.getWidth() / 2), (dim.height / 2) - (this.getHeight() / 2));
		this.setLocationRelativeTo(null);
		//setLocation(100, 100);
		setVisible(true);
	}

	@Override

	public void run() {

		
			try {

				while (!finish) {

					repaint();
					Thread.sleep(100);
					time = System.currentTimeMillis();
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
					String str = sdf.format(new Date(time));
					lblNewLabel.setText(str);
					if (start == 1) {
						if(vo[0].getAni_speed()<6){
						x = x + (int) (Math.random() * vo[0].getAni_speed() * 10);
						}else
					    x = x + (int) (Math.random() * vo[0].getAni_speed() * 7);
						
						if(vo[1].getAni_speed()<6){
						y = y + (int) (Math.random() * vo[1].getAni_speed() * 10);
						}else
					    y = y + (int) (Math.random() * vo[1].getAni_speed() * 7);
						
						if(vo[2].getAni_speed()<6){
						z = z + (int) (Math.random() * vo[2].getAni_speed() * 10);
						}else 
						z = z + (int) (Math.random() * vo[2].getAni_speed() * 7);
						
						if(vo[3].getAni_speed()<6){
						w = w + (int) (Math.random() * vo[3].getAni_speed() * 10);
						}else 
						w = w + (int) (Math.random() * vo[3].getAni_speed() * 7);

					}

					if (x > 1000) {
						JOptionPane.showMessageDialog(null, vo[0].getAni_name() + "승리!");
						RacingVO ra = new RacingVO();

						// ra.setAni_code(vo[0].getAni_code());
						// RacingModel rm = new RacingModel();
						// rm.racing(ra);

						if (result == 1) {
							JOptionPane.showMessageDialog(null, "적중!!");
							RacingVO rac = new RacingVO();
							RacingModel rm = new RacingModel();
							rac.setRacing_bet_code(rb2.getRacing_bet_code());
							rac.setWinner_code(vo[0].getAni_code());
							rm.racingresult(rac);

							rm.racingupdate(rb2.getRacing_bet_code());

							rm.racingmoney(user_id, Integer.parseInt((lblNewLabel_8.getText())));

						} else {
							JOptionPane.showMessageDialog(null, "미적중!!");
							RacingVO rac = new RacingVO();
							RacingModel rm = new RacingModel();
							rac.setRacing_bet_code(rb2.getRacing_bet_code());
							rac.setWinner_code(vo[0].getAni_code());
							rm.racingresult(rac);

							rm.racingmoneym(user_id, Integer.parseInt((textField_1.getText())));

						}

						break;

					} else if (y > 1000) {
						JOptionPane.showMessageDialog(null, vo[1].getAni_name() + "승리!");

						RacingVO ra = new RacingVO();

						// ra.setAni_code(vo[1].getAni_code());
						// RacingModel rm = new RacingModel();
						// rm.racing(ra);

						if (result == 2) {
							JOptionPane.showMessageDialog(null, "적중!!");
							RacingVO rac = new RacingVO();
							RacingModel rm = new RacingModel();
							rac.setRacing_bet_code(rb2.getRacing_bet_code());
							rac.setWinner_code(vo[1].getAni_code());

							 rm.racingresult(rac);

							rm.racingupdate(rb2.getRacing_bet_code());
							rm.racingmoney(user_id, Integer.parseInt((lblNewLabel_8.getText())));

						} else {
							JOptionPane.showMessageDialog(null, "미적중!!");
							RacingVO rac = new RacingVO();
							RacingModel rm = new RacingModel();
							rac.setRacing_bet_code(rb2.getRacing_bet_code());
							rac.setWinner_code(vo[1].getAni_code());
							rm.racingresult(rac);
							rm.racingmoneym(user_id, Integer.parseInt((textField_1.getText())));
						}
						break;

					} else if (z > 1000) {
						JOptionPane.showMessageDialog(null, vo[2].getAni_name() + "승리!");
						RacingVO ra = new RacingVO();

						// ra.setAni_code(vo[2].getAni_code());
						// RacingModel rm = new RacingModel();
						// rm.racing(ra);

						if (result == 3) {
							JOptionPane.showMessageDialog(null, "적중!!");
							RacingVO rac = new RacingVO();
							RacingModel rm = new RacingModel();
							rac.setRacing_bet_code(rb2.getRacing_bet_code());
							rac.setWinner_code(vo[2].getAni_code());

							rm.racingresult(rac);

							rm.racingupdate(rb2.getRacing_bet_code());
							rm.racingmoney(user_id, Integer.parseInt((lblNewLabel_8.getText())));

						} else {
							JOptionPane.showMessageDialog(null, "미적중!!");
							RacingVO rac = new RacingVO();
							RacingModel rm = new RacingModel();
							rac.setRacing_bet_code(rb2.getRacing_bet_code());
							rac.setWinner_code(vo[2].getAni_code());
							rm.racingresult(rac);
							rm.racingmoneym(user_id, Integer.parseInt((textField_1.getText())));
						}

						break;
					} else if (w > 1000) {
						JOptionPane.showMessageDialog(null, vo[3].getAni_name() + "승리!");
						RacingVO ra = new RacingVO();

						// ra.setAni_code(vo[3].getAni_code());
						// RacingModel rm = new RacingModel();
						// rm.racing(ra);

						if (result == 4) {
							JOptionPane.showMessageDialog(null, "적중!!");
							RacingVO rac = new RacingVO();
							RacingModel rm = new RacingModel();
							rac.setRacing_bet_code(rb2.getRacing_bet_code());
							rac.setWinner_code(vo[3].getAni_code());

							rm.racingresult(rac);

							rm.racingupdate(rb2.getRacing_bet_code());
							rm.racingmoney(user_id, Integer.parseInt((lblNewLabel_8.getText())));

						} else {
							JOptionPane.showMessageDialog(null, "미적중!!");
							RacingVO rac = new RacingVO();
							RacingModel rm = new RacingModel();
							rac.setRacing_bet_code(rb2.getRacing_bet_code());
							rac.setWinner_code(vo[3].getAni_code());
							rm.racingresult(rac);
							rm.racingmoneym(user_id, Integer.parseInt((textField_1.getText())));
						}

						break;
					}

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			finish = true;
		}
}


package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.CouponModel;
import model.CustomerModel;
import model.EplModel;
import model.KBOModel;
import model.ListModel;
import model.TeamModel;
import model.rec.KBOBetVO;
import model.rec.ScBetVO;

public class Toto extends JFrame {
	private Font font = new Font("����ײ�äL", Font.BOLD, 18);

	private CustomerView customer;
	private GameView game;
	private SportsView sports;
	private BettingListView bettingList;

	private String user_ID;
	private int myMoney = 0;
	private int myPoint = 0;

	private ImageIcon img_logo = new ImageIcon("img/user_logo.png");
	private JLabel lbLogo = new JLabel();
	private JLabel lbID = new JLabel();
	private JLabel lbMoney = new JLabel();
	private JLabel lbPoint = new JLabel();

	private JButton bRecharge = new JButton();
	private JButton bExchange = new JButton();
	private JButton bLogOut = new JButton();

	private rechargeListTable rTable;
	private exchangeListTable eTable;
	private JTable table1, table2;

	CustomerModel model;
	ListModel blmodel; // ���ø���Ʈ��

	public Toto(String user_ID) {
		TotoEventHandler evt = new TotoEventHandler();
		try {
			model = new CustomerModel();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.addWindowFocusListener(evt);
		setLayout(null);
		this.user_ID = user_ID;

		customer = new CustomerView();
		game = new GameView();
		sports = new SportsView();
		bettingList = new BettingListView();

		getInfo();
		lbLogo.setIcon(img_logo);
		lbLogo.setBounds(5, 10, 25, 25);
		lbLogo.setFont(font);
		add(lbLogo);

		lbID.setText(user_ID);
		lbID.setBounds(30, 0, 150, 50);
		lbID.setFont(font);
		add(lbID);

		lbMoney = new JLabel();
		lbMoney.setBounds(230, 0, 150, 50);
		lbMoney.setFont(font);
		add(lbMoney);

		lbPoint = new JLabel();
		lbPoint.setBounds(360, 0, 150, 50);
		lbPoint.setFont(font);
		add(lbPoint);

		bRecharge = new JButton(new ImageIcon("img/bt_charge.png"));
		bRecharge.setBounds(500, 5, 120, 60);
		bRecharge.setBorderPainted(false);
		bRecharge.setFocusPainted(false);
		bRecharge.setContentAreaFilled(false);
		bRecharge.addActionListener(evt);

		add(bRecharge);

		bExchange = new JButton(new ImageIcon("img/bt_exchange.png"));
		bExchange.setBounds(625, 5, 120, 60);
		bExchange.setBorderPainted(false);
		bExchange.setFocusPainted(false);
		bExchange.setContentAreaFilled(false);
		bExchange.addActionListener(evt);

		add(bExchange);

		bLogOut = new JButton(new ImageIcon("img/bt_logout.png"));
		bLogOut.setBounds(750, 5, 120, 60);
		bLogOut.setBorderPainted(false);
		bLogOut.setFocusPainted(false);
		bLogOut.setContentAreaFilled(false);
		bLogOut.addActionListener(evt);

		add(bLogOut);

		JTabbedPane pJTab = new JTabbedPane();
		pJTab.setFont(font);

		pJTab.addTab("���������", sports);
		pJTab.addTab("�ǽð�����", game);
		pJTab.addTab("���ó���", bettingList);
		pJTab.addTab("����������", customer);

		pJTab.setSelectedIndex(0);
		pJTab.setForeground(Color.WHITE);
		pJTab.setBounds(0, 35, 900, 600);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(900, 600);
		setLocationByPlatform(true);
		setLocation(400, 150);
		setResizable(false);

		add(pJTab);

		ImageIcon img = new ImageIcon("img/logo.png");
		setIconImage(img.getImage());
		setTitle("ToTo");

		setVisible(true);

	}

	public void getInfo() {
		try {
			myMoney = model.getMoney(user_ID);
			myPoint = model.getPoint(user_ID);

			lbMoney.setText(String.format("��%,d", myMoney));
			lbPoint.setText(String.format("%,d", myPoint) + "P");

			rTable.data = blmodel.recharge(user_ID);
			eTable.data = blmodel.exchange(user_ID);

			table1.setModel(rTable);
			table2.setModel(eTable);
			rTable.fireTableDataChanged();
			eTable.fireTableDataChanged();

		
			
			EplModel em = new EplModel();
			ArrayList<ScBetVO> sb = new ArrayList<ScBetVO>();
			sb=em.prize(user_ID);
			System.out.println(sb.get(0).getSc_bet_code()+"����");
			System.out.println(sb.get(1).getSc_bet_code()+"gkk");
			System.out.println(sb.get(2).getSc_bet_code()+"����");
			System.out.println(sb.get(3).getSc_bet_code()+"ȣȣ");
			
			for(int i=0;i<sb.size();i++){
			em.update_betY(sb.get(i).getSc_bet_code());
			em.changeMoney(sb.get(i).getSc_bet_code(),user_ID);
			}
			

			// ���⼭ ������Ʈ	
			KBOModel m = new KBOModel();
			m.checkSelectedTeam(user_ID);

		} catch (Exception e) {
		}

	}

	public class CustomerView extends JPanel {

		private JLabel lbID = new JLabel("���̵�");
		private JTextField tfID = new JTextField();

		private JLabel lbPW = new JLabel("��й�ȣ");
		private JPasswordField tfPW = new JPasswordField();

		private JLabel lbExPW = new JLabel("ȯ���� ��й�ȣ");
		private JPasswordField tfExPW = new JPasswordField();

		private JLabel lbName = new JLabel("�̸�");
		private JTextField tfName = new JTextField();

		private JLabel lbTel = new JLabel("����ó");
		private JTextField tfTel = new JTextField();

		private JLabel lbAdr = new JLabel("�ּ�");
		private JTextField tfAdr = new JTextField();

		private JLabel lbMoney = new JLabel("�����ܾ�");
		private JTextField tfMoney = new JTextField();

		private JLabel lbPoint = new JLabel("���� ����Ʈ");
		private JTextField tfPoint = new JTextField();

		private JLabel lbBankName = new JLabel("�����");
		private JTextField tfBankName = new JTextField();

		private JLabel lbAccNum = new JLabel("���¹�ȣ");
		private JTextField tfAccNum = new JTextField();

		private JPanel pInfo = new JPanel(new GridLayout(0, 2, 5, 5));
		private JPanel jpCharge = new JPanel(); // �������� �����ִ��г�
		private JPanel jpExchange = new JPanel(); // ȯ������ �����ִ� �г�

		TitledBorder tb1 = new TitledBorder(new LineBorder(Color.white));
		private Font font = new Font("����ײ�äL", Font.BOLD, 18);

		JScrollPane jsp1, jsp2;

		public CustomerView() {
			ArrayList custInfoList = new ArrayList();

			try {

				model = new CustomerModel();
				custInfoList = model.getInfo(user_ID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			setLayout(null);

			rTable = new rechargeListTable();
			eTable = new exchangeListTable();

			table1 = new JTable(rTable);
			jsp1 = new JScrollPane(table1);

			table2 = new JTable(eTable);
			jsp2 = new JScrollPane(table2);

			pInfo.setBorder(tb1);
			jpCharge.setBorder(tb1);
			jpExchange.setBorder(tb1);

			lbID.setFont(font);
			tfID.setFont(font);
			tfID.setEditable(false);
			tfID.setText((String) custInfoList.get(0));

			pInfo.add(lbID);
			pInfo.add(tfID);

			lbPW.setFont(font);
			tfPW.setFont(font);
			tfPW.setEditable(false);
			tfPW.setText((String) custInfoList.get(1));

			pInfo.add(lbPW);
			pInfo.add(tfPW);

			lbExPW.setFont(font);
			tfExPW.setFont(font);
			tfExPW.setEditable(false);
			tfExPW.setText((String) custInfoList.get(2));

			pInfo.add(lbExPW);
			pInfo.add(tfExPW);

			lbName.setFont(font);
			tfName.setFont(font);
			tfName.setEditable(false);
			tfName.setText((String) custInfoList.get(3));

			pInfo.add(lbName);
			pInfo.add(tfName);

			lbTel.setFont(font);
			tfTel.setFont(font);
			tfTel.setEditable(false);
			tfTel.setText((String) custInfoList.get(4));

			pInfo.add(lbTel);
			pInfo.add(tfTel);

			lbAdr.setFont(font);
			tfAdr.setFont(font);
			tfAdr.setEditable(false);
			tfAdr.setText((String) custInfoList.get(5));

			pInfo.add(lbAdr);
			pInfo.add(tfAdr);

			lbMoney.setFont(font);
			tfMoney.setFont(font);
			tfMoney.setEditable(false);
			tfMoney.setText(String.valueOf(custInfoList.get(6)));

			pInfo.add(lbMoney);
			pInfo.add(tfMoney);

			lbPoint.setFont(font);
			tfPoint.setFont(font);
			tfPoint.setEditable(false);
			tfPoint.setText(String.valueOf(custInfoList.get(7)));

			pInfo.add(lbPoint);
			pInfo.add(tfPoint);

			lbAccNum.setFont(font);
			tfAccNum.setFont(font);
			tfAccNum.setEditable(false);
			tfAccNum.setText((String) custInfoList.get(8));

			pInfo.add(lbAccNum);
			pInfo.add(tfAccNum);

			lbBankName.setFont(font);
			tfBankName.setFont(font);
			tfBankName.setEditable(false);
			tfBankName.setText((String) custInfoList.get(9));

			pInfo.add(lbBankName);
			pInfo.add(tfBankName);

			pInfo.setBounds(13, 10, 370, 473);
			jpCharge.setBounds(390, 10, 480, 240);
			jpExchange.setBounds(390, 245, 480, 240);

			add(pInfo);
			jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jsp1.setPreferredSize(new Dimension(470, 230));
			jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jsp2.setPreferredSize(new Dimension(470, 230));

			try {
				blmodel = new ListModel();

				rTable.data = blmodel.recharge(user_ID);
				eTable.data = blmodel.exchange(user_ID);

				table1.setModel(rTable);
				table2.setModel(eTable);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			jpCharge.add(jsp1);
			jpExchange.add(jsp2);
			add(jpCharge);
			add(jpExchange);

		}

	}


	class ExchangeView extends JDialog {
		private Font font = new Font("����ײ�äL", Font.BOLD, 14);
		private JLabel lbTitle = new JLabel("ȯ�� ��û��");

		private JLabel lbPoint = new JLabel("���� ����Ʈ");
		private JTextField tfPoint = new JTextField();
		private int ex_myPoint = 0;

		private int sumPrice = 0;
		private int[] price = { 1, 3, 5, 10, 20, 30, 50, 100, 200, 300 };
		private JLabel lbPrice = new JLabel("ȯ�� �ݾ�");
		private JTextField tfPrice = new JTextField();
		private JButton[] bPrice = new JButton[11];

		private JButton bSubmit = new JButton("ȯ�� ��û");

		public ExchangeView() {

			ExchangeEventHandler evt = new ExchangeEventHandler();
			setLayout(null);
			setTitle("ȯ���޴�");

			lbTitle.setFont(new Font("����ײ�äL", Font.BOLD, 48));
			lbTitle.setBounds(30, 10, 400, 50);
			add(lbTitle);

			lbPoint.setBounds(80, 80, 100, 30);
			lbPoint.setFont(font);
			add(lbPoint);

			tfPoint.setBounds(180, 80, 250, 30);
			tfPoint.setFont(font);
			try {
				ex_myPoint = model.getPoint(user_ID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tfPoint.setText(String.format("%,d", ex_myPoint) + "P");
			tfPoint.setEditable(false);
			add(tfPoint);

			lbPrice.setBounds(80, 120, 100, 30);
			lbPrice.setFont(font);
			add(lbPrice);

			tfPrice.setBounds(180, 120, 250, 30);
			tfPrice.setFont(font);
			tfPrice.setText("��0");
			tfPrice.setEditable(false);
			add(tfPrice);

			for (int i = 0; i < 11; i++) {
				if (i == 10)
					bPrice[i] = new JButton("����");
				else
					bPrice[i] = new JButton(price[i] + "����");

				bPrice[i].setBounds(80 + (80 * i), 160, 80, 30);
				bPrice[i].setFont(font);
				bPrice[i].addActionListener(evt);
				add(bPrice[i]);

			}

			bSubmit.setBounds(400, 250, 150, 50);
			bSubmit.setFont(font);
			bSubmit.addActionListener(evt);

			add(bSubmit);

			setSize(1000, 400);
			setLocation(500, 300);
			setVisible(true);
			setResizable(false);
		}

		class ExchangeEventHandler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if (obj == bSubmit) {
					try {
						model.exchange(user_ID, sumPrice);
						model.getExchange(user_ID, sumPrice);

						sumPrice = 0;
						ex_myPoint = model.getPoint(user_ID);
						tfPrice.setText(String.format("��%,d", sumPrice));
						tfPoint.setText(String.format("%,d", ex_myPoint) + "P");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				for (int i = 0; i < 11; i++) {
					if (obj == bPrice[i]) {
						if (i == 10) {
							sumPrice = 0;
						} else {
							if (sumPrice + price[i] * 10000 <= ex_myPoint) {
								sumPrice += price[i] * 10000;
							} else {
								JOptionPane.showMessageDialog(null, "��û�ݾ��� ���� ����Ʈ���� �����ϴ�.");
							}

						}
						tfPrice.setText(String.format("��%,d", sumPrice));
					}
				}
			}

		}
	}// end of ExchangeView CLASS

	class TotoEventHandler implements ActionListener, WindowFocusListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if (obj == bRecharge) {
				new RechargeView(user_ID);
			} else if (obj == bExchange) {
				new ExchangeView();
			} else if (obj == bLogOut) {
				new LoginView();
				setVisible(false);
			}
		}

		@Override
		public void windowGainedFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			getInfo();
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
		}

	}

	// ���ø���Ʈ
	class BettingListView extends JPanel {
		BettingListEventHandler blEvent = new BettingListEventHandler();
		private JButton btLadder_bet, btLotto_bet, btRace_bet, btKBO_bet, btEPL_bet;
		private JTable table;
		private Font font = new Font("����ײ�äL", Font.BOLD, 15);
		BetListTable BLT; // ���ø���Ʈ J���̺��
		JScrollPane jsp;

		public BettingListView() {

			setLayout(null);

			btLadder_bet = new JButton();// ��ٸ� ���ó���
			btLotto_bet = new JButton();// �ζ� ���ó���
			btRace_bet = new JButton();// ���� ���ó���
			btKBO_bet = new JButton();// KBO ���ó���
			btEPL_bet = new JButton();// EPL ���ó���
			BLT = new BetListTable();
			table = new JTable(BLT);
			jsp = new JScrollPane(table);

			btLadder_bet.setFont(font);
			btLotto_bet.setFont(font);
			btRace_bet.setFont(font);
			btKBO_bet.setFont(font);
			btEPL_bet.setFont(font);

			btLadder_bet.setIcon(new ImageIcon("img/��ٸ����ó�����ư.png"));
			btLotto_bet.setIcon(new ImageIcon("img/Lotto���ó�����ư.png"));
			btRace_bet.setIcon(new ImageIcon("img/���ֹ��ó�����ư.png"));
			btKBO_bet.setIcon(new ImageIcon("img/KBO���ó�����ư.png"));
			btEPL_bet.setIcon(new ImageIcon("img/EPL���ó�����ư.png"));

			btKBO_bet.setBounds(60, 15, 130, 53);
			btEPL_bet.setBounds(220, 15, 130, 53);
			btLadder_bet.setBounds(380, 15, 130, 53);
			btLotto_bet.setBounds(540, 15, 130, 53);
			btRace_bet.setBounds(700, 15, 130, 53);

			table.setBounds(50, 150, 500, 250);

			btLadder_bet.addActionListener(blEvent);
			btLotto_bet.addActionListener(blEvent);
			btRace_bet.addActionListener(blEvent);
			btKBO_bet.addActionListener(blEvent);
			btEPL_bet.addActionListener(blEvent);

			add(btLadder_bet);
			add(btLotto_bet);
			add(btRace_bet);
			add(btKBO_bet);
			add(btEPL_bet);

			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jsp.setBounds(50, 85, 800, 385);
			jsp.setOpaque(false);
			jsp.getViewport().setOpaque(false);
			add(jsp);

		}

		class BettingListEventHandler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object obj = e.getSource();
				if (obj == btLadder_bet) {
					try {
						blmodel = new ListModel();

						BLT.data = blmodel.ladder_bet(user_ID);
						table.setModel(BLT);
						BLT.fireTableDataChanged();

					} catch (Exception ex) {
						ex.printStackTrace();
					}

					String[] strs = { "���� ��¥", "��ٸ� ����", "���� �ݾ�", "���� ����", "����", "���� �ݾ�" };
					BLT.setTitle(strs);
					table.setModel(BLT);
					BLT.fireTableStructureChanged();

				}
				if (obj == btLotto_bet) {
					// �ζ� ���ó���
					try {
						blmodel = new ListModel();

						BLT.data = blmodel.lotto_bet(user_ID);
						table.setModel(BLT);
						BLT.fireTableDataChanged();

					} catch (Exception ex) {
						ex.printStackTrace();
					}

					String[] strs = { "Lotto ��¥", "Lotto ȸ��", "��÷ ����", "���� �ݾ�" };
					BLT.setTitle(strs);
					table.setModel(BLT);
					BLT.fireTableStructureChanged();

				}
				if (obj == btRace_bet) {
					// ���� ���ó���
					try {
						blmodel = new ListModel();

						BLT.data = blmodel.race_bet(user_ID);
						table.setModel(BLT);
						BLT.fireTableDataChanged();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					String[] strs = { "���� ��¥", "���� ����", "���� �ݾ�", "���� ����", "����", "���� �ݾ�" };
					BLT.setTitle(strs);
					table.setModel(BLT);
					BLT.fireTableStructureChanged();
				}
				if (obj == btKBO_bet) {
					// KBO ���ó���
					try {
						blmodel = new ListModel();

						BLT.data = blmodel.KBO_bet(user_ID);
						table.setModel(BLT);
						BLT.fireTableDataChanged();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					String[] strs = { "���� ��¥", "Ȩ��", "������", "������", "���� �ݾ�", "���� ����", "����", "���� �ݾ�" };
					BLT.setTitle(strs);
					table.setModel(BLT);
					BLT.fireTableStructureChanged();
				}

				if (obj == btEPL_bet) {
					// EPL ���ó���
					try {
						blmodel = new ListModel();

						BLT.data = blmodel.EPL_bet(user_ID);
						table.setModel(BLT);
						BLT.fireTableDataChanged();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					String[] strs = { "���� ��¥", "Ȩ��", "������", "������", "���� �ݾ�", "���� ����", "����", "���� �ݾ�" };
					BLT.setTitle(strs);
					table.setModel(BLT);
					BLT.fireTableStructureChanged();
				}

			}

		}

	}

	class GameView extends JPanel {
		GameViewEventHandler evt = new GameViewEventHandler();
		private final int btn_cnt = 3;

		private JPanel pMain = new JPanel();

		String[] paths = { "img/icon_ladder.png", "img/icon_lotto.png", "img/icon_race.png" };
		private Image bg_game = Toolkit.getDefaultToolkit().createImage("img/bg_sports.jpg");

		private ImageIcon[] originIcons = new ImageIcon[btn_cnt];
		private Image[] originImgs = new Image[btn_cnt];
		private Image[] changedImgs = new Image[btn_cnt];
		private ImageIcon[] icons = new ImageIcon[btn_cnt];

		private JButton[] bPlayGame = new JButton[btn_cnt];
		// bPlayGame[0] : ��ٸ�
		// bPlayGame[1] : �ζ�
		// bPlayGame[2] : ����

		public GameView() {
			setLayout(null);
			pMain.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
			pMain.setOpaque(false);
			pMain.setBounds(0, 150, 900, 600);

			for (int i = 0; i < btn_cnt; i++) {
				originIcons[i] = new ImageIcon(paths[i]);
				originImgs[i] = originIcons[i].getImage();
				changedImgs[i] = originImgs[i].getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				icons[i] = new ImageIcon(changedImgs[i]);
				bPlayGame[i] = new JButton(icons[i]);
				bPlayGame[i].setBorderPainted(false);
				bPlayGame[i].setContentAreaFilled(false);
				bPlayGame[i].setFocusPainted(false);
				bPlayGame[i].addActionListener(evt);
				pMain.add(bPlayGame[i]);
			}
			add(pMain);

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (bg_game != null) {
				g.drawImage(bg_game, 0, 0, this);
			}
		}

		class GameViewEventHandler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object obj = e.getSource();
				if (obj == bPlayGame[0]) {
					Thread th = new Thread(new LadderView(user_ID));
					th.start();
				} else if (obj == bPlayGame[1]) {

					new lottoPaper(user_ID);
				} else if (obj == bPlayGame[2]) {
					Thread th = new Thread(new Race(user_ID));
					th.start();
				}
			}
		}
	}

	class SportsView extends JPanel {
		SportsViewEventHandler evt = new SportsViewEventHandler();
		private final int btn_cnt = 2;
		private JPanel pMain = new JPanel();

		String[] paths = { "img/icon_kbo.png", "img/icon_epl.png" };
		private Image bg_Sports = Toolkit.getDefaultToolkit().createImage("img/bg_Sports.jpg");
		private ImageIcon[] originIcons = new ImageIcon[btn_cnt];
		private Image[] originImgs = new Image[btn_cnt];
		private Image[] changedImgs = new Image[btn_cnt];
		private ImageIcon[] icons = new ImageIcon[btn_cnt];

		private JButton[] bPlaySports = new JButton[btn_cnt];

		public SportsView() {
			setLayout(null);
			pMain.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
			pMain.setOpaque(false);
			pMain.setBounds(0, 150, 900, 600);

			for (int i = 0; i < btn_cnt; i++) {
				originIcons[i] = new ImageIcon(paths[i]);
				originImgs[i] = originIcons[i].getImage();
				changedImgs[i] = originImgs[i].getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				icons[i] = new ImageIcon(changedImgs[i]);
				bPlaySports[i] = new JButton(icons[i]);
				bPlaySports[i].setBorderPainted(false);
				bPlaySports[i].setContentAreaFilled(false);
				bPlaySports[i].setFocusPainted(false);
				bPlaySports[i].addActionListener(evt);
				pMain.add(bPlaySports[i]);
			}
			add(pMain);
			setSize(1800, 800);

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (bg_Sports != null) {
				g.drawImage(bg_Sports, 0, 0, this);
			}
		}

		class SportsViewEventHandler implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object obj = e.getSource();
				try {
					TeamModel tModel = new TeamModel();
				if (obj == bPlaySports[0]) {
					tModel.updateKBOData();
					tModel.updateMatchData("KBO");
					new KBOView(user_ID);
				} else if (obj == bPlaySports[1]) {
					tModel.updateSCData();
					tModel.updateMatchData("EPL");
					tModel.updateMatchData("BUNDE");
					new EPLview(user_ID);
				}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}
	}

	// ���ø���Ʈ ���̺�
	class BetListTable extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] title = { " �ش��ϴ� ������ ���ø���Ʈ �Ǵ� ������������ ���ø���Ʈ�� �������ּ���! " };

		public int getColumnCount() {

			return title.length;
		}

		public int getRowCount() {

			return data.size();
		}

		public void setTitle(String[] strs) {
			this.title = strs;
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		// ������ �÷��� ��ȯ�ϱ�
		public String getColumnName(int col) {
			return title[col];
		}

	}

	// ��������Ʈ ���̺�
	class rechargeListTable extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] title = { "���� ��¥", "���� �ݾ� " };

		public int getColumnCount() {

			return title.length;
		}

		public int getRowCount() {

			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		// ������ �÷��� ��ȯ�ϱ�
		public String getColumnName(int col) {
			return title[col];
		}

	}

	// ȯ������Ʈ ���̺�
	class exchangeListTable extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] title = { "ȯ�� ��¥", "ȯ�� �ݾ� " };

		public int getColumnCount() {

			return title.length;
		}

		public int getRowCount() {

			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		// ������ �÷��� ��ȯ�ϱ�
		public String getColumnName(int col) {
			return title[col];
		}

	}

}

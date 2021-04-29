package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import model.LottoModel;
import model.rec.LottoBetVO;
import model.rec.LottoVO;

public class lottoGame extends JFrame {

	private JLabel[] boll; // ���ư��� �� �̹���
	private ImageIcon[] img; // �����

	private JLabel machin; // �ڿ� ���
	private JLabel backImg, paper; // �� ���,��÷ ��ȣ�� ���

	private JLayeredPane layeredPane;
	private int[] arr; // ���� ��û ��ȣ
	private JButton click[]; // Ŭ���ϸ� ��÷��ȣ�� ���Ϳ�!
	private int count = 0; // 5���� �Ǹ� ��÷�ݾ��� �������� ����
	private JLabel cnt; // ��ȸ������
	private LottoVO lottovo;
	private String user_id;

	// �����Է��� ��ȣ,��÷��ȣ
	private int myNum[] = new int[5];
	private int lottoNum[] = new int[5];
	private int winCount = 0;
	private int temp = -1;

	// ������
	public lottoGame(LottoVO lottovo, String user_id) {

		setTitle("������ �ζ�");

		this.lottovo = lottovo;
		this.user_id = user_id;
		ImageIcon img_logo = new ImageIcon("img/logo.png");
		setIconImage(img_logo.getImage());

		setSize(530, 670);
		setLayout(null);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width / 2) - (this.getWidth() / 2), (dim.height / 2) - (this.getHeight() / 2));
		this.setLocationRelativeTo(null);

		result();
		lottoBallImage(); // �ζ� �̹��� ������ ��÷��ȣ ������ �޼ҵ�

		setVisible(true);
	}

	public void lottoBallImage() {

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 510, 641);
		layeredPane.setLayout(null);

		boll = new JLabel[20];

		int cnt = 0;

		for (int x = 0; x < boll.length; x++) {
			cnt++;
			boll[x] = new JLabel(new ImageIcon("lottoimg\\ball_" + cnt + ".png"));
		}

		for (int x = 0; x < boll.length; x++) {
			layeredPane.add(boll[x]);
		}

		new gameThread().start();

		// ��÷��ȣ �ڿ� ���
		paper = new JLabel(new ImageIcon("lottoimg\\��÷��ȣ����.png"));
		paper.setBounds(-35, 450, 582, 197);
		layeredPane.add(paper);

		// �ζ� ��ȣ�ڿ� ���
		machin = new JLabel(new ImageIcon("lottoimg\\�ζǱ��.png"));
		machin.setBounds(30, 1, 450, 606);
		layeredPane.add(machin);

		// �ζǹ�ȣ �ڿ� ���
		backImg = new JLabel(new ImageIcon("lottoimg\\�ζǱ�� ���.png"));
		backImg.setBounds(0, 0, 510, 641);
		layeredPane.add(backImg);

		add(layeredPane);
	}

	private class gameThread extends Thread {
		Random random = new Random();

		public void run() {

			while (true) {
				try {
					Thread.sleep(110);

				} catch (Exception e) {
					System.out.println("�ζ� �����ſ��� ����");
				}

				for (int x = 0; x < 20; x++) {
					boll[x].setBounds(random.nextInt(210) + 100, random.nextInt(210) + 40, 91, 90);
				}
			}
		}
	}

	// �������� �ϴ� ������~
	public void result() {

		Random random = new Random();

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(0, 1, 700, 700);
		arr = new int[5]; // �ζǹ�ȣ ���� �迭

		for (int x = 0; x < 5; x++) {
			arr[x] = random.nextInt(19); // 5������ �������� �ִ´�.

			for (int y = 0; y < x; y++) {

				if (arr[y] == arr[x]) { // �ߺ�����
					x--;
					break;
				}
			}
		}

		for (int x = 0; x < 5; x++) {
			Arrays.sort(arr); // �ڵ� ���� �ѹ�!
			System.out.println(arr[x]);
		}
		img = new ImageIcon[20];

		int imgCnt = 0;
		for (int x = 0; x < img.length; x++) {
			imgCnt++;
			img[x] = new ImageIcon("lottoimg\\ball_" + imgCnt + ".png");
		}
		click = new JButton[5];
		int xLine = 0;
		for (int x = 0; x < 5; x++) {

			click[x] = new JButton(new ImageIcon("lottoimg\\����ǥ.png"));
			xLine += 75;
			click[x].setBounds(xLine, 540, 53, 53);
			layeredPane.add(click[x]);
			click[x].setFocusPainted(false);
		}

		for (int x = 0; x < click.length; x++) {

			click[x].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();

					for (int i = 0; i < click.length; i++) {
						if (obj == click[i]) {
							click[i].setIcon(img[arr[i]]);
							click[i].setContentAreaFilled(false);
							click[i].setBorderPainted(false);

							if (temp != i) { // �ٸ���ȣ�� ������ ī��Ʈ ����
								count++;
								temp = i;
							}

							// �ɼʶ��� ���ù�ȣ �߰� �ϰ� Ȯ�ι�ư������ ����÷�Ǿ�����? ������!
							if (count == 5) {
								String[] buttons = { "��÷Ȯ��", "����" };
								String n1 = Integer.toString(arr[0] + 1); // �ζ�
																			// ��ȣ
								String n2 = Integer.toString(arr[1] + 1);
								String n3 = Integer.toString(arr[2] + 1);
								String n4 = Integer.toString(arr[3] + 1);
								String n5 = Integer.toString(arr[4] + 1);

								String lotto_number = n1 + "-" + n2 + "-" + n3 + "-" + n4 + "-" + n5;
								for (int lotto = 0; lotto < lottoNum.length; lotto++) {
									lottoNum[lotto] = arr[lotto] + 1; // �ζǹ�ȣ��
																		// ��
								}
								// ���� �Է��� �ζǹ�ȣ
								myNum[0] = Integer.parseInt(lottovo.getNum1());
								myNum[1] = Integer.parseInt(lottovo.getNum2());
								myNum[2] = Integer.parseInt(lottovo.getNum3());
								myNum[3] = Integer.parseInt(lottovo.getNum4());
								myNum[4] = Integer.parseInt(lottovo.getNum5());
								// �ζǿ������̺� ����
								Random random = new Random();
								int lotto_price = ((random.nextInt(500000) + 20000) / 10000) * 10000; // ��÷��

								int result = JOptionPane.showOptionDialog(null,
										"������ ��ȣ�� " + n1 + "  " + n2 + "  " + n3 + "  " + n4 + "  " + n5 + "�Դϴ�!", "�ζ�",
										JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons,
										"�ζ� ��÷");

								System.out.println(lottovo.getNum1() + " " + lottovo.getNum2() + " " + lottovo.getNum3()
										+ " " + lottovo.getNum4() + " " + lottovo.getNum5()); // ����
																								// �Է���
																								// �ζǹ�ȣ

								if (result == JOptionPane.YES_OPTION) { // Ȯ�ι�ư��
																		// ��������
																		// ��÷�Ǿ�����
																		// ���ϸ޼�����
																		// �Բ�
																		// ����!
									try {
										LottoModel lottoM = new LottoModel();
										LottoBetVO lottobet = new LottoBetVO();

										for (int x = 0; x < myNum.length; x++) {
											for (int y = 0; y < myNum.length; y++) {
												if (myNum[x] == lottoNum[y]) {
													winCount++;
												}
											}
										}

										if (winCount == 5) {
											JOptionPane.showMessageDialog(null,
													"1��! " + Integer.toString(lotto_price) + "�� ��÷ �Ǿ����ϴ�.", "�ζǴ�÷",
													JOptionPane.INFORMATION_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, lotto_price);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "Y");
											int yourMoney = lottoM.yourMoney(user_id);
											lottoM.jackpot(user_id, yourMoney, lotto_price); // ��÷��
																								// ����
										} else if (winCount == 4) {
											JOptionPane.showMessageDialog(null, "2��! 100000�� ��÷ �Ǿ����ϴ�.", "�ζǴ�÷",
													JOptionPane.INFORMATION_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, 100000);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "Y");
											int yourMoney = lottoM.yourMoney(user_id);
											lottoM.jackpot(user_id, yourMoney, lotto_price);
										} else if (winCount == 3) {
											JOptionPane.showMessageDialog(null, "3��! 50000�� ��÷ �Ǿ����ϴ�.", "�ζǴ�÷",
													JOptionPane.INFORMATION_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, 50000);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "Y");
											int yourMoney = lottoM.yourMoney(user_id);
											lottoM.jackpot(user_id, yourMoney, lotto_price);
										} else if (winCount == 2) {
											JOptionPane.showMessageDialog(null, "4��! 1000�� ��÷ �Ǿ����ϴ�.", "�ζǴ�÷",
													JOptionPane.INFORMATION_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, 1000);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "Y");
											int yourMoney = lottoM.yourMoney(user_id);
											lottoM.jackpot(user_id, yourMoney, lotto_price);
										} else {
											JOptionPane.showMessageDialog(null, "�� !!", "�ζ� ��",
													JOptionPane.WARNING_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, lotto_price);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "N");
										}

									} catch (Exception e1) {
										e1.printStackTrace();
									}

									System.out.println(winCount);

								}

							}
						}
					}
				}
			});
		}

		add(layeredPane);
	}
}

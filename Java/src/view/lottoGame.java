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

	private JLabel[] boll; // 돌아가느 공 이미지
	private ImageIcon[] img; // 공결과

	private JLabel machin; // 뒤에 기계
	private JLabel backImg, paper; // 뒤 배경,당첨 번호뒤 배경

	private JLayeredPane layeredPane;
	private int[] arr; // 최종 담청 번호
	private JButton click[]; // 클릭하면 당첨번호가 나와요!
	private int count = 0; // 5개가 되면 당첨금액이 나오게할 변수
	private JLabel cnt; // 몇회차인지
	private LottoVO lottovo;
	private String user_id;

	// 내가입력한 번호,당첨번호
	private int myNum[] = new int[5];
	private int lottoNum[] = new int[5];
	private int winCount = 0;
	private int temp = -1;

	// 생성자
	public lottoGame(LottoVO lottovo, String user_id) {

		setTitle("오늘의 로또");

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
		lottoBallImage(); // 로또 이미지 나오고 당첨번호 나오는 메소드

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

		// 당첨번호 뒤에 배경
		paper = new JLabel(new ImageIcon("lottoimg\\당첨번호종이.png"));
		paper.setBounds(-35, 450, 582, 197);
		layeredPane.add(paper);

		// 로또 번호뒤에 기계
		machin = new JLabel(new ImageIcon("lottoimg\\로또기계.png"));
		machin.setBounds(30, 1, 450, 606);
		layeredPane.add(machin);

		// 로또번호 뒤에 배경
		backImg = new JLabel(new ImageIcon("lottoimg\\로또기계 배경.png"));
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
					System.out.println("로또 굴리거에서 오류");
				}

				for (int x = 0; x < 20; x++) {
					boll[x].setBounds(random.nextInt(210) + 100, random.nextInt(210) + 40, 91, 90);
				}
			}
		}
	}

	// 공나오게 하는 쓰레드~
	public void result() {

		Random random = new Random();

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(0, 1, 700, 700);
		arr = new int[5]; // 로또번호 담을 배열

		for (int x = 0; x < 5; x++) {
			arr[x] = random.nextInt(19); // 5번동안 랜덤숫자 넣는다.

			for (int y = 0; y < x; y++) {

				if (arr[y] == arr[x]) { // 중복제거
					x--;
					break;
				}
			}
		}

		for (int x = 0; x < 5; x++) {
			Arrays.sort(arr); // 자동 정렬 한번!
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

			click[x] = new JButton(new ImageIcon("lottoimg\\물음표.png"));
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

							if (temp != i) { // 다른번호를 눌러야 카운트 증가
								count++;
								temp = i;
							}

							// 옵셥떠서 오늘번호 뜨게 하고 확인버튼누르면 몇등당첨되었는지? 꽝인지!
							if (count == 5) {
								String[] buttons = { "당첨확인", "종료" };
								String n1 = Integer.toString(arr[0] + 1); // 로또
																			// 번호
								String n2 = Integer.toString(arr[1] + 1);
								String n3 = Integer.toString(arr[2] + 1);
								String n4 = Integer.toString(arr[3] + 1);
								String n5 = Integer.toString(arr[4] + 1);

								String lotto_number = n1 + "-" + n2 + "-" + n3 + "-" + n4 + "-" + n5;
								for (int lotto = 0; lotto < lottoNum.length; lotto++) {
									lottoNum[lotto] = arr[lotto] + 1; // 로또번호가
																		// 들어감
								}
								// 내가 입력한 로또번호
								myNum[0] = Integer.parseInt(lottovo.getNum1());
								myNum[1] = Integer.parseInt(lottovo.getNum2());
								myNum[2] = Integer.parseInt(lottovo.getNum3());
								myNum[3] = Integer.parseInt(lottovo.getNum4());
								myNum[4] = Integer.parseInt(lottovo.getNum5());
								// 로또원시테이블 저장
								Random random = new Random();
								int lotto_price = ((random.nextInt(500000) + 20000) / 10000) * 10000; // 당첨금

								int result = JOptionPane.showOptionDialog(null,
										"오늘의 번호는 " + n1 + "  " + n2 + "  " + n3 + "  " + n4 + "  " + n5 + "입니다!", "로또",
										JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons,
										"로또 당첨");

								System.out.println(lottovo.getNum1() + " " + lottovo.getNum2() + " " + lottovo.getNum3()
										+ " " + lottovo.getNum4() + " " + lottovo.getNum5()); // 내가
																								// 입력한
																								// 로또번호

								if (result == JOptionPane.YES_OPTION) { // 확인버튼을
																		// 눌렀을때
																		// 당첨되었으면
																		// 축하메세지와
																		// 함께
																		// 적립!
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
													"1등! " + Integer.toString(lotto_price) + "원 당첨 되었습니다.", "로또당첨",
													JOptionPane.INFORMATION_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, lotto_price);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "Y");
											int yourMoney = lottoM.yourMoney(user_id);
											lottoM.jackpot(user_id, yourMoney, lotto_price); // 당첨금
																								// 업뎃
										} else if (winCount == 4) {
											JOptionPane.showMessageDialog(null, "2등! 100000원 당첨 되었습니다.", "로또당첨",
													JOptionPane.INFORMATION_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, 100000);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "Y");
											int yourMoney = lottoM.yourMoney(user_id);
											lottoM.jackpot(user_id, yourMoney, lotto_price);
										} else if (winCount == 3) {
											JOptionPane.showMessageDialog(null, "3등! 50000원 당첨 되었습니다.", "로또당첨",
													JOptionPane.INFORMATION_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, 50000);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "Y");
											int yourMoney = lottoM.yourMoney(user_id);
											lottoM.jackpot(user_id, yourMoney, lotto_price);
										} else if (winCount == 2) {
											JOptionPane.showMessageDialog(null, "4등! 1000원 당첨 되었습니다.", "로또당첨",
													JOptionPane.INFORMATION_MESSAGE, null);
											lottoM.lotto_insert(lotto_number, 1000);
											lottobet = lottoM.seq(user_id);
											lottoM.lottoBetInsert(user_id, lottobet.getLotto_code(), "Y");
											int yourMoney = lottoM.yourMoney(user_id);
											lottoM.jackpot(user_id, yourMoney, lotto_price);
										} else {
											JOptionPane.showMessageDialog(null, "꽝 !!", "로또 꽝",
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

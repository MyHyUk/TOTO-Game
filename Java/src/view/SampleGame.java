package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import model.CouponModel;

public class SampleGame extends JDialog implements Runnable {
	private int start_cnt = 3;
	private final int COOKIE_CNT = 20;
	private final int MAX_TIME = 10;
	private Cookie[] cookies = new Cookie[COOKIE_CNT];
	private JLayeredPane pMain = new JLayeredPane();
	private MyKeyListener evt = new MyKeyListener();
	private Player bang = new Player();
	private JLabel timer = new JLabel();
	private JLabel cnt = new JLabel();
	private int sec = 0, mSec = 0;
	private boolean isRun = true;

	public SampleGame() {
		pMain.setBounds(0, 0, 800, 800);
		pMain.setLayout(null);
		pMain.setOpaque(false);
		this.addKeyListener(evt);

		timer.setText("TIME : 00:00 / " + MAX_TIME + ":00");
		timer.setBounds(20, 5, 400, 50);
		timer.setForeground(Color.WHITE);
		timer.setFont(new Font("양재붓꽃채L", Font.BOLD, 40));

		cnt.setText(start_cnt + "초 후 포츈쿠키가 떨어집니다!!");
		cnt.setBounds(100, 100, 600, 50);
		cnt.setForeground(Color.WHITE);
		cnt.setFont(new Font("양재붓꽃채L", Font.BOLD, 40));

		for (int i = 0; i < COOKIE_CNT; i++) {
			cookies[i] = new Cookie(this);
			cookies[i].setOpaque(false);
			cookies[i].setBounds(0, 0, 800, 800);
			pMain.add(cookies[i], new Integer(i));
		}

		bang.setOpaque(false);
		bang.setBounds(0, 0, 800, 800);
		pMain.add(bang, new Integer(COOKIE_CNT + 1));

		pMain.add(timer, new Integer(COOKIE_CNT + 2));
		pMain.add(cnt, new Integer(COOKIE_CNT + 3));
		add(pMain);
		setTitle("포춘쿠키 피하기");
		setLayout(null);
		setLocation(400, 200);
		setSize(800, 800);
		setModal(false);
		setVisible(true);
		new Thread(this).start();

	}

	public Player getPlayer() {
		return bang;
	}

	public void endGame() {
		for (int i = 0; i < COOKIE_CNT; i++)
			cookies[i].setStop();
		isRun = false;
		this.removeKeyListener(evt);
		setVisible(false);

	}

	public class TimeCount implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (sec != MAX_TIME && isRun) {
				try {
					mSec++;
					Thread.sleep(10);
					if (mSec == 100) {
						mSec = 0;
						sec++;
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (sec > 9 && mSec > 9) {
					timer.setText("TIME : " + sec + ":" + mSec + " / " + MAX_TIME + ":00");
				} else if (sec < 10 && mSec > 9) {
					timer.setText("TIME : 0" + sec + ":" + mSec + " / " + MAX_TIME + ":00");
				} else if (sec > 9 && mSec < 10) {
					timer.setText("TIME : " + sec + ":0" + mSec + " / " + MAX_TIME + ":00");
				}
			}

			endGame();
			if (sec == MAX_TIME) {
				JOptionPane.showMessageDialog(null, "클리어! ");
				try {
					CouponModel model = new CouponModel();
					model.registCoupon("10% 추가충전");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				bang.moveLeft();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				bang.moveRight();
			}

		}
	}

	public boolean getIsRun() {
		// TODO Auto-generated method stub
		return isRun;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (start_cnt > 0) {
			cnt.setText(start_cnt-- + "초 후 포츈쿠키가 떨어집니다!!");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (start_cnt == 0) {
				cnt.setText(MAX_TIME + "초 동안 피하세요!!");
				cnt.setBounds(200, 100, 600, 50);
			}

		}
		new Thread(new TimeCount()).start();
		for (int i = 0; i < COOKIE_CNT; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(cookies[i]).start();
		}

	}

}

class Cookie extends JPanel implements Runnable {
	private boolean isRun;
	private int x, y;
	private final int goal = 800;
	private Image img = Toolkit.getDefaultToolkit().createImage("img/fortune_cookie.png");
	private SampleGame sg;

	public Cookie(SampleGame sg) {
		this.sg = sg;
		this.isRun = sg.getIsRun();
		x = ((int) (Math.random() * 32) + 1) * 25;
		y = -50;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (img != null)
			g.drawImage(img, x, y, this);
	}

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	public void setStop() {
		isRun = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (isRun) {
				if (sg.getPlayer().getPosX() == this.x) {
					if (sg.getPlayer().getPosY() - 5 <= this.y && sg.getPlayer().getPosY() + 5 >= this.y) {
						sg.endGame();
						JOptionPane.showMessageDialog(null, "죽었습니다!");
					}
				}
				Thread.sleep(10);
				y += ((int) (Math.random() * 10) + 1);
				repaint();
				if (y >= goal) {
					x = ((int) (Math.random() * 32) + 1) * 25;
					y = ((int) (Math.random() * 30) + 1) * 5;
				}
			}
		} catch (Exception e) {

		}
	}
}

class Player extends JPanel {
	private int x, y;
	private final int speed = 50;
	private Image img = Toolkit.getDefaultToolkit().createImage("img/방쉐프.gif");

	public Player() {
		x = 400;
		y = 600;

	}

	public void paint(Graphics g) {
		super.paint(g);
		if (img != null)
			g.drawImage(img, x, y, this);
	}

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		if (this.x > 0)
			this.x -= speed;
		repaint();
	}

	public void moveRight() {
		// TODO Auto-generated method stub
		if (this.x < 750)
			this.x += speed;
		repaint();
	}

}

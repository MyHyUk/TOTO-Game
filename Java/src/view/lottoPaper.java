package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.LottoModel;
import model.rec.LottoVO;

public class lottoPaper extends JFrame{

	private BufferedImage lottoPaperImg,lottoBillImg; //�ζǹ�ȣ�Է»���, ���� �Է��� ��ȣ����
	private JLayeredPane layeredPane; 

	private MyPanel panel; // �̹���
	private yourNumber yournumber;  // �̹��� 
	
	private JButton[] button = new JButton[20]; // �ζ� ��ȣ ����
	
	private JLabel[] numberLabel = new JLabel[5]; // �������� ��ȣ�� ����(��ȣ ������)
	private int arrCnt= -1;  // �迭���� 0�̿���
	
	private JButton gameButton,battingButton; // ���ӽ��۹�ư(�ζǰ������� ȭ���̵�) , ���ù�ư
    private JLabel borderLabel; // ���
    private JLabel yourMoney,howMuch; // �����ݾ�,���ñݾ�
    private JTextField yourMoneyF,howMuchf; // �����ݾ� �ʵ�, ���ñݾ� �ʵ�
    private int finalNumber[]; // ���� ���� �Է��� �ζ� ��ȣ�� ���� �迭
   
    private JButton resetButton; // ���¹�ư
    private String user_id; // ȸ�����̵�
    private JButton quButton;
    
    private  LottoVO lottovo = new LottoVO();
    private  int sw =0; // ����ġ ����  �迭���� �پ����� 1 �������� 0
    private  String[] buttons = {"Ȯ��","����"};
    private JLabel tooltipLabel;
    private JPanel panel_2;
    private JLabel lblNewLabel;
   
    //�������̵� �޾ƿ��� ������
	public lottoPaper(String user_id){
	
	 	setTitle("LOTTO");
	 	ImageIcon img_logo = new ImageIcon("img/logo.png");
		setIconImage(img_logo.getImage());
		
	  
		this.user_id = user_id;
		lottovo.setCust_id(user_id); // ���� ���̵� �޾Ƽ� lotto_bet ���̺��� Ȱ���� ����
	
	  	setSize(858,599);
	  	getContentPane().setLayout(null);
	  	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width / 2) - (this.getWidth() / 2), (dim.height / 2) - (this.getHeight() / 2));
		this.setLocationRelativeTo(null);
	  	
	  	paper(); // �ζǹ�ȣ �ڿ� �ִ� �ζ����� ����� ������ �ޙ�,
	  	showNumber(); //������ �Է��� �ζǹ�ȣ�� ���� �׸�

		Action(); // �ζǹ�ȣ��ư������ ��(������)�� �ڽ��� �Է��� ��ȣ�� �������ϴ� �޼ҵ� (�׼��̺�Ʈ ��ټ��� ���⼭ ����)
		
	  	battingCard(); // ����ݾ�,�����ϱ� ��ư�� �ִ� �޼ҵ�
	  	reset(); // ���� ��ư
	  	
	  	setVisible(true);
	}
	
	//�ʱ�ȭ �ϴ� �޼ҵ�
     public void reset()
     {
  		
    	/*
    		quButton = new JButton(new ImageIcon("lottoimg\\�ζ�Ʃ�丮���ư.png")); // ������ư
    	  	quButton.setBounds(515,150, 48,60);
    	  	quButton.setToolTipText("���ӹ��");
    	  	quButton.setBorderPainted(false);
    	  	quButton.setFocusPainted(false);
    	  	quButton.setContentAreaFilled(false);
    	  	quButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
			
					new lottoTolltip();
				}
			});
		*/
    	  	//layeredPane.add(quButton);
    		getContentPane().add(layeredPane);
    		
    		JPanel battingCartPanel = new JPanel();
    		battingCartPanel.setBorder(new LineBorder(Color.WHITE));
    		battingCartPanel.setBounds(592, 351, 240, 209);
    		layeredPane.add(battingCartPanel);
    			    battingCartPanel.setLayout(null);
    			    
    			    JLabel lblBettingCart_1 = new JLabel("Betting Cart");
    			    lblBettingCart_1.setFont(new Font("����ײ�üL", Font.BOLD, 15));
    			    lblBettingCart_1.setBounds(3, 3, 121, 32);
    			    battingCartPanel.add(lblBettingCart_1);
    			    
    			    JLabel lblBettingCart = new JLabel("");
    			    lblBettingCart.setIcon(new ImageIcon("lottoimg/labelcolor.png"));
    			    lblBettingCart.setForeground(Color.WHITE);
    			    lblBettingCart.setBackground(Color.BLACK);
    			    lblBettingCart.setFont(new Font("����ײ�üL", Font.BOLD, 15));
    			    lblBettingCart.setBounds(1, 1, 239, 32);
    			    battingCartPanel.add(lblBettingCart);
    		
    			    borderLabel = new JLabel();
    			    borderLabel.setBounds(117, 6, 0, 0);
    			    battingCartPanel.add(borderLabel);
    			    borderLabel.setForeground(Color.WHITE);
    			    borderLabel.setFont(new Font("����ײ�üL", Font.BOLD, 20));
    			    
    			    gameButton = new JButton(new ImageIcon("lottoimg/���ӽ��۹�ư.png"));
    			    gameButton.setBounds(125, 119, 105, 60);
    			    battingCartPanel.add(gameButton);
    			    gameButton.setEnabled(false);
    			    gameButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new lottoGame(lottovo,user_id);
				}
			});
    			    
    			    JPanel panel_1 = new JPanel();
    			    panel_1.setBorder(new LineBorder(Color.WHITE));
    			    panel_1.setBounds(592, 10, 240, 334);
    			    layeredPane.add(panel_1);
    			    panel_1.setLayout(null);
    			    
    			    tooltipLabel = new JLabel("");
    			    tooltipLabel.setIcon(new ImageIcon("lottoimg/�ζ�Ʃ�丮��.png"));
    			    tooltipLabel.setBounds(1, 1, 238, 332);
    			    panel_1.add(tooltipLabel);
    			    
    			    panel_2 = new JPanel();
    			    panel_2.setBorder(new LineBorder(Color.WHITE));
    			    panel_2.setBounds(0, 10, 580, 550);
    			    layeredPane.add(panel_2);
    			    panel_2.setLayout(null);
    			    
    			    lblNewLabel = new JLabel("");
    			    lblNewLabel.setIcon(new ImageIcon("lottoimg/��ȣ������.png"));
    			    lblNewLabel.setBounds(19, 290, 80, 252);
    			    panel_2.add(lblNewLabel);
    		
     }

	public void showNumber(){ //���� ������ȣ�� �Ʒ��� ���̰�	
		try {
	  		lottoBillImg = ImageIO.read(new File("lottoimg\\�ζ������̰ɷ�.png"));
		} catch (IOException e) {
		System.out.println("�̹��� �ҷ����� ����");
		}
		
		yournumber = new yourNumber();
		yournumber.setBounds(100,300,419,253);		
		numberLabel[0] = new JLabel("");

		numberLabel[0].setBounds(130,430,80,50);
		layeredPane.add(numberLabel[0]);	
		numberLabel[1] = new JLabel("");

		numberLabel[1].setBounds(190,430,80,50);
		layeredPane.add(numberLabel[1]);		
		numberLabel[2] = new JLabel("");
	
		numberLabel[2].setBounds(260,430,80,50);
		layeredPane.add(numberLabel[2]);	
		numberLabel[3] = new JLabel("");

		numberLabel[3].setBounds(320,430,80,50);
		layeredPane.add(numberLabel[3]);		
		numberLabel[4] = new JLabel("");
		
		numberLabel[4].setBounds(380,430,80,50);
		layeredPane.add(numberLabel[4]);
		
		//��Ʈũ�� �۾�ũ��
		for(int x=0;x <5; x++){
			 numberLabel[x].setFont(new Font("����ײ�üL", Font.PLAIN, 25));
			 numberLabel[x].setForeground(Color.BLACK);		 
		}

		layeredPane.add(yournumber);
		
	}

	public void paper(){ //�ζ����� �޼ҵ�
		
		layeredPane = new JLayeredPane();
	  	layeredPane.setBounds(0,0,1000,600);
	  	layeredPane.setLayout(null);
	  	
	 //��ư����  		
		int xLine = 145;
	 	int yLine = 30;
	  	int cnt=0;
  	for(int x=0; x< 7; x++){
  	 
	  		cnt++;
	  		button[x] = new JButton(Integer.toString(cnt));
	  		button[x].setBounds(xLine,yLine,48,60);
	  		xLine += 53;	
	  		
	  		layeredPane.add(button[x]);
	  	}
  	int xLine1 = 145;
  	int yLine1 = 95;
  	
  	for(int x=7; x< 14; x++){
  		cnt++;
  		
  		button[x] = new JButton(Integer.toString(cnt));
  		button[x].setBounds(xLine1,yLine1,48,60);
  		xLine1 += 53;	
  		layeredPane.add(button[x]);
  	}
  	    int xLine2 = 145;
	  	int yLine2 = 160;
	  	
  	for(int x=14; x<20 ; x++){
  		cnt++;
  
  		button[x] = new JButton(Integer.toString(cnt));
  		button[x].setBounds(xLine2,yLine2,48,60);
  		xLine2 += 53;
  		
  		layeredPane.add(button[x]);
  	}
	  	// �ڿ� ���� �������� �ζǻ���
  	try {
  		lottoPaperImg = ImageIO.read(new File("lottoimg\\�ζ����̱��.png"));
	} catch (IOException e) {
	System.out.println("�̹��� �ҷ����� ����");
	}
  	//
  	    panel = new MyPanel();
  	    panel.setBounds(17,20,504,271);
	  	layeredPane.add(panel); //����� ���ʿ�
	  	
	  	getContentPane().add(layeredPane); 
	}

// ��������
	public void battingCard(){
		resetButton = new JButton(new ImageIcon("lottoimg\\images.png"));	
		resetButton.setBounds(527,239,38,50);
		resetButton.setToolTipText("�ʱ�ȭ");
		resetButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					gameButton.setEnabled(false);
					
					for(int x=0; x<5; x++){
			
						try{
							numberLabel[x].setText("");						
							//temp�� ��Ƶΰ�  
							int temp = finalNumber[x];
							button[temp-1].setEnabled(true);
							arrCnt= -1; //ī��Ʈ�� �ٽ� �ʱ�ȭ
						}catch(Exception ex){
						System.out.println("����");
							
						}
											
					}}});
		
		layeredPane.add(resetButton);
		
		yourMoney = new JLabel("���� �ݾ� ");
		yourMoney.setFont(new Font("����ײ�äL",Font.BOLD,13));
		yourMoney.setBounds(608,394,80,25);
		layeredPane.add(yourMoney);
		yourMoneyF = new JTextField();
		yourMoneyF.setBounds(690,394,120,25);
		//���⿡ ��� ����� �����ݾ��� �����´�
		try {
			LottoModel lotto = new LottoModel();
			
			int money = lotto.yourMoney(user_id);		
		    yourMoneyF.setText("    "+String.valueOf(money)); // �����޾ƿ´�
			
		} catch (Exception e1) {

			e1.printStackTrace();
			System.out.println("�����ݾ� ����");
		}

		yourMoneyF.setEditable(false);
		layeredPane.add(yourMoneyF);
		
		howMuch = new JLabel("���� �ݾ� ");
		howMuch.setFont(new Font("����ײ�äL",Font.BOLD,13));
		howMuch.setBounds(608,432,80,25);
		layeredPane.add(howMuch);
	  
	    howMuchf = new JTextField();
		howMuchf.setBounds(690,432,120,25);
		howMuchf.setText("                   1000");
		howMuchf.setEditable(false);
		layeredPane.add(howMuchf);
   
	    
	    battingButton = new JButton("");
	    battingButton.setIcon(new ImageIcon("lottoimg/�ζǹ����ϱ��ư.png"));
	   // battingButton.setFont(new Font("����ײ�üL", Font.PLAIN, 14));
	    battingButton.setBounds(603,471,105,60);
	    layeredPane.add(battingButton);
    
	    //���ù�ư ������ �ζǸ� �����Ͻðڽ��ϱ�?
	
		 battingButton.addActionListener(new ActionListener() {			
			 	 
				@Override
				public void actionPerformed(ActionEvent e) {

					
					 for(int x=0; x<finalNumber.length; x++){
					    	if(finalNumber[x] == 0){
					    		  sw = 1;
					    		  break;
					    	}
					    	else{
					    		sw =0;
					    	}
					    }
					   //5���� ���Է¸��ؼ� ����ġ������ 1�̵Ǹ� if �ƴϸ� else				
					 if(sw == 1){
		
						 JOptionPane.showOptionDialog
							(null,"���ڸ� 5�� �Է��ϼ���", "�ζǱ���",
									JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,buttons,"���� ����");
					 	}else{
								
					String[] check = {"����","����"};
			
						//���� �����ǰ� ���������ִ� ��ȣ�� ���� ���־����
					   
						int result1 = JOptionPane.showOptionDialog
								(null,"������ �Ͻðڽ��ϱ�?", "�ζ�",
										JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,check,"���� ����");
						if(result1 == JOptionPane.YES_OPTION){
							
							gameButton.setEnabled(true); // ��������Ȯ�� ��ư�� �������� ���ӽ��� ��ư�� ������.
							
							 try {
								LottoModel lotto =  new LottoModel();
								int yourMoney = lotto.yourMoney(user_id);
								lotto.updateMoney(user_id,yourMoney);
								
								yourMoneyF.setText("    "+Integer.toString(lotto.yourMoney(user_id)));
												
							} catch (Exception e1) {e1.printStackTrace();}

							for(int x =0; x<5; x++){ // ���� �ѹ���~
							 Arrays.sort(finalNumber);
							}
							
							for(int x=0; x<5; x++){ // ���Ĺ�ȣ�� ������ ��ȣ�� �ٲ�, ��ȣ�� �ٽ� ���� �� �ְ� �ٲ�
				
								numberLabel[x].setText(Integer.toString(finalNumber[x]));
								int temp  = finalNumber[x];
								System.out.println(finalNumber[x]);
								
							}
						      lottovo.setNum1(Integer.toString(finalNumber[0]));
						      lottovo.setNum2(Integer.toString(finalNumber[1]));
						      lottovo.setNum3(Integer.toString(finalNumber[2]));
						      lottovo.setNum4(Integer.toString(finalNumber[3]));
						      lottovo.setNum5(Integer.toString(finalNumber[4]));	     			      
						}
				   }}
			});
			getContentPane().add(layeredPane);

	 }

    public void Action(){ // �׼� �̺�Ʈ ó�� �޼ҵ�
   
    	    finalNumber = new int[5]; // �迭�� �޾ƿͼ� ������ ����

    	    for(int x=0; x<button.length; x++){
    	    	
    	    	button[x].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Object obj = e.getSource();
						for(int y=0; y<button.length; y++){
							if(obj == button[y]){
								
								 arrCnt++;
								 try{					 
			    					 finalNumber[arrCnt] = y+1; // finalNumber[0]�� 1�� ���ٴ°�! 1�� ��ư 1���� �ǹ���;	
			    				
			    					 button[y].setEnabled(false);
			    				 }catch(Exception ex){
			    				
			    					 if(arrCnt == 5){
			    	    					 			
			        		    		 JOptionPane.showOptionDialog
			        						(null,"5���Է��� �ִ��Դϴ�!", "�ζ�",
			        								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,null,buttons,"�ζ� ����");
			        		    		     				 }}
			   				 			 
			    				 switch(arrCnt){			 		    				 
			    				 case 0:
			    					 
			    					 numberLabel[0].setText("     "+Integer.toString(finalNumber[arrCnt]));
			    			
			    					 break;
			    				 case 1:
			    					 numberLabel[1].setText("     "+Integer.toString(finalNumber[arrCnt]));
			    			
			    					 break;
			    				 case 2:
			    					 numberLabel[2].setText("     "+Integer.toString(finalNumber[arrCnt]));

			    					 break;
			    				 case 3:
			    					 numberLabel[3].setText("     "+Integer.toString(finalNumber[arrCnt]));
			    			
			    					 break;
			    				 case 4:
			    					 numberLabel[4].setText("     "+Integer.toString(finalNumber[arrCnt]));
			    			
			    					 break;
			    				 default :
			    					 System.out.println("����");   
			    				 }}}}
				});
    	      }


        
    } 
	private class MyPanel extends JPanel{ //�ζǹ�ȣ �Է»���
    public void paint(Graphics g){g.drawImage(lottoPaperImg,0,0,null);}}
    
	private class yourNumber extends JPanel{ //���� �Է��� ��ȣ ����
    public void paint(Graphics g){g.drawImage(lottoBillImg,0,0,null);}}
}

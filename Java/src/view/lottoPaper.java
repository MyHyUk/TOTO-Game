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

	private BufferedImage lottoPaperImg,lottoBillImg; //로또번호입력사진, 내가 입력한 번호사진
	private JLayeredPane layeredPane; 

	private MyPanel panel; // 이미지
	private yourNumber yournumber;  // 이미지 
	
	private JButton[] button = new JButton[20]; // 로또 번호 선택
	
	private JLabel[] numberLabel = new JLabel[5]; // 영수증에 번호가 보임(번호 누르면)
	private int arrCnt= -1;  // 배열값이 0이여서
	
	private JButton gameButton,battingButton; // 게임시작버튼(로또게임으로 화면이동) , 배팅버튼
    private JLabel borderLabel; // 경계
    private JLabel yourMoney,howMuch; // 보유금액,배팅금액
    private JTextField yourMoneyF,howMuchf; // 보유금액 필드, 배팅금액 필드
    private int finalNumber[]; // 최종 내가 입력한 로또 번호를 담을 배열
   
    private JButton resetButton; // 리셋버튼
    private String user_id; // 회원아이디
    private JButton quButton;
    
    private  LottoVO lottovo = new LottoVO();
    private  int sw =0; // 스위치 변수  배열값이 다없으면 1 다있으면 0
    private  String[] buttons = {"확인","종료"};
    private JLabel tooltipLabel;
    private JPanel panel_2;
    private JLabel lblNewLabel;
   
    //유저아이디를 받아오는 생성자
	public lottoPaper(String user_id){
	
	 	setTitle("LOTTO");
	 	ImageIcon img_logo = new ImageIcon("img/logo.png");
		setIconImage(img_logo.getImage());
		
	  
		this.user_id = user_id;
		lottovo.setCust_id(user_id); // 유저 아이디를 받아서 lotto_bet 테이블을 활용할 예정
	
	  	setSize(858,599);
	  	getContentPane().setLayout(null);
	  	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width / 2) - (this.getWidth() / 2), (dim.height / 2) - (this.getHeight() / 2));
		this.setLocationRelativeTo(null);
	  	
	  	paper(); // 로또번호 뒤에 있는 로또종이 배경을 설정한 메솓,
	  	showNumber(); //유저가 입력한 로또번호를 담을 그릇

		Action(); // 로또번호버튼누르면 라벨(영수증)에 자신이 입력한 번호가 나오게하는 메소드 (액션이벤트 대다수가 여기서 실행)
		
	  	battingCard(); // 현재금액,배팅하기 버튼이 있는 메소드
	  	reset(); // 리셋 버튼
	  	
	  	setVisible(true);
	}
	
	//초기화 하는 메소드
     public void reset()
     {
  		
    	/*
    		quButton = new JButton(new ImageIcon("lottoimg\\로또튜토리얼버튼.png")); // 툴팁버튼
    	  	quButton.setBounds(515,150, 48,60);
    	  	quButton.setToolTipText("게임방법");
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
    			    lblBettingCart_1.setFont(new Font("양재붓꽃체L", Font.BOLD, 15));
    			    lblBettingCart_1.setBounds(3, 3, 121, 32);
    			    battingCartPanel.add(lblBettingCart_1);
    			    
    			    JLabel lblBettingCart = new JLabel("");
    			    lblBettingCart.setIcon(new ImageIcon("lottoimg/labelcolor.png"));
    			    lblBettingCart.setForeground(Color.WHITE);
    			    lblBettingCart.setBackground(Color.BLACK);
    			    lblBettingCart.setFont(new Font("양재붓꽃체L", Font.BOLD, 15));
    			    lblBettingCart.setBounds(1, 1, 239, 32);
    			    battingCartPanel.add(lblBettingCart);
    		
    			    borderLabel = new JLabel();
    			    borderLabel.setBounds(117, 6, 0, 0);
    			    battingCartPanel.add(borderLabel);
    			    borderLabel.setForeground(Color.WHITE);
    			    borderLabel.setFont(new Font("양재붓꽃체L", Font.BOLD, 20));
    			    
    			    gameButton = new JButton(new ImageIcon("lottoimg/게임시작버튼.png"));
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
    			    tooltipLabel.setIcon(new ImageIcon("lottoimg/로또튜토리얼.png"));
    			    tooltipLabel.setBounds(1, 1, 238, 332);
    			    panel_1.add(tooltipLabel);
    			    
    			    panel_2 = new JPanel();
    			    panel_2.setBorder(new LineBorder(Color.WHITE));
    			    panel_2.setBounds(0, 10, 580, 550);
    			    layeredPane.add(panel_2);
    			    panel_2.setLayout(null);
    			    
    			    lblNewLabel = new JLabel("");
    			    lblNewLabel.setIcon(new ImageIcon("lottoimg/번호생성라벨.png"));
    			    lblNewLabel.setBounds(19, 290, 80, 252);
    			    panel_2.add(lblNewLabel);
    		
     }

	public void showNumber(){ //내가 누른번호가 아래에 보이게	
		try {
	  		lottoBillImg = ImageIO.read(new File("lottoimg\\로또종이이걸로.png"));
		} catch (IOException e) {
		System.out.println("이미지 불러오기 실패");
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
		
		//폰트크기 글씨크기
		for(int x=0;x <5; x++){
			 numberLabel[x].setFont(new Font("양재붓꽃체L", Font.PLAIN, 25));
			 numberLabel[x].setForeground(Color.BLACK);		 
		}

		layeredPane.add(yournumber);
		
	}

	public void paper(){ //로또종이 메소드
		
		layeredPane = new JLayeredPane();
	  	layeredPane.setBounds(0,0,1000,600);
	  	layeredPane.setLayout(null);
	  	
	 //버튼생성  		
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
	  	// 뒤에 사진 가져오기 로또사진
  	try {
  		lottoPaperImg = ImageIO.read(new File("lottoimg\\로또종이긴거.png"));
	} catch (IOException e) {
	System.out.println("이미지 불러오기 실패");
	}
  	//
  	    panel = new MyPanel();
  	    panel.setBounds(17,20,504,271);
	  	layeredPane.add(panel); //배경은 뒤쪽에
	  	
	  	getContentPane().add(layeredPane); 
	}

// 배팅정보
	public void battingCard(){
		resetButton = new JButton(new ImageIcon("lottoimg\\images.png"));	
		resetButton.setBounds(527,239,38,50);
		resetButton.setToolTipText("초기화");
		resetButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					gameButton.setEnabled(false);
					
					for(int x=0; x<5; x++){
			
						try{
							numberLabel[x].setText("");						
							//temp에 담아두고  
							int temp = finalNumber[x];
							button[temp-1].setEnabled(true);
							arrCnt= -1; //카운트를 다시 초기화
						}catch(Exception ex){
						System.out.println("오류");
							
						}
											
					}}});
		
		layeredPane.add(resetButton);
		
		yourMoney = new JLabel("보유 금액 ");
		yourMoney.setFont(new Font("양재붓꽃채L",Font.BOLD,13));
		yourMoney.setBounds(608,394,80,25);
		layeredPane.add(yourMoney);
		yourMoneyF = new JTextField();
		yourMoneyF.setBounds(690,394,120,25);
		//여기에 디비에 저장된 보유금액을 가져온다
		try {
			LottoModel lotto = new LottoModel();
			
			int money = lotto.yourMoney(user_id);		
		    yourMoneyF.setText("    "+String.valueOf(money)); // 돈을받아온다
			
		} catch (Exception e1) {

			e1.printStackTrace();
			System.out.println("보유금액 오류");
		}

		yourMoneyF.setEditable(false);
		layeredPane.add(yourMoneyF);
		
		howMuch = new JLabel("배팅 금액 ");
		howMuch.setFont(new Font("양재붓꽃채L",Font.BOLD,13));
		howMuch.setBounds(608,432,80,25);
		layeredPane.add(howMuch);
	  
	    howMuchf = new JTextField();
		howMuchf.setBounds(690,432,120,25);
		howMuchf.setText("                   1000");
		howMuchf.setEditable(false);
		layeredPane.add(howMuchf);
   
	    
	    battingButton = new JButton("");
	    battingButton.setIcon(new ImageIcon("lottoimg/로또배팅하기버튼.png"));
	   // battingButton.setFont(new Font("양재붓꽃체L", Font.PLAIN, 14));
	    battingButton.setBounds(603,471,105,60);
	    layeredPane.add(battingButton);
    
	    //배팅버튼 누르면 로또를 구매하시겠습니까?
	
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
					   //5개를 다입력못해서 스위치변수가 1이되면 if 아니면 else				
					 if(sw == 1){
		
						 JOptionPane.showOptionDialog
							(null,"숫자를 5개 입력하세요", "로또구매",
									JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,buttons,"배팅 시작");
					 	}else{
								
					String[] check = {"배팅","종료"};
			
						//돈이 차감되고 영수증에있는 번호가 정렬 되있어야함
					   
						int result1 = JOptionPane.showOptionDialog
								(null,"배팅을 하시겠습니까?", "로또",
										JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,check,"배팅 시작");
						if(result1 == JOptionPane.YES_OPTION){
							
							gameButton.setEnabled(true); // 배팅최종확인 버튼이 눌렸을때 게임시작 버튼이 열린다.
							
							 try {
								LottoModel lotto =  new LottoModel();
								int yourMoney = lotto.yourMoney(user_id);
								lotto.updateMoney(user_id,yourMoney);
								
								yourMoneyF.setText("    "+Integer.toString(lotto.yourMoney(user_id)));
												
							} catch (Exception e1) {e1.printStackTrace();}

							for(int x =0; x<5; x++){ // 정렬 한번쭉~
							 Arrays.sort(finalNumber);
							}
							
							for(int x=0; x<5; x++){ // 정렬번호로 영수증 번호가 바뀜, 번호가 다시 누를 수 있게 바뀜
				
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

    public void Action(){ // 액션 이벤트 처리 메소드
   
    	    finalNumber = new int[5]; // 배열로 받아와서 정렬할 예정

    	    for(int x=0; x<button.length; x++){
    	    	
    	    	button[x].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Object obj = e.getSource();
						for(int y=0; y<button.length; y++){
							if(obj == button[y]){
								
								 arrCnt++;
								 try{					 
			    					 finalNumber[arrCnt] = y+1; // finalNumber[0]에 1이 들어간다는것! 1은 버튼 1번을 의미함;	
			    				
			    					 button[y].setEnabled(false);
			    				 }catch(Exception ex){
			    				
			    					 if(arrCnt == 5){
			    	    					 			
			        		    		 JOptionPane.showOptionDialog
			        						(null,"5개입력이 최대입니다!", "로또",
			        								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,null,buttons,"로또 오류");
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
			    					 System.out.println("오류");   
			    				 }}}}
				});
    	      }


        
    } 
	private class MyPanel extends JPanel{ //로또번호 입력사진
    public void paint(Graphics g){g.drawImage(lottoPaperImg,0,0,null);}}
    
	private class yourNumber extends JPanel{ //내가 입력한 번호 사진
    public void paint(Graphics g){g.drawImage(lottoBillImg,0,0,null);}}
}

package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.KBOModel;
import model.rec.KBOBetVO;

public class KBOView extends JFrame {
	
	private String user_id; //사용자 아이디
	private int user_price; // 보유금액

	private JTextField textField;
	private JLabel lbNewLabel3,label_1;
	private JTextField textField_1; // 보유잔액
	private JTextField textField_2; //적중금액
	private JTextField textField_3; // 배팅금액
	
     //경기가 이뤄지는메인 변수 생성해볼까?	
	private JLayeredPane layeredpane;
	private JLabel[] verseLabel; //승무패 이미지라벨 
	private JButton[] battingButton; // 배당률이 적혀있는 버튼(핵심)
	private JLabel[] home;//홈 인지 보여주는 이미지
	private JLabel[] away; //어웨이 인지 보여주는 이미지
	private JLabel[] WDL; //win,draw,lose
	
	//배팅률,시간,남은시간 각각 label
	private JLabel[] gameTime; // 경기시작 시간
	private JLabel[] threadTime; // 남은시간 이건 쓰레드로 시작시간에 맞춰서 계속 시간이 깎임

	private ArrayList<KBOBetVO> home_kbo = new ArrayList<KBOBetVO>();
	private ArrayList<KBOBetVO> away_kbo = new ArrayList<KBOBetVO>();
	
	private String time; // 경기시작 디비에서 받아옴
	private int countGame;
	
	private String result; //배당률
	private String selectTeam; //선택한 팀이름
	private int result_code; // 니가 선택한 팀의 매치번호
	private int home_code; // 매치코드
	private int away_code; 
    private int finalMoney; // 적중금액
	private int buttonNumber; 	
    private int mybatMoney;
    
	public KBOView(String user_id) {
		setTitle("KBO 배팅게임");
		ImageIcon img_logo = new ImageIcon("img/logo.png");
		setIconImage(img_logo.getImage());
		
		this.user_id =user_id;
	    getContentPane().setLayout(null);
		setSize(1115,600);				
		try {KBOModel kbom = new KBOModel();
			countGame = kbom.getCountGame(); } 
		    catch (Exception e) {e.printStackTrace();}		
		match();
		matchTeamName();
		gameing();	
		battingcard();	
		action();
		
		setVisible(true);		
	}
	// 매치가 이뤄질 ui 메소드
    public void match(){
	
    layeredpane=  new JLayeredPane(); // ui꾸밀대는 이게 가장 편한거 같음	
    layeredpane.setBounds(12,165,800,568);
    layeredpane.setBorder(new LineBorder(Color.BLACK));
    layeredpane.setLayout(null);	
       
    WDL = new JLabel[5];
    int wdlYline = 30;
    
    for(int x=0; x < WDL.length; x++){
    	WDL[x] = new JLabel();
    	WDL[x].setText("승무패");
    	WDL[x].setForeground(Color.WHITE);
    	WDL[x].setFont(new Font("양재붓꽃체L", Font.BOLD, 14));   	
    	WDL[x].setBounds(116,wdlYline,60,30);
    	
    	wdlYline += 65;    	
    	layeredpane.add(WDL[x]);
    }         
	battingButton = new JButton[10]; //10개구단에 대한 각각의 버튼
  
	int buttonXline= 195;
	int buttonYline =30;
	
	int buttonXline2 = 195;
	int buttonYline2 = 95;
	
	int buttonXline3 = 195;
	int buttonYline3 = 160;
	
	int buttonXline4 = 195;
	int buttonYline4 = 225;
	
	int buttonXline5 = 195;
	int buttonYline5 = 290;
	
	for(int x=0; x< countGame*2; x++){
		
		battingButton[x] = new JButton();
		battingButton[x].setBackground(Color.BLACK);
		battingButton[x].setForeground(Color.white);
		
		if(x < 2){
			battingButton[x].setBounds(buttonXline,buttonYline,210,36);	
			buttonXline += 290;		
		}else if (x == 2 || x == 3 ){
						
			battingButton[x].setBounds(buttonXline2,buttonYline2,210,36);
			buttonXline2 += 290;
		}else if (x == 4 || x ==5 ){		
			battingButton[x].setBounds(buttonXline3,buttonYline3,210,36);
			buttonXline3 += 290;
		}else if (x == 6 || x ==7){
			battingButton[x].setBounds(buttonXline4,buttonYline4,210,36);
			buttonXline4 += 290;
		}else{
			battingButton[x].setBounds(buttonXline5,buttonYline5,210,36);
			buttonXline5 += 290;
		}
	layeredpane.add(battingButton[x]);
		
	}		   
	gameTime = new JLabel[5];
	//홈인지 어웨이인지 사진 추가 

	int homeYline = 37;
	home = new JLabel[5];
	for(int x=0; x< home.length; x++){
		
		home[x] = new JLabel(new ImageIcon("KBOimg\\홈경기로고.PNG"));
		home[x].setBounds(170,homeYline,20,20);
		homeYline += 65;	
		layeredpane.add(home[x]);
	}	
	//경기시간
	try {
		KBOModel kbom = new KBOModel();
		 time = kbom.getDay();	 
	} catch (Exception e) {
		e.printStackTrace();}
	
	int timeLine = 34;
	for(int x=0; x< countGame; x++){
		   gameTime[x] = new JLabel();
		   gameTime[x].setText(time);
	       gameTime[x].setBounds(20,timeLine,100,40);
		   timeLine +=64;
		   layeredpane.add(gameTime[x]);
	}	
	//원정경기 이미지 
	int awayYline = 37;
	away = new JLabel[5];
	
	for(int x=0; x< away.length; x++){
		
		away[x] = new JLabel(new ImageIcon("KBOimg\\원정경기로고.PNG"));
		away[x].setBounds(700,awayYline,20,20);
		awayYline += 65;
		layeredpane.add(away[x]);
	}	
	//첫번째 vs	
	verseLabel = new JLabel[5];
	int yLine = 30;	
	//vs사진 추가
	for(int x=0; x<verseLabel.length; x++){
		
		verseLabel[x] = new JLabel(new ImageIcon("KBOimg\\vs.PNG"));	
		verseLabel[x].setBounds(410,yLine,70,36);
		
		layeredpane.add(verseLabel[x]);	
		yLine += 65;
	}
	getContentPane().add(layeredpane);
	} 
	//오늘 경기인 팀들 보이게 할 메소드
	public void matchTeamName(){
		
	int cnt=0;
	int cnt2=1;	
	double resultRate; // 승률차이
	double resultRate2;
	double StrongTeam; //강팀
	double underdog; //약팀
	
	 DecimalFormat form = new DecimalFormat("#.##"); // 소수점 버리는 클래스
	
	 //붙은팀 승률 빼서 거기에맞게 배당률 알고리즘
		try {
			KBOModel kbom = new KBOModel();
			home_kbo = kbom.getTeamName();//홈팀
			away_kbo = kbom.getAwayTeam();//away
		
			for(int x=0; x<away_kbo.size(); x++){

				if(Double.parseDouble(home_kbo.get(x).getHomeWinrate()) >= Double.parseDouble(away_kbo.get(x).getAwayWinrate()))
				{
					resultRate = Double.parseDouble(home_kbo.get(x).getHomeWinrate()) - Double.parseDouble(away_kbo.get(x).getAwayWinrate());
					
					if(resultRate > 0.5){				
						StrongTeam =  1.3;
						underdog = 4.5;
					}else if(resultRate <=0.5 && resultRate >0.4){
						StrongTeam = 1.5; 
						underdog = 3.5;
					}else if(resultRate <= 0.4 && resultRate >=0.35){
						StrongTeam = 1.6; 
						underdog = 2.8;					
					}else if(resultRate < 0.35 && resultRate > 0.3){
						StrongTeam = 1.8; 
						underdog = 2.5;	
					}else if(resultRate <= 0.3 && resultRate > 0.2){
						StrongTeam = 1.8; 
						underdog = 2.3;	
					}else if(resultRate <= 0.2 && resultRate > 0.1){
						StrongTeam = 1.8; 
						underdog = 2;	
					}else if(resultRate == 0){
						StrongTeam =  1.5;
						underdog = 1.5;
					}else{
						StrongTeam = 1.5;
						underdog = 1.8;
					}
					
					battingButton[cnt].setText(away_kbo.get(x).getAwayTeam()+"                           "+form.format(underdog));

					battingButton[cnt2].setText(home_kbo.get(x).getHomeTeam()+"                           "+form.format(StrongTeam));
				    cnt +=2;
				    cnt2 +=2;
				    
				}else if(Double.parseDouble(home_kbo.get(x).getHomeWinrate()) <= Double.parseDouble(away_kbo.get(x).getAwayWinrate())){
					
					resultRate2 = Double.parseDouble(away_kbo.get(x).getAwayWinrate()) - Double.parseDouble(home_kbo.get(x).getHomeWinrate());
								
					if(resultRate2 > 0.5){				
						StrongTeam =  1.3;
						underdog = 4.5;
					}else if(resultRate2 <=0.5 && resultRate2 >0.4){
						StrongTeam = 1.5; 
						underdog = 3.5;
					}else if(resultRate2 <= 0.4 && resultRate2 >=0.35){
						StrongTeam = 1.6; 
						underdog = 2.8;					
					}else if(resultRate2 < 0.35 && resultRate2 > 0.3){
						StrongTeam = 1.8; 
						underdog = 2.5;	
					}else if(resultRate2 <= 0.3 && resultRate2 > 0.2){
						StrongTeam = 1.8; 
						underdog = 2.3;	
					}else if(resultRate2 <= 0.2 && resultRate2 > 0.1){
						StrongTeam = 1.8; 
						underdog = 2;	
					}else if(resultRate2 == 0){
						StrongTeam =  1.5;
						underdog = 1.5;
					}else{
						StrongTeam = 1.5;
						underdog = 1.8;
					}				 
					battingButton[cnt].setText(away_kbo.get(x).getAwayTeam()+"                           "+form.format(StrongTeam));
					battingButton[cnt2].setText(home_kbo.get(x).getHomeTeam()+"                           "+form.format(underdog));
					 cnt +=2;
					 cnt2 +=2;									
				}}   	} catch (Exception e) {e.printStackTrace();}
	}

        //배팅카드쪽 메소드 
	public void battingcard(){
		
		JPanel battingPanel = new JPanel();
		battingPanel.setBorder(new LineBorder(Color.WHITE));
		battingPanel.setBounds(815, 25, 262, 224);
	    getContentPane().add(battingPanel);
		battingPanel.setLayout(null);
		
		JLabel label_19 = new JLabel("\uBCF4\uC720\uAE08\uC561");
		label_19.setHorizontalAlignment(SwingConstants.LEFT);
		label_19.setForeground(Color.WHITE);
		label_19.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
		label_19.setBackground(Color.BLACK);
		label_19.setBounds(12, 58, 89, 17);
		battingPanel.add(label_19);
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBackground(new Color(255, 204, 0));
		panel_16.setBounds(1, 1, 260, 46);
		battingPanel.add(panel_16);
		
		JLabel label_20 = new JLabel("Betting Cart");
		label_20.setHorizontalAlignment(SwingConstants.LEFT);
		label_20.setForeground(Color.WHITE);
		label_20.setFont(new Font("양재붓꽃체L", Font.BOLD, 15));
		label_20.setBackground(Color.BLACK);
		label_20.setBounds(10, 10, 110, 28);
		panel_16.add(label_20);
		
		//보유금액
		textField_1 = new JTextField("");
		//글씨 오른쪽
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(108, 58, 142, 21);
		
		// 여기에 돈추가
		try {
			KBOModel k = new KBOModel();
			int money  = k.yourMoney(user_id);
			textField_1.setText(String.valueOf(money));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		battingPanel.add(textField_1);
		
		JLabel label_21 = new JLabel("\uC801\uC911\uAE08\uC561");
		label_21.setHorizontalAlignment(SwingConstants.LEFT);
		label_21.setForeground(Color.WHITE);
		label_21.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
		label_21.setBackground(Color.BLACK);
		label_21.setBounds(12, 128, 89, 17);
		battingPanel.add(label_21);
		
		//적중금액
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setColumns(10);
		textField_2.setBounds(108, 128, 142, 21);
		battingPanel.add(textField_2);
		
		//배팅금액
		textField_3 = new JTextField("0");
		textField_3.setColumns(10);
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setBounds(108, 91, 142, 21);
		battingPanel.add(textField_3);
				
		JButton bettingButton = new JButton("");
		bettingButton.setForeground(Color.BLACK);
		bettingButton.setIcon(new ImageIcon("img/kbo배팅하기.png"));
		bettingButton.setBounds(14, 160, 237, 55);
		battingPanel.add(bettingButton);
		
		//입력금액 받아서 보유금액에서 빼기
		bettingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int betMoney = Integer.parseInt(textField_3.getText());
				int sumMoney = Integer.parseInt(textField_1.getText());
								
				if(textField_3.getText().equals("0")){
					JOptionPane.showMessageDialog(null,"배팅금액을 입력하세요","경고창",JOptionPane.WARNING_MESSAGE,null);
				}else
				{
				try {
					   //배팅금액    보유금액
					if(betMoney < sumMoney){
						
						KBOModel m = new KBOModel();
						
						String[] check = {"배팅","종료"};
						
						int result1 = JOptionPane.showOptionDialog
								(null,"배팅을 하시겠습니까?", "KBO",
										JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,check,"배팅 시작");
						if(result1 == JOptionPane.YES_OPTION){
							
						SimpleDateFormat formatter = new SimpleDateFormat( "yy/MM/dd", Locale.KOREA );
						Date currentTime = new Date ( );
						String dTime = formatter.format (currentTime);
				
						int checkCount = m.checkCount(result_code,user_id);
						
						if(checkCount > 0){
							JOptionPane.showMessageDialog(null,"이미 배팅하신 경기입니다!","경고창",JOptionPane.WARNING_MESSAGE,null);
							textField_2.setText("");
							textField_3.setText("0");
						}else{
							mybatMoney = Integer.parseInt(textField_3.getText()); //내가배팅한금액
							
							user_price = m.changeMoney(mybatMoney,Integer.parseInt(textField_1.getText()), user_id);				
							textField_1.setText(Integer.toString(user_price));						   
							textField_3.setText("0");
							textField_2.setText("");
							//여기에 디비에저장	
							   //유저아이디,배팅금액,배당률,날짜,당첨금액,경기번호,선택한팀
							System.out.println("******************");
							System.out.println("배팅금액: "+ mybatMoney);
							System.out.println("배당률: "+result);
							System.out.println("오늘날짜: "+dTime);
							System.out.println("적중금액: "+finalMoney);
							System.out.println("매치코드: "+result_code);
							System.out.println("선택한팀: "+selectTeam);
							
							m.inKBO_bet(user_id,mybatMoney
									,Double.parseDouble(result),dTime,finalMoney,result_code,selectTeam);				
						}
			
						if(buttonNumber == 0){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber+1].setEnabled(false);
						}else if(buttonNumber == 1){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber-1].setEnabled(false);
						}else if(buttonNumber == 2){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber+1].setEnabled(false);
						}else if(buttonNumber == 3){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber-1].setEnabled(false);
						}else if(buttonNumber == 4){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber+1].setEnabled(false);
						}else if(buttonNumber == 5){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber-1].setEnabled(false);
						}else if(buttonNumber == 6){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber+1].setEnabled(false);
						}else if(buttonNumber == 7){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber-1].setEnabled(false);
						}else if(buttonNumber == 8){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber+1].setEnabled(false);
						}else if(buttonNumber == 9){
							battingButton[buttonNumber].setEnabled(false);
							battingButton[buttonNumber-1].setEnabled(false);
						}}
					}else{
						JOptionPane.showMessageDialog(null,"보유금액 부족!","경고창",JOptionPane.WARNING_MESSAGE,null);
						textField_3.setText("0");
						textField_2.setText("");
					}}catch (Exception e1) {e1.printStackTrace();}}						
			}});
		
		JLabel label_22 = new JLabel("\uBC30\uD305\uAE08\uC561");
		label_22.setHorizontalAlignment(SwingConstants.LEFT);
		label_22.setForeground(Color.WHITE);
		label_22.setFont(new Font("양재붓꽃체L", Font.BOLD, 14));
		label_22.setBackground(Color.BLACK);
		label_22.setBounds(12, 93, 89, 17);
		battingPanel.add(label_22);
		
		JLabel KBOTitle = new JLabel("");
		KBOTitle.setIcon(new ImageIcon("img\\Korea_Baseball_Organization.png"));
		KBOTitle.setBounds(165, 22, 500, 148);
		getContentPane().add(KBOTitle);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(Color.WHITE));
		mainPanel.setBounds(10, 10, 1083, 547);
		getContentPane().add(mainPanel);
	}
	public void gameing(){		
		//현재시간과 비교해서 경기시작 ~ 3시간이후전까지 배팅금지
		SimpleDateFormat formatter = new SimpleDateFormat ( "HHmm", Locale.KOREA );
		Date currentTime = new Date ( );
		String dTime = formatter.format ( currentTime );
	
			if(countGame>0){
				String temp1 = time.substring(time.length()-5,time.length()-3);
				String temp2 = time.substring(time.length()-2,time.length());		
				
				String result = temp1+temp2;
	    
			     if(Integer.parseInt(dTime) >= Integer.parseInt(result) && Integer.parseInt(dTime) <2400){
	 
			    	 for(int x=0; x<countGame*2; x++){
			    		 battingButton[x].setText("배팅하실 수 없습니다.");	  
			    		 battingButton[x].setEnabled(false);
			    	 }} 
			}else{
				 for(int x=0; x<countGame*2; x++){
		    		 battingButton[x].setText("경기가 없습니다.");
		    		 battingButton[x].setEnabled(false);	    	
		    	 }}     
	}		
	//액션처리 
	public void action(){			
		for(int x =0; x< countGame*2; x++){
			battingButton[x].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					for(int y=0; y<countGame*2; y++){
						
						if(obj == battingButton[y]){
							
							if(countGame >0){
								String temp = battingButton[y].getText();
								
								result = temp.substring(temp.length()-4,temp.length()); //팀 배당률
							
								try {
									KBOModel kbom = new KBOModel();
									
							if(textField_3.getText().equals("0")){
								JOptionPane.showMessageDialog(null,"배팅금액을 입력하세요!","경고창",JOptionPane.WARNING_MESSAGE,null);
							}else{
									if(y == 1){
										selectTeam = home_kbo.get(y-1).getHomeTeam();	
										home_code = kbom.getHomeCode(selectTeam);
										away_code =0;
										buttonNumber =y;
									}else if(y == 3){
										selectTeam = home_kbo.get(y-2).getHomeTeam();
										home_code = kbom.getHomeCode(selectTeam);
										away_code =0;
										buttonNumber =y;
									}else if(y == 5){
										selectTeam = home_kbo.get(y-3).getHomeTeam();
										home_code = kbom.getHomeCode(selectTeam);
										away_code =0;
										buttonNumber =y;
									}else if(y == 7){
										selectTeam = home_kbo.get(y-4).getHomeTeam();
										home_code = kbom.getHomeCode(selectTeam);
										away_code =0;
										buttonNumber =y;
									}else if(y == 9){
										selectTeam = home_kbo.get(y-5).getHomeTeam();
										home_code = kbom.getHomeCode(selectTeam);
										away_code =0;
										buttonNumber =y;
									}								
									if(y == 0){
										selectTeam = away_kbo.get(y).getAwayTeam();	
										 away_code = kbom.getAwayCode(selectTeam);
										 home_code =0;
										 buttonNumber =y;
									}else if(y == 2){
										selectTeam = away_kbo.get(y-1).getAwayTeam();
										away_code = kbom.getAwayCode(selectTeam);
										home_code =0;
										buttonNumber =y;
									}else if(y == 4){
										selectTeam = away_kbo.get(y-2).getAwayTeam();
										away_code = kbom.getAwayCode(selectTeam);
										home_code =0;
										buttonNumber =y;			
									}else if(y == 6){
										selectTeam = away_kbo.get(y-3).getAwayTeam();
										away_code = kbom.getAwayCode(selectTeam);
										home_code =0;
										buttonNumber =y;			
									}else if(y == 8){
										selectTeam = away_kbo.get(y-4).getAwayTeam();
										away_code = kbom.getAwayCode(selectTeam);
										home_code =0;
										buttonNumber =y;
									}
									
							}		
									System.out.println("--------------------------");
									System.out.println("당신의 선택한팀은 "+selectTeam);
									System.out.println("그팀의 배당률"+result);
									System.out.println("어웨이코드 "+home_code);
									System.out.println("홈코드 "+away_code);
									System.out.println("--------------------------");
									
									if(away_code == 0 && home_code >0){
										result_code = home_code;
										System.out.println(result_code);
									}else{
										result_code = away_code;		
										System.out.println(result_code);
									}
																		
								} catch (Exception e1) {e1.printStackTrace();}
								
								int myMoney = Integer.parseInt(textField_3.getText());		
								double winRate = Double.parseDouble(result);
						        double expectHit = winRate*myMoney;	
					
						        finalMoney = (int)expectHit; // 적중금액
								
								textField_2.setText(Integer.toString(finalMoney));															
								}else{
									battingButton[y].setText("");
									
								}}}}
			});}	

		for(int x=0; x<home_kbo.size(); x++ ){
			
			verseLabel[x].addMouseListener(new MouseAdapter() {	
				public void mouseClicked(MouseEvent e) {
					Object obj = e.getSource();
					for(int i=0; i<home_kbo.size(); i++){
						
						if(obj ==  verseLabel[i]){
							
							if(textField_3.getText().equals("0")){
								JOptionPane.showMessageDialog(null,"배팅금액을 입력하세요!","경고창",JOptionPane.WARNING_MESSAGE,null);
							}else{
								//무승부는 무조건 배당률 2배 야구는 무승부 확률이 희박 하므로
								mybatMoney = Integer.parseInt(textField_3.getText());
							    result = "2.0"; //배당률
								finalMoney = mybatMoney  * (int)Double.parseDouble(result) ;  // 적중금액
								selectTeam = "없음"; // 무승부를 선택했으므로 선택한 팀은 없음
								textField_2.setText(Integer.toString(finalMoney));
								try {
									KBOModel kbom = new KBOModel();
											
										String tempTeam = away_kbo.get(i).getAwayTeam();
										result_code = kbom.getAwayCode(tempTeam); //match_code 										
										if(i == 0){
											buttonNumber = 0;
										}else if(i == 1){
											buttonNumber = 2;
										}else if(i == 2){
											buttonNumber = 4;
										}else if(i == 3){
											buttonNumber = 6;
										}else{
											buttonNumber = 8;
										}									
										System.out.println("라벨번호는"+i);
										System.out.println(result_code);
										System.out.println(mybatMoney);

								} catch (Exception e1) {e1.printStackTrace();}
							}}}}  });
					}
		
		}
		}
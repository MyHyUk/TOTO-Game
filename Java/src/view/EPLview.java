package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.print.DocFlavor.STRING;
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

import model.EplModel;
import model.KBOModel;
import model.rec.ScBetVO;
import model.rec.TeamVO;


public class EPLview extends JFrame {
	
	private String user_id; //����ھ��̵�
	private int user_price; // ���� ������
	public static ScBetVO sc;

	
	private JTextField textField;
	private JLabel lbNewLabel3,label_1;
	private JTextField textField_1; //�����ܾ�
	private JTextField textField_2; //���߱ݾ�
	private JTextField textField_3;// ���ñݾ�

     //��Ⱑ �̷����¸��� ���� �����غ���?	
	private JLayeredPane layeredpane;
	private JLabel[] verseLabel; //�¹��� �̹����� 
	private JButton[] battingButton; // ������ �����ִ� ��ư(�ٽ�)
	private JLabel[] home;//Ȩ ���� �����ִ� �̹���
	private JLabel[] away; //����� ���� �����ִ� �̹���
	private JLabel[] WDL; //win,draw,lose
	
	//���÷�,�ð�,�����ð� ���� label
	private JLabel[] gameTime; // ������ �ð�
	private JLabel[] threadTime; // �����ð� �̰� ������� ���۽ð��� ���缭 ��� �ð��� ����
	
	//Ȩ��,������� 
	ArrayList<TeamVO> at = new ArrayList<TeamVO>();
	ArrayList<TeamVO> at2 = new ArrayList<TeamVO>();
	
	private int savemoney;
	private String[] str;
	
	
	
	
	//�ð������� �Ұ� �Ʒ���
	public EPLview(String user_id) {
		setTitle("EPL ���ð���");
		ImageIcon img_logo = new ImageIcon("img/logo.png");
		setIconImage(img_logo.getImage());
		this.user_id =user_id;
		
	    getContentPane().setLayout(null);
		setSize(1130,1000);
		
		match();
		battingcard();
		
		setVisible(true);
			
	}
	// ��ġ�� �̷��� ui �޼ҵ�
    public void match(){
	
    	
    	try {
			EplModel epl = new EplModel();
			at=epl.homematch();
			at2=epl.awaymatch();
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    layeredpane=  new JLayeredPane(); // ui�ٹд�� �̰� ���� ���Ѱ� ����	
    layeredpane.setBounds(12,163,800,700);
    layeredpane.setBorder(new LineBorder(Color.BLACK));
    layeredpane.setLayout(null);	
    
   System.out.println("ȣȣ"+at.size()+"����");
    
    WDL = new JLabel[20];
    int wdlYline = 30;
    
    gameTime = new JLabel[20];
   
    for(int x=0; x < at.size(); x++){
    	gameTime[x] = new JLabel();
    	gameTime[x].setText(at.get(x).getStarttime());
    	gameTime[x].setForeground(Color.WHITE);
    	gameTime[x].setFont(new Font("����ײ�üL", Font.BOLD, 12));
    	gameTime[x].setBounds(10,wdlYline,90,30);
    	
    	
    	wdlYline += 65;
    	
    	layeredpane.add(gameTime[x]);
    	
    }
    wdlYline=30;
    
    for(int x=0; x < at.size(); x++){
    	WDL[x] = new JLabel();
    	WDL[x].setText("�¹���");
    	WDL[x].setForeground(Color.WHITE);
    	WDL[x].setFont(new Font("����ײ�üL", Font.BOLD, 14));
    	WDL[x].setBounds(116,wdlYline,60,30);
    	
    	
    	wdlYline += 65;
    	
    	layeredpane.add(WDL[x]);
    	
    }
          
	battingButton = new JButton[30]; //10�����ܿ� ���� ������ ��ư
   
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
	
	int buttonXline6 = 195;
	int buttonYline6 = 355;
	
	int buttonXline7 = 195;
	int buttonYline7 = 420;
	
	int buttonXline8= 195;
	int buttonYline8 = 485;
	
	int buttonXline9 = 195;
	int buttonYline9 = 550;
	
	int buttonXline10 = 195;
	int buttonYline10 = 615;
	

	
	
	System.out.println("����"+at.size()+"����");
	for(int x=0; x<at.size()*2; x++){
		//battingButton[x] = new JButton(new ImageIcon("KBOimg\\���̸����÷���ư.PNG"));
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
		}else if (x == 8 || x ==9){
			battingButton[x].setBounds(buttonXline5,buttonYline5,210,36);
			buttonXline5 += 290;
		}else if (x == 10 || x ==11){
			battingButton[x].setBounds(buttonXline6,buttonYline6,210,36);
			buttonXline6 += 290;
		}else if (x == 12 || x ==13){
			battingButton[x].setBounds(buttonXline7,buttonYline7,210,36);
			buttonXline7 += 290;
		}else if (x == 14 || x ==15){
			battingButton[x].setBounds(buttonXline8,buttonYline8,210,36);
			buttonXline8 += 290;
		}else if (x == 16 || x ==17){
			battingButton[x].setBounds(buttonXline9,buttonYline9,210,36);
			buttonXline9 += 290;
		}else if (x == 18 || x ==19){
			battingButton[x].setBounds(buttonXline10,buttonYline10,210,36);
			buttonXline10 += 290;
		}
	layeredpane.add(battingButton[x]);
		
	}
	//Ȩ�� ����� �߰�
	
	
	//�·��� ���� �ٲٱ�
    for(int i=0;i<at.size();i++){
    	double resultRate=at.get(i).getWinRate()-at2.get(i).getWinRate();
    	if(resultRate>0){
    		if(resultRate > 0.5){				
    			at.get(i).setWinRate(1.3);
    			at2.get(i).setWinRate(4.5);
			}else if(resultRate <=0.5 && resultRate >0.4){
				at.get(i).setWinRate(1.5); 
				at2.get(i).setWinRate(3.5);
			}else if(resultRate <= 0.4 && resultRate >=0.35){
				at.get(i).setWinRate(1.6); 
				at2.get(i).setWinRate(2.8);					
			}else if(resultRate < 0.35 && resultRate > 0.3){
				at.get(i).setWinRate(1.8); 
				at2.get(i).setWinRate(2.5);	
			}else if(resultRate <= 0.3 && resultRate > 0.2){
				at.get(i).setWinRate(1.8); 
				at2.get(i).setWinRate(2.3);	
			}else if(resultRate <= 0.2 && resultRate > 0.1){
				at.get(i).setWinRate(1.8); 
				at2.get(i).setWinRate(2);	
			}else if(resultRate == 0){
				at.get(i).setWinRate(1.5);
				at2.get(i).setWinRate(1.5);
			}else{
				at.get(i).setWinRate(1.5);
				at2.get(i).setWinRate(1.8);
			}
    		
    		
    	}else if(resultRate<0){
    		if(resultRate > 0.5){				
    			at2.get(i).setWinRate(1.3);
    			at.get(i).setWinRate(4.5);
			}else if(resultRate <=0.5 && resultRate >0.4){
				at2.get(i).setWinRate(1.5); 
				at.get(i).setWinRate(3.5);
			}else if(resultRate <= 0.4 && resultRate >=0.35){
				at2.get(i).setWinRate(1.6); 
				at.get(i).setWinRate(2.8);					
			}else if(resultRate < 0.35 && resultRate > 0.3){
				at2.get(i).setWinRate(1.8); 
				at.get(i).setWinRate(2.5);	
			}else if(resultRate <= 0.3 && resultRate > 0.2){
				at2.get(i).setWinRate(1.8); 
				at.get(i).setWinRate(2.3);	
			}else if(resultRate <= 0.2 && resultRate > 0.1){
				at2.get(i).setWinRate(1.8); 
				at.get(i).setWinRate(2);	
			}else if(resultRate == 0){
				at2.get(i).setWinRate(1.5);
				at.get(i).setWinRate(1.5);
			}else{
				at2.get(i).setWinRate(1.5);
				at.get(i).setWinRate(1.8);
			}
    		
    		
    		
    		
    		
    	}	
    }
	
	
	for(int i=0;i<=at.size();i++){
		
		if(i<at.size()){
		
	battingButton[i*2].setText(at.get(i).getTeam_name()+"         "+at.get(i).getWinRate());
	  
		battingButton[i*2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Object obj = e.getSource();
				for(int i =0 ; i < at.size() ; i++)
					if(obj == battingButton[i*2]){
						
						String money = String.valueOf((Integer.valueOf(textField_3.getText())*at.get(i).getWinRate()));
						
						textField_2.setText(money);
						
						try {
							EplModel em = new EplModel();	
							 sc = new ScBetVO();
							
						
							sc.setMatch_code(em.homematchcode(at.get(i).getTeam_name()));
							
						
							
							if(em.matchcode(sc.getMatch_code(),user_id)==false){
								JOptionPane.showMessageDialog(null, "�̹� �����ϼ˽��ϴ�");
								battingButton[i*2].setEnabled(false);
								battingButton[i*2+1].setEnabled(false);
							}
							
							sc.setCust_id(user_id);
							sc.setSc_bet_price(Integer.valueOf(textField_3.getText()));
							sc.setSc_bet_prize(Double.valueOf(money));
							sc.setSc_multiple(at.get(i).getWinRate());
							sc.setSc_bet_result(at.get(i).getTeam_name());
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					
						
						
					}
				
			}
		});
	   	
	
	
		
		}		
		}
    for(int i=1;i<=at2.size();i++){
		
		if(i<=at2.size()){
	battingButton[2*i-1].setText(at2.get(i-1).getTeam_name()+"     "+at2.get(i-1).getWinRate());	
	battingButton[2*i-1].addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			Object obj = e.getSource();
			for(int i =1 ; i <= at2.size() ; i++)
				if(obj == battingButton[2*i-1]){
					String money = String.valueOf(Integer.valueOf(textField_3.getText())*at.get(i-1).getWinRate());
					
					textField_2.setText(money);
					
					try {
						EplModel em = new EplModel();	
						 sc = new ScBetVO();
						
						System.out.println(at2.size()+"����");
						sc.setMatch_code(em.awaymatchcode(at2.get(i-1).getTeam_name()));
						
						
						if(em.matchcode(sc.getMatch_code(),user_id)==false){
							JOptionPane.showMessageDialog(null, "�̹� �����ϼ˽��ϴ�");
							battingButton[2*i-1].setEnabled(false);
							battingButton[2*i-2].setEnabled(false);
						}
						
						sc.setCust_id(user_id);
						sc.setSc_bet_price(Integer.valueOf(textField_3.getText()));
						sc.setSc_bet_prize(Double.valueOf(money));
						sc.setSc_multiple(at2.get(i-1).getWinRate());
						sc.setSc_bet_result(at2.get(i-1).getTeam_name());
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		}
	});
		
		
		
		
		
		}
		}


	
	//Ȩ���� ��������� ���� �߰� 

	int homeYline = 37;
	home = new JLabel[15];
	for(int x=0; x< at.size(); x++){
		
		home[x] = new JLabel(new ImageIcon("KBOimg\\Ȩ���ΰ�.PNG"));
		home[x].setBounds(170,homeYline,20,20);
		homeYline += 65;	
		layeredpane.add(home[x]);
	}
	
	int awayYline = 37;
	away = new JLabel[15];
	
	for(int x=0; x< at.size(); x++){
		
		away[x] = new JLabel(new ImageIcon("KBOimg\\�������ΰ�.PNG"));
		away[x].setBounds(700,awayYline,20,20);
		awayYline += 65;
		layeredpane.add(away[x]);
	}
	
	//ù��° vs	
	verseLabel = new JLabel[10];
	int yLine = 30;
	
	//vs���� �߰�
	for(int x=0; x<at.size(); x++){
		
		verseLabel[x] = new JLabel(new ImageIcon("KBOimg\\vs.PNG"));	
		verseLabel[x].setBounds(410,yLine,70,36);
		
		layeredpane.add(verseLabel[x]);	
		yLine += 65;
	}
	getContentPane().add(layeredpane);
	}
    
        //����ī���� �޼ҵ� 
	public void battingcard(){
		
		JPanel battingPanel = new JPanel();
		battingPanel.setBorder(new LineBorder(Color.WHITE));
		battingPanel.setBounds(830, 29, 262, 224);
	    getContentPane().add(battingPanel);
		battingPanel.setLayout(null);
		
		JLabel label_19 = new JLabel("\uBCF4\uC720\uAE08\uC561");
		label_19.setHorizontalAlignment(SwingConstants.LEFT);
		label_19.setForeground(Color.WHITE);
		label_19.setFont(new Font("����ײ�üL", Font.PLAIN, 14));
		label_19.setBackground(Color.BLACK);
		label_19.setBounds(12, 58, 89, 17);
		battingPanel.add(label_19);
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBackground(new Color(102, 153, 255));
		panel_16.setBounds(0, 0, 261, 48);
		battingPanel.add(panel_16);
		
		JLabel label_20 = new JLabel("Betting Cart");
		label_20.setHorizontalAlignment(SwingConstants.LEFT);
		label_20.setForeground(Color.WHITE);
		label_20.setFont(new Font("����ײ�üL", Font.BOLD, 14));
		label_20.setBackground(Color.BLACK);
		label_20.setBounds(15, 10, 89, 17);
		panel_16.add(label_20);
		
		EplModel em;
		try {
			em = new EplModel();
			savemoney=em.eplmoney(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		textField_1 = new JTextField(String.valueOf(savemoney));
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(108, 58, 142, 21);
		battingPanel.add(textField_1);
		
		JLabel label_21 = new JLabel("\uC801\uC911\uAE08\uC561");
		label_21.setHorizontalAlignment(SwingConstants.LEFT);
		label_21.setForeground(Color.WHITE);
		label_21.setFont(new Font("����ײ�üL", Font.PLAIN, 14));
		label_21.setBackground(Color.BLACK);
		label_21.setBounds(12, 128, 89, 17);
		battingPanel.add(label_21);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(108, 128, 142, 21);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		battingPanel.add(textField_2);
		
		textField_3 = new JTextField("0");
		textField_3.setColumns(10);
		textField_3.setBounds(108, 91, 142, 21);
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		battingPanel.add(textField_3);
		
		
		JButton bettingButton = new JButton("");
		bettingButton.setForeground(Color.BLACK);
		bettingButton.setIcon(new ImageIcon("img/epl�����ϱ�.png"));
		bettingButton.setBounds(14, 163, 237, 55);
		battingPanel.add(bettingButton);
		//�Է±ݾ� �޾Ƽ� �����ݾ׿��� ����
				bettingButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int betMoney = Integer.parseInt(textField_3.getText());
						int sumMoney = Integer.parseInt(textField_1.getText());
						
						
						if(textField_3.getText().equals("0")){
							JOptionPane.showMessageDialog(null,"���ñݾ��� �Է��ϼ���","���â",JOptionPane.WARNING_MESSAGE,null);
						}else
						{
						try {
							   //���ñݾ�    �����ݾ�
							if(betMoney < sumMoney){
								KBOModel m = new KBOModel();
								EplModel em = new EplModel();
								
								System.out.println("����"+sc.getMatch_code()+"�ȳ�");
								
								em.epl_bet(sc);
								
								/*String team=em.winteam(sc.getMatch_code());
								
								if(team==sc.getSc_bet_result()){
									em.changeMoney((int)sc.getSc_bet_prize(), user_id);
									em.update_betY(sc.getMatch_code());
									
								}*/
								
								
								String[] check = {"����","����"};
								
								int result1 = JOptionPane.showOptionDialog
										(null,"������ �Ͻðڽ��ϱ�?", "EPL",
												JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,check,"���� ����");
								if(result1 == JOptionPane.YES_OPTION){
									
								user_price = m.changeMoney(Integer.parseInt(textField_3.getText()), 
										Integer.parseInt(textField_1.getText()), user_id);
								
								textField_1.setText(Integer.toString(user_price));
								textField_3.setText("");
								}
							}else{
								JOptionPane.showMessageDialog(null,"�����ݾ� ����!","���â",JOptionPane.WARNING_MESSAGE,null);
								textField_3.setText("");
							}
						
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}}
						
						
					}
					
				});
	//�ð� ������ ���ù�ư ��������
	SimpleDateFormat formatter = new SimpleDateFormat ( "HHmm", Locale.KOREA );
	Date currentTime = new Date ( );
	String dTime = formatter.format ( currentTime );
	
	
	   

		for (int i = 0; i < at.size() * 2; i++) {

			if (i < at.size()) {
				str = at.get(i).getStarttime().split(" ");
				
			}
			if (Integer.parseInt(dTime) >= Integer.parseInt(str[1].replaceAll(":", ""))
					&& Integer.parseInt(dTime) < 2400) {
				battingButton[i+i].setEnabled(false);
				battingButton[i+(i+1)].setEnabled(false);
				battingButton[i+i].setText("�����Ͻ� �� �����ϴ�");
				battingButton[i+(i+1)].setText("�����Ͻ� �� �����ϴ�");

			}
		}
				
				
				
		JLabel label_22 = new JLabel("\uBC30\uD305\uAE08\uC561");
		label_22.setHorizontalAlignment(SwingConstants.LEFT);
		label_22.setForeground(Color.WHITE);
		label_22.setFont(new Font("����ײ�üL", Font.PLAIN, 14));
		label_22.setBackground(Color.BLACK);
		label_22.setBounds(12, 93, 89, 17);
		battingPanel.add(label_22);
		
		JLabel eplTitle = new JLabel("");
		eplTitle.setIcon(new ImageIcon("img\\epl�ΰ�.png"));
		eplTitle.setBounds(140, 10, 480, 145);
		getContentPane().add(eplTitle);
	}
	
	
	
	
}

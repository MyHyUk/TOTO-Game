package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class lottoTolltip extends JFrame{

	
		 public lottoTolltip(){
			 setTitle("로또게임방법");
			 setSize(286,381);
			 setLocation(1300,250);
			 
			 add(new JLabel(new ImageIcon("lottoimg\\로또튜토리얼.png")));
	 
			 setVisible(true);
		 }
		 
		 
		 
	
}

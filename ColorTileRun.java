


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

public class ColorTileRun {

	static Board board;
	public static void main(String[] args) {

         board=new Board();
         
	}

}
class MyButton extends JButton
{
	private static final long serialVersionUID = 1L;
	JButton jb;
	int indexOfButton; 
	Color color;
     MyButton(JButton jb,int indexOfButton,Color color)
     {
    	 super(jb.getText());
    	 this.jb=jb;
    	 this.indexOfButton=indexOfButton;
    	 this.color=color;
     }
     public int getIndex()
     {
    	 return indexOfButton;
     }
     @Override
    public void setBackground(Color bg) {
    	super.setBackground(bg);
    }
}
  
class Board implements ActionListener{
	JFrame jf;
	MyButton button ;
	static Color colorSet[]=new Color[]{Color.WHITE,Color.BLACK,Color.RED,Color.BLACK,
			                            Color.WHITE,Color.BLUE,Color.RED,Color.BLUE,
			                            Color.YELLOW,Color.MAGENTA,Color.PINK,Color.PINK,
			                            Color.GREEN,Color.MAGENTA,Color.GREEN,Color.YELLOW};
    static int clicks=0;
    static int score;
    static MyButton b[];
    MyButton alreadyClicked;
    JTextField scoretext;
    
	Board(){
		setGUI();
		Collections.shuffle(Arrays.asList(colorSet));
	}
 
	public void setGUI()
	{
		jf=new JFrame(" ColorTiles ");
		jf.setSize(600,600);
	
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout());
		JPanel jp=new JPanel();
		jp.setLayout(new GridLayout(4,4,3,3));
		b=new MyButton[16];
		JPanel header=new JPanel();
		JLabel top =new JLabel("  All the best   ");
		button=new MyButton(new JButton("Quit"),100,Color.PINK);
		button.addActionListener(this);
		JLabel scoreLabel =new JLabel("      Your Score");
		scoretext=new JTextField();
		scoretext.setText("0");
		//top.setSize(400,50);
		header.setLayout(new FlowLayout(5));
		header.add(top);
		header.add(button);
		header.add(scoreLabel);
		header.add(scoretext);
		header.setVisible(true);
		jf.add(header,BorderLayout.NORTH);
		JLabel space1=new JLabel("  ");
		JLabel space2=new JLabel("  ");
		JLabel space3=new JLabel("  ");
		jf.add(space1,BorderLayout.SOUTH);
		jf.add(space2,BorderLayout.EAST);		
		jf.add(space3,BorderLayout.WEST);
		jf.add(jp,BorderLayout.CENTER);

		for(int i=0;i<16;i++)
		{
			b[i]=new MyButton(new JButton(),i,Color.CYAN);
			b[i].setBackground(Color.CYAN);
			b[i].setSize(20,20);
			b[i].addActionListener(this);
			jp.add(b[i]);
		}
		jp.setVisible(true);
		score=0;
	    jf.setVisible(true);
	    jf.setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		MyButton clicked=(MyButton)e.getSource();
		if(clicked==button)
		{
			System.exit(0);
		}
		
		clicked.setBackground(colorSet[clicked.indexOfButton]);
		if(clicks==0){
			clicks=1;
			alreadyClicked=clicked;
			alreadyClicked.setEnabled(false);
		}
		else if(clicks==1)
		{
			if(alreadyClicked.getBackground().hashCode()==clicked.getBackground().hashCode())
				{
				score+=2;
				
				scoretext.setText(Integer.toString(score));
				//System.out.println(score);
				clicked.setEnabled(false);
				alreadyClicked.setEnabled(false);
				}
			else
			{
				clicked.setBackground(Color.CYAN);
				alreadyClicked.setBackground(Color.CYAN);
				clicked.setContentAreaFilled(true);
				alreadyClicked.setContentAreaFilled(true);
		        //System.out.println("no match"+clicked.jb.getBackground()+" "+alreadyClicked.jb.getBackground());
		        alreadyClicked.setEnabled(true);
			}
			clicks=0;
			alreadyClicked=null;
		}
		
	}
}


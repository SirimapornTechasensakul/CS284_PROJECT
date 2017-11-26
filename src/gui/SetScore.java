package gui;
import logic.*;

import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.ErrorScoreException;

public class SetScore extends JFrame implements ActionListener {

	private int quizs;
	private int mid_scr, fin_scr;
	private List<JTextField> scr_txt;
	
	
	private JPanel grid;
	private JButton bck_btn, sub_btn;
	
	public SetScore(int quizs) {
		// TODO Auto-generated constructor stub
		//initialize
		this.quizs = quizs;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 200, 600, 600);
		setTitle("Set Score");
		
		
		scr_txt = new ArrayList<>(); //prepare to store JTextField
		
		grid = new JPanel();
		grid.setLayout(new GridLayout(quizs+3, 0));
		
		//add header
		JPanel header = new JPanel();
		JLabel setScr = new JLabel("Set Score");
		setScr.setFont(Holder.font);
		header.add(setScr);
		grid.add(header);
		
		//add quiz
		for(int i = 0;i < quizs; i++){
			JPanel a = new JPanel();
			a.add(new JLabel("Quiz " + (i+1)));
			JTextField qz_txt = new JTextField(10);
			scr_txt.add(i, qz_txt);
			a.add(qz_txt);
			grid.add(a);
		}
		
		//add midterm and final
		JPanel b = new JPanel();
		JTextField mid_txt = new JTextField(10);
		b.add(new JLabel("Midterm"));
		b.add(mid_txt);
		scr_txt.add(mid_txt);
		
		JPanel c = new JPanel();
		JTextField fin_txt = new JTextField(10);
		c.add(new JLabel("Final"));
		c.add(fin_txt);
		scr_txt.add(fin_txt);
		
		JPanel subgrid = new JPanel();
		subgrid.setLayout(new GridLayout(0, 2));
		subgrid.add(b);
		subgrid.add(c);
		grid.add(subgrid);
		
		//add button
		bck_btn = new JButton("Back");
		sub_btn = new JButton("Submit");
		bck_btn.addActionListener(this);
		sub_btn.addActionListener(this);
		JPanel d = new JPanel();
		d.add(sub_btn);
		d.add(bck_btn);
		grid.add(d);
		

		this.add(grid);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(bck_btn.getActionCommand())){
			logic.Main.home.setVisible(true);
			dispose();
		}else if(e.getActionCommand().equals(sub_btn.getActionCommand())){
			logic.Holder.remove(); //remove numbers of quiz and each maximum quiz score
			try{int sum = 0;
				for(int i =0; i< quizs+2;i++){
					String s = scr_txt.get(i).getText();
					for(int j =0; j< s.length(); j++){
						char c = s.charAt(j);
						if(!Character.isDigit(c)){
							throw new NumberFormatException();
						}
					}
					int a = Integer.parseInt(s);
					logic.Holder.add(a);
					sum += a ;
				}
				if(sum != 100) {
					logic.Holder.remove();
					throw new ErrorScoreException(sum);
				}
				logic.Main.home.setVisible(true);
				dispose();
			}catch(NumberFormatException n){
				JOptionPane.showMessageDialog(this, "You must fill only digit(s)");
			}catch(ErrorScoreException n){
				JOptionPane.showMessageDialog(this, n.getMessage());
			}
		}
	}

}

package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import logic.Holder;
import logic.Main;
import logic.Student;

public class ScorePreview extends JFrame implements ActionListener {
	//everything in this class is similar to FillUpScore
	private JPanel grid;
	private JScrollPane jspn;
	private int studentNum, quizNum;
	private JButton edit_btn, sub_btn;
	
	public ScorePreview() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 200, 1330, 960);
		setTitle("Score Preview");
		
		studentNum = Holder.getNumStudent();
		quizNum = Holder.getNumQuiz();

		grid = new JPanel(new GridLayout(studentNum+3, 1));
		jspn = new JScrollPane(grid);
		
		jspn.getVerticalScrollBar().setUnitIncrement(50); //set vertical speed scroll
		
		JPanel menu = new JPanel();
		JLabel fillSc = new JLabel("Score Preview");
		fillSc.setFont(Holder.font);
		menu.add(fillSc);
		grid.add(menu);
		
		JPanel header = new JPanel();
		JLabel idh = new JLabel("ID and Firstname - Lastname");
		idh.setPreferredSize(new Dimension(300, 10));
		header.add(idh);
		
		for(int k =0; k<quizNum+2; k++){
			JLabel quizlb;
			if(k == quizNum - 2){
				quizlb = new JLabel("Midterm("+ Holder.getMaxQuiz(k) +")");
			}else if(k == quizNum - 1){
				quizlb = new JLabel("Final("+ Holder.getMaxQuiz(k) +")");
			}else if(k == quizNum){
				quizlb = new JLabel("Total Score(100)");
			}else if(k == quizNum+1){
				quizlb = new JLabel("Grade");
			}else{
				quizlb = new JLabel("Quiz"+k+"("+ Holder.getMaxQuiz(k) +")");
			}
			quizlb.setPreferredSize(new Dimension(114, 20));
			header.add(quizlb);
		}
		grid.add(header);
		
		for(int i=0; i< studentNum; i++){
			Student s = Holder.getStudent(i);
			String id = s.getId();
			String fname = s.getFname();
			String lname = s.getLname();
			JPanel a = new JPanel();
			JLabel label = new JLabel(id + " " + fname+ " "+ lname);
			label.setPreferredSize(new Dimension(300, 10));
			a.add(label);
			for(int j=0; j < quizNum+2; j++){
				JLabel scrlb;
				if(j == quizNum){
					scrlb = new JLabel(""+s.getTotscr());
				}else if(j == quizNum+1){
					scrlb = new JLabel(s.getGrd());
				}else{
					scrlb = new JLabel(""+s.getScore(j));
				}
				scrlb.setPreferredSize(new Dimension(114, 20));
				a.add(scrlb);
			}
			grid.add(a);
		}
		
		JPanel bottom = new JPanel();
		sub_btn = new JButton("Submit");
		sub_btn.addActionListener(this);
		edit_btn = new JButton("Edit Score");
		edit_btn.addActionListener(this);
		bottom.add(sub_btn);
		bottom.add(edit_btn);
		grid.add(bottom);
		
		this.add(jspn);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(sub_btn.getActionCommand())){
			Main.home.setVisible(true);
			Holder.setReadyToExport();
			dispose();
		}else if(e.getActionCommand().equals(edit_btn.getActionCommand())){
			FillUpScore fillUpform = new FillUpScore();
			dispose();
		}
	}

}

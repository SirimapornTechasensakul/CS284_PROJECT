package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import logic.Holder;
import logic.Main;
import logic.OutOfRangeException;
import logic.Student;

public class FillUpScore extends JFrame implements ActionListener {

	private JPanel grid;
	
	//jspn is a pane for using scroll bar
	private JScrollPane jspn;
	
	//tfList is a list contains all Textfield
	private ArrayList<JTextField> tfList;
	
	//studentNum is numbers of student, quizNum is numbers of quiz(included mid,fin)
	private int studentNum, quizNum; 
	private JButton bck_btn, sub_btn, clr_btn;
	
	public FillUpScore() { //this use for editing score(just put any integer parameter in constructor)
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 200, 1330, 960);
		setTitle("Inform Students' Score");
		
		Holder.setNotReadyToExport();
		studentNum = Holder.getNumStudent();
		quizNum = Holder.getNumQuiz();
		tfList = new ArrayList<JTextField>();
		grid = new JPanel(new GridLayout(studentNum+4, 1));
		jspn = new JScrollPane(grid);
		
		jspn.getVerticalScrollBar().setUnitIncrement(50); //set vertical speed scroll
		
		JPanel menu = new JPanel();
		JLabel fillSc = new JLabel("Fill Up Score");
		fillSc.setFont(Holder.font);
		menu.add(fillSc);
		grid.add(menu);
		
		JPanel header = new JPanel();
		JLabel idh = new JLabel("ID and Firstname - Lastname");
		idh.setPreferredSize(new Dimension(300, 10));
		header.add(idh);
		
		for(int k =0; k<quizNum; k++){
			JLabel quizlb;
			if(k == quizNum - 2){
				quizlb = new JLabel("Midterm("+ Holder.getMaxQuiz(k) +")");
			}else if(k == quizNum - 1){
				quizlb = new JLabel("Final("+ Holder.getMaxQuiz(k) +")");
			}else{
				quizlb = new JLabel("Quiz"+(k+1)+"("+ Holder.getMaxQuiz(k) +")");
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
			for(int j=0; j < quizNum; j++){
				JTextField scrtf = new JTextField(10);
				scrtf.setText(""+s.getScore(j)); // set score in Textfield as a previous score
				tfList.add(scrtf);
				a.add(scrtf);
			}
			grid.add(a);
		}
		
		//add clear button
		JPanel clear = new JPanel();
		clr_btn = new JButton("Clear Score");
		clr_btn.addActionListener(this);
		clear.add(clr_btn);
		grid.add(clear);
		
		JPanel bottom = new JPanel();
		sub_btn = new JButton("Submit");
		sub_btn.addActionListener(this);
		bck_btn = new JButton("Back");
		bck_btn.addActionListener(this);
		bottom.add(sub_btn);
		bottom.add(bck_btn);
		grid.add(bottom);
		
		this.add(jspn);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(bck_btn.getActionCommand())){
			Main.home.setVisible(true);
			dispose();
		}
		else if(e.getActionCommand().equals(sub_btn.getActionCommand())){
			try{ //try to get score from TextFeild
				for(int i = 0; i< tfList.size(); i++){ //access one TextFeild at a time
					JTextField tf = tfList.get(i);
					String scr = tf.getText();
					for(int j = 0 ; j< scr.length(); j++){ // check scr is a positive real number or not
						char c = scr.charAt(j);
						if(Character.isDigit(c) || c =='.'){} // Yes do nothing
						else{
							throw new NumberFormatException(); // No warning
						}
					}
					double scr_d = Double.parseDouble(scr);
					Student s = Holder.getStudent(i/quizNum);
					if(scr_d > Holder.getMaxQuiz(i%quizNum)){ //check score is greater than max or not
						throw new OutOfRangeException(1); //Yes, warning
					}
					//No, add scr_d to the student
					if(s.getNumScore() > i % quizNum) s.setScore(i % quizNum, scr_d); //check if the student has score already, then change his score to new score
			
					else s.addScore(scr_d); // if doesn't have score yet, then add score. 
					System.out.println(""+i % quizNum+ " " + quizNum);
					// when student s knows entire score then compute grade
					if(i % quizNum == quizNum-1) s.calGrd(); 
				}
				dispose();
				ScorePreview scorePreview = new ScorePreview(); //swicth to ScorePreview 
			}catch(NumberFormatException n){
//				System.out.print("You must fill only positive real number and you must fill score completely.");
				JOptionPane.showMessageDialog(this, "You must fill only positive real number and you must fill score completely.");
			} catch (OutOfRangeException n) {
//				System.out.print(n.getMessage());
				JOptionPane.showMessageDialog(this, n.getLocalizedMessage());
			}
			
		}
		else if(e.getActionCommand().equals(clr_btn.getActionCommand())){
			String ObjButtons[] = {"Yes","No"};
	        int PromptResult = JOptionPane.showOptionDialog(null,"Do you want to clear up your score.","Clear Score",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
	        if(PromptResult==JOptionPane.YES_OPTION)
	        {
	        	
	            for(int i =0 ; i < tfList.size(); i++){
	            	tfList.get(i).setText("0.0");
	            	if(i % quizNum == 0){
	            		Holder.getStudent(i / quizNum).clearScore();
	            		Holder.setNotReadyToExport();
	            	}
	            }
	        }
		}
	}

}

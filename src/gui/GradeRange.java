package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.ErrorScoreException;
import logic.Holder;
import logic.OutOfRangeException;

public class GradeRange extends JFrame implements ActionListener {
	
	private List<JTextField> min_tx;
	private JPanel grid;
	private JButton bck_btn, sub_btn;
	
	public GradeRange() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 200, 600, 600);
		setTitle("Grade Range");
		
		min_tx = new ArrayList<JTextField>();
		
		grid = new JPanel(new GridLayout(10,0));
		
		JPanel txtPanel= new JPanel();
		JLabel grdRng = new JLabel("Grade Range");
		grdRng.setFont(Holder.font);
		txtPanel.add(grdRng);
		grid.add(txtPanel);
		
		for(int i = 0; i<8; i++){
			JPanel b = new JPanel();
			JLabel a ;
			int grade = Holder.getGradeScore(i);
			String range;
			switch(i){
			case 0: a = new JLabel("A  >=");range = grade>-1?""+grade:"80";break;
			case 1: a = new JLabel("B+ >=");range = grade>-1?""+grade:"75";break;
			case 2: a = new JLabel("B  >=");range = grade>-1?""+grade:"70";break;
			case 3: a = new JLabel("C+ >=");range = grade>-1?""+grade:"65";break;
			case 4: a = new JLabel("C  >=");range = grade>-1?""+grade:"60";break;
			case 5: a = new JLabel("D+ >=");range = grade>-1?""+grade:"55";break;
			case 6: a = new JLabel("D  >=");range = grade>-1?""+grade:"50";break;
			case 7: a = new JLabel("F  >=");range = grade>-1?""+grade:"0";break;
			default: a= null;range = "";
			}
			a.setPreferredSize(new Dimension(75, 20));
			b.add(a);
			JTextField tx = new JTextField(10);
			tx.setText(range);
			b.add(tx);
			min_tx.add(tx);
			grid.add(b);
		}
		
		//add button
		bck_btn = new JButton("Back");
		bck_btn.addActionListener(this);
		sub_btn = new JButton("Submit");
		sub_btn.addActionListener(this);
		JPanel btnPanel = new JPanel();
		btnPanel.add(sub_btn);
		btnPanel.add(bck_btn);
		grid.add(btnPanel);
		
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
			logic.Holder.clearGradeScore(); //Clear all grade score
			try{
			for(int i =0; i< min_tx.size();i++){
				String s = min_tx.get(i).getText();
				for(int j =0; j< s.length(); j++){
					char c = s.charAt(j);
					if(!Character.isDigit(c)){
						throw new NumberFormatException();
					}
				}
				int a = Integer.parseInt(s);
				if(logic.Holder.addGrade(a) == -1){
					logic.Holder.clearGradeScore();
					throw new OutOfRangeException();
				}
			}
			if(logic.Holder.getGradeScore(7) != 0){
				logic.Holder.clearGradeScore();
				throw new OutOfRangeException();
			}
			GradeRangeShow gradeRSh = new GradeRangeShow();
			dispose();
			}catch(NumberFormatException n){
//				System.out.print(n.getLocalizedMessage());
				JOptionPane.showMessageDialog(this, "You must fill only digit(s)");
			}catch(OutOfRangeException n){
//				System.out.print(n.getLocalizedMessage());
				JOptionPane.showMessageDialog(this, n.getLocalizedMessage());
			}
		}
	}

}

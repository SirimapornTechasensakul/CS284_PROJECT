package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Holder;

public class GradeRangeShow extends JFrame implements ActionListener {
	
	private JPanel grid;
	private JButton bck_btn, sub_btn;
	public GradeRangeShow() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 200, 600, 600);
		setTitle("Grade Range Show");
		
		grid = new JPanel(new GridLayout(10,0));
		
		JPanel txtPanel= new JPanel();
		JLabel grdRng = new JLabel("Grade Range Show");
		grdRng.setFont(Holder.font);
		txtPanel.add(grdRng);
		grid.add(txtPanel);
		
		for(int i = 0; i<8; i++){
			JPanel b = new JPanel();
			JLabel a ;
			switch(i){
			case 0: a = new JLabel("A  >= " + logic.Holder.getGradeScore(i));break;
			case 1: a = new JLabel("B+ >= " + logic.Holder.getGradeScore(i));break;
			case 2: a = new JLabel("B  >= " + logic.Holder.getGradeScore(i));break;
			case 3: a = new JLabel("C+ >= " + logic.Holder.getGradeScore(i));break;
			case 4: a = new JLabel("C  >= " + logic.Holder.getGradeScore(i));break;
			case 5: a = new JLabel("D+ >= " + logic.Holder.getGradeScore(i));break;
			case 6: a = new JLabel("D  >= " + logic.Holder.getGradeScore(i));break;
			case 7: a = new JLabel("F  >= " + logic.Holder.getGradeScore(i));break;
			default: a= null;
			}
			a.setPreferredSize(new Dimension(75, 20));
			b.add(a);
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
			GradeRange gradeRange = new GradeRange();
			dispose();
		}
		else if(e.getActionCommand().equals(sub_btn.getActionCommand())){
			logic.Main.home.setVisible(true);
			dispose();
			
		}
	}

}

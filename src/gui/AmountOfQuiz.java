package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import logic.Holder;

public class AmountOfQuiz extends JDialog {
		private JTextField nmQuiz_tx;
	    private JLabel lbnmQuiz;
	    private JButton ok_btn;
	    private JButton cancel_btn;
	 
	    public AmountOfQuiz(Frame parent) {
	        super(parent, "#Quiz", true);

	        JPanel panel = new JPanel(new GridBagLayout());
	        GridBagConstraints cs = new GridBagConstraints();
	        panel.setBackground(Color.lightGray);
	 
	        cs.fill = GridBagConstraints.HORIZONTAL;
	 
	        lbnmQuiz = new JLabel("#Quiz(s): ");
	        cs.gridx = 0;
	        cs.gridy = 0;
	        cs.gridwidth = 1;
	        panel.add(lbnmQuiz, cs);
	 
	        nmQuiz_tx = new JTextField(20);
	        cs.gridx = 1;
	        cs.gridy = 0;
	        cs.gridwidth = 2;
	        panel.add(nmQuiz_tx, cs);
	 
	        ok_btn = new JButton("OK");
	 
	        ok_btn.addActionListener(new ActionListener() {
	 
	            public void actionPerformed(ActionEvent e) {
	                try{
	                	String s = nmQuiz_tx.getText();
	    				for(int j =0; j< s.length(); j++){
	    					char c = s.charAt(j);
	    					if(!Character.isDigit(c)){
	    						throw new NumberFormatException();
	    					}
	    				}
	    				int a = Integer.parseInt(s);
	    				gui.SetScore setScore = new gui.SetScore(a);
	    				Holder.quizNum = a+2;
	    				parent.dispose();
	    				dispose();
	                }catch(NumberFormatException n){
	                	System.out.print(n.getLocalizedMessage());
	                }
	            }
	        });
	        cancel_btn = new JButton("Cancel");
	        cancel_btn.addActionListener(new ActionListener() {
	 
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
	        JPanel bp = new JPanel();
	        bp.setBackground(Color.lightGray);
	        bp.add(ok_btn);
	        bp.add(cancel_btn);
	 
	        getContentPane().add(panel, BorderLayout.CENTER);
	        getContentPane().add(bp, BorderLayout.PAGE_END);
	 
	        pack();
	        setResizable(false);
	        setLocationRelativeTo(parent);
	    }
}

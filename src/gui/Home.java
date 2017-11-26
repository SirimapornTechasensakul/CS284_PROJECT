package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import logic.Holder;
import logic.Student;


public class Home extends JFrame implements ActionListener{
	
	private JButton grdrange_btn, setgrd_btn, dwnload_btn, fillUp_btn, expt_btn;
	private JPanel top, center1, center2, bottom1, bottom2;
	private final JFileChooser fc = new JFileChooser(); // file chooser use to choose file from opening window
	
	public Home(){
		//initialize
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		        String ObjButtons[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Do you want to save your work","Grader",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		        	
		        	String save = "save.txt";
		        	
		        	try {
						File file = new File(save); 
						  String path = file.getAbsolutePath();
						  System.out.println(path);
						  BufferedWriter out = new BufferedWriter(new OutputStreamWriter( //open writer
								    new FileOutputStream(file), "UTF-8"));
						  try {
							  int numStu = Holder.getNumStudent();
							  int numQuiz = Holder.getNumQuiz();
							  //write number of Quiz
							  out.newLine();
							  out.write(""+numQuiz+",");
							  for(int j = 0; j< numQuiz;j++){
								  out.write(""+Holder.getMaxQuiz(j) +","); //write each max quiz score
							  }
							  out.newLine();
							  // end of number of Quiz
							  
							  // grade range
							  for(int j= 0; j < 8; j++){
								  out.write(""+Holder.getGradeScore(j)+",");
							  }
							  out.newLine();
							  // end of grade range
							  
							  //ready to export
							  out.write(""+Holder.isReadyToExport());
							  out.newLine();
							  //end of ready to export
							  
							  //fill all students' score including( total score and grade ) one student at a line
							  for(int i = 0; i<numStu ;i++){
								  Student s = Holder.getStudent(i);
								  out.write(s.getId()+ "," + s.getFname()+ "," + s.getLname() + ",");
								  for(int k =0; k<numQuiz;k++){
									  out.write(""+s.getScore(k)+",");
								  }
								  out.write(""+s.getTotscr()+",");
								  out.write(s.getGrd());
								  out.newLine();
							  }
						  } finally {
							  out.close();
						  }
					}
					catch (UnsupportedEncodingException n)
					{
						System.out.println(n.getMessage());
					}
					catch (IOException n)
					{
						System.out.println(n.getMessage());
					}
					catch (Exception n)
					{
						System.out.println(n.getMessage());
					}
					  
				
		        	
		            System.exit(0);
		        }else if(PromptResult==JOptionPane.NO_OPTION){
		        	
		        	System.exit(0);
		        }
		    }
		});
		setBounds(200, 200, 600, 400);
		setTitle("Home");
		
		
		LineBorder border = new LineBorder(Color.black, 3, true);
		
		JPanel a = new JPanel();
		a.setLayout(new GridLayout(5, 0));
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Welcome");
		title.setTitleFont(Holder.font);
		title.setTitleColor(Color.white);
		title.setBorder(border);
		a.setBorder(title);
		
		top = new JPanel();
		setgrd_btn = new JButton("Set Score");
		top.add(setgrd_btn);
		top.setBackground(Color.gray);
		setgrd_btn.addActionListener(this);
		setgrd_btn.setPreferredSize(new Dimension(150, 40));
		setgrd_btn.setForeground(Color.white);
		setgrd_btn.setBackground(Color.black);
		setgrd_btn.setFocusPainted(false);
		
		
		center1 = new JPanel();
		grdrange_btn = new JButton("Grade Range");
		center1.add(grdrange_btn);
		center1.setBackground(Color.gray);
		grdrange_btn.addActionListener(this);
		grdrange_btn.setPreferredSize(new Dimension(150, 40));
		grdrange_btn.setForeground(Color.white);
		grdrange_btn.setBackground(Color.black);
		grdrange_btn.setFocusPainted(false);
		
		center2 = new JPanel();
		dwnload_btn = new JButton("ClassList Download");
		center2.add(dwnload_btn);
		center2.setBackground(Color.gray);
		dwnload_btn.addActionListener(this);
		dwnload_btn.setPreferredSize(new Dimension(150, 40));
		dwnload_btn.setForeground(Color.white);
		dwnload_btn.setBackground(Color.black);
		dwnload_btn.setFocusPainted(false);
		
		bottom1 = new JPanel();
		fillUp_btn = new JButton("Fill Up Score");
		bottom1.setBackground(Color.gray);
		bottom1.add(fillUp_btn);
		fillUp_btn.addActionListener(this);
		fillUp_btn.setPreferredSize(new Dimension(150, 40));
		fillUp_btn.setForeground(Color.white);
		fillUp_btn.setBackground(Color.black);
		fillUp_btn.setFocusPainted(false);
		
		bottom2 = new JPanel();
		expt_btn = new JButton("Export File");
		bottom2.setBackground(Color.gray);
		bottom2.add(expt_btn);
		expt_btn.addActionListener(this);
		expt_btn.setPreferredSize(new Dimension(150, 40));
		expt_btn.setForeground(Color.white);
		expt_btn.setBackground(Color.black);
		expt_btn.setFocusPainted(false);
		
		a.setBackground(Color.gray);
		a.add(top);
		a.add(center1);
		a.add(center2);
		a.add(bottom1);
		a.add(bottom2);
		
		this.add(a);
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(setgrd_btn.getActionCommand())){ //set score is clicked
			setScore();
		}
		else if(e.getActionCommand().equals(grdrange_btn.getActionCommand())){ // grade range is clicked
			setGradeRange();
		}
		else if(e.getActionCommand().equals(dwnload_btn.getActionCommand())){ //download is clicked
			dwnldCL();
		}
		else if(e.getActionCommand().equals(fillUp_btn.getActionCommand())){ //fill up score is clicked
			fillUpScr();
		}else if(e.getActionCommand().equals(expt_btn.getActionCommand())){// export button is clicked
			export();
		}
	}
	
	private void setScore(){
		AmountOfQuiz amount = new AmountOfQuiz(this);
		amount.show();
	}
	
	private void setGradeRange(){
		dispose();
		gui.GradeRange gradeRange = new gui.GradeRange();
	}
	
	private void dwnldCL(){
		 int returnVal = fc.showOpenDialog(this);
	     if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fc.getSelectedFile(); //open selected file
				
				BufferedReader in = new BufferedReader( //open reader
						new InputStreamReader(
								new FileInputStream(file), "UTF-8"));
				
				String str;
				int line = 0; // line is index to current line
				Holder.clearStudent();
				while ((str = in.readLine()) != null) {
				    if(line > 0){ //skip first line because of header from classList file
				    	String[] words=str.split("\\s");//splits the string based on whitespace  
				    	Student s = new Student(words[1],words[2],words[0]);
				    	Holder.addStudent(s);
				    }
				    line++; // go to next line
				}
				
					in.close(); //close reader
			}
			catch (UnsupportedEncodingException n)
			{
				System.out.println(n.getMessage());
			}
			catch (IOException n)
			{
				System.out.println(n.getMessage());
			}
			catch (Exception n)
			{
				System.out.println(n.getMessage());
			}

	        } else {
	        	System.out.println("Open command cancelled by user.");
	        }
		
	}
	
	private void fillUpScr(){
		if(Holder.getNumQuiz() != 0 && Holder.getNumStudent() != 0 && Holder.isSetGrade()){
			// check all properties is set 
			FillUpScore fillUpScore = new FillUpScore(); //switch to FillUpScore
			dispose();
		}else{
			JOptionPane.showMessageDialog(this, "Make sure you already fill the above menu completely.");
		}
	}
	
	private void export(){
		if(Holder.isReadyToExport()){ //check whether ready to export or not
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);
			if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String path = fc.getSelectedFile().toString();
				System.out.println(path);
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("Grade");

		        int rowCount = 0;
		        int numStudent = Holder.getNumStudent();
		        for (int i = 0; i< numStudent; i++) {
		            Row row = sheet.createRow(rowCount++);
		            Student s = Holder.getStudent(i);
		            Cell cell0 = row.createCell(0);
	                cell0.setCellValue((String) s.getId());
	                Cell cell1 = row.createCell(1);
	                cell1.setCellValue((String) s.getGrd());
		        }
		         
		         
		        try (FileOutputStream outputStream = new FileOutputStream(path+"/studentGrade.xlsx")) {
		            workbook.write(outputStream);
		            JOptionPane.showMessageDialog(this, "File exporting is done.");
		        }catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
			}
		}else{
			JOptionPane.showMessageDialog(this, "File exporting is not ready.");
		}
	}
}

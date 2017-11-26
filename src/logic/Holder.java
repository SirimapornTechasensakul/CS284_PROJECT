package logic;


import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

public class Holder {
	public static Holder holder = new Holder();
	private static ArrayList list;
	private static ArrayList gradeList;
	private static ArrayList<Student> studentList;
	private static boolean isReadyToExport = false;
	public static Font font = new Font("Courier New", Font.BOLD, 30);
	public static int quizNum;
	
	public Holder() {
		// TODO Auto-generated constructor stub
		list = new ArrayList();
		gradeList = new ArrayList();
		studentList = new ArrayList<Student>();
		quizNum = 0;
	}
	
	static{ // do once when open program
		loadSaveFile();
	}
	
	private static void loadSaveFile(){ //load saved file
		try {
			String save = "save.txt";
			File file = new File(save); //open selected file
			
			BufferedReader in = new BufferedReader( //open reader
					new InputStreamReader(
							new FileInputStream(file), "UTF-8"));
			
			String str;
			int line = 0; // line is index to current line
			Holder.clearStudent();
			int numQuiz = 0;
			while ((str = in.readLine()) != null) {
			    if(line > 0){ //skip first line because of header from classList file
			    	String[] words=str.split("[\\s,]+");//splits the string based on whitespace [       
			    	
			    	if(line == 1){
			    		numQuiz = Integer.parseInt(words[0]);
			    		for(int i = 0 ; i< numQuiz; i++){
			    			list.add(Integer.parseInt(words[i+1]));
			    		}
			    	}else if(line == 2){
			    		for(int i = 0 ; i< 8; i++){
			    			gradeList.add(Integer.parseInt(words[i]));
			    		}
			    	}else if(line == 3){
			    		if(Boolean.parseBoolean(words[0])) isReadyToExport = true;
			    	}else{
			    		Student s = new Student(words[1],words[2],words[0]);
				    	
				    	s.print();
				    	for(int i =0 ; i< numQuiz; i++){
				    		s.addScore(Double.parseDouble(words[i+3]));
				    	}
				    	Holder.addStudent(s);
			    	}
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
	}
	
	public static int add(Number e){
		if(!(e instanceof Integer)) return -1;
		else{
			int a = (int)e;
			int n = list.size();
			if(n < quizNum && a > 0){
				list.add(a);
				return 0;
			}
			else return -1;
		}
			
	}
	
	public static void remove(){
		list.clear();
	}
	
	public static int getMaxQuiz(int index){
		return (int)list.get(index);
	}
	
	public static int getNumQuiz(){
		return list.size();
	}
	
	public static int addGrade(Number e){
		if(!(e instanceof Integer)) return -1;
		else{
			int a = (int)e;
			if(a > 100 || a < 0 ) return -1;
			int n = gradeList.size();
			if(n == 0 || (int)gradeList.get(n-1) > a){
				gradeList.add(a);
				System.out.println(a);
				return 0;
			}
			else return -1;
		}
	}
	
	public static void clearGradeScore(){
		gradeList.clear();
	}
	
	public static int getGradeScore(int index){
		if(index > -1 && index < gradeList.size())return (int)gradeList.get(index);
		return -1;
	}
	
	public static boolean isSetGrade(){
		if(gradeList.isEmpty())return false;
		return true;
	}

	public static void addStudent(Student s){
		studentList.add(s);
	}
	
	public static Student getStudent(int index){
		return studentList.get(index);
	}
	
	public static int getNumStudent(){
		return studentList.size();
	}
	
	public static void clearStudent(){
		studentList.clear();
	}
	public static void setReadyToExport(){
		isReadyToExport = true;
	}
	
	public static void setNotReadyToExport(){
		isReadyToExport = false;
	}
	
	public static boolean isReadyToExport(){
		return isReadyToExport;
	}
	
}

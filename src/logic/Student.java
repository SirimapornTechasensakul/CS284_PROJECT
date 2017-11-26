package logic;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String fname, lname, id;
	private List scrs;
	private String grd;
	private double totscr;
	
	public Student(String fname, String lname, String id) {
		// TODO Auto-generated constructor stub
		this.fname = fname;
		this.lname = lname;
		this.id = id;
		scrs = new ArrayList();
		grd = "F";
		totscr = 0;
	}
	
	public String getFname(){
		return this.fname;
	}
	
	public String getLname(){
		return this.lname;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getGrd(){
		return this.grd;
	}
	
	public double getTotscr(){
		return this.totscr;
	}
	
	public int addScore(double scr){
		int index = scrs.size();
		if(scrs.size() < (logic.Holder.getNumQuiz()) && scr <= (double)Holder.getMaxQuiz(index) && scr >= 0){
			scrs.add(scr);
			return 0;
		}
		return -1;
	}
	
	public double getScore(int index){
		if(index >= scrs.size()) return 0;
		return (double)scrs.get(index);
	}
	
	public void setScore(int index, double scr){
		if(index > -1 && index < scrs.size()){
			scrs.set(index, scr);
		}
	}
	
	public void calGrd(){
		double sum = 0;
		for(int i =0; i< scrs.size(); i++){
			sum += (double)(scrs.get(i));
		}
		if(sum >= logic.Holder.getGradeScore(0)) grd = "A";
		else if(sum >= logic.Holder.getGradeScore(1)) grd = "B+";
		else if(sum >= logic.Holder.getGradeScore(2)) grd = "B";
		else if(sum >= logic.Holder.getGradeScore(3)) grd = "C+";
		else if(sum >= logic.Holder.getGradeScore(4)) grd = "C";
		else if(sum >= logic.Holder.getGradeScore(5)) grd = "D+";
		else if(sum >= logic.Holder.getGradeScore(6)) grd = "D";
		else if(sum >= logic.Holder.getGradeScore(7)) grd = "F";
		totscr = sum;
	}
	
	public void clearScore(){
		scrs.clear();
		totscr = 0;
		grd = "F";
	}
	
	public void print(){
		System.out.println(fname +" " +lname + " " + id);
	}
	
	public int getNumScore(){
		return scrs.size();
	}
}

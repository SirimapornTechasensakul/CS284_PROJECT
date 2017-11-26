package logic;

public class OutOfRangeException extends Exception {
	public OutOfRangeException(){
		super("The range must relate to following sequence\n 100>=A>B+>B>C+>C>D+>D>F>=0.");
	}
	
	public OutOfRangeException(int n){
		super("The score must be greater than 0 and lower than max score.");
	}
}

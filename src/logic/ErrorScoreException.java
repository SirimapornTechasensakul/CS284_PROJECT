package logic;

public class ErrorScoreException extends Exception {
	public ErrorScoreException(int scr){
		super("Total score is " + scr +".\nIt must be 100");
	}
}

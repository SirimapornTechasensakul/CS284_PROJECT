package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddScoreTest {

	@Before
	public void setUp() throws Exception {
	
	}

	@Test //test score < 0
	public void test1() {
		Holder.quizNum = 5;
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		
		Student s = new Student("John","Saw","5831042621");
		assertEquals(-1, s.addScore(-1) );
		assertEquals(-1, s.addScore(-100) );
		assertEquals(-1, s.addScore(-1.1) );
		assertEquals(-1, s.addScore(-0.1) );
		assertEquals(-1, s.addScore(-0.5) );
		Holder.remove();
	}
	
	@Test //test score > max
	public void test2() {
		Holder.quizNum = 5;
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Student s = new Student("John","Saw","5831042621");
		assertEquals(-1, s.addScore(22) );
		assertEquals(-1, s.addScore(23) );
		assertEquals(-1, s.addScore(100) );
		assertEquals(-1, s.addScore(50) );
		assertEquals(-1, s.addScore(26) );
		Holder.remove();
	}
	
	@Test //test 0 <= score <= max
	public void test3() {
		Holder.quizNum = 5;
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Student s = new Student("John","Saw","5831042621");
		assertEquals(0, s.addScore(0) );
		assertEquals(0, s.addScore(12) );
		assertEquals(0, s.addScore(0.5) );
		assertEquals(0, s.addScore(20) );
		assertEquals(0, s.addScore(19.9) );
		Holder.remove();
	}
	
	@Test // add score more than exist quiz
	public void test4() {
		Holder.quizNum = 5;
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Holder.add(20);
		Student s = new Student("John","Saw","5831042621");
		assertEquals(0, s.addScore(0) );
		assertEquals(0, s.addScore(12) );
		assertEquals(0, s.addScore(0.5) );
		assertEquals(0, s.addScore(20) );
		assertEquals(0, s.addScore(19.9) );
		assertEquals(-1, s.addScore(19.1) );
		assertEquals(-1, s.addScore(20) );
		assertEquals(-1, s.addScore(0.0) );
		assertEquals(-1, s.addScore(9.5) );
		Holder.remove();
	}
	
	

}

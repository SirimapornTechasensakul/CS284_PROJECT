package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SetScoreTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test //Test add quiz score with non integer. and non positive integer 
	public void test1() {
		Holder.quizNum = 4;
		assertEquals(-1, Holder.add(-20));
		assertEquals(-1, Holder.add(1.5));
		assertEquals(-1, Holder.add(-2.5));
		assertEquals(-1, Holder.add(50.5));
		Holder.remove();

	}
	
	@Test //Test add quiz score with positive integer.
	public void test2() {
		Holder.quizNum = 4;
		assertEquals(0, Holder.add(20));
		assertEquals(0, Holder.add(25));
		assertEquals(0, Holder.add(30));
		assertEquals(0, Holder.add(50));
		Holder.remove();

	}
	
	@Test //Test add quiz score more than number of quiz.
	public void test3() {
		Holder.quizNum = 4;
		assertEquals(0, Holder.add(20));
		assertEquals(0, Holder.add(25));
		assertEquals(0, Holder.add(30));
		assertEquals(0, Holder.add(50));
		assertEquals(-1, Holder.add(25));
		assertEquals(-1, Holder.add(30));
		assertEquals(-1, Holder.add(10));
		
		Holder.remove();

	}
	
	@Test //Test add quiz score in range number of quiz.
	public void test4() {
		Holder.quizNum = 4;
		assertEquals(0, Holder.add(20));
		assertEquals(0, Holder.add(25));
		assertEquals(0, Holder.add(30));
		assertEquals(0, Holder.add(50));
		
		Holder.remove();

	}

}

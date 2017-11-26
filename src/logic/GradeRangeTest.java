/**
 * 
 */
package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 */
public class GradeRangeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		logic.Holder.clearGradeScore();
	}

	@Test
	public void test1() { 
		//test add number out of range 0-100
		assertEquals(-1, logic.Holder.addGrade(-1));
		assertEquals(-1, logic.Holder.addGrade(-100));
		assertEquals(-1, logic.Holder.addGrade(101));
		assertEquals(-1, logic.Holder.addGrade(150));
	}
	
	@Test
	public void test2() {
		//test add number in range 0-100 and the numbers are sorted to decreasing order

		assertEquals(0, logic.Holder.addGrade(100));
		assertEquals(0, logic.Holder.addGrade(90));
		assertEquals(0, logic.Holder.addGrade(80));
		assertEquals(0, logic.Holder.addGrade(70));
		logic.Holder.clearGradeScore();
	}
	
	@Test
	public void test3() {
		//test add number in increasing order 
		assertEquals(0, logic.Holder.addGrade(0));
		assertEquals(-1, logic.Holder.addGrade(10));
		assertEquals(-1, logic.Holder.addGrade(20));
		assertEquals(-1, logic.Holder.addGrade(30));
		logic.Holder.clearGradeScore();
	}
	
	@Test
	public void test4() {
		//test add number in non-order
		assertEquals(0, logic.Holder.addGrade(50));
		assertEquals(-1, logic.Holder.addGrade(60));
		assertEquals(0, logic.Holder.addGrade(40));
		assertEquals(0, logic.Holder.addGrade(30));
		logic.Holder.clearGradeScore();
	}
	
	@Test
	public void test5() {
		//test add floating point number
		assertEquals(-1, logic.Holder.addGrade(55.5));
		assertEquals(-1, logic.Holder.addGrade(50.5));
		assertEquals(-1, logic.Holder.addGrade(40.33));
		assertEquals(-1, logic.Holder.addGrade(-40.5));
		logic.Holder.clearGradeScore();
	}

}

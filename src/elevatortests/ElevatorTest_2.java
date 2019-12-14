package elevatortests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elevator.Elevator;

/**
 * Some basic test cases
 * 
 * @author thuan pham
 *
 */
public class ElevatorTest_2 {
	
	private Elevator myElevator;

	@Before
	public void setUp() throws Exception {
		myElevator = new Elevator();
	}

	/**
	 * Test moving from floor 2, pickup on floor 3 but not 
	 * the passenger pushing down on floor 
	 */
	@Test
	public void testInvalidPushNotMove() {
		boolean valid = myElevator.pushDown(2);
		int floor = myElevator.move();
		valid = myElevator.pushIn(2);
		assertFalse("should not be able to pushin 2 when on floor 2", valid);
		valid = myElevator.pushIn(3);
		assertFalse("should not be able to pushin 3 when on floor 2 and pushed down", valid);
		floor = myElevator.move();
		assertEquals("should be on floor 2", 2, floor);
		myElevator.pushIn(1);
	}
	
	/**
	 * Test push in button to same floor
	 */
	@Test
	public void testSameFloor() {
		boolean valid = myElevator.pushDown(2);
		int floor = myElevator.move();
		valid = myElevator.pushIn(2);
		assertFalse("should not be able to pushin 2 when on floor 2", valid);
	}

	/**
	 * Test push in when move not called
	 */
	@Test
	public void testNoMove() {
		boolean valid = myElevator.pushDown(2);
		valid = myElevator.pushIn(1);
		assertFalse("cannot pushin since move not called and elevator not on floor 2", valid);
	}
	
	/**
	 * Test goto floor 3 first
	 */
	@Test
	public void testFloor3First() {
		boolean valid = myElevator.pushUp(3);
		valid = valid && myElevator.pushDown(5);
		valid = valid && myElevator.pushDown(4);
		valid = valid && myElevator.pushDown(3);
		valid = valid && myElevator.pushDown(2);
		assertTrue("All pushes should be valid", valid);
		assertEquals("Should move to 3rd floor", 3, myElevator.move());
	}
	
}

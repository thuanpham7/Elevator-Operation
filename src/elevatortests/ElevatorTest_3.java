package elevatortests;

import elevator.Elevator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Some sample Elevator class test cases!
 * 
 * @author thuan pham
 *
 */

public class ElevatorTest_3 
{
	private Elevator myElevator;
	 
	/**
	 * An elevator with 5 floors will be set up new before running 
	 * each test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		myElevator = new Elevator(5);
	}

	/**
	 * Test when picking up when elevator moves up from first floor
	 * but "up" buttons pushed out of order
	 */
	@Test
	public void testUpOutOfOrder() 
	{
		assertTrue(myElevator.pushUp(1));
		assertTrue(myElevator.pushUp(4));
		assertTrue(myElevator.pushDown(4));
		assertTrue(myElevator.pushUp(3));
		assertEquals("Move should place elevator on first floor", myElevator.move(), 1);
		assertTrue(myElevator.pushIn(5));
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertEquals("Move should place elevator on fourth floor", myElevator.move(), 4);
		assertEquals("Move should place elevator on fifth floor", myElevator.move(), 5);
		assertEquals("Move should keep elevator on fifth floor", myElevator.move(), 4);	
		assertEquals("Move should keep elevator on fifth floor", myElevator.move(), 4);	
	}

	/**
	 * Test when picking up when elevator moves down then up from second floor
	 * but down buttons are preferred floors first
	 */
	@Test
	public void testUpDownOutOfOrder() 
	{
		assertTrue(myElevator.pushDown(2));
		assertEquals("Move should place elevator on second floor", myElevator.move(), 2);
		assertTrue(myElevator.pushIn(1));
		assertTrue(myElevator.pushDown(3));
		assertTrue(myElevator.pushUp(4));
		assertTrue(myElevator.pushUp(3));
		assertEquals("Move should place elevator on first floor", myElevator.move(), 1);
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertTrue(myElevator.pushIn(2));
		assertEquals("Move should place elevator on second floor", myElevator.move(), 2);
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertTrue(myElevator.pushIn(4));
		assertEquals("Move should place elevator on fourth floor", myElevator.move(), 4);
		assertTrue(myElevator.pushIn(5));
		assertEquals("Move should place elevator on fifth floor", myElevator.move(), 5);
		assertEquals("Move should keep elevator on fifth floor", myElevator.move(), 5);		
	}

	/**
	 * Test buttons that should not move elevator
	 */
	@Test
	public void testNotMoving() 
	{
		assertTrue(myElevator.pushDown(2));
		assertEquals("Move should place elevator on second floor", myElevator.move(), 2);
		assertFalse("Elevator should already be on second floor", myElevator.pushIn(2));
		assertFalse("Elevator should only go down from here", myElevator.pushIn(3));
		assertEquals("Move should place elevator on second floor", myElevator.move(), 2);
		assertTrue(myElevator.pushIn(1));
		assertEquals("Move should place elevator on first floor", myElevator.move(), 1);
		assertTrue(myElevator.pushDown(3));
		assertEquals("Should move to the third floor", myElevator.move(),3);
		assertFalse("Already pushed down button on third floor", myElevator.pushDown(3));
		assertTrue(myElevator.pushUp(3));
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertEquals("Direction should be set to UP", myElevator.getDirection(), Elevator.UP);
		assertFalse("Already on third floor", myElevator.pushIn(3));
		assertEquals("Move should keep elevator on third floor", myElevator.move(), 3);
		assertTrue("Should be able to push button for upper floor", myElevator.pushIn(5));
		assertEquals("Move should place elevator on fifth floor", myElevator.move(), 5);
	}
	
	/**
	 * Test direction
	 */
	@Test
	public void testDirection() 
	{
		assertEquals("Direction should not be set yet", myElevator.getDirection(), Elevator.NOT_SET);
		assertTrue(myElevator.pushUp(3));
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertEquals("Direction should be set to UP", myElevator.getDirection(), Elevator.UP);
		assertTrue(myElevator.pushIn(5));
		assertTrue(myElevator.pushDown(4));
		assertEquals("Move should place elevator on fifth floor", myElevator.move(), 5);
		//assertEquals("Direction should be set to DOWN", myElevator.getDirection(), Elevator.DOWN);
		assertEquals("Move should place elevator on fourth floor", myElevator.move(), 4);
		assertTrue(myElevator.pushIn(3));
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertEquals("Direction should not be set yet", myElevator.getDirection(), Elevator.NOT_SET);
		assertEquals("Move should keep elevator on third floor", myElevator.move(), 3);
	}
	
	/**
	 * Test when picking up when elevator moves up from first floor
	 * but "up" buttons pushed out of order
	 */
	@Test
	public void testUp() 
	{
		assertTrue(myElevator.pushUp(1));
		assertTrue(myElevator.pushUp(3));
		assertEquals("Move should place elevator on first floor", myElevator.move(), 1);
		assertTrue(myElevator.pushIn(2));
		assertTrue(myElevator.pushIn(5));
		assertEquals("Move should place elevator on second floor", myElevator.move(), 2);
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertTrue(myElevator.pushIn(4));
		assertEquals("Move should place elevator on fourth floor", myElevator.move(), 4);
		assertEquals("Move should place elevator on fifth floor", myElevator.move(), 5);
	}

	/**
	 * Test when picking up when elevator moves down then up from second floor
	 * but down buttons are preferred floors first
	 */
	@Test
	public void testUpDownRepeat() 
	{
		assertTrue(myElevator.pushUp(2));
		assertEquals("Move should place elevator on second floor", myElevator.move(), 2);
		assertTrue(myElevator.pushDown(4));
		assertEquals("Move should place elevator on forth floor", myElevator.move(), 4);
		assertTrue(myElevator.pushUp(2));
		assertEquals("Move should place elevator on second floor", myElevator.move(), 2);
		assertTrue(myElevator.pushDown(4));
		assertEquals("Move should place elevator on forth floor", myElevator.move(), 4);
	}

	/**
	 * Test elevator stays at floor if nothing else pushed.
	 */
	@Test
	public void testStay() 
	{
		assertTrue(myElevator.pushUp(3));
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
	}

	/**
	 * Test elevator moves down
	 */
	@Test
	public void testDown() 
	{
		assertTrue(myElevator.pushDown(4));
		assertEquals("Move should place elevator on forth floor", myElevator.move(), 4);
		assertTrue(myElevator.pushIn(1));
		assertTrue(myElevator.pushIn(2));
		assertTrue(myElevator.pushIn(3));
		assertEquals("Move should place elevator on third floor", myElevator.move(), 3);
		assertEquals("Move should place elevator on second floor", myElevator.move(), 2);
		assertEquals("Move should place elevator on first floor", myElevator.move(), 1);
	}

	/**
	 * Test buttons that should not move elevator
	 */
	@Test
	public void testBadFloors() 
	{
		assertFalse("Only 5 floors", myElevator.pushUp(6));
		assertFalse("First floor should be 1", myElevator.pushUp(0));
		assertFalse("Only 5 floors", myElevator.pushDown(6));
		assertFalse("First floor should be 1", myElevator.pushDown(0));
		assertFalse("No up button on fifth floor", myElevator.pushUp(5));
		assertFalse("No down button on first floor", myElevator.pushDown(1));
	}
	
}

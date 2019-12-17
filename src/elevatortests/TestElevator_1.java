package elevatortests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import elevator.Elevator;
import elevator.ElevatorOperation;



public class TestElevator_1 {
	private Elevator myElevator;

	@Before
	public void setUp() throws Exception {
		myElevator = new Elevator();
	}

	/*
	 * Tests to make sure it will pickup people that are only on there way down
	 */
	
	@Test
	public void testOnlyDown ()
	{
		assertTrue("Could not find floor 3" , myElevator.pushDown(5));
		assertTrue("Could not find floor 5" , myElevator.pushDown(3));
		assertTrue("Could not find floor 4" , myElevator.pushDown(2));
		assertEquals("Error moving to 5th floor" , myElevator.move(), 5);
		assertTrue("Invalid Floor to push in", myElevator.pushIn(4));
		assertEquals("Error moving to 4th floor" , myElevator.move(), 4);
		assertEquals("Error moving to 3rd floor" , myElevator.move(), 3);
		assertTrue("Invalid Floor to push in", myElevator.pushIn(1));
		assertEquals("Cannot move to the 2nd floor" , myElevator.move(), 2);
		assertEquals("Cannot move to the 1st floor" , myElevator.move(), 1);
	}
	
	/*
	 * Tests to make sure it will pickup people only on there way up
	 */
	
	@Test
	public void testOnlyUp()
	{
		assertTrue("Could not find floor 3" , myElevator.pushUp(1));
		assertTrue("Could not find floor 5" , myElevator.pushUp(3));
		assertTrue("Could not find floor 4" , myElevator.pushUp(4));
		assertEquals("Error moving to 1st floor" , myElevator.move(), 1);
		assertTrue("Invalid Floor to push in", myElevator.pushIn(4));
		assertEquals("Error moving to 3rd floor" , myElevator.move(), 3);
		assertEquals("Error moving to 4th floor" , myElevator.move(), 4);
		assertTrue("Invalid Floor to push in", myElevator.pushIn(5));
		assertEquals("Cannot move to the 5th floor" , myElevator.move(), 5);
	}
	
	/*
	 * Tests to make sure it will pickup people in the correct order
	 */
	
	@Test
	public void testAnotherDay()
	{
		assertTrue("Could not find floor 2" , myElevator.pushDown(2));
		assertTrue("Could not find floor 3" , myElevator.pushUp(3));
		assertTrue("Could not find floor 4" , myElevator.pushDown(4));
		assertTrue("Could not find floor 4" , myElevator.pushUp(4));
		assertEquals("Error moving to 2nd floor" , myElevator.move(), 2);
		assertTrue("Invalid Floor to push in", myElevator.pushIn(1));
		assertEquals("Error moving to 1st floor" , myElevator.move(), 1);
		assertEquals("Error moving to 3rd floor" , myElevator.move(), 3);
		assertTrue("Invalid Floor to push in", myElevator.pushIn(5));
		assertEquals("Error moving to 4th floor" , myElevator.move(), 4);
		assertEquals("Error moving to 5th floor" , myElevator.move(), 5);
		assertEquals("Error moving to 4th floor" , myElevator.move(), 4);
		assertTrue("Invalid Floor to push in", myElevator.pushIn(3));
		assertEquals("Error moving to 3rd floor" , myElevator.move(), 3);
	}
	
	@Test
	public void commonFalseCases() 
	{
		boolean valid = myElevator.pushDown(1);
		assertFalse("Cannot go down from lowest floor", valid);
		valid = myElevator.pushUp(5);
		assertFalse("Cannot go up from highest floor", valid);
		valid = myElevator.pushUp(1);
		valid = myElevator.pushDown(5);
		int myMovement = myElevator.move();
		assertEquals("Should move to floor 1, first valid press up or down occurred there", 1, myMovement);
		valid = myElevator.pushIn(1);
		assertFalse("Already on floor 1", valid);
		myMovement = myElevator.move();
		valid = myElevator.pushIn(5);
		assertFalse("Already on floor 5", valid);
		myMovement = myElevator.move();
		assertEquals("No buttons pushed in yet, should remain on 5", 5, myMovement);
	}
	
	/*
	 * Test 1:
	 * Someone on floor 1 pushes up, floor 3 pushes down, and floor 4 pushes up
	 * The person on floor 1 pushes in 4, the person on floor 3 pushes in 2, and 
	 * floor 4 pushes in 5.
	 */
	@Test
	public void test1() {
		boolean valid = myElevator.pushUp(1);
		valid = myElevator.pushDown(3);
		valid = myElevator.pushUp(4);
		int floor = myElevator.move();
		assertTrue("Elevator should move to floor 1", valid);
		assertEquals("Current floor should be 1", 1, floor);
		valid = myElevator.pushIn(4);
		floor = myElevator.move();
		assertTrue("Elevator should move to floor 4", valid);
		assertEquals("Current floor should be 4", 4, floor);
		valid = myElevator.pushIn(5);
		floor = myElevator.move();
		assertTrue("Elevator should move to floor 5", valid);
		assertEquals("Current floor should be 5", 5, floor);
		floor = myElevator.move();
		assertTrue("Elevator should move to floor 3", valid);
		assertEquals("Current floor should be 3", 3, floor);
		valid = myElevator.pushIn(2);
		floor = myElevator.move();
		assertTrue("Elevator should move to floor 2", valid);
		assertEquals("Current floor should be 2", 2, floor);
	}
		
	/*
	 * Floor 3 pushes down, then floor 5 pushes down
	 * It picks up floor 3, takes them to floor 2
	 * It then goes to floor 5, and no button is pushed
	 * The move function activates on floor 5 a second time
	 */
	@Test
	public void test5() {
		boolean valid = myElevator.pushDown(3);
		valid = myElevator.pushDown(5);
		int floor = myElevator.move();
		assertTrue("Elevator should move to floor 3", valid);
		assertEquals("Current floor should be 3", 3, floor);
		valid = myElevator.pushIn(2);
		floor = myElevator.move();
		assertTrue("Elevator should move to floor 2", valid);
		assertEquals("Current floor should be 2", 2, floor);
		floor = myElevator.move();
		assertTrue("Elevator should move to floor 5", valid);
		assertEquals("Current floor should be 5", 5, floor);
		floor = myElevator.move();
		assertTrue("Elevator should move to floor 5", valid);
		assertEquals("Current floor should be 5", 5, floor);
	}
		
	/*
	 * Floor 5 is pushed down, it moves to floor 5
	 * No button is pushes
	 * The move function activates again
	 */
	@Test
	public void test7() {
		boolean valid = myElevator.pushDown(5);
		int floor = myElevator.move();
		assertTrue("Elevator should move to floor 5", valid);
		assertEquals("Current floor should be 5", 5, floor);
		floor = myElevator.move();
		assertTrue("Elevator should move to floor 5", valid);
		assertEquals("Current floor should be 5", 5, floor);
	}
	
	//Asserts that the elevator shouldn't move with an empty queue
	private void assertEmptyQueue()
	{
		assertEquals("Nowhere to move", myElevator.getFloor(), myElevator.move());
	}
	
	//Asserts that the button for the current floor is pushed
	private void assertSameFloor(int floor)
	{
		assertFalse("Already on floor", myElevator.pushIn(floor));
	}
	
	//Moves and asserts that the floor should be the one entered
	private void assertFloorMoved(int floor)
	{
		assertEquals("Should be on " + floor, floor, myElevator.move());
	}
	
	/*Tests whether the elevator will go above the fifth floor
	 * or move when fed the same floor it's on
	 */
	@Test
	public void testEmptyQueue() {
		assertFalse("Cannot go above floor 5", myElevator.pushUp(5));
		myElevator.pushDown(5);
		myElevator.move();
		assertFalse("Already on floor", myElevator.pushIn(5));
		assertEmptyQueue();
		myElevator.pushIn(1);
		myElevator.move();
		assertSameFloor(1);
		assertEmptyQueue();
	}
				
	/*Tests whether the elevator will go to the first floor pushed when it's entered first
	 * and later pushes would conflict,
	 * and tests whether the elevator will go below floor 1
	 */
	@Test
	public void testPushConflict() {
		myElevator.pushUp(3);
		myElevator.pushDown(2);
		assertFloorMoved(3);
		myElevator.pushIn(4);
		assertFloorMoved(4);
		assertFloorMoved(2);
		myElevator.pushIn(1);
		assertFloorMoved(1);
		assertFalse("Cannot go above floor 1", myElevator.pushDown(1));
	}
	
	@Test
	public void testPt3() {
		/*
		 * push up 2
		 * push up 4
		 * push down 4
		 * move 2
		 * push in 5
		 * move 4
		 * push in 5
		 * move 5
		 * move 4
		 * push in 1
		 * move 1
		 */
		boolean validBool;
		int validInt;
		
		myElevator.pushUp(2);
		validBool = myElevator.pushUp(4);
		assertTrue("Should be able to go up from 4", validBool);
		myElevator.pushDown(4);
		validInt = myElevator.move(); // < 2
		assertEquals("Should move towards 2", 2, validInt);
		myElevator.pushIn(5);
		validInt = myElevator.move(); // < 4
		assertEquals("Should move towards 4", 4, validInt);	
		myElevator.pushIn(5);
		validInt = myElevator.move(); // < 5
		assertEquals("Should move towards 5", 5, validInt);	
		validInt = myElevator.move(); // < 4
		assertEquals("Should move towards 4", 4, validInt);	
		myElevator.pushIn(1);
		validInt = myElevator.move(); // < 1
		assertEquals("Should move towards 1", 1, validInt);
	}
	
	@Test
	public void testPt4() {
		/*
		 * push down 5
		 * push down 5
		 * push up 3
		 * push up 1
		 * move 5
		 * push in 2
		 * push in 3
		 * move 3
		 * move 2
		 * move 3
		 * push in 4
		 * move 4
		 * move 1
		 * push in 5
		 * move 5
		 */
		boolean validBool;
		int validInt;
		
		myElevator.pushDown(5);
		myElevator.pushIn(2);
		myElevator.move();
		validBool = myElevator.pushDown(5); // false
		assertFalse("Can't push the same level they are on", validBool);
		myElevator.pushUp(3);
		myElevator.pushUp(1); 
		validBool = myElevator.pushIn(3);
		assertTrue("Should be able to push second button that also goes same direction", validBool);
		validInt = myElevator.move(); // < 3
		assertEquals("Should move towards 3", 3, validInt);
		validInt = myElevator.move(); // < 2
		assertEquals("Should move towards 2", 2, validInt);
		validInt = myElevator.move(); // < 3
		assertEquals("Should move towards 3", 3, validInt);
		myElevator.pushIn(4); 
		validInt = myElevator.move(); // < 4
		assertEquals("Should move towards 4", 4, validInt);
		validInt = myElevator.move(); // < 1
		assertEquals("Should move towards 1", 1, validInt);
		myElevator.pushIn(5);
		validInt = myElevator.move(); // < 5
		assertEquals("Should move towards 5", 5, validInt);	
	}
		
	@Test
	public void testPt6() {
		/*
		 * push up 2
		 * push down 4
		 * push down 4
		 * push down 4
		 * move 2
		 * push in 5
		 * move 5
		 * move 4
		 * push in 3
		 * push in 5 <<< false
		 * push in 1
		 * push in 2
		 * move 3
		 * move 2
		 * move 1
		 */
		boolean validBool;
		int validInt;
		
		myElevator.pushUp(2);
		myElevator.pushDown(4);
		myElevator.pushDown(4);
		myElevator.pushDown(4);
		validInt = myElevator.move(); // < 2
		assertEquals("Should move towards 2", 2, validInt);
		myElevator.pushIn(5);
		validInt = myElevator.move(); // < 5
		assertEquals("Should move towards 5", 5, validInt);	
		validInt = myElevator.move(); // < 4
		assertEquals("Should move towards 4", 4, validInt);
		myElevator.pushIn(3);
		validBool = myElevator.pushIn(5); // << false
		assertFalse("Shouldn't e able to push for upper floor going down", validBool);
		myElevator.pushIn(1);
		myElevator.pushIn(2);
		validInt = myElevator.move(); // < 3
		assertEquals("Should move towards 3", 3, validInt);
		validInt = myElevator.move(); // < 2
		assertEquals("Should move towards 2", 2, validInt);
		validInt = myElevator.move(); // < 1
		assertEquals("Should move towards 1", 1, validInt);
		
	}
	
	@Test
	public void testPt7() {
		/*
		 * push down 5
		 * push up 3
		 * push down 4
		 * move 5
		 * push in 4
		 * move 4
		 * push in 2
		 * move 2
		 * move 3
		 * push in 4
		 * push up 1
		 * move 4
		 * move 1
		 * push in 5
		 * move 5
		 */
		boolean validBool;
		int validInt;
		
		myElevator.pushDown(5);
		myElevator.pushUp(3);
		myElevator.pushDown(4);
		validInt = myElevator.move(); // < 5
		assertEquals("Should move towards 5", 5, validInt);	
		myElevator.pushIn(4);
		validInt = myElevator.move(); // < 4
		assertEquals("Should move towards 4", 4, validInt);
		myElevator.pushIn(2);
		validInt = myElevator.move(); // < 2
		assertEquals("Should move towards 2", 2, validInt);
		validInt = myElevator.move(); // < 3
		assertEquals("Should move towards 3", 3, validInt);
		myElevator.pushIn(4); 
		validBool = myElevator.pushUp(1);
		assertTrue("Should be able to have another push up", validBool);
		validInt = myElevator.move(); // < 4
		assertEquals("Should move towards 4", 4, validInt);
		validInt = myElevator.move(); // < 1
		assertEquals("Should move towards 1", 1, validInt);
		myElevator.pushIn(5);
		validInt = myElevator.move(); // < 5
		assertEquals("Should move towards 5", 5, validInt);	
	}	
	
	
}

package elevator;

/**
 * Elevator class to simulate the operation of an elevator.  Details
 * associated with opening and closing of the elevator doors, entry and
 * exit of people to and from the elevator, the number of people in, 
 * entering or leaving the elevator, and the timing of the movement of
 * the elevator are all "abstracted" out of the problem, encompassing 
 * all of these actions into a single "move()" operation.
 * 
 * @author Daniel Plante
 *
 */
public class Elevator implements ElevatorOperation
{
	public static final int NO_VALUE = -1;
	private int myNumberOfFloors;
	// Set my Direction is when it's pushed
	private int myDirection;
	// mynexdirection would be equal to the one that you are going up or down
	private int myNextDirection;
	// the order of the button - mySequence Number // Comment in class
	private int mySequenceNumber;
	private int myPresentFloor;
	
	private int[] myUpButtonOuter;
	private int[] myDownButtonOuter;
	private boolean[] myInnerButtons;
	
	/**
	 * Default constructor setting the number of floors for
	 * the elevator to five (5)
	 */
	public Elevator()
	{
		this(5);
	}
	
	/**
	 * Constructor setting the number of floors
	 * 
	 * @param numFloors total number of floors
	 */
	public Elevator(int numFloors)
	{
		this.myNumberOfFloors = numFloors;
		myUpButtonOuter = new int[10];
		myDownButtonOuter = new int[10];
		myInnerButtons = new boolean[10];
	}
	

	/**
	 * Try to push the "up" button on a floor, and fail gracefully if
	 * not a valid choice or already pushed.
	 * 
	 * @param floor the floor the up button is pushed on
	 * 
	 * @return true if the button was pushed, false if not
	 */
	public boolean pushUp(int floor) {
		if ((floor > myNumberOfFloors) || (floor < myNumberOfFloors)) {
			return false;
		}else if (floor == 5) {
			return false;
		}
		return true;
	}
	
	/**
	 * Try to push the "down" button on a floor, and fail gracefully if
	 * not a valid choice or already pushed.
	 * 
	 * @param floor the floor the down button is pushed on
	 * 
	 * @return true if the button was pushed, false if not
	 * false if you are at 5th floor - comment in class
	 * check if it is a valid floor - comment in class
	 */
	public boolean pushDown(int floor)
	{
		if ((floor > myNumberOfFloors) || (floor < myNumberOfFloors)) {
			return false;
		}else if (floor == 1) {
			return false;
		}
		return true;
	}
	
	/**
	 * If a valid thing to do, push a button inside the elevator
	 */
	public boolean pushIn(int floor)
	{
		return true;
	}
	
	/**
	 * Try to actually move the elevator based on all present settings.  
	 * 
	 * @return the floor the elevator is on after complete
	 */
	public int move()
	{		
		return 0;
	}
	
	/**
	 * Accessor returning the current floor
	 * When move, checking if anybody's there in each floor then stop to pick up that person then check again what 
	 * floor that person want to go to. It's called sequence - comment
	 * @return floor presently on
	 */
	public int getFloor()
	{
		return myPresentFloor;
	}
	
	/**
	 * Accessor returning the current direction
	 * 
	 * @return direction presently set
	 */
	public int getDirection()
	{
		return myDirection;
	}
}
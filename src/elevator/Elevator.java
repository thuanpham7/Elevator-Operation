package elevator;

/**
 * Elevator class to simulate the operation of an elevator.  Details
 * associated with opening and closing of the elevator doors, entry and
 * exit of people to and from the elevator, the number of people in, 
 * entering or leaving the elevator, and the timing of the movement of
 * the elevator are all "abstracted" out of the problem, encompassing 
 * all of these actions into a single "move()" operation.
 * 
 * @author Thuan Pham
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
	private int mySequenceNumber = 0;
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
		myPresentFloor = 1;
		myDirection = NOT_SET;
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
		if ((floor > myNumberOfFloors) || (floor < 1)) {
			return false;
		} if (floor == 5) {
			return false;
		} 
		myUpButtonOuter[mySequenceNumber] = floor;
		mySequenceNumber += 1;
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
	public boolean pushDown(int floor) {
		if ((floor > myNumberOfFloors) || (floor < 1)) {
			return false;
		} if (floor == 1) {
			return false;
		}
		if (myPresentFloor == floor) {
			return false;
		}
		myDownButtonOuter[mySequenceNumber] = floor;
		mySequenceNumber += 1;
		return true;
	}

	/**
	 * If a valid thing to do, push a button inside the elevator
	 */
	public boolean pushIn(int floor)
	{

		if (myPresentFloor == floor) {
			myInnerButtons[floor] = false;
			mySequenceNumber += 1;
			return false;
		} 
		if ((myDirection == DOWN) && (myPresentFloor < floor)) {
			return false;
		} else if ((myDirection == UP) && (myPresentFloor > floor)) {
			return false;
		} else {
			myInnerButtons[floor] = true;

		}
		return true;
	}

	/**
	 * Try to actually move the elevator based on all present settings.  
	 * 
	 * @return the floor the elevator is on after complete
	 */
	public int move()
	{		
		int tempFloor = myPresentFloor;
		if ((myUpButtonOuter[0] != 0) && (myDirection == NOT_SET)) {
			myDirection = UP;
			myPresentFloor = myUpButtonOuter[0];
			return myPresentFloor;
		} else if ((myDownButtonOuter[0] != 0) && (myDirection == NOT_SET)) {
			myDirection = DOWN;
			myPresentFloor = myDownButtonOuter[0];
			return myPresentFloor;
		} 
		
		if (myDirection == DOWN) {
			if (myPresentFloor == 1) {
				for (int i = 2; i < 6; i++) {
					for (int j = 1; j < myUpButtonOuter.length; j++) {
						if (i == myUpButtonOuter[j]) {
							myPresentFloor = i;
							myDirection = UP;
							myUpButtonOuter[j] = 0;
							return myPresentFloor;
						} else if (i == myDownButtonOuter[j]) {
							myPresentFloor = i;
							myDownButtonOuter[j] = 0;
							myDirection = DOWN;
							return myPresentFloor;
						}
					}
				}
			}
			int another_1= 1;
			int another_2 = 1;
			tempFloor--;
			if (tempFloor == 0) {
				tempFloor = 1;
			}
			int count_2= 1;
			int count_3= 1;
			int count_4 = 1;
			for (int k = 1; k < myUpButtonOuter.length; k ++) {
				if (myDownButtonOuter[k] == 0) {
					another_1++;
				} if (myUpButtonOuter[k] == 0) {
					another_2++;
				} if (myInnerButtons[k] == false) {
					count_4++;
				}
			}
			
			if ((another_1 == 10) && (count_4 == 10)) {
				for (int i = 1; i < myUpButtonOuter.length; i++) {
					if (myUpButtonOuter[i] != 0) {
						myPresentFloor = myUpButtonOuter[i];
						if (another_2 == 10) {
							myDirection = DOWN;
						} else {
							myDirection = UP;
						}
						myUpButtonOuter[i] = 0;
						return myPresentFloor;
					}
				}
			}
			for (int i = tempFloor; i >0 ; i--) {
				for (int j = 1; j < myDownButtonOuter.length; j++) {
					if (i == myDownButtonOuter[j]) {
						myPresentFloor = i;
						myDownButtonOuter[j] = 0;
						for (int k = 0; k< myDownButtonOuter.length; k++) {
							if (myDownButtonOuter[k] == 0 && myInnerButtons[k] == false) {
								count_2++;
							}
						}
						if (((count_2 == 10) || (myPresentFloor == 1)) && (another_2 < 10)) {
							myDirection = UP;
						}
						return myPresentFloor;
					} else if (myInnerButtons[i] == true) {
						myPresentFloor = i;
						for (int k =1; k < myDownButtonOuter.length;k++) {
							if (myPresentFloor == myDownButtonOuter[k]) {
								myDownButtonOuter[k] = 0;
								myInnerButtons[i] = false;
								return myPresentFloor;
							}
						}
						myInnerButtons[i] = false;
						for (int k = 0; k< myDownButtonOuter.length; k++) {
							if (myDownButtonOuter[k] == 0 && myInnerButtons[k] == false) {
								count_3++;
							}
						}
						if ((count_3 == 10)) {
							myDirection = UP;
						}
						return myPresentFloor;
					} else if ((myDownButtonOuter[j] > tempFloor) && (count_4 ==10)) {
						myPresentFloor = myDownButtonOuter[j];
						myDownButtonOuter[j] = 0;
						return myPresentFloor;
					}
				}
			}
			
		} else if (myDirection == UP) {
			tempFloor++;
			int another = 1;
			int count = 1; 
			int count_1 = 1;
			for (int k = 1; k < myUpButtonOuter.length; k ++) {
				if (myUpButtonOuter[k] == 0 && myInnerButtons[k] == false) {
					another++;
				} else if (myUpButtonOuter[k] == myPresentFloor) {
					myUpButtonOuter[k] = 0;
				}
			}
			if (another == 10) {
				for (int i = 1; i < myUpButtonOuter.length; i++) {
					if (myDownButtonOuter[i] != 0) {
						myPresentFloor = myDownButtonOuter[i];
						myDirection = DOWN;
						myDownButtonOuter[i] = 0;
						return myPresentFloor;
					}
				}
			}
			int m = 1;
			for (int i = tempFloor; i < 6; i++) {
				for (int j = 1; j < myUpButtonOuter.length; j ++) {
					if (i == myUpButtonOuter[j]){
						myPresentFloor = i;
						myUpButtonOuter[j] = 0;
						for (int k = 1; k < myUpButtonOuter.length; k ++) {
							if (myUpButtonOuter[k] == 0 && myInnerButtons[k] == false) {
								count++;
							} if (myDownButtonOuter[k] == 0) {
								m++;
							}
						}
						if ((count == 10 || myPresentFloor == 5) && (m < 10)) {
							myDirection = DOWN;
						}
						return myPresentFloor;
					}else if(myInnerButtons[i] == true) {
						myPresentFloor = i;
						myInnerButtons[i] = false;
						for (int k = 1; k < myUpButtonOuter.length; k ++) {
							if (myUpButtonOuter[k] == 0 && myInnerButtons[k] == false) {
								count_1++;
							}
						}
						if (count_1 == 10 || myPresentFloor == 5) {
							myDirection = DOWN;
						}
						return myPresentFloor;
					} else if (i == 5) {
						for (int k = 1; k < myUpButtonOuter.length; k++) {
							if (myUpButtonOuter[k] != 0) {
								myPresentFloor = myUpButtonOuter[k];
								myUpButtonOuter[k] = 0;
								return myPresentFloor;
							}
						}
					}
				}
				
			}
		}
		return myPresentFloor;
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
		int count = 0;
		int i = 0;
		while (i < 10) {
			if (myInnerButtons[i] == false && myUpButtonOuter[i] == 0 && myDownButtonOuter[i] == 0) {
				count += 1;
			}
			i++;
		}
		if (count == 9) {
			myUpButtonOuter[0] = 0;
			myDownButtonOuter[0] = 0;
			return myDirection;
		} else if (count == 10) {
			myDirection = NOT_SET;
		}
		return myDirection;
	}
}
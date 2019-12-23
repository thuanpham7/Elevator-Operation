package elevator;


 /**
  * @author ThuanPham
  */

public class Elevator implements ElevatorOperation
{
	public static final int NO_VALUE = -1;
	private int myNumberOfFloors;
	// Set my Direction is when it's pushed
	private int myDirection;
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
		
		//Return false if the floor pushed is less than 1 or the floor is greater than the number of floors
		if ((floor > myNumberOfFloors) || (floor < 1)) {
			return false;
		
		//Returning false if the floor pushed is equal to the number of floor
		} if (floor == myNumberOfFloors) {
			return false;
		}
		
		/*
		 * Keep track with the sequence number while
		 * the case is not in one of 2 false conditions above and store the 
		 * floor pushed in the up button outer array
		 */
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
	 */
	public boolean pushDown(int floor) {
		
		//Returning false if the floor pushed is less than 1
		if ((floor > myNumberOfFloors) || (floor < 1)) {
			return false;
			
		//Returning false if the floor pushed is equal to 1
		} if (floor == 1) {
			return false;
		}

		//Returning false if the floor pushed is equal to the present floor
		if (myPresentFloor == floor) {
			return false;
		}

		/*
	 	* Keep track with the
		* sequence number while the case is not in one of 3 false conditions
		* and store the floor pushed into the down button outer array
		*/
		myDownButtonOuter[mySequenceNumber] = floor;
		mySequenceNumber += 1;       
		
		return true;
	}

	/**
	 * If a valid thing to do, push a button inside the elevator
	 */
	public boolean pushIn(int floor) {
		
		//Returning false if floor the floor push in
		if (myPresentFloor == floor) {
			myInnerButtons[floor] = false;
			mySequenceNumber += 1;
			return false;
		} 

		/*
		 * Returning false if push-in floor is 
		 * greater than present floor while the direction is down
		 */
		if ((myDirection == DOWN) && (myPresentFloor < floor)) {
			return false;

		/*
		* Returning false if push-in floor is 
		* less than present floor while the direction is up
		*/	
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
	public int move() {
		
		//Naming tempFloor that equals to the present floor so it won't change the value of the present floor
		int tempFloor = myPresentFloor;
		
		//Set the direction which the first person push
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
			/*
			 * The floor can't go down from the first floor, therefore the conditions
			 * are to check whether there are still floor pushed from the outside
			 * by checking the array of myDownButtonOuter and myUpButtonOuter arrays
			 */
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
			
			/*
			 * Creating some constant numbers are used to check the index of each array
			 */
			int another_1 = 1;
			int another_2 = 1;
			int count_2= 1;
			int count_3= 1;
			int count_4 = 1;
			
			//The floor will go down when the direction is down
			tempFloor--;
			if (tempFloor == 0) {
				tempFloor = 1;
			}
			
			//Loop that check each value of 3 arrays
			for (int k = 1; k < myUpButtonOuter.length; k ++) {
				if (myDownButtonOuter[k] == 0) {
					another_1++;
				} if (myUpButtonOuter[k] == 0) {
					another_2++;
				} if (myInnerButtons[k] == false) {
					count_4++;
				}
			}
			
			/*
			 * Condition true that there is no value left in myDownButtonOuter
			 * and myInnerButtons arrays so the elevator whether changing the direction 
			 * to up or keep the direction of down
			 */
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
			
			//Loop that make the floor going down 1 floor when the direction is down
			for (int i = tempFloor; i >0 ; i--) {
				for (int j = 1; j < myDownButtonOuter.length; j++) {
					
					/*
					 * Going to the condition that floor the floor push down outer array match the floor 
					 * that's in the process of going down
					 */
					if (i == myDownButtonOuter[j]) {
						myPresentFloor = i;
						myDownButtonOuter[j] = 0; //When move to that floor, set it to zero
						
						//Checking if the myDownButtonOuter array is empty, the direction will go up
						for (int k = 0; k< myDownButtonOuter.length; k++) {
							if (myDownButtonOuter[k] == 0 && myInnerButtons[k] == false) {
								count_2++;
							}
						}
						if (((count_2 == 10) || (myPresentFloor == 1)) && (another_2 < 10)) {
							myDirection = UP;
						}
						return myPresentFloor;
						
					/*
					 * Going to the condition that floor the floor push in array match the floor
					 * that's in the process of going down
					 */
					}else if (myInnerButtons[i] == true) {
						myPresentFloor = i;
						myInnerButtons[i] = false;
						
						//Making the index of myDownButtonOuter that match the index of the array the make that index equals to 0
						for (int k =1; k < myDownButtonOuter.length;k++) {
							if (myPresentFloor == myDownButtonOuter[k]) {
								myDownButtonOuter[k] = 0;
								myInnerButtons[i] = false;
								return myPresentFloor;
							}
						}
						
						
						//Checking if the myDownButton array is empty , the direction will go up
						for (int k = 0; k< myDownButtonOuter.length; k++) {
							if (myDownButtonOuter[k] == 0 && myInnerButtons[k] == false) {
								count_3++;
							}
						}
						if ((count_3 == 10)) {
							myDirection = UP;
						}
						return myPresentFloor;
						
					//Returning floor the floor that down outer push is greater than present floor and inner button has to be empty
					}else if ((myDownButtonOuter[j] > tempFloor) && (count_4 == 10)) {
						myPresentFloor = myDownButtonOuter[j];
						myDownButtonOuter[j] = 0;
						return myPresentFloor;
					}
				}
			}
			
		/**
		 * The elevator is in the track of going down, if the up button outer
		 */
			
		}else if (myDirection == UP) {
			tempFloor++;		//The floor will go up when the direction is up
			
			//Initialize values mainly check if the array is equal to zero
			int another = 1;
			int count = 1; 
			int count_1 = 1;
			int m = 1;
			
			//Loop checks if the up button outer is empty 
			for (int k = 1; k < myUpButtonOuter.length; k ++) {
				if (myUpButtonOuter[k] == 0 && myInnerButtons[k] == false) {
					another++;
				} else if (myUpButtonOuter[k] == myPresentFloor) {
					myUpButtonOuter[k] = 0;
				}
			}
			
			//Changing the direction to down if up button outer is empty
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
			
			//Loop the process of floor going up then move to floor the floor that either match up button outer and inner button
			for (int i = tempFloor; i < 6; i++) {
				for (int j = 1; j < myUpButtonOuter.length; j ++) {
					
					/*
					 * Going to the condition that floor the floor push up outer array match the floor 
					 * that's in the process of going down
					 */
					if (i == myUpButtonOuter[j]){
						myPresentFloor = i;
						myUpButtonOuter[j] = 0;
						
						//Loop checking if the up button outer and inner button is empty, if it is, change the direction to down
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
						
					 
					/*
					 * Going to the condition that floor the floor push in array match the floor
					 * that's in the process of going up
					 */
					}else if(myInnerButtons[i] == true) {
						myPresentFloor = i;
						myInnerButtons[i] = false;
						
						//Loop checking if the up button outer and inner button arrays are empty, if it is, change the direction to down
						for (int k = 1; k < myUpButtonOuter.length; k ++) {
							if (myUpButtonOuter[k] == 0 && myInnerButtons[k] == false) {
								count_1++;
							}
						}
						if (count_1 == 10 || myPresentFloor == 5) {
							myDirection = DOWN;
						}
						return myPresentFloor;
						
					//if the temp floor reaches the highest floor, then check the up button outer array again to see if there is anybody push
					}else if (i == myNumberOfFloors) {
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
	public int getDirection() {
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
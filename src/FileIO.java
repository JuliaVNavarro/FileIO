import java.util.*;
import java.io.*;
	
public class FileIO {
	
	

	public static void main(String[] args) {
		
		/* Create two ArrayLists. One for Strings and one for integers.
		 * Call the readFile method while passing through the ArrayLists*/
		ArrayList <String> states = new ArrayList <String> ();
		ArrayList <Integer> population = new ArrayList <Integer> ();
		
		readFile(states, population);
		
		int choice = menu(); // initialize the menu
		
		while (choice != 5) { // while loop to keep program running until the user quits
			if (choice == 1) {
				sortStates(states, population); // sort alphabetically
				displayStates(states, population); // display states & populations
			}
			else if(choice == 2) {
				sortPopulation(states, population); // sort numerically
				displayStates(states, population); // display states & populations
			}
			else if(choice == 3) {
				System.out.printf("US Population = %, d \n", totalSum(population)); // display the total sum of the populations
			}
			else if (choice == 4) {
				populationGreater(states, population); // display the states & populations with populations greater than user's input
			}
			System.out.println();
			choice = menu(); // prompt user to select a new option from the menu
		}
	}
	
		/* The readFile method will look for the desired .txt file. If the file is found
		 * it will read the file, and split each string that is separated by commas
		 * from each line. Since the file should only contain a state followed by their population
		 * on each line, the states will be added to the ArrayList s and the populations
		 * will be changed from a string to an integer while being added to the ArrayList p.
		 * @parm an ArrayList for Strings
		 * @parm an ArrayList for integers*/
	public static void readFile(ArrayList <String> s, ArrayList <Integer> p) {
		
		try {
			Scanner read = new Scanner (new File("StatePops.txt")); // reads the file
			
			while(read.hasNext()) { // each line will be read until the end of the file has been reached
				String line = read.nextLine();
				String [] tokens = line.split(","); // splits information that is separated by a comma
				
				s.add(tokens[0]); // adds states to string ArrayList
				p.add(Integer.parseInt(tokens[1])); // adds populations to integer ArrayList
				
				}
			
			read.close(); // closes the file
		} catch(FileNotFoundException fnf) { // exception is thrown if the file can not be found
			System.out.println("File was not found");
		}
	}
	
	 /* The sortStates method will take all the states listed in the provided txt file
	  * and will arrange them in alphabetical order and will place the corresponding 
	  * population and place it in the same index in their separate ArrayList.
	  * @parm an ArrayList for Strings
	  * @parm an ArrayList for integers*/
	public static void sortStates(ArrayList <String> s, ArrayList <Integer> p) {
		
		boolean swapped = false;// Initialize boolean variable "swapped" to FALSE
		
	   /* As long as swapped is "TRUE", the loop will continue "swapping" states and their
		* corresponding populations in the two ArrayLists until the end of the lists have 
		* been reached*/
		do {

			swapped = false; // do/while loop will begin and end with swapped = false
			
			for (int i = 0; i < s.size() - 1; i++) { // traverse the entire ArrayList for the states
				
				/* if the compareToIgnoreCase results in a positive # larger than 0, then the string in the
				 * index after should go before that string alphabetically */
				if (s.get(i).compareToIgnoreCase(s.get(i+1)) > 0) { 
					
					/* save the state and corresponding population in initialized variables*/
					String sSwap = s.get(i); 
					int pSwap = p.get(i);
					
					/* copy the state and corresponding population that has a higher 
					 * precedence to the index before it*/
					s.set(i, s.get(i + 1));
					p.set(i, p.get(i + 1));
					
					/* take the saved state and corresponding population, and use them
					 * to replace the state and population that was in the index
					 * after their original index*/
					s.set(i + 1, sSwap);
					p.set(i + 1, pSwap);
				
					swapped = true; // swapped is now set to "TRUE"
				}
			}
			
		}while (swapped); // same as "while (swapped == true)"		
	}
	
	 /* The sortStates method will take all the populations listed in the provided txt file
	  * and will arrange them in numerical order and will place the corresponding 
	  * state and place it in the same index in their separate ArrayList.
	  * @parm an ArrayList for Strings
	  * @parm an ArrayList for integers*/
	public static void sortPopulation(ArrayList <String> s, ArrayList <Integer> p) {
		
		boolean swapped = false; // Initialize boolean variable "swapped" to FALSE
		
	   /* As long as swapped is "TRUE", the loop will continue "swapping" populations and their
	    * corresponding states in the two ArrayLists until the end of the lists have 
		* been reached*/
		do {
			swapped = false; // do/while loop will begin and end with swapped = false
			for (int i = 0; i < p.size() - 1; i++) { // traverse the entire ArrayList for the populations
				/* if the value in the current index larger than the value in the index to the right, then the value in the
				 * right index should go in the current index and the value in the current index should be moved up
				 * one index. */
				if (p.get(i) > p.get(i + 1)) {
					
					int pSwap = p.get(i);
					String sSwap = s.get(i);
					
					p.set(i, p.get(i + 1));
					s.set(i, s.get(i + 1));
					
					p.set(i + 1, pSwap);
					s.set(i + 1, sSwap);
					
					swapped = true; // swapped is set to "TRUE"
				}
			}
		}while(swapped); // same as "while(swapped == true)"
	}
	
	public static void displayStates(ArrayList <String> s, ArrayList <Integer> p) {
		
		for (int i = 0; i < s.size(); i++) { // traverse the ArrayLists till the end is reached for both
			
			/* %[argumentIndex$] [flag] [width] [.precision]type
			 * left alignment for the states, show commas in the population numbers, and 30 spaces
			 * between the states and populations */
			System.out.printf("%-30s %, d \n", s.get(i), p.get(i));
		}
	}
	
	/* This method will add up all of the populations and retrun the total
	 * @parm int ArrayList of populations
	 * @return int total sum of populations*/
	public static int totalSum(ArrayList <Integer> p) {
		
		int total = 0; //initialize total to 0
		
		for (int i = 0; i < p.size(); i++) { //traverse the ArrayList
			
			total += p.get(i); // the value of the current index is added to and becomes the total value
		}
		return total; //once the end of the ArrayList has been reached, the total will be returned
	}
	
	/* This method allows the user to enter a population, it will then take all the populations
	 * that are greater than the user's input and display them along with their corresponding state.
	 * @parm ArrayList String
	 * @parm ArrayList Integer*/
	public static void populationGreater(ArrayList <String> s, ArrayList <Integer> p) {
		
		/* Create two new empty ArrayLists to store the states and populations
		 * that have populations greater than the user's input*/
		ArrayList <String> _state = new ArrayList <String> ();
		ArrayList <Integer> _population = new ArrayList <Integer> ();
		
		/* Prompt the user to enter a population, while also checking that their
		 * input is an integer and that the integer is not greater than the 
		 * biggest population in the txt file*/
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a population: ");
		int entry = 0;
		boolean valid = false;
		while(!valid) {
			if (input.hasNextInt()) {
				entry = input.nextInt();
				if (entry < Collections.max(p)) {
					valid = true;
				} else if (entry >= Collections.max(p)) {
					System.out.println("No populations greater than " + entry + " found");
					break;
				}
			} else {
				input.next();
				System.out.print("Invalid Input. Try again: ");
			}
		}
		
		/* Once the user's entry is both an integer and is smaller than the largest population,
		 * we will traverse through the population ArrayList and collect all the values
		 * along with their corresponding states and add them to the new ArrayLists*/
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i) > entry) {
				_population.add(p.get(i));
				_state.add(s.get(i));
			}
		}
		
		displayStates(_state, _population); // use the displayStates method to display the states with the population greater than the user's entry
	}
	
	public static int menu() {
		
		Scanner input = new Scanner(System.in);
		int choice = 0;
		boolean valid = false;
		
		System.out.println("State Stats");
		System.out.println("1. Display Sorted States");
		System.out.println("2. Display Sorted Populations");
		System.out.println("3. Display Total US Population");
		System.out.println("4. Display States with Population Greater Than");
		System.out.println("5. Quit");
		
		while(!valid) {
			if (input.hasNextInt()) {
				choice = input.nextInt();
				if (choice >= 1 && choice <= 5) {
					valid = true;
				} else {
					System.out.println("Invalid option. Please try again: ");
					valid = false;
				}
			} else {
				input.next();
				System.out.println("Invalid input. Please try again:  ");
			}
		}
		
		return choice;
	}

}

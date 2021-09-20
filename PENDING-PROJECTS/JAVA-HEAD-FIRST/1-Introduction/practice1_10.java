public class practice1_10 {
	public static void main(String[] args) {
/*
		int x=2;
		String name = "Dirk";
		if (x == 10) {
			System.out.println("X must be 10");
		}else {
			System.out.println("x isn't 10");
		}
		if ((x<3) & (name.equals("Dirk"))) {
			System.out.println("Gently");
		}
		System.out.println("This line runs no matter what.");
*/

		//The application of booleans
		/*		
		boolean isHot = true;
		while (isHot) {
			System.out.println("Damm, It's hot.");	
		}
		*/
		//How to get a random number?
		//I am going to use an array of integers:
		int[] array = {1,2,3,4,5};
		int size = array.length;
		//Generate a random number everytime I run.
		int randomNumber = (int) (Math.random() * size);
		System.out.println("A random number now is: "+randomNumber);
	}
}



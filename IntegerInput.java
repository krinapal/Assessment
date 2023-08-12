import java.util.Scanner;

public class IntegerInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter an integer between 1 and 3: ");
        int n = scanner.nextInt();
        
        if (n >= 1 && n <= 3) {
            switch (n) {
                case 1:
                    System.out.println("You entered 1.");
                    
                    break;
                case 2:
                    System.out.println("You entered 2.");
                    
                    break;
                case 3:
                    System.out.println("You entered 3.");
                    
                    break;
            }
        } else {
            System.out.println("Invalid input. Please enter an integer between 1 and 3.");
        }
        
        scanner.close();

	}

}

// Guess.java
// Ari Iramanesh
// 1628897
// pa2, Guess
// You have three attempts to guess a number between 1 and 10

import java.util.Random;
import java.util.Scanner;

public class Guess {

  public static void main(String[] args ){
    Scanner scan = new Scanner(System.in);
    boolean didTheyWin = false;
    int target = (int) (Math.random() * 10) + 1;
    System.out.println("I'm thinking of an integer in the range 1 to 10. You have three guesses.\n");
    for (int count = 3; count >= 1; count--)
    {
      //Determine guess attempt
      if (count == 3) System.out.print("Enter your first guess: ");
      else if (count == 2) System.out.print("Enter your second guess: ");
      else if (count == 1) System.out.print("Enter your third guess: ");
      
      int guess = scan.nextInt();
      
      //Guess check
      if (guess == target)
      {
        System.out.println("You win!");
        count = 0;
        didTheyWin = true;
      }
      else if (guess > target) 
      {
        System.out.println("Your guess is too high.\n");
      }
      else if (guess < target) 
      { 
        System.out.println("Your guess is too low.\n");  	
      }
      
    } 
    if (!didTheyWin)
      System.out.println("You lose. The number was "+target+".");
  }	
}

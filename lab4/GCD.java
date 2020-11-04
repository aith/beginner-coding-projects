// GCD.java
// Ari Iramanesh
// 1628897
// Programming Assignment 3 - GCD, Euclidean Method
// Finds the GCD of two integers

import java.util.Scanner;

public class GCD {

  public static void main(String[] args) {  
    int dividend = 0, divisor = 0, remainder = 1;
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a positive integer: ");
    while (true) {
      int negative_checker;
      if (!sc.hasNextInt()) {
        sc.next();
        System.out.print("Please enter a positive integer: ");	 
      } else {
        negative_checker = sc.nextInt();
        if (negative_checker < 0) {
          System.out.print("Please enter a positive integer: ");	
          continue;
        }
        dividend = negative_checker;
        break;
      }
    }
    System.out.print("Enter another positive integer: ");
    while (true) {
        int negative_checker;
        if (!sc.hasNextInt()) {
          sc.next();
          System.out.print("Please enter a positive integer: ");	 
        } else {
          negative_checker = sc.nextInt();
          if (negative_checker < 0) {
            System.out.print("Please enter a positive integer: ");	
            continue;
          }
        divisor = negative_checker;
        break;
        }
    }
    System.out.print("The GCD of " + dividend + " and " + divisor + " is ");
    //flip the integers to dec order
    if (divisor > dividend) {
      int temp = dividend;
      dividend = divisor;
      divisor = temp;
    }
    //for when dividend can be perfectly divided by divisor
    if ((dividend%divisor) == 0) {
      remainder = divisor;
    } else {
      //euclidian method
      while (remainder > 0) {
      //see if the two numbers are equal
        if (dividend == divisor) {
          remainder = dividend;
          break;
        }
        if ((dividend%divisor) == 0) {
          break;
        }
        remainder = dividend%divisor;
        dividend = divisor;
        divisor = remainder;
      }
    }
    System.out.print(remainder + "\n");
  }
}

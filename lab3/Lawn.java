// Lawn.java
// Ari Iramanesh
// 1628897
// Programming Assignment 1 - Lawn
// Calculates how long it would take to mow your lawn.

import java.util.Scanner;

public class Lawn {

  public static void main(String[] args ){
    double length, width, lot, house, mowrate, leftover;
    int hours, minutes, seconds;
    
    Scanner input = new Scanner(System.in);
    length = input.nextDouble();
    width = input.nextDouble();
    lot = length * width;
    length = input.nextDouble();
    width = input.nextDouble();
    house = length * width;
    leftover = lot - house;
    System.out.println("The lawn area is " + leftover + " square feet.");
    System.out.print("Enter the mowing rate, in square feet per second: ");
    mowrate = input.nextDouble();
    leftover /= mowrate; //change to total time needed
    seconds = (int) Math.round((leftover) % 60);
    hours = (int) Math.max(0, (leftover/3600)); //double check if this just returns int
    leftover -= (hours * 3600);
    minutes = (int) (leftover / 60);
    System.out.println("The mowing time is " + hours + " " + (hours == 1? "hour" : "hours") + " " + minutes + " " + 
    	      (minutes == 1? "minute" : "minutes") + " " + seconds + " " + (seconds == 1? "second" : "seconds") + ".");
  }
}

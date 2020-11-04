// Queens.java
// Ari Iramanesh
// 1628897
// Programming Assignment 5 - Queens
// Finds solutions to Queens problem based on 
// board with size of inputed value
import java.util.Scanner;
public class Queens {

   public static void main(String[] args) {
      // args shoudl be {"-v", "someInt"}  or {"someInt"}
      Scanner sc = new Scanner(System.in);
      int num_solutions;
      int input_correct = 0;
      while (true) { //only runs once, not used as a loop
         num_solutions = 0;  
         try {
            int n = Integer.parseInt(args[0]);
            input_correct++;
            int[] array = create_array(n);
            for (int count = 1; count<(factorial(array.length-1)); count++) {
               if (isSolution(array)) {
                  num_solutions++;
               }
               nextPermutation(array);
            }
            System.out.println(n+"-Queens has " +num_solutions + " solutions");
            break;
         } catch (NumberFormatException e){
            try { String verbose = args[0];
            if (!(verbose.equals("-v"))) {
               break;               
            }
            int n = Integer.parseInt(args[1]);
            input_correct++;
            int[] array = create_array(n);
            for (int count = 1; count<(factorial(array.length-1)); count++) {
               if (isSolution(array)) {
                  num_solutions++;
                  print_array(array);
                  System.out.println(" ");
               }
               nextPermutation(array);
            }
            System.out.println(n+"-Queens has " +num_solutions + " solutions");
            break;
         } catch (java.lang.NumberFormatException e3) {
            break;
         }
         }catch (java.lang.ArrayIndexOutOfBoundsException e2) {
            break;  
         }
      }
      if (input_correct < 1) {
         System.out.println("Usage: Queens [-v] number");
         System.out.println("Options: -v verbose output, print all solutions"); 
      }
      sc.close();
   }
   
   static void nextPermutation(int[] array) {
      int pivot = 0;
      int successor = 0;
      for (int index = (array.length-1); index > 1; index--) {
         if (array[index-1] < array[index]) {
            pivot = (index-1);
            break;
         }
         if (index <= 2) {
            reverse(array, 1, (array.length-1));
            return;
         }
      }
      for (int i = (array.length -1); i > 1; i--) {
         if (array[pivot] < array[i]) {
            successor = i;
            break;
         }
      }
      swap(array, pivot, successor);
      reverse(array, (pivot + 1), (array.length-1));  
   }
   
   static void swap(int[] array, int num1, int num2) {
      int temp = array[num1];
      array[num1] = array[num2];
      array[num2] = temp;
   }
   //study this for midterm
   static void reverse(int[] array, int start, int end) {
      int a = start;
      int b = end;
      while (a < b) {
         swap(array, a, b);
         a++;
         b--;
      }
   }
   static boolean isSolution(int[] A) {
      for(int i=1;i<(A.length); i++) {
         for(int second = 1; second < (A.length); second++) {
            if (i == second) { 
               continue;
            }           
            if (Math.abs(A[i] - A[second]) == Math.abs(i - second)) {
               return false;
            }
         }
      }
      return true;
   } 
   
   static int factorial(int n) {
      int total = 1;
      for (int i=1; i<=n; i++) {
         total *= i;
      }
      return total;
   }
   static void print_array(int[] A) {
      System.out.print("(");
      for(int c=1; c<A.length; c++) {
         if (A.length - c == 1) {
            System.out.print(A[c]);
         } else System.out.print(A[c] + ", "); 
   } System.out.print(")");
   }
   
   static int[] create_array(int n) {
      int[] array = new int[n+1];
      for (int i = 0; i < n + 1; i++) {
         array[i] = i;
      }
      return array;
   }    
}


// Roots.java
// Ari Iramanesh
// 1628897
// pa4 - Roots
// Calculates the roots of a given polynomial, given an interval

import java.util.Scanner;
//does this need a precondition?
public class Roots {
    public static void main(String []args) {

        double tolerance = Math.pow(10, -11), threshold = Math.pow(10, -3);
        double ep1 = 1, ep2 = 2; //arbitrary

        int degree;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the degree: ");
        degree = sc.nextInt();
        double[] poly = new double[degree + 1];
        System.out.println("Enter "+(degree+1)+" coefficients: ");
        for(int count = 0; count < (poly.length); count++){
            poly[count] = sc.nextDouble();
        }
        
        while (true) {  
            System.out.println("Enter endpoints, or q to quit: ");
            if (sc.hasNextDouble()) {
                ep1 = sc.nextDouble(); //doesnt need to be ordered automatically
            } else break;
            
            if (sc.hasNextDouble()) {
                ep2 = sc.nextDouble();
            } else break;

            double[] derivative = diff(poly); 
            double mid = (ep1+ep2)/2; 


            if(poly(poly, ep1)*poly(poly, ep2) <= 0) {
                System.out.print("   Odd root in ["+ep1+", "+ep2+"] found at: ");
                System.out.printf("%.10f%n", (findRoot(poly, ep1, ep2, tolerance)));            
                
            } else if ((poly(derivative, ep1)*poly(derivative, ep2)) <= 0) {
                mid = findRoot(derivative, ep1, ep2, tolerance);
                 if (Math.abs(poly(poly, mid)) < threshold) {  
                    System.out.print("   Even root in ["+ep1+", "+ep2+"] found at: ");
                    System.out.printf("%.10f%n", mid);
                } else  System.out.println("   No root found in ["+ep1+", "+ep2+"]");
            
            } else System.out.println("   No root found in ["+ep1+", "+ep2+"]");                     
        } System.out.println("bye!");
    }
    
    static double poly(double[] C, double x) {
        double sum = 0;
        for (int index = 0; index < C.length; index++) {
            sum += C[index] * Math.pow(x, index);
        }
        return sum;
    }
    static double[] diff(double[] C) {
        double[] D = new double[C.length - 1];
        for(int index = 0; index < D.length; index++) {
            D[index] = (index + 1) * C[index + 1];
        }
        return D;
        
    }
    static double findRoot(double[] C, double a, double b, double tolerance) {
        double width = 1, mid; //arbitrary num    
            do {
                mid = (a+b)/2;
                if ((poly(C, a)*poly(C, mid)) <= 0) {
                    b = mid;
                } else {
                    a = mid;
                }
                width = b - a;
            } while(width > tolerance);
            return mid;
    }
}


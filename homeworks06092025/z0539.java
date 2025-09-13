import java.util.*;
public class z0539 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	long n = sc.nextLong();
	if (n % 2 == 0) {
	    System.out.println(n/2);
	} if (n % 2 == 1 & n != 1) {
	      System.out.println(n);
	} if (n < 2) {
	     System.out.println("0");
	}
    }
}
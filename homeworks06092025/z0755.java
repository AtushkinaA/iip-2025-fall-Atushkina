import java.util.*;
public class z0755 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int a = sc.nextInt();
	int b = sc.nextInt();
	int c = sc.nextInt();
	int ab = a+b-c;
	if (ab >= 0) {
	    System.out.println(ab);
	} else {
	      System.out.println("Impossible");
	}
    }
}
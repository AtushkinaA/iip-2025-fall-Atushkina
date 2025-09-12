import java.util.*;
public class z0754 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int a = sc.nextInt();
	int b = sc.nextInt();
	int c = sc.nextInt();
	int maxci = Math.max(Math.max(a, b), c);
	int minci = Math.min(Math.min(a, b), c);
	if (727 >= maxci & minci >= 94) {
	    System.out.println(maxci);
	} else {
	      System.out.println("Error");
	}
    }
}
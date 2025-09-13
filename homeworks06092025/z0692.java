import java.util.*;
public class z0692 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	long st = sc.nextLong();
	if (st > 0 && (st & (st - 1)) == 0) {
    	    System.out.println("YES");
	} else {
    	      System.out.println("NO");
	}
    }
}
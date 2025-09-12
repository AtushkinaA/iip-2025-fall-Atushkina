import java.util.*;
public class z0106 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int a = sc.nextInt();
	int kolvob = 0;
	int kolvoa = 0;
	while (a > 0) {
	    int b = sc.nextInt();
	    a -= 1;
	    if (b == 0) {
		kolvob += 1;
	    } else {
	          kolvoa += 1;
	    }
	}
	System.out.println(Math.min(kolvob, kolvoa));
    }
}
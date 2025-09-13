import java.util.*;
public class z0061 {
   public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int aa = sc.nextInt();
	int ab = sc.nextInt();
	int ac = sc.nextInt();
	int ad = sc.nextInt();
	int ae = sc.nextInt();
	int af = sc.nextInt();
	int ag = sc.nextInt();
 	int ah = sc.nextInt();
	int aceg = aa+ac+ae+ag;
	int bdfh = ab+ad+af+ah;
	if (aceg > bdfh) {
	    System.out.println("1");
	} if (aceg < bdfh) {
	      System.out.println("2");
	} if (aceg == bdfh) {
	      System.out.println("DRAW");
	}
    }
}
	      
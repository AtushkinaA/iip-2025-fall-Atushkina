import java.util.*;
public class PatternRegular {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String pattern = "(((((\\d{2}[02468][48])|(\\d{2}[13579][26])|(\\d{2}[2468][48]))|((([02468][48])|([13579][26])|([2468]0))0{2}))-((((0[13578])|(10)|(12))-(([0-2]\\d)|(3[01])))|(((0[469])|(11))-(([0-2]\\d)|(30)))|(02-[012]\\d)))|(((0{3}[01235679])|(\\d{2}(([13579][01345789])|([2468][1235679]))))-((((0[13578])|(10)|(12))-(([0-2]\\d)|(3[01])))|(((0[469])|(11))-(([0-2]\\d)|(30)))|(02-(([01]\\d)|(2[1-8]))))))T(([01]\\d)|(2[0-3])):[0-5]\\d:[0-5]\\d\\.\\d{9}Z";
		while (true) {
			String toTest = sc.nextLine();
			boolean matches = toTest.matches(pattern);
			System.out.println(matches ? "YES" : "NO");
		}
	}
}
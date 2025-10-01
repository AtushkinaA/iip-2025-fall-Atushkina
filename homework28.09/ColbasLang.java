import java.util.*;
public class ColbasLang {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String lang = sc.nextLine();
		String str = sc.nextLine();
		if (lang.equals("decode")) {
			System.out.println(cglc(str));
		}
		else if (lang.equals("encode")) {
			System.out.println(glcgl(str));
		} else {
			System.out.println("Invalid command");
		}
	} public static String cglc(String str) {
		String gl = "уеёыаоэяию";
		String res = "";
		for (int i = 0; i < str.length(); i++) {
			if (gl.contains(String.valueOf(Character.toLowerCase(str.charAt(str.length() - 1))))) {
				return "Invalid sausage string";
			}
			char n = str.charAt(i);
			if (gl.contains(String.valueOf(Character.toLowerCase(n))) && i+2 < str.length()) {
				char m = str.charAt(i+1);
				char v = str.charAt(i+2);
				if (m == 'с' && gl.contains(String.valueOf(Character.toLowerCase(v)))) {
					res += n;
					i += 2;
					continue;
				} else {
					res += n;
				}
			} else {
				res += n;
			}
		}
		return res;
	} public static String glcgl(String str) {
		String gl = "уеёыаоэяию";
		String res = "";
		char s = 'с';
		for (int i = 0; i < str.length(); i++) {
			char n = str.charAt(i);
			if (gl.contains(String.valueOf(Character.toLowerCase(n)))) {
				res += n;
				res += s;
				res += String.valueOf(Character.toLowerCase(n));
				continue;
			} else {
				res += n;
			}
		}
		return res;
	}
}
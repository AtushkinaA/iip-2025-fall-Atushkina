import java.util.*;
public class dzeshka {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = 0;
		int[] arrpr = new int[n];
		for (int i = 2; i < n; i++) {
			if (prime(i)) {
				arrpr[k++] = i;
			}
		}
		if (n % 2 == 0) {
			for (int i = 0; i < arrpr.length; i++) {
				for (int j = i; j < arrpr.length; j++) {
					if ((arrpr[i] + arrpr[j]) == n) {
						System.out.println(arrpr[i] + "+" + arrpr[j]);
						return;
					} 
				} 
			}
		} else {
			for (int i = 0; i < arrpr.length; i++) {
				for (int j = i; j < arrpr.length; j++) {
					for (int z = j; z < arrpr.length; z++) {
						if ((arrpr[i] + arrpr[j] + arrpr[z]) == n) {
							System.out.println(arrpr[i] + "+" + arrpr[j] + "+" + arrpr[z]);
							return;
						}
					}
				} 
			} 
		}
	} public static boolean prime(int a) {  
    	if (a <= 1) {  
        	return false;  
    	}  
    	for (int i = 2; i <= Math.sqrt(a); i++) {  
        	if (a % i == 0) {  
            	return false;  
        	}  
    	}  
    	return true;  
	} 
}
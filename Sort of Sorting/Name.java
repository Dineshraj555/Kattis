// S Dinesh Raj
// A0227252L

import java.lang.*;
import java.util.*;

public class Name {

	public static void main(String[] args) {

		int n;
		Com com = new Com();
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();

		while(n != 0) {
			String[] str = new String[n];
			for(int i = 0; i < n; i++){
				str[i] = sc.nextLine();
			}
			arrange(n,str,com);
			n = sc.nextInt();
			if (n != 0) {
				System.out.println("");
			}
			sc.nextLine();
		}

	}

	private static void arrange(int n, String[] str, Com com) {
		Arrays.sort(str, com);
		for(int i = 0; i < n; i++){
			System.out.println(str[i]);
		}
	}

	private static class Com implements Comparator<String> {
		
		public int compare(String s1, String s2) {
			int diff = (s1.substring(0,2)).compareTo(s2.substring(0,2));
			
			if (diff > 0) {
				return 1;
			} else if (diff < 0) {
				return -1;
			} else {
				return 0;
			}
		}

		public boolean equals(Object obj) {
			return this == obj;
		}
	}
}
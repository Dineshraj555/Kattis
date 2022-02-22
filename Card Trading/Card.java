// S Dinesh Raj
// A0227252L 
import java.util.*;
import java.io.*;

public class Card {

	public static void main(String[] args) throws IOException {
		int n; // deck size
		int t; // largest integar and no of types 
		int k; // no of combo 

		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first = br.readLine().split(" ");
		n = Integer.parseInt(first[0]);
		t = Integer.parseInt(first[1]);
		k = Integer.parseInt(first[2]);

		// input array for deck
		int[] deck = new int[n];
		String[] dec = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			deck[i] = Integer.parseInt(dec[i]);
		}

		// calculate the number of times a type is present
		int[] comAr = ComboArr(deck, t);

		// create table to store infomation on types 
		Type[] table = new Type[t];
		for(int j = 0; j < t; j++) {
			String[] price = br.readLine().split(" ");
			table[j] = new Type(j, Long.parseLong(price[0]), 
				Long.parseLong(price[1]), comAr[j]);
		}

		// sort based on sell + buy
		// amt() in type
		Diff diff = new Diff();
		Arrays.sort(table, diff);

		// calculate the coins
		long coins = 0;
		for(int j = 0; j < t; j++) {
			if (j < k) {
				// till we have K combo, buy what we need of that type
				coins -= table[j].buy() * table[j].remain();
			} else {
				// once we have K combo, sell the rest
				coins += table[j].sell() * table[j].no();
			}
		}

		//Print coins
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(coins);
		pw.close();
	}

	// no of numbers present
	// grouped based on their index
	private static int[] ComboArr(int[] input, int t) {
		
		Arrays.sort(input);
		
		int[] combo = new int[t];
		for (int b = 0; b < t; b++) {
			combo[b] = 0;
		}

		int count = 0;
		int i = 0;
		while (i < input.length) {
			if(input[i] == (count + 1)) {
				combo[count] += 1;
				i += 1;
			} else {
				count +=1;
			}
		}

		return combo;
	}

	private static class Diff implements Comparator<Type> {
        
        public int compare(Type t1, Type t2) {
            long diff = t1.amt() - t2.amt();
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

	private static class Type {
		int type;
		long buy;
		long sell;
		int no;

		public Type(int index, long x, long y, int no) {
			this.type = index + 1;
			this.buy = x;
			this.sell = y;
			this.no = no;
		}

		public long buy() {
			return this.buy;
		}		

		public long sell() {
			return this.sell;
		}

		public int type() {
			return this.type;
		}

		public int no() {
			return this.no;
		}

		public int remain() {
			if(this.no == 0) {
				return 2;
			} else if(this.no == 1) {
				return 1;
			} else {
				return 0;
			}
		}

		public long amt() {
			return (this.no * this.sell) + (remain() * this.buy); 
		}
	}
}
// S Dinesh Raj
// A0227252L

import java.lang.*;
import java.util.*;

public class Coconut {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int no_sy = sc.nextInt();
		int no_player = sc.nextInt();

		ArrayList<Player> list = new ArrayList<Player>();

		for(int i = 0; i < no_player; i++) {
			list.add(new Player(i + 1, 0));
		}

		int index = (no_sy - 1) % no_player;

		while (list.size() > 1) {

		    Player p = list.get(index);
		    int n = p.getno();
		    
		    // 0 -> folded
		    // 1 -> fist
		    // 2 -> palm
			if(p.getstate() == 0) {
				list.remove(index);
				list.add(index, new Player(n,1));
				list.add(index, new Player(n,1));
			} else if (p.getstate() == 1) {
				list.remove(index);
				list.add(index, new Player(n,2));
				index += 1;
			} else {
				list.remove(index);
			}

			index = index + (no_sy - 1);

			if (index >= list.size()) {
				index = index % list.size();
			}

		}

		System.out.println(list.get(0).getno());
	}

	public static class Player {

		int no;
		int state;

		public Player(int no, int state) {
			this.no = no;
			this.state = state;
		}

		public int getno() {
			return this.no;
		}

		public int getstate() {
			return this.state;
		}
	}

}
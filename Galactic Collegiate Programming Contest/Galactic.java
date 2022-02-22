// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Galactic {

	public static void main(String[] args) throws IOException {
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]); // teams
		int m = Integer.parseInt(input[1]); // events

        AVL avl = new AVL();
        Team[] team = new Team[n];
        for(int x = 0; x < n; x++) {
            team[x] = new Team(x);
            avl.insert(team[x]);
        }

		for(int i = 0; i < m; i++) {
			 String[] in = br.readLine().split(" ");
			 int t = Integer.parseInt(in[0]) - 1; // team that solved
			 int p = Integer.parseInt(in[1]);     // penalty

             avl.delete(team[t]);
             team[t].update(p);
             avl.insert(team[t]);

             int result = n - avl.rank(team[0]);
             pw.println(result);
		}

        br.close();
		pw.close();
    }
}

class Team implements Comparable<Team> {
	public int no;
	public int p_sol;
	public int pen;

	public Team(int no) {
		this.no = no;
		this.p_sol = 0;
		this.pen = 0;
	}

	public void update(int p) {
		this.p_sol += 1;
		this.pen += p;
	}

    @Override
      public int compareTo(Team t) {
        if (this.p_sol > t.p_sol) {
            return 1;
        } else if (this.p_sol == t.p_sol && this.pen < t.pen) {
            return 1;
        } else if (this.p_sol == t.p_sol && this.pen == t.pen) {
            if (this.no < t.no) {
                return 1;
            } else if (this.no > t.no) {
                return -1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

}

class Vertex {
 
  public Vertex right;
  public Vertex left;
  public Vertex parent;
  public Team key;
  public int height; 
  public int size; 

  Vertex(Team v) { 
  	key = v; 
  	parent = null;
    left = null;
    right = null; 
  	height = 0;
  	size = 1; 
  }

}

class AVL {

	public Vertex root;
	public AVL() {root = null;}
    
	public Vertex search(Vertex T, Team v) {
        if (T == null) {
            return null;
        } else if (v.compareTo(T.key) < 0) {
            return search(T.left, v);
        } else if (v.compareTo(T.key) > 0) {
            return search(T.right, v);
        } else {
            return T;
        }
    }

    public Team findMin() { return findMin(root); }
    public Team findMin(Vertex T) {
        if (T.left == null) {
            return T.key;
        } else {
            return findMin(T.left); 
        }         
    }

    public Team findMax() { return findMax(root); }
    public Team findMax(Vertex T) {
        if (T.right == null) {
            return T.key;
        } else {
            return findMax(T.right); 
        }       
    }

    public Team successor(Team v) {
        Vertex x = search(root,v);
        if (x == null) {
            return null;
        } else {
            return successor(x);
        }
    }

    public Team successor(Vertex T) {
    	if (T.right != null) {                             
    		return findMin(T.right);                        
        } else {
        	Vertex par = T.parent;
        	Vertex cur = T;
        	while ((par != null) && (cur.key.no == par.right.key.no)) {
        	    cur = par;                                  
        	    par = cur.parent;
            }
            return par == null ? null : par.key;           
        }
    }

    public void insert(Team v) { root = insert(root, v); }
    public Vertex insert(Vertex T, Team v) {
    	if (T == null) {
    		return new Vertex(v);
    	} else if (v.compareTo(T.key) > 0) {
    		T.right = insert(T.right, v);
    		T.right.parent = T;
    	} else { 
    		T.left = insert(T.left, v);
    		T.left.parent = T;
    	}
    	update(T);
    	return balance(T);
    }

    public void delete(Team v) { root = delete(root, v); }
    public Vertex delete(Vertex T, Team v) {
    	if (T == null)  {return T;}
    	if (v.compareTo(T.key) > 0) {
    		T.right = delete(T.right, v);
    	} else if (v.compareTo(T.key) < 0) {
    		T.left = delete(T.left, v);
    	} else {
    		if (T.left == null && T.right == null) {
    			T = null;
    		} else if (T.left == null && T.right != null) {
    			T.right.parent = T.parent;
    			T = T.right;
    		} else if (T.left != null && T.right == null) {
    			T.left.parent = T.parent;
    			T = T.left;
    		} else { 
    			Team successorV = successor(v);
    			T.key = successorV;
    			T.right = delete(T.right, successorV);
    		}
    	}
    	update(T);
    	return balance(T);
    }

    public int size(Vertex T) {
        if (T == null) {
            return 0;
        } else {
            return T.size;
        }
    }

    public int height(Vertex T) {
        if (T == null) {
            return -1;
        } else {
            return T.height;
        }
    }

    public int balance_f(Vertex T) {
    	if (T == null) { return 0; }
    	else { return height(T.left) - height(T.right);} 
    }

    public Vertex right_r(Vertex T) {
    	
    	if (T == null) {return null;}

    	Vertex l = T.left;
    	Vertex l_r = l.right;

    	l.right = T;
    	T.left = l_r;

    	update(T);
    	update(l);

    	return l;
    }

    public Vertex left_r(Vertex T) {

    	if (T == null) {return null;}

    	Vertex r = T.right;
    	Vertex r_l = r.left;

    	r.left = T;
    	T.right = r_l;

    	update(T);
    	update(r);

    	return r;
    }

    public Vertex balance(Vertex T) {
        int b_f = balance_f(T);
        if (b_f == -2) {
            if (balance_f(T.right) == 1) {
                T.right = right_r(T.right);
            }
            T = left_r(T);
        } else if (b_f == 2) {
            if (balance_f(T.left) == -1) {
                T.left = left_r(T.left);
            } 
            T = right_r(T);
        }
        return T;
    }

    public int rank(Team v) { return rank(root, v); }
    public int rank(Vertex T, Team v) {
    	if (T == null) {
    		return 0;
    	} else if (v.compareTo(T.key) < 0) {
    		return rank(T.left, v);
    	} else if (v.compareTo(T.key) > 0) { 
    		return size(T.left) + 1 + rank(T.right, v);
    	} else {
    		return size(T.left);
    	}
    }

    public void update(Vertex T) {
        if (T != null) {
            T.size = size(T.left) + size(T.right) + 1;
            T.height = Math.max(height(T.left),height(T.right)) + 1; 
        }
    }
}








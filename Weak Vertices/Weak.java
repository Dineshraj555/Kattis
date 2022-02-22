// S Dinesh Raj
// A0227252L 
import java.io.*;
import java.util.*;


public class Weak {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		while (n != -1) {
			
			int[][] in_mat = new int[n][n];
			int[] result = new int[n];

			for(int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");
				for(int j = 0; j < n; j++) {
					in_mat[i][j] = Integer.parseInt(input[j]);
				}
			} 

			for(int x = 0; x < n; x++) {
				for(int y = 0; y < n; y++) {
					if (y != x) {
						for(int z = 0; z < n; z++) {
							if (z != x && z != y) {
								
								boolean c1 = (in_mat[x][y] == 1);
								boolean c2 = (in_mat[y][z] == 1);
								boolean c3 = (in_mat[z][x] == 1);

								if (c1 && c2 && c3) {
									result[x] = 3000;
									result[y] = 3000;
									result[z] = 3000;

								}

							}
						}
					}
				}
			}

			for(int w = 0; w < n; w++) {
				if (result[w] != 3000) {
					System.out.print(w + " ");
				}
			}
			
			System.out.print("\n");
			n = Integer.parseInt(br.readLine());
		}
	
		br.close();
	}
}
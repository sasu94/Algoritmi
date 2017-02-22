package algoritmi;

import java.util.Scanner;

public class asd {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String field = s.nextLine();
		int nf = 0, nM = 0, nP = 0;
		do {
			nf = Integer.parseInt(field.split(" ")[1]);

			String second = s.nextLine();
			String[] sec = second.split(" ");

			nM = Integer.parseInt(sec[0]);
			nP = Integer.parseInt(sec[1]);

			String num = s.nextLine();
			String[] numbers = num.split(" ");

			System.out.println("result " + nf);
			partition(numbers, nM, nP);

			field = s.nextLine();
			if (!field.equals("-1"))
				System.out.println();
		} while (!field.equals("-1"));
		s.close();
	}

	public static void partition(String s[], int n, int k) {
		int[][] m = new int[n + 1][k + 1];
		int[][] d = new int[n + 1][k + 1];
		int cost;
		int i, j, x;
		m[0][1] = 0;
		for (i = 1; i <= n; i++) {
			m[i][1] = m[i - 1][1] + Integer.parseInt(s[i - 1]);
		}
		m[1][1] = Integer.parseInt(s[0]);
		for (i = 2; i <= n; i++)
			for (j = 2; j <= k; j++) {
				m[1][j] = Integer.parseInt(s[0]);
				m[i][j] = Integer.MAX_VALUE;
				for (x = 1; x <= (i - 1); x++) {
					cost = Math.max(m[x][j - 1], m[i][1] - m[x][1]);
					if (m[i][j] > cost) {
						m[i][j] = cost;
						d[i][j] = x;
					}
				}
			}
		reconstruct_partition(s, d, n, k);
	}

	private static void reconstruct_partition(String[] s, int[][] d, int n, int k) {
		if (k == 1)
			for (int i = 1; i <= n; i++)
				System.out.print((i == n) ? s[i - 1] : s[i - 1] + " ");
		else {
			reconstruct_partition(s, d, d[n][k], k - 1);
			System.out.print(" | ");
			for (int i = d[n][k] + 1; i <= n; i++)
				System.out.print((i == n) ? s[i - 1] : s[i - 1] + " ");
		}
	}

}

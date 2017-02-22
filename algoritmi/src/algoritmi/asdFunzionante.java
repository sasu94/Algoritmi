package algoritmi;

import java.util.Scanner;

public class asdFunzionante {

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
			int[] numeri = new int[nM + 1];
			numeri[0] = 0;
			for (int i = 1; i < nM + 1; i++) {
				numeri[i] = Integer.parseInt(numbers[i - 1]);
			}

			System.out.println("result " + nf);
			partition(numeri, nM, nP);

			field = s.nextLine();
			if (!field.equals("-1"))
				System.out.println();
		} while (!field.equals("-1"));
		s.close();
	}

	public static void partition(int s[], int n, int k) {
		int[][] m = new int[n + 1][k + 1];
		int[][] d = new int[n + 1][k + 1];
		int[] p = new int[n + 1];
		int cost;
		int i, j, x;
		p[0] = 0;
		for (i = 1; i <= n; i++) {
			p[i] = p[i - 1] + s[i];
			m[i][1] = p[i];
		}
		for (j = 1; j <= k; j++)
			m[1][j] = s[1];
		for (i = 2; i <= n; i++)
			for (j = 2; j <= k; j++) {
				m[i][j] = Integer.MAX_VALUE;
				for (x = 1; x <= (i - 1); x++) {
					cost = Math.max(m[x][j - 1], p[i] - p[x]);
					if (m[i][j] > cost) {
						m[i][j] = cost;
						d[i][j] = x;
					}
				}
			}
		reconstruct_partition(s, d, n, k);
	}

	private static void reconstruct_partition(int[] s, int[][] d, int n, int k) {
		if (k == 1)
			print(s, 1, n);
		else {
			reconstruct_partition(s, d, d[n][k], k - 1);
			System.out.print(" | ");
			print(s, d[n][k] + 1, n);
		}
	}

	private static void print(int[] s, int start, int end) {
		int i;
		for (i = start; i <= end; i++)
			System.out.print((i == end) ? s[i] : s[i] + " ");
	}
}

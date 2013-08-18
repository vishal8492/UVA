import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static ArrayList<String> perms;

	public static void main(String args[]) throws NumberFormatException,
			IOException {
		String parts[], str;
		perms = new ArrayList<String>();
		perm("BCG", 0, 3);
		Collections.sort(perms);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while ((str = br.readLine()) != null) {
			Bean bean[] = new Bean[3];
			int green = 0, brown = 0, clear = 0;
			parts = str.split(" ");
			int k = 0;
			for (int i = 0; i < 3; i++) {
				brown = Integer.parseInt(parts[k++]);
				green = Integer.parseInt(parts[k++]);
				clear = Integer.parseInt(parts[k++]);
				bean[i] = new Bean(brown, green, clear);
			}
			calc_mov(bean);
		}

	}

	private static void calc_mov(Bean[] bean) {
		// TODO Auto-generated method stub
		int min = 0, cost[] = new int[6], j = 0, temp = 0, min_index = 0;
		for (String per : perms) {
			temp = 0;
			for (int i = 0; i < 3; i++) {
				temp += bean[i].calc_cost(per.charAt(i), bean, i);
			}
			min = (j == 0 ? temp : (temp < min ? temp : min));
			cost[j++] = temp;
		}
		for (int i = 0; i < 6; i++) {

			if (cost[i] == min) {
				min_index = i;
				break;
			}
		}
		print_min(min_index, min);

	}

	private static void print_min(int min_index, int min) {

		System.out.println(perms.get(min_index) + " " + min);
	}

	private static void perm(String string, int i, int n) {

		int k = 0;
		if (i == n) {
			perms.add(string);
		} else {
			for (int j = i; j < n; j++) {

				perm(string.substring(j, n) + string.substring(i, j)
						+ string.substring(0, i), i + 1, n);

			}
		}

	}

}

class Bean {
	int brown, green, clear;

	Bean(int brown, int green, int clear) {
		this.brown = brown;
		this.green = green;
		this.clear = clear;
	}

	public int calc_cost(char ch, Bean[] bean, int t) {
		// TODO Auto-generated method stub
		int cost = 0;
		if (ch == 'G')
			cost = green_cost(bean, t);
		if (ch == 'C')
			cost = clear_cost(bean, t);
		if (ch == 'B')
			cost = brown_cost(bean, t);
		return cost;
	}

	private int brown_cost(Bean[] bean, int t) {
		// TODO Auto-generated method stub
		int cost = 0;
		for (int i = 0; i < 3; i++) {
			if (i != t)
				cost += bean[i].brown;
		}
		return cost;
	}

	private int clear_cost(Bean[] bean, int t) {
		int cost = 0;
		for (int i = 0; i < 3; i++) {
			if (i != t)
				cost += bean[i].clear;
		}
		return cost;
	}

	private int green_cost(Bean[] bean, int t) {
		int cost = 0;
		for (int i = 0; i < 3; i++) {
			if (i != t)
				cost += bean[i].green;
		}
		return cost;
	}
}
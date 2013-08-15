import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Main {
	Map<Integer, ArrayList<Integer>> map;

	Main() {
		map = new TreeMap<Integer, ArrayList<Integer>>();

	}

	public static void main(String args[]) throws NumberFormatException,
			IOException {
		Main mn = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String cl;
		String[] parts;
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> al = new ArrayList<Integer>();
			al.add(i);
			mn.map.put(i, al);
		}
		while (!(cl = br.readLine()).equals("quit")) {
			cl = cl.trim();
			parts = cl.split(" +");
			if (parts[0].equals("move")) {
				if (parts[2].equals("onto")) {
					move_onto(Integer.parseInt(parts[1]),
							Integer.parseInt(parts[3]), mn);
				} else {
					move_over(Integer.parseInt(parts[1]),
							Integer.parseInt(parts[3]), mn);

				}
			} else {

				if (parts[2].equals("onto")) {
					pile_onto(Integer.parseInt(parts[1]),
							Integer.parseInt(parts[3]), mn);

				} else {

					pile_over(Integer.parseInt(parts[1]),
							Integer.parseInt(parts[3]), mn);

				}
			}
		}
		print(mn);
	}

	private static void print(Main mn) {
		String pt = "";
		Set<Entry<Integer, ArrayList<Integer>>> st = mn.map.entrySet();
		for (Entry<Integer, ArrayList<Integer>> entry : mn.map.entrySet()) {
			System.out.print(entry.getKey() + ":");
			for (int a : entry.getValue()) {
				System.out.print(" " + a);
			}

			System.out.println();
		}

	}

	private static void move_onto(int a, int b, Main mn) {
		// TODO Auto-generated method stub
		if (a != b) {
			int temp1 = search(a, mn), temp2 = search(b, mn);
			if (temp1 != temp2) {
				ArrayList<Integer> al1 = mn.map.get(temp1);
				ArrayList<Integer> al2 = mn.map.get(temp2);

				int loc = search_loc(a, al1), loc1 = search_loc(b, al2);

				int j = 0;

				while (al1.size() != loc + 1) {
					j = al1.remove(al1.size() - 1);
					mn.map.get(j).add(0, j);
				}

				while (al2.size() != loc1 + 1) {
					j = al2.remove(al2.size() - 1);
					mn.map.get(j).add(0, j);
				}
				j = al1.remove(al1.size() - 1);
				al2.add(j);
			}
		}
	}

	private static void pile_onto(int a, int b, Main mn) {

		if (a != b) {
			int temp1 = search(a, mn), temp2 = search(b, mn);
			if (temp1 != temp2) {
				ArrayList<Integer> al1 = mn.map.get(temp1);
				ArrayList<Integer> al2 = mn.map.get(temp2);

				int i = al1.size() - 1, j = 0;
				i = al2.size() - 1;

				int loc = search_loc(b, al2);
				while (al2.size() != loc + 1) {
					j = al2.remove(al2.size() - 1);
					mn.map.get(j).add(0, j);
				}
				loc = search_loc(a, al1);
				while (al1.size() != loc) {
					j = al1.remove(loc);
					al2.add(j);
				}
			}
		}
	}

	private static void pile_over(int a, int b, Main mn) {

		if (a != b) {
			int temp1 = search(a, mn), temp2 = search(b, mn);
			if (temp1 != temp2) {
				ArrayList<Integer> al1 = mn.map.get(temp1);
				ArrayList<Integer> al2 = mn.map.get(temp2);

				int j = 0, i;
				int loc = search_loc(a, al1);
				i = loc;
				while (al1.size() != loc) {
					j = al1.remove(loc);
					al2.add(j);
				}

			}
		}
	}

	private static void move_over(int a, int b, Main mn) {

		if (a != b) {
			int temp1 = search(a, mn), temp2 = search(b, mn);
			if (temp1 != temp2) {
				ArrayList<Integer> al1 = mn.map.get(temp1);
				ArrayList<Integer> al2 = mn.map.get(temp2);
				int j;
				int loc = search_loc(a, al1);
				while (al1.size() != loc + 1) {

					j = al1.remove(al1.size() - 1);
					mn.map.get(j).add(0, j);
				}
				j = al1.remove(al1.size() - 1);
				al2.add(j);
			}
		}
	}

	private static int search_loc(int a, ArrayList<Integer> al1) {
		int i = 0;
		for (int a1 : al1) {
			if (a == a1)
				return i;
			i++;
		}
		return 0;
	}

	private static int search(int a, Main mn) {

		Set<Entry<Integer, ArrayList<Integer>>> st = mn.map.entrySet();

		for (Entry<Integer, ArrayList<Integer>> entry : mn.map.entrySet()) {

			for (int a1 : entry.getValue()) {

				if (a1 == a) {
					return entry.getKey();
				}
			}
		}
		return 0;
	}
} 

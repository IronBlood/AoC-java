package y2016.d19;

public class Solution {
	private static class Elf {
		public int count;
		public int id;

		Elf(int id) {
			this.id = id;
			this.count = 1;
		}
	}

	private static class LinkedListNode {
		public int id;
		public LinkedListNode next;
		public LinkedListNode prev;

		LinkedListNode(int id) {
			this.id = id;
		}
	}

	public static int get_all(int num) {
		Elf[] arr = new Elf[num];
		for (int i = 0; i < num; i++) {
			arr[i] = new Elf(i + 1);
		}

		int i = 0;
		while (arr[i].count != num) {
			int j = (i + 1) % num;
			while (arr[j].count == 0) {
				j = (j + 1) % num;
			}
			if ((arr[i].count += arr[j].count) == num) {
				return arr[i].id;
			}
			arr[j].count = 0;
			while (arr[j].count == 0) {
				j = (j + 1) % num;
			}
			i = j;
		}
		return i;
	}

	public static int get_all_2(int num) {
		LinkedListNode[] nodes = new LinkedListNode[num];
		for (int i = 0; i < num; i++) {
			nodes[i] = new LinkedListNode(i + 1);
			if (i > 0) {
				nodes[i - 1].next = nodes[i];
				nodes[i].prev = nodes[i - 1];
			}
		}
		nodes[num - 1].next = nodes[0];
		nodes[0].prev = nodes[num - 1];

		int ptr_idx = num >> 1, tick = num % 2;
		LinkedListNode ptr = nodes[ptr_idx];

		while (num-- > 1) {
			ptr.next.prev = ptr.prev;
			ptr.prev.next = ptr.next;
			if (++tick == 2) {
				tick = 0;
				ptr = ptr.next.next;
			} else {
				ptr = ptr.next;
			}
		}

		return ptr.id;
	}

	public static void run(String input) {
		int num = Integer.parseInt(input);
		System.out.println(get_all(num));
		System.out.println(get_all_2(num));
	}
}

public class LinkedListDeque<Item> {

	// having ItemNode to assisst the LinkedListDeque
	private class ItemNode {
		public Item val;
		public ItemNode prev;
		public ItemNode next;
		//constructor
		public ItemNode(Item val, ItemNode prev, ItemNode next) {
			this.val = val;
			this.prev = prev;
			this.next = next;
		}
	}

	//instant variable for LinkedListDeque
	private int size;
	private ItemNode sentinal;

	public LinkedListDeque() {
		size = 0;
		sentinal = new ItemNode(null, null, null);
		sentinal.prev = sentinal;
		sentinal.next = sentinal;
	}


	public void addFirst(Item item) {
		// sentinal sentinal
		// sentinal -> first sentinal
		// sentinal -> first <- sentinal
		size++;
		sentinal.next = new ItemNode(item, sentinal, sentinal.next);
		sentinal.next.next.prev = sentinal.next;
	}

	public void addLast(Item item) {
		// sentinal sentinal
		// sentinal last <- sentinal
		// sentinal -> last <- sentinal
		size++;
		sentinal.prev = new ItemNode(item, sentinal.prev, sentinal);
		sentinal.prev.prev.next = sentinal.prev;
	}

	public Item removeFirst() {
		if (isEmpty()) return null;

		Item cur = sentinal.next.val;
		// remove first item and reconnect the Nodes
		// sentinal -> first -> second -> sentinal
		// sentinal -> second -> sentinal
		sentinal.next = sentinal.next.next;
		// sentinal <-> second -> sentinal
		sentinal.next.prev = sentinal;
		size--;
		return cur;
	}

	public Item removeLast() {
		if (isEmpty()) return null;

		Item cur = sentinal.prev.val;
		// remove last item and reconnect the Nodes
		// sentinal -> first -> second -> sentinal
		// sentinal -> first <- sentinal
		sentinal.prev = sentinal.prev.prev;
		// sentinal -> first <-> sentinal
		sentinal.prev.next = sentinal;
		size--;
		return cur;
	}

	public Item get(int index) {
		if (size - 1 < index) return null;
		ItemNode cur = sentinal.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.val;
	}

	public Item getRecursive(int index) {
		if (size - 1 < index) return null;
		ItemNode cur = sentinal;
		return getRecursive(index, cur);
	}

	private Item getRecursive(int index, ItemNode cur) {
		if (index == 0) return cur.next.val;
		return getRecursive(index - 1, cur.next);
	}

	public void printDeque() {
		ItemNode cur = sentinal;
		while (cur.next != sentinal) {
			System.out.print(cur.next.val + " ");
			cur = cur.next;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}
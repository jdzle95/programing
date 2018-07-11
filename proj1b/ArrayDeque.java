public class ArrayDeque<Item> implements Deque<Item> {
	private int size;
	private int firstSpace;
	private int lastSpace;
	private Item[] items;

	// constructor
	public ArrayDeque() {
		size = 0;
		items = (Item[]) new Object[8];
		firstSpace = 3;
		lastSpace = 4;
	}

	// to take care of the size issues, to expand the array or to compress the array
	// if the size > items.length, double the size
	// if the usage is less than 0.25, half the size
	// always put the data on the middle of the resized array
	private void resize() {
		if (isFull() || tooEmpty()) {
			int capacity = items.length;
			if (1.0 * size / capacity > 0.8) {
				capacity *= 2;
			} else if (tooEmpty()) {
				capacity /= 2;
			}
			// after resizeing items as needed, put the data into the new array
			// if the array size does not change, we adjust the 
			Item[] newItems = (Item[]) new Object[capacity];
			System.arraycopy(items, firstSpace + 1, newItems, capacity / 2 - size / 2, size);
			firstSpace = capacity / 2 - 1 - size / 2;
			lastSpace = firstSpace + size + 1;
			items = newItems;
		}
		return;

	}

	private boolean isFull() {
		return firstSpace < 0 || lastSpace == items.length;
	}

	private boolean tooEmpty() {
		if (items.length <= 8) return false;
		return 1.0 * size / items.length < 0.25;
	}



	@Override
	public void addFirst(Item item) {
		resize();
		items[firstSpace] = item;
		size++;
		firstSpace--;
	}

	@Override
	public void addLast(Item item) {
		resize();
		items[lastSpace] = item;
		size++;
		lastSpace++;
	}

	@Override
	public void printDeque() {
		int start = firstSpace + 1;
		for (int i = 0; i < size; i++) {
			System.out.print(items[start + i] + " ");
		}
		System.out.println();
	}

	@Override
	public Item removeFirst() {
		Item cur = items[firstSpace + 1];
		size--;
		items[firstSpace + 1] = null;
		firstSpace++;
		resize();
		return cur;
	}

	@Override
	public Item removeLast() {
		Item cur = items[lastSpace - 1];
		size--;
		items[lastSpace - 1] = null;
		lastSpace--;
		resize();
		return cur;
	}

	@Override
	public Item get(int index) {
		return items[firstSpace + 1 + index];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
}
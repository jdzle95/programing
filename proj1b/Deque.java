public interface Deque<Item> {
	public void addFirst(Item item);
	public void addLast(Item item);
	public void printDeque();
	public Item removeFirst();
	public Item removeLast();
	public Item get(int index);
	public boolean isEmpty();
	public int size();
}
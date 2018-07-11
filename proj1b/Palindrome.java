public class Palindrome {
	public Deque<Character> wordToDeque(String word) {
		Deque<Character> dq = new LinkedListDeque<Character>();
		for (int i = 0; i < word.length(); i++) {
			dq.addLast(word.charAt(i));
		}
		return dq;
	}

	public boolean isPalindrome(String word){
		if (word == null || word.length() < 2) {
			return true;
		}
		Deque<Character> dq = wordToDeque(word.toLowerCase());
		for (int i = 0; i < dq.size() / 2; i++) {
			if (dq.get(i) != dq.get(word.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}


	//static CharacterComparator obo = new OffByOne();

	public boolean isPalindrome(String word, CharacterComparator cc) {
		if (word == null || word.length() < 2) {
			return true;
		}
		Deque<Character> dq = wordToDeque(word.toLowerCase());
		for (int i = 0; i < dq.size() / 2; i++) {
			if (!cc.equalChars(dq.get(i), dq.get(word.length() - 1 - i))) {
				return false;
			}
		}
		return true;
	}
}
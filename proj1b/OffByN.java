public class OffByN implements CharacterComparator {
    //instant variable to control the N value, here call it diff
    private int diff;
    //constructor
    public OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == diff;
    }
}

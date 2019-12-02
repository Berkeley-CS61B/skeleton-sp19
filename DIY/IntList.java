public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public int size() {
        if (rest == null)
            return 1;
        return 1 + rest.size();
    }

    public int get(int i) {
        if (i < 0)
            throw new IllegalArgumentException("i must be greater than 0");
        if (i == 0)
            return first;
        return rest.get(i - 1);
    }
}

package lab4;

public final class Storage<T> {
    private final T value;

    public Storage(T value) {
        this.value = value;
    }

    public T get(T defaultValue) {
        return value != null ? value : defaultValue;
    }
}

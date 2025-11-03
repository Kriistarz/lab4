package lab4;

public class Box<T> {
    private T value;

    public Box() {  }

    public Box(T value) {
        this.value = value;
    }

    public void set(T value) {
        if (this.value != null) {
            throw new IllegalStateException("Коробка уже заполнена.");
        }
        this.value = value;
    }

    public T get() {
        T valueToReturn = value;
        value = null;
        return valueToReturn;
    }

    public boolean isFilled() {
        return value != null;
    }
}

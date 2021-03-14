public final class Box<T> {
    private T element;

    public void putToBox(final T elements) {
        this.element = elements;
    }

    public T getFromBox() {
        return element;
    }

}

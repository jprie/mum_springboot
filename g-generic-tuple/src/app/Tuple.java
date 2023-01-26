package app;

public class Tuple<T> {

    private T l;
    private T r;

    public Tuple(T l, T r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "l=" + l +
                ", r=" + r +
                '}';
    }

    public T getL() {
        return l;
    }

    public void setL(T l) {
        this.l = l;
    }

    public T getR() {
        return r;
    }

    public void setR(T r) {
        this.r = r;
    }
}

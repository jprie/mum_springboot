package app;

public class ObjectTuple {

    private Object l;
    private Object r;

    public ObjectTuple(Object l, Object r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public String toString() {
        return "ObjectTuple{" +
                "l=" + l +
                ", r=" + r +
                '}';
    }

    public Object getL() {
        return l;
    }

    public void setL(Object l) {
        this.l = l;
    }

    public Object getR() {
        return r;
    }

    public void setR(Object r) {
        this.r = r;
    }
}

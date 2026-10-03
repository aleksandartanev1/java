public class DLLNode<E> {
    protected E element;
    protected int brPojavuvanja;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
        this.brPojavuvanja=1;
    }

    @Override
    public String toString() {
        return element.toString() + "(Br. Pojavuvanja: " + this.brPojavuvanja + ")";
    }
}



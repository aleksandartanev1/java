import java.util.Iterator;
import java.util.NoSuchElementException;

public class zadacha13_pomosh_tree {
    // Интерфејс за дрво
    public interface Tree<E> {
        // //////////Accessors ////////////

        public Tree.Node<E> root();

        public Tree.Node<E> parent(Tree.Node<E> node);

        public int childCount(Tree.Node<E> node);

        // //////////Transformers ////////////
        public void makeRoot(E elem);

        public Tree.Node<E> addChild(Tree.Node<E> node, E elem);

        public void remove(Tree.Node<E> node);

        // //////////Iterator ////////////
        public Iterator<E> children(Tree.Node<E> node);

        public Iterator<Node<E>> childrenNodes(Tree.Node<E> node);

        // //////Inner interface for tree nodes ////////
        public interface Node<E> {

            public E getElement();

            public void setElement(E elem);

        }
    }

    public static class SLLTree<E> implements Tree<E> {
        static class SLLNode<P> implements Node<P> {
            SLLNode<P> parent, sibling, firstChild;
            P element;

            public SLLNode(P o) {
                element = o;
                parent = sibling = firstChild = null;
            }
            public P getElement() { return element; }
            public void setElement(P o) { element = o; }
        }

        protected SLLNode<E> root;

        public Node<E> root() { return root; }
        public Node<E> parent(Node<E> node) { return ((SLLNode<E>) node).parent; }

        public int childCount(Node<E> node) {
            SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
            int num = 0;
            while (tmp != null) { tmp = tmp.sibling; num++; }
            return num;
        }

        public void makeRoot(E elem) { root = new SLLNode<>(elem); }

        public Node<E> addChild(Node<E> node, E elem) {
            SLLNode<E> tmp = new SLLNode<>(elem);
            SLLNode<E> curr = (SLLNode<E>) node;
            tmp.sibling = curr.firstChild;
            curr.firstChild = tmp;
            tmp.parent = curr;
            return tmp;
        }

        public void remove(Node<E> node) {
            SLLNode<E> curr = (SLLNode<E>) node;
            if (curr.parent != null) {
                if (curr.parent.firstChild == curr) {
                    curr.parent.firstChild = curr.sibling;
                } else {
                    SLLNode<E> tmp = curr.parent.firstChild;
                    while (tmp.sibling != curr) tmp = tmp.sibling;
                    tmp.sibling = curr.sibling;
                }
            } else root = null;
        }

        class SLLTreeIterator<T> implements Iterator<T> {
            SLLNode<T> current;
            public SLLTreeIterator(SLLNode<T> node) { current = node; }
            public boolean hasNext() { return current != null; }
            public T next() {
                if (current != null) {
                    SLLNode<T> tmp = current;
                    current = current.sibling;
                    return tmp.getElement();
                } else throw new NoSuchElementException();
            }
        }

        public Iterator<E> children(Node<E> node) {
            return new SLLTreeIterator<>(((SLLNode<E>) node).firstChild);
        }

        public Iterator<Node<E>> childrenNodes(Node<E> node) {
            return new Iterator<Node<E>>() {
                SLLNode<E> curr = ((SLLNode<E>) node).firstChild;
                public boolean hasNext() { return curr != null; }
                public Node<E> next() {
                    if (curr == null) throw new NoSuchElementException();
                    SLLNode<E> tmp = curr; curr = curr.sibling; return tmp;
                }
            };
        }

        public void printTree() { printTreeRecursive(root, 0); }
        private void printTreeRecursive(Node<E> node, int level) {
            if (node == null) return;
            for (int i = 0; i < level; i++) System.out.print("  ");
            System.out.println(node.getElement());
            SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
            while (tmp != null) { printTreeRecursive(tmp, level+1); tmp = tmp.sibling; }
        }

        public int countMaxChildren() { return countMaxChildrenRecursive(root); }
        private int countMaxChildrenRecursive(SLLNode<E> node) {
            if (node == null) return 0;
            int t = childCount(node);
            SLLNode<E> tmp = node.firstChild;
            while (tmp != null) {
                t = Math.max(t, countMaxChildrenRecursive(tmp));
                tmp = tmp.sibling;
            }
            return t;
        }
    }

    public static void main(String[] args) {
        SLLTree<String> tree = new SLLTree<>();
        tree.makeRoot("A");

        Tree.Node<String> root = tree.root();
        Tree.Node<String> b = tree.addChild(root, "B");
        Tree.Node<String> c = tree.addChild(root, "C");
        Tree.Node<String> d = tree.addChild(root, "D");

        tree.addChild(b, "E");
        tree.addChild(b, "F");
        tree.addChild(c, "G");

        System.out.println("Структура на дрвото:");
        tree.printTree();

        System.out.println("Коренот има " + tree.childCount(root) + " деца.");
        System.out.println("Максимален број деца: " + tree.countMaxChildren());

        System.out.print("Деца на коренот: ");
        Iterator<String> it = tree.children(root);
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        tree.remove(c);
        System.out.println("После бришење на C:");
        tree.printTree();
    }
}

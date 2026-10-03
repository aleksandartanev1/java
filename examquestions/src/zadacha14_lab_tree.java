import java.util.*;

public class zadacha14_lab_tree {
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

            public P getElement() {
                return element;
            }

            public void setElement(P o) {
                element = o;
            }
        }

        protected SLLNode<E> root;

        public Node<E> root() {
            return root;
        }

        public Node<E> parent(Node<E> node) {
            return ((SLLNode<E>) node).parent;
        }

        public int childCount(Node<E> node) {
            SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
            int num = 0;
            while (tmp != null) {
                tmp = tmp.sibling;
                num++;
            }
            return num;
        }

        public void makeRoot(E elem) {
            root = new SLLNode<>(elem);
        }

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

            public SLLTreeIterator(SLLNode<T> node) {
                current = node;
            }

            public boolean hasNext() {
                return current != null;
            }

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

                public boolean hasNext() {
                    return curr != null;
                }

                public Node<E> next() {
                    if (curr == null) throw new NoSuchElementException();
                    SLLNode<E> tmp = curr;
                    curr = curr.sibling;
                    return tmp;
                }
            };
        }

        public void printTree() {
            printTreeRecursive(root, 0);
        }

        private void printTreeRecursive(Node<E> node, int level) {
            if (node == null) return;
            for (int i = 0; i < level; i++) System.out.print("  ");
            System.out.println(node.getElement());
            SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
            while (tmp != null) {
                printTreeRecursive(tmp, level + 1);
                tmp = tmp.sibling;
            }
        }

        public int countMaxChildren() {
            return countMaxChildrenRecursive(root);
        }

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


//racno pishuvano
    public int countLeaves(Node<E> node) {
            if(node == null) {return 0;}
            SLLNode<E> current = (SLLNode<E>) node;
            if (current.firstChild == null) {return 1;}
            int total = 0;
            SLLNode<E> temp = current.firstChild;
            while (temp != null) {
                total+=countLeaves(temp);
                temp = temp.sibling;
            }

            return total;
    }

    }





        public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        String root = inp.next();
        SLLTree<String> tree = new SLLTree<>();
        tree.makeRoot(root);

        Map<String, Tree.Node<String>> map = new HashMap<>();
        map.put(root, tree.root());

        for(int i = 0; i < n - 1; i++) {
            String parentname = inp.next();
            String childname = inp.next();
            Tree.Node<String> parentnode = map.get(parentname);
            Tree.Node<String> childnode = tree.addChild(parentnode, childname);
            map.put(childname, childnode);
        }

        int m = inp.nextInt();
        for(int i = 0; i < m; i++) {
            String query = inp.next();
            Tree.Node<String> querynode = map.get(query);
            System.out.println(tree.countLeaves(querynode));
        }
    }
}

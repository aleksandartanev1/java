import java.util.NoSuchElementException;
import java.util.Stack;

public class zadacha12_pomosh_btree {
    public static class BNode<E> {

        public E info;
        public BNode<E> left;
        public BNode<E> right;

        static int LEFT = 1;
        static int RIGHT = 2;

        public BNode<E> parent;

        public BNode(E info, BNode<E> parent) {
            this.parent = parent;
            this.info = info;
            left = null;
            right = null;
        }

        public BNode(E info) {
            this.parent = null;
            this.info = info;
            left = null;
            right = null;
        }

        public BNode(E info, BNode<E> left, BNode<E> right) {
            this.info = info;
            this.left = left;
            this.right = right;
        }
    }

    static class ArrayStack<E> extends Stack<E> {
        private E[] elems;
        private int depth;

        @SuppressWarnings("unchecked")
        public ArrayStack (int maxDepth) {
            // Konstrukcija na nov, prazen stek.
            elems = (E[]) new Object[maxDepth];
            depth = 0;
        }


        public boolean isEmpty () {
            // Vrakja true ako i samo ako stekot e prazen.
            return (depth == 0);
        }


        public E peek () {
            // Go vrakja elementot na vrvot od stekot.
            if (depth == 0)
                throw new NoSuchElementException();
            return elems[depth-1];
        }


        public void clear () {
            // Go prazni stekot.
            for (int i = 0; i < depth; i++)  elems[i] = null;
            depth = 0;
        }


        public E push (E x) {
            // Go dodava x na vrvot na stekot.
            elems[depth++] = x;
            return x;
        }


        public E pop () {
            // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
            if (depth == 0)
                throw new NoSuchElementException();
            E topmost = elems[--depth];
            elems[depth] = null;
            return topmost;
        }
    }

    public static class BTree<E> {

        public BNode<E> root;

        public BTree() {
            root = null;
        }

        public BTree(E info) {
            root = new BNode<E>(info);
        }

        public void makeRoot(E elem) {
            root = new BNode<E>(elem);
        }

        public BNode<E> addChild(BNode<E> node, int where, E elem) {

            BNode<E> tmp = new BNode<E>(elem, node);

            if (where == BNode.LEFT) {
                if (node.left != null)  // veke postoi element
                    return null;
                node.left = tmp;
            } else {
                if (node.right != null) // veke postoi element
                    return null;
                node.right = tmp;
            }

            return tmp;
        }

        public void inorder() {
            System.out.print("INORDER: ");
            inorderR(root);
            System.out.println();
        }

        public void inorderR(BNode<E> n) {
            if (n != null) {
                inorderR(n.left);
                System.out.print(n.info.toString()+" ");
                inorderR(n.right);
            }
        }

        public void preorder() {
            System.out.print("PREORDER: ");
            preorderR(root);
            System.out.println();
        }

        public void preorderR(BNode<E> n) {
            if (n != null) {
                System.out.print(n.info.toString()+" ");
                preorderR(n.left);
                preorderR(n.right);
            }
        }

        public void postorder() {
            System.out.print("POSTORDER: ");
            postorderR(root);
            System.out.println();
        }

        public void postorderR(BNode<E> n) {
            if (n != null) {
                postorderR(n.left);
                postorderR(n.right);
                System.out.print(n.info.toString()+" ");
            }
        }

        public void inorderNonRecursive() {
            ArrayStack<BNode<E>> s = new ArrayStack<BNode<E>>(100);
            BNode<E> p = root;
            System.out.print("INORDER (nonrecursive): ");

            while (true) {
                // pridvizuvanje do kraj vo leva nasoka pri sto site koreni
                // na potstebla se dodavaat vo magacin za podocnezna obrabotka
                while (p != null) {
                    s.push(p);
                    p = p.left;
                }

                // ako magacinot e prazen znaci deka stebloto e celosno izminato
                if (s.isEmpty())
                    break;

                p = s.peek();
                // pecatenje (obrabotka) na jazelot na vrvot od magacinot
                System.out.print(p.info.toString()+" ");
                // brisenje na obraboteniot jazel od magacinot
                s.pop();
                // pridvizuvanje vo desno od obraboteniot jazel i povtoruvanje na
                // postapkata za desnoto potsteblo na jazelot
                p = p.right;

            }
            System.out.println();

        }

        int insideNodesR(BNode<E> node) {
            if (node == null)
                return 0;

            if ((node.left == null)&&(node.right == null))
                return 0;

            return insideNodesR(node.left) + insideNodesR(node.right) + 1;
        }

        public int insideNodes() {
            return insideNodesR(root);
        }

        int leavesR(BNode<E> node) {
            if (node != null) {
                if ((node.left == null)&&(node.right == null))
                    return 1;
                else
                    return (leavesR(node.left) + leavesR(node.right));
            } else {
                return 0;
            }
        }

        public int leaves() {
            return leavesR(root);
        }

        int depthR(BNode<E> node) {
            if (node == null)
                return 0;
            if ((node.left == null)&&(node.right == null))
                return 0;
            return (1 + Math.max(depthR(node.left), depthR(node.right)));
        }

        public int depth() {
            return depthR(root);
        }

        void mirrorR(BNode<E> node) {
            BNode<E> tmp;

            if (node == null)
                return;

            // simetricno preslikuvanje na levoto i desnoto potsteblo
            mirrorR(node.left);
            mirrorR(node.right);

            // smena na ulogite na pokazuvacite na momentalniot jazel
            tmp = node.left;
            node.left = node.right;
            node.right = tmp;

        }

        public void mirror() {
            mirrorR(root);
        }
    }


    public static void main(String[] args) {
        // Креирај дрво со корен 1
        BTree<Integer> tree = new BTree<>(1);

        // Додај лев и десен син на коренот
        BNode<Integer> left = tree.addChild(tree.root, BNode.LEFT, 2);
        BNode<Integer> right = tree.addChild(tree.root, BNode.RIGHT, 3);

        // Додај деца на левиот син
        tree.addChild(left, BNode.LEFT, 4);
        tree.addChild(left, BNode.RIGHT, 5);

        // Додај дете на десниот син
        tree.addChild(right, BNode.RIGHT, 6);

        // Обиколки
        tree.inorder();            // INORDER: 4 2 5 1 3 6
        tree.preorder();           // PREORDER: 1 2 4 5 3 6
        tree.postorder();          // POSTORDER: 4 5 2 6 3 1
        tree.inorderNonRecursive();// INORDER (nonrecursive): 4 2 5 1 3 6

        // Анализа
        System.out.println("Leaves: " + tree.leaves());           // 3 (4,5,6)
        System.out.println("Inside nodes: " + tree.insideNodes()); // 3 (1,2,3)
        System.out.println("Depth: " + tree.depth());             // 2

        // Симетрично пресликување
        tree.mirror();
        tree.inorder(); // INORDER после mirror: 6 3 1 5 2 4
    }



}
import org.w3c.dom.Node;

import java.util.*;
import java.util.Queue;
public class zadacha15_lab_btree {
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

    public static void assignDepth(Map<Integer, Integer> themap, int depth, BNode<Integer> thenode) {
        if (thenode == null) return;
        themap.put(thenode.info, depth);
        assignDepth(themap, depth+1, thenode.left);
        assignDepth(themap, depth+1, thenode.right);
    }

    public static int oddDepthSum(Map<Integer, Integer> themap, BNode<Integer> thenode) {
        int sum = 0;
        Queue<BNode<Integer>> thequeue = new LinkedList<>();
        thequeue.add(thenode);
        while (!thequeue.isEmpty()) {
            BNode<Integer> current = thequeue.poll();
            if (themap.get(current.info) % 2 == 1) {
                sum+=current.info;
            }
            if (current.left != null) thequeue.add(current.left);
            if (current.right !=null) thequeue.add(current.right);
        }
        return sum;
    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int root = input.nextInt();
        BTree<Integer> thetree = new BTree<>(root);
        Map<Integer, BNode<Integer>> nodemap = new HashMap<>();
        nodemap.put(root, thetree.root);

        for (int i = 0; i < n - 1; i++) {
            int parentnumber = input.nextInt();
            int childnumber = input.nextInt();
            String direction = input.next();
            BNode<Integer> child = null;
            BNode<Integer> parent = nodemap.get(parentnumber);
            if (parent == null) {
                parent = new BNode<>(parentnumber);
            }
            if(Objects.equals(direction, "LEFT")) {
                child = thetree.addChild(parent, BNode.LEFT, childnumber);
            }
            else if(Objects.equals(direction, "RIGHT")) {
                child = thetree.addChild(parent, BNode.RIGHT, childnumber);
            }
            nodemap.put(childnumber, child);
        }

        Map<Integer, Integer> depthmap = new HashMap<>();
        assignDepth(depthmap, 0, thetree.root);

        int maxsum = Integer.MIN_VALUE;
        Queue<BNode> thequeue = new LinkedList<>();
        thequeue.add(thetree.root);
        while (!thequeue.isEmpty()) {
            BNode<Integer> current = thequeue.poll();
            int sum = oddDepthSum(depthmap, current);
            maxsum = Math.max(maxsum, sum);
            if (current.left != null) thequeue.add(current.left);
            if (current.right !=null) thequeue.add(current.right);
        }

        System.out.println(maxsum);
    }

}



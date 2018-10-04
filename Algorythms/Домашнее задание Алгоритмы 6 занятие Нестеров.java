public class Garden {
    public static void main(String[] args) {

        int NUMBER_OF_TREES = 100;
        int balanced = 0;

        Tree[] trees = new Tree[NUMBER_OF_TREES];

        for (int i = 0; i < trees.length; i++) {
            trees[i] = new Tree();
            for (int j = 0; j < 63; j++) {                      // 63 - это 6 полностью заполненных уровней.
                int volume = (int) (Math.random() * 20 - 100);
                Element element = new Element(volume);
                trees[i].insert(element);
            }
            boolean a =  trees[i].isBalanced();
            if (a) {
                balanced++;
            }
            System.out.println(" Дерево " + (i + 1) + " сбалансировано? " + a + " \n");
        }
        System.out.println(" Процент сбалансированных деревьев = " + " "+ ((double)balanced/NUMBER_OF_TREES) * 100);

    }
}

public class Tree {
    private TreeNode root;
    int leftDepth = 0;
    int rightDepth = 0;

    public boolean isBalanced() {   //  Сбалансировано ли дерево? Добавлены значения глубины левой и правой половин
        TreeNode left = root.left;
        findDepthLeft(left);
        TreeNode right = root.right;
        findDepthRight(right);
        System.out.println(" left " + leftDepth + " right " + rightDepth);
        if (Math.abs(leftDepth - rightDepth) <= 1) {
            return true;
        } else return false;
    }

    public void findDepthLeft(TreeNode current) {          // Метод, обходящий левую половину дерева в поисках максимального уровня
        if (current != null) {
            if (current.depth > leftDepth) {
                leftDepth = current.depth;
            }
            findDepthLeft(current.left);
            findDepthLeft(current.right);
        }
    }

    public void findDepthRight(TreeNode current) {          // Метод, обходящий правую половину  дерева в поисках максимального уровня
        if (current != null) {
            if (current.depth > rightDepth) {
                rightDepth = current.depth;
            }
            findDepthRight(current.left);
            findDepthRight(current.right);
        }
    }

    public void insert(Element p) {
        TreeNode node = new TreeNode(p);
        if (root == null) {
            root = node;
            root.depth = 0;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (p.volume < current.p.volume) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        parent.left.depth = (parent.depth + 1);         //  Увеличиваем значение уровня
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        parent.right.depth = (parent.depth + 1);        //  Увеличиваем значение уровня
                        return;
                    }
                }
            }
        }
    }
}

public class TreeNode {
    Element p;

    public TreeNode left;
    public TreeNode right;
    public int depth;                   // Добавлена характеристика глубины ноды.

    TreeNode(Element p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return String.format("(ID: %d;volume: %d; level: %d)",      // В вывод добавлен уровень.
                p.uid, p.volume, this.depth);

    }
}

public class Element {

    private static int id = 0;
    int volume;
    int uid;

    public Element(int volume) {
        this.volume = volume;
        this.uid = ++id;
    }

    @Override
    public String toString() {
        return String.format(" Element ID: %d;  volume: %d",
                uid, volume);
    }
}
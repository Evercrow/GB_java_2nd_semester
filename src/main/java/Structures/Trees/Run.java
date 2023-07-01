package Structures.Trees;

public class Run {
    public static void main(String[] args) {
        LeftSidedRedBlackTree rbtree = new LeftSidedRedBlackTree();
        for (int i = 1; i <= 21; i+=2){
            rbtree.add(i);
        }
        // rbtree.printDepth();
        rbtree.printWidth();
        LeftSidedRedBlackTree.Node m = rbtree.find(7);
        // System.out.println(rbtree.findMaxChild(m).value);
    }
}

package Structures.Trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Красно-Черное дерево - это сбалансированное бинарное дерево, узлы которого хранят дополнительно бит о черном или красном цвете,
 * за счет чего осуществляется логика балансировки по следующим правилам:
 * 1)корень всегда черный
 * 2)дети красного узла всегда черные
 * 3)новый элемент всегда красный
 *доп. правило левостороннего красно-черного дерева - красный ребенок может быть только слева
 * 4) красный узел может быть только слева.
 * Также должны выполнятся общие свойства бинарных деревье:
 * 1)разница глубины правой и левой ветви дерева не больше 1;
 * 2)левый ребенок меньше родителя, а правый больше(по значению)
 * 3)асимптотика поиска O(log N)
 * 4)все значения узлов уникальны
 */
public class LeftSidedRedBlackTree {
    Node root;
    public class Node{
        int value;
        Color color;
        Node left;
        Node right;


        public void  show() {
            System.out.printf("\n%d %s \n",this.value,this.color);

            if(this.left != null){
                System.out.printf("L:%d ", this.left.value);
            }
            if(this.right != null){
                System.out.printf("R:%d",this.right.value);
            }
        }
    }
    private enum Color{ RED , BLACK}
    public Node find(int value){ // O(log N)
        Node cur = root;
        while(cur != null){
            if(cur.value == value){
                return cur;
            }
            if(cur.value < value){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }
        return null;
    }


    public void printDepth(){
        root.show();
        System.out.println("\nleft branch");
        printD(root.left);
        System.out.println("right branch");
        printD(root.right);
    }

    private void printD(Node node){
        if(node == null){
            return;
        }
        node.show();
        printD(node.left);
        printD(node.right);
    }
    public void printWidth(){
       List<Node> line = new ArrayList<>();
       line.add(root);
       int depth = 0;
        while(line.size()>0){
            List<Node> futureLine = new ArrayList<>();
            System.out.printf("level %d :",depth++);
            for ( Node node : line ){
                if(node != null){
                    System.out.printf(" %d %s ", node.value, node.color);
                    if(node.left != null){
                        System.out.printf("L:%d ", node.left.value);
                    }
                    if(node.right != null){
                        System.out.printf("R:%d",node.right.value);
                    }
                    System.out.print("\t");
                    futureLine.add(node.left);
                    futureLine.add(node.right);
                }
            }
            System.out.println();
            line = futureLine;
        }
    }

    public boolean remove(int val){
        if(root != null){

            boolean success = delNode(root,val);

            return success;

        } else {
            return false;
        }
    }

    public Node findMaxChild(Node node){
        Node max = node;
        while(node.right.right != null){
            max = findMaxChild(node.right) ;
        }
        return max;
    }

    private boolean delNode(Node node, int val){
        if(node.left.value == val ){
            return false; //элемент неуникален, добавить не удалось
        } else if(node.value > val){ //ищем пустой узел рекурсией, беря направление спуска через сравнение значений
            if(node.left != null){
                boolean unique = newNode(node.left , val);
                node.left =  checkBalance(node.left);
                // спускаясь по ветке, заодно проверяем балансировку. Такой подход позволяет не хранить информацию о родителях в узлах
                return unique;
            } else {
                node.left = new Node();
                node.left.value = val;
                node.left.color = Color.RED;
                return true;
            }
        } else {
            if(node.right != null){
                boolean unique = newNode(node.right , val);
                node.right =  checkBalance(node.right);
                return unique;
            } else {
                node.right = new Node();
                node.right.value = val;
                node.right.color = Color.RED;
                return true;
            }

        }
    }

    public boolean add(int val){
        if(root == null){
            root = new Node();
            root.value = val;
            root.color = Color.BLACK;
            return true;
        } else {
            boolean success = newNode(root,val);
            root = checkBalance(root);
            root.color = Color.BLACK;
            return success;
        }
    }

    /**
     * рекурсивный метод для поиска места для вставки нового значения
     * @param node - текущая нода рекурсии
     * @param val - значения для вставки
     * @return
     */
    private boolean newNode(Node node, int val){
        if(node.value == val ){
            return false; //элемент неуникален, добавить не удалось
        } else if(node.value > val){ //ищем пустой узел рекурсией, беря направление спуска через сравнение значений
            if(node.left != null){
                boolean unique = newNode(node.left , val);
                node.left =  checkBalance(node.left);
                // спускаясь по ветке, заодно проверяем балансировку. Такой подход позволяет не хранить информацию о родителях в узлах
                return unique;
            } else {
                node.left = new Node();
                node.left.value = val;
                node.left.color = Color.RED;
                return true;
            }
        } else {
            if(node.right != null){
                boolean unique = newNode(node.right , val);
                node.right =  checkBalance(node.right);
                return unique;
            } else {
                node.right = new Node();
                node.right.value = val;
                node.right.color = Color.RED;
                return true;
            }
            
        }
    }

    /**
     * метод, проверяющий правила красно-черного дерева, и вызывающий нужные для балансировки операции
     * @param node - текущая нода в рекурсии
     * @return
     */
    private Node checkBalance(Node node ){
        Node changedNode = node;
        boolean wrongBalance ;
        do {
            wrongBalance = false;
            //правый поворот, когда правый родитель красный, а левый нет или пуст
            if( (changedNode.right != null && changedNode.right.color == Color.RED ) &&
                    (changedNode.left == null || changedNode.left.color ==Color.BLACK)){
                wrongBalance = true;
                changedNode = counterclockTurn(changedNode);
            }
            //левый поворот, когда у красного левого родителя есть красный ребенок
            if( (changedNode.left != null && changedNode.left.color == Color.RED) &&
                    (changedNode.left.left != null && changedNode.left.left.color == Color.RED)){
                wrongBalance = true;
                changedNode = clockwiseTurn(changedNode);
            }
            // оба красных ребенка
            if((changedNode.right != null && changedNode.right.color == Color.RED ) &&
                    (changedNode.left != null && changedNode.left.color == Color.RED )){
                wrongBalance = true;
                swapColor(changedNode);
            }

        }while(wrongBalance); // запускаем еще раз проверку уже по изменившемуся узлу

        return changedNode;
    }

    /**
     * левый родитель на место "деда", "дед" на место правого родителя,
     * правый ребенок с левой ветви становится левым ребенком правой ветви.
     * @param grandpa - в аргументе ссылка на "дедушку", затрагивает 4 ноды(глубина 2)
     * @return
     */
    private Node clockwiseTurn(Node grandpa){
        Node leftParent = grandpa.left;
        Node swapChild = leftParent.right;

        leftParent.color = grandpa.color;
        leftParent.right = grandpa;
        grandpa.left = swapChild;
        grandpa.color = Color.RED;
        return leftParent; // возвращаем для подстановки в цепь рекурсии левого родителя вместо дедушки
    }

    /**
     * операция левостороннего перевода, когда родитель и левый ребенок красные.
     *
     * @param parent в аргументе ссылка на родителя, затрагивает 3 ноды(глубина 1)
     */
    private void swapColor(Node parent){
        parent.color = Color.RED;
        parent.left.color = Color.BLACK;
        parent.right.color = Color.BLACK;
    }

    /**
     * правый родитель на место "деда", "дед" на место левого родителя,
     *  левый ребенок с правой ветви становится правым ребенком левой ветви.
     * @param grandpa - в аргументе ссылка на "дедушку", затрагивает 4 ноды
     * @return
     */
    private Node counterclockTurn(Node grandpa){
        Node rightParent = grandpa.right;
        Node swapChild = rightParent.left;

        rightParent.color = grandpa.color;
        rightParent.left = grandpa;
        grandpa.right = swapChild;
        grandpa.color = Color.RED;
        return rightParent;
    }


}

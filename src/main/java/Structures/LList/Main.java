package Structures.LList;

public class Main {
    public static void main(String[] args) {
        OneWayList<Integer> simpleList = new OneWayList<Integer>();

        for (int i = 0; i < 6; i++){
            simpleList.addFirst(i);
        }
        System.out.println("Testing adding to head");
        System.out.println(simpleList);
        System.out.printf("5 is in: %b \n",simpleList.contains(5));
        System.out.println("Testing removing from head");
        simpleList.delFirst();
        simpleList.delFirst();
        System.out.println(simpleList);
        System.out.printf("5 is in: %b \n",simpleList.contains(5));
        System.out.println("Testing adding to tail");
        simpleList.addLast(-1);
        simpleList.addLast(-2);
        System.out.println(simpleList);
        System.out.println("Testing removing from tail");
        simpleList.delLast();
        simpleList.delLast();
        System.out.println(simpleList);
        System.out.println("testing turnaround");
        simpleList.turnAround();
        System.out.println(simpleList);

    }
}

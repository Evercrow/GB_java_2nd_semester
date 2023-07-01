package Structures.LList;

public class OneWayList <T>{
    Node<T> head;
    protected static class Node<T>{
        T value;
        Node<T> next;

    }

    public void addFirst(T val){
        Node<T> n = new Node<T>();
        n.value = val;
        n.next = head;
        head = n;
    }

    public void delFirst(){
        if(head != null){
            head = head.next;
        }
    }

    public boolean contains(T val){
        Node<T> current = this.head;
        while(current != null){
            if(current.value.equals(val)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void addLast(T val){
        Node<T> n = new Node<T>();
        n.value = val;
        if(head == null){
            head = n;
        }else {
            Node<T> cur = head;
            while(cur.next !=null){
                cur = cur.next;
            }
            cur.next = n;
        }
    }

    public void delLast(){
        if(head != null){
            Node<T> last = head;
            while(last.next != null){
                if(last.next.next == null){
                    last.next = null;
                    return;
                }
                last = last.next;
            }
            // и частный случай из 1-го элемента, просто обнуляем голову
            head = null;

        }
    }

    public void turnAround(){
        if(head != null && head.next != null  ){ // если список пуст или из 1 головы, то ничего не делаем
            Node<T> cur = head;
            Node<T> prev = null;
            Node<T> future = head.next;
            while(future != null){ // проходимся по всем тройкам нод, запоминая ссылки и подменяя ссылку, хранимую в текущем
                cur.next = prev;

                prev = cur;
                cur = future;
                future = cur.next;
            }
            // мы находимся текущим узлом в конце, осталось подменить ссылку в нем и назначить хвост головой списка
            cur.next = prev;
            head = cur;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = this.head;
        while(current != null){
            sb.append(current.value.toString());
            sb.append("; ");
            current = current.next;
        }

        return sb.toString();
    }
}

public class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public void pushFront(int data) {
        Node nuevo = new Node (data);
        if (isEmpty ()){
            head = nuevo;
        }else{
            nuevo.next = head;
            head = nuevo;
        }
            size++;
    }

    public void pushBack(int data) {

    }

    public int popFront() {
        if (isEmpty ()) return -1;

        Node out = head;
        head = head.next;
        out.next = null;

        size--;
        return out.data; 
    }

    public int popBack() {
        if(isEmpty())return -1;
        
        Nodo current = head;
        
        while(current.next.next != null){
            current = current.next;
        }
        
        int data = current.next.data;
        
        current.next = null
        size--;
        return data; 
    }

    public Node find(int data) { 
        return null; 
    }

    public void erase(Node node) {
    }

    public void addBefore(Node node, int data) {

    }

    public void addAfter(Node node, int data) {
    }

    public boolean isEmpty() { 
        return head == null; 
    }
    public int topFront() { return head != null ? head.data : -1; }
    public int topBack() { return -1; }

    public int size() {
        return size; 
    }
}

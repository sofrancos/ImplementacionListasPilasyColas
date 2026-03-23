public class DoublyLinkedListWithTail {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedListWithTail() {
        head = null;
        tail = null;
        size = 0;
    }

    public void pushFront(int data) { 
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    public void pushBack(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public int popFront() { 
        if (head == null) return -1;
        int data = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null; 
        }
        size--;
        return data; 
    }
    public int popBack() { 
        if (tail == null) return -1;
        int data = tail.data;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return data;
    }
    public Node find(int data) { 
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return current;
            }
            current = current.next;
        }
        return null;
     }
    public void erase(Node node) {
        if (node == null) return;
        if (node == head) {
            popFront();
            return;
        }
        if (node == tail) {
            popBack();
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }
    public void addBefore(Node node, int data) { 
        if (node == null) return;
        if (node == head) {
            pushFront(data);
        } else {
            Node newNode = new Node(data);
            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
            size++;
        }
    }
    public void addAfter(Node node, int data) {
        if (node == null) return;
        if (node == tail) {
            pushBack(data);
        } else {
            Node newNode = new Node(data);
            newNode.prev = node;
            newNode.next = node.next;
            node.next.prev = newNode;
            node.next = newNode;
            size++;
        }
    }
    public boolean isEmpty() { 
        return head == null;
    }
    public int topFront() { 
        if (head == null) {
            return -1; 
        }
        return head.data;
    }
    public int topBack() { 
        if (tail == null) {
            return -1;
        }
        return tail.data;  
    }
    public int size() { 
        return size; 
    }
}

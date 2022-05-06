public class HashTable {
    private Node[] buckets;
    private int numOfBuckets;
    private int size;

    public HashTable() {
        this(6);
    }

    public HashTable(int capacity){
        this.numOfBuckets = capacity;
        this.buckets = new Node[numOfBuckets];
        this.size = 0;
    }

    private class Node {
        private Integer key;
        private String value;
        private Node next;

        public Node(Integer key, String value){
            this.key = key;
            this.value = value;
        }
    }

    public int size(){
        return size;
    }

    public void add(Integer key, String value){
        int bucketIndex = getBucketIndex(key);
        Node head = buckets[bucketIndex];
        while(head != null){
            if(head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = buckets[bucketIndex];
        Node node = new Node(key, value); // (key, value) -> null
        node.next = head;
        buckets[bucketIndex] = node;
    }

    private int getBucketIndex(Integer key){
        return key % buckets.length;
    }

    public String get(Integer key){
        int bucketIndex = getBucketIndex(key);
        Node head = buckets[bucketIndex];
        while(head != null){
            if(head.key.equals(key)){
                return head.value;
            }
            head = head.next;
        }
        return "This bucket is empty!";
    }

    public String remove(Integer key){
        int bucketIndex = getBucketIndex(key);
        Node head = buckets[bucketIndex];
        Node previous = null;

        while(head != null){
            if(head.key.equals(key)){
                break;
            }
            previous = head;
            head = head.next;
        }
        if(head == null){
            return null;
        }
        size--;
        if(previous != null){
            previous.next = head.next;
        } else {
            buckets[bucketIndex] = head.next;
        }

        return head.value;
    }


    public static void main(String[] args) {
        HashTable table = new HashTable(6);
        table.add(211451, "Sabina");
        table.add(216983, "Islam");
        table.add(215632, "Daniyar");
        table.add(214534, "Tolganay");
        table.add(212345, "Zhanel");
        table.add(219876, "Abzal");
        System.out.println(table.size());
        System.out.println(table.get(211451));
        System.out.println(table.remove(216983));
        System.out.println(table.get(216983));
        System.out.println(table.size());
    }

}
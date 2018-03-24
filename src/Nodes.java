import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class Nodes {

    private ArrayList<Node> list = new ArrayList<>();
    private Data data = null;

    public Nodes(int size) {
        list = new ArrayList<>(size);
    }

    public Nodes() {
        this(0);
    }

    public Data getData() {
        return data;
    }

    public boolean hasData() { return data != null; }

    public void addData(Data data) {
        if (hasData()) {
            throw new IllegalStateException("The list of Nodes already belongs to data.");
        }
        this.data = data;
    }

    public Data removeData() {
        if (!hasData()) {
            throw new IllegalStateException("The list of Nodes doesn't belong to data.");
        }
        Data d = data;
        this.data = null;
        return d;
    }

    public Node get(int i) {
        return list.get(i);
    }

    public void add(Node n) {
        list.add(n);
        n.addList(this);
    }

    public void addAll(Nodes ns) {
        list.addAll(ns.list);
    }

    public Node remove(int i) {
        Node n = list.get(i);
        list.remove(i);
        n.removeList();
        return n;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Node n) {
        return list.contains(n);
    }

    public int indexOf(Node n) {
        return list.indexOf(n);
    }

    public Nodes subList(int i1, int i2) {
        Nodes ns = new Nodes();
        for (int i = 0; i < i2-i1; i++) {
            ns.add(this.get(i1 + i));
        }
        return ns;
    }

    public Nodes filter(Predicate<Node> p) {
        Nodes ns = new Nodes();
        for (int i = 0; i < size(); i++) {
            if (p.test(get(i))) {
                ns.add(new Node(null, get(i).getId(), get(i).getKind(), get(i).getName(), get(i).getComposite(), get(i).getStereotype(), get(i).getAlignment()));
            }
        }
        return ns;
    }

    public void sortById() {
        list.sort(Comparator.comparing(Node::getId));
    }

    public void sortByKind() {
        list.sort(Comparator.comparing(Node::getKind));
    }

    public void sortByName() {
        list.sort(Comparator.comparing(Node::getName));
    }

    public void print() {
        System.out.println("List of " + size() + " nodes: \n");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).print();
        }
    }






}

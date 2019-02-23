import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class Tester {

    private static class Node {
        String val;
        List<Node> friends;

        Node(String theVal) {
            val = theVal;
        }

        @Override
        public String toString() {
            return "Node {" + val + "}";
        }
    }

    public List<Node> findFriends(Node root) {

        if (root == null) {
            return new ArrayList<>(0); // default capacity is 10
        }

        Set<Node> herFriends = new HashSet<>(root.friends);
        herFriends.add(root);
        Set<Node> newFriends = new HashSet<>();
        Queue<Node> nodesToCheck = new LinkedList<>();
        nodesToCheck.offer(root);

        int layer = 0;
        while (!nodesToCheck.isEmpty() && layer < 3) {
            int size = nodesToCheck.size();
            for(int i = 0; i < size; i++) {
                Node curr = nodesToCheck.poll();
                for (Node friend : curr.friends) {
                    if (!herFriends.contains(friend) && !newFriends.contains(friend)) {
                        newFriends.add(friend);
                    }
                    nodesToCheck.add(friend);
                }
            }
            layer++;
        }

        return new ArrayList<>(newFriends);
    }


    public static void main(String[] args) {
        Tester t = new Tester();
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        a.friends = Arrays.asList(b, c, d, e);
        b.friends = Arrays.asList(f, g);
        c.friends = Arrays.asList();
        d.friends = Arrays.asList(a, e);
        e.friends = Arrays.asList(a, d);
        f.friends = Arrays.asList(b);
        g.friends = Arrays.asList(b);

        System.out.println(t.findFriends(a));


    }
}

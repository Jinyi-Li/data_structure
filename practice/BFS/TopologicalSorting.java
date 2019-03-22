/**
 * Definition for Directed Acyclic Graph.
 * class Node {
 *     int label;
 *     ArrayList<Node> neighbors;
 *     Node(int x) { label = x; neighbors = new ArrayList<Node>(); }
 * };
 */
/*
    思路：in-degree matrix + BFS
    1. Generate in-degree matrix and initialize an empty List<Node> as the result list.
    2. Initialize BFS queue by offering all nodes with 0 in-degree.
    3. Traverse the graph using BFS; for each node polled from queue, add it to result list, subtract the in-degree of its neighbors by 1 ('cause its parent is removed from graph), add the neighbor to queue if its in-degree therefore becomes 0.
    4. Return the result list.
*/
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<Node> topSort(ArrayList<Node> graph) {        
        ArrayList<Node> result = new ArrayList<Node>();
        Map<Node, Integer> map = new HashMap();
        for (Node node : graph) {
            for (Node neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1); 
                }
            }
        }
        
        Queue<Node> q = new LinkedList<Node>();
        for (Node node : graph) {
            if (!map.containsKey(node)) {
                q.offer(node);
                result.add(node);
            }
        }
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Node n : node.neighbors) {
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) {
                    result.add(n);
                    q.offer(n);
                }
            }
        }
        return result;
    }
}
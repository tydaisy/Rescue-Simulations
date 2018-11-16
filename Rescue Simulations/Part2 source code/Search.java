package Part2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Search {
    // private static String response;
    private static PriorityQueue<Node> frontier;
    private static char[][] map;
    private static ArrayList<Node> exploredNode;
    private static ArrayList<Node> path;

    private static int maxY = 10;
    private static int maxX = 10;

    public Search(char[][] m) {
        map = m;
    }

    public void searchStep(Node initialNode, String response1, String response3, Node goalNode) {
        // add the initial node into the frontier queue, explored list
        frontier = new PriorityQueue<Node>();
        exploredNode = new ArrayList<Node>();

        System.out.println("1. the initial size of frontier is: " + frontier.size()
                + "; the head element of frontier is: " + frontier.peek());
        frontier.add(initialNode);
        System.out.println("2. the size of frontier is: " + frontier.size() + "; the head element of frontier is: "
                + frontier.peek());
        initialNode.setParentNode(initialNode);
        initialNode.setFCost(goalNode, response1, response3); // the path cost
                                                              // of initial node
                                                              // is 0 when use
                                                              // A* algorithm

        while (!frontier.isEmpty()) {
            System.out.println("3. the size of frontier is: " + frontier.size() + "; the head element of frontier is: "
                    + frontier.peek());
            Node node = frontier.poll();
            System.out.println("### current node ###" + node);
            System.out.println("4. the size of frontier is: " + frontier.size() + "; the head element of frontier is: "
                    + frontier.peek());

            exploredNode.add(node);
            if (node.equals(goalNode)) {
                goalNode.setParentNode(node.getParentNode());
                break;
            }
            expand(initialNode, node, goalNode, response1, response3);
            System.out.println("### current node ###" + node);
            System.out.println("5. the size of frontier is: " + frontier.size() + "; the head element of frontier is: "
                    + frontier.peek());
            System.out.println();
        }
    }

    private static void expand(Node initialNode, Node node, Node goalNode, String response1, String response3) {
        // check if the current node can go South
        if (node.getState().getY() + 1 < maxY) {
            System.out.println("South");
            // check if the next position is the position of the one of
            // Xs
            if (map[node.getState().getY() + 1][node.getState().getX()] != 'X') {
                State childNodeState = new State(node.getState().getX(), node.getState().getY() + 1);
                Node childNode = new Node(childNodeState);
                check(initialNode, node, childNode, goalNode, response1, response3);
            }
        }

        // check if the current node can go east
        if (node.getState().getX() + 1 < maxX) {
            System.out.println("East");
            if (map[node.getState().getY()][node.getState().getX() + 1] != 'X') {
                State childNodeState = new State(node.getState().getX() + 1, node.getState().getY());
                Node childNode = new Node(childNodeState);
                check(initialNode, node, childNode, goalNode, response1, response3);
            }
        }

        // check if the current node can go North
        if (node.getState().getY() > 0) {
            System.out.println("North");
            if (!(map[node.getState().getY() - 1][node.getState().getX()] == 'X')) {
                State childNodeState = new State(node.getState().getX(), node.getState().getY() - 1);
                Node childNode = new Node(childNodeState);
                check(initialNode, node, childNode, goalNode, response1, response3);
            }
        }

        // check if the current node can go West
        if (node.getState().getX() > 0) {
            System.out.println("West");
            if (!(map[node.getState().getY()][node.getState().getX() - 1] == 'X')) {
                State childNodeState = new State(node.getState().getX() - 1, node.getState().getY());
                Node childNode = new Node(childNodeState);
                check(initialNode, node, childNode, goalNode, response1, response3);
            }
        }
    }

    private static void check(Node initialNode, Node node, Node childNode, Node goalNode, String response1,
            String response3) {
        // choose Best First Search
        if (response1.equals("A")) {
            // check if the child node is in the frontier
            int counter2 = 0;
            for (Node n : frontier) {
                if (childNode.equals(n)) {
                    counter2++;
                    break;
                }
            }
            if (counter2 == 0) {
                // check if the child node is explorded
                int counter1 = 0;
                for (int i = 0; i < exploredNode.size(); i++) {
                    if (childNode.equals(exploredNode.get(i))) {
                        counter1++;
                        break;
                    }
                }
                if (counter1 == 0) {
                    childNode.setParentNode(node);
                    childNode.setFCost(goalNode, response1, response3);
                    frontier.add(childNode);
                    System.out.println("the size of frontier is: " + frontier.size()
                            + "; the head element of frontier is: " + frontier.peek());
                }
            }
        }

        // choose A*
        if (response1.equals("B")) {
            // check if the child node is explorded
            int counter1 = 0;
            for (int i = 0; i < exploredNode.size(); i++) {
                if (childNode.equals(exploredNode.get(i))) {
                    counter1++;
                    break;
                }
            }
            if (counter1 == 0) {
                // check if the child node is in the frontier
                int counter2 = 0;
                for (Node n : frontier) {
                    if (childNode.equals(n)) {
                        counter2++;
                        childNode.setParentNode(node);
                        childNode.setPathCost(initialNode);
                        childNode.setFCost(goalNode, response1, response3);
                        if (childNode.getFCost() < n.getFCost()) {
                            frontier.remove(n);
                            System.out.println("the size of frontier is: " + frontier.size()
                            + "; the head element of frontier is: " + frontier.peek());
                            frontier.add(childNode);
                            break;
                        } else {
                            break;
                        }
                    }
                    // counter2++;
                }
                if (counter2 == 0) {
                    childNode.setParentNode(node);
                    childNode.setPathCost(initialNode);
                    childNode.setFCost(goalNode, response1, response3);
                    frontier.add(childNode);
                    System.out.println("the size of frontier is: " + frontier.size()
                    + "; the head element of frontier is: " + frontier.peek());
                }
            }
        }
    }

    // get path
    public void getPath(Node initialNode, Node goalNode) {
        path = new ArrayList<Node>();
        path.add(goalNode);
        Node tempNode;
        tempNode = goalNode.getParentNode();
        path.add(tempNode);
        if (tempNode != null) {
            while (!(tempNode.equals(initialNode))) {
                tempNode = tempNode.getParentNode();
                path.add(tempNode);
            }
            for (int i = path.size(); i > 1; i--) {
                System.out.print(path.get(i - 1) + " 鈫� ");
            }
            System.out.println(path.get(0));
            System.out.println("the number of elements in path is: " + path.size());
        } else {
            System.out.println("There is no path in this situation!");
        }
    }

    // get the list of states expanded (i.e.explored list)
    public void getExplore() {
        for (int i = 0; i < exploredNode.size() - 1; i++) {
            // B
            System.out.print(exploredNode.get(i) + " 鈫� ");
        }
        System.out.println(exploredNode.get(exploredNode.size() - 1));
        System.out.println("the number of elements in explored Node is: " + exploredNode.size());
    }
}

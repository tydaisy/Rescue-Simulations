package part1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Search {
    private static String response;
    private static char[][] map;

    private static Queue<Node> frontierBFS;
    private static Stack<Node> frontierDFS;
    private static ArrayList<Node> exploredNode;// = new ArrayList();
    private static ArrayList<Node> path;

    private static int maxY = 10;
    private static int maxX = 10;

    public Search(String r, char[][] m) {
        response = r;
        map = m;
    }

    public void searchStep(Node initialNode, Node goalNode) {
        if (response.equals("A")) {
            // add the intitial node into the frontier queue, explored list
            frontierBFS = new LinkedList<Node>();
            exploredNode = new ArrayList<Node>();

            System.out.println("1. the initial size of frontier is: " + frontierBFS.size()
                    + "; the head element of frontier is: " + frontierBFS.peek());
            frontierBFS.add(initialNode);
            System.out.println("2. the size of frontier is: " + frontierBFS.size()
                    + "; the head element of frontier is: " + frontierBFS.peek());
            initialNode.setParentNode(initialNode);

            while (!frontierBFS.isEmpty()) {
                System.out.println("3. the size of frontier is: " + frontierBFS.size()
                        + "; the head element of frontier is: " + frontierBFS.peek());
                Node node = frontierBFS.remove();
                System.out.println("### current node ###" + node);
                System.out.println("4. the size of frontier is: " + frontierBFS.size()
                        + "; the head element of frontier is: " + frontierBFS.peek());

                exploredNode.add(node);
                if (node.equals(goalNode)) {
                    goalNode.setParentNode(node.getParentNode());
                    break;
                }
                expand(node, goalNode);
                System.out.println("### current node ###" + node);
                System.out.println("5. the size of frontier is: " + frontierBFS.size()
                        + "; the head element of frontier is: " + frontierBFS.peek());
                System.out.println();
            }
        }
        if (response.equals("B")) {
            // add the intitial node into the frontier queue, explored list
            frontierDFS = new Stack<Node>();
            exploredNode = new ArrayList<Node>();
            try {
                System.out.println("1. the initial size of frontier is: " + frontierDFS.size()
                        + "; the head element of frontier is: " + frontierDFS.peek());
            } catch (Exception e) {
                System.out.println("1. the initial size of frontier is: " + frontierDFS.size()
                        + "; the head element of frontier is: " + null);
            }
            frontierDFS.push(initialNode);
            try {
                System.out.println("2. the size of frontier is: " + frontierDFS.size()
                        + "; the head element of frontier is: " + frontierDFS.peek());
            } catch (Exception e) {
                System.out.println("2. the size of frontier is: " + frontierDFS.size()
                        + "; the head element of frontier is: " + null);
            }
            initialNode.setParentNode(initialNode);

            while (!frontierDFS.isEmpty()) {
                try {
                    System.out.println("3. the size of frontier is: " + frontierDFS.size()
                            + "; the head element of frontier is: " + frontierDFS.peek());
                } catch (Exception e) {
                    System.out.println("3.the size of frontier is: " + frontierDFS.size()
                            + "; the head element of frontier is: " + null);
                }
                Node node = frontierDFS.pop();
                System.out.println("### current node ###" + node);
                try {
                    System.out.println("4. the size of frontier is: " + frontierDFS.size()
                            + "; the head element of frontier is: " + frontierDFS.peek());
                } catch (Exception e) {
                    System.out.println("4.the size of frontier is: " + frontierDFS.size()
                            + "; the head element of frontier is: " + null);
                }
                exploredNode.add(node);
                if (node.equals(goalNode)) {
                    goalNode.setParentNode(node.getParentNode());
                    break;
                }
                expand(node, goalNode);
                System.out.println("### current node ###" + node);
                try{
                System.out.println("5. the size of frontier is: " + frontierDFS.size()
                        + "; the head element of frontier is: " + frontierDFS.peek());
                }catch(Exception e){
                    System.out.println("5. the size of frontier is: " + frontierDFS.size()
                    + "; the head element of frontier is: " + null);
                }
                System.out.println();

            }

        }
    }

    private static void expand(Node node, Node goalNode) {
        // check if the current node can go South
        if (node.getState().getY() + 1 < maxY) {
            System.out.println("South");
            // check if the next position is the position of the one of
            // Xs
            if (map[node.getState().getY() + 1][node.getState().getX()] != 'X') {
                State childNodeState = new State(node.getState().getX(), node.getState().getY() + 1);
                Node childNode = new Node(childNodeState);
                check(node, childNode);
            }
        }

        // check if the current node can go east
        if (node.getState().getX() + 1 < maxX) {
            System.out.println("East");
            if (map[node.getState().getY()][node.getState().getX() + 1] != 'X') {
                State childNodeState = new State(node.getState().getX() + 1, node.getState().getY());
                Node childNode = new Node(childNodeState);
                check(node, childNode);
            }
        }

        // check if the current node can go North
        if (node.getState().getY() > 0) {
            System.out.println("North");
            if (!(map[node.getState().getY() - 1][node.getState().getX()] == 'X')) {
                State childNodeState = new State(node.getState().getX(), node.getState().getY() - 1);
                Node childNode = new Node(childNodeState);
                check(node, childNode);
            }
        }

        // check if the current node can go West
        if (node.getState().getX() > 0) {
            System.out.println("West");
            if (!(map[node.getState().getY()][node.getState().getX() - 1] == 'X')) {
                State childNodeState = new State(node.getState().getX() - 1, node.getState().getY());
                Node childNode = new Node(childNodeState);
                check(node, childNode);
            }
        }
    }

    private static void check(Node node, Node childNode) {
        // check if the child node is in the frontier
        if (response.equals("A")) { // when use BFS
            int counter2 = 0;
            for (Node n : frontierBFS) {
                if (childNode.equals(n)) {
                    counter2++;
                    break;
                }
            }
            if (counter2 == 0) {
                // check if the child Node is in the explored array list.
                int counter1 = 0;
                for (int i = 0; i < exploredNode.size(); i++) {
                    if (childNode.equals(exploredNode.get(i))) {
                        counter1++;
                        break;
                    }
                }
                if (counter1 == 0) {
                    childNode.setParentNode(node);
                    frontierBFS.add(childNode);
                    System.out.println("the size of frontier is: " + frontierBFS.size()
                            + "; the head element of frontier is: " + frontierBFS.peek());
                }
            }
        }

        // check if the child node is in the frontier
        if (response.equals("B")) { // when use DFS
            int counter2 = 0;
            for (Node n : frontierDFS) {
                if (childNode.equals(n)) {
                    counter2++;
                }
            }
            if (counter2 == 0) {
                // check if the child Node is in the explored array list.
                int counter1 = 0;
                for (int i = 0; i < exploredNode.size(); i++) {
                    if (childNode.equals(exploredNode.get(i))) {
                        counter1++;
                    }
                }
                if (counter1 == 0) {
                    childNode.setParentNode(node);
                    frontierDFS.add(childNode);
                    System.out.println("the size of frontier is: " + frontierDFS.size()
                            + "; the head element of frontier is: " + frontierDFS.peek());
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

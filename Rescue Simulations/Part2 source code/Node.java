package Part2;

public class Node implements Comparable<Node> {
    private State state;
    private Node parentNode;
    private double fCost;
    private double pathCost = 0;

    public Node(State state) {
        this.state = state;
    }

    // return the state of the node
    public State getState() {
        return state;
    }

    // return the parent node of the node
    public Node getParentNode() {
        return parentNode;
    }

    // return the path cost
    public double getPathCost() {
        return pathCost;
    }

    // return the f-cost(n);
    public double getFCost() {
        return fCost;
    }

    // set the parent node
    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    // calculate the path cost
    public double setPathCost(Node initialNode) {
        pathCost = 0;
        Node tempNode = this.getParentNode();
        pathCost++;
        while (!(tempNode.equals(initialNode))) {
            tempNode = tempNode.getParentNode();
            pathCost++;
        }
        return pathCost;
    }

    // calculate f-cost(n)
    public void setFCost(Node goal, String response1, String response3) {
        // if choose do Best first search algorithm, f-cost(n)= h-cost(n)
        if (response1.equals("A")) {
            // if choose Manhattan distance
            if (response3.equals("A")) {
                fCost = Math.abs(getState().getX() - goal.getState().getX())
                        + Math.abs(getState().getY() - goal.getState().getY());
            }

            // if choose Euclidian distance
            if (response3.equals("B")) {
                fCost = Math.sqrt(Math.pow(getState().getX() - goal.getState().getX(), 2)
                        + Math.pow(getState().getY() - goal.getState().getY(), 2));
            }

            // if choose Chebyshev distance
            if (response3.equals("C")) {
                fCost = Math.max(Math.abs(getState().getX() - goal.getState().getX()),
                        Math.abs(getState().getY() - goal.getState().getY()));
            }

            // if choose Manhattan distance and Euclidian distance
            if (response3.equals("D")) {
                fCost = Math.max(
                        Math.abs(getState().getX() - goal.getState().getX())
                                + Math.abs(getState().getY() - goal.getState().getY()),
                        Math.sqrt(Math.pow(getState().getX() - goal.getState().getX(), 2)
                                + Math.pow(getState().getY() - goal.getState().getY(), 2)));
            }

            // if choose Manhattan distance and Chebyshev distance
            if (response3.equals("E")) {
                fCost = Math.max(
                        Math.abs(getState().getX() - goal.getState().getX())
                                + Math.abs(getState().getY() - goal.getState().getY()),
                        Math.max(Math.abs(getState().getX() - goal.getState().getX()),
                                Math.abs(getState().getY() - goal.getState().getY())));
            }

            // if choose Euclidian distance and Chebyshev distance
            if (response3.equals("F")) {
                fCost = Math.max(
                        Math.sqrt(Math.pow(getState().getX() - goal.getState().getX(), 2)
                                + Math.pow(getState().getY() - goal.getState().getY(), 2)),
                        Math.max(Math.abs(getState().getX() - goal.getState().getX()),
                                Math.abs(getState().getY() - goal.getState().getY())));
            }
        }

        // if choose do A* search algorithm, f-cost(n)= h-cost(n) + path-cost(n)
        if (response1.equals("B")) {
            // if choose Manhattan distance
            if (response3.equals("A")) {
                fCost = Math.abs(getState().getX() - goal.getState().getX())
                        + Math.abs(getState().getY() - goal.getState().getY()) + pathCost;
            }

            // if choose Euclidian distance
            if (response3.equals("B")) {
                fCost = Math.sqrt(Math.pow(getState().getX() - goal.getState().getX(), 2)
                        + Math.pow(getState().getY() - goal.getState().getY(), 2)) + pathCost;
            }

            // if choose Chebyshev distance
            if (response3.equals("C")) {
                fCost = Math.max(Math.abs(getState().getX() - goal.getState().getX()),
                        Math.abs(getState().getY() - goal.getState().getY())) + pathCost;
            }

            // if choose Manhattan distance and Euclidian distance
            if (response3.equals("D")) {
                fCost = Math.max(
                        Math.abs(getState().getX() - goal.getState().getX())
                                + Math.abs(getState().getY() - goal.getState().getY()),
                        Math.sqrt(Math.pow(getState().getX() - goal.getState().getX(), 2)
                                + Math.pow(getState().getY() - goal.getState().getY(), 2)))
                        + pathCost;
            }

            // if choose Manhattan distance and Chebyshev distance
            if (response3.equals("E")) {
                fCost = Math.max(
                        Math.abs(getState().getX() - goal.getState().getX())
                                + Math.abs(getState().getY() - goal.getState().getY()),
                        Math.max(Math.abs(getState().getX() - goal.getState().getX()),
                                Math.abs(getState().getY() - goal.getState().getY())))
                        + pathCost;
            }

            // if choose Euclidian distance and Chebyshev distance
            if (response3.equals("F")) {
                fCost = Math.max(
                        Math.sqrt(Math.pow(getState().getX() - goal.getState().getX(), 2)
                                + Math.pow(getState().getY() - goal.getState().getY(), 2)),
                        Math.max(Math.abs(getState().getX() - goal.getState().getX()),
                                Math.abs(getState().getY() - goal.getState().getY())))
                        + pathCost;
            }
        }
    }

    @Override
    public String toString() {
        return "(" + getState().getX() + "," + getState().getY() + ")";
    }

    @Override
    public int compareTo(Node n) {
        return (int) (this.getFCost() - n.getFCost());
    }

    public boolean equals(Node n) {
        if (getState().getX() == n.getState().getX() && getState().getY() == n.getState().getY()) {
            return true;
        } else {
            return false;
        }
    }
}

package part1;

public class Node {

    private State state;
    private Node parentNode;

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

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
    
    @Override
    public String toString() {
        return "(" + getState().getX() + "," + getState().getY() + ")";
    }
    
    public boolean equals(Node n) {
        if (getState().getX() == n.getState().getX() && getState().getY() == n.getState().getY()) {
            return true;
        } else {
            return false;
        }
    }
}

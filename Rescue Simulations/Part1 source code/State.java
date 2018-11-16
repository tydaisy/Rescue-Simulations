package part1;

public class State {
    private int x;
    private int y;

    public State(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
   
    public boolean equals(State s){
        if(s.getX() == getX() && s.getY() == getY()) {
            return true;
        } else {
            return false;
        }
    }
}

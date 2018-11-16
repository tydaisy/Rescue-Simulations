package part1;

import java.util.Scanner;

public class Solve {
    private static Scanner scan;
    private static String response1;
    private static String response2;
    private static State initialState;
    private static State goalState1;
    private static State goalState2;
    private static Node initialNode;
    private static Node goalNode1; // B
    private static Node goalNode2; // G
    private static char[][] map;
    private static Search search;

    public static void main(String[] args) {
        choose();
        getInitial(response2);
        search = new Search(response1, map);

        // search the path from I to B
        search.searchStep(initialNode, goalNode1);
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    "********************************************************************************************************************************************************************************************************************************************************************************");
        }
        System.out.println();
        System.out.println(
                "The path for map from I to B is (positions are represented by two coordinates in Euclidean Space (x and y)): ");
        search.getPath(initialNode, goalNode1);
        System.out.println();
        System.out.println("Output explored node: ");
        search.getExplore();
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    "********************************************************************************************************************************************************************************************************************************************************************************");
        }

        // search the path from B to G
        search.searchStep(goalNode1, goalNode2); // search the path from B to G
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    "********************************************************************************************************************************************************************************************************************************************************************************");
        }
        System.out.println();
        System.out.println(
                "The path for map is from B to G (positions are represented by two coordinates in Euclidean Space (x and y)): ");
        search.getPath(goalNode1, goalNode2);
        System.out.println();
        System.out.println("Output explored node: ");
        search.getExplore();
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    "********************************************************************************************************************************************************************************************************************************************************************************");
        }

    }

    private static void choose() {
        scan = new Scanner(System.in);
        // choose the algorithm you want
        System.out.println("Which algorithm do you want(A/B)?");
        System.out.println("A. Breadth first search");
        System.out.println("B. Depth first search");
        response1 = scan.nextLine();
        // choose the algorithm you want
        System.out.println("Which map do you want?");
        System.out.println("A. map1");
        System.out.println("B. map2");
        System.out.println("C. map3");
        System.out.println("D. map4");
        System.out.println("E. map5");
        System.out.println("F. map6");
        response2 = scan.nextLine(); // choose the algorithm you want
    }

    private static void getInitial(String response) { // get start and two goals
        switch (response) {
        case "A":
            initialState = new State(0, 0);
            initialNode = new Node(initialState);
            goalState1 = new State(8, 7);
            goalNode1 = new Node(goalState1); // B
            goalState2 = new State(9, 9);
            goalNode2 = new Node(goalState2); // C

            map = Maps.map1;
            break;
        case "B":
            initialState = new State(2, 9);
            initialNode = new Node(initialState);
            goalState1 = new State(6, 1);
            goalNode1 = new Node(goalState1);
            goalState2 = new State(0, 9);
            goalNode2 = new Node(goalState2);

            map = Maps.map2;
            break;

        case "C":
            initialState = new State(1, 4);
            initialNode = new Node(initialState);
            goalState1 = new State(8, 4);
            goalNode1 = new Node(goalState1);
            goalState2 = new State(4, 8);
            goalNode2 = new Node(goalState2);

            map = Maps.map3;
            break;
        case "D":
            initialState = new State(1, 4);
            initialNode = new Node(initialState);
            goalState1 = new State(8, 4);
            goalNode1 = new Node(goalState1);
            goalState2 = new State(4, 8);
            goalNode2 = new Node(goalState2);

            map = Maps.map4;
            break;
        case "E":
            initialState = new State(8, 8);
            initialNode = new Node(initialState);
            goalState1 = new State(2, 1);
            goalNode1 = new Node(goalState1);
            goalState2 = new State(4, 8);
            goalNode2 = new Node(goalState2);

            map = Maps.map5;
            break;
        case "F":
            initialState = new State(9, 2);
            initialNode = new Node(initialState);
            goalState1 = new State(2, 1);
            goalNode1 = new Node(goalState1);
            goalState2 = new State(4, 8);
            goalNode2 = new Node(goalState2);

            map = Maps.map6;
            break;
        default:
            break;
        }
    }

}

package com.company;

import java.util.*;

public class Maze {

    private final int level; // L
    private final int length; // R
    private final int width;  // C

    private char[][][] maze;
    private boolean[][][] visited;
    private int[][][] distances;
    private HashMap<Node,Node> predecessor;

    private Node start;
    private Node exit;


    public Maze(int level, int length, int width){
        this.level = level;
        this.length = length;
        this.width = width;

        distances = new int[level][length][width];
        for (int i = 0; i<level; i++){
            for (int j = 0; j<length; j++){
                for (int k=0; k<width; k++){
                    distances[i][j][k]= Integer.MAX_VALUE;
                }
                System.out.println();
            }
            System.out.println();
        }
        visited = new boolean[level][length][width];

        predecessor = new HashMap<>();
    }


    //dijkstra findet den Kuerzesten weg von start zu jedem adjazenten Knoten
    public void dijkstra(){

        ArrayList<Node> prioQueue = new ArrayList<>();

        prioQueue.add(start);
        setDistance(start,0);

        while (!prioQueue.isEmpty()){

            //System.out.print("Working\n.\n.\n.");

            // Comparator ist demnach konfiguriert, dass sich die Liste nach der aktuellen Distanz zu S sortiert
            prioQueue.sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if (getDistance(o1) < getDistance(o2)){
                        return -1;
                    }else {
                        return 1;
                    }
                }
            });

            Node pivot = prioQueue.remove(0);
            setVisitedNode(pivot);

            ArrayList<Node> neighbours = getUnvisitedNeighbours(pivot);


            for (Node neighbour: neighbours ) {
                if (getDistance(neighbour)>getDistance(pivot)+1){
                    setDistance(neighbour,getDistance(pivot)+1);
                    predecessor.put(neighbour,pivot);
                }

            }

            prioQueue.addAll(neighbours);

        }

    }

    // gibt das Labyrint auf der Konsole aus
    public void printMaze(){
        for (int i = 0; i<level; i++){
            for (int j = 0; j<length; j++){
                for (int k=0; k<width; k++){
                    System.out.print(maze[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    // gibt das originale Labyrinth neben dem Labyrinth mit eingezeichnetem Lösungsweg aus um die Lösung nachvollziehen zu können
    public void printPathToExit(){
        char[][][] mazeCopy = new char[level][length][width];
        for (int i = 0; i<level; i++){
            for (int j = 0; j<length; j++){
                for (int k=0; k<width; k++){
                    mazeCopy[i][j][k] = maze[i][j][k];
                }
            }
        }
        Node node = exit;
        if (predecessor.containsKey(exit)){
            while (!node.equals(start)){
                mazeCopy = setNodeAsPathToExit(predecessor.get(node), mazeCopy);
                node = predecessor.get(node);
            }
        }

        for (int i = 0; i<level; i++){
            for (int j = 0; j<length; j++){
                for (int k=0; k<width; k++){
                    System.out.print(maze[i][j][k]);
                }
                System.out.print("\t\t");
                for (int k=0; k<width; k++){
                    System.out.print(mazeCopy[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    // gibt die noch nicht besuchten Nachbarn zurück
    public ArrayList<Node> getUnvisitedNeighbours(Node pivot){

        ArrayList<Node> potentialNeighbours = new ArrayList<>();
        potentialNeighbours.add(new Node(pivot.getLevel()-1, pivot.getLength(), pivot.getWidth())); //below
        potentialNeighbours.add(new Node(pivot.getLevel()+1, pivot.getLength(), pivot.getWidth())); //above
        potentialNeighbours.add(new Node(pivot.getLevel(), pivot.getLength()-1, pivot.getWidth())); //front
        potentialNeighbours.add(new Node(pivot.getLevel(), pivot.getLength()+1, pivot.getWidth())); //behind
        potentialNeighbours.add(new Node(pivot.getLevel(), pivot.getLength(), pivot.getWidth()-1)); //left
        potentialNeighbours.add(new Node(pivot.getLevel(), pivot.getLength(), pivot.getWidth()+1)); //right

        ArrayList<Node> unvisitedNeighbours = new ArrayList<>();

        for (Node n : potentialNeighbours) {
            if (n.getLevel()>=0 && n.getLength()>=0 && n.getWidth()>=0){
                if(n.getLevel() < level && n.getLength() < length && n.getWidth() < width){
                    if (maze[n.getLevel()][n.getLength()][n.getWidth()] == '.' || maze[n.getLevel()][n.getLength()][n.getWidth()] == 'E'){
                        if (!visited[n.getLevel()][n.getLength()][n.getWidth()]) unvisitedNeighbours.add(n);
                    }
                }
            }
        }


        return unvisitedNeighbours;

    }

    // setzt ein X an stelle des Knotens um den Weg zum Ausgang zu markieren
    public char[][][] setNodeAsPathToExit(Node node, char[][][] maze){
        if (!node.equals(exit) && !node.equals(start))maze[node.getLevel()][node.getLength()][node.getWidth()] = 'X';
        return maze;
    }

    // markiert den Knoten als besucht
    public void setVisitedNode(Node node){
        visited[node.getLevel()][node.getLength()][node.getWidth()] = true;
    }

    public boolean isVisited(Node node){
        return visited[node.getLevel()][node.getLength()][node.getWidth()];
    }

    // nachdem dijkstra ausgeführt wurde gibt isEscapable() zurück ob es einen Weg von S zu E gibt
    public boolean isEscapable(){
        return visited[exit.getLevel()][exit.getLength()][exit.getWidth()];
    }

    // gibt zurück ob das Labyrinth in den Maßen 30x30x30 liegt
    public boolean isTooLarge(){
        if (level >30 || length >30 || width >30){
            System.out.println("Das Labyrinth ist zu groß!");
            return true;
        }
        return false;
    }

    public void setMaze(char[][][] maze) {
        this.maze = maze;
    }

    public void setDistance(Node node, int distance){
        distances[node.getLevel()][node.getLength()][node.getWidth()] = distance;
    }

    public int getDistance(Node node){
        return distances[node.getLevel()][node.getLength()][node.getWidth()];
    }

    public int getDistanceToExit(){
        return distances[exit.getLevel()][exit.getLength()][exit.getWidth()];
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
        setVisitedNode(start);
    }

    public void setExit(Node exit) {
        this.exit = exit;
    }

    public Node getExit() {
        return exit;
    }

    public int getLevel() {
        return level;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }


}

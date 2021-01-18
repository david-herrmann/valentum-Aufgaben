package com.company;

import sun.misc.Queue;

import java.util.*;

public class Maze {

    private final int level; // L
    private final int length; // R
    private final int width;  // C

    private char[][][] maze;
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
                    distances[i][j][k]= 0;
                }
                System.out.println();
            }
            System.out.println();
        }

        predecessor = new HashMap<>();
    }


    //dijkstra findet den Kuerzesten weg von start zu jedem adjazenten Knoten
    //da in diesem fall alle Kantengewichte = 1 gilt, kann man simpler auch bfs verwenden
    public void bfs() throws InterruptedException {
        Queue<Node> nodeQueue = new Queue<>();

        nodeQueue.enqueue(start);
        setDistance(start,0);

        while (!nodeQueue.isEmpty()){




            Node pivot = nodeQueue.dequeue();

            ArrayList<Node> neighbours = getUnvisitedNeighbours(pivot);


            for (Node neighbour: neighbours ) {

                setDistance(neighbour,getDistance(pivot) +1 );
                predecessor.put(neighbour,pivot);
                nodeQueue.enqueue(neighbour);

            }


            setDistance(start,-1);
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
                        if (distances[n.getLevel()][n.getLength()][n.getWidth()]==0) unvisitedNeighbours.add(n);
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


    // nachdem bfs ausgeführt wurde gibt isEscapable() zurück ob es einen Weg von S zu E gibt
    public boolean isEscapable(){
        if (distances[exit.getLevel()][exit.getLength()][exit.getWidth()]>0)return true;
        return false;
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
        setDistance(start,-1);
        this.start = start;
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

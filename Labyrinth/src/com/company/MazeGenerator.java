package com.company;

import java.util.Random;

public class MazeGenerator {


    private Maze maze;
    private int level;
    private int length;
    private int width;

    public MazeGenerator(){
        level = getRandomNumberInRange(1,30);
        length = getRandomNumberInRange(1,30);
        width = getRandomNumberInRange(1,30);
        maze = new Maze(level, length, width);
    }

    public MazeGenerator(int level, int length, int width) {
        this.level = level;
        this.length = length;
        this.width = width;
        maze = new Maze(level, length, width);

    }

    // generiert ein zufällig ausgefülltes Labyrinth inklusive Start und Endknoten
    public Maze generateRandomMaze(){

        Node start = setRandomStart(level,length,width);

        Node exit = setRandomExit(start, level, length, width);

        maze.setStart(start);
        maze.setExit(exit);

        maze.setMaze(getRandomMazeData(start, exit, level, length, width));

        maze.printMaze();

        return maze;


    }

    // generiert die Matrix des Labyrinths mit zuföllig ausgewählten Luft- und Wandelementen
    // Setzt zudem ein S an stelle des Starts und ein E an stelle des Ausgangs
    private static char[][][] getRandomMazeData(Node start, Node exit, int level, int length, int width){
        char[][][] maze = new char[level][length][width];
        for (int i=0; i<level; i++){
            for (int j=0; j<length; j++){
                for (int k=0; k<width; k++){
                    int random = getRandomNumberInRange(0,1);
                    if (random == 0){
                        maze[i][j][k] = '.';
                    }else {
                        maze[i][j][k] = '#';
                    }
                    if (i == start.getLevel() && j == start.getLength() && k == start.getWidth()) maze[i][j][k] = 'S';
                    if (i == exit.getLevel() && j == exit.getLength() && k == exit.getWidth()) maze[i][j][k] = 'E';
                }
            }
        }
        return maze;
    }

    // gibt einen zufällig ausgewählten Startknoten zurück
    private static Node setRandomStart(int level, int length, int width){
        Node node = new Node(getRandomNumberInRange(0,level-1), getRandomNumberInRange(0,length-1), getRandomNumberInRange(0,width-1));
        return node;
    }

    // gibt einen zufällig ausgewählten Ausgangsknoten zurück, der nicht der Startknoten ist
    private static Node setRandomExit(Node start, int level, int length, int width){
        Node node;
        do {
            node = new Node(getRandomNumberInRange(0,level-1), getRandomNumberInRange(0,length-1), getRandomNumberInRange(0,width-1));
        }while (start.equals(node));
        return node;
    }

    // Util-Methode, die eine Zufallszahl zwischen min und max zurückgibt
    private static int getRandomNumberInRange(int min, int max) {

        if (min == max) return 0;

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}

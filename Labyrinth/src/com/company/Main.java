package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    private static Maze maze;
    private static final Scanner scanner = new Scanner(System.in);

    // initiiert ein Labyrinth anhand der eingelesenen Eckdaten L R C
    public static Maze initializeMaze(){

        int level = scanner.nextInt();
        int length = scanner.nextInt();
        int width = scanner.nextInt();
        if (level == 0 && length == 0 && width ==0) new Maze(level,length,width);
        scanner.nextLine();
        return new Maze(level,length,width);

    }

    // beendigt das Einlesen eines weiteren Labyriths bei Eckdaten 0 0 0
    public static boolean continueGame(){
        return maze.getLevel() != 0 || maze.getLength() != 0 || maze.getWidth() != 0;
    }

    // liest ein Labyrith ein, inklusive Start- und Ausgangsknoten
    public static char[][][] readMazeData(Maze maze){

        char[][][] data = new char[maze.getLevel()][maze.getLength()][maze.getWidth()];


        for (int i=0 ; i< maze.getLevel() ; i++){
            for (int j=0; j< maze.getLength(); j++){
                String line = scanner.nextLine();
                for (int k=0; k< maze.getWidth(); k++){
                    char nextChar = line.charAt(k);
                    data[i][j][k] = nextChar;
                    if (nextChar== 'S'){
                        maze.setStart(new Node(i,j,k));
                    }
                    if (nextChar== 'E'){
                        maze.setExit(new Node(i,j,k));
                    }
                }
            }
            scanner.nextLine();
        }

        return data;

    }


    public static void main(String[] args) throws InterruptedException {

        boolean loop = true;

        // Das Programm wird wiederholt, bis es durch die eingabe B beendigt wird
        while (loop){
            System.out.print("Um...\n " +
                    "\teigene Labyrinte zu bearbeiten, drücke 'E'\n" +
                    "\tein zufällig generiertes Labyrint zu bearbeiten, drücke 'Z'\n" +
                    "\tein zufällig generiertes Labyrint mit Eckdaten zu bearbeiten, drücke 'M'\n" +
                    "\tdas Programm zu beenden, drücke 'B'\n" +
                    "... und bestätige mit (Enter): ");
            String s = scanner.nextLine();

            // Eingabe E - eigens in die Konsole eingegebenes Labyrith wird gelöst
            switch (s) {
                case "E":
                    System.out.println("Gebe hier die zu bearbeitenden Labyrinthe ein:");
                    maze = initializeMaze();
                    while (continueGame()) {

                        if (!maze.isTooLarge()) {

                            maze.setMaze(readMazeData(maze));
                            maze.bfs();
                            int solvedIn = maze.getDistanceToExit();

                            if (maze.isEscapable()) {
                                System.out.println();
                                System.out.println("Entkommen in " + solvedIn + " Minute(n)!");
                            } else {
                                System.out.println("Gefangen:-(");
                            }

                        }


                        maze = initializeMaze();
                    }
                    // Eingabe Z - Zufällig generiertes Labyrinth wird ausgegeben und gelöst
                    break;
                case "Z": {
                    MazeGenerator mazeGenerator = new MazeGenerator();
                    maze = mazeGenerator.generateRandomMaze();

                    maze.bfs();

                    int solvedIn = maze.getDistanceToExit();

                    if (maze.isEscapable()) {
                        System.out.println();
                        System.out.println("Entkommen in " + solvedIn + " Minute(n)!");
                        System.out.print("Lösungsweg Anzeigen? (Y) ");
                        String y = scanner.nextLine();
                        if (y.equals("Y")) {
                            maze.printPathToExit();
                        }
                    } else {
                        System.out.println("Gefangen:-(");
                    }

                    // Eingabe M - Eckdaten L R C werden abgefragt und ein zufällig generiertes Labyrinth wird ausgegeben und gelöst
                    break;
                }
                case "M": {
                    System.out.print("levels: ");
                    int level = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("length: ");
                    int lenght = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("width: ");
                    int width = scanner.nextInt();
                    scanner.nextLine();

                    MazeGenerator mazeGenerator = new MazeGenerator(level, lenght, width);
                    maze = mazeGenerator.generateRandomMaze();

                    if (!maze.isTooLarge()) {
                        maze.bfs();
                        int solvedIn = maze.getDistanceToExit();

                        if (maze.isEscapable()) {
                            System.out.println();
                            System.out.println("Entkommen in " + solvedIn + " Minute(n)!");
                            System.out.print("Lösungsweg Anzeigen? (Y) ");
                            String y = scanner.nextLine();
                            if (y.equals("Y")) {
                                maze.printPathToExit();
                            }
                        } else {
                            System.out.println("Gefangen:-(");
                        }
                    }


                    // Eingabe B - das Programm wird beendigt
                    break;
                }
                case "B":
                    loop = false;
                    break;
                default:
                    System.out.println("Unbekannte Eingabe");
                    break;
            }
        }






    }
}

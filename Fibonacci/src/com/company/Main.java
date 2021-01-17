package com.company;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    static Scanner scanner;

    //Liest eine unbestimmte Anzahl an Zahlen von der Konsole bis zu einer leeren Zeile
    public static TreeSet<Integer> readNumbersFromConsole(){
        System.out.println("Bitte gebe nun die Zahlen ein und bestaetige die Eingabe mit einer leeren Zeile: ");

        TreeSet<Integer> integers = new TreeSet<>();

        String line = scanner.nextLine();

        while (line != null && !line.equals("")){
            integers.add(Integer.parseInt(line));
            line = scanner.nextLine();
        }

        return integers;
    }

    // Berechnet die Fibonaccizahlen jedes elements in der eingegebenen Liste und gibt es auf der Konsole aus
    public static void printFibonacci(TreeSet<Integer> integers){
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger index = new BigInteger("1");
        BigInteger Fib;

        // Falls 0 in der Liste, entfernen und bearbeiten
        if (integers.remove(0)){
            System.out.println("Die Fibonacci Zahl für 0 ist: 0");
        }
        // Falls 1 in der Liste, entfernen und bearbeiten
        if (integers.remove(1)){
            System.out.println("Die Fibonacci Zahl für 1 ist: 1");
        }

        // Fibonaccizahlen bis hin zum größten Element berechnen
        // Worst-Case 4998 Schleifendurchläufe
        for (Integer i : integers) {
            do {
                index = index.add(BigInteger.ONE);
                Fib = zero.add(one);
                zero = one;
                one = Fib;

            }while ( !index.equals(new BigInteger(i.toString())) );

            System.out.println("Die Fibonacci Zahl für "+ i + " ist: " + Fib);
        }

    }

    // gibt ein TreeSet mit 523 zufälligen Zahlen zwischen 0 und 5000 zurück
    public static TreeSet<Integer> fillWithRandomNumbers(){
        TreeSet<Integer> integers = new TreeSet<>();
        for (int i=0 ; i<523; i++){
            Integer integer = getRandomNumberInRange(0,5000);
            integers.add(integer);
            System.out.println(integer);
        }
        return integers;
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


    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        System.out.println("Um...\n+" +
                "\teigene Zahlen einzugeben drücke E\n" +
                "\t523 zufällige Zahlen zwischen 0 und 5000 zu generieren drücke Z\n" +
                "... und bestätige mit (Enter)");
        char c = scanner.nextLine().charAt(0);
        if (c == 'E'){
            TreeSet<Integer> integers = readNumbersFromConsole();

            printFibonacci(integers);
        }else if(c == 'Z'){
            TreeSet<Integer> integers = fillWithRandomNumbers();
            printFibonacci(integers);
        }

    }
}

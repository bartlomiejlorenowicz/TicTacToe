package com.tictactoe;

import com.tictactoe.core.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose game mode:");
        System.out.println("1 - Classic 3x3 ");
        System.out.println("2 - Extended 10x10");

        int choice;
        do {
            System.out.print("Enter 1 or 2: ");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);

        int size = (choice == 1) ? 3 : 10;
        Game game = new Game(size);
        game.start();
    }
}
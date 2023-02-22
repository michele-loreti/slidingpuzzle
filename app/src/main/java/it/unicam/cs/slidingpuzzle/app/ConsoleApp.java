/*
 * MIT License
 *
 * Copyright (c)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package it.unicam.cs.slidingpuzzle.app;

import it.unicam.cs.slidingpuzzle.api.PuzzleBoard;
import it.unicam.cs.slidingpuzzle.api.SlidingDirection;

import java.util.Scanner;

public class ConsoleApp {

    private final PuzzleBoard board;

    private final Scanner input = new Scanner(System.in);

    public ConsoleApp() {
        this(PuzzleBoard.DEFAULT_SIZE);
    }

    public ConsoleApp(int size) {
        this.board = new PuzzleBoard(size);
    }


    private void printBoard() {
        for(int i=0; i < board.getSize(); i++) {
            printRowSeparator();
            printRow(i);
        }
        printRowSeparator();
    }

    private void printRow(int i) {
        for(int j=0; j < board.getSize() ; j++) {
            if (board.isEmpty(i, j)) {
                System.out.print("+    ");
            } else {
                System.out.printf("+ %2d ",board.get(i, j));
            }
        }
        System.out.println("+");
    }

    private void printRowSeparator() {
        for(int i=0; i < board.getSize() ; i++) {
            System.out.print("+----");
        }
        System.out.println("+");
    }

    public void start() {
        board.shuffle(200);
        int movesCounter = 0;
        while (!board.solved()) {
            printBoard();
            if (doAction()) {
                movesCounter++;
            }
        }
        System.out.printf("Well done! You solved the puzzle with %d moves!\n", movesCounter);
    }

    private boolean doAction() {
        System.out.println("Enter your move (u, d, l, r):  ");
        System.out.flush();
        boolean flag = true;
        boolean rightCommand = true;
        String command = input.nextLine();
        switch (command.charAt(0)) {
            case 'u':
                flag = board.move(SlidingDirection.UP);
                break;
            case 'd':
                flag = board.move(SlidingDirection.DOWN);
                break;
            case 'l':
                flag = board.move(SlidingDirection.LEFT);
                break;
            case 'r':
                flag = board.move(SlidingDirection.RIGHT);
                break;
            default:
                rightCommand = false;
        }
        if (!flag) {
            System.out.println("\n\nERROR: Illegal move!\n\n");
            return false;
        }
        if (!rightCommand) {
            System.out.println("Illegal command!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ConsoleApp cp = new ConsoleApp(5);
        cp.start();
    }

}

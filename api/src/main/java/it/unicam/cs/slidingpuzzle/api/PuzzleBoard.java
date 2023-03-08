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
package it.unicam.cs.slidingpuzzle.api;

import java.util.Random;

/**
 * This class is used to represent the schema of a puzzle.
 */
public class PuzzleBoard {

    /**
     * Default size of a schema.
     */
    public static final int DEFAULT_SIZE = 4;

    /**
     * An array used to store the state of this schema.
     */
    private final int[][] cells;

    /**
     * The size of this schema.
     */
    private final int size;

    private Position freeCell;
    private int shufflingDegree;

    /**
     * Creates a new puzzle with the default size.
     */
    public PuzzleBoard() {
        this(DEFAULT_SIZE);
    }

    /**
     * Creates a new puzzle of the given size.
     *
     * @param size the size of the created schema.
     */
    public PuzzleBoard(int size) {
        this.size = size;
        this.cells = new int[size][size];
        reset();
    }

    /**
     * Resets the state of this schema.
     */
    private void reset() {
        shufflingDegree = 0;
        freeCell = new Position(size);
        int counter = 1;
        for(int i=0; i < size; i++) {
            for(int j=0; j < size; j++) {
                this.cells[i][j] = (counter++)%(size*size);
            }
        }
    }

    public boolean move(SlidingDirection dir) {
        Position movingCell = freeCell.movingCell(dir);
        if (movingCell == null) {
            return false;
        }
        int n = get(movingCell);
        set(freeCell, n);
        set(movingCell, 0);
        shufflingDegree = shufflingDegree-movingCell.getDisorderDegree(n)+freeCell.getDisorderDegree(n);
        freeCell = movingCell;
        return true;
    }

    private int get(Position p) {
        return get(p.getRow(), p.getColumn());
    }

    private void set(Position p, int v) {
        this.cells[p.getRow()][p.getColumn()] = v;
    }


    public int get(int x, int y) {
        return this.cells[x][y];
    }

    public void shuffle(int movements) {
        shuffle(new Random(), movements);
    }

    public void shuffle(Random random, int movements) {
        for(int i=0; i<movements; i++) {
            SlidingDirection[] enabledMovement = enabledMoves();
            if (enabledMovement.length>0) {
                move(enabledMovement[random.nextInt(enabledMovement.length)]);
            }
        }
    }

    public SlidingDirection[] enabledMoves() {
        return freeCell.enabledMoves();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(int i, int j) {
        return cells[i][j]==0;
    }

    public boolean solved() {
        return this.shufflingDegree==0;
    }
}

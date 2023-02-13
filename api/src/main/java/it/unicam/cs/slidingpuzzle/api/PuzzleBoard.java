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

public class PuzzleBoard {

    private int[][] cells;

    private int emptyX;
    private int emptyY;

    public PuzzleBoard() {
        this.cells = new int[4][4];
        reset();
    }

    private void reset() {
        this.emptyX = 3;
        this.emptyY = 3;
        int counter = 1;
        for(int i=0; i < 4; i++) {
            for(int j=0; j < 4; j++) {
                this.cells[i][j] = (counter++)%16;
            }
        }
    }

    public boolean moveUp() {
        if (emptyY<3) {
            cells[emptyX][emptyY] = cells[emptyX][emptyY+1];
            emptyY++;
            cells[emptyX][emptyY] = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveDown() {
        if (emptyY>0) {
            cells[emptyX][emptyY] = cells[emptyX][emptyY-1];
            emptyY--;
            cells[emptyX][emptyY] = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveLeft() {
        if (emptyX>0) {
            cells[emptyX][emptyY] = cells[emptyX-1][emptyY];
            emptyX--;
            cells[emptyX][emptyY] = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveRight() {
        if (emptyX<3) {
            cells[emptyX][emptyY] = cells[emptyX+1][emptyY];
            emptyX++;
            cells[emptyX][emptyY] = 0;
            return true;
        } else {
            return false;
        }
    }

    public int get(int x, int y) {
        return this.cells[x][y];
    }

}

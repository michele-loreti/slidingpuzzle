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

import java.util.LinkedList;

/**
 * The instances of this class are used to identify positions in the schema.
 */
public class Position {

    private final int row;

    private final int column;

    private final int size;

    public Position(int row, int column, int size) {
        this.row = row;
        this.column = column;
        this.size = size;
    }

    public Position(int size) {
        this(size-1, size-1, size);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    public Position movingCell(SlidingDirection dir) {
        if ((dir == SlidingDirection.DOWN)
                &&(row>0))
            return new Position(row-1,column,size);
        if ((dir == SlidingDirection.UP)&&(row<size-1)) return new Position(row+1,column, size);
        if ((dir == SlidingDirection.LEFT)&&(column<size-1)) return new Position(row, column+1, size);
        if ((dir == SlidingDirection.RIGHT)&&(column>0)) return new Position(row, column-1, size);
        return null;
    }



    public SlidingDirection[] enabledMoves() {
        LinkedList<SlidingDirection> nextPositions = new LinkedList<>();
        if (row>0)
            nextPositions.add(SlidingDirection.LEFT);
        if (row<size-1)
            nextPositions.add(SlidingDirection.RIGHT);
        if (row>0)
            nextPositions.add(SlidingDirection.LEFT);
        if (column<size-1)
            nextPositions.add(SlidingDirection.UP);
        if (column>0)
            nextPositions.add(SlidingDirection.DOWN);
        return nextPositions.toArray(new SlidingDirection[0]);
    }

    /**
     * Returns the Manhattan distance obtained when considering the
     * value n placed in this location.
     *
     * @param n an integer value
     * @return the Manhattan distance obtained when considering the
     * value n placed in this location.
     */
    public int getDisorderDegree(int n) {
        // |rg - rc|+|cg - cc|
        return Math.abs(this.row-(n-1)/this.size) + Math.abs(this.column-(n-1)%this.size);
    }
}

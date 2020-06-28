package de.pms.minigames.main.helper;

public class Cell {
    private boolean top = true, bottom = true, left = true, right = true;
    private int column, row;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public boolean getTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean getBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public boolean getLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}

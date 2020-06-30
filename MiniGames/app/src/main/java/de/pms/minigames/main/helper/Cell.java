package de.pms.minigames.main.helper;

/**
 * Creates a new cell for the labyrinth.
 */
public class Cell {
    private boolean top = true, bottom = true, left = true, right = true;

    /**
     * Constructor of Cell.
     */
    public Cell() {
    }

    /**
     * Gets the top wall of a cell.
     * True -> wall
     * False -> no wall
     *
     * @return Top wall of a cell.
     */
    public boolean getTop() {
        return top;
    }

    /**
     * Sets the top wall of a cell.
     *
     * @param top Value of the wall.
     */
    public void setTop(boolean top) {
        this.top = top;
    }

    /**
     * Gets the bottom wall of a cell.
     * True -> wall
     * False -> no wall
     *
     * @return Bottom wall of a cell.
     */
    public boolean getBottom() {
        return bottom;
    }

    /**
     * Sets the bottom wall of a cell.
     *
     * @param bottom Value of the wall.
     */
    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    /**
     * Gets the left wall of a cell.
     * True -> wall
     * False -> no wall
     *
     * @return Left wall of a cell.
     */
    public boolean getLeft() {
        return left;
    }

    /**
     * Sets the left wall of a cell.
     *
     * @param left Value of the wall.
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Gets the right wall of a cell.
     * True -> wall
     * False -> no wall
     *
     * @return Right wall of a cell.
     */
    public boolean getRight() {
        return right;
    }

    /**
     * Sets the right wall of a cell.
     *
     * @param right Value of the wall.
     */
    public void setRight(boolean right) {
        this.right = right;
    }
}

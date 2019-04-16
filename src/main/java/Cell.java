public class Cell {

    private int y;
    private int x;
    private boolean isVisited;
    private boolean isWall;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public Cell(int y, int x) {
        this.y = y;
        this.x = x;
        this.isWall = true;
    }
}

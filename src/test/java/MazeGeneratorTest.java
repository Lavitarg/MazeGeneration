import org.junit.Test;


import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class MazeGeneratorTest {
    @Test
    public void generateMaze() throws Exception {
    }

    @Test
    public void getUnvisitedNeighbours() throws Exception {
        MazeGenerator mGen = new MazeGenerator();
        Cell[][] maze = mGen.generateMaze(5,5);
        ArrayList<Cell> neighbours = mGen.getUnvisitedNeighbours(maze[1][1], maze);
        assertFalse(neighbours.isEmpty());
        for(Cell cell:neighbours){
            System.out.println(cell.getX()+cell.getY());
            assertFalse(cell.isVisited());
        }

    }

    @Test
    public void testDrawing() throws Exception {
        MazeGenerator mGen = new MazeGenerator();
        mGen.drawMaze(mGen.generateMaze(7,7));
    }

    @Test
    public void testRemoveWallVertical() {
        MazeGenerator mGen = new MazeGenerator();
        Cell[][] maze = mGen.generateMaze(5,5);
        mGen.removeWall(maze[1][1],maze[3][1],maze);
        assertFalse(maze[2][1].isWall());
    }

    @Test
    public void testMazeModernization() {
        MazeGenerator mGen = new MazeGenerator();
        Cell[][] maze = mGen.generateMaze(7,7);
        mGen.modernizeMaze(maze[1][1],maze);
        assertTrue(mGen.getSetOfUVCells().isEmpty());
        mGen.drawMaze(maze);
    }
}
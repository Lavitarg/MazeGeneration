import java.util.*;

public class MazeGenerator {

    private Stack<Cell> stack = new Stack<Cell>();
    private HashSet<Cell> setOfUVCells = new HashSet<>();
    //Генерируем заготовку под лабиринт заданных размеров
    public Cell[][] generateMaze(int n,int m){
        if(n%2 == 0){
            n++;
        }
        if (m%2 == 0){
            m++;
        }
        Cell[][] maze = new Cell[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                maze[i][j] = new Cell(i,j);
                if(i!=0 && j!=0 && i%2!=0 &&j%2!=0){
                    maze[i][j].setWall(false);
                    setOfUVCells.add(maze[i][j]);

                }
            }
        }

        return maze;
    }
    //вспомогательный метод, убирающий стену  между двумя заданными точками-соседями
    void removeWall(Cell cell1, Cell cell2, Cell[][] maze) {
        int difY = cell2.getY() - cell1.getY();
        int difX = cell2.getX() - cell1.getX();

        int addX=(difX!=0) ? difX/Math.abs(difX):0;
        int addY=(difY!=0) ? difY/Math.abs(difY):0;

        maze[cell1.getY()+addY][cell1.getX()+addX].setWall(false);
        maze[cell1.getY()+addY][cell1.getX()+addX].setVisited(true);


    }
    //вспомогателньный метод, возвращающий лист непосещенных соседей
     public ArrayList<Cell> getUnvisitedNeighbours (Cell currentCell, Cell[][] maze){
        ArrayList<Cell> unvisitedNeighbours = new ArrayList<Cell>();


        if (currentCell.getY()-2>0){
            Cell nTop = maze[currentCell.getY()-2][currentCell.getX()];
            if(!nTop.isVisited()){
                unvisitedNeighbours.add(nTop);
            }
        }
        if( currentCell.getX()+2<maze[currentCell.getY()].length-1){
            Cell nRight = maze[currentCell.getY()][currentCell.getX()+2];
            if(!nRight.isVisited()){
                unvisitedNeighbours.add(nRight);
            }
        }
        if( currentCell.getX()-2>0){
            Cell nLeft = maze[currentCell.getY()][currentCell.getX()-2];
            if(!nLeft.isVisited()){
                unvisitedNeighbours.add(nLeft);
            }
        }
        if (currentCell.getY()+2<maze.length-1){
            Cell nBot = maze[currentCell.getY()+2][currentCell.getX()];
            if(!nBot.isVisited()){
                unvisitedNeighbours.add(nBot);
            }
        }

        return unvisitedNeighbours;
    }
    //рисуем лабиринт в консоли
    void drawMaze( Cell[][] maze){
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
                if(maze[i][j].isWall()){
                    System.out.print(1);
                }else{
                    System.out.print(0);

                }
            }
            System.out.println("");
        }
        System.out.println("--------------------------------------------");
    }

    public Stack<Cell> getStack() {
        return stack;
    }

    public HashSet<Cell> getSetOfUVCells() {
        return setOfUVCells;
    }
    
    void modernizeMaze(Cell startCell, Cell[][] maze) {
        Random rnd = new Random(System.currentTimeMillis());
        Cell currentCell = startCell;
        while (!setOfUVCells.isEmpty()) {
            currentCell.setVisited(true);
            setOfUVCells.remove(currentCell);
            ArrayList<Cell> neighbours = getUnvisitedNeighbours(currentCell, maze);
            if (!neighbours.isEmpty()) {
                stack.add(currentCell);
                neighbours.trimToSize();
                Cell nextCell = neighbours.get(neighbours.size() == 1 ? (0): rnd.nextInt(neighbours.size()));
                System.out.println(neighbours.indexOf(nextCell));
                removeWall(currentCell,nextCell,maze);
                currentCell=nextCell;
            }else if(!stack.isEmpty()) {
                currentCell = stack.pop();
            }
        }
    }
}
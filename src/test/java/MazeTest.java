import org.junit.jupiter.api.BeforeEach;

public class MazeTest {
    Maze maze;

    @BeforeEach
    public void createMaze(){
        char[][] map = new char[2][2];
        map[0][0] = 'b';
        map[0][1] = 'b';
        map[1][0] = '.';
        map[1][1] = '#';
        maze = new Maze(map);
    }
}

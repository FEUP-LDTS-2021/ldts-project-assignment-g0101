import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {

    Maze maze;
    GameCharacter gameCharacter;
    GameCharacter enemy;

    @BeforeEach
    public void createMaze(){
        char[][] map = new char[2][2];
        map[0][0] = 'b';
        map[0][1] = 'b';
        map[1][0] = '.';
        map[1][1] = '#';
        maze = new Maze(map);
        gameCharacter = new Pacman(0,0);
        enemy = new Ghost(1,0);
    }

    @Test
    public void tesCollision(){
        Assertions.assertTrue(maze.characterCanMoveTo(new Position(0,1)));
        Assertions.assertFalse(maze.characterCanMoveTo(new Position(1,1)));
    }

    @Test
    public void testPointInteraction(){
        Assertions.assertTrue(maze.characterInteractsWithPoint(gameCharacter,new Position(0,1)));
        Assertions.assertFalse(maze.characterInteractsWithPoint(enemy,new Position(0,1)));
    }

}

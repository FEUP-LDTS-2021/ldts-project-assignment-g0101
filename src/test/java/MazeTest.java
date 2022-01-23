import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class MazeTest {

    Maze maze;
    GameCharacter gameCharacter;
    GameCharacter enemy;

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
    public void testCollision(){
        createMaze();
        Assertions.assertTrue(maze.characterCanMoveTo(gameCharacter.getDown()));
        gameCharacter.moveDown();
        Assertions.assertFalse(maze.characterCanMoveTo(gameCharacter.getRight()));
    }

    @Test
    public void testPointInteraction(){
        createMaze();
        Assertions.assertTrue(maze.characterInteractsWithPoint(gameCharacter,gameCharacter.getDown()));
        Assertions.assertFalse(maze.characterInteractsWithPoint(enemy,enemy.getDown()));
    }

    @Test
    public void checkEndOfMaze(){
        char[][] map = new char[1][1];
        map[0][0] = 'P';
        maze = new Maze(map);
        Assertions.assertTrue(maze.checkEndMaze("Up"));
        Assertions.assertTrue(maze.checkEndMaze("Down"));
        Assertions.assertTrue(maze.checkEndMaze("Left"));
        Assertions.assertTrue(maze.checkEndMaze("Right"));
    }

    @Test
    public void setPosOppositeUp(){
        char[][] map = new char[2][1];
        map[0][0] = 'P';
        map[1][0] = 'b';
        maze = new Maze(map);
        maze.setPosOpposite("Up");
        Position expected = new Position(0,1);
        Position result = maze.pacman.getPosition();
        Assertions.assertEquals(expected.getX(),result.getX());
        Assertions.assertEquals(expected.getY(),result.getY());
    }

    @Test
    public void setPosOppositeDown(){
        char[][] map = new char[2][1];
        map[0][0] = 'b';
        map[1][0] = 'P';
        maze = new Maze(map);
        maze.setPosOpposite("Down");
        Position expected = new Position(0,0);
        Position result = maze.pacman.getPosition();
        Assertions.assertEquals(expected.getX(),result.getX());
        Assertions.assertEquals(expected.getY(),result.getY());
    }


    @Test
    public void setPosOppositeRight(){
        char[][] map = new char[1][2];
        map[0][0] = 'b';
        map[0][1] = 'P';
        maze = new Maze(map);
        maze.setPosOpposite("Right");
        Position expected = new Position(0,0);
        Position result = maze.pacman.getPosition();
        Assertions.assertEquals(expected.getX(),result.getX());
        Assertions.assertEquals(expected.getY(),result.getY());
    }

    @Test
    public void setPosOppositeLeft(){
        char[][] map = new char[1][2];
        map[0][0] = 'P';
        map[0][1] = 'b';
        maze = new Maze(map);
        maze.setPosOpposite("Left");
        Position expected = new Position(1,0);
        Position result = maze.pacman.getPosition();
        Assertions.assertEquals(expected.getX(),result.getX());
        Assertions.assertEquals(expected.getY(),result.getY());
    }






}

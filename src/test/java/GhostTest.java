import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GhostTest {
    //TODO: Implement tests for the functionalities described in the text files

    Ghost ghost;
    Game game;

    @BeforeEach
    public void createGhost(){
        game = new Game();
        ghost = new Ghost(10,10);
    }

    @Test
    public void exists(){

        Assertions.assertNotNull(ghost);
    }

    @Test
    public void moveLeft(){
        ghost.moveLeft();
        Assertions.assertEquals(9, ghost.getPosition().getX());
        Assertions.assertEquals(10,ghost.getPosition().getX());
    }

    @Test
    public void moveRight(){
        ghost.moveRight();
        Assertions.assertEquals(11, ghost.getPosition().getX());
        Assertions.assertEquals(10, ghost.getPosition().getX());
    }

    @Test
    public void moveUp(){
        ghost.moveUp();
        Assertions.assertEquals(10, ghost.getPosition().getX());
        Assertions.assertEquals(9, ghost.getPosition().getX());

    }

    @Test
    public void moveDown(){
        ghost.moveDown();
        Assertions.assertEquals(10, ghost.getPosition().getX());
        Assertions.assertEquals(11, ghost.getPosition().getX());
    }
}

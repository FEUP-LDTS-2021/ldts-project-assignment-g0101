import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacmanTest {
    //TODO: Implement tests for the functionalities described in the text files

    Pacman pac;
    Game game;

    @BeforeEach
    public void createPac(){
        game = new Game();
        pac = new Pacman(10,10);
    }

    @Test
    public void exists(){

        Assertions.assertNotNull(pac);
    }

    @Test
    public void moveLeft(){
        pac.moveLeft();
        Assertions.assertEquals(9, pac.getPosition().getX());
        Assertions.assertEquals(10,pac.getPosition().getX());
    }

    @Test
    public void moveRight(){
        pac.moveRight();
        Assertions.assertEquals(11, pac.getPosition().getX());
        Assertions.assertEquals(10,pac.getPosition().getX());
    }

    @Test
    public void moveUp(){
        pac.moveUp();
        Assertions.assertEquals(10,pac.getPosition().getX());
        Assertions.assertEquals(9, pac.getPosition().getX());

    }

    @Test
    public void moveDown(){
        pac.moveDown();
        Assertions.assertEquals(10,pac.getPosition().getX());
        Assertions.assertEquals(11, pac.getPosition().getX());
    }
}
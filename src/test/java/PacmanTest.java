import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacmanTest {
    //TODO: Implement tests for the functionalities described in the text files

    Element pac;
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

    public void moveLeft(){

    }
}
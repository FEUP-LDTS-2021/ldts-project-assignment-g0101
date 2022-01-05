import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.List;

public class Maze {
    private int width;
    private int height;

    private Pacman pacman;
    private List<Wall> walls;
    private List<Point> points;
    private List<Ghost> ghosts;

    private TextGraphics graphics;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;

        this.pacman = new Pacman(10,10);  //TODO: fix pacman starting coordinates
        this.walls = createMaze();
        this.points = createPointsList();
        this.ghosts = createGhostsList();
    }

    private List<Wall> createMaze() {
        //TODO: createMaze() functionality
        return null;
    }

    private List<Point> createPointsList() {
        //TODO: createPointsList() functionality
        return null;
    }

    private List<Ghost> createGhostsList() {
        //TODO: createGhostsList() functionality
        return null;
    }

    public void processKey(KeyStroke key) {
        //TODO: implement Pacman move
    }

    public void draw(TextGraphics newTextGraphics) {
        this.graphics = newTextGraphics;

        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#336699")); //TODO: change this later?
        this.graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        this.pacman.draw(graphics);

        /* //TODO: Un-comment these when their lists are created
        for (Wall wall : this.walls)
            wall.draw(graphics);

        for (Point point : this.points)
            point.draw(graphics);

        for (Ghost ghost : this.ghosts)
            ghost.draw(graphics);
        */
    }
}

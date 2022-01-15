import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class Maze implements Collision{
    private int width;
    private int height;

    private Pacman pacman;
    private List<Wall> walls;
    private List<Point> points;
    private List<Ghost> ghosts;

    private TextGraphics graphics;

    public Maze(char[][] gameMap) {
        //30 altura 28 largura pacman começa em 14,23
        this.height= gameMap.length;
        this.width = gameMap[0].length;

        this.pacman = new Pacman(13,16);  //TODO: fix pacman starting coordinates
        this.walls = createMaze(gameMap);
        this.points = createPointsList(gameMap);
        this.ghosts = createGhostsList();
    }

    private List<Wall> createMaze(char[][] gameMap) {//TODO: createMaze and createPointsList could maybe become one?
        List<Wall> mwalls = new ArrayList<>();

        for (int i = 0 ; i < height; i++){
            for (int j = 0; j < width; j++){
                if (gameMap[i][j] == '#') mwalls.add(new Wall(j,i));//System.out.println("wall found at :" + i + " " + j);
            }
        }

        return mwalls;
    }

    private List<Point> createPointsList(char[][] gameMap) {
        List<Point> mpoints = new ArrayList<>();


        for (int i = 0 ; i < height; i++){
            for (int j = 0; j < width; j++){
                if (gameMap[i][j] == '.') mpoints.add(new Point(j,i));
            }
        }
        return mpoints;
    }

    private List<Ghost> createGhostsList() {
        List<Ghost> mghosts = new ArrayList<>();

        mghosts.add(new Ghost(12,13));
        mghosts.add(new Ghost(13,13));
        mghosts.add(new Ghost(14,13));
        mghosts.add(new Ghost(15,13));

        return mghosts;
    }

    @Override
    public boolean characterCanMoveTo( Position position) {
        for(Wall wall : this.walls){
            System.out.println(wall.position.getX() + " " + wall.position.getY());
            if (wall.position.equals(position)) return false;
        }
        return true;
    }

    @Override
    public void characterInteractsWithElement(GameCharacter gameCharacter, Position position) {

    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft -> {
                pacman.moveLeft();
            }
            case ArrowRight -> {
                pacman.moveRight();
            }
            case ArrowUp -> {
                pacman.moveUp();
            }
            case ArrowDown -> {
                pacman.moveDown();
            }
        }
    }

    public void draw(TextGraphics newTextGraphics) {
        this.graphics = newTextGraphics;

        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#000000")); //TODO: change this later?
        this.graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');


        for (Wall wall : this.walls)
            wall.draw(graphics);

        for (Point point : this.points)
            point.draw(graphics);

        for (Ghost ghost : this.ghosts)
            ghost.draw(graphics);

        this.pacman.draw(graphics);
    }


}

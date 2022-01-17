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

        createMaze(gameMap);

    }

    private void createMaze(char[][] gameMap) {
        walls = new ArrayList<>();
        points = new ArrayList<>();
        ghosts = new ArrayList<>();

        for (int i = 0 ; i < height; i++){
            for (int j = 0; j < width; j++){
                if (gameMap[i][j] == '#') this.walls.add(new Wall(j,i));//System.out.println("wall found at :" + i + " " + j);
                else if (gameMap[i][j] == '.') this.points.add(new Point(j,i));
                else if (gameMap[i][j] == 'P') this.pacman = new Pacman(j,i);
                else if (gameMap[i][j] == 'F') this.ghosts.add(new Ghost(j,i));
            }
        }
    }


    @Override
    public boolean characterCanMoveTo( Position position) {
        for(Wall wall : this.walls){
            if (wall.position.equals(position)) return false;
        }
        return true;
    }

    @Override
    public boolean characterInteractsWithPoint(GameCharacter gameCharacter, Position position) {
        return true;
    }

    @Override
    public boolean characterInteractsWithEnemy(GameCharacter gameCharacter,Position position){
        return true;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft -> {
                if (characterCanMoveTo(new Position(pacman.position.getX() - 1,pacman.position.getY())))
                pacman.moveLeft();
            }
            case ArrowRight -> {
                if (characterCanMoveTo(new Position(pacman.position.getX() +1,pacman.position.getY())))
                pacman.moveRight();
            }
            case ArrowUp -> {
                if (characterCanMoveTo(new Position(pacman.position.getX() ,pacman.position.getY() - 1)))
                pacman.moveUp();
            }
            case ArrowDown -> {
                if (characterCanMoveTo(new Position(pacman.position.getX() ,pacman.position.getY() +1)))
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

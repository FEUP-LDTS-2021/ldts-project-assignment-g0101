import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze implements Collision{
    private int width;
    private int height;
    private int score;

    public Pacman pacman;
    private List<Wall> walls;
    private List<Point> points;
    private List<Ghost> ghosts;

    private TextGraphics graphics;

    public Maze(char[][] gameMap) {
        //30 altura 28 largura pacman começa em 14,23
        this.height= gameMap.length;
        this.width = gameMap[0].length;
        this.score = 0;
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
        if (gameCharacter.getClass() == Pacman.class){
            for( Point point : this.points){
                if (point.position.equals(position) ){
                    points.remove(point);
                    score+=100;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean characterInteractsWithEnemy(GameCharacter gameCharacter,Position position){

        return true;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft -> {
                if (characterCanMoveTo(new Position(pacman.position.getX() - 1,pacman.position.getY())))
                    if (checkEndMaze("Left")){

                    }
                    else pacman.moveLeft();
            }
            case ArrowRight -> {
                if (characterCanMoveTo(new Position(pacman.position.getX() +1,pacman.position.getY())))
                    if (checkEndMaze("Right")){

                    }
                    else pacman.moveRight();
            }
            case ArrowUp -> {
                if (characterCanMoveTo(new Position(pacman.position.getX() ,pacman.position.getY() - 1)))
                    if (checkEndMaze("Up")){

                    }
                    else pacman.moveUp();
            }
            case ArrowDown -> {
                if (characterCanMoveTo(new Position(pacman.position.getX() ,pacman.position.getY() +1))){
                    if (checkEndMaze("Down")){

                    } else pacman.moveDown();

                }
            }
        }
        characterInteractsWithPoint(pacman,pacman.position);

    }

    public boolean checkEndMaze(String dest) {
        Position pos = pacman.getPosition();
        switch (dest){
            case "Up":
                if(pos.getY()-1 < 0) return true;
                break;
            case "Down":
                if(pos.getY()+1 >= height) return true;
                break;
            case "Left":
                if(pos.getX()-1 < 0) return true;
                break;
            case "Right":
                if(pos.getX()+1 >= width) return true;
                break;
        }
        return false;
    }

    public void setPosOpposite(){

    }

    public void moveGhosts(){
        for(Ghost g: this.ghosts){
            Position pos = g.randomMove();
            while(!characterCanMoveTo(pos) || ghostThere(pos)){
                pos = g.randomMove();
            }
            g.setPosition(pos);
        }
    }

    private boolean ghostThere(Position pos) {
        for(Ghost g: ghosts){
            if(pos.equals(g.getPosition())) return true;
        }
        return false;
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

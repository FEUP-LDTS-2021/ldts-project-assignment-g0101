import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.lanterna.input.KeyStroke.*;

public class Maze implements Collision{
    private int width;
    private int height;
    private int score;
    private boolean endgame;

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
        endgame = false;

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
    public boolean characterCanMoveTo(Position position) {
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
    public boolean characterInteractsWithEnemy(String direction){
        Position characterPosition;
        switch(direction){
            case "Up":
                characterPosition = pacman.getUp();
                break;
            case "Down":
                characterPosition = pacman.getDown();
                break;
            case "Left":
                characterPosition = pacman.getLeft();
                break;
            default:
                characterPosition =pacman.getRight();
                break;
        }

        if(ghostThere(characterPosition)) return true;
        return false;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft -> {
                if(characterInteractsWithEnemy("Left")) pacmanDie();
                if (characterCanMoveTo(pacman.getLeft()))
                    if (checkEndMaze("Left")){
                        setPosOpposite("Left");
                    }
                    else pacman.moveLeft();
            }
            case ArrowRight -> {
                if(characterInteractsWithEnemy("Right")) pacmanDie();
                if (characterCanMoveTo(pacman.getRight()))
                    if (checkEndMaze("Right")){
                        setPosOpposite("Right");
                    }
                    else pacman.moveRight();
            }
            case ArrowUp -> {
                if (characterCanMoveTo(pacman.getUp())){
                    if(characterInteractsWithEnemy("Up")) pacmanDie();
                    if (checkEndMaze("Up")){
                        setPosOpposite("Up");
                    }
                    else pacman.moveUp();
                }

            }
            case ArrowDown -> {
                if(characterInteractsWithEnemy("Down")) pacmanDie();
                if (characterCanMoveTo(pacman.getDown())){
                    if (checkEndMaze("Down")){
                        setPosOpposite("Down");
                    } else pacman.moveDown();

                }
            }
        }
        characterInteractsWithPoint(pacman,pacman.position);
    }

    private void pacmanDie() {
        endgame = true;
    }



    public boolean checkEndMaze(String dest) {
        Position characterPosition = pacman.getPosition();
        switch (dest){
            case "Up":
                if(characterPosition.getY()-1 < 0) return true;
                break;
            case "Down":
                if(characterPosition.getY()+1 >= height) return true;
                break;
            case "Left":
                if(characterPosition.getX()-1 < 0) return true;
                break;
            case "Right":
                if(characterPosition.getX()+1 >= width) return true;
                break;
        }
        return false;
    }

    public void setPosOpposite(String move){
        Position characterPosition = pacman.getPosition();
        switch (move){
            case "Up":
                pacman.setPosition(new Position(characterPosition.getX(),height-1));
                break;
            case "Down":
                pacman.setPosition(new Position(characterPosition.getX(),0));
                break;
            case "Right":
                pacman.setPosition(new Position(0, characterPosition.getY()));
                break;
            case "Left":
                pacman.setPosition(new Position(width-1, characterPosition.getY()));
                break;
        }
    }

    public void moveGhosts(){
        for(Ghost g: this.ghosts){
            Position newGhostPosition = g.randomMove();
            while(!characterCanMoveTo(newGhostPosition) || ghostThere(newGhostPosition)){
                newGhostPosition = g.randomMove();
            }
            g.setPosition(newGhostPosition);
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

        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        this.graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');


        for (Wall wall : this.walls)
            wall.draw(graphics);

        for (Point point : this.points)
            point.draw(graphics);

        for (Ghost ghost : this.ghosts)
            ghost.draw(graphics);

        this.pacman.draw(graphics);
    }


    public boolean getState() {
        return this.endgame;
    }
}

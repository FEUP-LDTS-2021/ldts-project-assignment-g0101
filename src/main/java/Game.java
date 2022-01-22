import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.IOException;

public class Game {
    private Screen screen;
    private Maze maze;
    private final int fps;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(28, 30);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            this.screen = new TerminalScreen(terminal);

            this.screen.setCursorPosition(null);   // we don't need a cursor
            this.screen.startScreen();             // screens must be started
            this.screen.doResizeIfNecessary();     // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }
        MapReader mapReader = new MapReader(new File("basemaze.txt"));//TODO: Un-hardcode this
        this.maze = new Maze(mapReader.readMap());
        this.fps = 30;
    }

    public void run(){  //TODO : make this run on a different thread
        int frameTime = 1000 / this.fps;

        while(true){
            long startTime = System.currentTimeMillis();

            try {
                this.draw();
                /*KeyStroke key = screen.readInput();


                if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')){
                    this.screen.close();
                }
                else if (key.getKeyType() == KeyType.EOF){
                    break;
                }*/
                this.moveGhosts();
                //this.processKey(key);
                if(maze.getState()) this.screen.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }

        }
    }
    private void moveGhosts(){
        maze.moveGhosts();
    }
    private void processKey(KeyStroke key) {
        maze.processKey(key);
    }

    private void draw() throws IOException{
        this.screen.clear();
        this.maze.draw(screen.newTextGraphics());
        this.screen.refresh();
    }
}

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Maze maze;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
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

        this.maze = new Maze(40,20);  //TODO: fix Maze's dimensions
    }

    public void run(){  //TODO : make this run on a different thread
        while(true){
            try {
                this.draw();
                KeyStroke key = screen.readInput();

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                    this.screen.close();
                }
                else if (key.getKeyType() == KeyType.EOF){
                    break;
                }

                this.processKey(key);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

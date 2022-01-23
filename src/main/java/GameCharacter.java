public abstract class GameCharacter extends Element {
    protected String direction;
    public GameCharacter(int x, int y) {
        super(x, y);
        direction = "Right";
    }

    public void moveUp() {
        this.setPosition(new Position(position.getX(), position.getY() - 1));
        direction = "Up";
    }

    public void moveDown() {
        this.setPosition(new Position(position.getX(), position.getY() + 1));
        direction = "Down";
    }

    public void moveLeft() {
        this.setPosition(new Position(position.getX() - 1, position.getY()));
        direction = "Left";
    }

    public void moveRight() {
        this.setPosition(new Position(position.getX() + 1, position.getY()));
        direction = "Right";
    }

    public Position getUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position getDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position getLeft(){
        return new Position(position.getX() - 1, position.getY());
    }

    public Position getRight(){
        return new Position(position.getX() + 1, position.getY());
    }



}

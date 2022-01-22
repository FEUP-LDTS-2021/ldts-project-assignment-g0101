public abstract class GameCharacter extends Element {

    public GameCharacter(int x, int y) {
        super(x, y);
    }

    public void moveUp() {
        this.setPosition(new Position(position.getX(), position.getY() - 1));
    }

    public void moveDown() {
        this.setPosition(new Position(position.getX(), position.getY() + 1));
    }

    public void moveLeft() {
        this.setPosition(new Position(position.getX() - 1, position.getY()));
    }

    public void moveRight() {
        this.setPosition(new Position(position.getX() + 1, position.getY()));
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

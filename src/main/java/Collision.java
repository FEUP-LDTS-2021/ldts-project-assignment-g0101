public interface Collision {
    boolean characterCanMoveTo(Position position);
    boolean characterInteractsWithPoint(GameCharacter gameCharacter,Position position);
    boolean characterInteractsWithEnemy(GameCharacter gameCharacter,Position position);

}

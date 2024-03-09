package e1.piece;
import e1.utils.Pair;

public interface Piece {

    Pair<Integer, Integer> getPiecePosition();

    void setPiecePosition(Pair<Integer, Integer> newPiecePosition);

}

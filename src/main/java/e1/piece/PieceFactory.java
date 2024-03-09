package e1.piece;

import e1.utils.Pair;

public interface PieceFactory {

    MovablePiece createKnight(Pair<Integer, Integer> knightPosition);

    Piece createPawn(Pair<Integer, Integer> pawnPosition);

}

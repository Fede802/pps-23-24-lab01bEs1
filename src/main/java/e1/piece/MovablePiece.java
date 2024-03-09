package e1.piece;

import e1.utils.Pair;

public interface MovablePiece extends Piece {

    boolean moveTo(Pair<Integer, Integer> newPiecePosition);

}

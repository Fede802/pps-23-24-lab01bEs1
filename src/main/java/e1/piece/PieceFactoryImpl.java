package e1.piece;

import e1.utils.Pair;
import e1.piece.movement.KnightMovement;

public class PieceFactoryImpl implements PieceFactory {

    @Override
    public MovablePiece createKnight(Pair<Integer, Integer> knightPosition) {
        return new MovablePieceImpl(knightPosition, new KnightMovement());
    }

    @Override
    public Piece createPawn(Pair<Integer, Integer> pawnPosition) {
        return new PieceImpl(pawnPosition);
    }

}

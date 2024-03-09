package e1.piece;

import e1.utils.Pair;
import e1.piece.movement.Movement;

public class MovablePieceImpl implements MovablePiece {

    private final Piece piece;
    private final Movement movement;

    public MovablePieceImpl(Pair<Integer, Integer> piecePosition, Movement movement) {
        this.piece = new PieceImpl(piecePosition);
        this.movement = movement;
    }

    @Override
    public Pair<Integer, Integer> getPiecePosition() {
        return this.piece.getPiecePosition();
    }

    @Override
    public void setPiecePosition(Pair<Integer, Integer> newPiecePosition) {
        piece.setPiecePosition(newPiecePosition);
    }

    @Override
    public boolean moveTo(Pair<Integer, Integer> newPiecePosition) {
        int movementX = newPiecePosition.getX() - this.getPiecePosition().getX();
        int movementY = newPiecePosition.getY() - this.getPiecePosition().getY();
        Pair<Integer,Integer> move = new Pair<>(movementX, movementY);
        if(this.movement.isValidMovement(move)) {
            movement.makeMovement(this, move);
            return true;
        }
        return false;
    }

}

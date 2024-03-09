package e1.piece;

import e1.utils.Pair;
import e1.piece.movement.KnightMovement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovablePieceTest extends PieceTest{

    @Override
    protected Piece createPiece() {
        return new MovablePieceImpl(PIECE_POSITION,new KnightMovement());
    }

    @Test
    public void testMovement(){
        int newPieceX = this.piece.getPiecePosition().getX() + KnightMovement.VALID_MOVES.get(0).getX();
        int newPieceY = this.piece.getPiecePosition().getY() + KnightMovement.VALID_MOVES.get(0).getY();
        Pair<Integer, Integer> newPiecePosition = new Pair<>(newPieceX,newPieceY);
        boolean movementDone = ((MovablePiece) this.piece).moveTo(newPiecePosition);
        assertAll(
                () -> assertEquals(newPiecePosition, this.piece.getPiecePosition()),
                () -> assertTrue(movementDone)
        );

    }

    @Test
    public void testWrongMovement(){
        int newPieceX = this.piece.getPiecePosition().getX() + KnightMovement.INVALID_MOVE.getX();
        int newPieceY = this.piece.getPiecePosition().getY() + KnightMovement.INVALID_MOVE.getY();
        Pair<Integer, Integer> newPiecePosition = new Pair<>(newPieceX,newPieceY);
        boolean movementDone = ((MovablePiece) this.piece).moveTo(newPiecePosition);
        assertAll(
                () -> assertEquals(PIECE_POSITION, this.piece.getPiecePosition()),
                () -> assertFalse(movementDone)
        );
    }

}

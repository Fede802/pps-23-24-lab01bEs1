package e1.piece;

import e1.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class PieceTest {

    protected final static Pair<Integer, Integer> PIECE_POSITION = new Pair<>(5,-3);

    protected Piece piece;

    @BeforeEach
    void initPiece(){
        this.piece = this.createPiece();
    }

    protected abstract Piece createPiece();

    @Test
    void pieceCorrectlyCreated(){
        assertEquals(PIECE_POSITION,this.piece.getPiecePosition());
    }

    @Test
    void canSetPieceCoordinate(){
        Pair<Integer, Integer> newPiecePosition = new Pair<>(2,2);
        this.piece.setPiecePosition(newPiecePosition);
        assertEquals(newPiecePosition,this.piece.getPiecePosition());
    }

}

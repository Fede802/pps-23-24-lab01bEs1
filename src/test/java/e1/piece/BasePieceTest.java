package e1.piece;

public class BasePieceTest extends PieceTest{

    @Override
    protected Piece createPiece() {
        return new PieceImpl(PIECE_POSITION);
    }

}

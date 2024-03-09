package e1.piece;

import e1.utils.Pair;

public class PieceImpl implements Piece {

    private Pair<Integer,Integer> piecePosition;

    public PieceImpl(Pair<Integer,Integer> piecePosition) {
        this.piecePosition = piecePosition;
    }

    @Override
    public Pair<Integer, Integer> getPiecePosition() {
        return this.piecePosition;
    }

    @Override
    public void setPiecePosition(Pair<Integer, Integer> newPiecePosition) {
        this.piecePosition = newPiecePosition;
    }

}

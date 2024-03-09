package e1.board;

import e1.utils.Pair;
import e1.piece.MovablePiece;
import e1.piece.Piece;

import java.util.Optional;

public class BoardImpl implements Board {

    private final int boardSize;
    private final MovablePiece knight;
    private final Piece pawn;

    public BoardImpl(int boardSize) {
        this(boardSize,null,null);
    }

    public BoardImpl(int boardSize, MovablePiece knight, Piece pawn) throws IllegalArgumentException{
        this.boardSize = boardSize;
        this.checkPiecePosition(knight);
        this.knight = knight;
        this.checkPiecePosition(pawn);
        this.pawn = pawn;
    }

    private void checkPiecePosition(Piece piece){
        if(piece != null) {
            if (!this.isBoardPoint(piece.getPiecePosition())) {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public int getBoardSize() {
        return this.boardSize;
    }

    @Override
    public Optional<Pair<Integer, Integer>> getKnightPosition() {
        return this.knight != null ? Optional.of(this.knight.getPiecePosition()) :Optional.empty();
    }

    @Override
    public Optional<Pair<Integer, Integer>> getPawnPosition() {
        return this.pawn != null ? Optional.of(this.pawn.getPiecePosition()) :Optional.empty();
    }

    @Override
    public boolean moveKnightTo(Pair<Integer, Integer> newKnightPosition) throws IllegalStateException {
        if(this.knight == null){
            throw new IllegalStateException();
        }
        if(!this.isBoardPoint(newKnightPosition)){
            throw new IndexOutOfBoundsException();
        }
        return this.knight.moveTo(newKnightPosition);
    }

    @Override
    public boolean isBoardPoint(Pair<Integer, Integer> point) {
        return point.getX() >= 0 && point.getY() >= 0 && point.getX() < boardSize && point.getY() < boardSize;
    }
}

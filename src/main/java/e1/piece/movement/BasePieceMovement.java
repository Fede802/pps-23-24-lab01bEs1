package e1.piece.movement;


import e1.utils.Pair;
import e1.piece.Piece;

public abstract class BasePieceMovement implements Movement {

    @Override
    public void makeMovement(Piece piece, Pair<Integer, Integer> move) throws IllegalArgumentException{
        if(!isValidMovement(move)){
            throw new IllegalArgumentException();
        }
        int newPieceX = piece.getPiecePosition().getX()+move.getX();
        int newPieceY = piece.getPiecePosition().getY()+move.getY();
        piece.setPiecePosition(new Pair<>(newPieceX,newPieceY));
    }

}

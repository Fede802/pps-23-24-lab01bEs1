package e1;

import e1.board.Board;
import e1.board.BoardFactoryImpl;
import e1.utils.Pair;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Board board;
	 
    public LogicsImpl(int boardSize){
    	this.board = new BoardFactoryImpl().createBoardWithRandomPiecePosition(boardSize);
    }

	public LogicsImpl(int boardSize, Pair<Integer, Integer> knightPosition, Pair<Integer, Integer> pawnPosition) {
		this.board = new BoardFactoryImpl().createBoardWithPiecePositions(boardSize, knightPosition, pawnPosition );
	}

	private void checkInputPosition(int row, int col) {
		Pair<Integer,Integer> boardPoint = new Pair<>(row, col);
		if(!this.board.isBoardPoint(boardPoint)){
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public boolean hit(int row, int col) {
		this.board.moveKnightTo(new Pair<>(row,col));
		return this.board.getKnightPosition().equals(this.board.getPawnPosition());
	}

	@Override
	public boolean hasKnight(int row, int col) throws IndexOutOfBoundsException{
		this.checkInputPosition(row, col);
		return this.board.getKnightPosition().orElseThrow().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) throws IndexOutOfBoundsException {
		this.checkInputPosition(row, col);
		return this.board.getPawnPosition().orElseThrow().equals(new Pair<>(row,col));
	}

}

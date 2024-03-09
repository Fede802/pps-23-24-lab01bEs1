package e1.board;

import e1.utils.Pair;
import e1.piece.PieceFactory;
import e1.piece.PieceFactoryImpl;
import e1.utils.PositionGenerator;
import e1.utils.RandomPositionGenerator;

public class BoardFactoryImpl implements BoardFactory {

    @Override
    public Board createEmptyBoard(int boardSize) {
        return new BoardImpl(boardSize);
    }

    @Override
    public Board createBoardWithPiecePositions(int boardSize, Pair<Integer, Integer> knightPosition, Pair<Integer, Integer> pawnPosition) {
        PieceFactory pieceFactory = new PieceFactoryImpl();
        return new BoardImpl(boardSize, pieceFactory.createKnight(knightPosition), pieceFactory.createPawn(pawnPosition));
    }

    @Override
    public Board createBoardWithRandomPiecePosition(int boardSize) {
        PositionGenerator positionGenerator = new RandomPositionGenerator();
        Pair<Integer, Integer> knightPosition = positionGenerator.generatePosition(boardSize);
        Pair<Integer, Integer> pawnPosition = positionGenerator.generatePosition(boardSize);
        while(pawnPosition.equals(knightPosition)){
            pawnPosition = positionGenerator.generatePosition(boardSize);
        }
        return this.createBoardWithPiecePositions(boardSize,knightPosition,pawnPosition);
    }
}

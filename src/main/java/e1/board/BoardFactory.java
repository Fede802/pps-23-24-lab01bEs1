package e1.board;

import e1.utils.Pair;

public interface BoardFactory {

    Board createEmptyBoard(int boardSize);

    Board createBoardWithPiecePositions(int boardSize, Pair<Integer, Integer> knightPosition, Pair<Integer, Integer> pawnPosition);

    Board createBoardWithRandomPiecePosition(int boardSize);

}

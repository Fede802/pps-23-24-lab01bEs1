package e1.board;

import e1.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardFactoryTest {

    private final static int BOARD_SIZE = 5;
    private BoardFactory boardFactory;

    @BeforeEach
    void initBoardFactory(){
        this.boardFactory = new BoardFactoryImpl();
    }

    @Test
    void emptyBoardCorrectlyCreated(){
        Board board = this.boardFactory.createEmptyBoard(BOARD_SIZE);
        assertAll(
                () -> assertTrue(board.getKnightPosition().isEmpty()),
                () -> assertTrue(board.getPawnPosition().isEmpty())
        );
    }

    @Test
    void boardWithPresetPieceCorrectlyCreated(){
        Pair<Integer,Integer> knightPosition = new Pair<>(0,0);
        Pair<Integer,Integer> pawnPosition = new Pair<>(1,1);
        Board board = this.boardFactory.createBoardWithPiecePositions(BOARD_SIZE,knightPosition,pawnPosition);
        assertAll(
                () -> assertEquals(knightPosition, board.getKnightPosition().orElseThrow()),
                () -> assertEquals(pawnPosition, board.getPawnPosition().orElseThrow())
        );
    }

    @Test
    void boardWithRandomPiecePositionCorrectlyCreated(){
        assertDoesNotThrow(() -> this.boardFactory.createBoardWithRandomPiecePosition(BOARD_SIZE));
    }

}

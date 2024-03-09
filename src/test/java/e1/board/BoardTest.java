package e1.board;

import e1.piece.movement.KnightMovement;
import e1.utils.Pair;
import e1.piece.PieceFactory;
import e1.piece.PieceFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private final static int BOARD_SIZE = 5;
    private Board board;

    @BeforeEach
    void initBoard(){
        this.board = new BoardImpl(BOARD_SIZE);
    }

    @Test
    void boardSizeSetCorrectly(){
        assertEquals(BOARD_SIZE, this.board.getBoardSize());
    }

    @Test
    void boardIsInitiallyWithoutPiece(){
        assertAll(
                () -> assertTrue(this.board.getKnightPosition().isEmpty()),
                () -> assertTrue(this.board.getPawnPosition().isEmpty())
        );
    }

    @Test
    void setupBoardWithPiece(){
        PieceFactory pieceFactory = new PieceFactoryImpl();
        Pair<Integer,Integer> knightPosition = new Pair<>(3,3);
        Pair<Integer,Integer> pawnPosition = new Pair<>(4,2);
        this.board = new BoardImpl(BOARD_SIZE,pieceFactory.createKnight(knightPosition),pieceFactory.createPawn(pawnPosition));
        assertAll(
                () -> assertTrue(this.board.getKnightPosition().isPresent()),
                () -> assertEquals(knightPosition, this.board.getKnightPosition().orElseThrow()),
                () -> assertTrue(this.board.getPawnPosition().isPresent()),
                () -> assertEquals(pawnPosition, this.board.getPawnPosition().orElseThrow())
        );
    }

    @Test
    void setupBoardWithWrongPiecePosition(){
        PieceFactory pieceFactory = new PieceFactoryImpl();
        Pair<Integer,Integer> knightPosition = new Pair<>(-1,2);
        Pair<Integer,Integer> pawnPosition = new Pair<>(4,BOARD_SIZE);

        assertThrows(IllegalArgumentException.class, () -> new BoardImpl(BOARD_SIZE,pieceFactory.createKnight(knightPosition),pieceFactory.createPawn(pawnPosition)));
    }

    @Test
    void moveKnightWithoutPiece(){
        assertThrows(IllegalStateException.class, () -> this.board.moveKnightTo(new Pair<>(2,2)));
    }

    @Test
    void moveKnightOutOfBoard(){
        PieceFactory pieceFactory = new PieceFactoryImpl();
        Pair<Integer,Integer> knightPosition = new Pair<>(3,3);
        Pair<Integer,Integer> pawnPosition = new Pair<>(4,2);
        this.board = new BoardImpl(BOARD_SIZE,pieceFactory.createKnight(knightPosition),pieceFactory.createPawn(pawnPosition));
        assertThrows(IndexOutOfBoundsException.class, () -> this.board.moveKnightTo(new Pair<>(BOARD_SIZE, -1)));
    }

    @Test
    void checkBoardPositions(){
        assertAll(

                () -> assertTrue(this.board.isBoardPoint(new Pair<>(2,2))),
                () -> assertFalse(this.board.isBoardPoint(new Pair<>(-1,0))),
                () -> assertFalse(this.board.isBoardPoint(new Pair<>(0,-1))),
                () -> assertFalse(this.board.isBoardPoint(new Pair<>(BOARD_SIZE,0))),
                () -> assertFalse(this.board.isBoardPoint(new Pair<>(0,BOARD_SIZE)))

        );
    }

    @Test
    void checkKnightMovement(){
        PieceFactory pieceFactory = new PieceFactoryImpl();
        Pair<Integer,Integer> knightPosition = new Pair<>(3,3);
        Pair<Integer,Integer> pawnPosition = new Pair<>(4,2);
        this.board = new BoardImpl(BOARD_SIZE,pieceFactory.createKnight(knightPosition),pieceFactory.createPawn(pawnPosition));
        assertAll(
                ()->assertTrue(this.board.moveKnightTo(new Pair<>(
                        this.board.getKnightPosition().orElseThrow().getX() + KnightMovement.VALID_MOVES.get(0).getX(),
                        this.board.getKnightPosition().orElseThrow().getY() + KnightMovement.VALID_MOVES.get(0).getY()))),
                ()->assertFalse(this.board.moveKnightTo(new Pair<>(
                        this.board.getKnightPosition().orElseThrow().getX() + KnightMovement.INVALID_MOVE.getX(),
                        this.board.getKnightPosition().orElseThrow().getY() + KnightMovement.INVALID_MOVE.getY())))
        );
    }

}

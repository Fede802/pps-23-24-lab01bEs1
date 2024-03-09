package e1;
import e1.board.BoardFactory;
import e1.board.BoardFactoryImpl;
import e1.board.BoardFactoryTest;
import e1.utils.Pair;
import e1.utils.RandomPositionGenerator;
import org.junit.jupiter.api.*;

import java.util.function.BiPredicate;

import static e1.piece.movement.KnightMovement.VALID_MOVES;
import static org.junit.jupiter.api.Assertions.*;
public class LogicTest {

  private final static int BOARD_SIZE = 5;
  private Logics logics;

  private boolean findPieceOnBoard(BiPredicate<Integer,Integer> pieceFinder){
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if(pieceFinder.test(i,j)){
          return true;
        }
      }
    }
    return false;
  }

  private void checkPossibleMoves(int currentKnightX, int currentKnightY) {
    this.executeValidMoves(currentKnightX,currentKnightY);
    this.checkInvalidMoves(currentKnightX,currentKnightY);
  }

  private void executeValidMoves(int currentKnightX, int currentKnightY) {

    VALID_MOVES.stream().filter(
            (move) -> !this.isNotValidBoardPosition(currentKnightX + move.getX(), currentKnightY + move.getY())
    ).forEach(
            (move)-> {
              this.logics = new LogicsImpl(BOARD_SIZE, new Pair<>(currentKnightX,currentKnightY), new Pair<>(0,0));
              this.logics.hit(currentKnightX + move.getX(), currentKnightY + move.getY());
            }
    );
  }

  private void checkInvalidMoves(int currentKnightX, int currentKnightY) {
    assertAll(
            VALID_MOVES.stream().filter(
                    (move) -> this.isNotValidBoardPosition(currentKnightX + move.getX(), currentKnightY + move.getY())
            ).map(
                    (move) ->
                            () -> {
                              assertThrows(IndexOutOfBoundsException.class,()->this.logics.hit(currentKnightX+move.getX(), currentKnightY+move.getY()));
                            }
            )
    );
  }

  private boolean isNotValidBoardPosition(int boardX, int boardY){
    return boardX < 0 || boardY < 0 || boardX >= BOARD_SIZE || boardY >= BOARD_SIZE;
  }

  @BeforeEach
  public void initBoard(){
    this.logics = new LogicsImpl(BOARD_SIZE);
  }

  @Test
  public void knightAndPawnInitiallyPlacedOnBoard(){
    assertAll(
            () -> assertTrue(this.findPieceOnBoard((row,col)->this.logics.hasKnight(row,col))),
            () -> assertTrue(this.findPieceOnBoard((row,col)->this.logics.hasPawn(row,col)))
    );
  }

  @Test
  public void knightAndPawnPositioning(){
    Pair<Integer, Integer> knightPosition = new Pair<>(0,2);
    Pair<Integer, Integer> pawnPosition = new Pair<>(1,3);
    this.logics = new LogicsImpl(BOARD_SIZE,knightPosition,pawnPosition);
    assertAll(
            () -> assertTrue(this.logics.hasKnight(knightPosition.getX(), knightPosition.getY())),
            () -> assertTrue(this.logics.hasPawn(pawnPosition.getX(), pawnPosition.getY()))
    );
  }

  @Test
  public void knightAndPawnWrongPositioning(){
    Pair<Integer, Integer> wrongPosition = new Pair<>(-1, BOARD_SIZE);
    assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(BOARD_SIZE, wrongPosition, wrongPosition));
  }

  @Test
  public void knightAndPawnOutOfBoardSearch(){
    int wrongRow = -1;
    int wrongColumn = BOARD_SIZE;
    assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hasKnight(wrongRow,wrongColumn)),
            () -> assertThrows(IndexOutOfBoundsException.class, () ->this.logics.hasPawn(wrongRow,wrongColumn))
    );
  }

  @Test
  public void moveKnight(){
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        this.checkPossibleMoves(i,j);
      }
    }
  }

  @Test
  public void checkWinningSituation(){
    int knightX = 0;
    int knightY = 0;
    int pawnX = 1;
    int pawnY = 2;
    this.logics = new LogicsImpl(BOARD_SIZE, new Pair<>(knightX,knightY), new Pair<>(pawnX,pawnY));
    assertTrue(this.logics.hit(pawnX,pawnY));
  }

}

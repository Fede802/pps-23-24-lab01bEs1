package e1.board;

import e1.utils.Pair;

import java.util.Optional;

public interface Board {

    int getBoardSize();

    Optional<Pair<Integer,Integer>> getKnightPosition();

    Optional<Pair<Integer,Integer>> getPawnPosition();

    boolean moveKnightTo(Pair<Integer, Integer> newKnightPosition) throws IllegalStateException;

    boolean isBoardPoint(Pair<Integer, Integer> point);

}

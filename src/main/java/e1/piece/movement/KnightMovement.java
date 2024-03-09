package e1.piece.movement;


import e1.utils.Pair;

import java.util.Arrays;
import java.util.List;

public class KnightMovement extends BasePieceMovement {

    public final static List<Pair<Integer,Integer>> VALID_MOVES = Arrays.asList(
            new Pair<>(-2,-1),
            new Pair<>(-1,-2),
            new Pair<>(-2,1),
            new Pair<>(-1,2),
            new Pair<>(2,-1),
            new Pair<>(1,-2),
            new Pair<>(2,1),
            new Pair<>(1,2));

    public final static Pair<Integer,Integer> INVALID_MOVE = new Pair<>(2,2);

    @Override
    public boolean isValidMovement(Pair<Integer, Integer> move) {
        return move.getX() != 0 && move.getY() != 0 && Math.abs(move.getX())+Math.abs(move.getY()) == 3;
    }

}

package e1.utils;




public interface PositionGenerator {

    Pair<Integer,Integer> generatePosition(int minIndex, int maxIndex);

    Pair<Integer,Integer> generatePosition(int maxIndex);

}

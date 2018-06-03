package com.hatchrun.game.Utilities;

/**
 * A class representing a pair
 * @param <F> First member's type
 * @param <S> Second member's type
 */
public class Pair<F, S>  {

        private F first; //first member of pair
        private S second; //second member of pair

    /**
     * Constructs a pair
     * @param first First member
     * @param second Second member
     */
    public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }


    /**
     * Returns first member
     * @return First member
     */
    public F getFirst() {
            return first;
        }

    /**
     * Returns second member
     * @return Second member
     */
    public S getSecond() {
            return second;
        }

}

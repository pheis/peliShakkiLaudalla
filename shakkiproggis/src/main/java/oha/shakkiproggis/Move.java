/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import java.util.BitSet;

/**
 * This class represents moves. This is actually a pair of squares.
 * @author pyry
 */
public class Move {
	/**
	 * starting point of the move.
	 */
	public final Square first;
	/**
	 * Ending point of the move.
	 */
	public final Square second;
	/**
	 * A chess move.
	 * @param a starting point.
	 * @param b end point.
	 */
	public Move(Square a, Square b) {
		this.first = a;
		this.second = b;
	}
	/**
	 * Transforms move to a unique long integer.
	 * @return a number that is used to dedect repetition of moves.
	 */
	public long toLong() {
		int i = first.ordinal();
		int j = second.ordinal();
		long a = 1 << i;
		long b = 1 << j;
		return a + b;
	}
/**
 * String.
 * @return as a string.
 */
	@Override
	public String toString() {
		return this.first.toString() + this.second.toString();
	}
}

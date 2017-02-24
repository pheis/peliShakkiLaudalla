/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

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
	 * chess move.
	 * @param a starting point.
	 * @param b end point.
	 */
	public Move(Square a, Square b) {
		this.first = a;
		this.second = b;
	}
	@Override
	public String toString() {
		return this.first.toString() + this.second.toString();
	}
}

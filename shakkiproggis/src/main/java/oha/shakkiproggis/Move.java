/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

/**
 *
 * @author pyry
 */
public class Move {
	public final Square first;
	public final Square second;
	public Move(Square a, Square b) {
		this.first = a;
		this.second = b;
	}
	@Override
	public String toString() {
		return this.first.toString()+this.second.toString();
	}
}

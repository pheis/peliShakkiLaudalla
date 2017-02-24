/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

/**
 * Possible promotions for a pawn.
 * @author pyry
 */
// Because there is no way to extend an enum in Java.
public enum PromoTarget {
	QUEEN, BISHOP, ROOK, KNIGHT;
}
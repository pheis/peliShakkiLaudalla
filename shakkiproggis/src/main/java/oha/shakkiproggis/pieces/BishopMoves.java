/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.pieces;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.pieces.Slideable;
import java.util.stream.Stream;
import oha.shakkiproggis.Square;

/**
 * Provides bishop moves for bishop and queen.
 * @author pyry
 */
public interface BishopMoves extends Slideable {
	/**
	 * Squares whre bishop attacks.
	 * @param s starting square
	 * @param my my pieces
	 * @param enemy enemy pieecs
	 * @return squares where bishop attacks.
	 */
	public default Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy) {
		Stream<Square> leftD = slideOn((x, y)-> Square.onSameLeftDiagonal(x, y), s, my, enemy);
		Stream<Square> rightD = slideOn((x, y)-> Square.onSameRightDiagonal(x, y), s, my, enemy);
		return Stream.concat(leftD, rightD);
	}
}

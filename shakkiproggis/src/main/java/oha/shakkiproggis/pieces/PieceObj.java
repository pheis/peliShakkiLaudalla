/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.pieces;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.Square;
import java.util.stream.Stream;

/**
 * Interface that represents piece.
 * @author pyry
 */
public interface PieceObj {
	/**
	 * attacked squares by this piece.
	 * @param s Square in which the piece is.
	 * @param my my piece set.
	 * @param enemy enemy piece set.
	 * @return attacked squares.
	 */
	public Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy);
	/**
	 * Moves of the piece.
	 * @param s starting square.
	 * @param my piece set.
	 * @param enemy piece set.
	 * @return moves.
	 */
	public default Stream<Square> moves(Square s, PieceGroup my, PieceGroup enemy) {
		return attacks(s, my, enemy)
			.filter(x -> !(my.squares().anyMatch(y -> (x == y))));

	}
	
}

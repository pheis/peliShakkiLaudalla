/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.pieces;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.pieces.Slideable;
import oha.shakkiproggis.Square;
import java.util.stream.Stream;

/**
 * Rook moves and attacks.
 * @author pyry
 */
public interface RookMoves extends Slideable {
	/**
	 * Rook attacking squares.
	 * @param s starting square
	 * @param my piece set that is on the same side as rook
	 * @param enemy piece set that is enemy to the rook.
	 * @return stream of attacked squares.
	 */
	public default Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy) {
		Stream<Square> rank = slideOn((x, y) -> Square.onSameRank(x, y), s, my, enemy);
		Stream<Square> file = slideOn((x, y) -> Square.onSameFile(x, y), s, my, enemy);
		return Stream.concat(rank, file);
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.Piece.Slideable;
import oha.shakkiproggis.Square;
import java.util.stream.Stream;

/**
 *
 * @author pyry
 */
public interface RookMoves extends Slideable {
	public default Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy) {
		Stream<Square> rank = slideOn((x, y) -> Square.onSameRank(x, y), s, my, enemy);
		Stream<Square> file = slideOn((x, y) -> Square.onSameFile(x, y), s, my, enemy);
		return Stream.concat(rank, file);
	}
}

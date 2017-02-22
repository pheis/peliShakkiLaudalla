/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.Square;
import java.util.stream.Stream;

/**
 *
 * @author pyry
 */
public interface PieceObj {

	public Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy);
	
	public default Stream<Square> moves(Square s, PieceGroup my, PieceGroup enemy) {
		return attacks(s, my, enemy)
			.filter(x -> !(my.squares().anyMatch(y -> (x == y))));

	}
	
}

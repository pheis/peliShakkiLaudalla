/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;

import oha.shakkiproggis.PieceGroup;
import java.util.stream.Stream;
import oha.shakkiproggis.Square;
import oha.shakkiproggis.Piece.PieceObj;

/**
 *
 * @author pyry
 */
public class Knight implements PieceObj {

	@Override
	public Stream<Square> attacks(Square s, PieceGroup friends, PieceGroup enemy) {
		return Square.allSquares()
			.filter((Square x) -> (Square.manhattanDistance(s, x) == 3))
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 2));
	}	
}

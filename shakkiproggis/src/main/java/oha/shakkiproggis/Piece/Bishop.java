/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;

import oha.shakkiproggis.PieceGroup;
import java.util.stream.Stream;
import oha.shakkiproggis.Square;

/**
 *
 * @author pyry
 */
public class Bishop implements BishopMoves, PieceObj {	

	@Override
	public Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy) {
		return BishopMoves.super.attacks(s, my, enemy);
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.pieces;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.pieces.RookMoves;
import oha.shakkiproggis.Square;
import java.util.stream.Stream;

/**
 * Queen.
 * @author pyry
 */
public class Queen implements RookMoves, BishopMoves, PieceObj {
	@Override
	public Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy) {
		return Stream.concat(RookMoves.super.attacks(s, my, enemy), BishopMoves.super.attacks(s, my, enemy));
	}
}


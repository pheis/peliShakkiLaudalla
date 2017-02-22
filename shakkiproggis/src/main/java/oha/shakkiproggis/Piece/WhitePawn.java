/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.Square;
import oha.shakkiproggis.Piece.Pawn;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 *
 * @author pyry
 */
public class WhitePawn implements Pawn {
	
	BiPredicate<Square, Square> bp = Square.gt;

	@Override
	public Stream<Square> attacks(Square s, PieceGroup friends, PieceGroup enemy) {
		return Pawn.super.attacks(s, friends, enemy, bp);
	}

	@Override
	public Stream<Square> moves(Square s, PieceGroup friends, PieceGroup enemy) {
		return Pawn.super.moves(s, friends, enemy, bp);
	}	
}

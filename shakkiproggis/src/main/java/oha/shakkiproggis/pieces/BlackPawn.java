/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.pieces;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.pieces.Pawn;
import java.util.function.BiPredicate;
import java.util.stream.Stream;
import oha.shakkiproggis.Square;
import oha.shakkiproggis.pieces.PieceObj;

/**
 *
 * @author pyry
 */
public class BlackPawn implements Pawn, PieceObj {

	final BiPredicate<Square, Square> bp = Square.lt;

	@Override
	public Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy) {
		return Pawn.super.attacks(s, my, enemy, bp);
	}

	@Override
	public Stream<Square> moves(Square s, PieceGroup my, PieceGroup enemy) {
		return Pawn.super.moves(s, my, enemy, bp); //To change body of generated methods, choose Tools | Templates.
	}
	
}

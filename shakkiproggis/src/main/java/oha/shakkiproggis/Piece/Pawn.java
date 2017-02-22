/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.Square;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 *
 * @author pyry
 */
public interface Pawn extends Slideable, PieceObj {
	default Stream<Square> attacks(Square s, PieceGroup friends, PieceGroup enemy, BiPredicate<Square, Square> ltORgt) {
		return Square.allSquares()
			.filter((Square x) -> (Square.manhattanDistance(s, x) == 2))
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 1))
			.filter((Square x) -> (ltORgt.test(x, s)));
	}
	
	default Stream<Square> moves(Square s, PieceGroup my, PieceGroup enemy, BiPredicate<Square, Square> ltORgt) {
		int moveDist = (Square.onSameRank(s, Square.A2) || Square.onSameRank(s, Square.A7)) ? 2 : 1;
		Stream<Square> attackMoves = attacks(s, my, enemy, ltORgt)
								.filter(x -> (enemy.squares().anyMatch(y -> y == x)));
		
		Stream<Square> moves = slideOn((x, y)->Square.onSameFile(x, y), s, my, enemy)
			.filter((Square x) -> (ltORgt.test(x, s)))
			.filter((Square x) -> (Square.euclidianDistance(x, s) <= moveDist))
			.filter((Square x) -> !(my.squares().anyMatch(y -> y == x)))
			.filter(x -> !(enemy.squares().anyMatch(y -> (x == y))));
		
		return Stream.concat(Stream.concat(attackMoves, moves), enPassant(s, enemy, ltORgt));
	}
	
	// Enpassant IS NOT AN ATTACK!!!. IT is not directed to king. IT is a MOVE.
	// If white, biPredicate is (greaterThan), else (lesserThan)
	
	default Stream<Square> enPassant(Square s, PieceGroup enemy, BiPredicate<Square, Square> ltORgt) {

		Function<Square, Square> fun = (ltORgt.test(Square.H8, Square.A1)) ? Square.rankPlus : Square.rankMinus;
		
		Stream<Square> epp = enemy.enpassPawn.isPresent() ? 
			enemy.squares()
				.filter(x -> (Square.isSideBySide(x, s)))
				.filter(x -> (x == enemy.enpassPawn.get()))
				.map(x -> fun.apply(x)) : Stream.empty();

		
		//Square epSquare = (ltORgt.test(Square.H8, Square.A1)) ? Square.rankPlus(epp.get()) : Square.rankMinus(epp.get());
		
		//Square epSquare = (ltORgt.test(Square.H8, Square.A1)) ? Square.rankPlus(s) : Square.rankMinus(s);
		
		
		
		return epp;
	}

}
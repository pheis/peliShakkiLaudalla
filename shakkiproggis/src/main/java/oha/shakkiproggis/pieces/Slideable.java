/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.pieces;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.Square;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 * An interface for sliding pieces. Rook, Queen, Bishop. Also Pawn's first move if double move is used.
 * @author pyry
 */
public interface Slideable {
	
	/**
	 * Square with the smallest ordinal in which piece can still move.
	 * @param onSame on same diagonal or on same file or on same rank.
	 * @param s starting square
	 * @param friends my pieces 
	 * @param enemy enemy pieces
	 * @return infimum of the slide. The square with the smallest ordinal in chich the piece can still slide.
	 */
	default Optional<Integer> infimum(BiPredicate<Square, Square> onSame, Square s, PieceGroup friends, PieceGroup enemy) {
		return Stream.concat(friends.squares(), enemy.squares())
			.filter((Square x) -> (onSame.test(x, s)))
			.filter((Square x) -> (x.ordinal() < s.ordinal()))
			.map((Square x) -> x.ordinal())
			.reduce((x, y) -> Math.max(x, y));
	}
	/**
	 * Square with the greatest ordinal in which piece can still move.
	 * @param onSame bipredicate function: diagonal, rank or file.
	 * @param s starting square
	 * @param friends my pieces.
	 * @param enemy enemy pieces.
	 * @return supremum of the slide. Square with the largest ordinal that we can still slide.
	 */
	default Optional<Integer> supremum(BiPredicate<Square, Square> onSame, Square s, PieceGroup friends, PieceGroup enemy) {
		return Stream.concat(friends.squares(), enemy.squares())
			.filter((Square x) -> (onSame.test(x, s)))
			.filter((Square x) -> (x.ordinal() > s.ordinal()))
			.map((Square x) -> x.ordinal())
			.reduce((x, y) -> Math.min(x, y));
	}
	/**
	 * Squares where piece can slide.
	 * @param onSame bipredicate function: diagonal, rank or file.
	 * @param s starting square.
	 * @param friends my pieces.
	 * @param enemy enemy pieces.
	 * @return A stream of squares where the piece that slides can slide. 
	 */
	default Stream<Square> slideOn(BiPredicate<Square, Square> onSame, Square s, PieceGroup friends, PieceGroup enemy) {
		return Square.allSquares()
			.filter((Square x) -> (x != s))
			.filter((Square x) -> (onSame.test(s, x)))
			.filter((Square x) -> (x.ordinal() >= infimum(onSame, s, friends, enemy).orElse(Square.A1.ordinal())))
			.filter((Square x) -> (x.ordinal() <= supremum(onSame, s, friends, enemy).orElse(Square.H8.ordinal())));
	}
	
}

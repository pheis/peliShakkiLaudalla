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
import java.util.stream.Stream;

/**
 *
 * @author pyry
 */
public interface Slideable {
	
	
	default Optional<Integer> infimum(BiPredicate<Square, Square> onSame, Square s, PieceGroup friends, PieceGroup enemy) {
		return Stream.concat(friends.squares(), enemy.squares())
			.filter((Square x) -> (onSame.test(x, s)))
			.filter((Square x) -> (x.ordinal() < s.ordinal()))
			.map((Square x) -> x.ordinal())
			.reduce((x, y) -> Math.max(x, y));
	}
	
	default Optional<Integer> supremum(BiPredicate<Square, Square> onSame, Square s, PieceGroup friends, PieceGroup enemy) {
		return Stream.concat(friends.squares(), enemy.squares())
			.filter((Square x) -> (onSame.test(x, s)))
			.filter((Square x) -> (x.ordinal() > s.ordinal()))
			.map((Square x) -> x.ordinal())
			.reduce((x, y) -> Math.min(x, y));
	}
	
	default Stream<Square> slideOn(BiPredicate<Square, Square> onSame, Square s, PieceGroup friends, PieceGroup enemy) {
		return Square.allSquares()
			.filter((Square x) -> (x != s))
			.filter((Square x) -> (onSame.test(s, x)))
			.filter((Square x) -> (x.ordinal() >= infimum(onSame, s, friends, enemy).orElse(Square.A1.ordinal())))
			.filter((Square x) -> (x.ordinal() <= supremum(onSame, s, friends, enemy).orElse(Square.H8.ordinal())));
	}
	
}

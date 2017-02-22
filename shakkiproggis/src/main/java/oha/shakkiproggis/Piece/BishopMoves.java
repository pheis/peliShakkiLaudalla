/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;

import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.Piece.Slideable;
import java.util.stream.Stream;
import oha.shakkiproggis.Square;

/**
 *
 * @author pyry
 */
public interface BishopMoves extends Slideable {
	public default Stream<Square> attacks(Square s, PieceGroup my, PieceGroup enemy) {
		Stream<Square> leftD = slideOn((x, y)-> Square.onSameLeftDiagonal(x, y), s, my, enemy);
		Stream<Square> rightD = slideOn((x, y)-> Square.onSameRightDiagonal(x, y), s, my, enemy);
		return Stream.concat(leftD, rightD);
	}
}

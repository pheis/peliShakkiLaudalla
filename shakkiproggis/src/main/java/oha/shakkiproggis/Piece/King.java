/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;

import oha.shakkiproggis.PieceGroup;
import java.util.EnumMap;
import java.util.stream.Stream;
import oha.shakkiproggis.Square;
import oha.shakkiproggis.Piece.PieceObj;

/**
 *
 * @author pyry
 */
public class King implements PieceObj {


	@Override
	public Stream<Square> attacks(Square s, PieceGroup friends, PieceGroup enemy) {
		return Square.allSquares()
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 1));
	}

	@Override
	public Stream<Square> moves(Square s, PieceGroup friends, PieceGroup enemy) {
		Stream<Square> castle = Stream.concat(shortCastling(friends, enemy), longCastling(friends, enemy));
		Stream<Square> normMoves;
		normMoves = PieceObj.super.moves(s, friends, enemy)
			.filter((Square x) -> !(enemy.attacksTo(friends).anyMatch((Square y) -> (y == x))));
		return Stream.concat(normMoves, castle);
	}
	
	public boolean inCheck(Square s, PieceGroup friends, PieceGroup enemy) {
		return enemy.attacksTo(friends)
			.filter(x -> x == s)
			.findAny()
			.isPresent();
	}
	
	private Stream<Square> shortCastling(PieceGroup my, PieceGroup enemy) {
		if ( !(my.castling[0]) || inCheck(my.king, my, enemy)) {
			return Stream.empty();
		} else {
			if (my.king == Square.E1) {	
				if (Stream.concat(my.squares(), enemy.attacksTo(my)).filter(x -> (x == Square.F1 || x == Square.G1))
								.findAny()
								.isPresent()) {
					return Stream.empty();		
				} else {
					return Stream.of(Square.G1);
				}
			} else {
				if (Stream.concat(my.squares(), enemy.attacksTo(my)).filter(x -> (x == Square.F8 || x == Square.G8))
								.findAny()
								.isPresent()) {
					return Stream.empty();
				} else {
					return Stream.of(Square.G8);
				}
			}
		}
	}
		
	private Stream<Square> longCastling(PieceGroup my, PieceGroup enemy) {
		if ( !(my.castling[1]) || inCheck(my.king, my, enemy)) {
			return Stream.empty();
		} else {
			if (my.king == Square.E1) {
				if (Stream.concat(my.squares(), enemy.attacksTo(my)).filter(x -> (x == Square.D1 || x == Square.C1))
								.findAny()
								.isPresent()) {
					return Stream.empty();
				} else {
					return Stream.of(Square.C1);
				}
			} else {
				if (Stream.concat(my.squares(), enemy.attacksTo(my)).filter(x -> (x == Square.D8 || x == Square.C8))
								.findAny()
								.isPresent()) {
					return Stream.empty();
				} else {
					return Stream.of(Square.C8);
				}
			}
		}
	}
	
	public boolean[] castlingBools(Square a, PieceGroup my) {
		boolean[] bools = my.castling.clone();
		if (my.sqPtMap.get(a) == PieceT.ROOK) {
			bools[0] = (a == Square.A1 || a == Square.A8) ? false : my.castling[0];
			bools[1] = (a == Square.H1 || a == Square.H8) ? false : my.castling[1];
		}
		if (a == my.king) {
			bools[0] = false;
			bools[1] = false;
		}
		return bools;
	}	
	
	public void castling(PieceGroup my, EnumMap<Square, PieceT> nSqPtMap, Square a, Square b) {	
		if (a == my.king && Square.euclidianDistance(a, b) == 2) {
			if (a == Square.E1) {
				if (b == Square.G1) {
					whiteKingShortCastle(nSqPtMap);
				} else if (b == Square.C1) {
					whiteKingLongCastle(nSqPtMap);
				}
			} else {
				if (b == Square.G8) {
					blackKingShortCastle(nSqPtMap);
				} else if (b == Square.C8) {
					blackKingLongCastle(nSqPtMap);
				}
			}
		}
	}
	
	private void whiteKingShortCastle(EnumMap<Square, PieceT> nSqPtMap) {
		nSqPtMap.remove(Square.H1);
		nSqPtMap.put(Square.F1, PieceT.ROOK);
	}
	private void whiteKingLongCastle(EnumMap<Square, PieceT> nSqPtMap) {
		nSqPtMap.remove(Square.A1);
		nSqPtMap.put(Square.D1, PieceT.ROOK);
	}
	private void blackKingShortCastle(EnumMap<Square, PieceT> nSqPtMap) {
		nSqPtMap.remove(Square.H8);
		nSqPtMap.put(Square.F8, PieceT.ROOK);
	}
	private void blackKingLongCastle(EnumMap<Square, PieceT> nSqPtMap) {
		nSqPtMap.remove(Square.A8);
		nSqPtMap.put(Square.D8, PieceT.ROOK);
	}
		
}


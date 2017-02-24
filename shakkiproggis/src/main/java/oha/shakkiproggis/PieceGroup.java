/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;
import oha.shakkiproggis.pieces.King;
import oha.shakkiproggis.pieces.PieceT;
import oha.shakkiproggis.pieces.PieceObj;
/**
 * This represents a pieceset. can be white or black.
 * This is immutable object. When a move is done, new one is generated.
 * @author pyry
 */
public class PieceGroup implements PiecesToStartingFormation {
	public final EnumMap<Square, PieceT> sqPtMap;
	public final EnumMap<PieceT, PieceObj> pTpMap;
	public final Square king;
	public final boolean[] castling;
	public final Optional<Square> enpassPawn;
	public final PawnPromoChooser pc;
	/**
	 * default constructor.
	 * @param color black or white.
	 * @param pc pawn promo chooser object.
	 */
	public PieceGroup(boolean color, PawnPromoChooser pc) {
		this.sqPtMap = new EnumMap<>(Square.class);
		this.pTpMap = new EnumMap<>(PieceT.class);
		sqPtMapGen(this.sqPtMap, color);
		pTpMapGen(this.pTpMap, color);
		this.king = (color) ? Square.E1 : Square.E8;
		this.castling = new boolean[]{true, true};
		this.enpassPawn = Optional.empty();
		this.pc = pc;
	}
	/**
	 * This constructor is used to generate new piecegroups from old ones.
	 * @param sqPtMap squares to piece types map.
	 * @param pTpMap piece types to piece objects map.
	 * @param kingSq king's square.
	 * @param castling boolean that tell if castling is possible.
	 * @param epp enpassantable pawn's square.
	 * @param pc pawn promotion target chooser object.
	 */
	public PieceGroup(EnumMap<Square, PieceT> sqPtMap, EnumMap<PieceT, PieceObj> pTpMap, Square kingSq, boolean[] castling, Optional<Square> epp, PawnPromoChooser pc) {
		this.sqPtMap = sqPtMap;
		this.pTpMap = pTpMap;
		this.king = kingSq;
		this.castling = castling;	
		this.enpassPawn = epp;
		this.pc = pc;
	}

	private PieceObj sqToPiece(Square sq) {
		return pTpMap.get(sqPtMap.get(sq));
	}
	/**
	 * Player's occupied squares.
	 * @return a stream of squares occupied by pieces of this player. 
	 */
	public Stream<Square> squares() {
		return sqPtMap.keySet().stream();
	}
	/**
	 * Squares that are under our attack.
	 * @param enemy other players pieces
	 * @return Squares that can be attacked by this pieceGroups pieces. Also empty squares.
	 */
	public Stream<Square> attacksTo(PieceGroup enemy) {
		return sqPtMap.keySet()
			.stream()
			.flatMap(sq -> sqToPiece(sq).attacks(sq, this, enemy));
	}
	/**
	 * Is our important king in check.
	 * @param enemy other player's pieces
	 * @return true if this player's king is in check.
	 */
	public boolean kingInCheck(PieceGroup enemy) {
		King k = (King) sqToPiece(this.king);
		return k.inCheck(king, this, enemy);
	}

	private boolean enPassEmerges(Square a, Square b) {
		boolean t = (this.sqPtMap.get(a) == PieceT.BPAWN || this.sqPtMap.get(a) == PieceT.WPAWN);
		return (t) ? (Math.abs(b.ordinal() - a.ordinal()) == 16) : t;
	}
	
	private EnumMap<Square, PieceT> genNewSqPtMap(Square a, Square b) {
		EnumMap<Square, PieceT> nSqPtMap = new EnumMap<>(Square.class);
		squares().filter(x -> x != a).forEach(x -> nSqPtMap.put(x, this.sqPtMap.get(x)));
		nSqPtMap.put(b, this.sqPtMap.get(a));
		promote(b, nSqPtMap);
		return nSqPtMap;
	}
	
	private King kingObject() {
		return (King) sqToPiece(king);
	}
	/**
	 * Moves my piece.
	 * @param a starting square
	 * @param b ending square
	 * @return new pieceGroup in which square A has mutated to B.
	 */
	public PieceGroup moveMyPiece(Square a, Square b) {
		EnumMap<Square, PieceT> nSqPtMap = genNewSqPtMap(a, b);
		kingObject().castling(this, nSqPtMap, a, b);
		Square k = (a == this.king) ? b : this.king;
		Optional<Square> epp = enPassEmerges(a, b) ? Optional.of(b) : Optional.empty();
		return new PieceGroup(nSqPtMap, this.pTpMap, k, kingObject().castlingBools(a, this), epp, this.pc);
	}
	/**
	 * Kills my piece if there is one on the square b.
	 * @param a starting point of the enemy move.
	 * @param b end point of the enemy move. 
	 * @param eater type of piece who moved there.
	 * @return new piece set with the same pieces on same locations. maybe one piece is missing or is not.
	 */
	public PieceGroup eatOneOfMyPieces(Square a, Square b, PieceGroup eater) {
		EnumMap<Square, PieceT> nSqPtMap = new EnumMap<>(Square.class);
		squares().filter(x -> x != b).forEach(x -> nSqPtMap.put(x, this.sqPtMap.get(x)));
		enPassantKillsMyPawn(a, b, nSqPtMap, eater.sqPtMap);
		return new PieceGroup(nSqPtMap, this.pTpMap, this.king, this.castling, Optional.empty(), this.pc);
	}
	
	private void enPassantKillsMyPawn(Square a, Square b, EnumMap<Square, PieceT> myNew, EnumMap<Square, PieceT> enemy) {
		if (this.enpassPawn.isPresent() && (enemy.get(a) == PieceT.WPAWN || enemy.get(a) == PieceT.BPAWN)) {
			Square epp = this.enpassPawn.get();
			Function<Square, Square> fun = (enemy.get(a) == PieceT.WPAWN) ? Square.rankPlus : Square.rankMinus;
			if (b == fun.apply(epp)) {
				myNew.remove(epp);
			}
		}
	}
	
	/**
	 * Possile moves of my pieces.
	 * @param s starting square
	 * @param enemy other player
	 * @return all the squares in which this piece can move
	 */
	public Stream<Square> moves(Square s, PieceGroup enemy) {
		return sqToPiece(s).moves(s, this, enemy);
	}
	
	private void promote(Square b, EnumMap<Square, PieceT> nSqPtMap) {
		if (b.ordinal() < Square.A2.ordinal() || Square.H7.ordinal() < b.ordinal()) {
			PieceT isPawn = nSqPtMap.get(b);
			if (isPawn == PieceT.WPAWN || isPawn == PieceT.BPAWN) {
				nSqPtMap.put(b, this.pc.get());
			}
		}
	}
	
}

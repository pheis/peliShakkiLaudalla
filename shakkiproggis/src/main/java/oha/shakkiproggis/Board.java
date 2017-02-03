package oha.shakkiproggis;

import  oha.shakkiproggis.Piece;
import  oha.shakkiproggis.Square;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.*;

public class Board {
	public EnumMap<Square, Piece> myPieces;
	public EnumMap<Square, Piece> enemyPieces;
	
	private final boolean iAmWhite;
	
	private final boolean myKingMoved;
	private final boolean myARookMoved;
	private final boolean myHRookMoved;
	private final boolean enemyKingMoved;
	private final boolean enemyARookMoved;
	private final boolean enemyHRookMoved;
	
	Optional<Square> enpassantablePawn;
	
	private String name;
	
	public Board() {
		this.myPieces = new EnumMap<>(Square.class);
		this.enemyPieces = new EnumMap<>(Square.class);
		
		this.iAmWhite = true;
		
		this.myKingMoved = false;
		this.myARookMoved = false;
		this.myHRookMoved = false;
		this.enemyKingMoved = false;
		this.enemyARookMoved = false;
		this.enemyHRookMoved = false;
		
		this.enpassantablePawn = Optional.empty();
		
		this.name = "";
	}
	public Board(EnumMap<Square, Piece> mp, EnumMap<Square, Piece> ep, boolean[] ts, Optional<Square> epp, String n) {
		this.myPieces = mp;
		this.enemyPieces = ep;
		this.iAmWhite = ts[0];
		this.myKingMoved = ts[1];
		this.myARookMoved = ts[2];
		this.myHRookMoved = ts[3];
		this.enemyKingMoved = ts[4];
		this.enemyARookMoved = ts[5];
		this.enemyHRookMoved = ts[6];
		this.enpassantablePawn = epp;
		this.name = n;
	}
	
	private void myPiecesToStartingFormation() {
		myPieces.put(Square.A1, Piece.ROOK);
		myPieces.put(Square.B1, Piece.KNIGHT);
		myPieces.put(Square.C1, Piece.BISHOP);
		myPieces.put(Square.D1, Piece.QUEEN);
		myPieces.put(Square.E1, Piece.KING);
		myPieces.put(Square.F1, Piece.BISHOP);
		myPieces.put(Square.G1, Piece.KNIGHT);
		myPieces.put(Square.H1, Piece.ROOK);
	}
	
	private void myPawnsToStartingFormation() {
		Square.allSquares()
				.filter(x-> x.ordinal() >= Square.A2.ordinal())
				.filter(x-> x.ordinal() <= Square.H2.ordinal())
				.forEach((Square x) -> myPieces.put(x, Piece.WPAWN));
	}
	private void enemyPiecesToStartingFormation() {
		enemyPieces.put(Square.A8, Piece.ROOK);
		enemyPieces.put(Square.B8, Piece.KNIGHT);
		enemyPieces.put(Square.C8, Piece.BISHOP);
		enemyPieces.put(Square.D8, Piece.KING);
		enemyPieces.put(Square.E8, Piece.QUEEN);
		enemyPieces.put(Square.F8, Piece.BISHOP);
		enemyPieces.put(Square.G8, Piece.KNIGHT);
		enemyPieces.put(Square.H8, Piece.ROOK);
	}
	private void enemyPawnsToStartingFormation() {
		Square.allSquares()
				.filter(x-> x.ordinal() >= Square.A7.ordinal())
				.filter(x-> x.ordinal() <= Square.H7.ordinal())
				.forEach((Square x) -> enemyPieces.put(x, Piece.BPAWN));
	}
	public void piecesToStartingFormation() {
		myPawnsToStartingFormation();
		myPiecesToStartingFormation();
		enemyPawnsToStartingFormation();
		enemyPiecesToStartingFormation();
	}

	public Stream<Square> mySquares() {
		return this.myPieces.keySet().stream();
	}
	public Stream<Square> enemySquares() {
		return this.enemyPieces.keySet().stream();
	}
	public Stream<Square> allSquaresWithAPiece() {
		return Stream.concat(mySquares(), enemySquares());
	}
	
	public Stream<Square> knightAttacks(Square s) {
		return Square.allSquares()
			.filter((Square x) -> (Square.manhattanDistance(s, x) == 3))
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 2));
	}
	public Stream<Square> blackPawnAttacks(Square s) {
		return Square.allSquares()
			.filter((Square x) -> (Square.manhattanDistance(s, x) == 2))
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 1))
			.filter((Square x) -> (x.ordinal() < s.ordinal()));
	}
	public Stream<Square> whitePawnAttacks(Square s) {
		//en passant puuttuu.
		return Square.allSquares()
			.filter((Square x) -> (Square.manhattanDistance(s, x) == 2))
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 1))
			.filter((Square x) -> (x.ordinal() > s.ordinal()));
	}
	public Stream<Square> kingAttacks(Square s) {
		return Square.allSquares()
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 1));
	}
	
	private Optional<Integer> infimum(BiPredicate<Square, Square> onSame, Square s) {
		return allSquaresWithAPiece()
			.filter((Square x) -> (onSame.test(x, s)))
			.filter((Square x) -> (x.ordinal() < s.ordinal()))
			.map((Square x) -> x.ordinal())
			.reduce((x, y) -> Math.max(x, y));
	}
	
	private Optional<Integer> supremum(BiPredicate<Square, Square> onSame, Square s) {
		return allSquaresWithAPiece()
			.filter((Square x) -> (onSame.test(x, s)))
			.filter((Square x) -> (x.ordinal() > s.ordinal()))
			.map((Square x) -> x.ordinal())
			.reduce((x, y) -> Math.min(x, y));
	}
	
	private Stream<Square> slideOn(BiPredicate<Square, Square> onSame, Square s) {
		return Square.allSquares()
			.filter((Square x) -> (onSame.test(s, x)))
			.filter((Square x) -> (x.ordinal() >= infimum(onSame, s).orElse(Square.A1.ordinal())))
			.filter((Square x) -> (x.ordinal() <= supremum(onSame, s).orElse(Square.H8.ordinal())));
	}
	
	public Stream<Square> rookAttacks(Square s) {
		Stream<Square> rank = slideOn((x, y)->Square.onSameRank(x, y), s);
		Stream<Square> file = slideOn((x, y)->Square.onSameFile(x, y), s);
		return Stream.concat(rank, file);
	}
	
	public Stream<Square> bishopAttacks(Square s) {
		Stream<Square> leftD = slideOn((x, y)->Square.onSameLeftDiagonal(x, y), s);
		Stream<Square> rightD = slideOn((x, y)->Square.onSameRightDiagonal(x, y), s);
		return Stream.concat(leftD, rightD);
	}
	
	public Stream<Square> queenAttacks(Square s) {
		return Stream.concat(rookAttacks(s), bishopAttacks(s));
	}
	
	public Stream<Square> linkPieceToAttack(Square s, Piece p) {
		switch (p) {
			case KING:
				return kingAttacks(s);	
			case QUEEN:
				return queenAttacks(s);
			case ROOK:
				return rookAttacks(s);
			case BISHOP:
				return bishopAttacks(s);
			case KNIGHT:
				return knightAttacks(s);
			case WPAWN:
				return whitePawnAttacks(s);
			case BPAWN:
				return blackPawnAttacks(s);
			default:
				System.out.println("KAKKAA TUULETTIMESSA");
				return EnumSet.noneOf(Square.class).stream();
		}
	}

	public Stream<Square> attacks(EnumMap<Square, Piece> pieces) {
		return pieces.keySet()
			.stream()
			.flatMap((Square x) -> linkPieceToAttack(x, pieces.get(x)));
	}
	
	public Square kingSquare(EnumMap<Square, Piece> pieces) {
		// TÄÄLLÄ ON BUGI!! LÖYSIN GUBIN!!
		return pieces.keySet().stream()
			.filter((Square x) -> (pieces.get(x) == Piece.KING))
			.findFirst()
			// NEEDS FIXING HERE
			.orElse(Square.A1); // Impossible to be null... :-P // FIX THIS
	}
	
	public boolean kingInCheck(EnumMap<Square, Piece> defender, EnumMap<Square, Piece> attacker) {
		return attacks(attacker)
			.filter(x -> x == kingSquare(defender))
			.findAny()
			.isPresent();
	}
	
	public boolean possible() {
		return !(kingInCheck(this.enemyPieces, this.myPieces));
	}
	
	public Stream<Square> kingMoves() {
		return kingAttacks(kingSquare(this.myPieces))
			.filter((Square x) -> !(mySquares().anyMatch(y -> y == x)))
			.filter((Square x) -> !(attacks(this.enemyPieces).anyMatch((Square y) -> (y == x))));
			//.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
	}
	
	public Stream<Square> whitePawnMoves(Square s) {
		//EnumSet<Square> moves = EnumSet.noneOf(Square.class);
		int moveDist = (s.ordinal() <= Square.H2.ordinal() && Square.A2.ordinal() <= s.ordinal()) ? 2 : 1;
		Stream<Square> moves = slideOn((x, y)->Square.onSameFile(x, y), s)
						.filter((Square x) -> (x.ordinal() > s.ordinal()))
						.filter((Square x) -> (Square.euclidianDistance(x, s) <= moveDist))
						.filter((Square x) -> !(mySquares().anyMatch(y -> y == x)))
						.filter(x -> !(enemySquares().anyMatch(y -> (x == y))));
						//.collect(Collectors.toCollection(() -> moves));
		Stream<Square> attackMoves = whitePawnAttacks(s)
						.filter(x -> (enemySquares().anyMatch(y -> y == x)));
						//.collect(Collectors.toCollection(() -> moves));
		return Stream.concat(moves, attackMoves);
	}
	
	public Stream<Square> blackPawnMoves(Square s) {
		//EnumSet<Square> moves = EnumSet.noneOf(Square.class);
		int moveDist = (s.ordinal() <= Square.H7.ordinal() && Square.A7.ordinal() <= s.ordinal()) ? 2 : 1;
		Stream<Square> moves = slideOn((x, y)->Square.onSameFile(x, y), s)
						.filter((Square x) -> (x.ordinal() < s.ordinal()))
						.filter((Square x) -> (Square.euclidianDistance(x, s) <= moveDist))
						.filter((Square x) -> !(mySquares().anyMatch(y -> y == x)))
						.filter(x -> !(enemySquares().anyMatch(y -> (x == y))));
						//.collect(Collectors.toCollection(() -> moves));
		Stream<Square> attackMoves = blackPawnAttacks(s)
						.filter(x -> (enemySquares().anyMatch(y -> y == x)));
						//.collect(Collectors.toCollection(() -> moves));
		return Stream.concat(moves, attackMoves);
	}
	
	public Optional<Square> addEnPassant(Square s) {
		return this.enpassantablePawn.isPresent() ? 
			enemySquares()
				.filter(x -> (Square.isSideBySide(x, s)))
				.filter(x -> (x == enpassantablePawn.get()))
				.findAny() : Optional.empty();
	}
	
	
	public Stream<Square> movesOfThisPiece(Square s) {
		Piece p = this.myPieces.get(s);
		if (p == null) {
			return EnumSet.noneOf(Square.class).stream();
		}
		if (p == Piece.KING) {
			return kingMoves();
		}
		if (p == Piece.WPAWN) {
			return whitePawnMoves(s);
		}
		if (p == Piece.BPAWN) {
			return blackPawnMoves(s);
		} else {
			return linkPieceToAttack(s, p)
				.filter(x -> !(mySquares().anyMatch(y -> (x == y)))); // Remove squares occupied by me
				//.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
		}
	}
	
	private EnumMap<Square, Piece> moveMyPiece(Square a, Square b) {
		EnumMap<Square, Piece> myNewPieces = new EnumMap<>(Square.class);
		Piece movingPiece = this.myPieces.get(a);
		mySquares().filter(x -> x != a)
				   .forEach(x -> myNewPieces.put(x, this.myPieces.get(x)));
		myNewPieces.put(b, movingPiece);
		return myNewPieces;
	} 
	
	private EnumMap<Square, Piece> eatEnemyPiece(Square b) {
		EnumMap<Square, Piece> newEnemyPieces = new EnumMap<>(Square.class);
		enemySquares().filter(x -> !(x == b))
				   .forEach(x -> newEnemyPieces.put(x, this.enemyPieces.get(x)));
		return newEnemyPieces;
	} 
	
	private boolean enpassEmerges(Square a, Square b) {
		boolean t = (this.myPieces.get(a) == Piece.BPAWN || this.myPieces.get(a) == Piece.WPAWN);
		t = (t) ? (b.ordinal() - a.ordinal() == 16) : t;
		return t;
	}

	private boolean[] generateBools(Square a) {
		boolean[] ts = new boolean[7];
		ts[0] = !(this.iAmWhite);
		ts[1] = (this.myKingMoved) ? true : (this.myPieces.get(a) != Piece.KING);
		ts[2] = (this.myARookMoved) ? true : (this.myPieces.get(a) != Piece.ROOK ? a == Square.A1 : false);
		ts[3] = (this.myARookMoved) ? true : (this.myPieces.get(a) != Piece.ROOK ? a == Square.H1 : false);
		ts[4] = this.enemyKingMoved;
		ts[5] = this.enemyARookMoved;
		ts[6] = this.enemyHRookMoved;
		return ts;
	}
	
	public Optional<Board> makeAmove(Square a, Square b) {
	//public Board makeAmove(Square a, Square b) {
		EnumMap<Square, Piece> nep = moveMyPiece(a, b);
		EnumMap<Square, Piece> nmp = eatEnemyPiece(b);
		Optional<Square> enPassantable = (enpassEmerges(a, b)) ? Optional.of(b) : Optional.empty();
		String newName = (a.toString() + " -> " + b.toString());
		Board board = new Board(nmp, nep, generateBools(a), enPassantable, newName);
		//return board;
		return (board.possible()) ? Optional.of(board) : Optional.empty();
	}
	
	
	
	
	public ArrayList<Board> listPossibleMoves() {
		ArrayList<Board> boards = new ArrayList<>();
		mySquares()
			.flatMap(x -> movesOfThisPiece(x).map(y-> makeAmove(x, y)))
			//.forEach(z -> boards.add(z));
			.filter(z -> z.isPresent())
			.map(z -> z.get())
			.forEach(z -> boards.add(z));
		return boards;
	}

	
	
			/*
			.flatmap(x -> movesOfThisPiece(x).map(y -> makeAmove(x, y))
				.filter(z->z.isPresent())
				.map(z->z.get())
				.sequential()
				.forEach(z->boards.add(z)));*/
				
		
		
		//boards = boards.stream()
			//.filter(x -> x.possible())
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	

}

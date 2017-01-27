package oha.shakkiproggis;
import  oha.shakkiproggis.Piece;
import  oha.shakkiproggis.Square;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.*;

public class Board {
	private EnumMap<Square,Piece> myPieces;
	private EnumMap<Square,Piece> enemyPieces;

	private final boolean iAmWhite;

	private final boolean whiteKingMoved;
	private final boolean white_A1_RookMoved;
	private final boolean white_H1_RookMoved;
	private final boolean blackKingMoved;
	private final boolean black_A8_RookMoved;
	private final boolean black_H8_RookMoved;
	
	//private final BiPredicate<Square,Square> onSameRank;
	
	public Board() {
		this.myPieces = new EnumMap<Square,Piece>(Square.class);
		this.enemyPieces = new EnumMap<Square,Piece>(Square.class);

		this.iAmWhite = true;
		
		this.whiteKingMoved = false;
		this.white_A1_RookMoved = false;
		this.white_H1_RookMoved = false;
		this.blackKingMoved = false;
		this.black_A8_RookMoved = false;
		this.black_H8_RookMoved = false;
	}
	
	private void myPiecesToStartingFormation() {
		myPieces.put(Square.A1,Piece.ROOK);
		myPieces.put(Square.B1,Piece.KNIGHT);
		myPieces.put(Square.C1,Piece.BISHOP);
		myPieces.put(Square.D1,Piece.QUEEN);
		myPieces.put(Square.E1,Piece.KING);
		myPieces.put(Square.F1,Piece.BISHOP);
		myPieces.put(Square.G1,Piece.KNIGHT);
		myPieces.put(Square.H1,Piece.ROOK);
	}
	
	private void myPawnsToStartingFormation() {
		allSquaresOnBoard()
			.filter(x-> x.ordinal() >= Square.A2.ordinal())
			.filter(x-> x.ordinal() <= Square.H2.ordinal())
			.forEach((Square x) -> myPieces.put(x,Piece.WPAWN));
	}
	private void enemyPiecesToStartingFormation() {
		enemyPieces.put(Square.A8,Piece.ROOK);
		enemyPieces.put(Square.B8,Piece.KNIGHT);
		enemyPieces.put(Square.C8,Piece.BISHOP);
		enemyPieces.put(Square.D8,Piece.KING);
		enemyPieces.put(Square.E8,Piece.QUEEN);
		enemyPieces.put(Square.F8,Piece.BISHOP);
		enemyPieces.put(Square.G8,Piece.KNIGHT);
		enemyPieces.put(Square.H8,Piece.ROOK);
	}
	private void enemyPawnsToStartingFormation() {
		allSquaresOnBoard()
			.filter(x-> x.ordinal() >= Square.A7.ordinal())
			.filter(x-> x.ordinal() <= Square.H7.ordinal())
			.forEach((Square x) -> enemyPieces.put(x,Piece.BPAWN));
	}
	public void piecesToStartingFormation(){
		myPawnsToStartingFormation();
		myPiecesToStartingFormation();
		enemyPawnsToStartingFormation();
		enemyPiecesToStartingFormation();
	}
	public Stream<Square> allSquaresOnBoard() {
		Stream<Square> all = Arrays.stream(Square.values());
		return all;
	}
	
	public Stream<Square> mySquares() {
		Stream<Square> squares;
		squares = this.myPieces
			.keySet()
			.stream();
			//.map(x->x.ordinal());
		return squares;
	}
	public Stream<Square> enemySquares() {
		Stream<Square> squares;
		squares = this.enemyPieces
			.keySet()
			.stream();
			//.map(x->x.ordinal());
		return squares;
	}
	public Stream<Square> allSquaresWithAPiece() {
		Stream<Square> all = Stream.concat(mySquares(),enemySquares());
		return all;
	}
	
	private boolean onSameRank(Square first,Square second) {
	boolean t;
	t = (first.ordinal()/8 == second.ordinal()/8);
	return t;
	}
	
	private boolean onSameFile(Square first,Square second) {
		boolean t;
		t = (first.ordinal()%8 == second.ordinal()%8);
		return t;
	}
	
	
	private boolean diagonalTest(Square a, Square b) {
		boolean t;
		t = (rankDistance(a, b) == fileDistance(a, b));
		return t;
	}
	
	private boolean isRight(Square a, Square b) {
		boolean t;
		t = ((a.ordinal()%8) - (b.ordinal()%8) < 0);
		return t;
	}
	
	private boolean isLeft(Square a, Square b) {
		boolean t;
		t = ((a.ordinal()%8) - (b.ordinal()%8) > 0);
		return t;
	}
	
	public boolean onSameRightDiagonal(Square a, Square b) {
		boolean t = diagonalTest(a,b);
		t = (t)?(a.ordinal()%9==b.ordinal()%9):t;
		t = (t)?(isLeft(a,b) ^ (a.ordinal() <= b.ordinal())):t;
		return t;
	}	
	
	public boolean onSameLeftDiagonal(Square a, Square b) {
		boolean t = diagonalTest(a, b);
		t = (t)?(a.ordinal()%7 == b.ordinal()%7):t;
		
		t = (t)?(isRight(a,b) ^ (a.ordinal() <= b.ordinal() )):t;
		
		return t;
	}
	
	private int fileDistance(Square a, Square b) {
		int x1 = a.ordinal()%8;
		int x2 = b.ordinal()%8;
		int rankDist = Math.abs(x2-x1);
		return rankDist;
	}
	private int rankDistance(Square a, Square b) {
		int y1 = a.ordinal()/8;
		int y2 = b.ordinal()/8;
		int rankDist = Math.abs(y2-y1);
		return rankDist;
	}
	private int euclidianDistance(Square a, Square b) {
		return Math.max(rankDistance(a,b),fileDistance(a,b));
	}
	private int manhattanDistance(Square a, Square b) {
		return rankDistance(a,b)+fileDistance(a,b);
	}
	public EnumSet<Square> knightAttacks(Square s) {
		EnumSet<Square> attacks = allSquaresOnBoard()
			.filter((Square x) -> (manhattanDistance(s,x) == 3))
			.filter((Square x) -> (euclidianDistance(s,x) == 2))
			.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
			return attacks;
	}
	public EnumSet<Square> blackPawnAttacts(Square s) {
		EnumSet<Square> attacks = allSquaresOnBoard()
			.filter((Square x) -> (manhattanDistance(s,x) == 2))
			.filter((Square x) -> (euclidianDistance(s,x) == 1))
			.filter((Square x) -> (x.ordinal() < s.ordinal()))
			.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
			return attacks;
	}
	public EnumSet<Square> whitePawnAttacts(Square s) {
		//en passant puuttuu.
		EnumSet<Square> attacks = allSquaresOnBoard()
			.filter((Square x) -> (manhattanDistance(s,x) == 2))
			.filter((Square x) -> (euclidianDistance(s,x) == 1))
			.filter((Square x) -> (x.ordinal() > s.ordinal()))
			.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
			return attacks;
	}
	public EnumSet<Square> kingAttacks(Square s) {
		EnumSet<Square> attacks = allSquaresOnBoard()
			.filter((Square x) -> (euclidianDistance(s,x) == 1))
			.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
			return attacks;
	}
	
	private Optional<Integer> infimum(BiPredicate<Square,Square> onSame, Square s) {
		Optional<Integer> inf;
		inf = allSquaresWithAPiece()
			.filter((Square x) -> (onSame.test(x,s)))
			.filter((Square x) -> (x.ordinal() < s.ordinal()))
			.map((Square x) -> x.ordinal())
			.reduce((x,y) -> Math.max(x, y));
		return inf;
	}
	
	private Optional<Integer> supremum(BiPredicate<Square,Square> onSame, Square s) {
		Optional<Integer> sup;
		sup = allSquaresWithAPiece()
			.filter((Square x) -> (onSame.test(x,s)))
			.filter((Square x) -> (x.ordinal() > s.ordinal()))
			.map((Square x) -> x.ordinal())
			.reduce((x,y) -> Math.min(x, y));
		return sup;
	}
	
	private Stream<Square> slideOn(BiPredicate<Square,Square> onSame, Square s) {
		Stream<Square> slide = allSquaresOnBoard()
		.filter((Square x) -> (onSame.test(s, x)))
		.filter((Square x) -> (x.ordinal() >= infimum(onSame,s).orElse(Square.A1.ordinal())))
		.filter((Square x) -> (x.ordinal() <= supremum(onSame,s).orElse(Square.H8.ordinal())));
		return slide;
	}
	
	public EnumSet<Square> rookAttacks(Square s) {
		Stream<Square> rank = slideOn((x,y)->onSameRank(x,y), s);
		Stream<Square> file = slideOn((x,y)->onSameFile(x,y), s);
		Stream<Square> both = Stream.concat(rank,file);
		EnumSet<Square> attacks;
		attacks = both.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
		return attacks;
	}
	
	public EnumSet<Square> bishopAttacks(Square s) {
		Stream<Square> leftD = slideOn((x,y)->onSameLeftDiagonal(x,y), s);
		Stream<Square> rightD = slideOn((x,y)->onSameRightDiagonal(x,y), s);
		Stream<Square> both = Stream.concat(leftD,rightD);
		EnumSet<Square> attacks;
		attacks = both.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
		return attacks;
	}
	
	public EnumSet<Square> queenAttacks(Square s) {
		EnumSet<Square> rook = rookAttacks(s);
		EnumSet<Square> bishop = bishopAttacks(s);
		rook.addAll(bishop);
		return rook;
	}
	
	
	public EnumSet<Square> allEnemyAttacks() {
		EnumSet<Square> attacks;
		attacks = EnumSet.noneOf(Square.class);
		return attacks;
	}

}

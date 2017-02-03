package oha.shakkiproggis;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.*;
public enum Square {
	A1,
	B1,
	C1,
	D1,
	E1,
	F1,
	G1,
	H1,
	
	A2,
	B2,
	C2,
	D2,
	E2,
	F2,
	G2,
	H2,
  
	A3,
	B3,
	C3,
	D3,
	E3,
	F3,
	G3,
	H3,
  
	A4,
	B4,
	C4,
	D4,
	E4,
	F4,
	G4,
	H4,
  
	A5,
	B5,
	C5,
	D5,
	E5,
	F5,
	G5,
	H5,
  
	A6,      
	B6,
	C6,
	D6,
	E6,
	F6,
	G6,
	H6,
  
	A7,
	B7,
	C7,
	D7,
	E7,
	F7,
	G7,
	H7,
  
	A8,
	B8,
	C8,
	D8,
	E8,
	F8,
	G8,
	H8;
	
	public static Stream<Square> allSquares() {
		Stream<Square> all = Arrays.stream(Square.values());
		return all;
	}

	public static Stream<Square> allSquaresOnBoard() {
		Stream<Square> all = Arrays.stream(Square.values());
		return all;
	}

	public static boolean onSameRank(Square first, Square second) {
		boolean t = (first.ordinal() / 8 == second.ordinal() / 8);
		return t;
	}
  
	public static boolean onSameFile(Square first, Square second) {
		boolean t  = (first.ordinal() % 8 == second.ordinal() % 8);
		return t;
	}
	
	
	public static boolean diagonalTest(Square a, Square b) {
		boolean t = (rankDistance(a, b) == fileDistance(a, b));
		return t;
	}
	
	public static boolean isRight(Square a, Square b) {
		boolean t = ((a.ordinal() % 8) - (b.ordinal() % 8) < 0);
		return t;
	}
	
	public static boolean isLeft(Square a, Square b) {
		boolean t = ((a.ordinal() % 8) - (b.ordinal() % 8) > 0);
		return t;
	}
	
	public static boolean onSameRightDiagonal(Square a, Square b) {
		boolean t = diagonalTest(a, b);
		t = (t) ? (a.ordinal() % 9 == b.ordinal() % 9) : t;
		t = (t) ? (isLeft(a, b) ^ (a.ordinal() <= b.ordinal())) : t;
		return t;
	}	
	
	public static boolean onSameLeftDiagonal(Square a, Square b) {
		boolean t = diagonalTest(a, b);
		t = (t) ? (a.ordinal() % 7 == b.ordinal() % 7) : t;
		t = (t) ? (isRight(a, b) ^ (a.ordinal() <= b.ordinal())) : t;
		return t;
	}
	
	public static int fileDistance(Square a, Square b) {
		int x1 = a.ordinal() % 8;
		int x2 = b.ordinal() % 8;
		int rankDist = Math.abs(x2 - x1);
		return rankDist;
	}
	public static int rankDistance(Square a, Square b) {
		int y1 = a.ordinal() / 8;
		int y2 = b.ordinal() / 8;
		int rankDist = Math.abs(y2 - y1);
		return rankDist;
	}
	public static int euclidianDistance(Square a, Square b) {
		return Math.max(rankDistance(a, b), fileDistance(a, b));
	}
	public static int manhattanDistance(Square a, Square b) {
		return rankDistance(a, b) + fileDistance(a, b);
	}
	
	public static boolean isSideBySide(Square a, Square b) {
		boolean t = ((Math.abs(a.ordinal() - b.ordinal()) == 1));
		t = (t) ? onSameRank(a, b) : t;
		return t;
	}

}

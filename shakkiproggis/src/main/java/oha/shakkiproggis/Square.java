package oha.shakkiproggis;
import java.util.function.BiPredicate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
public enum Square {
	A1, B1, C1, D1, E1, F1, G1, H1,
	A2, B2, C2, D2, E2, F2, G2, H2,
	A3, B3, C3, D3, E3, F3, G3, H3, 
	A4, B4, C4, D4, E4, F4, G4, H4,
	A5, B5, C5, D5, E5, F5, G5, H5,
	A6, B6, C6, D6, E6, F6, G6, H6,
	A7, B7, C7, D7, E7, F7, G7, H7,
	A8, B8, C8, D8, E8, F8, G8, H8;
	/**
	 * 
	 * @return stream of all squares 
	 */
	public static Stream<Square> allSquares() {
		Stream<Square> all = Arrays.stream(Square.values());
		return all;
	}
/**
 * 
 * @param first
 * @param second
 * @return true if they are on the same rank.
 */
	public static boolean onSameRank(Square first, Square second) {
		boolean t = (first.ordinal() / 8 == second.ordinal() / 8);
		return t;
	}
  /**
   * 
   * @param first
   * @param second
   * @return true if they are on the same file.
   */
	public static boolean onSameFile(Square first, Square second) {
		boolean t  = (first.ordinal() % 8 == second.ordinal() % 8);
		return t;
	}
	/**
	 * 
	 * @param a
	 * @param b
	 * @return true if you have to move as many ranks as files to get from A to B. 
	 */
	public static boolean diagonalTest(Square a, Square b) {
		boolean t = (rankDistance(a, b) == fileDistance(a, b));
		return t;
	}
	/**
	 * 
	 * @param a some square
	 * @param b some other square
	 * @return True if A is physically right from B on the board;
	 */
	public static boolean isRight(Square a, Square b) {
		boolean t = ((a.ordinal() % 8) - (b.ordinal() % 8) < 0);
		return t;
	}
	/**
	 * 
	 * @param a some square
	 * @param b some other square
	 * @return True if A is physically left from B on the board;
	 */
	public static boolean isLeft(Square a, Square b) {
		boolean t = ((a.ordinal() % 8) - (b.ordinal() % 8) > 0);
		return t;
	}
	/**
	 * 
	 * @param a some square
	 * @param b some other square
	 * @return true if they are on the same left to right diagonal. otherwise false.
	 */
	public static boolean onSameRightDiagonal(Square a, Square b) {
		boolean t = diagonalTest(a, b);
		t = (t) ? (a.ordinal() % 9 == b.ordinal() % 9) : t;
		t = (t) ? (isLeft(a, b) ^ (a.ordinal() <= b.ordinal())) : t;
		return t;
	}	
	/**
	 * 
	 * @param a square
	 * @param b other square
	 * @return true if they are on the same right to left diagonal. Otherwise false.
	 */
	public static boolean onSameLeftDiagonal(Square a, Square b) {
		boolean t = diagonalTest(a, b);
		t = (t) ? (a.ordinal() % 7 == b.ordinal() % 7) : t;
		t = (t) ? (isRight(a, b) ^ (a.ordinal() <= b.ordinal())) : t;
		return t;
	}
	/**
	 * 
	 * @param a some square
	 * @param b some other
	 * @return tells how many ranks you have to move to get from one to another
	 */
	public static int fileDistance(Square a, Square b) {
		int x1 = a.ordinal() % 8;
		int x2 = b.ordinal() % 8;
		int rankDist = Math.abs(x2 - x1);
		return rankDist;
	}
	/**
	 * 
	 * @param a some square
	 * @param b some other square
	 * @return distance beetween the ranks of the two
	 */
	public static int rankDistance(Square a, Square b) {
		int y1 = a.ordinal() / 8;
		int y2 = b.ordinal() / 8;
		int rankDist = Math.abs(y2 - y1);
		return rankDist;
	}
	/**
	 * 
	 * @param a some square
	 * @param b some other square
	 * @return shortest possible path on squares beetween the two.
	 */
	public static int euclidianDistance(Square a, Square b) {
		return Math.max(rankDistance(a, b), fileDistance(a, b));
	}
	/**
	 * 
	 * @param a some square
	 * @param b some other square
	 * @return manhattan distance beetween the squares
	 */
	public static int manhattanDistance(Square a, Square b) {
		return rankDistance(a, b) + fileDistance(a, b);
	}
	/**
	 * 
	 * @param a Some square
	 * @param b Some other square
	 * @return True if the two are side by side on the same rank.
	 */
	public static boolean isSideBySide(Square a, Square b) {
		boolean t = ((Math.abs(a.ordinal() - b.ordinal()) == 1));
		t = (t) ? onSameRank(a, b) : t;
		return t;
	}
	/**
	 * tells if first arguments is greater than second.
	 */
	public static BiPredicate<Square, Square> gt = (a, b) -> a.ordinal() > b.ordinal();
	/**
	 * Tells if first argument is lesser than second.
	 */
	public static BiPredicate<Square, Square> lt = (a, b) -> a.ordinal() < b.ordinal();
	/**
	 * 
	 * @return An array of squares on the first rank. 
	 */
	public static Square[] firstRank() {
		Square[] rank1 = new Square[]{A1,B1,C1,D1,E1,F1,G1,H1};
		return rank1;
	}
	/**
	 * 
	 * @return An Array of squares of the second rank. 
	 */
	public static Square[] secondRank() {
		//EnumSet<Square> rank2 = EnumSet.of(A2,B2,C2,D2,E2,F2,G2,H2);
		Square[] secRank = new Square[]{A2,B2,C2,D2,E2,F2,G2,H2};
		
		return secRank;
	}
	/**
	 * 
	 * @return  an Array of squares of the seventh rank
	 */
	public static Square[] seventhRank() {
		Square[] sevRank = new Square[]{A7,B7,C7,D7,E7,F7,G7,H7};
		return sevRank;
	}
	/**
	 * 
	 * @return An array of squares in eight rank 
	 */
	public static Square[] eightRank() {
		Square[] rank8 = new Square[]{A8,B8,C8,D8,E8,F8,G8,H8};
		return rank8;
	}
	/**
	 * returns square above
	 */
	public static Function<Square, Square> rankPlus = s -> 
				(s.ordinal() < A8.ordinal()) ? Square.values()[(s.ordinal()+8)] : s;
	
	/**
	 * returns square below 
	 */
	public static Function<Square, Square> rankMinus = s ->
				(s.ordinal() > H1.ordinal()) ? Square.values()[(s.ordinal()-8)] : s;
}



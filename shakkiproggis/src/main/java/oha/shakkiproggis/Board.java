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
	
	
	/**
	 * Tells if curret player is white.
	 * if true -> current player is white.
	 * else -> current player is black.
	 */
	public final boolean iAmWhite;
	
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
	/**
	 * Creates new game state.
	 * @param mp my Pieces and their locations on the board
	 * @param ep enemy Pieces and their locations on the board
	 * @param ts an array of booleans, tells if current player is black or white and about castling
	 * @param epp optional square that exists if there is a pawn that can be en passanted
	 * @param n name of the board.	Tells from where and to where the last player has moved a piece.
	 */
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
		
		/*System.out.println("###########################");
		System.out.println(ts[0]);
		System.out.println(ts[1]);
		System.out.println(ts[2]);
		System.out.println(ts[3]);
		System.out.println(ts[4]);
		System.out.println(ts[5]);
		System.out.println(ts[6]);
		System.out.println("####################################");*/
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
		enemyPieces.put(Square.E8, Piece.KING);
		enemyPieces.put(Square.D8, Piece.QUEEN);
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

	/**
	 * Tells players piece locations
	 * @return Square stream that contains current players all piece locations as Squares
	 */
	
	public Stream<Square> mySquares() {
		return this.myPieces.keySet().stream();
	}
	/**
	 * Tells enemys piece locations
	 * @return A Stream of locations
	 */
	public Stream<Square> enemySquares() {
		return this.enemyPieces.keySet().stream();
	}
	public Stream<Square> allSquaresWithAPiece() {
		return Stream.concat(mySquares(), enemySquares());
	}
	
	private Stream<Square> knightAttacks(Square s) {
		return Square.allSquares()
			.filter((Square x) -> (Square.manhattanDistance(s, x) == 3))
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 2));
	}
	private Stream<Square> blackPawnAttacks(Square s) {
		return Square.allSquares()
			.filter((Square x) -> (Square.manhattanDistance(s, x) == 2))
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 1))
			.filter((Square x) -> (x.ordinal() < s.ordinal()));
	}
	private Stream<Square> whitePawnAttacks(Square s) {
		//en passant puuttuu.
		return Square.allSquares()
			.filter((Square x) -> (Square.manhattanDistance(s, x) == 2))
			.filter((Square x) -> (Square.euclidianDistance(s, x) == 1))
			.filter((Square x) -> (x.ordinal() > s.ordinal()));
	}
	private Stream<Square> kingAttacks(Square s) {
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
	
	private Stream<Square> rookAttacks(Square s) {
		Stream<Square> rank = slideOn((x, y)->Square.onSameRank(x, y), s);
		Stream<Square> file = slideOn((x, y)->Square.onSameFile(x, y), s);
		return Stream.concat(rank, file);
	}
	
	private Stream<Square> bishopAttacks(Square s) {
		Stream<Square> leftD = slideOn((x, y)->Square.onSameLeftDiagonal(x, y), s);
		Stream<Square> rightD = slideOn((x, y)->Square.onSameRightDiagonal(x, y), s);
		return Stream.concat(leftD, rightD);
	}
	
	private Stream<Square> queenAttacks(Square s) {
		return Stream.concat(rookAttacks(s), bishopAttacks(s));
	}
	
	private Stream<Square> linkPieceToAttack(Square s, Piece p) {
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

	private Stream<Square> attacks(EnumMap<Square, Piece> pieces) {
		return pieces.keySet()
			.stream()
			.flatMap((Square x) -> linkPieceToAttack(x, pieces.get(x)));
	}
	
	public Square kingSquare(EnumMap<Square, Piece> pieces) {
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

	private boolean possible() {
		return !(kingInCheck(this.enemyPieces, this.myPieces));
	}
	
	private Stream<Square> kingMoves() {
		return kingAttacks(kingSquare(this.myPieces))
			.filter((Square x) -> !(mySquares().anyMatch(y -> y == x)))
			.filter((Square x) -> !(attacks(this.enemyPieces).anyMatch((Square y) -> (y == x))));
			//.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class)));
	}
	
	private Stream<Square> whitePawnMoves(Square s) {
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
	
	private Stream<Square> blackPawnMoves(Square s) {
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
		
		Stream<Square> both = Stream.concat(moves, attackMoves);
		return both;
	}
	


	
	private Stream<Square> movesOfThisPiece(Square s) {
		Piece p = this.myPieces.get(s);
		if (p == null) {
			return EnumSet.noneOf(Square.class).stream();
		}
		if (p == Piece.KING) {
			return Stream.concat(kingMoves(), Stream.concat(shortCastling(), longCastling()));
		}
		if (p == Piece.WPAWN) {
			return Stream.concat(whitePawnMoves(s), whitePawnEnpassant(s));
		}
		if (p == Piece.BPAWN) {
			return Stream.concat(blackPawnMoves(s), blackPawnEnpassant(s));
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
		t = (t) ? (Math.abs(b.ordinal() - a.ordinal()) == 16) : t;
		return t;
	}

	private boolean[] generateBools(Square a) {
		boolean[] ts = new boolean[7];
		ts[0] = !(this.iAmWhite);	
		ts[4] = this.myKingMoved ? true : (this.myPieces.get(a) == Piece.KING);
		ts[5] = (this.myARookMoved) ? true : (this.myPieces.get(a) != Piece.ROOK ? a == Square.A1 : false);
		ts[6] = (this.myARookMoved) ? true : (this.myPieces.get(a) != Piece.ROOK ? a == Square.H1 : false);
		ts[1] = this.enemyKingMoved;
		ts[2] = this.enemyARookMoved;
		ts[3] = this.enemyHRookMoved;
		return ts;
	}
	
	private void castling(EnumMap<Square, Piece> nep, Square a, Square b) {	
		if (this.myPieces.get(a) == Piece.KING && Square.euclidianDistance(a, b) == 2) {
			if (iAmWhite) {
				if (b == Square.G1) {
					nep.remove(Square.H1);
					nep.put(Square.F1, Piece.ROOK);
				} else if (b == Square.C1) {
					nep.remove(Square.A1);
					nep.put(Square.D1, Piece.ROOK);
				}
			} else {
				if (b == Square.G8) {
					nep.remove(Square.H8);
					nep.put(Square.F8, Piece.ROOK);
				} else if (b == Square.C8) {
					nep.remove(Square.A8);
					nep.put(Square.D8, Piece.ROOK);
				}
			}
		}
	}
	
	
	
	/**
	 * Generates a new board based on the move that player/Ai does.
	 * @param a Piece previous location
	 * @param b New location of the piece
	 * @return 
	 */
	public Optional<Board> makeAmove(Square a, Square b) {
		EnumMap<Square, Piece> nep = moveMyPiece(a, b);
		EnumMap<Square, Piece> nmp = eatEnemyPiece(b);
		Optional<Square> enPassantable = (enpassEmerges(a, b)) ? Optional.of(b) : Optional.empty();
		
		String newName = (a.toString() + b.toString());
		castling(nep, a, b);
		
		if (whitePawnEnpassant(a).anyMatch(x -> x == b) || blackPawnEnpassant(a).anyMatch(x -> x == b)) {
			nmp.remove(this.enpassantablePawn.get());
		}
		
		Board board = new Board(nmp, nep, generateBools(a), enPassantable, newName);
		return (board.possible()) ? Optional.of(board) : Optional.empty();
	}
	
	
	/**
	 * Lists possible moves from current game situation.
	 * @return an ArrayList that contains all the gamestates that are possible from the current game state
	 */
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
	
		
	public Optional<Square> enPassant(Square s) {
		return this.enpassantablePawn.isPresent() ? 
			enemySquares()
				.filter(x -> (Square.isSideBySide(x, s)))
				.filter(x -> (x == enpassantablePawn.get()))
				.findAny() : Optional.empty();
	}
	
	public Stream<Square> whitePawnEnpassant(Square s) {
		if (enPassant(s).isPresent()) {
			return Square.allSquares()
				.filter(x -> (x.ordinal() == enPassant(s).get().ordinal() + 8));
		} else {
			return Stream.empty();
		}
	}
	
	public Stream<Square> blackPawnEnpassant(Square s) {
		if (enPassant(s).isPresent()) {
			return Square.allSquares()
				.filter(x -> (x.ordinal() == enPassant(s).get().ordinal() - 8));
		} else {
			return Stream.empty();
		}
	}
	
	public Stream<Square> shortCastling() {
		if (this.myKingMoved || this.myHRookMoved || kingInCheck(this.myPieces, this.enemyPieces)) {
			//System.out.println("FOFOFFOFO");
			return Stream.empty();
		} else {
			if (this.iAmWhite) {
				
				if (Stream.concat(mySquares(), attacks(this.enemyPieces)).filter(x -> (x == Square.F1 || x == Square.G1))
								.findAny()
								.isPresent()) {
								//System.out.println("asdfasdfasdf");
					return Stream.empty();		
								
				} else {
					//System.out.println("qwerty");
					return Stream.of(Square.G1);
				}
			
			} else {
				if (Stream.concat(mySquares(), attacks(this.enemyPieces)).filter(x -> (x == Square.F8 || x == Square.G8))
								.findAny()
								.isPresent()) {
					return Stream.empty();
				} else {
					return Stream.of(Square.G8);
			
				}
			}
		}
	}
		
	public Stream<Square> longCastling() {
		if (this.myKingMoved || this.myARookMoved || kingInCheck(this.myPieces, this.enemyPieces)) {
			return Stream.empty();
		} else {
			if (this.iAmWhite) {
				if (Stream.concat(mySquares(), attacks(this.enemyPieces)).filter(x -> (x == Square.D1 || x == Square.C1))
								.findAny()
								.isPresent()) {
					return Stream.empty();
				} else {
					return Stream.of(Square.C1);
				}
			
			} else {
				if (Stream.concat(mySquares(), attacks(this.enemyPieces)).filter(x -> (x == Square.D8 || x == Square.C8))
								.findAny()
								.isPresent()) {
					return Stream.empty();
				} else {
					return Stream.of(Square.C8);
			
				}
			}
		}
	}
	/**
	 * Tells if current players King is in check
	 * @return true if king is in check.
	 */
	public boolean myKingInCheck() {
		return kingInCheck(myPieces, enemyPieces);
	}
		
	
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	

}

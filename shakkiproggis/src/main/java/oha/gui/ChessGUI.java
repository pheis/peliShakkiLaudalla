/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.gui;

import static java.nio.file.Files.size;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import oha.shakkiproggis.Square;
import oha.shakkiproggis.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import oha.ai.AI;

/**
 *
 * @author pyry
 */
public class ChessGUI extends Application {
	int random = ThreadLocalRandom.current().nextInt(0, 2);	
	Iload il = new Iload();
	MoveValidator game = new MoveValidator();
	AI ai = new AI(game);
	int startX, startY, endX, endY;
	boolean doingMove = false;
	public static final int SQUARESIZE = 100;
	public static final int HEIGHT = 8;
	public static final int WIDTH = 8;
	private Group squares = new Group();
	private Group pieces = new Group();
	private ArrayList<ChessPiece> piecesList = new ArrayList<>();
	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(WIDTH * SQUARESIZE, HEIGHT * SQUARESIZE);	
		root.getChildren().addAll(squares, pieces);
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				ChessSquare sq = new ChessSquare((i + j) % 2 == 0, i, j);
				game.getMyPieces()
								.keySet()
								.forEach(p ->
								{
									int gX = coordToGuiCoordX(p);
									int gY = coordToGuiCoordY(p);
									ChessPiece piece;
									piece = new ChessPiece(gX, gY, game.getMyPieces().get(p), game.iAmWhite(), il);
									piecesList.add(piece);
									pieces.getChildren().add(piece);
								});

				game.getEnemyPieces()
								.keySet()
								.forEach(p ->
								{		
									int gX = coordToGuiCoordX(p);
									int gY = coordToGuiCoordY(p);
									ChessPiece piece;
									piece = new ChessPiece(gX, gY, game.getEnemyPieces().get(p), !game.iAmWhite(), il);
									piecesList.add(piece);
									pieces.getChildren().add(piece);
								});
				
				squares.getChildren().add(sq);
			}
		}
		return root;
	}
	private void updCont() {
		piecesList.forEach(p -> this.pieces.getChildren().remove(p));
		piecesList.removeAll(piecesList);
		game.getMyPieces().keySet()
						.forEach(p ->
						{
							int gX = coordToGuiCoordX(p);
							int gY = coordToGuiCoordY(p);
							ChessPiece piece;
							piece = new ChessPiece(gX, gY, game.getMyPieces().get(p), game.iAmWhite(), il);
							piecesList.add(piece);
							pieces.getChildren().add(piece);
						});
				
				
		game.getEnemyPieces().keySet()
						.forEach(p ->
						{
							int gX = coordToGuiCoordX(p);
							int gY = coordToGuiCoordY(p);
							ChessPiece piece;
							piece = new ChessPiece(gX, gY, game.getEnemyPieces().get(p), !game.iAmWhite(), il);
							piecesList.add(piece);
							pieces.getChildren().add(piece);
						});
	}
	private int transformGuiCoordToBcoord(double x, double y) {
		int newX = (int) Math.floor(x / SQUARESIZE);
		int newY = 7 -  (int) Math.floor(y / SQUARESIZE);
		return newX + (8 * newY);
	}
	private int coordToGuiCoordX(int bCoord) {
		int x;
		x = bCoord % 8;
		//x *= 100;
		return x;
	}	
	private int coordToGuiCoordY(int bCoord) {
		// FIX THIS
		int y;
		y = 7 - (bCoord / 8);
		//y *= 100;
		return y;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {	
		Scene scene = new Scene(createContent());	
		scene.setOnMousePressed((MouseEvent event) ->
		{
			if (doingMove == false) {
				startX = (int) Math.floor(event.getSceneX() / SQUARESIZE);
				startY = 7 - (int) Math.floor(event.getSceneY() / SQUARESIZE);
				doingMove = true;
			}			
		});		
		scene.setOnMouseReleased((MouseEvent event) ->
		{
			if (doingMove == true) {			
				endX = (int) Math.floor(event.getSceneX() / SQUARESIZE);
				endY = 7 - (int) Math.floor(event.getSceneY() / SQUARESIZE);			
				int startSq = startX + (8 * startY);
				int endSq = endX + (8 * endY);								
				if (0 <= startSq && startSq <= 63 && 0 <= endSq && endSq <= 63) {			
					StringBuilder sb = new StringBuilder();
					sb.append("");
					sb.append(Square.values()[startSq]);
					sb.append(Square.values()[endSq]);
					String wantedBoard = sb.toString();				
					boolean t;			
					t = game.movePiece(wantedBoard);				
					if (t) {
						updCont();				
						ai.move();	
						updCont();
					}	
					System.out.println(t);	
					System.out.println(wantedBoard);
				}	
			}
			doingMove = false;
		});	
		primaryStage.setTitle("Very High Quality Chess Program");	
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}


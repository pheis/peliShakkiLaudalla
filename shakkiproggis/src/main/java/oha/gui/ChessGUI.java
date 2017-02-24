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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oha.ai.AI;

/**
 * This is the gui for the program.
 * @author pyry
 */
public class ChessGUI extends Application {
	int random = ThreadLocalRandom.current().nextInt(0, 2);	
	Imageload il = new Imageload();
	
	
	// promoChooserin kaa pitää ziigata kun implementoin värin valinnan.
	
	PawnPromoChooser pc1 = new PawnPromoChooser();
	PawnPromoChooser pc2 = new PawnPromoChooser();
	
	MoveValidator mv = new MoveValidator(pc1, pc2);
	
	AI ai2 = new AI(mv, pc1);
	
	AI ai = new AI(mv, pc2);
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
		
		
		root.setOnMousePressed((MouseEvent event) ->
		{
			if (doingMove == false) {
				startX = (int) Math.floor(event.getSceneX() / SQUARESIZE);
				startY = 7 - (int) Math.floor(event.getSceneY() / SQUARESIZE);
				doingMove = true;
			}			
		});		
		root.setOnMouseReleased((MouseEvent event) ->
		{
			if (doingMove == true) {			
				endX = (int) Math.floor(event.getSceneX() / SQUARESIZE);
				endY = 7 - (int) Math.floor(event.getSceneY() / SQUARESIZE);			
				int startSq = startX + (8 * startY);
				int endSq = endX + (8 * endY);								
				if (0 <= startSq && startSq <= 63 && 0 <= endSq && endSq <= 63) {			
					
					Square a = Square.values()[startSq];
					Square b = Square.values()[endSq];
					
					Move move = new Move(a, b);
							
					boolean t;			
					t = mv.move(move);				
					if (t) {
						updCont();				
						ai.move();	
						updCont();
					}	
					System.out.println(t);	
					System.out.println(move.toString());
				}	
			}
			doingMove = false;
		});
		
		
		
		
		
		
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				ChessSquare sq = new ChessSquare((i + j) % 2 == 0, i, j);
				mv.getMyPieces()
								.keySet()
								.forEach(p ->
								{
									int gX = coordToGuiCoordX(p);
									int gY = coordToGuiCoordY(p);
									ChessPiece piece;
									piece = new ChessPiece(gX, gY, mv.getMyPieces().get(p), mv.iAmWhite(), il);
									piecesList.add(piece);
									pieces.getChildren().add(piece);
								});

				mv.getEnemyPieces()
								.keySet()
								.forEach(p ->
								{		
									int gX = coordToGuiCoordX(p);
									int gY = coordToGuiCoordY(p);
									ChessPiece piece;
									piece = new ChessPiece(gX, gY, mv.getEnemyPieces().get(p), !mv.iAmWhite(), il);
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
		mv.getMyPieces().keySet()
						.forEach(p ->
						{
							int gX = coordToGuiCoordX(p);
							int gY = coordToGuiCoordY(p);
							ChessPiece piece;
							piece = new ChessPiece(gX, gY, mv.getMyPieces().get(p), mv.iAmWhite(), il);
							piecesList.add(piece);
							pieces.getChildren().add(piece);
						});
				
				
		mv.getEnemyPieces().keySet()
						.forEach(p ->
						{
							int gX = coordToGuiCoordX(p);
							int gY = coordToGuiCoordY(p);
							ChessPiece piece;
							piece = new ChessPiece(gX, gY, mv.getEnemyPieces().get(p), !mv.iAmWhite(), il);
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
		Scene scene = new Scene(new VBox());
		
		
		MenuBar menuBar = new MenuBar();
 
        // --- Menu File
        Menu menuNewGame = new Menu("New Game");
 
        // --- Menu Edit
        Menu menuOptions = new Menu("Options");
 
        // --- Menu View
        Menu menuMore = new Menu("More");
 
        menuBar.getMenus().addAll(menuNewGame, menuOptions, menuMore);
 
 
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, createContent());


			
		primaryStage.setTitle("Very High Quality Chess Program");	
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}


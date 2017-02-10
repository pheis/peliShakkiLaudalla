/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import oha.ai.AI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pyry
 */
public class AiTest {
	
	public AiTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void hello() {
	
			
		MoveValidator mv = new MoveValidator();
		
		AI ai = new AI(mv);
		boolean t = true;
			
		t = t ? mv.movePiece("E2E3") : t;
		t = t ? ai.move() : t;
		t = t ? mv.movePiece("F1E2") : t;
		t = t ? ai.move() : t;
		t = t ? mv.movePiece("G1F3") : t;
		t = t ? ai.move() : t;
		t = t ? mv.movePiece("E1G1") : t;
	
		
		assertEquals(true, t);
	
	
	}
}

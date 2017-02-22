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
public class CastlingTest {
	
	public CastlingTest() {
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
	public void whiteShortCastle() {
		PawnPromoChooser pc1 = new PawnPromoChooser();
		PawnPromoChooser pc2 = new PawnPromoChooser();	
		MoveValidator mv = new MoveValidator(pc1, pc2);
		AI ai = new AI(mv, pc2);
		
		boolean t;
			
		mv.move("E2E3");
		ai.fmove();
		mv.move("F1E2");
		ai.fmove();
		mv.move("G1F3");
		ai.fmove();
		t = mv.move("E1G1");
	
		assertEquals(true, t);
	
	
	
	
	
	
	}
}

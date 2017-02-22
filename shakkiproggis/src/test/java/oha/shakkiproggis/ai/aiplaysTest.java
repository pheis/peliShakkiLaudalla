/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.ai;

import oha.ai.AI;
import oha.shakkiproggis.MoveValidator;
import oha.shakkiproggis.PawnPromoChooser;
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
public class aiplaysTest {
	
	public aiplaysTest() {
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
	PawnPromoChooser pc1 = new PawnPromoChooser();
	PawnPromoChooser pc2 = new PawnPromoChooser();
	
	MoveValidator mv = new MoveValidator(pc1, pc2);
	
	AI ai1 = new AI(mv, pc1);
	AI ai2 = new AI(mv, pc2);
	
	while(!(mv.stalemate() || mv.mate())) {
		ai1.move();
		ai2.move();
	}
	
	assertTrue(true);
	
	}
}

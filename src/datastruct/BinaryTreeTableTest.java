package datastruct;

import static org.junit.Assert.*;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BinaryTreeTableTest {
	private BinaryTreeTable<Integer, String> btt ;

	@Before
	public void setUp() throws Exception {
		this.btt = new BinaryTreeTable<>() ; 	
	}


	@Test
	public void testBinaryTreeTable() {
		assertNotNull("l'arbre n'a pas été créé correctement" , this.btt);  
	}

	@Test
	public void testSelect() {
		this.btt.insert(5, "le5") ; 
		this.btt.insert(6, "le6") ; 
		this.btt.insert(4, "le4") ; 
		
		// cas normal
		assertNotNull("le Node n'a pas pu être récupéré" , this.btt.select(5) ) ;
		assertEquals("la valeur recupérée n'est pas la bonne" , this.btt.select(5) , "le5") ;		
	}

	@Test
	public void testInsert() {
		//Test cas normal 	
		assertTrue("Insertion échouée" , this.btt.insert(2, "2") ) ; 
		
		//Test cas d'echec
		assertFalse("Ce Node existait deja" , this.btt.insert(2 ,  "3") ) ; 
	}
	
	@Test
	public void testShowTree() {
		this.btt.insert(5, "le5") ; 
		this.btt.insert(6, "le6") ; 
		
		this.btt.showTree();
		
	}

	@Test
	public void testDelete() {
		this.btt.insert(5, "5") ; 
		
		// cas d'echec
		assertFalse("Un Node qui n'existe normalement pas a été supprimé" , this.btt.delete(127)) ; 
		
		// cas normal
		assertTrue("Le Node n'a pas été supprimé correctement" , this.btt.delete(5)) ; 
		
	}

	@Test
	public void testToStringNode() {
		this.btt.insert(5, "hey") ; 
		this.btt.insert(4, "hoy") ; 
		
		assertNotNull("La methode a retourné un String null !" , this.btt.toString()) ; 
	}

}

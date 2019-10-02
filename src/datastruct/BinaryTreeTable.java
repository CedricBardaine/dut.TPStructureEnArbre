package datastruct;

import ihm.TreeDraw;

/**
 * Models a binary tree. 
 * @author cedri
 *
 * @param <E>
 * @param <T>
 */
public class BinaryTreeTable<E extends Comparable <E> , T> implements Table<T , E> {
	Node root ; 
	
	public BinaryTreeTable() {
		this.root = null ; 
	}
	
	
	@Override
	public T select(E key) {
		Node nodetmp = this.root ; 
		// donc utiliser nodeEnCours.theValue 
		
		if (this.root == null) return null ; 
		
		Node leNodeTrouve = findNode(nodetmp , key) ; 
		
		if ( leNodeTrouve == null )	{ return null ; }
		else 						{ return leNodeTrouve.theValue ; }
	}

	@Override
	public boolean insert(E key, T data) {
		//// il faut que les keys soit uniques
		Node nodeToCompare = this.root ; 
		boolean ret = false ; 
		
		if( this.root == null) {
			this.root = new Node(key , data) ; 
			ret = true ; 
		}
		else {
			while(ret == false) {
				
//				// si pas de r et > left
//				if ( nodeToCompare.rSon == null && nodeToCompare.lSon != null && key.compareTo(nodeToCompare.lSon.key) > 0 ) {
//					nodeToCompare.rSon = new Node(key , data) ; 
//					ret = true ;
//				}
//				// si pas de l et < r 
//				else if ( nodeToCompare.lSon == null && nodeToCompare.rSon != null && key.compareTo(nodeToCompare.rSon.key) < 0 ) {
//					nodeToCompare.lSon = new Node(key , data) ; 
//					ret = true ; 
//				}
//				
//				else if ( key.compareTo(nodeToCompare.rSon.key) > 0 ) { //si key (quon ajoute) est plus grand que le pluss grand 
//					nodeToCompare = nodeToCompare.rSon ; 
//				}
//				else if ( key.compareTo(nodeToCompare.lSon.key) < 0 ) {
//					nodeToCompare = nodeToCompare.lSon ; 
//				}
//				
//				else {
//					nodeToCompare = nodeToCompare.rSon ; 
//					// choix de passer au rSon, risque de surcharger la branche droite. 
//					// peut-etre remplacer un des deux fils par le nouveau ( et relinker les nouveaux fils apres ) 
//				}
				
				// si rSon & key >ou= rSon
				if ( nodeToCompare.rSon != null && key.compareTo(nodeToCompare.rSon.key) >/*=*/ 0 ) {
					nodeToCompare = nodeToCompare.rSon ; 
				}
				else if( nodeToCompare.rSon != null && nodeToCompare.lSon == null) {
					nodeToCompare.lSon = new Node(key , data) ;  
				}
					
				
				// si lSon & key < lSon
				if ( nodeToCompare.lSon != null && key.compareTo(nodeToCompare.lSon.key) == -1 ) {
					nodeToCompare = nodeToCompare.lSon ; 
				}
				else if( nodeToCompare.lSon != null && nodeToCompare.rSon == null) {
					nodeToCompare.lSon = new Node(key , data) ; 
				}
				
				// si ya pas de fils : 
				if (nodeToCompare.lSon == null && nodeToCompare.rSon == null ) {
					nodeToCompare.rSon = new Node(key, data) ; 
				}
				
				// si >L & <R
				if ( nodeToCompare.rSon != null && nodeToCompare.lSon != null ) {
					nodeToCompare = nodeToCompare.rSon ; 
					
				}
				
			}
		
		} 
		
		assert(invariant()) : "Invariant violé" ; 
		return ret;
	}

	@Override
	public boolean delete(E key) {
		Node tmp = this.root ; 
		boolean suppr = false ; 
		
		while(!suppr) {
			
		
		if ( this.root == null) {
			throw new RuntimeException("la racine ne doit pas etre null !") ;
		}
		
		else {
			
			if ( key.compareTo(tmp.key) == -1 ) 
				if (tmp.lSon != null)
					tmp = tmp.lSon ; 
				else 
					return false ; 
			
			else if ( key.compareTo(tmp.key) == 1 ) 
				if (tmp.rSon != null) 
					tmp = tmp.rSon ;
				else 
					return false ; 
			
			else {
				
				// if it's a leaf node
				if ( tmp.lSon == null && tmp.rSon == null) {
					tmp = null ; 
					suppr = true ; 
					
				}
				
				// if it has only a right child 
				else if (tmp.lSon == null) {
					Node tmp2 = tmp.rSon ; 
					tmp = tmp.rSon ; 
					suppr = true ; 
					
				}
				// if it has only a left child 
				else if (tmp.rSon == null) {
					Node tmp2 = tmp.lSon ; 
					tmp = tmp.lSon ; 
					suppr = true ; 
					
				}
				
				
			}
			
			
		}
		
		
		}

		assert(invariant()) : "Invariant violé" ;
		return suppr;
	}
	
	/**
	 * Allows to display the binary tree in its entirety. 
	 * Or at least all the children of a specified Node.
	 * The shall be passed in parameters if the need is to display all the Node. 
	 * @param leNodeParseur The Node that must be displayed with its children. 
	 * @return The binary tree or a part of it. 
	 */
	public String toString(Node leNodeParseur) {
		String ret = null ; 
//		Node leNodeParseur = this.root ; 
		
		if ( leNodeParseur != null) {
			toString(leNodeParseur.lSon) ; 
			ret += " " + leNodeParseur.key;
			toString(leNodeParseur.rSon) ; 			
		}
		
		return ret ;
	}
	
	/**
	 * Draws a graphical tree 
	 */
	public void showTree() {
		TreeDraw<E,T> td = new TreeDraw<>(this.root) ; 
		td.drawTree(); 
		
		try { Thread.sleep(5000) ;} 
		catch(InterruptedException e) { Thread.currentThread().interrupt() ; }
	}
	
	/**
	 * Finds the node matching with the key passed in parameters.
	 * @param theNode The node where the method begins.
	 * @param key The key used to find the corresponding Node.
	 * @return
	 */
	private Node findNode(Node theNode , E key) {
		if ( theNode == null )  	return null ;
		if ( theNode.key == key ) 	return theNode ; 
		
		Node ret = null ; 
		if(theNode.key.compareTo(key) == 1 ) 
			ret = findNode(theNode.lSon , key) ; 
		else if ( theNode.key.compareTo(key) == -1 )
			ret = findNode(theNode.rSon , key ) ; 
		
		return ret ; 
	}
	
	
	private boolean invariant() {
		boolean ret = true ; 
		
		if (this.root == null) ret = false ; 
		
		// ... 
		
		return ret ; 
	}
	
	public class Node {
		private Node lSon ; 	// null possible
		private Node rSon ; 	// null possible
		private Node father ;	// null si le noeud est root
		private T theValue ; 	
		private E key ; 		
		
		public Node(E newKey , T newData) {
			this.lSon = null ; 
			this.rSon = null ; 
			this.father = null ; 
			////
			this.key = newKey ; 
			this.theValue = newData ; 
		}
		
		// - - - - - Accesseurs
		
		/**
		 * @return the key of the Node.
		 */
		public String getLabel() { return this.key.toString() ; }
		/**
		 * @return the left son of the Node.
		 */
		public Node getLeft() { return this.lSon ; }
		/**
		 * @return the right son of the Node
		 */
		public Node getRight() { return this.rSon ; }
		
		// - - - - - Duplication
		
		/**
		 * Is usefull for the method showtree()
		 */
		public Node clone() {
//			Node retNode = new Node(this.key , this.theValue) ; 
//			retNode.lSon = this.lSon ; 
//			retNode.rSon = this.rSon ; 
//			retNode.father = this.father ; 
//			
//			return retNode ; 
			// ah non c'est pas comme ca apparement 
			return null ; // !!!!
		}
	}
}

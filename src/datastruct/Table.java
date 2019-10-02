package datastruct;

public interface Table<T, E extends Comparable<E>> {
	
	/**
	 * This method return the data in the node matching the key.
	 * It's recursive. 
	 * @param key The key used to find the data.
	 * @return the data
	 */
	public T select(E key) ; 
	
	/**
	 * If the key doesn't already exist in the table, this method inserts to the right place in the tree the new node wich the key an the data are passed in parameters. 
	 * @param key The key of the new node.
	 * @param data The data of the new node.
	 * @return false if the insertion isn't possible.
	 */
	public boolean insert(E key , T data) ; 
	
	/**
	 * Deletes from the binary tree the node that corresponds to the key passed in parameters. 
	 * It's recursive.
	 * @param key The key of the node to delete.
	 * @return false is the deletion isn't possible. (the key doesn't exists for example)  
	 */
	public boolean delete(E key) ; 

}

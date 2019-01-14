package app;
import java.util.ArrayList;
import java.util.List;

public class Sommet extends Symbol {

    private List<String> successor; //les seccesseurs d'un sommet
   
    /**
     * les Accesseurs et les Mutateurs
     * @return
     */
	public List<String> getSuccessor() {
		return successor;		
	}

	public void setSuccessor(List<String> successor) {
		this.successor = successor;
	}

	/**
	 * le constructeur
	 * @param alphabet
	 * @param probabilty
	 */
	public Sommet(String alphabet, float probabilty) {
		super(alphabet, probabilty);
		this.successor=new ArrayList<String>();
		
		// TODO Auto-generated constructor stub
	}
   /**
    * Methode permet d'ajouter un suivant au sommet
    * @param next
    */

	public void AddSuccessor(String next){
    	this.successor.add(next);
    }
}

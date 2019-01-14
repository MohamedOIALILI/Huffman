package app;

public class Symbol {
	
	protected String alphabet;     // alphabet de la source
	protected float probabilty;	   // probabilitée d'occurence 
	protected String code;         // le code de symbol
	

	/**
	 *    le constructeur
	 * @param alphabet
	 * @param probabilty
	 */
	
	public Symbol(String alphabet, float probabilty) {
		super();
		this.alphabet = alphabet;
		this.probabilty = probabilty;
		this.code="";
		
	}
	/**
	 *  Methode permet de changer le contenu d'un symbol
	 * @param s
	 */
    public void change(Symbol s){
    	this.alphabet = s.getAlphabet();
		this.probabilty =s.getProbabilty();		
    }
	

    /**
	 *  les Accesseurs et les Mutateurs
	 * @return
	 */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	public float getProbabilty() {
		return probabilty;
	}

	public void setProbabilty(float probabilty) {
		this.probabilty = probabilty;
	}

	

	
	
          
}

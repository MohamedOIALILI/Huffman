package app;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Huffman {

	private List<Sommet> huffmanTree; //L'arbre de huffman
	private float CodeEntropy;        //l'entropie de la source   
	private float CodeLength;         //la longueur moyenne du code
	private float CodeEfficiency;     //l'efficacité de code 
	/**
	 * le constructeur
	 */
	public Huffman() {
	     this.huffmanTree=new ArrayList<Sommet>();
	     this.CodeEntropy=0.0f;
	     this.CodeLength=0.0f; 
	     this.CodeEfficiency=0.0f;
	}
	/**
	 *  les Accesseurs et les Mutateurs	  
	 */
	public List<Sommet> getHuffmanTree() {
		return huffmanTree;
	}
	public void setHuffmanTree(List<Sommet> huffmanTree) {
		this.huffmanTree = huffmanTree;
	}
	public float getCodeEntropy() {
		return CodeEntropy;
	}
	public void setCodeEntropy(float codeEntropy) {
		CodeEntropy = codeEntropy;
	}
	public float getCodeLength() {
		return CodeLength;
	}
	public void setCodeLength(float codeLength) {
		CodeLength = codeLength;
	}
	public float getCodeEfficiency() {
		return CodeEfficiency;
	}
	public void setCodeEfficiency(float codeEfficiency) {
		CodeEfficiency = codeEfficiency;
	}
	/**
	 *  Methode permet le calcul de certains statistique du code de huffman   
	 * @param list
	 */
	public void statisticCode(List<Symbol> list){
		for(int i=0;i<list.size();i++){
			CodeEntropy+=list.get(i).getProbabilty()*((Math.log(1/list.get(i).getProbabilty()))/Math.log(2));
			CodeLength+=list.get(i).getProbabilty()*list.get(i).getCode().length();
			CodeEfficiency=(CodeEntropy/CodeLength)*100;
		}
	}
	/**
	 *  Methode qui permet de cree l'arbre de codage de huffman 
	 * @param list
	 */	
	public void creatHuffamTree(List<Symbol> list){
		List<Sommet> huffman=new ArrayList<Sommet>();
		Sommet s1,s2,s3;
		while(list.size()!=1){
			list=sort(list);
			s1=new Sommet(list.get(list.size()-1).getAlphabet(), list.get(list.size()-1).getProbabilty());
			s2=new Sommet(list.get(list.size()-2).getAlphabet(), list.get(list.size()-2).getProbabilty());			
			s3=new Sommet(s1.getAlphabet()+s2.getAlphabet(),s1.getProbabilty()+s2.getProbabilty());
			s3.AddSuccessor(s1.getAlphabet());
			s3.AddSuccessor(s2.getAlphabet());
			if(search(huffman,s1.getAlphabet()) ==null){
				huffman.add(s1);	
			}
			if(search(huffman,s2.getAlphabet()) ==null){
				huffman.add(s2);	
			}			
			huffman.add(s3);	
			list.remove(list.size()-1);
			list.remove(list.size()-1);
			list.add(new Symbol(s3.getAlphabet(),s3.getProbabilty()));			
		}
		for(int i=huffman.size()-1;i>=0;i--){
			huffmanTree.add(huffman.get(i));
		}
	}
	
	/**
	 * Methode permet le trie des symboles par rapport leurs probabilitéés
	 * @param list
	 * @return
	 */
	//   
	private List<Symbol> sort (List<Symbol> list){
		 Symbol s3;
		  for(int i=0;i<=list.size()-2;i++)
			  for( int j=i+1;j<list.size();j++)
			  {			 
				  if(list.get(i).getProbabilty()<list.get(j).getProbabilty()){
					  s3=new Symbol(list.get(i).getAlphabet(), list.get(i).getProbabilty());
					  list.set(i, list.get(j));
					  list.set(j,s3);
				  }
			  }
		  return list;
		
	}
	/**
	 *  Methode de codage des symboles de la source (le fichier) 
	 * @param st
	 */
	//
	public void Encoding(Stream st){
		List<Symbol> list= st.getSourceCoding();
		List<Sommet> visitedSommet =new ArrayList<Sommet>();
		Stack<Sommet> path=new Stack<Sommet>();		
		boolean stop=false; 
		for(int i=0;i<list.size();i++){
			path.push(huffmanTree.get(0));
			visitedSommet.add(huffmanTree.get(0));
			stop=false;
			while(!stop){			
				Sommet s1=path.peek();
				if(s1.getAlphabet().equals(list.get(i).getAlphabet()) ) {
					Symbol s=list.get(i);
					s.setCode(readCode(path));
					list.set(i,s);
					path.clear();
					visitedSommet.clear(); 
					  stop=true;
				}
				else{
					List<Sommet> next=new ArrayList<Sommet>();					
					if(s1.getSuccessor().size()!=0){				
					     next.add(search(huffmanTree,s1.getSuccessor().get(0) ));					
					     next.add(search(huffmanTree,s1.getSuccessor().get(1) ));			
					}
					next=intersection(visitedSommet, next);
					if(next.size()==0){
						path.pop();					
				    }
					else{	
					
	
	                     path.push(next.get(0));
	                     visitedSommet.add(next.get(0));
	
			  		}
				}
			
			}//fin de while				
		}//fin de for
		st.setSourceCoding(list);
	}
	/**
	 *    Methode permet la cherche d'un sommet a partir son nom  
	 * @param list
	 * @param str
	 * @return
	 */
	private Sommet search(List<Sommet> list,String str){
		for(Sommet s:list){
			if(s.getAlphabet().equals(str))
				return s;
		}
		return null;
	}	
	/**
	 *   Methode permet de conclure le code d'un symbole a partir leur chemin dans l'arbre
	 * @param list
	 * @return
	 */
	private String readCode(List<Sommet> list){
		String str="";
		for(int i=0;i<list.size()-1;i++){
			if(list.get(i).getSuccessor().get(0).equals(list.get(i+1).getAlphabet())){
			 	str+="0";
			}
			else{
				str+="1";
			}
		}
		return str;
	}
	/**
	 *  Methode permet le renvoi la liste des sommet qui ne sont pas deja visité
	 * @param list
	 * @param next
	 * @return
	 */
	public static List<Sommet> intersection(List<Sommet> list, List<Sommet> next)
    {
        for (Sommet s:list)
            for (int i=0;i<next.size();i++)
            {   
            	Sommet n=next.get(i);
                if (n.getAlphabet().equals( s.getAlphabet()))
                    next.remove(i);
            }
        return next;
    }
}

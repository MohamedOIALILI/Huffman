package app;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Stream {
	private  List<Symbol> sourceCoding;   //la liste des symboles a envoyés par la source  
	private String sourceSymbol;          //la chaine d'envoi
	/**
	 * le constructeur
	 */
	public Stream(){
		  sourceCoding=new ArrayList<Symbol>();
		  sourceSymbol="";
		}
	/**
	 * les Accesseurs et les Mutateurs
	 * 
	 */
	public  List<Symbol> getSourceCoding() {
		return sourceCoding;
	}
	public  void setSourceCoding(List<Symbol> sourceCode) {
		 sourceCoding = sourceCode;
	}
	public  String getSourceSymbol() {
		return sourceSymbol;
	}
	public void setSourceSymbol(String sourceSymbol) {
		this.sourceSymbol = sourceSymbol;
	}
	/**
	 * Methode permet de copier la liste des symboles
	 */
	public List<Symbol> clone(){
		List<Symbol> list=new ArrayList<Symbol>();
		for(int i=0;i<sourceCoding.size();i++)
			list.add(sourceCoding.get(i));
		return list;
	}
	/**
	 *  Methode permet de renvoyée la chaine a transmetter 
	 * @param path
	 * @return
	 */
	
	public String inputFile(String path){
		File file =new File(path);
		String str="";
		try {
			FileReader reader=new FileReader(file);
			BufferedReader buffer=new BufferedReader(reader);			
			while ((str=buffer.readLine())!=null)
			{
				sourceSymbol+=str+"\n";
			}		
			reader.close();
			buffer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;		
	}
	/**
	 *  Methode permet de renvoyée la chaine a transmetter les bits
	 * @param path
	 */
	public void outputFile(String path,String value){
		File file =new File(path+".txt");
		
		try {
			FileWriter writer=new FileWriter(file);
			BufferedWriter buffer=new BufferedWriter(writer);			
			buffer.write(value);
			buffer.close();
			writer.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	/**
	 * Methode permet d'extraire les alphabets et leurs probabilitées  
	 */
	public void symbolExtraction(){
	       
	       String str=sourceSymbol;
	       float total=sourceSymbol.length();
	       int i=0;
	       while (str!=""){	    	   
	    	   char [] alphabet =str.toCharArray();
	    	   str="";
	    	   char alpha=alphabet[0];
	    	   for(int j=0;j<alphabet.length;j++){
	    		   if(alphabet[j]==alpha){
	    			   i++;	    			   
	    			   }
	    		   else{
	    			   str+=alphabet[j];
	    		   }
	    	   }	    	   
	    	   Symbol s=new Symbol(String.valueOf(alpha), i/total);
	    	   sourceCoding.add(s);	 
	    	   i=0;
	       }
	       System.out.print("");
	}
	
	/*
	public static void main(String [] arg){
		Stream s=new Stream();
		s.inputFile("C:\\Documents and Settings\\Administrator\\Desktop\\doc.txt");
		s.symbolExtraction();
		Huffman huf=new Huffman();
         		huf.creatHuffamTree(s.clone());
         		huf.Encoding(s);
	}
	//*/
            
}

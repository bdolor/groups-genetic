package main.java.GeneticAlgorithm.Common;

import main.java.GeneticAlgorithm.Interfaces.IChromosome;

public abstract class BinaryChromosome implements IChromosome {
	
	private String Encoding = null;
	private int Length;

	public String getEncoding() {
		return this.Encoding;
	}
	
	public void setEncoding(String encoding) {
		this.Encoding = encoding;
		this.Length = encoding.length();		
	}
	
	public int getLength() {
		return this.Length;
	}
	
	public BinaryChromosome(int length) {
		this.setEncoding(this.getRandomBinary(length));
	}

	public BinaryChromosome(String encoding) {
		this.setEncoding(encoding);
	}
	
	protected String getRandomBinary(int length) {
		String encoding = "";
		for (int i=0; i<length; i++) {
			encoding += Math.round(Math.random());
		}
		return encoding;
	}
	
	public static BinaryChromosome NewInstance(IChromosome o) throws GeneticAlgorithmException {
		IChromosome newInstance;
		try {
			
			newInstance = o.getClass().newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new GeneticAlgorithmException(e.getMessage());			
		}
		
		return (BinaryChromosome) newInstance;				
	}

}

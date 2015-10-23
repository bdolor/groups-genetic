package main.java.GeneticAlgorithm.Binomial;

import main.java.GeneticAlgorithm.Common.BinaryChromosome;

public class BinomialChromosome extends BinaryChromosome {

	public final static int ENCODING_LENGTH = 20;
	public final static double MAX_INPUT = 10;
	
	public BinomialChromosome() {
		super(BinomialChromosome.ENCODING_LENGTH);
	}
	
	public BinomialChromosome(String encoding) {
		super(encoding);
	}
	

	//  Fitness is binomial -3(x^2) - 2x + 4
	@Override
	public double GetFitness() {
		return (-3 * Math.pow(this.getChromosomeValue(), 2)) - (2 * this.getChromosomeValue()) + 4;
	}

	protected double getChromosomeValue() {
		return BinomialChromosome.MAX_INPUT / Math.pow(2, BinomialChromosome.ENCODING_LENGTH)
				* Integer.parseInt(this.getEncoding());
	}

}

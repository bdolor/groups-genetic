package main.java.GeneticAlgorithm.Binomial;

import main.java.GeneticAlgorithm.Common.BinaryChromosome;

public class BinomialChromosome extends BinaryChromosome {

	public final static int ENCODING_LENGTH = 30;
	public final static double MAX_INPUT = 150;

	public BinomialChromosome() {
		super(BinomialChromosome.ENCODING_LENGTH);
	}

	public BinomialChromosome(String encoding) {
		super(encoding);
	}

	// Fitness is binomial -0.1*x^2 + 20x + 50
	@Override
	public double getFitness() {
		return (-0.1 * Math.pow(this.getChromosomeValue(), 2)) + (20 * this.getChromosomeValue()) + 50;
	}

	public double getChromosomeValue() {
		int intValue = Integer.parseInt(this.getBinaryEncoding(), 2);
		return BinomialChromosome.MAX_INPUT / Math.pow(2, BinomialChromosome.ENCODING_LENGTH) * intValue;
	}

	@Override
	public String getEncoding() {
		return this.getBinaryEncoding();
	}

}

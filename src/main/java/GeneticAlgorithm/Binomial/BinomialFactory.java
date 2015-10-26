package main.java.GeneticAlgorithm.Binomial;

import main.java.GeneticAlgorithm.Common.BinaryChromosome;
import main.java.GeneticAlgorithm.Interfaces.IFactory;

public class BinomialFactory<T extends BinaryChromosome> implements IFactory<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T CreateChromosome() {
		return (T) new BinomialChromosome();
	}

}

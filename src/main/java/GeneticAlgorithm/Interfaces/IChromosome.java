package main.java.GeneticAlgorithm.Interfaces;

public interface IChromosome<T> {
	double getFitness();
	T getEncoding();
	String[] getMembers();
}

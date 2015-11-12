package main.java.GeneticAlgorithm.Interfaces;

public interface IChromosome<T> {
	double getFitness();
	T getEncoding();
	boolean isValid();
	/*String[] getMembers();
	double[] getEachGroupMaxDistance();
	double[] getEachGroupGH();*/
}

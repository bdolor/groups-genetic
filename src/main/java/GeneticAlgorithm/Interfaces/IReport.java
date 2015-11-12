package main.java.GeneticAlgorithm.Interfaces;

import main.java.GeneticAlgorithm.GeneticAlgorithm;

public interface IReport<T extends IChromosome<?>> {
	public void updateReport(double averageFitness, double maxFitness, T fittest, boolean isConverged, long startTime);
	public void initializeReport(GeneticAlgorithm<?> algorithm);	
}

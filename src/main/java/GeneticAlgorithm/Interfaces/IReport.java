package main.java.GeneticAlgorithm.Interfaces;

public interface IReport {
	void updateReport(double averageFitness, double averageValidGroups, int validSolutions, double maxFitness,
			double minFitness, double maxValidFitness);
}

package main.java.GeneticAlgorithm;

public class GeneticAlgorithmConfig {
	
	private int PopulationSize = 50;
	private int MaximumEvolutions = 1000;
	private int RequiredParentCount = 2;
	private double CrossoverProbability = 0.3; // (0.5-1.0)
	private double MutationProbability = 0.3; // (0.001-0.05)
	
	public int getPopulationSize() {
		return PopulationSize;
	}
	public double getCrossoverProbability() {
		return CrossoverProbability;
	}
	public void setCrossoverProbability(double crossoverProbability) {
		CrossoverProbability = crossoverProbability;
	}
	public double getMutationProbability() {
		return MutationProbability;
	}
	public void setMutationProbability(double mutationProbability) {
		MutationProbability = mutationProbability;
	}
	public void setPopulationSize(int populationSize) {
		PopulationSize = populationSize;
	}
	public int getMaximumEvolutions() {
		return MaximumEvolutions;
	}
	public void setMaximumEvolutions(int maximumEvolutions) {
		MaximumEvolutions = maximumEvolutions;
	}
	public int getRequiredParentCount() {
		return RequiredParentCount;
	}
	public void setRequiredParentCount(int requiredParentCount) {
		RequiredParentCount = requiredParentCount;
	}
}

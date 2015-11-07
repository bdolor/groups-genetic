package main.java.GeneticAlgorithm;

public class GeneticAlgorithmConfig {
	
	private int PopulationSize = 500;
	private int MaximumEvolutions = 20000;
	private int RequiredParentCount = 2;
	private double CrossoverProbability = 0.9;
	private double MutationProbability = 0.2;
	private int ReportRefreshRate = 2;
	
	public int getPopulationSize() {
		return PopulationSize;
	}
	public int getReportRefreshRate() {
		return ReportRefreshRate;
	}
	public void setReportRefreshRate(int reportRefreshRate) {
		ReportRefreshRate = reportRefreshRate;
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

package main.java.GeneticAlgorithm;

public class GeneticAlgorithmConfig {
	
	private int PopulationSize = 50;
	private int MaximumEvolutions = 1000;
	private int RequiredParentCount = 2;
	private double CrossoverProbability = 1.0; // (0.5-1.0) 
	private double MutationProbability = 0.05; // (0.001-0.05) 
	
	public int getPopulationSize() {
		return PopulationSize;
	}
	public double getCrossoverProbability() {
		return CrossoverProbability;
	}
	
	/**
	 * Adjusts the Crossover probability based on how fit the parents are.
	 * 
	 * If parent fitness is low, CP is increased ( promote the extensive recombination )
	 *  
	 * 
	 * @param avgFitness
	 * @param maxFitness
	 * @param parentFitness 
	 */
	public void setCrossoverProbability(double avgFitness, double maxFitness, double parentFitness) {
		// adaptive probability
		double fallback = 0.99;
		double crossoverProbability;
		double upperLimit = 1.0;
		
		// CP = (max fitness - parent fitness)/ (max fitness - avg fitness), CP <= 1.0
		crossoverProbability = upperLimit * ((maxFitness - parentFitness) / (maxFitness - avgFitness));
		
		/**
		 * safety, must be less than 1.0
		 * A value of greater than 1 occurs when parentFitness
		 * is less than average Fitness
		 */
		
		if (crossoverProbability >= upperLimit ) {
			crossoverProbability = fallback;
		}
		CrossoverProbability = crossoverProbability;

	}
	public double getMutationProbability() {
		return MutationProbability;
	}
	/**
	 * Adjusts the Mutation probability based on how fit the parents are.
	 * 
	 * If parent fitness is high, MP is decreased ( prevent disruption of the solution ) 
	 * 
	 * @param avgFitness
	 * @param maxFitness
	 * @param parentFitness 
	 */
	public void setMutationProbability(double avgFitness, double maxFitness, double parentFitness) {
		// adaptive probability
		double fallback = 0.049;
		double mutationProbability;
		double upperLimit = 0.05;
		
		// MP <= 0.05
		mutationProbability = upperLimit * ((maxFitness - parentFitness) / (maxFitness - avgFitness));
		
		/**
		 * safety, must be less than 0.05
		 * A value of greater than 0.05 occurs when parentFitness
		 * is less than average Fitness
		 */
		if (mutationProbability >= upperLimit ){
			mutationProbability = fallback;
		}
		
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

package main.java.GeneticAlgorithm;

import java.util.ArrayList;

import main.java.GeneticAlgorithm.Common.GeneticAlgorithmException;
import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.ICrossOver;
import main.java.GeneticAlgorithm.Interfaces.IFactory;
import main.java.GeneticAlgorithm.Interfaces.IMutation;
import main.java.GeneticAlgorithm.Interfaces.ISelect;
import main.java.GeneticAlgorithm.Interfaces.ISolution;

@SuppressWarnings("rawtypes")
public class GeneticAlgorithm<T extends IChromosome> {

	protected GeneticAlgorithmConfig Config;
	protected IFactory<T> Factory;
	protected ICrossOver<T> CrossOver;
	protected IMutation<T> Mutation;
	protected ISelect<T> Select;
	protected ISolution<T> Solution;

	public void Evolve() throws GeneticAlgorithmException {

		if (!this.isValidConfiguraiton()) {
			throw new GeneticAlgorithmException("Genetic Algorithm not initalized correctly.");
		}

		/**
		 * Initialize population.
		 * 
		 * If PopulationSize = 50 and MAXIMUM_STUDENTS = 512
		 * population = ArrayList[50][512]
		 */
		ArrayList<T> population = new ArrayList<T>();
		for (int i = 0; i < this.Config.getPopulationSize(); i++) {
			population.add(this.Factory.CreateChromosome());
		}
		System.out
				.println(String.format("Initialized population with %d candidates.", this.Config.getPopulationSize()));

		boolean isComplete = false;
		int evolution = 1;
		while (!isComplete) {

			/**
			 * Calculate population fitness.
			 * 
			 * Creates an array of fitness values for each of the group arrangement solutions
			 * in a population of solutions, also sums the total fitness for the entire population 
			 * of group arrangement solutions.
			 */
			double totalFitness = 0;
			double[] populationFitness = new double[this.Config.getPopulationSize()];
			for (int i = 0; i < this.Config.getPopulationSize(); i++) {
				populationFitness[i] = population.get(i).getFitness();
				totalFitness += populationFitness[i];
			}

			/**
			 * Build new generation, two at a time to max population size.
			 * 
			 * Grab two of the 'best' parents, 
			 * create two 'offspring' (crossover)
			 * mutate the two 'offspring' 
			 * add them to the new Generation until max population size
			 */ 
			
			ArrayList<T> newGeneration = new ArrayList<T>();
			int crossoverCount = 0;
			int mutationCount = 0;
			while (newGeneration.size() < this.Config.getPopulationSize()) {
				
				// Select the best 'individuals' (student arrangement in a class) within a population
				T[] parents = this.Select.GetParents(population, populationFitness,
						this.Config.getRequiredParentCount());
				
				// Crossover is only applied on a random basis, 
				// that is, if a random number is less that 0.3 = CrossoverProbability
				// @see GeneticAlgorithmConfig.java
				T[] offspring = null;
				if (Math.random() < this.Config.getCrossoverProbability()) {
					offspring = this.CrossOver.CrossOver(parents);
					crossoverCount++;
				} else {
					offspring = parents;
				}
				
				// Mutation only applied on a random basis, 
				// that is, if a random number is less than 0.3 = MutationProbability
				// @see GeneticAlgorithmConfig.java
				T[] mutatedOffspring = null;
				if (Math.random() < this.Config.getMutationProbability()) {
					mutatedOffspring = this.Mutation.Mutate(offspring);
					mutationCount++;
				} else {
					mutatedOffspring = offspring;
				}

				for (int i = 0; i < mutatedOffspring.length; i++) {
					if (newGeneration.size() < this.Config.getPopulationSize()) {
						newGeneration.add(mutatedOffspring[i]);
					}
				}
			}

			// Log generation stats
			totalFitness = 0;
			//int invalidSolutions = 0;
			for (int i = 0; i < this.Config.getPopulationSize(); i++) {
				totalFitness += population.get(i).getFitness();
//				if (0d == population.get(i).getFitness()) {
//					invalidSolutions++;
//				}
			}
			System.out.println(String.format(
					"Generation %d:   {avg fitness = %f}, {best fitness = %f}, {crossover = %d}, {mutations = %d}",
					evolution, totalFitness / this.Config.getPopulationSize(),
					this.Solution.getFittestSolution(population).getFitness(), crossoverCount,
					mutationCount));
			
			// need the index of the best grouping (winner class)
			int winnerClass = this.Solution.getFittestSolutionIndex();
			//System.out.println(String.format("Index # of the fittest: %d", winnerClass ));
			
			// need to output member IDs of the groups (in winner class)
			String[] memberIDs = this.Solution.getMembersOfGroup(population, winnerClass);
			
			// highest Euclidean distance of each group (in winner class)
			double [] eachGroupED = this.Solution.getEachGroupDistance(population, winnerClass);
			
			for(int i = 0; i < memberIDs.length; i++){
				System.out.print("("+(i+1) +") [ED="+ eachGroupED[i] +"] [IDs=" + memberIDs[i] + "] " );
			};
			System.out.println(String.format("\n")); // line break
			
						
			// need to output GH of each group (in winner class)
			
			// sum of all GH values (in winner class)
			
			// Find solutions
			// new ArrayList
			ArrayList<T> solutions = this.Solution.getSolutions(newGeneration);

			// Check terminating condition
			isComplete = solutions.size() > 0 || evolution == this.Config.getMaximumEvolutions();
			population = newGeneration;
			evolution++;
		}

		System.out.println(String.format("Algorithm reached terminating condition.  Fittest solution is %f",
				this.Solution.getFittestSolution(population).getFitness()));
	}

	protected Boolean isValidConfiguraiton() {
		return this.Config != null && this.Factory != null && this.CrossOver != null && this.Mutation != null
				&& this.Select != null && this.Solution != null;
	}

	public void setConfig(GeneticAlgorithmConfig config) {
		Config = config;
	}

	public void setFactory(IFactory<T> factory) {
		Factory = factory;
	}

	public void setCrossOver(ICrossOver<T> crossOver) {
		CrossOver = crossOver;
	}

	public void setMutation(IMutation<T> mutation) {
		Mutation = mutation;
	}

	public void setSelect(ISelect<T> select) {
		Select = select;
	}

	public void setSolution(ISolution<T> solution) {
		Solution = solution;
	}
}

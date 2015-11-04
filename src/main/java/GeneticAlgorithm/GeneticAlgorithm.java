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
	protected double fittestSolution;

	public void Evolve() throws GeneticAlgorithmException {

		if (!this.isValidConfiguration()) {
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
			
			T elite = this.Solution.getFittestSolution(population);
			ArrayList<T> newGeneration = new ArrayList<T>();
			
			// inject a little elitism into the new generation
			newGeneration.add(elite);
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

			/************** LOG RESULTS **************/
			
			totalFitness = 0;
			int convergence = 0;
			int convergenceIndex;
			for (int i = 0; i < this.Config.getPopulationSize(); i++) {
				
				// sum up fitness of a generation
				populationFitness[i] = population.get(i).getFitness();
				totalFitness += populationFitness[i];
				
				// check for convergence, notify 
				if (population.get(i).isAllValidGroups()) {
					convergence++;
					convergenceIndex = i;
					// average fitness level for all populations up to this point
					double avg = totalFitness / i+1;
					// winner, winner, chicken diner
					System.out.println("!!!!!!!!  CONVERGENCE !!!!!!!!!!! -> index: " + convergenceIndex);
					this.displayResults(population, avg, totalFitness, convergenceIndex, crossoverCount, mutationCount, convergence, evolution);
				}
			}
			// average fitness level for the Generation
			double avg = totalFitness / this.Config.getPopulationSize();
			/**
			 * if no convergence, then we should display the fittest
			 * using the index of the best grouping (winner class)
			 */
			int winnerClass = this.Solution.getFittestSolutionIndex();

			this.displayResults(population, avg, totalFitness, winnerClass, crossoverCount, mutationCount, convergence, evolution);

			/**
			 * ********** END LOG RESULTS ***********
			 */
			
			// Find solutions
			// new ArrayList
			ArrayList<T> solutions = this.Solution.getSolutions(newGeneration);

			// Check terminating condition
			// stop if convergence is reached, or maximium evolutions is reached, 
			// or somehow there are no solutions (safety)
			isComplete = convergence > 0 || solutions.size() > 0 || evolution == this.Config.getMaximumEvolutions();
			// reassign variable 
			population = newGeneration;
			evolution++;
		}

		System.out.println(String.format("Algorithm reached terminating condition.  Fittest solution is %f",
				this.fittestSolution));
	}

	public void displayResults(ArrayList population, double avg, double total, int index, int crossover, int mutation, int convergence, int evolution) {
		// need to output member IDs of the groups (in winner class)
		String[] memberIDs = this.Solution.getMembersOfGroup(population, index);

		// highest Euclidean distance of each group (in winner class)
		double[] eachGroupED = this.Solution.getEachGroupDistance(population, index);

		// need to output GH of each group (in winner class)
		double[] eachGroupGH = this.Solution.getEachGroupGH(population, index);

		// sum of all GH values
		double sumFitness = 0;
		for (int i = 0; i < eachGroupGH.length; i++) {
			sumFitness += eachGroupGH[i];
		}
		// track the actual fittestSolution
		if (sumFitness > this.fittestSolution) {
			this.fittestSolution = sumFitness;
		}

		// loop 
		System.out.println(String.format(
			"Generation %d:   {convergence: %d }, {avg gen fitness = %f}, {best fitness = %f}, {total gen fitness = %f}, {crossover = %d}, {mutations = %d}",
			evolution, convergence, avg, sumFitness, total, crossover, mutation));

		// requirement outputs 
		String GH = "";
		String ED = "";
		String ID = "";
		for (int i = 0; i < memberIDs.length; i++) {
			GH += "(" + (i + 1) + ")[" + eachGroupGH[i] + "], ";
			ED += "(" + (i + 1) + ")[" + eachGroupED[i] + "], ";
			ID += "(" + (i + 1) + ")[" + memberIDs[i] + "], ";
		};
		System.out.println("GH: " + GH);
		System.out.println("ED: " + ED);
		System.out.println("ID: " + ID);
		System.out.println("");
	}
	
	
	protected Boolean isValidConfiguration() {
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

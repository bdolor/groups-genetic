package main.java.GeneticAlgorithm;

import java.util.ArrayList;
import main.java.GeneticAlgorithm.Common.GeneticAlgorithmException;
import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.ICrossOver;
import main.java.GeneticAlgorithm.Interfaces.IFactory;
import main.java.GeneticAlgorithm.Interfaces.IMutation;
import main.java.GeneticAlgorithm.Interfaces.IReport;
import main.java.GeneticAlgorithm.Interfaces.ISelect;
import main.java.GeneticAlgorithm.Interfaces.ISolution;
import main.java.GeneticAlgorithm.StudentGrouping.GroupEncodingChromosome;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroups;

@SuppressWarnings("rawtypes")
public class GeneticAlgorithm<T extends IChromosome> {

	protected GeneticAlgorithmConfig Config;
	protected IFactory<T> Factory;
	protected ICrossOver<T> CrossOver;
	protected IMutation<T> Mutation;
	protected ISelect<T> Select;
	protected ISolution<T> Solution;
	protected IReport Report;
	protected double fittestSolution;
	protected int CrossoverCount;
	protected int MutationCount;
	protected double[] EvolutionFitness;

	/**
	 * Build new generation, two at a time to max population size.
	 *
	 * Grab two of the 'best' parents, create two 'offspring' (crossover)
	 * mutate the two 'offspring' add them to the new Generation until max
	 * population size
	 */
	public void Evolve() throws GeneticAlgorithmException {

		if (!this.isValidConfiguration()) {
			throw new GeneticAlgorithmException("Genetic Algorithm not initalized correctly.");
		}

		long startTime = System.nanoTime();

		ArrayList<T> population = this.getInitialPopulation();

		this.EvolutionFitness = new double[this.Config.getReportRefreshRate()];

		boolean isComplete = false;
		int evolution = 1;
		int convergenceCount = 0;
		double sumFitness = 0;
		double maxFitness = 0;

		while (!isComplete) {

			/**
			 * Calculate population fitness.
			 *
			 * Creates an array of fitness values for each of the
			 * group arrangement solutions in a population of
			 * solutions, also sums the total fitness for the entire
			 * population of group arrangement solutions.
			 */
			 
			/********* PLEASE LEAVE HERE FOR ADAPTIVE PROBABILITY **********/
			
			double totalFitness = 0.0;
			//double [] populationFitness = this.getPopulationFitness(population);
			double[] populationFitness = new double[this.Config.getPopulationSize()];
			for (int i = 0; i < this.Config.getPopulationSize(); i++) {
				populationFitness[i] = population.get(i).getFitness();
				totalFitness += populationFitness[i];
				if (populationFitness[i] > maxFitness) {
					maxFitness = populationFitness[i];
				}
			}
			double parentFitness = this.Config.getRequiredParentCount();
			double avgFitness = totalFitness / this.Config.getPopulationSize();

			/**
			 * ******** END OF ADAPTIVE PROBABILITY *********
			 */
			ArrayList<T> newGeneration = new ArrayList<T>();

			while (newGeneration.size() < this.Config.getPopulationSize()) {

				// Select the best 'individuals' (student arrangement in a
				// class) within a population
				T[] parents = this.Select.GetParents(population, populationFitness,
					this.Config.getRequiredParentCount());

				// calculate parent fitness (required for adaptive probability)
				for (int i = 0; i < this.Config.getRequiredParentCount(); i++) {
					parentFitness += parents[i].getFitness();
				}

				T[] offspring = this.doAdaptiveCrossover(parents, avgFitness, maxFitness, parentFitness);
				//T[] offspring = this.doCrossover(parents);
				
				T[] mutatedOffspring = this.doAdaptiveMutation(offspring, avgFitness, maxFitness, parentFitness);
				//T[] mutatedOffspring = this.doMutation(parents);
				
				startTime = System.nanoTime();
				for (int i = 0; i < mutatedOffspring.length; i++) {
					if (newGeneration.size() < this.Config.getPopulationSize()) {
						newGeneration.add(mutatedOffspring[i]);
					}
				}
			}
			
			/**
			 * calculate fitness after the new Generation is built
			 */
			populationFitness = this.getPopulationFitness(newGeneration);
			sumFitness = 0;
			maxFitness = 0;
			T fittestSolution = null;
			for (int i = 0; i < populationFitness.length; i++) {
				sumFitness += populationFitness[i];
				if (populationFitness[i] > maxFitness) {
					maxFitness = populationFitness[i];
					fittestSolution = population.get(i);
				}
			}
			avgFitness = sumFitness / population.size();
			convergenceCount = Math.abs(avgFitness - maxFitness) < 0.000001 ? convergenceCount + 1 : 0;
			boolean isConverged = convergenceCount == this.Config.getConvergenceMaximum();

			this.UpdateReport(population);

			isComplete = isConverged || evolution == this.Config.getMaximumEvolutions();
			population = newGeneration;
			evolution++;

		}
		// we're done
		long endTime = System.nanoTime();

		System.out.println(String.format("Algorithm converged at %.3f", maxFitness));
		System.out.println(String.format("Evolutions : %d", evolution));
		System.out.println(this.Config.toString());
		System.out.println(String.format("Elapsed: %d", (int) ((endTime - startTime) / 1000000000d)));
		System.out.println(String.format("Crossover : %s", this.CrossOver.toString()));
		System.out.println(String.format("Mutation : %s", this.Mutation.toString()));
		System.out.println(String.format("Select : %s", this.Select.toString()));
	}

	protected Boolean isValidConfiguration() {
		// return this.Config != null && this.Factory != null && this.CrossOver
		// != null && this.Mutation != null
		// && this.Select != null && this.Solution != null && this.Report !=
		// null;
		return true;
	}

	protected ArrayList<T> getInitialPopulation() {
		/**
		 * Initialize population.
		 *
		 * If PopulationSize = 50 and MAXIMUM_STUDENTS = 512 population
		 * = ArrayList[50][512]
		 */
		ArrayList<T> population = new ArrayList<T>();
		for (int i = 0; i < this.Config.getPopulationSize(); i++) {
			population.add(this.Factory.CreateChromosome());
		}
		System.out
			.println(String.format("Initialized population with %d candidates.", this.Config.getPopulationSize()));

		return population;
	}

	protected T[] doAdaptiveCrossover(T[] parents, double avgFitness, double maxFitness, double parentFitness) {
		// Crossover is only applied on a random basis,
		// that is, if a random number is less that 0.3 = CrossoverProbability
		// @see GeneticAlgorithmConfig.java
		T[] offspring = null;
		this.Config.setAdaptiveCrossoverProbability(avgFitness, maxFitness, parentFitness / 2);
		if (Math.random() < this.Config.getCrossoverProbability()) {

			try {
				offspring = this.CrossOver.CrossOver(parents);
			} catch (GeneticAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.CrossoverCount++;
		} else {
			offspring = parents;
		}

		return offspring;

	}

	protected T[] doAdaptiveMutation(T[] offspring, double avgFitness, double maxFitness, double parentFitness) {
		// Mutation only applied on a random basis,
		// that is, if a random number is less than 0.3 = MutationProbability
		// @see GeneticAlgorithmConfig.java
		T[] mutatedOffspring = null;
		this.Config.setAdaptiveMutationProbability(avgFitness, maxFitness, parentFitness / 2);
		if (Math.random() < this.Config.getMutationProbability()) {
			try {
				mutatedOffspring = this.Mutation.Mutate(offspring);
			} catch (GeneticAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.MutationCount++;
		} else {
			mutatedOffspring = offspring;
		}

		return mutatedOffspring;
	}
	
	protected T[] doCrossover(T[] parents) {
		// Crossover is only applied on a random basis,
		// that is, if a random number is less that 0.3 = CrossoverProbability
		// @see GeneticAlgorithmConfig.java
		T[] offspring = null;

		if (Math.random() < this.Config.getCrossoverProbability()) {

			try {
				offspring = this.CrossOver.CrossOver(parents);
			} catch (GeneticAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			offspring = parents;
		}

		return offspring;

	}
	
	protected T[] doMutation(T[] offspring) {
		// Mutation only applied on a random basis,
		// that is, if a random number is less than 0.3 = MutationProbability
		// @see GeneticAlgorithmConfig.java
		T[] mutatedOffspring = null;
		if (Math.random() < this.Config.getMutationProbability()) {
			try {
				mutatedOffspring = this.Mutation.Mutate(offspring);
			} catch (GeneticAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			mutatedOffspring = offspring;
		}

		return mutatedOffspring;
	}
	
	protected double[] getPopulationFitness(ArrayList<T> population) {
		double[] fitness = new double[this.Config.getPopulationSize()];
		for (int i = 0; i < this.Config.getPopulationSize(); i++) {
			fitness[i] = population.get(i).getFitness();
		}
		return fitness;
	}
	
	protected void UpdateReport(ArrayList<T> population) {

		double totalFitness = 0;
		double totalGroupCount = 0;
		int validSolutions = 0;
		double maxFitness = 0;
		double minFitness = 0;
		double maxValidFitness = 0;

		for (int i = 0; i < this.Config.getPopulationSize(); i++) {

			double fitness = population.get(i).getFitness();

			totalFitness += fitness;
			maxFitness = fitness > maxFitness ? fitness : maxFitness;
			minFitness = fitness < maxFitness ? fitness : minFitness;

			GroupEncodingChromosome group = (GroupEncodingChromosome) population.get(i);

			if (group.isValid()) {
				validSolutions++;
				maxValidFitness = fitness > maxValidFitness ? fitness : maxValidFitness;
			}

		}

		double averageFitness = totalFitness / population.size();
		double averageValidGroups = totalGroupCount / population.size();

		this.Report.updateReport(averageFitness, averageValidGroups, validSolutions, maxFitness, minFitness,
			maxValidFitness);

		System.out.println(String.format("Avg Fitness = %f Avg Valid Groups = %f Valid Solutions = %d Max Fitness = %f",
			averageFitness, averageValidGroups, validSolutions, maxFitness));
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

	public void setReport(IReport report) {
		Report = report;
	}
}

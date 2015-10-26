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

		// Initialize population
		ArrayList<T> population = new ArrayList<T>();
		for (int i = 0; i < this.Config.getPopulationSize(); i++) {
			population.add(this.Factory.CreateChromosome());
		}
		System.out
				.println(String.format("Initialized population with %d candidates.", this.Config.getPopulationSize()));

		boolean isComplete = false;
		int evolution = 1;
		while (!isComplete) {

			// Calculate population fitness
			double totalFitness = 0;
			double[] populationFitness = new double[this.Config.getPopulationSize()];
			for (int i = 0; i < this.Config.getPopulationSize(); i++) {
				populationFitness[i] = population.get(i).getFitness();
				totalFitness += populationFitness[i];
			}
			
			System.out.println(String.format("Generation %d has average fitness %f  Fittest solution is %f", evolution,
					totalFitness / this.Config.getPopulationSize(),
					this.Solution.getFittestSolution(population).getFitness()));

			// Build new generation
			ArrayList<T> newGeneration = new ArrayList<T>();
			int crossoverCount = 0;
			int mutationCount = 0;
			while (newGeneration.size() < this.Config.getPopulationSize()) {

				T[] parents = this.Select.GetParents(population, populationFitness,
						this.Config.getRequiredParentCount());

				T[] offspring = null;
				if (Math.random() < this.Config.getCrossoverProbability()) {
					offspring = this.CrossOver.CrossOver(parents);
					crossoverCount++;
				} else {
					offspring = parents;
				}

				T[] mutatedOffspring = null;
				if (Math.random() < this.Config.getMutationProbability()) {
					mutatedOffspring = this.Mutation.Mutate(offspring);
					mutationCount++;
				} else {
					mutatedOffspring = offspring;
				}

				for (int i = 0; i < this.Config.getRequiredParentCount(); i++) {
					if (newGeneration.size() < this.Config.getPopulationSize()) {
						newGeneration.add(mutatedOffspring[i]);
					}
				}
			}

			// Find solutions
			ArrayList<T> solutions = this.Solution.getSolutions(newGeneration);

			System.out.println(
					String.format("New generation evolved through %d crossover and %d mutations, having %d solutions",
							crossoverCount, mutationCount, solutions.size()));

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

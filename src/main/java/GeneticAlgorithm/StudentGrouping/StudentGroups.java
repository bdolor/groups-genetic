package main.java.GeneticAlgorithm.StudentGrouping;

import java.util.ArrayList;

import main.java.GeneticAlgorithm.Common.PermutationChromosome;
import main.java.GeneticAlgorithm.Interfaces.IStudentChromosome;

public class StudentGroups extends PermutationChromosome implements IStudentChromosome {

	public final static int MAXIMUM_GH_PER_GROUP = 14;
	public final static int MAXIMUM_STUDENTS = 512;
	// public final static int MAXIMUM_STUDENTS = 12;
	
	private StudentScores scores = new StudentScores();

	public StudentGroups() {
		super(StudentGroups.MAXIMUM_STUDENTS);
	}

	public StudentGroups(int[] groups) {
		super(groups);
	}

	/**
	 * Fitness is a measure of the overall heterogeneity of ALL groups, invalid
	 * groups aren't counted.
	 * 
	 * Loop must iterate through the entire 'class' of grouped students to get
	 * an accurate measure of the overall heterogeneity of all groups in that
	 * class.
	 * 
	 * @return sumGh double - the sum of all GH of only valid groups
	 */
	@Override
	public double getFitness() {

		//return (this.getDistanceFitness() + this.getGhCompleteFitness() + (2 * this.getSumGhFitness())) / 4;		
		return this.getSumGhFitness();
	}

	@Override
	public int[] getEncoding() {
		return this.getPermutationEncoding();
	}

	/**
	 * Required for the output, intended for use once the best/fittest class has
	 * been selected.
	 * 
	 * @return Array
	 */
	public String[] getMembers() {
		int dimension1 = StudentGroups.MAXIMUM_STUDENTS / 4;
		String[] members = new String[dimension1];

		// loop through bestGroup to get individual members
		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int i1 = this.getEncoding()[(i * 4)];
			int i2 = this.getEncoding()[(i * 4) + 1];
			int i3 = this.getEncoding()[(i * 4) + 2];
			int i4 = this.getEncoding()[(i * 4) + 3];

			String s1 = Integer.toString(i1);
			String s2 = Integer.toString(i2);
			String s3 = Integer.toString(i3);
			String s4 = Integer.toString(i4);

			members[i] = s1 + "," + s2 + "," + s3 + "," + s4;

		}

		return members;

	}

	/**
	 * Output the highest Euclidean distance of each group.
	 * 
	 * @return double eachGroupMaxDistance
	 */
	public double[] getEachGroupMaxDistance() {
		double[] eachGroupMaxDistance = new double[(StudentGroups.MAXIMUM_STUDENTS / 4)];
		// loop through bestGroup to get individual members
		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			// set the variable with values
			eachGroupMaxDistance[i] = this.scores.getMaxDistance(s1, s2, s3, s4);
		}

		return eachGroupMaxDistance;

	}

	/**
	 * Output each group's GH, or Goodness Heterogeneity
	 * 
	 * @return double
	 */
	public double[] getEachGroupGH() {
		double[] eachGroupGH = new double[(StudentGroups.MAXIMUM_STUDENTS / 4)];
		// loop through best group to get individual members
		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			// set the variable with values
			eachGroupGH[i] = this.scores.getGhValue(s1, s2, s3, s4);
		}

		return eachGroupGH;
	}

	
	public boolean isValid() {
		Boolean allValidGroups = true;

		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			if (this.scores.getMaxDistance(s1, s2, s3, s4) <= 2) {
				// only valid groups can contribute to the overall heterogenity
				// of all groups
				// continue on with loop to measure all valid groups
				allValidGroups = false;
			}

			double gh = this.scores.getGhValue(s1, s2, s3, s4);
			if (gh < 0.5) {
				// only valid groups can contribute to the overall heterogenity
				// of all groups
				// continue on with loop to measure all valid groups
				allValidGroups = false;
			}
		}

		return allValidGroups;
	}

	public int[] getValidGroups() {

		ArrayList<Integer> validGroups = new ArrayList<Integer>();

		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {
			Boolean islValidGroup = true;

			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			if (this.scores.getMaxDistance(s1, s2, s3, s4) <= 2) {
				// only valid groups can contribute to the overall heterogenity
				// of all groups
				// continue on with loop to measure all valid groups
				islValidGroup = false;
			}

			double gh = this.scores.getGhValue(s1, s2, s3, s4);
			if (gh < 0.5) {
				// only valid groups can contribute to the overall heterogenity
				// of all groups
				// continue on with loop to measure all valid groups
				islValidGroup = false;
			}

			if (islValidGroup) {
				validGroups.add(s1);
				validGroups.add(s2);
				validGroups.add(s3);
				validGroups.add(s4);
			}
		}

		int[] res = new int[validGroups.size()];
		for (int i = 0; i < validGroups.size(); i++) {
			res[i] = validGroups.get(i);
		}

		return res;
	}

	protected double getDistanceFitness() {
		int validCount = 0;

		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {
			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			if (this.scores.getMaxDistance(s1, s2, s3, s4) > 2) {
				validCount++;
			}
		}
		return (double) validCount / (double) StudentGroups.MAXIMUM_STUDENTS / 4;
	}

	protected double getGhCompleteFitness() {
		int validCount = 0;

		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {
			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			if (this.scores.getGhValue(s1, s2, s3, s4) >= 0.5) {
				validCount++;
			}
		}
		return (double) validCount / (double) StudentGroups.MAXIMUM_STUDENTS / 4;
	}

	protected double getSumGhFitness() {
		int sumGh = 0;

		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {
			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			if (this.scores.getGhValue(s1, s2, s3, s4) >= 0.5 && this.scores.getMaxDistance(s1, s2, s3, s4) > 2) {
				sumGh += this.scores.getGhValue(s1, s2, s3, s4);
			}
		}
		return (double) sumGh;
	}

	@Override
	public ArrayList<int[]> getStudentGroups() {
		ArrayList<int[]> groups = new ArrayList<int[]>();
		
		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {
			int[] group = new int[4];
			group[0] = this.getEncoding()[(i * 4)];
			group[1] = this.getEncoding()[(i * 4) + 1];
			group[2] = this.getEncoding()[(i * 4) + 2];
			group[3] = this.getEncoding()[(i * 4) + 3];
			
			groups.add(group);
		}
		
		return groups;		
	}

	// public void displayResults(ArrayList population, int index, int
	// crossover, int mutation, int convergence, int evolution, int validCount)
	// {
	// // need to output member IDs of the groups (in winner class)
	// String[] memberIDs = this.Solution.getMembersOfGroup(population, index);
	//
	// // highest Euclidean distance of each group (in winner class)
	// double[] eachGroupED = this.Solution.getEachGroupDistance(population,
	// index);
	//
	// // need to output GH of each group (in winner class)
	// double[] eachGroupGH = this.Solution.getEachGroupGH(population, index);
	//
	// // sum of all GH values
	// double sumFitness = 0;
	// for (int i = 0; i < eachGroupGH.length; i++) {
	// sumFitness += eachGroupGH[i];
	// }
	// // track the actual fittestSolution
	// if (sumFitness > this.fittestSolution) {
	// this.fittestSolution = sumFitness;
	// }
	//
	// System.out.println(sumFitness);
	// // loop
	// //System.out.println(String.format(
	// //"Generation %d: {convergence: %d }, {sum fitness = %f}, {crossover =
	// %d}, {mutations = %d}, {valid = %d}",
	// //evolution, convergence, sumFitness, crossover, mutation, validCount));
	//
	// // requirement outputs
	//// String GH = "";
	//// String ED = "";
	//// String ID = "";
	//// for (int i = 0; i < memberIDs.length; i++) {
	//// GH += "(" + (i + 1) + ")[" + eachGroupGH[i] + "], ";
	//// ED += "(" + (i + 1) + ")[" + eachGroupED[i] + "], ";
	//// ID += "(" + (i + 1) + ")[" + memberIDs[i] + "], ";
	//// };
	//// System.out.println("GH: " + GH);
	//// System.out.println("ED: " + ED);
	//// System.out.println("ID: " + ID);
	//// System.out.println("");
	// }

}

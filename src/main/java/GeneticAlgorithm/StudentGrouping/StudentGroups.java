package main.java.GeneticAlgorithm.StudentGrouping;

import main.java.GeneticAlgorithm.Common.PermutationChromosome;

public class StudentGroups extends PermutationChromosome {

	public final static int MAXIMUM_STUDENTS = 512;
	//public final static int MAXIMUM_STUDENTS = 12;

	public StudentGroups() {
		super(StudentGroups.MAXIMUM_STUDENTS);
	}

	public StudentGroups(int[] groups) {
		super(groups);
	}
	
	/**
	 * Fitness is a measure of the overall heterogeneity of ALL groups, 
	 * invalid groups aren't counted. 
	 * 
	 * Loop must iterate through the entire 'class' of grouped students to get an
	 * accurate measure of the overall heterogeneity of all groups in that class.
	 * 
	 * @return double sumGh - the sum of all GH of only valid groups 
	 */
	@Override
	public double getFitness() {

		double sumGh = 0;

		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			if (StudentScores.getMaxDistance(s1, s2, s3, s4) <= 2) {
				// only valid groups can contribute to the overall heterogenity of all groups
				// continue on with loop to measure all valid groups 
				continue; 
			}

			double gh = StudentScores.getGhValue(s1, s2, s3, s4);
			if (gh < 0.5) {
				// only valid groups can contribute to the overall heterogenity of all groups
				// continue on with loop to measure all valid groups
				continue;
			}			

			sumGh += gh;
		}
		
		return sumGh;
	}

	@Override
	public int[] getEncoding() {
		return this.getPermutationEncoding();
	}
}

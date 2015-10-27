package main.java.GeneticAlgorithm.StudentGrouping;

import main.java.GeneticAlgorithm.Common.PermutationChromosome;

public class StudentGroups extends PermutationChromosome {

	//public final static int MAXIMUM_STUDENTS = 512;
	public final static int MAXIMUM_STUDENTS = 12;

	public StudentGroups() {
		super(StudentGroups.MAXIMUM_STUDENTS);
	}

	public StudentGroups(int[] groups) {
		super(groups);
	}

	@Override
	public double getFitness() {

		Boolean isValidGroup = true;
		double sumGh = 0;

		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			if (StudentScores.getMaxDistance(s1, s2, s3, s4) <= 2) {
				isValidGroup = false;
				break;
			}

			double gh = StudentScores.getGhValue(s1, s2, s3, s4);
			if (gh < 0.5) {
				isValidGroup = false;
				break;
			}			

			sumGh += gh;
		}
		
		return isValidGroup ? sumGh : 0;
	}

	@Override
	public int[] getEncoding() {
		return this.getPermutationEncoding();
	}
}

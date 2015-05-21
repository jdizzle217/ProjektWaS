package ch.bfh.wstat.project.legacy;

/**
 * Statistics - implement methods to get relative frequencies, middle gain, ...
 */
public class Statistics {

	public static int CooperateCooperate = 0;
	public static int DefectCooperate = 0;
	public static int CooperateDefect = 0;
	public static int DefectDefect = 0;

//*** START OF MODIFIED CODE FRAGMENT ***
	public static float Gain = 0.f; //variable for total gain (initialised to zero)

	public static double getNumberOfRounds() { //calculate the number of rounds by adding the frequencies of the different events
		return CooperateCooperate + DefectCooperate + CooperateDefect + DefectDefect;
	}

	public static double getRelativeFrequencyCooperateCooperate() { //calculate the relative frequencies by dividing the absolute frequencies by the total number of rounds
		return (double)CooperateCooperate / getNumberOfRounds();
	}

	public static double getRelativeFrequencyDefectCooperate() {
		return (double)DefectCooperate / getNumberOfRounds();
	}

	public static double getRelativeFrequencyCooperateDefect() {
		return (double)CooperateDefect / getNumberOfRounds();
	}

	public static double getRelativeFrequencyDefectDefect() {
		return (double)DefectDefect / getNumberOfRounds();
	}

	public static double getMiddleGain() { //calculate the middle gain by dividing the total gain by the number of played rounds
		return (double)Gain / getNumberOfRounds();
	}
//*** END OF MODIFIED CODE FRAGMENT ***
}

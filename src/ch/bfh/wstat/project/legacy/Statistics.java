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
	public static float Gain = 0.f;
	public static int Rounds = 0;

	public static double getRelativeFrequencyCooperateCooperate() {
		return (double)CooperateCooperate / Rounds;
	}

	public static double getRelativeFrequencyDefectCooperate() {
		return (double)DefectCooperate / Rounds;
	}

	public static double getRelativeFrequencyCooperateDefect() {
		return (double)CooperateDefect / Rounds;
	}

	public static double getRelativeFrequencyDefectDefect() {
		return (double)DefectDefect / Rounds;
	}

	public static double getMiddleGain() {
		return (double)Gain / Rounds;
	}
//*** END OF MODIFIED CODE FRAGMENT ***
}

package ch.bfh.wstat.project.legacy;

/**
 * Statistics - implement methods to get relative frequencies, middle gain, ...
 */
public class Statistics {

	public static int CooperateCooperate = 0;
	public static int DeceiveCooperate = 0;
	public static int CooperateDeceive = 0;
	public static int DeceiveDeceive = 0;

//*** START OF MODIFIED CODE FRAGMENT ***
	public static float Gain = 0.f;
	public static int Rounds = 0;

	public static double getRelativeFrequencyCooperateCooperate() {
		return (double)CooperateCooperate / Rounds;
	}

	public static double getRelativeFrequencyDeceiveCooperate() {
		return (double)DeceiveCooperate / Rounds;
	}

	public static double getRelativeFrequencyCooperateDeceive() {
		return (double)CooperateDeceive / Rounds;
	}

	public static double getRelativeFrequencyDeceiveDeceive() {
		return (double)DeceiveDeceive / Rounds;
	}

	public static double getMiddleGain() {
		return (double)Gain / Rounds;
	}
//*** END OF MODIFIED CODE FRAGMENT ***
}

package ml.paulobatista.simitrieve.feedback;

public class FeedBackManager {
	public static void processGetStarted(String typeOfProcess) {
		System.out.println("A " + typeOfProcess + " began");
	}

	
	public static void allProccessesHasBeenFinished() {
		System.out.println("Finished.");
	}
	
	public static void currentProcces(String process) {
		System.out.println("Current process: " + process);
	}
	
}

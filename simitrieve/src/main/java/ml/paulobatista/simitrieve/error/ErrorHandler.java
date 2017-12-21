package ml.paulobatista.simitrieve.error;



public class ErrorHandler {

	public static void fileNotExist() {

		System.out.println("Unexpected Error: Project file doesn't exist");

		System.exit(1);
	}
}

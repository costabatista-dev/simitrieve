package ml.paulobatista.simitrieve.error;



public class ErrorHandler {

	public static void fileNotExist() {

		System.out.println("Unexpected Error: Project file doesn't exist");

		System.exit(Error.FILE_NOT_EXIST.getCode());
	}
	
	public static void unexpectedProjectProgrammingLanguage() {
		System.out.println("Unexpected Error: Project programming language haven't support");
		System.exit(Error.PROJECT_PROGRAMMING_LANGUAGE_NOT_SUPPORTED.getCode());
	}
	
	public static void patternIsNULL() {
		System.out.println("Unexpected Error: Project pattern language file is NULL");
		System.exit(Error.PROJECT_LANGUAGE_PATTERN_IS_NULL.getCode());
	}
	
	public static void projectIsEmpty() {
		System.out.println("Unexpected Error: For some reason project's empty, check source code root folder\n"
				+ "It's possible that the programming language's wrong to this project");
		System.exit(Error.PROJECT_IS_EMPTY.getCode());
	}
}

package ml.paulobatista.simitrieve.error;

public class ErrorHandler {

	public static void fileNotExist() {

		System.out.println("Unexpected Error: Project file doesn't exist");

		System.exit(Error.FILE_NOT_EXIST_ERROR.getCode());
	}

	public static void unexpectedProjectProgrammingLanguage() {
		System.out.println("Unexpected Error: Project programming language haven't support");
		System.exit(Error.PROJECT_PROGRAMMING_LANGUAGE_NOT_SUPPORTED_ERROR.getCode());
	}

	public static void patternIsNULL() {
		System.out.println("Unexpected Error: Project pattern language file is NULL");
		System.exit(Error.PROJECT_LANGUAGE_PATTERN_IS_NULL_ERROR.getCode());
	}

	public static void projectIsEmpty() {
		System.out.println("Unexpected Error: For some reason project's empty, check source code root folder\n"
				+ "It's possible that the programming language's wrong to this project");
		System.exit(Error.PROJECT_IS_EMPTY_ERROR.getCode());
	}

	public static void errorInFileReader(String fileName, String line) {
		System.out.println(
				"Unexpected Error: Error while reading text from file - " + fileName + "in line " + line + " of it");
		System.exit(Error.READING_FILE_ERROR.getCode());
	}

	public static void UnexpectedFileExtension() {
		System.out.println("Unexpected Error: A file with a unknown extension is inside the file list");
		System.exit(Error.UNKNOWN_FILE_EXTENSION_ERROR.getCode());
	}

	public static void projectNameIsWrong() {
		System.out.println("Unexpected Error: The project name is wrong");
		System.exit(Error.PROJECT_NAME_IS_WRONG_ERROR.getCode());
	}

	public static void directoryCannotBeCreated(String directory) {
		System.out.println("Unexpected Error: The directory" + directory + "cannot be created");
		System.exit(Error.CANNOT_CREATE_DIRECTORY_ERROR.getCode());
	}

	public static void projectVersionIsNULL() {
		System.out.println("UNexpected Error: project version is NULL");
		System.exit(Error.PROJECT_VERSION_IS_NULL.getCode());
	}

	public static void tokenListDoNotContainsToken(String value) {
		System.out.println("Unexpected Error: project do not contains value - " + value);
		System.exit(Error.TOKENLIST_DO_NOT_CONTAINS_VALUE_ERROR.getCode());
	}
	
	public static void errorInDictionaryReading(String message) {
		System.out.println("Unexpected Error: " + message);
		System.exit(Error.DICTIONARY_READING_ERROR.getCode());
	}
	
	public static void errorInCMD(String parameter) {
		System.out.println("Error in command line parameter - Parameter: " + parameter);
		System.exit(Error.COMMAND_LINE_PARAMETER_ERROR.getCode());
	}
	
	public static void removeCommentsError() {
		System.out.println("Unexpected error: Error while is removing comment from source code" );
		System.exit(Error.REMOVE_COMMENTS_ERROR.getCode());
	}
	
	
}

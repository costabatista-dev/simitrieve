package ml.paulobatista.simitrieve.error;

public enum Error {
	FILE_NOT_EXIST_ERROR(1),
	PROJECT_PROGRAMMING_LANGUAGE_NOT_SUPPORTED_ERROR(2),
	PROJECT_LANGUAGE_PATTERN_IS_NULL_ERROR(3),
	PROJECT_IS_EMPTY_ERROR(4),
	READING_FILE_ERROR(5),
	UNKNOWN_FILE_EXTENSION_ERROR(6),
	PROJECT_NAME_IS_WRONG_ERROR(7);
	
	
	private int code;
	
	Error(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}

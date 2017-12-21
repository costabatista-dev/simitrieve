package ml.paulobatista.simitrieve.error;

public enum Error {
	FILE_NOT_EXIST(1),
	PROJECT_PROGRAMMING_LANGUAGE_NOT_SUPPORTED(2),
	PROJECT_LANGUAGE_PATTERN_IS_NULL(3),
	PROJECT_IS_EMPTY(4);
	
	private int code;
	
	Error(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}

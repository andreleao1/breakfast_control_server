package breakfastcontrolserver.domain.exceptions;

public class ErrorSavingEntityException extends RuntimeException {

	private static final long serialVersionUID = -2997285306392440280L;

	private static final String MESSAGE = "Error saving entity of type %s";

	public ErrorSavingEntityException(String entityType) {
		super(String.format(MESSAGE, entityType));
	}
}

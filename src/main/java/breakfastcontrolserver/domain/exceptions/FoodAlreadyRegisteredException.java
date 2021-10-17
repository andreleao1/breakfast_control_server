package breakfastcontrolserver.domain.exceptions;

public class FoodAlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = -3764699698650878785L;

	private static final String MESSAGE = "Another employee has already registred the food %s";

	public FoodAlreadyRegisteredException(String foodName) {
		super(String.format(MESSAGE, foodName));
	}
}

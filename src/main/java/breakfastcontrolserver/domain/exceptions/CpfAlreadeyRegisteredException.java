package breakfastcontrolserver.domain.exceptions;

public class CpfAlreadeyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = -1027251554016239957L;

	private static final String MESSAGE = "CPF number %s already registred. Please, check that the numbers entered are correct and try again.";

	public CpfAlreadeyRegisteredException(String cpf) {
		super(String.format(MESSAGE, cpf));
	}
}

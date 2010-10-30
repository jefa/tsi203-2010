package partuzabook.servicioDatos.exception;

public class UserNotFoundException extends IllegalArgumentException {

	public UserNotFoundException(String string) {
		super(string);
	}

	public UserNotFoundException() {
	}

	private static final long serialVersionUID = 1L;

}

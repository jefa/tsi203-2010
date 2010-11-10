package partuzabook.servicioDatos.exception;

public class MessageTooLongException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public MessageTooLongException(String string) {
		super(string);
	}
}

package partuzabook.servicioDatos.exception;

public class NotificationNotFoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	public NotificationNotFoundException(String string) {
		super(string);
	}

	public NotificationNotFoundException() {
	}

}

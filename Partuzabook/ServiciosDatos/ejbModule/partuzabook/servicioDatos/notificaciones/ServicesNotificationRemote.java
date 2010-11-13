package partuzabook.servicioDatos.notificaciones;

import java.util.List;

import javax.ejb.Remote;

import partuzabook.datatypes.DatatypeNotification;
import partuzabook.servicioDatos.exception.MessageTooLongException;
import partuzabook.servicioDatos.exception.NotificationNotFoundException;
import partuzabook.servicioDatos.exception.UserNotFoundException;

@Remote
public interface ServicesNotificationRemote {

	/**
     * Returns a list of the latest X update notifications for the user,
     * including read and unread 
     * @param username		- Id for the user
     * @return				- List of notifications for the user
     */
    public List<DatatypeNotification> getUpdateNotifications(String username);
    
    /**
     * Returns a list of unread notifications for the user
     * @param username		- Id for the user
     * @return				- List of notifications for the user
     */
    public List<DatatypeNotification> getUpdateNotificationsUnread(String username);

	/**
	 * Returns a list of niotificactions, with all the notifications received for the user
	 * @param username		- username of the user
	 * @return 				- The notifications received
	 * */
	public List<DatatypeNotification> getUpdateNotificationsReceived(
			String username);

	/**
	 * Returns a list of the notifications sent, for the user with username
	 * @param username		- username of the user
	 * @return 				- The notifications sent
	 * */
	public List<DatatypeNotification> getUpdateNotificationsSent(String username);
	
	/**
	 * Creates a new notification
	 * @param fromUser		- username of the sender
	 * @param toUser		- username of the reciver
	 * @param type			- The type of notificaction {Notification.MAIL_NOTIF_TYPE, Notification.OTHER_NOTIF_TYPE}
	 * @param message		- The body of the message, not longer than 5000 chars
	 * @param subject		- The subject of the message, no longer than 200 chars
	 * */
	public DatatypeNotification createNotification(String fromUser, String toUser, Integer type, String message, String subject) throws UserNotFoundException, MessageTooLongException;

	/**
	 * Sets a notification's 'read' column true or false
	 * @param notId			- id of the notification to update
	 * @param read			- Value for the 'read' column
	 * */
	public void setNotificationRead(int notId, boolean read) throws NotificationNotFoundException;
}

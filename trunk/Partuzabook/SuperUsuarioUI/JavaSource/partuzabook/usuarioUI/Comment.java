package partuzabook.usuarioUI;

import java.util.Date;

public class Comment {

    private Long id;
	private Image image;
	private User author;
	private Date date;
	private String message;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
	public boolean equals(Object obj) {
        if (this == obj) {
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

        final Comment comment = (Comment) obj;

        return (id == null ? comment.getId() == null : id.equals(comment.getId()))
				&& (author == null ? comment.getAuthor() == null : author.equals(comment.getAuthor()))
				&& image.equals(comment.getImage())
				&& message.equals(comment.getMessage());
    }

    @Override
	public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + image.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }
}

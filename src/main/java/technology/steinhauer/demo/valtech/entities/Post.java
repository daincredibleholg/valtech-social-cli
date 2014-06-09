package technology.steinhauer.demo.valtech.entities;

import java.util.Date;

/**
 * Represents one post, made by a specific user.
 *
 * The needed Hibernate mappings can be found in
 * resources/technology/steinhauer/demo/valtech/entities/Follower.hbm.xml.
 */
public class Post {

    private int id;
    private String message;
    private String username;
    private Date date;

    /**
     * Default constructor, needed for hibernate mapping.
     */
    public Post() { }

    public Post(String username, String message, Date date) {
        this.username = username;
        this.message = message;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!date.equals(post.date)) return false;
        if (!message.equals(post.message)) return false;
        if (!username.equals(post.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}

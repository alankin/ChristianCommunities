package ccomunities.alashka.com.ccommunities_dev.Model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

/**
 * Created by ALANKIN on 10/10/16.
 */
public class Comment extends SugarRecord {
    String content;
    String date;
    User user;
    Publication publication;
    String created_at;
    String updated_at;
    @Ignore
    Long user_id;
    @Ignore
    Long publication_id;

    public Comment() {
    }

    public Comment(String content, Date date, User user, Publication publication, String created_at, String updated_at) {
    }

    public Comment(Long publication_id, String created_at, String updated_at, Long user_id, String date, String content) {
        this.publication_id = publication_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user_id = user_id;
        this.date = date;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updated_at = updatedAt;
    }
}

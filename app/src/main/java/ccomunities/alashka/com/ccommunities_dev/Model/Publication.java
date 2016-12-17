package ccomunities.alashka.com.ccommunities_dev.Model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;
import java.util.List;

/**
 * Created by ALANKIN on 10/10/16.
 */
public class Publication extends SugarRecord {

    String title;
    String description;
    String date;
    String place;
    String created_at;
    String updated_at;
    User user;
    @Ignore
    Long user_id;

    public Publication() {
    }

    public Publication(String title, String description, Date date, String place, User user, String created_at, String updated_at) {
    }

    public Publication(String title, String description, String date, String place, Long user_id, String created_at, String updated_at) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.place = place;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Publication(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<Rate> getRates() {
        return Rate.find(Rate.class, "publication = ?", String.valueOf(this.getId()));
    }

    public List<Comment> getComment() {
        return Comment.find(Comment.class, "publication = ?", String.valueOf(this.getId()));
    }
}

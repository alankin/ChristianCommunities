package ccomunities.alashka.com.ccommunities_dev.Model;

import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by ALANKIN on 10/10/16.
 */
public class Publication extends SugarRecord {

    String title;
    String description;
    Date date;
    String place;
    User user;

    public Publication() {
    }

    public Publication(String title, String description, Date date, String place, User user) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public List<Rate> getRates(){
        return Rate.find(Rate.class, "publication = ?", String.valueOf(this.getId()));
    }

    public List<Comment> getComment(){
        return Comment.find(Comment.class, "publication = ?", String.valueOf(this.getId()));
    }
}

package ccomunities.alashka.com.ccommunities_dev.Model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ALANKIN on 10/10/16.
 */
public class Rate extends SugarRecord {

    Date date;
    User user;
    Publication publication;

    public Rate() {
    }

    public Rate(Date date, User user, Publication publication) {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

}

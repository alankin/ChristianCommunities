package ccomunities.alashka.com.ccommunities_dev.Model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by ALANKIN on 15/10/16.
 */
public class UserAchievement extends SugarRecord {

    Date date;
    Achievement achievement;
    User user;

    public UserAchievement() {
    }

    public UserAchievement(Date date, Achievement achievement, User user) {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

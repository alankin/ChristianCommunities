package ccomunities.alashka.com.ccommunities_dev.Model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by ALANKIN on 10/10/16.
 */
public class Community extends SugarRecord {

    String name;
    String description;
    String responsibleId;
    String location;

    public Community() {
    }

    public Community(String name, String description, String responsibleId, String location) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(String responsibleId) {
        this.responsibleId = responsibleId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<User> getUsers(){
        return User.find(User.class, "user = ?", String.valueOf(this.getId()));
    }
}

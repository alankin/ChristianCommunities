package ccomunities.alashka.com.ccommunities_dev.Model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by ALANKIN on 10/10/16.
 */
public class Achievement extends SugarRecord {

    String name;
    String description;
    String typeAchievement;
    String created_at;
    String updated_at;
    Integer requiredQuantity;

    public Achievement() {
    }

    public Achievement(String name, String description, String type, Integer requiredQuantity, String created_at, String updated_at) {
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

    public String getTypeAchievement() {
        return typeAchievement;
    }

    public void setTypeAchievement(String type) {
        this.typeAchievement = type;
    }

    public Integer getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Integer requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
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

    public List<UserAchievement> getUserAchievements(){
        return UserAchievement.find(UserAchievement.class, "achievement = ?", String.valueOf(this.getId()));
    }
}

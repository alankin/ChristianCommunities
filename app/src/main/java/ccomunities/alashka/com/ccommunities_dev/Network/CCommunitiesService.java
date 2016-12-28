package ccomunities.alashka.com.ccommunities_dev.Network;

import java.util.List;
import java.util.Map;

import ccomunities.alashka.com.ccommunities_dev.Model.Comment;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.Model.UserAchievement;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface CCommunitiesService {
    @Headers({
            "Content-Type: application/vnd.api+json",
            "Accept: application/vnd.api+json"
    })
    @GET("/login")
    Call<User> login(
            @QueryMap Map<String, String> options
    );


    /*@GET("users?{user}&?{pass}")
    Call<User> login(@Path("user") String user, @Path("pass") String pass);*/

    @GET("/publications")
    Call<List<Publication>> getPublications(@Query("user_id") long user_id);

    @GET("/publications")
    Call<List<Publication>> getAllPublications();

    @POST("/publications")
    Call<Publication> createPublication(@Body Publication publication);

    @POST("/comments")
    Call<Comment> createComment(@Body Comment comment);

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") long user_id);

    @GET("/users/{user}/achievements")
    Call<List<UserAchievement>> getUserAchievements(@Path("user") long user_id);

    @GET("/publications/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") long publication_id);

    /*@POST("posts")
    Call<Post> post(@Body Post post, @Query("user_id") int user_id);*/
}
package ccomunities.alashka.com.ccommunities_dev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ccomunities.alashka.com.ccommunities_dev.Adapter.CommentAdapter;
import ccomunities.alashka.com.ccommunities_dev.Model.Comment;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Network.CommentAsyncTask;
import ccomunities.alashka.com.ccommunities_dev.Network.NewCommentAsyncTask;
import ccomunities.alashka.com.ccommunities_dev.Network.NewPublicationAsyncTask;

public class CommentActivity extends AppCompatActivity {

    private ListView listView;
    private CommentAdapter commentAdapter;
    private Long publicationId;
    private Toolbar toolbar;

    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        publicationId = getIntent().getLongExtra("publicationId", -1);
        commentAdapter = new CommentAdapter(this);
        listView = (ListView) findViewById(R.id.list_view_comment);
        listView.setAdapter(commentAdapter);
        commentText = (EditText) findViewById(R.id.messageEditText);


        /*Async task*/
        CommentAsyncTask task = new CommentAsyncTask(this);
        task.execute(publicationId);

        initToolbar();
    }

    public CommentAdapter getCommentAdapter() {
        return commentAdapter;
    }

    public void setAdapter(CommentAdapter adapter) {
        this.commentAdapter = adapter;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_list_comment);
        toolbar.setTitle(R.string.edit_text_comment);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.toolbar_back);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }

        );
    }

    public void saveComment(View view) {
        String content = commentText.getText().toString();

        //Today's date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(getString(R.string.date_format));
        String today = df.format(c.getTime());

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        Long userId = sharedPreferences.getLong("user_id", -1);

        if (userId != -1 && publicationId != -1) {

            Comment comment = new Comment(publicationId, today, today, userId, today, content);

            NewCommentAsyncTask task = new NewCommentAsyncTask(this);
            task.execute(comment);

            finish();
        } else {
            Toast.makeText(this, R.string.comment_error, Toast.LENGTH_LONG).show();
        }
    }
}

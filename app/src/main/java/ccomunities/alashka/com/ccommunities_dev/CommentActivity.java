package ccomunities.alashka.com.ccommunities_dev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ccomunities.alashka.com.ccommunities_dev.Adapter.CommentAdapter;
import ccomunities.alashka.com.ccommunities_dev.Model.Comment;
import ccomunities.alashka.com.ccommunities_dev.Network.CommentAsyncTask;

public class CommentActivity extends AppCompatActivity {

    private ListView listView;
    private CommentAdapter commentAdapter;
    private Long publicationId;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        publicationId = getIntent().getLongExtra("publicationId", -1);
        commentAdapter = new CommentAdapter(this);
        listView = (ListView) findViewById(R.id.list_view_comment);
        listView.setAdapter(commentAdapter);


        /*Async task*/
        CommentAsyncTask task = new CommentAsyncTask(this);
        task.execute(publicationId);

        initToolbar();
    }

    public  CommentAdapter getCommentAdapter(){
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
}

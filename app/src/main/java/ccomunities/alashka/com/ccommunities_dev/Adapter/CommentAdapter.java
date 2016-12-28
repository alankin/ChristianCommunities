package ccomunities.alashka.com.ccommunities_dev.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

import java.util.ArrayList;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

import ccomunities.alashka.com.ccommunities_dev.Model.Comment;
import ccomunities.alashka.com.ccommunities_dev.R;

/**
 * Created by nicaela on 11/12/16.
 */

public class CommentAdapter  extends ArrayAdapter<Comment>{

    private final String EMPTY_STRING = "";

    public CommentAdapter(Context context){
        super(context, R.layout.item_comment);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Comment comment = getItem(position);
        View currentView;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            currentView = inflater.inflate(R.layout.item_comment, parent, false);
        }else {
            currentView = convertView;
        }

        TextView nameUser = (TextView) currentView.findViewById(R.id.name_user_comment);
        TextView description = (TextView) currentView.findViewById(R.id.description_comment);
        TextView date = (TextView) currentView.findViewById(R.id.date_comment);
        CircleImageView profileImageView = (CircleImageView) currentView.findViewById(R.id.image_user_comment);

        /*nameUser.setText(comment != null ? comment.getUser().getName() + " " : EMPTY_STRING );
        description.setText(comment != null ? comment.getContent() + " " : EMPTY_STRING );
        date.setText(comment != null ? comment.getDate() + " " : EMPTY_STRING);*/

        nameUser.setText(comment.getUser().getName()); // may can produce null pointerException
        description.setText(comment.getContent());
        date.setText(comment.getDate().toString());

        Glide.with(getContext()).load(comment.getUser().getPathPhoto()).into(profileImageView);

        return  currentView;
    }
}

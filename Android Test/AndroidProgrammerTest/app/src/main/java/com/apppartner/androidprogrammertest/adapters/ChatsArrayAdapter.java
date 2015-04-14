package com.apppartner.androidprogrammertest.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.models.ChatData;
import com.apppartner.androidprogrammertest.models.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */

// Modified the model to fetch image from URL using a third part picasso library.
public class ChatsArrayAdapter extends ArrayAdapter<ChatData>
{
    private Bitmap bitmap;
    public ChatsArrayAdapter(Context context, List<ChatData> objects)
    {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChatCell chatCell = new ChatCell();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.cell_chat, parent, false);

        chatCell.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
        chatCell.messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        chatCell.mImageView = (ImageView)convertView.findViewById(R.id.imageView);
        ChatData chatData = getItem(position);

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Jelloween - Machinato.ttf");
        chatCell.usernameTextView.setTypeface(font);
        chatCell.usernameTextView.setText(chatData.username);

        font = Typeface.createFromAsset(getContext().getAssets(), "Jelloween - Machinato Light.ttf");
        chatCell.messageTextView.setTypeface(font);
        chatCell.messageTextView.setText(chatData.message);
        //we can fetch the images simultaneously with a single line of code instead of making our own threads.
        Picasso.with(getContext()).load(chatData.avatarURL.toString()).transform(new CircleTransform()).into(chatCell.mImageView);
        return convertView;
    }

    private static class ChatCell
    {
        TextView usernameTextView;
        TextView messageTextView;
        ImageView mImageView;

    }
}

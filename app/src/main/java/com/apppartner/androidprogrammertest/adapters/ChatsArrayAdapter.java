package com.apppartner.androidprogrammertest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.models.ChatData;
import com.apppartner.androidprogrammertest.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatsArrayAdapter extends ArrayAdapter<ChatData> {
    public ChatsArrayAdapter(Context context, List<ChatData> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ChatCell chatCell = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cell_chat, parent, false);
            chatCell = new ChatCell();
            chatCell.init(convertView);
            convertView.setTag(chatCell);
        } else {
            chatCell = (ChatCell) convertView.getTag();
        }
        ChatData chatData = getItem(position);

        chatCell.usernameTextView.setText(chatData.username);
        chatCell.messageTextView.setText(chatData.message);
        ImageLoaderUtil.displayImage(getContext(), chatData.avatarURL, chatCell.imageView);
        return convertView;
    }

    private static class ChatCell {
        TextView usernameTextView;
        TextView messageTextView;
        ImageView imageView;

        public void init(View convertView) {
            this.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
            this.messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
            this.imageView = (ImageView) convertView.findViewById(R.id.user_image);
        }
    }
}

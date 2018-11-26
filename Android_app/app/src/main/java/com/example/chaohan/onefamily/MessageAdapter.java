package com.example.chaohan.onefamily;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    Context mContext;
    private Chatting curChat;
    private ArrayList<String> mChat;

    public MessageAdapter(Context mContext, Chatting curChat) {
        this.mContext = mContext;
        this.curChat = curChat;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == 0) {
            View view = LayoutInflater.from(mContext).inflate
                    (R.layout.chat_box_s, viewGroup, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate
                    (R.layout.chat_box_f, viewGroup, false);
            return new MessageAdapter.ViewHolder(view);
        }


        //        View view = LayoutInflater.from(mContext).inflate()
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder viewHolder, int i) {
        String curMsg = curChat.getAllMessages().get(i);

        viewHolder.showMsg.setText(curMsg);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        String curMsg = curChat.getAllMessages().get(position);
        if (curChat.getMyMessages().contains(curMsg)) {
            return 0;
        }
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView showMsg;

        public ViewHolder(View itemView) {
            super(itemView);

            showMsg = itemView.findViewById(R.id.chatText);
        }
    }
}

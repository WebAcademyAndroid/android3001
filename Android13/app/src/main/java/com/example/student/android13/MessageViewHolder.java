package com.example.student.android13;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextViewMessage;
    public TextView mTextViewTime;

    public MessageViewHolder(View itemView) {
        super(itemView);

        mTextViewMessage = itemView.findViewById(android.R.id.text1);
        mTextViewTime = itemView.findViewById(android.R.id.text2);
    }
}

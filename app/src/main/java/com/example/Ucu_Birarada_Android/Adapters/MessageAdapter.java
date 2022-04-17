package com.example.Ucu_Birarada_Android.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Ucu_Birarada_Android.Models.ChatMessage;
import com.example.Ucu_Birarada_Android.R;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.Objects;

public class MessageAdapter extends RecyclerView.Adapter {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    int ITEM_SEND = 1;
    int ITEM_RECEIVED = 2;
    private Context mContext;
    private ArrayList<ChatMessage> mChats;



    public MessageAdapter(Context mContext, ArrayList<ChatMessage> mChats){
        this.mContext = mContext;
        this.mChats = mChats;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_SEND){ //sender
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_container_sent_message,parent,false);
            return new SenderViewHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_container_recieved_message,parent,false);
            return new ReceiverViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage chatMessage = mChats.get(position);
        if (holder.getClass() == SenderViewHolder.class){
            SenderViewHolder senderViewHolder = (SenderViewHolder) holder;
            senderViewHolder.show_message.setText(chatMessage.getMessage());
        }else{
            ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) holder;
            receiverViewHolder.show_message.setText(chatMessage.getMessage());
        }


    }

    @Override
    public int getItemCount() {

        return mChats.size();
    }


    public class SenderViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.showMessage);//herhalde emin degilim
        }

    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.showMessage);
        }

    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = mChats.get(position);
        if (Objects.equals(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail(), message.getSenderEmail())) {
            return ITEM_SEND;
        }
        else {
            return ITEM_RECEIVED;
        }
    }
}


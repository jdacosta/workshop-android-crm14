package fr.gobelins.crm14.workshop_android_crm14.message.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.gobelins.crm14.workshop_android_crm14.R;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    private List<String> mDatas;

    public InboxAdapter(ArrayList<String> datas) {
        mDatas = datas;
    }

    @Override
    public InboxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inbox, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InboxAdapter.ViewHolder holder, int position) {
        holder.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.messageInboxNameText);
        }

        public void setText(String text) {
            this.mTextView.setText(text);
        }
    }
}

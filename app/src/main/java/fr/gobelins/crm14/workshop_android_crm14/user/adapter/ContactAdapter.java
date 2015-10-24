package fr.gobelins.crm14.workshop_android_crm14.user.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private static final String TAG = "ContactAdapter";
    private List<User> mData;

    public ContactAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        holder.updateData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(User user) {
        if (!mData.contains(user)) {
            mData.add(user);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.userContactNameText);
        }

        public void updateData(User user) {
            this.mTextView.setText(user.getUsername());
        }
    }
}

package fr.gobelins.crm14.workshop_android_crm14.user.adapter;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.user.events.OpenDiscussionEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private static final String TAG = "ContactAdapter";
    private static ArrayList<Parcelable> users;


    public ContactAdapter() {
        users = new ArrayList<>();
    }

    public void setUsers(ArrayList<Parcelable> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public ArrayList<Parcelable> getUsers() {
        return users;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        holder.updateData((User) users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addItem(User user) {
        if (!users.contains(user)) {
            users.add(user);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.userContactNameText);

            itemView.setOnClickListener(this);

        }

        public void updateData(User user) {
            this.mTextView.setText(user.getUsername());
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            User user = (User) users.get(position);
            Log.d(TAG, user.getUsername());
            BusProvider.getInstance()
                    .post(new OpenDiscussionEvent(user));
        }
    }
}

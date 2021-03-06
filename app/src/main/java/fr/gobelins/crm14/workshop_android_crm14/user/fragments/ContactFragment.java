package fr.gobelins.crm14.workshop_android_crm14.user.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;
import fr.gobelins.crm14.workshop_android_crm14.user.adapter.ContactAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ContactFragment extends Fragment {

    private static final String TAG = "ContactFragment";
    private static final String USERS_LIST = "usersList";
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.contactRecyclerView);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ContactAdapter();

        // Doesn't work :(
        if(savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState not null");
            mAdapter.setUsers(savedInstanceState.getParcelableArrayList(USERS_LIST));
        } else {
            Log.d(TAG, "savedInstanceState null");
        }

        mRecyclerView.setAdapter(mAdapter);

        ButterKnife.bind(this, view);
        BusProvider.getInstance().register(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
        BusProvider.getInstance().unregister(this);
        mListener = null;
        mRecyclerView = null;
        mAdapter = null;
        mLayoutManager = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS_LIST, mAdapter.getUsers());
    }

    @OnClick(R.id.userContactAddFab)
    public void onAddContactFabClick() {
        mListener.onAddContactFabClick();
    }

    @Subscribe
    public void onGetUserData(GetUserDataEvent event) {
        if (!event.hasError() && event.getRequestId() == GetUserDataEvent.GET_USER_DATA_TO_APPEND_CONTACTS_LIST) {
            mAdapter.addItem(event.getUser());
        }
    }

    public interface OnFragmentInteractionListener {
        void onAddContactFabClick();
    }

}

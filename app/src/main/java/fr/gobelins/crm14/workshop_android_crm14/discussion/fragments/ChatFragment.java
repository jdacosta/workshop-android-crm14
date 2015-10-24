package fr.gobelins.crm14.workshop_android_crm14.discussion.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.gobelins.crm14.workshop_android_crm14.R;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.DiscussionService;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.adapter.ContactAdapter;

/**
 * Created by risq on 10/24/15.
 */
public class ChatFragment extends Fragment {
    private static final String TAG = "ContactFragment";
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.discussionChatMessageField) TextView messageField;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.chatRecyclerView);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ContactAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // Initial data
//        for (int i = 0; i < 100; i++) {
//            mAdapter.addItem("Contact " + i);
//        }

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

//    @OnClick(R.id.userContactAddFab)
//    public void onAddContactFabClick() {
//        mListener.onAddContactFabClick();
//    }

    public interface OnFragmentInteractionListener {

    }
}

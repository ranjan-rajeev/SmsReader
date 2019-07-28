package com.horizonlabs.smsreader.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.horizonlabs.smsreader.R;
import com.horizonlabs.smsreader.adpaters.KeywordAdapter;
import com.horizonlabs.smsreader.data.local.model.SMSKeyWord;
import com.horizonlabs.smsreader.viewmodel.KeywordViewModel;

import java.util.List;

public class KeywordFragment extends BaseFragment implements View.OnClickListener {

    View view;
    RecyclerView rvAllUsers;
    KeywordViewModel userViewModel;
    KeywordAdapter userAdapter;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_keyword, container, false);
        rvAllUsers = view.findViewById(R.id.rvAllUsers);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        rvAllUsers.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvAllUsers.setHasFixedSize(true);
        userAdapter = new KeywordAdapter();
        rvAllUsers.setAdapter(userAdapter);


        userViewModel = ViewModelProviders.of(this.getActivity()).get(KeywordViewModel.class);
        userViewModel.getAllUser().observe(this.getActivity(), new Observer<List<SMSKeyWord>>() {
            @Override
            public void onChanged(@Nullable List<SMSKeyWord> userEntities) {
                if (userEntities != null) {
                    userAdapter.setUserEntities(userEntities);
                } else {
                    Toast.makeText(getActivity(), "On Changed ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        userAdapter.setOnItemClickListener(new KeywordAdapter.ItemClick() {
            @Override
            public void onItemClick(SMSKeyWord userEntity) {

            }
        });

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                openPopUp(this.getActivity());
                break;
        }
    }

    public void openPopUp(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Keyword");

        // Set up the input
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userViewModel.insertUser(new SMSKeyWord(input.getText().toString()));
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}

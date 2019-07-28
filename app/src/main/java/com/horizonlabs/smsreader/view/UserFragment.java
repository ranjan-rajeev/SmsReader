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
import com.horizonlabs.smsreader.adpaters.UserAdapter;
import com.horizonlabs.smsreader.data.local.model.UserEntity;
import com.horizonlabs.smsreader.viewmodel.UserViewModel;

import java.util.List;

public class UserFragment extends BaseFragment implements View.OnClickListener {

    View view;
    RecyclerView rvAllUsers;
    UserViewModel userViewModel;
    UserAdapter userAdapter;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        rvAllUsers = view.findViewById(R.id.rvAllUsers);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        rvAllUsers.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvAllUsers.setHasFixedSize(true);
        userAdapter = new UserAdapter();
        rvAllUsers.setAdapter(userAdapter);


        userViewModel = ViewModelProviders.of(this.getActivity()).get(UserViewModel.class);
        userViewModel.getAllUser().observe(this.getActivity(), new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> userEntities) {
                if (userEntities != null) {
                    userAdapter.setUserEntities(userEntities);
                } else {
                    Toast.makeText(getActivity(), "On Changed ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        userAdapter.setOnItemClickListener(new UserAdapter.ItemClick() {
            @Override
            public void onItemClick(UserEntity userEntity) {
                userViewModel.deleteUser(userEntity);
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
                userViewModel.insertUser(new UserEntity(input.getText().toString()));
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

package com.horizonlabs.smsreader.adpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.horizonlabs.smsreader.R;
import com.horizonlabs.smsreader.data.local.model.SMSKeyWord;
import com.horizonlabs.smsreader.data.local.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 27-07-2019.
 */
public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.UserHolder> {

    private ItemClick itemClick;
    List<SMSKeyWord> scanEntities = new ArrayList<>();

    public KeywordAdapter() {
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_keyword, viewGroup, false);
        return new UserHolder(itView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int i) {
        SMSKeyWord scanEntity = scanEntities.get(i);
        holder.tvName.setText(scanEntity.getKeyWord());
        holder.tvTag.setText("" + scanEntity.getId());
    }

    @Override
    public int getItemCount() {
        return scanEntities.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvTag;

        public UserHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTag = itemView.findViewById(R.id.tvTag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemView != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                        itemClick.onItemClick(scanEntities.get(getAdapterPosition()));
                }
            });
        }
    }

    public void setUserEntities(List<SMSKeyWord> scanEntities) {
        this.scanEntities = scanEntities;
        notifyDataSetChanged();
    }

    public interface ItemClick {
        void onItemClick(SMSKeyWord scanEntity);
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }
}

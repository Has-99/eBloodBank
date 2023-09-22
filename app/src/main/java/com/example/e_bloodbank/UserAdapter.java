package com.example.e_bloodbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public void setContent(DocumentSnapshot userSnapshot) {
            User user = userSnapshot.toObject(User.class);

            TextView txtName = this.view.findViewById(R.id.name);
            txtName.setText(user.getName());

            TextView txtContact = this.view.findViewById(R.id.contact_no);
            txtContact.setText(user.getContactNumber());

            TextView txtDistrict = this.view.findViewById(R.id.district);
            txtDistrict.setText(user.getDistrict());
        }
    }

    private List<DocumentSnapshot> userSnapshots;

    public UserAdapter(List<DocumentSnapshot> userSnapshots) {
        this.userSnapshots = userSnapshots;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.setContent(userSnapshots.get(position));
    }

    @Override
    public int getItemCount() {
        return this.userSnapshots.size();
    }
}

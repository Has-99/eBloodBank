package com.example.e_bloodbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public void setContent(DocumentSnapshot hospitalSnapshot) {
            Hospital hospital = hospitalSnapshot.toObject(Hospital.class);

            TextView txtName = this.view.findViewById(R.id.name);
            txtName.setText(hospital.getName());

            TextView txtContact = this.view.findViewById(R.id.contact_no);
            txtContact.setText(hospital.getTp());

            TextView txtDistrict = this.view.findViewById(R.id.district);
            txtDistrict.setText(hospital.getDistrict());

            TextView txtProvince = this.view.findViewById(R.id.province);
            txtProvince.setText(hospital.getProvince());
        }
    }

    private List<DocumentSnapshot> hospitalSnapshots;

    public HospitalAdapter(List<DocumentSnapshot> hospitalSnapshots) {
        this.hospitalSnapshots = hospitalSnapshots;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_hospital, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.setContent(hospitalSnapshots.get(position));
    }

    @Override
    public int getItemCount() {
        return this.hospitalSnapshots.size();
    }
}

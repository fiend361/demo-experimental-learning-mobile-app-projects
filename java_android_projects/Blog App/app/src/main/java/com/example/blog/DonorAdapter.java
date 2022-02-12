package com.example.blog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blog.Model.Donor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder> {

    private Context context;
    private List<Donor> donorList;

    public DonorAdapter(Context context, List<Donor> donorList) {
        this.context = context;
        this.donorList = donorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_donor, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Donor donor = donorList.get(i);

        viewHolder.donorName.setText(donor.getFirstName());
        viewHolder.donorDescription.setText(donor.getDonorDescription());
        viewHolder.donorBloodType.setText(donor.getBloodType());

        String imageURL = donor.getImage();
        Picasso.get().load(imageURL).into(viewHolder.donorImage);
    }


    @Override
    public int getItemCount() {
        return donorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView donorImage;
        TextView donorDescription, donorName, donorBloodType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            donorImage = itemView.findViewById(R.id.donor_imageView);
            donorDescription= itemView.findViewById(R.id.donor_description_textView);
            donorName = itemView.findViewById(R.id.donor_Name_textView);
            donorBloodType = itemView.findViewById(R.id.donor_bloodType_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

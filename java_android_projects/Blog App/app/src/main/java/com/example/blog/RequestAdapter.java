package com.example.blog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blog.Model.Request;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private Context context;
    private List<Request> RequestList;

    public RequestAdapter(Context context, List<Request> RequestList) {
        this.context = context;
        this.RequestList = RequestList;
    }

    @NonNull
    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_request, parent, false);
        return new RequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.ViewHolder viewHolder, int i) {
        Request request = RequestList.get(i);

        viewHolder.hosName.setText(request.getHospitalName());
        viewHolder.hosAddress.setText(request.getHospitalAddress());
        viewHolder.reqDescription.setText(request.getRequestDescription());
        viewHolder.reqPoints.setText(request.getRequestPoints());

        String imageURL = request.getHospitalImage();
        Picasso.get().load(imageURL).into(viewHolder.hosImage);
    }

    @Override
    public int getItemCount() {
        return RequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView hosImage;
        TextView hosName, hosAddress, reqDescription, reqPoints;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hosImage = itemView.findViewById(R.id.hos_imageView);
            hosName= itemView.findViewById(R.id.hos_Name_textView);
            hosAddress= itemView.findViewById(R.id.hos_address_textView);
            reqDescription = itemView.findViewById(R.id.req_description_textView);
            reqPoints = itemView.findViewById(R.id.req_points);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

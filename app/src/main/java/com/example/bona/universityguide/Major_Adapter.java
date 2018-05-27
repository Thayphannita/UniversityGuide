package com.example.bona.universityguide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Bona on 29-Sep-17.
 */

public class Major_Adapter extends RecyclerView.Adapter<Major_Adapter.MajorViewHolder> {
    private Major[] majors;
    public Major_Adapter(){
        majors=new Major[0];
    }

    public void setMajors(Major[] majors) {
        this.majors = majors;
        notifyDataSetChanged();
    }

    @Override
    public MajorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.major_viewholder,parent,false);
        MajorViewHolder viewHolder = new MajorViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MajorViewHolder holder, int position) {
        Major major = majors [position];
        holder.txtName.setText(major.majorName);

    }

    @Override
    public int getItemCount() {
        return majors.length;
    }

    public class MajorViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        public MajorViewHolder(View itemView) {

            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.text_view);

        }
    }
}

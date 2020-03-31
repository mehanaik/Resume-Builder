package com.depstar.resumebuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder>{

    Context ctx;
    List<Education> educationList;
    private OnNoteListener mNote;

    public EducationAdapter(Context ctx, List<Education> educationList, OnNoteListener mNote) {
        this.ctx = ctx;
        this.educationList = educationList;
        this.mNote = mNote;
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.layouteducation,parent,false);

        EducationViewHolder educationViewHolder= new EducationViewHolder(view,mNote);
        return educationViewHolder;
    }

    public void removeItem(int position) {
        educationList.remove(position);
        notifyItemRemoved(position);
        Education_page pro=new Education_page();
        pro.delete(educationList.get(position).getCourse());
    }
    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {

        Education education=educationList.get(position);
        holder.course.setText(education.getCourse());
        holder.institute.setText(education.getInstitute());
        holder.cgpa.setText(education.getCgpa());
        holder.year.setText(education.getYear());

    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    class EducationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView course,year,institute,cgpa;
        EducationAdapter.OnNoteListener onNoteListener;
        public EducationViewHolder(@NonNull View itemView, EducationAdapter.OnNoteListener onNoteListener) {
            super(itemView);

            course=itemView.findViewById(R.id.layoutcourse);
            institute=itemView.findViewById(R.id.layoutinstitute);
            cgpa=itemView.findViewById(R.id.layoutcgpa);
            year=itemView.findViewById(R.id.layoutyear);

            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

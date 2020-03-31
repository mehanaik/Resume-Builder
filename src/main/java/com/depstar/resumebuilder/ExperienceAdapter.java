package com.depstar.resumebuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder> {
    Context ctx;
    List<Experience> experienceList;
    private OnNoteListener mNote;

    public ExperienceAdapter(Context ctx, List<Experience> experienceList, OnNoteListener mNote) {
        this.ctx = ctx;
        this.experienceList = experienceList;
        this.mNote = mNote;
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.layoutexperience,parent,false);

        ExperienceViewHolder experienceViewHolder= new ExperienceViewHolder(view,mNote);
        return experienceViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Experience experience=experienceList.get(position);
        holder.organisation.setText(experience.getOrganisation());
        holder.designation.setText(experience.getDesignation());
        holder.duration.setText(experience.getDuration());
    }

    public void removeItem(int position) {
        experienceList.remove(position);
        notifyItemRemoved(position);
        Experience_page pro=new Experience_page();
        pro.delete(experienceList.get(position).getOrganisation());
    }

    @Override
    public int getItemCount() {
        return experienceList.size();    }

    class ExperienceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView organisation,duration,designation;
        ExperienceAdapter.OnNoteListener onNoteListener;


        public ExperienceViewHolder(@NonNull View itemView, ExperienceAdapter.OnNoteListener onNoteListener) {
            super(itemView);

            organisation = itemView.findViewById(R.id.layoutorganisation);
            duration = itemView.findViewById(R.id.layoutduration);
            designation = itemView.findViewById(R.id.layoutdesignation);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

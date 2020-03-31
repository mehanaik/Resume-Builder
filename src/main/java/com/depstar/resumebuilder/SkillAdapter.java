package com.depstar.resumebuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder>  {
    Context ctx;
    List<Skill> skillList;
    private OnNoteListener mNote;

    public SkillAdapter(Context ctx, List<Skill> skillList, OnNoteListener mNote) {
        this.ctx = ctx;
        this.skillList = skillList;
        this.mNote = mNote;
    }

    @NonNull
    @Override
    public SkillAdapter.SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.layoutskills,parent,false);

        SkillViewHolder skillViewHolder= new SkillViewHolder(view,mNote);
        return skillViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull SkillAdapter.SkillViewHolder holder, int position) {
        Skill skill=skillList.get(position);
        holder.skill.setText(skill.getSkill());
    }

    public void removeItem(int position) {
        skillList.remove(position);
        notifyItemRemoved(position);
        Skills_page pro=new Skills_page();
        pro.delete(skillList.get(position).getSkill());
    }

    @Override
    public int getItemCount() {
        return skillList.size();    }

    class SkillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView skill;
        SkillAdapter.OnNoteListener onNoteListener;


        public SkillViewHolder(@NonNull View itemView, SkillAdapter.OnNoteListener onNoteListener) {
            super(itemView);

            skill = itemView.findViewById(R.id.layouttechskills);

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

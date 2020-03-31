package com.depstar.resumebuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {
    Context ctx;
    List<Achievement> achievementList;
    private OnNoteListener mNote;

    public AchievementAdapter(Context ctx, List<Achievement> achievementList, OnNoteListener mNote) {
        this.ctx = ctx;
        this.achievementList = achievementList;
        this.mNote = mNote;
    }

    @NonNull
    @Override
    public AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.layoutachievements,parent,false);

        AchievementViewHolder achievementViewHolder= new AchievementViewHolder(view,mNote);
        return achievementViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewHolder holder, int position) {
        Achievement achievement=achievementList.get(position);
        holder.achive.setText(achievement.getAchive());

    }

    public void removeItem(int position) {
        achievementList.remove(position);
        notifyItemRemoved(position);
        Achievement_page pro=new Achievement_page();
        pro.delete(achievementList.get(position).getAchive());
    }
    @Override
    public int getItemCount() {
        return achievementList.size();
    }

    class AchievementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView achive;
        AchievementAdapter.OnNoteListener onNoteListener;


        public AchievementViewHolder(@NonNull View itemView, AchievementAdapter.OnNoteListener onNoteListener) {
            super(itemView);

            achive = itemView.findViewById(R.id.layoutach);

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

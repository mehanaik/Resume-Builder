package com.depstar.resumebuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestViewHolder>  {
    Context ctx;
    List<Interest> interestList;
    private OnNoteListener mNote;

    public InterestAdapter(Context ctx, List<Interest> interestList, OnNoteListener mNote) {
        this.ctx = ctx;
        this.interestList = interestList;
        this.mNote = mNote;
    }

    @NonNull
    @Override
    public InterestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.layoutinterest,parent,false);

        InterestViewHolder interestViewHolder= new InterestViewHolder(view,mNote);
        return interestViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InterestViewHolder holder, int position) {
        Interest interest=interestList.get(position);
        holder.interest.setText(interest.getInterest());
    }

    public void removeItem(int position) {
        interestList.remove(position);
        notifyItemRemoved(position);
        Interest_page pro=new Interest_page();
        pro.delete(interestList.get(position).getInterest());
    }

    @Override
    public int getItemCount() {
        return interestList.size();
    }

    class InterestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView interest;
        InterestAdapter.OnNoteListener onNoteListener;


        public InterestViewHolder(@NonNull View itemView,InterestAdapter.OnNoteListener onNoteListener) {
            super(itemView);

            interest = itemView.findViewById(R.id.layoutint);

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

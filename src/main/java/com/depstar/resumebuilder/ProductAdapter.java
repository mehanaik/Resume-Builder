package com.depstar.resumebuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    Context ctx;
    List<product> productList;
    private OnNoteListener monNote;

    public ProductAdapter(Context ctx, List<product> productList,OnNoteListener onNoteListener) {
        this.ctx = ctx;
        this.productList = productList;
        this.monNote=onNoteListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.layoutproject,parent,false);

        ProductViewHolder productViewHolder= new ProductViewHolder(view,monNote);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        product product=productList.get(position);
        holder.txtname.setText(product.getProductname());
        holder.txtdes.setText(product.getDesc());
        holder.txttime.setText(product.getTime());

    }
    public void removeItem(int position) {
        productList.remove(position);
        notifyItemRemoved(position);
        Project_page pro=new Project_page();
        pro.delete(productList.get(position).getProductname());
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtname,txtdes,txttime;
        OnNoteListener onNoteListener;
        public ProductViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            txtname=itemView.findViewById(R.id.txtprojectname1);
            txtdes=itemView.findViewById(R.id.txtprojectdescription1);
            txttime=itemView.findViewById(R.id.txtprojectduration1);
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

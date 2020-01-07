package br.com.module.exchange.views.rvadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.module.exchange.R;
import br.com.module.exchange.model.History;

public class RVAHistory extends RecyclerView.Adapter<RVAHistory.ItemViewHolder> {

    private List<History> mItens;

    private Context mContext;

    public RVAHistory(List<History> itens, Context context) {
        this.mItens = itens;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rva_history, parent, false);
        RVAHistory.ItemViewHolder itemViewHolder = new RVAHistory.ItemViewHolder(v);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.mTxtFromTo.setText(mContext.getString(R.string.app_from) +" "+
                this.mItens.get(position).getFrom() +" "+
                mContext.getString(R.string.app_to) +" "+
                this.mItens.get(position).getTo());

        holder.mTxtResult.setText(this.mItens.get(position).getResult());
        holder.mTxtDate.setText(this.mItens.get(position).getDate());
        holder.mTxtHour.setText(this.mItens.get(position).getHourMinuteAndSeconds());
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView mTxtFromTo;
        protected TextView mTxtResult;
        protected TextView mTxtDate;
        protected TextView mTxtHour;

        public ItemViewHolder(final View itemView) {
            super(itemView);

            mTxtFromTo = (TextView) itemView.findViewById(R.id.idTxtFromTo);
            mTxtResult = (TextView) itemView.findViewById(R.id.idTxtResult);
            mTxtDate = (TextView) itemView.findViewById(R.id.idTxtDate);
            mTxtHour = (TextView) itemView.findViewById(R.id.idTxtHour);

        }
    }
}

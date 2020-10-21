package cs.ubru.ubcandlefestival;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TempleAdapter extends RecyclerView.Adapter<TempleAdapter.TempleViewHolder> {
    private Context context;
    private ArrayList<Temple> temples;
    private OnItemClickListener listener;

    public TempleAdapter(Context context, ArrayList<Temple> temples) {
        this.context = context;
        this.temples = temples;
    }

    @Override
    public TempleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.temple_item, parent, false);
        return new TempleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempleViewHolder holder, int position) {
        Temple temple = temples.get(position);

        int id = temple.getId();
        String templeName = temple.getTempleName();
        String city = temple.getCity();

        holder.tvTempleName.setText(templeName);
        holder.tvCity.setText(city);
    }

    @Override
    public int getItemCount() {
        return temples.size();
    }

    public class TempleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTempleName;
        public TextView tvCity;
        public TempleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTempleName = itemView.findViewById(R.id.tv_temple_name);
            tvCity = itemView.findViewById(R.id.tv_city);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

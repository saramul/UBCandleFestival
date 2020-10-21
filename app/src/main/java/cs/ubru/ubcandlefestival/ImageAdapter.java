package cs.ubru.ubcandlefestival;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context context;
    private ArrayList<Image> images;

    public ImageAdapter(Context context, ArrayList<Image> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image = images.get(position);

        String templeName = image.getTempleName();
        String description = image.getDescription();
        String imgName = image.getImgName();

        String imgURL = "http://suriyon.cs.ubru.ac.th/uboncandlefest/images/" + imgName;

        holder.tvTempleName.setText(templeName);
        holder.tvDescription.setText(description);

        Picasso.get().load(imgURL).fit().centerInside().into(holder.imgName);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgName;
        public TextView tvTempleName;
        public TextView tvDescription;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imgName = itemView.findViewById(R.id.img_candle);
            tvTempleName = itemView.findViewById(R.id.tv_temple_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}

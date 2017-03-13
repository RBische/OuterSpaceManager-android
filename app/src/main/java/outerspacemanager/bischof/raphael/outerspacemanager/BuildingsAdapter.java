package outerspacemanager.bischof.raphael.outerspacemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.Building;

/**
 * Created by biche on 13.03.17.
 */
public class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.BuildingViewHolder> {
    private final List<Building> buildings;
    private final Context context;

    public BuildingsAdapter(List<Building> buildings, Context context) {
        this.buildings = buildings;
        this.context = context;
    }

    @Override
    public BuildingsAdapter.BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_buildings, parent, false);
        BuildingViewHolder viewHolder = new BuildingViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BuildingsAdapter.BuildingViewHolder holder, int position) {
        Building building = buildings.get(position);
        holder.tvName.setText(building.getName());
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public class BuildingViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public BuildingViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}

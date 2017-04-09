package com.example.deepak.viewpager2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.viewpager2.model.PlacementRecords;

import java.util.ArrayList;

/**
 * Created by deepak on 8/4/17.
 */

public class Tab3 extends Fragment {

    RecyclerView rvPlacementsRecords  ;
    ArrayList<PlacementRecords> placementRecordsArrayList ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab3, container, false);
    }



    PlacementRecordsAdapter placementRecordsAdapter = new PlacementRecordsAdapter();

    class PlacementRecordsHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvYear,tvNumberStudents;

        public PlacementRecordsHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tvName);
            this.tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            this.tvNumberStudents = (TextView) itemView.findViewById(R.id.tvNumberStudents);
        }
    }

    class PlacementRecordsAdapter extends RecyclerView.Adapter<PlacementRecordsHolder>{

        @Override
        public PlacementRecordsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater(null);

            View itemView;
            itemView = layoutInflater.inflate(R.layout.rv_placements_records,parent,false);
            PlacementRecordsHolder placementRecordsHolder  = new PlacementRecordsHolder(itemView);
            return placementRecordsHolder;
        }

        @Override
        public void onBindViewHolder(PlacementRecordsHolder holder, int position) {
            PlacementRecords placementRecords = placementRecordsArrayList.get(position);
            holder.tvName.setText(placementRecords.getName());
            holder.tvYear.setText(placementRecords.getYear());
            holder.tvNumberStudents.setText(placementRecords.getNumberOfStudents());
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}



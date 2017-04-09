package com.example.deepak.viewpager2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.deepak.viewpager2.model.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak on 8/4/17.
 */

public class Tab2 extends Fragment {

    private RecyclerView companyRv;
    private CompanyListAdapter companyListAdapter;
    private List<Company> companyList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        companyRv = (RecyclerView)getActivity().findViewById(R.id.recycler_view);
        companyList = new ArrayList<>();
        companyListAdapter = new CompanyListAdapter(getActivity(), companyList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        companyRv.setLayoutManager(mLayoutManager);
        companyRv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        companyRv.setItemAnimator(new DefaultItemAnimator());
        companyRv.setAdapter(companyListAdapter);
        prepareCompanies();
    }

    class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.MyViewHolder> {

        private Context mContext;
        private List<Company> companyList;

        class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView title, count;
            public ImageView thumbnail, overflow;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.comp_title);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
                overflow = (ImageView) view.findViewById(R.id.overflow);
            }
        }


        public CompanyListAdapter(Context mContext, List<Company> companyList) {
            this.mContext = mContext;
            this.companyList = companyList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.company_card, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            Company company = companyList.get(position);
            holder.title.setText(company.getCompanyName());

            // loading album cover using Glide library
            Glide.with(mContext).load(company.getThumbnail()).into(holder.thumbnail);

            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(holder.overflow);
                }
            });
        }

        /**
         * Showing popup menu when tapping on 3 dots
         */
        private void showPopupMenu(View view) {
            // inflate menu
            PopupMenu popup = new PopupMenu(mContext, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_company, popup.getMenu());
            popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
            popup.show();
        }

        /**
         * Click listener for popup menu items
         */
        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            public MyMenuItemClickListener() {
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add_favourite:
                        Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_play_next:
                        Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                }
                return false;
            }
        }

        @Override
        public int getItemCount() {
            return companyList.size();
        }
    }

    private void prepareCompanies() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Company a = new Company("True Romance", covers[0]);
        companyList.add(a);

        a = new Company("Xscpae", covers[1]);
        companyList.add(a);

        a = new Company("Maroon 5", covers[2]);
        companyList.add(a);

        a = new Company("Born to Die", covers[3]);
        companyList.add(a);

        a = new Company("Honeymoon", covers[4]);
        companyList.add(a);

        a = new Company("I Need a Doctor", covers[5]);
        companyList.add(a);

        a = new Company("Loud", covers[6]);
        companyList.add(a);

        a = new Company("Legend", covers[7]);
        companyList.add(a);

        a = new Company("Hello", covers[8]);
        companyList.add(a);

        a = new Company("Greatest Hits", covers[9]);
        companyList.add(a);

        companyListAdapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}

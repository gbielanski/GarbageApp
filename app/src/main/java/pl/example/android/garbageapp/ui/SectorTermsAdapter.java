package pl.example.android.garbageapp.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.databinding.BigRcListElementBinding;
import pl.example.android.garbageapp.databinding.SmallRcListElementBinding;
import pl.example.android.garbageapp.utilities.SectorTermsUtil;

class SectorTermsAdapter extends RecyclerView.Adapter<SectorTermsAdapter.TermViewHolder> {

    public final int VIEW_TYPE_FIRST = 1;
    public final int VIEW_TYPE_ANY = 0;
    private List<SectorTerm> mSectorTermsData = SectorTermsUtil.getFakeSectorTermsData();
    private int mSelectorColor;

    public SectorTermsAdapter(int color) {
        this.mSelectorColor = color;
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_FIRST) {
            BigRcListElementBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.big_rc_list_element, parent, false);
            binding.setSectorColor(mSelectorColor);
            return new TermViewHolder(binding);
        } else {
            SmallRcListElementBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.small_rc_list_element, parent, false);
            binding.setSectorColor(mSelectorColor);
            return new TermViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_FIRST)
            holder.bigElementBinding.setSectorTerm(mSectorTermsData.get(position));
        else
            holder.smallElementBinding.setSectorTerm(mSectorTermsData.get(position));

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return VIEW_TYPE_FIRST;

        return VIEW_TYPE_ANY;
    }

    @Override
    public int getItemCount() {
        return mSectorTermsData.size();
    }

    public class TermViewHolder extends RecyclerView.ViewHolder {
        SmallRcListElementBinding smallElementBinding;
        BigRcListElementBinding bigElementBinding;

        public TermViewHolder(SmallRcListElementBinding binding) {
            super(binding.getRoot());
            this.smallElementBinding = binding;
        }

        public TermViewHolder(BigRcListElementBinding binding) {
            super(binding.getRoot());
            this.bigElementBinding = binding;
        }
    }
}

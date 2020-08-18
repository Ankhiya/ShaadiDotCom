package com.mobile.shaadidotcom.ankhiya.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobile.shaadidotcom.ankhiya.R;
import com.mobile.shaadidotcom.ankhiya.databinding.ViewHolderCandidateBinding;
import com.mobile.shaadidotcom.ankhiya.model.Candidate;
import com.mobile.shaadidotcom.ankhiya.view.CandidateAdapterCallback;

/**
 * Candidates adapter
 */
public final class CandidatesAdapter extends BasePaginatedRecyclerViewAdapter<Candidate> {

    @NonNull
    private final CandidateAdapterCallback mCandidateAdapterCallback;
    private static final int S_CANDIDATE_VIEW_TYPE_ID = 30;

    public CandidatesAdapter(int takeCount,
                             @NonNull CandidateAdapterCallback candidateAdapterCallback) {
        super(takeCount);
        mCandidateAdapterCallback = candidateAdapterCallback;
    }

    @Override
    protected void getMoreItems(int dataSize) {
        mCandidateAdapterCallback.getMoreData(dataSize);
    }

    @Override
    protected int getViewType(int position) {
        return S_CANDIDATE_VIEW_TYPE_ID;
    }

    @Override
    protected RecyclerView.ViewHolder getView(ViewGroup parent, int viewType) {
        View item = View.inflate(parent.getContext(), R.layout.view_holder_candidate, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        item.setLayoutParams(lp);
        ViewHolderCandidateBinding viewHolderCandidateBinding = DataBindingUtil.bind(item);
        return new CandidateViewHolder(viewHolderCandidateBinding);
    }

    @Override
    protected void bindView(RecyclerView.ViewHolder holder, int position) {
        CandidateViewHolder candidateViewHolder = (CandidateViewHolder) holder;
        candidateViewHolder.bind(getList().get(position));
    }

    /**
     * View holder
     */
    private class CandidateViewHolder extends RecyclerView.ViewHolder {

        private final ViewHolderCandidateBinding mBinding;

        CandidateViewHolder(ViewHolderCandidateBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(@NonNull Candidate candidate) {
            // loading profile with place holder
            Glide.with(mBinding.getRoot().getContext())
                    .load(candidate.getLargePictureURL())
                    .placeholder(R.drawable.user)
                    .apply(RequestOptions.circleCropTransform())
                    .into(mBinding.candidateProfileImageView);

            // updating view with data
            mBinding.candidateNameTextView.setText(candidate.getName());
            mBinding.ageNatTextView.setText(candidate.getAgeNat());
            mBinding.cityStateCountryTextView.setText(candidate.getAddress());
            mBinding.setIsReacted(candidate.isReacted());
            mBinding.setIsAccepted(candidate.isAccepted());
            mBinding.candidateNameTextView.setText(candidate.getFirstName());
            mBinding.acceptCandidate.setOnClickListener(view -> {
                mCandidateAdapterCallback.acceptCandidate(candidate);
                mBinding.setIsReacted(true);
                mBinding.setIsAccepted(true);
            });
            mBinding.declineCandidate.setOnClickListener(view -> {
                mCandidateAdapterCallback.declineCandidate(candidate);
                mBinding.setIsReacted(true);
                mBinding.setIsAccepted(false);
            });
        }
    }
}

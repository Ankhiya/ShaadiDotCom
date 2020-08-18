package com.mobile.shaadidotcom.ankhiya.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.mobile.shaadidotcom.ankhiya.adapter.CandidatesAdapter;
import com.mobile.shaadidotcom.ankhiya.databinding.FragmentCandidatesListBinding;
import com.mobile.shaadidotcom.ankhiya.model.Candidate;
import com.mobile.shaadidotcom.ankhiya.utils.connetivity.base.ConnectivityProvider;
import com.mobile.shaadidotcom.ankhiya.viewmodel.CandidatesViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Use this fragment to show list of candidates with accept and decline actions
 */
public class CandidateListFragment extends Fragment
        implements ConnectivityProvider.ConnectivityStateListener {

    static final String TAG = "CandidateListFragment";
    private CandidatesAdapter mCandidatesAdapter;
    private CandidatesViewModel mViewModel;
    private FragmentCandidatesListBinding mBinding;
    private WeakReference<ConnectivityProvider> mConnectivityProvider;
    private boolean mIsConnected;

    /**
     * newInstance fragment
     *
     * @return fragmnet instance {@link CandidateListFragment}
     */
    static CandidateListFragment newInstance() {
        return new CandidateListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCandidatesListBinding.inflate(inflater, container, false);
        if (getContext() != null) {
            mConnectivityProvider = new WeakReference<>(ConnectivityProvider.createProvider(getContext()));
            mIsConnected = mConnectivityProvider.get().isConnected();
        }
        initViewPager();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this)
                .get(CandidatesViewModel.class);
        subscribeCandidatesList(mViewModel.getCandidates());
        subscribeError(mViewModel.getError());
    }

    @Override
    public void onDestroyView() {
        mCandidatesAdapter.cleanUp();
        mCandidatesAdapter = null;
        super.onDestroyView();
    }

    private void initViewPager() {
        mCandidatesAdapter = new CandidatesAdapter(CandidatesViewModel.TAKE_COUNT,
                mCandidateAdapterCallback);
        mBinding.candidatesRecyclerView.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset,
                                               int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                        mBinding.setCurrentCount(String.valueOf(position + 1));
                    }
                });
        mBinding.candidatesRecyclerView.setPageTransformer(new ZoomOutPageTransformer());
        mBinding.candidatesRecyclerView.setAdapter(mCandidatesAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mConnectivityProvider != null && mConnectivityProvider.get() != null) {
            mConnectivityProvider.get().addListener(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mConnectivityProvider != null && mConnectivityProvider.get() != null) {
            mConnectivityProvider.get().removeListener(this);
        }
    }

    @Override
    public void onStateChange(boolean isConnected) {
        if (!mIsConnected) {
            loadMoreData(mCandidatesAdapter.getCurrentSize());
        }
        this.mIsConnected = isConnected;
    }

    private void loadMoreData(int currentSize) {
        if (mConnectivityProvider != null && mConnectivityProvider.get() != null) {
            mIsConnected = mConnectivityProvider.get().isConnected();
        }
        mViewModel.loadDataFor(currentSize, mIsConnected);
    }

    /**
     * Callback for list item view user interactions
     */
    private final CandidateAdapterCallback mCandidateAdapterCallback
            = new CandidateAdapterCallback() {
        @Override
        public void getMoreData(int currentSize) {
            loadMoreData(currentSize);
        }

        @Override
        public void acceptCandidate(Candidate candidate) {
            mViewModel.accepted(candidate);
        }

        @Override
        public void declineCandidate(Candidate candidate) {
            mViewModel.declined(candidate);
        }
    };

    private void subscribeError(LiveData<String> error) {
        error.observe(getViewLifecycleOwner(), errorString -> mCandidatesAdapter.setError(errorString));
    }

    private void subscribeCandidatesList(LiveData<List<Candidate>> liveData) {
        // Update the list when the data changes
        liveData.observe(getViewLifecycleOwner(), candidates -> {
            if (mCandidatesAdapter.getCurrentSize() == 0) {
                mBinding.countDetailsView.setVisibility(View.VISIBLE);
            }
            mCandidatesAdapter.setData(candidates);
            mBinding.setTotalCount(String.valueOf(mCandidatesAdapter.getCurrentSize()));
        });
    }
}

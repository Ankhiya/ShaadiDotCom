package com.mobile.shaadidotcom.ankhiya.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.shaadidotcom.ankhiya.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for paginated recycler view adapter
 * Extend this class to implement pagination list
 */
public abstract class BasePaginatedRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    private static final int S_LOADING_VIEW_TYPE_ID = 28;
    private static final int S_NO_RESULTS_VIEW_TYPE_ID = 20;
    private static final int S_ERROR_VIEW_TYPE_ID = 40;

    private List<T> mData;
    private boolean mIsLoading = false;
    private boolean mIsError = false;
    private boolean mIsPendingData = true;
    private int mNextLoadingPosition = 0;
    private int mTakeCount;
    private String mErrorMessage;

    /**
     * Constructor
     */
    BasePaginatedRecyclerViewAdapter(int takeCount) {
        this.mData = new ArrayList<>();
        mTakeCount = takeCount;
    }

    /**
     * Call this method to clean objects
     */
    public void cleanUp() {
        mData = null;
    }

    /**
     * Call this method to check for error
     *
     * @return true if error, false otherwise
     */
    public boolean isError() {
        return mIsError;
    }

    /**
     * Call this method to set error
     *
     * @param errorMessage message to be set
     */
    public void setError(String errorMessage) {
        mIsError = errorMessage != null;
        mErrorMessage = errorMessage;
        notifyDataSetChanged();
    }

    /**
     * Call this method to get current data list size
     *
     * @return data list size
     */
    public int getCurrentSize() {
        return mData.size();
    }

    /**
     * Call this method to append data list
     *
     * @param data data list
     */
    public void setData(List<T> data) {
        setLoading(false);
        if (null == data || data.isEmpty()) {
            mIsPendingData = false;
        }
        if (null != data && !data.isEmpty()) {
            mIsPendingData = true;
            mData.addAll(mData.size(), data);
            if (mIsPendingData) {
                mNextLoadingPosition = mData.size() - (mTakeCount / 2);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * Resets adapter
     * call this method instead creating new adapter
     */
    public void reset() {
        if (null != mData) {
            mData.clear();
        }
        mIsPendingData = true;
        setLoading(false);
        setError(null);
        notifyDataSetChanged();
    }


    /**
     * Call this method to get data list
     *
     * @return list
     */
    List<T> getList() {
        return mData;
    }

    /**
     * item view type to be shown at given position
     *
     * @param position ** position **
     * @return ** view type **
     */
    @Override
    public int getItemViewType(final int position) {

        if (mIsError && position == mData.size()) {
            return S_ERROR_VIEW_TYPE_ID;
        } else if (mIsPendingData && position == mData.size()) {
            return S_LOADING_VIEW_TYPE_ID;
        } else if (mIsPendingData && position == mNextLoadingPosition) {
            callGetMoreData();
            return getViewType(position);
        } else if (!mIsPendingData && mData.isEmpty()) {
            return S_NO_RESULTS_VIEW_TYPE_ID;
        }
        return getViewType(position);
    }

    /**
     * returns view
     *
     * @param viewGroup ** viewGroup **
     * @param viewType  ** type**
     * @return ** view **
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup,
                                                      final int viewType) {
        if (viewType == S_NO_RESULTS_VIEW_TYPE_ID) {
            return getNoResultView(viewGroup);
        } else if (viewType == S_LOADING_VIEW_TYPE_ID) {
            return getLoadingView(viewGroup);
        } else if (viewType == S_ERROR_VIEW_TYPE_ID) {
            return getErrorView(viewGroup);
        }
        return getView(viewGroup, viewType);
    }

    /**
     * bind view with data
     *
     * @param viewHolder ** view holder **
     * @param position   ** position **
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder,
                                 final int position) {
        if (viewHolder.getItemViewType() == S_LOADING_VIEW_TYPE_ID) {
            callGetMoreData();
        } else if (viewHolder.getItemViewType() != S_NO_RESULTS_VIEW_TYPE_ID
                && viewHolder.getItemViewType() != S_ERROR_VIEW_TYPE_ID) {
            bindView(viewHolder, position);
        }
    }

    /**
     * count of items
     *
     * @return ** count **
     */
    @Override
    public int getItemCount() {
        if (mIsPendingData) {
            // returning extra view for loading view
            return mData.size() + 1;
        } else if (mData.isEmpty()) {
            // returning extra view for no result view
            return mData.size() + 1;
        } else {
            return mData.size();
        }
    }

    /**
     * Override this method in derived class to get more data
     */
    protected abstract void getMoreItems(int dataSize);

    /**
     * Override this method in derived class to return view type for given position
     *
     * @param position ** position **
     * @return ** view type **
     */
    protected abstract int getViewType(int position);

    /**
     * Override this method in derived class to return view for custom view type
     *
     * @param parent   ** parent **
     * @param viewType ** view type **
     * @return ** view **
     */
    protected abstract RecyclerView.ViewHolder getView(ViewGroup parent, int viewType);

    /**
     * Override this method in derived class to bind view with data
     *
     * @param holder   ** view holder **
     * @param position ** position **
     */
    protected abstract void bindView(RecyclerView.ViewHolder holder, int position);

    private void callGetMoreData() {
        if (!this.isLoading()) {
            setLoading(true);
            getMoreItems(mData.size());
        }
    }

    private RecyclerView.ViewHolder getLoadingView(final ViewGroup viewGroup) {
        View item = View.inflate(viewGroup.getContext(), R.layout.view_holder_loading, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        item.setLayoutParams(lp);
        return new LoadingViewHolder(item);
    }

    private RecyclerView.ViewHolder getNoResultView(final ViewGroup viewGroup) {
        View item = View.inflate(viewGroup.getContext(), R.layout.view_holder_no_result, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        item.setLayoutParams(lp);
        return new NoResultFoundViewHolder(item);
    }

    private RecyclerView.ViewHolder getErrorView(final ViewGroup viewGroup) {
        View item = View.inflate(viewGroup.getContext(), R.layout.view_holder_error, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        item.setLayoutParams(lp);
        return new ErrorViewHolder(item);
    }

    private void setLoading(boolean loading) {
        mIsLoading = loading;
    }

    private boolean isLoading() {
        return mIsLoading;
    }

    /**
     * No result view holder
     */
    private static class NoResultFoundViewHolder extends RecyclerView.ViewHolder {

        private NoResultFoundViewHolder(@NonNull final View itemView) {
            super(itemView);
        }
    }

    /**
     * Loading view holder
     */
    private static class LoadingViewHolder extends RecyclerView.ViewHolder {

        private LoadingViewHolder(@NonNull final View itemView) {
            super(itemView);
        }
    }

    /**
     * Error view holder
     */
    private class ErrorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ErrorViewHolder(@NonNull final View itemView) {
            super(itemView);
            TextView errorTv = itemView.findViewById(R.id.tvErrorMessage);
            ImageButton retryIb = itemView.findViewById(R.id.btnRetry);
            errorTv.setText(mErrorMessage);
            retryIb.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            setError(null);
            notifyDataSetChanged();
        }
    }
}

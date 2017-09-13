package com.example.doncho.androidmvpsample;

import android.support.v4.app.Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


public abstract class BaseFragment extends Fragment {
    protected int mLoadingContainerId;
    protected int mContentContainerId;
    private ViewGroup mLoadingContainer;
    private ProgressBar mLoadingUI;

    protected void setLoadingContainerId(int loadingContainerId) {
        mLoadingContainerId = loadingContainerId;
    }

    protected void setContentContainerId(int contentContainerId) {
        mContentContainerId = contentContainerId;
    }

    public void showLoadingUi() {
        if(mLoadingContainer == null) {
            mLoadingContainer = getActivity().findViewById(mLoadingContainerId);
            mLoadingUI = new ProgressBar(getContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            mLoadingUI.setLayoutParams(lp);
            mLoadingContainer.addView(mLoadingUI);
        }

        getActivity().findViewById(mLoadingContainerId).setVisibility(View.VISIBLE);
        getActivity().findViewById(mContentContainerId).setVisibility(View.GONE);
    }

    public void hideLoadingUi() {
        getActivity().findViewById(mLoadingContainerId).setVisibility(View.GONE);
        getActivity().findViewById(mContentContainerId).setVisibility(View.VISIBLE);
    }
}


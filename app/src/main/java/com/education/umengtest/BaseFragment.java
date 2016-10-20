package com.education.umengtest;

import android.support.v4.app.Fragment;

/**
 * Created by zhonghang on 2016/10/20.
 */

public class BaseFragment extends Fragment {
    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getActivity().getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(getActivity().getClass().getName());
    }
}

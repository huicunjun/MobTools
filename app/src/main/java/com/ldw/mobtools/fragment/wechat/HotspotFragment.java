package com.ldw.mobtools.fragment.wechat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldw.mobtools.R;
import com.ldw.mobtools.bean.WechatDataBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotspotFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private List<WechatDataBean.ResultBean.ListBean> list;

    public HotspotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_wechat, container, false);
        recyclerView = view.findViewById(R.id.frag_wechat_rec);


        return view;
    }

}

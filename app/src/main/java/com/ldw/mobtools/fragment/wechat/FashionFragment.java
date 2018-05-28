package com.ldw.mobtools.fragment.wechat;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ldw.mobtools.R;
import com.ldw.mobtools.adapter.WechatRecyclerViewAdapter;
import com.ldw.mobtools.bean.WechatDataBean;
import com.ldw.mobtools.utils.MsgUtils;
import com.ldw.mobtools.utils.OkhttpUtils;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FashionFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private WechatRecyclerViewAdapter adapter;
    private List<WechatDataBean.ResultBean.ListBean> datalist;

    private Handler handler;
    private Gson gson = new Gson();
    private static String url = "http://apicloud.mob.com/wx/article/search?key=25c1f67d8be28&cid=";
    public FashionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initHandler();
        initData();
        view =inflater.inflate(R.layout.fragment_wechat, container, false);



        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = OkhttpUtils.http_get(url+"1");
                    if (result.length()>20){
                        handler.sendMessage(MsgUtils.getMsg(1,result));
                    } else {
                        handler.sendMessage(MsgUtils.getMsg(0,null));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        WechatDataBean wechatDataBean = gson.fromJson(msg.obj.toString(),WechatDataBean.class);
                        datalist = wechatDataBean.getResult().getList();
                        initRecylerView();
                        break;

                        //错误情况
                    case 0:
                        Toast.makeText(getContext(), "服务器GG了!!!", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
    }

    private void initRecylerView() {
        recyclerView = view.findViewById(R.id.frag_wechat_rec);

    }

}

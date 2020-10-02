package com.canhdinh.lib.event;

import com.canhdinh.lib.helper.BusHelper;

public class EventDemo {
    public static void post() {
        BusHelper.post(new EventDemo());
    }
}

//   1. Define events:
//
//public static class MessageEvent { /* Additional fields if needed */ }
//  2.  Prepare subscribers: Declare and annotate your subscribing method, optionally specify a thread mode:
//
//@Subscribe(threadMode = ThreadMode.MAIN)
//public void onMessageEvent(MessageEvent event) {/* Do something */};
//
//@Override
//public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//        }
//
//@Override
//public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//        }
//
//        3.Post events:
//
//        EventBus.getDefault().post(new MessageEvent());
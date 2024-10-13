package com.vokrob.notebook.db;

import com.vokrob.notebook.adapter.ListItem;

import java.util.List;

public interface OnDataReceived {
    void onReceived(List<ListItem> list);
}

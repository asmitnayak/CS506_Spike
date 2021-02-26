package com.example.android.cs506_spike;

import androidx.recyclerview.widget.RecyclerView;

public interface CallBackOrderTouch {

    void orderTouchOnMove(int oldPosition, int newPosition);

    void onSwiped(RecyclerView.ViewHolder viewHolder, int position);
}

package com.example.android.cs506_spike;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class orderItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    CallBackOrderTouch callBackOrderTouch;

    public orderItemTouchHelperCallBack(CallBackOrderTouch callBackOrderTouch) {
        this.callBackOrderTouch = callBackOrderTouch;
    }

    //


    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        // get movement of the recyclerview order
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        // moves items
        callBackOrderTouch.orderTouchOnMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        callBackOrderTouch.onSwiped(viewHolder,viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        } else{
            final View fgView = ((OrderAdapter.OrderViewHolder)viewHolder).viewB;
            getDefaultUIUtil().onDrawOver(c,recyclerView,fgView, dX,dY,actionState,isCurrentlyActive);
        }
        //show delete button when we swipe
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    if(actionState!=ItemTouchHelper.ACTION_STATE_DRAG){
        final View fgView = ((OrderAdapter.OrderViewHolder)viewHolder).viewF;
        getDefaultUIUtil().onDrawOver(c,recyclerView,fgView, dX,dY,actionState,isCurrentlyActive);
    }


    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //super.clearView(recyclerView, viewHolder);

        final View fgView = ((OrderAdapter.OrderViewHolder)viewHolder).viewF;
        fgView.setBackgroundColor(ContextCompat.getColor(((OrderAdapter.OrderViewHolder)viewHolder).viewF.getContext(),R.color.white));
        getDefaultUIUtil().clearView(fgView);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(viewHolder!=null){
            final View fgView = ((OrderAdapter.OrderViewHolder)viewHolder).viewF;
            if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
                fgView.setBackgroundColor(Color.LTGRAY);
            }
            getDefaultUIUtil().onSelected(fgView);
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
}

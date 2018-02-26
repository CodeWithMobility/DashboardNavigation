package com.mobiledev.dashboardnavigation.ease;

/**
 * Created by manu on 1/7/2018.
 */

public interface IOnItemFocusChangedListener {

    /**
     * Called when a new item in PieChart is selected
     * @param _Position List position of the item.
     */
    void onItemFocusChanged(int _Position);
}

package com.androiddevteam.quest.activity;

import android.app.ActionBar;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androiddevteam.quest.R;
import com.androiddevteam.quest.adapter.ListAdapterAllPoints;
import com.androiddevteam.quest.google_map.GoogleMapManager;
import com.androiddevteam.quest.structure.PointItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 06.07.15
 */

public class ActNewQuest extends BaseAbstractFragmentActivity
        implements
        OnMapReadyCallback,
        View.OnClickListener{

    public static final int CONTENT_VIEW_ID = R.layout.act_new_quest;
    private static final int ACTION_BAR_LAYOUT_ID = R.layout.action_bar_act_new_quest;
    public static final int MAP_ID = R.id.google_map;
    public static final int MOVE_TO_CURRENT_LOC_ID = R.id.imageButton_new_quest_curr_loc;
    public static final int SWITCH_MAP_TYPE_ID = R.id.imageButton_new_quest_switch_map;
    public static final int POINTS_DRAWER_ID = R.id.slidingDrawer_pointsList;
    public static final int POINTS_LIST_ID = R.id.listView_points;
    private static final int ACTION_BAR_TITLE_ID = R.id.textView_title;
    private static final int ACTION_BAR_ACCEPT_ID = R.id.imageButton_accept;
    private static final int ACTION_BAR_CANCEL_ID = R.id.imageButton_cancel;

    //Handle points
    public static final int HANDLE_LAYOUT_ID = R.id.relativeLayout_handle;
    public static final int HANDLE_START_POINT_ID = R.id.textView_startPoint;
    public static final int HANDLE_FINISH_POINT_ID = R.id.textView_finishPoint;
    public static final int HANDLE_DISTANCE_MAIN_ID = R.id.textView_main_distance;

    //Content points
    public static final int CONTENT_LAYOUT_ID = R.id.linearLayout_content;


    private static final String NEW_QUEST_NAME_DEFAULT = "New Quest";

    public static final int START_POINT_INDEX = 0;

    private GoogleMapManager googleMapManager;
    private List<PointItem> points;
    private List<String> distances;
    private int mainDistance = 0;

    @Override
    protected void actionsBeforeParentResume() {

    }

    @Override
    protected void actionsAfterParentResume() {

    }

    @Override
    protected void actionsBeforeParentPause() {

    }

    @Override
    protected void actionsAfterParentPause() {

    }

    @Override
    protected void actionsBeforeParentStop() {

    }

    @Override
    protected void actionsAfterParentStop() {

    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected int getContentViewId() {
        return CONTENT_VIEW_ID;
    }

    @Override
    protected void additionalCustomization() {
        ((MapFragment) getFragmentManager().findFragmentById(MAP_ID)).getMapAsync(this);
        findViewById(MOVE_TO_CURRENT_LOC_ID).setOnClickListener(this);
        findViewById(SWITCH_MAP_TYPE_ID).setOnClickListener(this);

        points = new ArrayList<>();
        distances = new ArrayList<>();
        ListAdapterAllPoints adapterAllPoints = new ListAdapterAllPoints(this, points, distances);
        ((ListView) findViewById(POINTS_LIST_ID)).setAdapter(adapterAllPoints);
    }

    @Override
    protected void customizeActionBar(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setCustomView(ACTION_BAR_LAYOUT_ID);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ((TextView)actionBar.getCustomView().findViewById(ACTION_BAR_TITLE_ID))
                    .setText(NEW_QUEST_NAME_DEFAULT);
            actionBar.getCustomView().findViewById(ACTION_BAR_ACCEPT_ID).setOnClickListener(this);
            actionBar.getCustomView().findViewById(ACTION_BAR_CANCEL_ID).setOnClickListener(this);
        }
    }

    public void addPointToList(PointItem pointItem){
        points.add(pointItem);

        if (points.size() > 1) {
//            mainDistance
//                    += GoogleMapManager.distanceBetweenPointsInt(points.get(START_POINT_INDEX).getPointPosition(),
//                    pointItem.getPointPosition());

            calculateAndAddDistance();
        }

        setMainDistance();
        ((ListAdapterAllPoints) ((ListView) findViewById(POINTS_LIST_ID)).getAdapter())
                .notifyDataSetChanged();
    }

    public void removePointFromList(PointItem pointItem){
        points.add(pointItem);

        ((ListAdapterAllPoints) ((ListView) findViewById(POINTS_LIST_ID)).getAdapter())
                .notifyDataSetChanged();
    }

    public List<PointItem> getPoints() {
        return points;
    }

    public PointItem getPoint(int position) {
        return points.get(position);
    }

    public void setPoints(List<PointItem> points) {
        this.points = points;

        ((ListAdapterAllPoints) ((ListView) findViewById(POINTS_LIST_ID)).getAdapter())
                .notifyDataSetChanged();
    }

    public void showHidePointsList(boolean show){
        if (show){
            findViewById(POINTS_DRAWER_ID).setVisibility(View.VISIBLE);

            RelativeLayout handleLayout = ((RelativeLayout) findViewById(HANDLE_LAYOUT_ID));
            ((TextView) handleLayout.findViewById(HANDLE_START_POINT_ID))
                    .setText(points.get(START_POINT_INDEX).getPointName());
            ((TextView) handleLayout.findViewById(HANDLE_FINISH_POINT_ID))
                    .setText(points.get(points.size() - 1).getPointName());

            setMainDistance();
        } else {
            findViewById(POINTS_LIST_ID).setVisibility(View.GONE);
        }
    }

    private void setMainDistance(){
        RelativeLayout handleLayout = ((RelativeLayout) findViewById(HANDLE_LAYOUT_ID));

        ((TextView) handleLayout.findViewById(HANDLE_DISTANCE_MAIN_ID))
                .setText(mainDistance + GoogleMapManager.METERS);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapManager = new GoogleMapManager(googleMap, this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case MOVE_TO_CURRENT_LOC_ID:
                googleMapManager.moveToMyPosition();
                break;
            case SWITCH_MAP_TYPE_ID:
                googleMapManager.switchMapType();
                break;
            case ACTION_BAR_ACCEPT_ID:
                break;
            case ACTION_BAR_CANCEL_ID:
                break;
            default:
                break;
        }
    }

    private void calculateAndAddDistance() {
        int size = points.size();

        mainDistance += GoogleMapManager.distanceBetweenPointsInt(
                points.get(size - 2).getPointPosition(),
                points.get(size - 1).getPointPosition());

        String distance = GoogleMapManager.distanceBetweenPointsString(
                points.get(size - 2).getPointPosition(),
                points.get(size - 1).getPointPosition());

        distances.add(distance);
    }
}

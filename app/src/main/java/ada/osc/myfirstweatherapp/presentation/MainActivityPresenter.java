package ada.osc.myfirstweatherapp.presentation;

import android.support.v4.app.FragmentManager;

import ada.osc.myfirstweatherapp.locationRepository.AllLocations;
import ada.osc.myfirstweatherapp.ui.adapter.CustomViewPagerFragmentAdapter;
import ada.osc.myfirstweatherapp.ui.main.MainActivityContract;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View mMainActivityView;
    private CustomViewPagerFragmentAdapter mAdapter;
    private AllLocations mAllLocations;

    public MainActivityPresenter(MainActivityContract.View mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
        mAllLocations = AllLocations.getInstance();

    }


    @Override
    public void setAdapter(FragmentManager supportFragmentManager) {
        this.mAdapter = new CustomViewPagerFragmentAdapter(supportFragmentManager);
        mAdapter.setAdapterData(mAllLocations.getAllLocations());
        mMainActivityView.setAdapterToViewPager(mAdapter);
    }

    @Override
    public void updateData() {
        mAdapter.updateData(mAllLocations.getAllLocations());
    }
}

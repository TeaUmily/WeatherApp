package ada.osc.myfirstweatherapp.ui.main;

import android.support.v4.app.FragmentManager;

import ada.osc.myfirstweatherapp.ui.adapter.CustomViewPagerFragmentAdapter;

public interface MainActivityContract {

    interface View {


        void setAdapterToViewPager(CustomViewPagerFragmentAdapter mAdapter);
    }


    interface Presenter {

        void setAdapter(FragmentManager supportFragmentManager);

        void updateData();
    }

}

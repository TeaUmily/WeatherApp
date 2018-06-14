package ada.osc.myfirstweatherapp.ui.addLocation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import ada.osc.myfirstweatherapp.pojo.LocationWrapper;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.presentation.AddNewLocationPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class AddLocationFragment extends Fragment implements NewLocationContract.View {


    @BindView(R.id.fragment_add_location_enter_city_edit_text) EditText mEnterLocationNameEditText;


    private NewLocationContract.Presenter mPresenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_location, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new AddNewLocationPresenter(App.getApiInteractor());
        mPresenter.setView(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void onLocationAlreadyExistsError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.location_already_exists_error_message));
    }

    @Override
    public void onEmptyStringRequestError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.empty_location_string_error_message));
    }


    @OnClick (R.id.fragment_add_location_button)
     void addNewLocationBtnClick() {
        LocationWrapper location = new LocationWrapper(mEnterLocationNameEditText.getText().toString());
        mPresenter.addNewLocation(location);
    }
}

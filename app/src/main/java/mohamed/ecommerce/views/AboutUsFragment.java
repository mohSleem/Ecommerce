package mohamed.ecommerce.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mohamed.ecommerce.R;
import mohamed.ecommerce.adapters.SettingsAdapter;
import mohamed.ecommerce.modules.Setting;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {
    private SettingsAdapter adapter;
    private RecyclerView recyclerView;


    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_about_us, container, false);
        recyclerView = convertView.findViewById(R.id.about_rc);
        setupRecycler();
        setupData();

        return convertView;
    }
    private void setupData() {
        int[] image = {R.drawable.ic_arrow_forward_black_24dp, R.drawable.ic_arrow_forward_black_24dp, R.drawable.ic_arrow_forward_black_24dp, R.drawable.ic_arrow_forward_black_24dp};
        String[] title = {getString(R.string.official_website), getString(R.string.privacy_policy), getString(R.string.refund_policy), getString(R.string.term_service)};
        List<Setting> list = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            list.add(new Setting(image[i], title[i]));
        }
        adapter.insertData(list);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        adapter = new SettingsAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


    }


}

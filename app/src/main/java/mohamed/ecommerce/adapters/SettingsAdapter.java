package mohamed.ecommerce.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mohamed.ecommerce.R;
import mohamed.ecommerce.modules.Setting;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.MyHolder>{
    private List<Setting> settingsList;
    private Context context;
    public SettingsAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View convertView = LayoutInflater.from(context).inflate(R.layout.settings_row, parent, false);
        return new MyHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Setting currentItem = settingsList.get(position);
        holder.title.setText(currentItem.getmSettingTitle());
        holder.titleImage.setImageResource(currentItem.getmButtonIcon());

    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    public void insertData(List<Setting> settingsList){
        this.settingsList = settingsList;

    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView titleImage;
        private TextView title;

        public MyHolder(View itemView) {
            super(itemView);
            titleImage = itemView.findViewById(R.id.title_btn);
            title = itemView.findViewById(R.id.titleView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    switch (position){
                        case 1:
//                            Intent i = new Intent(context, PrivacyActivity.class);
//                            context.startActivity(i);
                    }
                }
            });
        }
    }
}

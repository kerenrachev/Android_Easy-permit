package com.example.ands_finalproject_kerenrachev_318638129.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ands_finalproject_kerenrachev_318638129.R;
import com.example.ands_finalproject_kerenrachev_318638129.data.Application;
import com.example.ands_finalproject_kerenrachev_318638129.fragments.Fragment_permissions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ApplicationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static ApplicationsAdapter me;
    private AppCompatActivity appCompatActivity;
    private Context context;
    private ArrayList<Application> applications;
    private boolean isGrid = false;

    private ApplicationsAdapter(Context context) {
        this.context = context;
    }
    public static ApplicationsAdapter getMe() {
        return me;
    }

    public static ApplicationsAdapter initHelper(Context context) {
        if (me == null) {
            me = new ApplicationsAdapter(context);
        }
        return me;
    }

    public ApplicationsAdapter setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        return this;
    }

    public ApplicationsAdapter setApplications(ArrayList<Application> applications) {
        this.applications = applications;
        return this;
    }

    public ApplicationsAdapter setGrid(boolean grid) {
        isGrid = grid;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(!isGrid){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_apps_item, parent, false);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_apps_item_grid, parent, false);
        }

        ApplicationHolder applicationHolder = new ApplicationHolder(view);
        return applicationHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ApplicationHolder holder = (ApplicationHolder) viewHolder;
        Application application = getItem(position);

        holder.apps_TXT_name.setText(application.getApplicationName());
        holder.apps_IMG_icon.setImageDrawable(application.getApplicationIcon());
        if(!isGrid){
            holder.apps_TXT_package.setText(application.getApplicationPackage());
        }
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public Application getItem(int position) {
        return applications.get(position);
    }



    class ApplicationHolder extends RecyclerView.ViewHolder {
        private MaterialCardView app_card;

        private ImageView apps_IMG_icon;
        private MaterialTextView apps_TXT_name;
        private MaterialTextView apps_TXT_package;

        public ApplicationHolder(View itemView) {
            super(itemView);
            app_card = itemView.findViewById(R.id.app_card);
            apps_IMG_icon = itemView.findViewById(R.id.apps_IMG_icon);
            apps_TXT_name = itemView.findViewById(R.id.apps_TXT_name);
            if(!isGrid) apps_TXT_package = itemView.findViewById(R.id.apps_TXT_package);
            app_card.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    // Clicking on the card will open a new fragment of permissions, which will display all the app's granted permissions.
                    Fragment_permissions fragment_permissions = new Fragment_permissions( apps_TXT_name.getText().toString(),apps_IMG_icon.getDrawable(), applications.get(getAdapterPosition()).getPermissions(), 0, applications.get(getAdapterPosition()).getApplicationPackage());
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_permissions).commit();
                }
            });
        }

    }
}

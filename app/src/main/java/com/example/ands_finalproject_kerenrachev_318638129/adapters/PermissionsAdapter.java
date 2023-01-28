package com.example.ands_finalproject_kerenrachev_318638129.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ands_finalproject_kerenrachev_318638129.R;
import com.example.ands_finalproject_kerenrachev_318638129.data.Application;
import com.example.ands_finalproject_kerenrachev_318638129.data.Permission;
import com.example.ands_finalproject_kerenrachev_318638129.fragments.Fragment_apps;
import com.example.ands_finalproject_kerenrachev_318638129.fragments.Fragment_permissions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class PermissionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static PermissionsAdapter me;

    private Context context;
    private ArrayList<Permission> permissions;
    private AppCompatActivity appCompatActivity;

    private PermissionsAdapter(Context context) {
        this.context = context;
    }
    public static PermissionsAdapter getMe() {
        return me;
    }

    public static PermissionsAdapter initHelper(Context context) {
        if (me == null) {
            me = new PermissionsAdapter(context);
        }
        return me;
    }

    public PermissionsAdapter setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    public PermissionsAdapter setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_permissions_item, parent, false);
        PermissionHolder permissionHolder = new PermissionHolder(view);
        return permissionHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final PermissionHolder holder = (PermissionHolder) viewHolder;
        Permission permission = getItem(position);

        holder.permissions_TXT_name.setText(permission.getPermissionName().split("\\.")[2].replace('_', ' '));
    }

    @Override
    public int getItemCount() {
        return permissions.size();
    }

    public Permission getItem(int position) {
        return permissions.get(position);
    }



    class PermissionHolder extends RecyclerView.ViewHolder {

        private MaterialTextView permissions_TXT_name;
        private MaterialCardView card;
        public PermissionHolder(View itemView) {
            super(itemView);
            permissions_TXT_name = itemView.findViewById(R.id.permissions_TXT_name);
            card = itemView.findViewById(R.id.card);
            card.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    Fragment_apps fragment_apps = new Fragment_apps(true, permissions.get(getAdapterPosition()).getApplications(), permissions_TXT_name.getText().toString());
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_apps).commit();
                }
            });
        }

    }
}

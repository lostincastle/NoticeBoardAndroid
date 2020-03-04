package com.shishir.onlinenoticeboard.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.shishir.onlinenoticeboard.LoginActivity;
import com.shishir.onlinenoticeboard.R;
import com.shishir.onlinenoticeboard.StrictMode.StrictModeC;
import com.shishir.onlinenoticeboard.api.RetrofitApi;
import com.shishir.onlinenoticeboard.api.RetrofitInterface;
import com.shishir.onlinenoticeboard.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private ImageView imgprofile;
    private TextView pUsername, pEmail, pAddress, pMob;
    Context context;
    private Button buttonLogout;


    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        pUsername = root.findViewById(R.id.fragment_profile_userName);
        pEmail = root.findViewById(R.id.fragment_profile_email);
        pAddress = root.findViewById(R.id.fragment_profile_address);
        pMob = root.findViewById(R.id.fragment_profile_mobileNo);
        buttonLogout = root.findViewById(R.id.button_logout);


        context = getContext();
        final RetrofitInterface api = RetrofitApi.getInstance().create(RetrofitInterface.class);
        StrictModeC.StrictMode();


        final Call<UserModel> getUserProfiles = api.getMyProfile(RetrofitApi.token);
        getUserProfiles.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getContext(), "profile is loading", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    pUsername.setText(response.body().getUsername());
                    pEmail.setText(response.body().getEmail());
                    pMob.setText(response.body().getMobileNumber());
                    pAddress.setText(response.body().getPermanentAddress());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                RetrofitApi.token = "";
                getActivity().finish();
            }
        });
        return root;

    }

//    public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.UserProfileViewHolder>{
//        Context context;
//        List<UserModel> userModels;
//
//        public UserProfileAdapter(Context context, List<UserModel> userModels) {
//            this.context = context;
//            this.userModels = userModels;
//        }
//
//        @NonNull
//        @Override
//        public UserProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//          View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_profile,viewGroup,false);
//          return new UserProfileViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull UserProfileViewHolder userProfileViewHolder, int position) {
//            final UserModel model = userModels.get(position);
//            userProfileViewHolder.pUsername.setText(model.getUsername());
//            userProfileViewHolder.pEmail.setText(model.getEmail());
//            userProfileViewHolder.pMob.setText(model.getMobileNumber());
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }
//
//        public class UserProfileViewHolder extends RecyclerView.ViewHolder {
//            CircleImageView imgprofile;
//            TextView pUsername, pEmail, pAddress,pMob;
//
//
//            public UserProfileViewHolder(@NonNull View profileView) {
//                super(profileView);
//                imgprofile = profileView.findViewById(R.id.imgpro);
//                pUsername = profileView.findViewById(R.id.epusername);
//                pEmail = profileView.findViewById(R.id.epemail);
//                pAddress = profileView.findViewById(R.id.epaddress);
//                pMob = profileView.findViewById(R.id.epmobile);
//            }
//        }
//    }

}
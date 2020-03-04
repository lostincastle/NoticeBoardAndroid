package com.shishir.onlinenoticeboard.ui.comment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shishir.onlinenoticeboard.R;
import com.shishir.onlinenoticeboard.api.BLL;
import com.shishir.onlinenoticeboard.api.RetrofitApi;
import com.shishir.onlinenoticeboard.api.RetrofitInterface;
import com.shishir.onlinenoticeboard.model.CommentModel;
import com.shishir.onlinenoticeboard.model.NoticeModel;
import com.shishir.onlinenoticeboard.ui.home.HomeFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllNotice extends Fragment {


    RecyclerView recyclerView;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_comment, container, false);
        recyclerView = root.findViewById(R.id.recyclerview_allnotices);
        context  = getContext();


        LoadNotices();

        return root;
    }
    public void LoadNotices(){
        RetrofitInterface api = RetrofitApi.getInstance().create(RetrofitInterface.class);
        Call<List<NoticeModel>> listCall = api.getNotice(RetrofitApi.token);
        listCall.enqueue(new Callback<List<NoticeModel>>() {
            @Override
            public void onResponse(Call<List<NoticeModel>> call, Response<List<NoticeModel>> response) {
                if (!response.isSuccessful()){

                    Toast.makeText(getContext(), "Token has expired , login again", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<NoticeModel> modelList = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                NoticeAdapter adapter = new NoticeAdapter(getContext(),modelList);

                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<NoticeModel>> call, Throwable t) {

            }
        });
    }
    public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
        Context context;
        List<NoticeModel> NoticeModels;

        public NoticeAdapter(Context context, List<NoticeModel> noticeModels) {
            this.context = context;
            this.NoticeModels = noticeModels;
        }

        @NonNull
        @Override
        public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.noticedetail_viewholder, viewGroup, false);
            return new NoticeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final NoticeViewHolder noticeViewHolder, int i) {
            final NoticeModel model = NoticeModels.get(i);
            noticeViewHolder.title.setText(model.getTitle());
            noticeViewHolder.description.setText(model.getDescription());
            noticeViewHolder.postedby.setText(model.getPostedby().getUsername());
            noticeViewHolder.buttonComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    StartComment(noticeViewHolder.textViewId.getText().toString(),
//                            noticeViewHolder.title.getText().toString(),
//                            noticeViewHolder.description.getText().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return NoticeModels.size();
        }

        public void StartComment(String PostID, String title, String post){
            AllNotice commentFragment= new AllNotice();
            Bundle bundle = new Bundle();
            bundle.putString("postid",PostID);
            bundle.putString("title",title);
            bundle.putString("post",post);
            commentFragment.setArguments(bundle);
//            ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.nav_host_fragment,commentFragment)
//                    .commit();
        }

        public class NoticeViewHolder extends RecyclerView.ViewHolder {

            CircleImageView imgview;
            TextView title, description, postedby;
            ImageButton buttonComment;

            public NoticeViewHolder(@NonNull View noticeView) {
                super(noticeView);
                // imgview = noticeView.findViewById(R.id.imgview);;
                title = noticeView.findViewById(R.id.postview_title);
                description = noticeView.findViewById(R.id.postview_description);
                postedby = noticeView.findViewById(R.id.postview_postedby);
                buttonComment = noticeView.findViewById(R.id.postview_button_comment);
            }
        }
    }
}
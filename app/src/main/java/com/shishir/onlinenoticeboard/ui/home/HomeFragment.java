package com.shishir.onlinenoticeboard.ui.home;

import android.content.Context;

import android.os.Bundle;
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
import com.shishir.onlinenoticeboard.api.RetrofitApi;
import com.shishir.onlinenoticeboard.api.RetrofitInterface;
import com.shishir.onlinenoticeboard.model.NoticeModel;
import com.shishir.onlinenoticeboard.ui.comment.AllNotice;


import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    Context context;
    RecyclerView recyclerView;

    EditText textTitle, textContent;
    Button buttonPost;
    private  TextView Title,Description,Comment;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.dashboard_container);
        context = getContext();

        textTitle = root.findViewById(R.id.text_title);
        textContent = root.findViewById(R.id.text_content);

        buttonPost = root.findViewById(R.id.button_post);

        LoadNotices();
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<NoticeModel> PostNotice = RetrofitApi.getInstance().create(RetrofitInterface.class)
                        .postNotice(RetrofitApi.token,textTitle.getText().toString(),textContent.getText().toString());
                PostNotice.enqueue(new Callback<NoticeModel>() {
                    @Override
                    public void onResponse(Call<NoticeModel> call, Response<NoticeModel> response) {
                        if(response.code() == 200){
                            textTitle.setText("");
                            textContent.setText("");
                            Toast.makeText(context,"getNotice has been Posted", Toast.LENGTH_SHORT).show();
                            LoadNotices();
                        }
                        else {
                            Toast.makeText(context,"getNotice post failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoticeModel> call, Throwable t) {
                        Toast.makeText(context,"getNotice post failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        return root;
    }

    public void LoadNotices(){
        RetrofitInterface api = RetrofitApi.getInstance().create(RetrofitInterface.class);
        Call<List<NoticeModel>> listCall = api.getMyNotice(RetrofitApi.token);
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
            ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment,commentFragment)
                    .commit();
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
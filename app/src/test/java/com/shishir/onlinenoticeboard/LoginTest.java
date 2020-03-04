package com.shishir.onlinenoticeboard;

import com.shishir.onlinenoticeboard.api.RetrofitInterface;
import com.shishir.onlinenoticeboard.model.UserModel;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.when;

public class LoginTest {
    @Test
    public void LoginApiTest() {
        RetrofitInterface apiClient = Mockito.mock(RetrofitInterface.class);
        final Call<UserModel> mockedLogin = Mockito.mock(Call.class);
        when(apiClient.Login("admin","admin")).thenReturn(mockedLogin);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<UserModel> callback = invocation.getArgument(0,Callback.class);
                callback.onResponse(mockedLogin, Response.success(new UserModel()));
                return null;
            }
        });
    }
}

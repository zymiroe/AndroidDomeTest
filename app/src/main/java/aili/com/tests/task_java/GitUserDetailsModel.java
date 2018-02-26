package aili.com.tests.task_java;

import java.io.IOException;

import aili.com.tests.task_java.bean.TaskGitBean;
import aili.com.tests.task_java.http.ApiService;
import aili.com.tests.task_java.http.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 获取git用户信息 model
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-04 11:24
 */

public class GitUserDetailsModel {


    /**
     * 获取 git 用户信息  api
     *
     * @param listener
     */
    public void getGitDetails(String path, final GitUserDetailContract.onGetGitUserDetailsListener listener) {
        ApiService apiService = RetrofitUtils.getInstance().createApi(ApiService.class);
        Call<TaskGitBean> call = apiService.getGitUserInfo(path);
        call.enqueue(new Callback<TaskGitBean>() {
            @Override
            public void onResponse(Call<TaskGitBean> call, Response<TaskGitBean> response) {
                if (response.code() == 200) {
                    System.out.println(response.body().toString());
                    if (listener != null && response != null) {
                        listener.getGitUserDetailSuccess(response.body());
                    }
                } else {
                    listener.getGitUserDetailFailure(response);
                }
            }

            @Override
            public void onFailure(Call<TaskGitBean> call, Throwable t) {
                try {
                    listener.getGitUserDetailFailure(call.execute());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

package aili.com.tests.task_java.http;

import aili.com.tests.task_java.bean.TaskGitBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * http api
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-04 11:17
 */

public interface ApiService {

    /**
     * 获取用户 git 信息
     *
     * @param user
     * @return
     */
    @GET("users/{user}")
    Call<TaskGitBean> getGitUserInfo(@Path("user") String user);
}

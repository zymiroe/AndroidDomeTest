package aili.com.tests.task_java;

import com.androiddometest.BasePresenter;
import com.androiddometest.BaseView;

import aili.com.tests.task_java.bean.TaskGitBean;
import retrofit2.Response;

/**
 * 获取git 用户信息契约类
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-04 11:27
 */

public class GitUserDetailContract {

    public interface Presenter extends BasePresenter {
        void getGitUserDetails(String userKey);

    }

    public interface View extends BaseView<Presenter> {
        void showGitUserDetails(TaskGitBean gitBean);

        void showGitUserDetailsFailure();

        void showEmpty();
    }


    public interface onGetGitUserDetailsListener {

        void getGitUserDetailSuccess(TaskGitBean gitBean);

        void getGitUserDetailFailure(Response<TaskGitBean> response);
    }
}

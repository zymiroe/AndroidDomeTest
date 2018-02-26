package aili.com.tests.task_java;

import java.io.File;

import aili.com.tests.task_java.bean.TaskGitBean;
import aili.com.tests.util.FileUtil;
import retrofit2.Response;

/**
 * 获取 git 用户详情 presenter
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-05 10:47
 */

public class GitUserDetailsPresenter implements GitUserDetailContract.Presenter, GitUserDetailContract.onGetGitUserDetailsListener {

    private GitUserDetailContract.View mView;
    private GitUserDetailsModel mModel;

    public GitUserDetailsPresenter(GitUserDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {
        mModel = new GitUserDetailsModel();
    }

    @Override
    public void getGitUserDetails(String userKey) {
        mModel.getGitDetails(userKey, this);
    }

    @Override
    public void getGitUserDetailSuccess(TaskGitBean gitBean) {
        if (mView != null) {
            if (gitBean != null) {
                mView.showGitUserDetails(gitBean);
            } else {
                mView.showEmpty();
            }
        }
    }

    @Override
    public void getGitUserDetailFailure(Response<TaskGitBean> response) {
        if (mView != null) {
            mView.showGitUserDetailsFailure();
        }
    }

    public int computeNum(int num1, int num2) {
        if (num1 >= num2) {
            return num1;
        } else {
            return num2;
        }
    }

    /**
     * 获取 用户名字 （私有方法 单测 有返回值）
     */
    private String getGitUserName(TaskGitBean gitBean) {
        GitUserHelp gitUserHelp = new GitUserHelp();
        String tempName = null;
        if (gitBean != null) {
            tempName = gitUserHelp.getUserName(gitBean);
        } else {
            new NullPointerException("TaskGitBean is null");
        }
        return tempName;
    }


    public String setUserName(TaskGitBean userName) {
        return getGitUserName(userName);
    }

    /**
     * 内部new出的对象（局部对象）
     * 如果我们再方法内部 new一个file，而要测试这样的代码,Mockito 是无法完成的
     * 需要使用 PowerMock 框架来测试
     *
     * @return
     */
    public boolean fileIsExists(String path) {
        File file = new File(path);
        return file.exists();
    }


    /**
     * 局部变量的 void 方法
     */
    public void saveGitUser(TaskGitBean gitBean) {
        GitUserHelp gitUserHelp = new GitUserHelp();
        gitUserHelp.svaUserName(gitBean);
    }


    public String readFile(String path, String charsetName) {
        return FileUtil.readFile(path, charsetName).toString();
    }


    public boolean deleteFile(File file) {
        try {
            FileUtil.deleteAllFile(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String getUserAddress(TaskGitBean gitBean) {
        GitUserHelp gitUserHelp = new GitUserHelp();
        String userAddress = null;
        try {
            if (gitBean != null) {
                userAddress = gitUserHelp.getUserAddress(gitBean);
            } else {
                userAddress = "TaskGitBean 是空对象";
            }
        } catch (Exception e) {
            userAddress = "程序异常";
        }
        return userAddress;
    }
}

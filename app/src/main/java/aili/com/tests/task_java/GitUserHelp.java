package aili.com.tests.task_java;

import aili.com.tests.task_java.bean.TaskGitBean;

/**
 * git user帮助类  （为了单测添加的）
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-06 11:33
 */

public class GitUserHelp {

    public String getUserName(TaskGitBean taskGitBean) {
        return taskGitBean.getLogin();
    }


    public void svaUserName(TaskGitBean taskGitBean) {

    }


    public String getUserAddress(TaskGitBean taskGitBean) throws Exception{
        return taskGitBean.getLocation();
    }
}


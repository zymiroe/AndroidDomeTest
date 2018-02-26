package aili.com.tests.robolectric.task_java;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import aili.com.tests.BuildConfig;
import aili.com.tests.R;
import aili.com.tests.base.BaseTestTestRunner;
import aili.com.tests.task_java.GitUserDetailsActivity;
import aili.com.tests.task_java.bean.TaskGitBean;
import aili.com.tests.task_kotlin.task.TaskMainActivity;

/**
 * 获取git 用户信息activity 单测用例
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-05 11:15
 */
@RunWith(BaseTestTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class GitUserDetailsActivityTest {
    private GitUserDetailsActivity gitUserDetailsActivity;

    @Before
    public void setUp() throws Exception {
        // 默认会调用Activity的生命周期: onCreate->onStart->onResume
        gitUserDetailsActivity = Robolectric.setupActivity(GitUserDetailsActivity.class);
    }


    /**
     * 创建 GitUserDetailsActivity 验证它是非空的
     */
    @Test
    public void activityNotNullTest() {
        Assert.assertNotNull(gitUserDetailsActivity);
    }

    /**
     * GitUserDetailsActivity 页面跳转测试用例
     */
    @Test
    public void activityIntentTest() {
        // 模拟点击跳转
        Button btnNexActivity = gitUserDetailsActivity.findViewById(R.id.btn_go_kotlin);
        btnNexActivity.performClick();
        //获取我们 GitUserDetailsActivity中要跳转的页面意图
        Intent intent = ShadowApplication.getInstance().getNextStartedActivity();
        //期望意图
        Intent expected = new Intent(gitUserDetailsActivity, TaskMainActivity.class);
        //比较两个组件是否相等
        Assert.assertEquals(expected.getComponent(), intent.getComponent());
    }

    /**
     * 测试获取服务器数据和预想的显示是否正确
     */
    @Test
    public void showGitUserDetailsTest() {
        TextView tvGitUser = gitUserDetailsActivity.findViewById(R.id.tv_git_user_detail);
        TaskGitBean gitBean = new TaskGitBean();
        gitBean.setLogin("yexiaochai");
        gitBean.setCompany("aili");
        gitBean.setCreated_at("2013年3月2日");
        gitBean.setLocation("上海");
        gitBean.setUrl("http://com.github.com/yexiaochai");
        gitUserDetailsActivity.showGitUserDetails(gitBean);
        String userinfo = "git用户名:yexiaochai" + "\n" +
                "公司:aili" + "\n" +
                "地址:上海" + "\n" +
                "git地址:http://com.github.com/yexiaochai" + "\n" +
                "git创建时间:2013年3月2日";
        //判断mTextView值是否是onStart
        Assert.assertEquals(userinfo, tvGitUser.getText().toString());
    }


}
package aili.com.tests.task_java;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import aili.com.tests.R;
import aili.com.tests.task_java.bean.TaskGitBean;
import aili.com.tests.task_kotlin.task.TaskMainActivity;


/**
 * 获取git 用户信息 页面
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-01-30 18:21
 */

public class GitUserDetailsActivity extends AppCompatActivity implements View.OnClickListener, GitUserDetailContract.View {
    private Button btnGitUser, btnGoKotlin;
    private TextView tvGitUserDetail;

    private GitUserDetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_user_details_activity);
        initView();
        initData();
    }

    private void initView() {
        btnGitUser = findViewById(R.id.btn_get_user);
        tvGitUserDetail = findViewById(R.id.tv_git_user_detail);
        btnGoKotlin = findViewById(R.id.btn_go_kotlin);
        btnGoKotlin.setOnClickListener(this);
        btnGitUser.setOnClickListener(this);
    }

    private void initData() {
        mPresenter = new GitUserDetailsPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_user:
                if (mPresenter != null) {
                    mPresenter.getGitUserDetails("zymiroe");
                }
                break;
            case R.id.btn_go_kotlin:
                goIntentKotlinActivity();
                break;
            default:
                break;
        }
    }

    private void goIntentKotlinActivity() {
        Intent intent = new Intent(this, TaskMainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(GitUserDetailContract.Presenter presenter) {

    }

    @Override
    public void showGitUserDetails(TaskGitBean gitBean) {
        tvGitUserDetail.setText("git用户名:" + gitBean.getLogin() + "\n" +
                "公司:" + gitBean.getCompany() + "\n" +
                "地址:" + gitBean.getLocation() + "\n" +
                "git地址:" + gitBean.getUrl() + "\n" +
                "git创建时间:" + gitBean.getCreated_at());
    }

    @Override
    public void showGitUserDetailsFailure() {
        tvGitUserDetail.setText("不好意思请求出错了啦");
    }

    @Override
    public void showEmpty() {
        tvGitUserDetail.setText("这是空数据啊");
    }
}

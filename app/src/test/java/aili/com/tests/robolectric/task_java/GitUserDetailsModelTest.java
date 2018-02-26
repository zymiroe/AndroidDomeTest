package aili.com.tests.robolectric.task_java;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import aili.com.tests.task_java.GitUserDetailContract;
import aili.com.tests.task_java.GitUserDetailsModel;
import aili.com.tests.task_java.bean.TaskGitBean;
import retrofit2.Response;

/**
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-04 11:44
 * <p>
 * 测试用例
 * 1.正确 api地址和参数，获取服务器数据，数据是否正确
 * 2.错误的api地址和参数，向用户提示服务器错误信息
 * 3.没任何数据时，给用户相关提示
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*") //屏蔽ssl
public class GitUserDetailsModelTest {

    @Captor
    private ArgumentCaptor<GitUserDetailContract.onGetGitUserDetailsListener> mListener;
    private GitUserDetailsModel mModel;
    private CountDownLatch latch;

    @Before
    public void setUp() throws Exception {
        mModel = new GitUserDetailsModel();
        latch = new CountDownLatch(1);
    }

    TaskGitBean mTaskGitBean;

    /***
     * 正确的api地址和参数 验证数据是否正确
     * @throws Exception
     */
    @Test
    public void getGitUserDetailsApiTestSuccess() throws Exception {
        mModel.getGitDetails("zymiroe", new GitUserDetailContract.onGetGitUserDetailsListener() {
            @Override
            public void getGitUserDetailSuccess(TaskGitBean gitBean) {
                mTaskGitBean = gitBean;
                latch.countDown();
            }

            @Override
            public void getGitUserDetailFailure(Response<TaskGitBean> response) {
                latch.countDown();
            }
        });
        latch.await(4, TimeUnit.SECONDS);
        Assert.assertEquals("zymiroe", mTaskGitBean.getLogin());
    }

    Response<TaskGitBean> mResponse;

    /***
     * 错误的api地址和参数 验证服务器返回错误
     * @throws Exception
     */
    @Test
    public void getUserDetailsApiFailure() throws Exception {
        mModel.getGitDetails("zymiroe", new GitUserDetailContract.onGetGitUserDetailsListener() {
            @Override
            public void getGitUserDetailSuccess(TaskGitBean gitBean) {

            }

            @Override
            public void getGitUserDetailFailure(Response<TaskGitBean> response) {
                mResponse = response;

            }
        });
        latch.await(4, TimeUnit.SECONDS);
        Assert.assertNotNull(mResponse);
        Assert.assertEquals(404, mResponse.code());
    }

}
package aili.com.tests.robolectric.http;

import android.os.Build;
import android.util.Log;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aili.com.tests.BuildConfig;
import aili.com.tests.base.BaseTestTestRunner;
import aili.com.tests.task_java.bean.TaskGitBean;
import aili.com.tests.task_java.http.ApiService;
import aili.com.tests.task_java.http.RetrofitUtils;
import aili.com.tests.util.FileUtil;
import okhttp3.Headers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;

/**
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-05 16:10
 */
@RunWith(BaseTestTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class gitUserApiTest {
    private static final String TAG = "gitUserApiTest";

    private static final String JSON_ROOT_PATH = "/json/";
    private String gitUserJson;
    private ApiService mApiService;

    @Before
    public void setup() {
        mApiService = RetrofitUtils.getInstance().createApi(ApiService.class);
    }


    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        String jsonPath = getClass().getResource(JSON_ROOT_PATH).toURI().getPath();
        gitUserJson = FileUtil.readFile(jsonPath + "git_user.json", "UTF-8").toString();
    }

    /**
     * 测试模拟一次请求响应
     *
     * @throws Exception
     */
//    @Test
    public void gitUserApiTestSuccess() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(gitUserJson));
        mockWebServer.start();
        Call<TaskGitBean> beanCall = mApiService.getGitUserInfo("zymiroe");
        TaskGitBean gitBean = beanCall.execute().body();

        Assert.assertNotNull(gitBean);
        Assert.assertEquals("zymiroe", gitBean.getLogin());
        Assert.assertEquals("GET", mockWebServer.takeRequest().getMethod());
    }


//    @Test
    public void defaultMockResponse() {
        MockResponse response = new MockResponse();
        Assert.assertEquals(Arrays.asList("Content-Length: 0"), headersToList(response));
        Assert.assertEquals("HTTP/1.1 200 OK", response.getStatus());

        Log.i(TAG, response.getStatus());
    }

    private List<String> headersToList(MockResponse response) {
        Headers headers = response.getHeaders();
        int size = headers.size();
        List<String> headerList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            headerList.add(headers.name(i) + ": " + headers.value(i));
        }
        return headerList;
    }

    @After
    public void close() throws Exception {

    }

}

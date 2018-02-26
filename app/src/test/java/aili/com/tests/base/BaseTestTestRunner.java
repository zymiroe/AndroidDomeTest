package aili.com.tests.base;

import org.junit.runners.model.InitializationError;
import org.robolectric.RoboSettings;
import org.robolectric.RobolectricTestRunner;

/**
 * 因为我们再使用Robolectric 是需要下载一些依赖库，而这些库是在国外服务器
 * 所有我们要修改仓库的地址来解决下载依赖库问题。我们将仓库的地址改为阿里云的仓库
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-03 14:24
 */
public class BaseTestTestRunner extends RobolectricTestRunner {
    private static final String MAVEN_REPOSITORY_ID = "alimaven";
    private static final String MAVEN_REPOSITORY_URL = "http://maven.aliyun.com/nexus/content/groups/public/";

    /**
     * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file
     * and res directory by default. Use the {@link Config} annotation to configure.
     *
     * @param testClass the test class to be run
     * @throws InitializationError if junit says so
     */
    public BaseTestTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        RoboSettings.setMavenRepositoryId(MAVEN_REPOSITORY_ID);
        RoboSettings.setMavenRepositoryUrl(MAVEN_REPOSITORY_URL);
    }
}

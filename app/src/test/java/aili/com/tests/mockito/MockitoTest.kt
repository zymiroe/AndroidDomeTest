package aili.com.tests.mockito

import aili.com.tests.task_java.bean.TaskGitBean
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner


/**
 * 普通方法 Mock
 * @author yexiaochai
 * @date 2018-02-05 16:55
 * @version V 1.0
 */

/**
 * 表示使用什么运行器执行
 * 如果不使用 @RunWith注解器表明使用注解去，需要在 @Before 方法中使用  MockitoAnnotations.initMocks(this); 初始化
 */
@RunWith(MockitoJUnitRunner::class)
class MockitoTest {


    /**
     * 项目中各个模块，各个类之间是有相互依赖的，而在单元测试的时候，我们其实只关心被测试的单元，
     * 对于那些依赖的单元测试是不关心的，因此我们需要屏蔽这些外部依赖的类，使用Mock 模拟一个真实
     * 实体对象。
     * mock 是不实现任何逻辑，它们只是提供一种测试能够控制仿照类的所有业务逻辑方法行为的方法的空壳。
     *
     * 那mock出的对象和new出的区别在于？
     * 1. mock 出的对象属性为空或者是初始化值，具体取决于对象属性类型是原始类型还是包装类型
     * 2. mock 对如果没有被打桩，默认情况下会根据具体的放回值类型返回是 null，0，[] 等等
     * 比如 String /Object 返回null
     *     list/map  返回 []
     *     int 返回 0
     */
    @Mock
    private lateinit var mGitBean: TaskGitBean


    /**
     * 通过 @Rule和@Mock 共同 mock 一个对象，无需 @RunWith 注解器和 MockitoAnnotations的初始化
     *
     *  JUnit Rule 的作用类似于@Before、@After，是用来在每个测试方法的执行前后执行一些代码的一个方法。
     *  区别是 @Before、@After只能作用与一个类，比如如果在MockitoTest 类中，添加 @Before，那么它只能
     *  作用与MockitoTest类，而如果 @Before注解的方法中的代码需要在多个类中使用，它们就无法完成。而使用
     *  Rule注解可以帮助我们完成，我们可以使用自定义的Rule,继承TestRule 即可
     *
     */
    @Rule
    var mockitoRule = MockitoJUnit.rule()


    /** 第一种方法 Mockito.mock()方法 模拟 一个对象
     *
     * 使用mock 模拟TaskGitBean 对象
     */
    @Test
    fun testIsNotNull() {
        val gitBean = Mockito.mock(TaskGitBean::class.java)

        assertNotNull(gitBean)
    }


    /**
     * 第二种方法 使用mock 运行器 模拟一个对象
     *
     */
    @Test
    fun testIsNotNullRunner() {
        assertNotNull(mGitBean)
    }

}
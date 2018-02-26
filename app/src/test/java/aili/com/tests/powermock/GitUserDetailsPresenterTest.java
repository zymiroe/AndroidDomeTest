package aili.com.tests.powermock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

import aili.com.tests.task_java.GitUserDetailContract;
import aili.com.tests.task_java.GitUserDetailsPresenter;
import aili.com.tests.task_java.GitUserHelp;
import aili.com.tests.task_java.bean.TaskGitBean;
import aili.com.tests.util.FileUtil;

/**
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-06 10:48
 */


/**
 * PowerMock 专门用来针对一些特殊测试需求 ，比如 private方法，static 方法，final 类方法等等
 *
 * PowerMock使用的简单教程
 *
 * 1.如果我们使用 PowerMock的测试类，需要在测试类中添加 @RunWith(PowerMockRunner.class)，告诉运行期使用什么运行器去运行
 * 2.编写单元测试类，对类中的方法进行写单元测试用例
 * 3.如模拟类中的方法 ，方法返回 。首先对测试类（接口），进行mock ，然后录制相关行为（打桩）。假如要测试的类为A类
 *    1）通过 PowerMock mock（模拟）一个虚拟对象
 *     A a = PowerMockito.mock(A.class);
 *    2)为 mock 出的对象打桩
 *    //告诉PowerMockito 执行到A类中的method 方法是返回 valueToReturn值
 *    PowerMockito.when(a.method(Params…)).thenReturn(valueToReturn)
 *    3) 调用A类在的方法并获取返回值，进行断言,如果值相等 表示单测用例通过
 *    String value = a.method(Params…);
 *    Assert.assertEquals(value, valueToReturn);
 *
 *    "打桩" 语法:
 *     when(class.method()).thenReturn(value) //执行到class.method() 时返回value值
 *
 *
 *   以上是对一个有返回值方法的，单测教程和步骤。具体案例和场景
 *   参考 @GitUserDetailsPresenterTest类中，测试用例代码
 *
 *   我们是否不通过mock获取对象，而和正常写做项目那样实例化一个对象？答案是肯定可以的？那为什么还需要 mock 概念？
 *   在写单元测试的过程中，对于那些不容易构造或者是不容易获取到的对象我们改怎么办了？这时就需要创建一个虚拟对象来替代
 *   我们那些不容易构造和不容易获取的对象。
 *
 *    适合mock的场景有：
 *    1.真实对象很难被创建和获取到（比如 Context ，一些容器等）
 *    2.真实对象的某些行为很难触发(比 如网络错误)
 *    3.真实对象实际上不存在 （比如 依赖其他外部对象，多依赖等，硬件系统）
 *    4.异常逻辑的处理 （比如 一些异常的逻辑往往在正常测试中是很难触发的，通过Mock可以人为的控制触发异常逻辑）
 *    5.UI （比如 UI是很多时候都是用户行为触发事件，后展示信息，对这里UI mock，收益更大）
 *    6.第三方API （比如依赖第三方库的api 等）
 *    7.等等。。。。。
 *    以上这些场景都是非常适合通过mock 去创建虚拟对象来特带真实对象
 *
 *
 *    Mock 的两个误区：
 *    1.PowerMockito.mock()方法，并不是mock整个类，它是mock出属于这个类的一个对象，并且返回这个mock 对象。
 *    2.mock 出的对象是不会自动替换掉正式的代码中对象
 *
 *
 *   在单测用例编写的过程，需要对框架的熟练掌握，才能提高我们写单测用例效率，简单的列一下PowerMock框架需要掌握知识点
 *
 *   1.了解 PowerMock注解@RunWith与@PrepareForTest 意思和使用？
 *   2.如何通过PowerMockito.mock() 创建虚拟对象，并为其录制一段行为（打桩什么意思？）？
 *   3.Do...when...then  语法意义？
 *   4.普通的方法如何写单元测试用例 (有返回值和返回值（void）类型方法)？
 *   5.private 方法如何写单元测试用例 (有返回值和返回值（void）类型方法)？
 *   7.static 方法如何写单元测试用例(有返回值和返回值（void）类型方法)？
 *   8.final 修饰的类或方法如何写单元测试用例(有返回值和返回值（void）类型方法)？
 *   9.带有参数的构造函数，如何mock 这样的类？
 *   10.如何给方法中私有变量复制并写单测？
 *   11.PowerMockito.spy() 进行部分模拟,mock 和 spy 区别？
 *   12.如何对单测的方法做断言？
 *   13.如何验证单测的方法是否被调用,以及Verifying中其余几个Api的使用？
 *   14.如何模拟异常？
 *   15.如何做参数匹配？
 *   16.PowerMockito.doNothing与PowerMockito.doThrow如何使用？
 *   17.ArgumentsMatcher 使用场景？
 *   18.Answer接口的使用？以及参数匹配？
 *   19.什么是mock 测试，什么是真实测试?
 *  其实还有很多的API，就不一个一个列举。掌握以上知识点，你的单测写起来也就行如流水了。
 *
 */

@RunWith(PowerMockRunner.class)
// 声明需要测试  静态，final，私有方法，内部对象 的类， 可以声明多个，用逗号隔开
@PrepareForTest({FileUtil.class, GitUserDetailsPresenter.class})
public class GitUserDetailsPresenterTest {
    private GitUserDetailContract.View mView;
    private GitUserDetailsPresenter presenter;


    @Before
    public void setUp() {
        mView = PowerMockito.mock(GitUserDetailContract.View.class);
        presenter = new GitUserDetailsPresenter(mView);
    }


    /**
     * 针对 GitUserDetailsPresenter中getUserAddress()方法 重点说下单测用例覆盖率
     *
     * 提前说下结论： 假设测试类中只有三个方法
     *             只测试testGetUserAddress方法成功，那么这个类的测试覆盖率只有35%左右
     *             那么如果我测试testGetUserAddress方法失败，这个类的测试覆盖率只有70%左右
     *             你可能在想为什么会是这样的。 因为我们方法getUserAddress 方法中对异常做了捕获处理
     *             换句话说，如果想要让你的这个类测试覆盖率100%，测试代码就必须对异常处理写测试用例。
     **
     *  getUserAddress() 方法中，涉及到方法内部 实例化对象问题，在后面都详细的介绍如果测试
     *
     */

    /**
     * 测试 GetUserAddress 方法成功 单测用例类
     * @throws Exception
     */
    @Test
    public void testGetUserAddress() throws Exception{
      GitUserHelp userHelp = PowerMockito.mock(GitUserHelp.class);
      TaskGitBean gitBean = new TaskGitBean();
      gitBean.setLocation("上海");
      PowerMockito.when(userHelp.getUserAddress(gitBean)).thenReturn("上海");
      //这里powerMock开始发挥作用：当GitUserHelp.class被实例化的时候，强制使用模拟对象GitUserHelp代替代码中被实例化出来的对象。
      PowerMockito.whenNew(GitUserHelp.class).withNoArguments().thenReturn(userHelp);
      Assert.assertEquals(presenter.getUserAddress(gitBean),"上海");

    }

    /***
     * 测试 TaskGitBean 对象是空的时候单测用例
     *
     * @throws Exception
     */
    @Test
    public void testGetUserAddressFailure() throws Exception{
        GitUserHelp userHelp = PowerMockito.mock(GitUserHelp.class);
        //Stub虚拟对象的行为，即当调用模拟对象的add方法时，抛出异常。到这里使用的都是Mockito的功能。
        PowerMockito.when(userHelp.getUserAddress((TaskGitBean)Mockito.any())).thenThrow(new Exception());
        PowerMockito.whenNew(GitUserHelp.class).withNoArguments().thenReturn(userHelp);
        //断言 异常处理中的语句 userAddress = "程序异常"语句被执行了
        Assert.assertEquals(presenter.getUserAddress(new TaskGitBean()),"程序异常");
    }

    /***
     * 测试 GetUserAddress 方法异常的时候单测用例
     * @throws Exception
     */
    @Test
    public void testGetUserAddressException() throws Exception{
        GitUserHelp userHelp = PowerMockito.mock(GitUserHelp.class);

        PowerMockito.when(userHelp.getUserAddress((TaskGitBean)Mockito.any())).thenReturn("TaskGitBean 对象是空的");
        PowerMockito.whenNew(GitUserHelp.class).withNoArguments().thenReturn(userHelp);
        //断言 异常处理中的语句 userAddress = "程序异常"语句被执行了
        Assert.assertEquals(presenter.getUserAddress(new TaskGitBean()),"TaskGitBean 对象是空的");
    }



    /**
     * 场景：假设A类中有个公共方法，有返回值
     *
     * 主要比较 mock 的对象和 new 出的对象区别
     */
    @Test
    public void testComputeNum() throws Exception{
        // 通过 mock 创建GitUserDetailsPresenter虚拟对象
        GitUserDetailsPresenter mockPresenter = PowerMockito.mock(GitUserDetailsPresenter.class);
        //打桩
        PowerMockito.when(mockPresenter.computeNum(1,2)).thenReturn(2);
        //断言
        int value = mockPresenter.computeNum(3,2);
        Assert.assertEquals(value,2);

        /**------------------------------------------------------**/
        //通过真实的实体对象 去测试类中方法
//        int value2 = presenter.computeNum(4,3);
        //断言
//        Assert.assertEquals(value2,4);
    }




    /**
     *  场景：假设A类中有个公共方法，无返回值类型
     */
    @Test
    public void testGetGitUserDetailSuccess() {
        TaskGitBean gitBean = new TaskGitBean();
        //录入它的行为，告诉PowerMockito 执行到 mView接口的showGitUserDetails 方法时，什么也不做
        PowerMockito.doNothing().when(mView).showGitUserDetails(gitBean);
        presenter.getGitUserDetailSuccess(gitBean);

        //这里存在一个疑问？如何知道我 mView 中 showGitUserDetails 方法被调用了？
        //可以使用 Mockito中 verify api, 断言只有该方法被顺利的调用一次,我们就证明该方法单测通过
        Mockito.verify(mView).showGitUserDetails(gitBean);

        // 如过我们 TaskGitBean 不new，将是一个null ，这个时候跑单测就会失败，因为showGitUserDetails没有被执行
        // 而是执行了mView.showEmpty() 方法 ，因此view.showGitUserDetails() 断言条件就会没有成立
    }


    /**
     * 通过 mock去创建一个对象
     */
    @Test()
    public void testMockToMockObject() {
        //通过 mock 创建一个list
        ArrayList<String> mockList = PowerMockito.mock(ArrayList.class);
        mockList.add("test");
        //断言 mockList.ddd("test")重来没被外部调用 而实际是调用了，所以报错
        //Mockito.verify(mockList,Mockito.never()).add("test");

    }

    /**
     * 通过 spy去创建一个对象
     */
    @Test
    public void testSpyToMockObject() {
        //通过 spy 创建一个list
        ArrayList<String> spyList = PowerMockito.spy(new ArrayList<String>());
        spyList.add("test");
        //检查spyList.add 方法是否被调用
        Mockito.verify(spyList).add("test");
        Assert.assertEquals("test", spyList.get(0));
    }


    /**
     * 场景：假设A类中一个私有方法
     * <p>
     * 以下为测试私有方法的便捷办法
     * <p>
     * 私有方法测试,可用spy。注意：通过 mock 一样可测试私有方法
     * spy：它能够 mock 一个对象，并且只 mock 个别方法的行为，保留对某些方法原始的业务逻辑
     * <p>
     * mock 和 spy 区别
     * <p>
     * 1.用mock生成的类, 里面所有方法都不是真实的方法，如果有返回值的，都返回null
     * 用spy生成的类，所有方法都是真实存在的，返回的值和真实方法执行后返回值一样，具有和原始对象相同的行为。
     * 2.mock 出的对象是全程模拟，而spy 是部分模拟
     * <p>
     * 注意：下面的是"测试"私有方法，不是mock (Api:Whitebox.invokeMethod())
     */
    @Test
    public void testPrivateMethodGitUserNameOne() {
        //在测试 GitUserDetailsPresenter 中 getGitUserName的时候，因为涉及到私有方法，所以我们需要通过PowerMockito.spy api
        GitUserDetailsPresenter privateMethodP = PowerMockito.spy(new GitUserDetailsPresenter(mView));
        try {
            TaskGitBean gitBean = new TaskGitBean();
            gitBean.setLogin("用户");
            //为我们创建一个 mock 对象，因为需要提前去录制一段行为过程(也叫为spy对象打桩)，就是下面这句话,因此需要添加另外一个注解@PrepareForTest
            //这个注解告诉PowerMock提前为我们准备一个 xxx的 class且根据我预期的行为去准备

            // 这里通过真实的测试私有方法，而不是mock。测试私有方法的返回值
            String name = Whitebox.<String>invokeMethod(privateMethodP, "getGitUserName", gitBean);
            //断言返回值是否和预期的一样
            Assert.assertEquals(name, "用户");
        } catch (Exception e) {
            //只要捕获到异常，我们就认为单测失败
            Assert.fail();
        }

    }


    /**
     * 场景：假设A类中一个私有方法
     * 第二种 私有方法的单测  通过 PowerMockito.method api
     * 注意：这是"测试"私有方法，不是mock (Api:method)
     *
     * @throws Exception
     */
    @Test
    public void testPrivateMethodGitUserNameTwo() throws Exception {
        TaskGitBean gitBean = new TaskGitBean();
        gitBean.setLogin("用户");
        // 获取Method对象，
        Method method = PowerMockito.method(GitUserDetailsPresenter.class, "getGitUserName", TaskGitBean.class);
        // 调用Method的invoke方法来执行
        String result = (String) method.invoke(presenter, gitBean);
        //断言返回值是否和预期的一样
        Assert.assertEquals(result, "用户");
    }

    /**
     * 场景：假设一个类中的A是共有方法，但是它依赖了私有方法B，可是B方法因为特别复杂，在单元测试可能无法真实去模拟测试
     * 所以我们需要去 mock 这个私有方法行为
     * <p>
     * 注意：和以上不同，这里是mock 私有方法
     */
    @Test
    public void testSetUserNameMethodInternalCallPrivateMethod() throws Exception {
        GitUserDetailsPresenter privateMethodP = PowerMockito.spy(new GitUserDetailsPresenter(mView));
        TaskGitBean gitBean = new TaskGitBean();
        gitBean.setLogin("用户");

        //需要对私有方法的mock 。并提前去录制一段行为过程(也叫为mock出的对象打桩)
        //如果 我们的方法是 void 类型的，打桩需要用 PowerMockito.doNothing
        //例如：PowerMockito.doNothing().when(privateMethodP,"getGitUserName", gitBean);

        //告诉PowerMockito 执行到privateMethodP.setUserName 方法是调用真实方法
        PowerMockito.when(privateMethodP.setUserName(gitBean)).thenCallRealMethod();
        //在执行 setUserName()方法中，调用了 getGitUserName() 私有方法，所以要mock掉并打桩
        PowerMockito.when(privateMethodP, "getGitUserName", gitBean).thenReturn(gitBean.getLogin());
        String result = privateMethodP.setUserName(gitBean);
        Assert.assertEquals("用户", result);
        // 验证 getGitUserName 私有方法 有没有在setUserName 中调用
        PowerMockito.verifyPrivate(privateMethodP).invoke("getGitUserName", gitBean);
    }

    /**
     * 场景 ：假设要测试的方法内部调用了协作对象的方法，而协作对象不是通过外部传入的，是内部直接实例化的（方法内部局部变量），而方法是有返回值的
     * 以上场景，可用过以下测试用例方法单测 （api:whenNew）
     *
     * @PrepareForTest(GitUserDetailsPresenter.class) 已经整个测试类添加，无需在测试方法上重新添加
     * 如果需要使用 PowerMockito中(mock 静态，final，私有方法，内部对象都需要添加该注解）
     */
    @Test
    public void testInteriorNewObject() throws Exception {
        File file = PowerMockito.mock(File.class);
        PowerMockito.whenNew(File.class).withArguments("path").thenReturn(file);
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(presenter.fileIsExists("path"));
    }


    /**
     * 场景：假设A类中有M方法，调用了B类的方法，而B类不是通过外部传入的，是M方法内部直接实例化的（方法内部局部变量）。而M方法无返回值的（void）类型
     * 以上场景，可用过以下测试用例方法单测 （api:whenNew）
     */
    @Test
    public void testInteriorObjectEmployMethod() throws Exception {
        GitUserHelp userHelp = PowerMockito.mock(GitUserHelp.class);
        TaskGitBean gitBean = new TaskGitBean();
        PowerMockito.whenNew(GitUserHelp.class).withNoArguments().thenReturn(userHelp);
        presenter.saveGitUser(gitBean);
        //断言GitUserHelp 方法中 svaUserName 是否执行了，执行了表示单测用例跑成功
        Mockito.verify(userHelp).svaUserName(gitBean);


//        GitUserHelp help = new GitUserHelp();
        //高能预警: 以下会报错 Mockito.verify()方法的参数 必须是mock 出的对象
        //         换句话说 Mockito只能验证mock对象的方法调用情况
//        Mockito.verify(help).svaUserName(gitBean);
    }


    /**
     * 场景: 假设A类中有公共方法，它依赖了B类中静态方法,B类静态方法有返回值（比如一些Util工具类）
     * （api:mockStatic）
     */
    @Test
    public void testCallStaticMethod() throws Exception {
        String jsonPath = getClass().getResource("/json/").toURI().getPath();

        //Mock FileUtil类中的静态方法
        //注意：通过 PowerMockito.mockStatic 和 PowerMockito.spy mock 出类中的静态方法是不一样的
        //通过 PowerMockito.mockStatic mock 出的FileUtil 类是空的，需要给它打桩，而presenter.readFile 调用也并非是真实的方法
        //通过 PowerMockito.syp mock 出的FileUtil 是真实存在的，而presenter.readFile 调用的也是真实的方法

        //PowerMockito.spy(FileUtil.class); // mock出的对象可不打桩 调用真实方法
        PowerMockito.mockStatic(FileUtil.class);
        PowerMockito.when(FileUtil.readFile(jsonPath + "git_user.json", "UTF-8")).thenReturn(new StringBuilder("{\n" +
                "  \"login\": \"yexiaochai\",\n" +
                "  \"id\": 7712056,\n" +
                "  \"url\": \"https://api.github.com/users/geniusmart\",\n" +
                "  \"company\": \"aili\",\n" +
                "  \"location\": \"shanghai\",\n" +
                "  \"created_at\": \"2014-05-27T09:38:11Z\"\n" +
                "}"));

        Assert.assertEquals("{\n" +
                "  \"login\": \"yexiaochai\",\n" +
                "  \"id\": 7712056,\n" +
                "  \"url\": \"https://api.github.com/users/geniusmart\",\n" +
                "  \"company\": \"aili\",\n" +
                "  \"location\": \"shanghai\",\n" +
                "  \"created_at\": \"2014-05-27T09:38:11Z\"\n" +
                "}", presenter.readFile(jsonPath + "git_user.json", "UTF-8"));

    }


    /**
     * 场景: 假设A类中有公共方法，它依赖了B类中静态方法,B类静态方法无返回值类型（比如一些Util工具类）
     * <p>
     * （Api:mockStatic）
     */
    @Test
    public void testCallStaticVoidMethod() throws Exception{
        PowerMockito.mockStatic(FileUtil.class);
        //告诉PowerMockito 调用 FileUtil类是啥也不干
        PowerMockito.doNothing().when(FileUtil.class);
        presenter.deleteFile(new File("/path/name"));

        PowerMockito.verifyStatic(FileUtil.class,Mockito.times(111));
    }

}

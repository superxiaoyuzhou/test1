import com.piter.config.SpringConfiguration;
import com.piter.dao.IAccountDao;
import com.piter.entity.User;
import com.piter.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用Junit单元测试：测试我们的配置
 * Spring整合junit的配置
 *      1、导入spring整合junit的jar(坐标)
 *      2、使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的
 *             @Runwith
 *      3、告知spring的运行器，spring和ioc创建是基于xml还是注解的，并且说明位置
 *          @ContextConfiguration
 *                  locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                  classes：指定注解类所在地位置
 *
 *   当我们使用spring 5.x版本的时候，要求junit的jar必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AnnotationTest {

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private IAccountDao iAccountDao;

    @Test
    public void testFindAll() {
        iAccountService.saveAccount();
        iAccountDao.saveAccount();
    }


    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("------------------------懒加载user2-------------------");
        User user2 = applicationContext.getBean("user2", User.class);
        System.out.println("------------------------多实例user-------------------");
        for (int i = 0; i < 3; i++) {
            applicationContext.getBean("user", User.class);
        }
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IAccountService accountService = applicationContext.getBean(IAccountService.class);
        accountService.saveAccount();
    }

}

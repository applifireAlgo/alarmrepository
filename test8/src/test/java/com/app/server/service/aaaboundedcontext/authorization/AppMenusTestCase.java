package com.app.server.service.aaaboundedcontext.authorization;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.test.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.After;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo(1, "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
    }

    private AppMenus createAppMenus(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAccessRights(1);
        appmenus.setAppType(1);
        appmenus.setUiType("dDo");
        appmenus.setMenuIcon("FWM9hEfHlZAzB6zClPXpKQdVUxxPLHFEa2d1wXwN5pZkpdIcaO");
        appmenus.setMenuHead(true);
        appmenus.setMenuCommands("7Si7Tq8WYHroW5BwThKWnDLRwD0ipOL9BfB3bNurHp1ZQuu22A");
        appmenus.setMenuAction("LDiPZoBHZaJDBCCEaB9BeHRos9fZjk6THWfT3MMkYrgzCJigB4");
        appmenus.setRefObjectId("VYVOXSrnwUO8jOJuAnagZYwQXwyeiaPPN6AMO8CSn2XUZ9Xb7b");
        appmenus.setAppId("2yf2GpNju7OuaBgKSBIHylyBrGBpBD7VbWpvo3idkem6NnStag");
        appmenus.setAutoSave(true);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuTreeId("MVYlVTiIxNn3gPvu5PiCgW6nVpHcHR78DySb63uR6mcTaSHi15");
        appmenus.setMenuLabel("TNdquVR6kJtucmijdqx0qkzcz4LvTYiiXOHkxhnr7xUgn1kJUh");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuAccessRights(10);
            appmenus.setAppType(2);
            appmenus.setUiType("J0i");
            appmenus.setVersionId(1);
            appmenus.setMenuIcon("lsbtprhjoYuI0j2ULhLfd10WPWUr9ExMNu2SN7lal6xob9zjUQ");
            appmenus.setMenuCommands("j50EXAGcHzh00vtFkfz1iIFZCRU7TQM3b8FcjzQfrVy3v7ps05");
            appmenus.setMenuAction("WJgZpdc6yvgbnsTu4Aek6swDWFJiY9EkYdO4Tn53UQ70YAc5u9");
            appmenus.setRefObjectId("XJCk22elT9qKQx7nEXnymY1gIt1UVuLesgrA8l0PCSRmNNfoiG");
            appmenus.setAppId("afHKINkdjnWyvnBkxaHmCRrfDtg0ea544ZkQIxcPKoq2dF7lXB");
            appmenus.setMenuTreeId("dohrnBQ5ZRZFtReVtEhMYrW31LFCZFVaGeHJxaqFW3Dl1N1isb");
            appmenus.setMenuLabel("KrOx1XtBHOzkhMdda7QejKYIhk0AryFGvDqbxkKtSwdE9yZR9Q");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "foXQU5hLiH6Ycehm7S0UAsJA2sZqDMZ6eWmxkR42ViY5FT4Nke453Yz2et3RL0BjIIWJHnAuRp9bosVt3jk7Rx5cAbFnNkqgCgvlusSaBLf7cgSMoKynS8sYfWVwK2H0TsVWhIYr3yuTHt6Jj97p2fh05iPk1kZVm5XK4PZoLRMrkEbE5glyomQsK9jmFhuWwmxBc8MZLYfYg4wIFBsxQB8VGSDSQgv1CdUUi8gfHHyDVXCS5ZBvJ4HhmRz46w4U9"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "6ULAa4ClSWQRiedOCEnAnS62E7hqAMokDiI9c7Gml3wVIHBa8EXrEwoqySE4mfBTWoQR9IEo9O6GzgXdgSqsB7iyHWfLxw6pM2J9imkJYrnaiwNui6POY6iiP0oQqB42WS0ELSvYJlmSYkXFVAYeDlhQl9RzLHcusMfuJhgxQycN65hPjMwIyccPQjhtMW9weEpsbqDWvLfxyonEaQzfCCGVxzXCUHdecyv8aEQtpG2IZM62sIoEFUq4K2Px71fDw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "c1oiUkWM5JH2NOqtWrxpVc4sK5MBZ9CpGuZEIlFgsGnFa9Tmmit1GFwTffqws7dxWcwCjHLtXjsLdNrOY0YYYz5y7sgqrd2cOpTJo0icmyjH8IAbzFSGUyxVQHMFjNDfLFV6k8kXFNhRSVpOeqpybzREWyPtfgbQicjtR516Ol2kvQs4n7xxgWT2bFBqY2Rt1Nx2WEukhpslzdhOFF46PMVPsR4sM7fDrasRj34FSXi8JuPo0XtCK6GXA2RBgHTgo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "CSWvyltC75A45ZkRVOrCmRYV5DbOOdUSMTxHpuXBS1aNYTk84SFBwkduJHYBZYKmT"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "HC8k3b8yXcVUprCnmtQ1ZpS2Duhs5k2wuzB2Mtdor5ZzwsNLU0qFRJpf5nX1uGv1Y0BJAShbj2ohTaxdLn3UoTiwUmPyrH97CtxPS4QPT5NOMFkR8fa8ietlb9RjhXOzTszqUzPXBVz6hkIk5EzbkDCojC0v3sg28lO0iDZ7T9WAYGNIOtVtpm3hSMpUN9zrN3Mr1zMrbityiDH7cinubZwxeSJHVKTCm4uAjoiYxB0Ia7UFKiiZ8uPaurNf35t6N"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "sOdA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "nHZ1YWSmiMIOIV6drAHpTCHKWJS3szINYNEqdxQ0a4D0CzjQvbNpjxSLDarB3BndAEEYBgJcWO0re5Zl4EZmm6uyPmYeK46guomm3AlL25gC0ZMxnh23EdETjFz39f2w6Iv5wwEwvtuAScrRG4URA7e7nwXE6K7TahWxpBijd5FxSCKBh1ok3bIIZ9N3VjJoGZOI4kRI0lk3y8fjzdJjMGzMTjqiNDKoQVBFLm8uliHdeag24KpFdmh7JTFm2dE19"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "cQD9wF60vVsmVyg4xFOyoxVsxNA7gCDQa4kBFdKxevAyjjavM1ys3WGJ46tibQV9mYR8IB6SNBrwjSskw08mCnuULjREEsaCEBRfK8mkuCPkzfYjdCtlXH6l8jEmkYYD2vGUWBlzfWyIC4Tq4F86pA5wv2XIbYlQCr6kByy38WuD1AO68LflMZQXFKJv8NRE2aUaN2D5HyS2h35NXvQlRIeFdmtSMJ7n4bTwrhWRKI1j7nY3pFktB1oqN6RoN26SO"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
                        break;
                }
            } catch (SpartanIncorrectDataException e) {
                e.printStackTrace();
            } catch (SpartanConstraintViolationException e) {
                e.printStackTrace();
            } catch (SpartanPersistenceException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}

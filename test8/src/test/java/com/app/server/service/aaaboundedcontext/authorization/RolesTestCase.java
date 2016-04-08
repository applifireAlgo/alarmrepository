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
import com.app.server.repository.aaaboundedcontext.authorization.RolesRepository;
import com.app.shared.aaaboundedcontext.authorization.Roles;
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
import com.app.shared.aaaboundedcontext.authorization.RoleMenuBridge;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Roles roles = new Roles();
        roles.setRoleName("HkFKKT87hImNG8g0s9Nn89BTabI1OcEH75lDD8Uhj3mx43Tthp");
        roles.setRoleIcon("eSzUAmP7gpPqrGZ6KSYFcHjMqi3jB37BxQKba4xO9VWKiREoIU");
        roles.setRoleDescription("N1mt09se5xfNwiCCT6BmGrl2Bn8itXu6umipnz0GERYDVtNO2t");
        roles.setRoleHelp("0BJtC2sDEogxikxSVgNeso2edlN5wMsEw1snc5wuJGfvi8YINO");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAccessRights(8);
        appmenus.setAppType(1);
        appmenus.setUiType("sZs");
        appmenus.setMenuIcon("H8uwueNDNkbdMtusgzOM1oNDVv5TArazqSFCfHwSNkMWLr4SCR");
        appmenus.setMenuHead(true);
        appmenus.setMenuCommands("0z28HFt5NeJTbbsB066H7yS3L1TLOovNihTfCSvdMvxUFYsljs");
        appmenus.setMenuAction("wYItnslxmIQ4ZXRNsj5w1sE5ixuZvyDZKrG1IME6JNVOprVyt5");
        appmenus.setRefObjectId("3DZhy8JTSvPxdlgDRMhhVuZIfjRurp3OfT6RopvND2EGdxZWsd");
        appmenus.setAppId("UVqSSsL5uwFuzdkW7H1fcR5ti90JRfZg7GCUwUBM81BeuVfNoC");
        appmenus.setAutoSave(true);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuTreeId("LXpNV0fQtLmtvY8t5iO0MSnUrpICrcj9rU9ypJHogakSXYiMQJ");
        appmenus.setMenuLabel("V0s74bv1xp5r7WBAs6Dfy1VMRfyNnjuQvjc2bg16KWrbMPlYmJ");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
        }
        map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleName("I4TtUfhFxgt31xfLBjn5VG6oQrtnjB61FlOfP2L2XlRYLmocke");
            roles.setRoleIcon("79hA22G9UOLgRcLxRF9Q6zZHpyuUQ4SroUDSE0uQlvpuPGQNrH");
            roles.setVersionId(1);
            roles.setRoleDescription("lHkZOKx0y3ZhkAdWAbSAGI3clRN7TKUY7RTPhyForEO3bv7O32");
            roles.setRoleHelp("zuJCvB7K4iBsIC6LlJR8fuP4goQzgyyTaaOoXERYwejQI5f5kc");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "Bmrr8z1escKK4rlziRnzzmf8RE92QbgxURHTCksxTKFFT4WmVcUXzPQRjUqFa4sCRGYqKS4Z0ltHiW2udvEHYI6ZoOMW1qJiYLtqtGvElV8qHeyDFU9xEjNGtfHnZAlGiEZKhZLMbxgblpr2WIWtqvq2x9HT5IWqH8RwN7FWmJgMEdp8FwMrortd5b0mF5W1eJsodbqzf1etARcFJqPDNWcSLy6IoO1odhIRLrM3F1WHV21ECXjIdlTMHlIOUdxRH"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "CBfE5nhTDxSN1BsI3JMxO6h2VOcjThln200OPydJNquw1o6WJBAwmpOjc1KhIL5ohWnUJO2jw9qoT42HtxCik8Hxfv7IycuTBdssUoTYoiShj9DyUSMHH5lCWQHVhHWDPvxGoKNtthYbMz4z13tN0kfJplYcdySFRrdMSK8d7lW8eG5vO4j2mI2M7UXkrufeRX6wDtLhwrGbjfRdXZUeH9V9ScMT7ZfJgpI82ypv91rsfbWEfbQ7C4ev5FDZ2VPLM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "a2yUEo7eX2U6uZuYmBCc8fMXR2npI8P8rS9tVrWz6DBw6P7qzagLVzKohGzX8ibgG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "K14rxR6eoailB0bvGZuNbsy6F4w5O23XrziZLT5fXfjNSatJ3SpR4FgWBS1ahA0zNzeNZcdZKD3Au4e4bUPi5wQhhEmunq3GIHNiBDyoM1ObbrIXSzwo80MSbone7qHt2crRil86GxZiunu3SKKcEz8hgaICSPPaRKNKIPxJgDyE04AviT2P6VKdMWkUACNb08W6bDNBGriasLaoXyo9QLQddGzNkAnLKldDDjFSatgKzRmGST7iK5tQj2yVBoa3t"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = roles.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
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

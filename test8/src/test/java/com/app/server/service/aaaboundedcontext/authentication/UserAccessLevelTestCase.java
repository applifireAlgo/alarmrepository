package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("yr2L1E3OgmxH0NYg3JH8O4Veyzzt9DHZKRrMxZsfTloyVSZ9xZ");
        useraccesslevel.setLevelDescription("UHF6RWAP97JsmeeS2KaVzcF9DoFM6p8BAvaHBWh96m1Nh8hFbo");
        useraccesslevel.setLevelIcon("GIm7GQ772GFcreGVhcmOVVBVPRYQ84vzrCQ7OEUE50HSokPDug");
        useraccesslevel.setLevelName("Bpz7uUoPSACS4B0or2vFeSLWuAUtQpBuMc1RiPZuW95vJrm2uB");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelHelp("mK5XAPoYVA88K4mT7bZefyLgECCdASe48KWNxoGKDtKfNhLa4N");
            useraccesslevel.setLevelDescription("vXZ0U5IVUxitaNZDfFL8L5vzYIKBae6auijodQnjqm7wLv9wuI");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelIcon("Akr2Tj379PJZtUb0KDJPwNUbKG7TaVAex11DlFwzMHTa7oSwlV");
            useraccesslevel.setLevelName("4dYRPj32RHGxQshKxu7nwVZodNYZylAlstQLa25CzfoMGfNtpp");
            useraccesslevel.setUserAccessLevel(81754);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 2, "userAccessLevel", ""));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "userAccessLevel", 127262));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "levelName", "sNshesTA7XRbz4Yz4Bum3VVA6nK9B4qfwJOu8v3ADl3eHPzHInqrq3rwiOiagUk7Jvdjzs4qsQ3aCId0bb95U3FWbHRLH9MFeh0wXKiacm1EMhfSJN0Em2OqJgMRsubfv1wJ3sm4rjM75zTVazJrQswqXvhMchWgwZstIZC9x96clZnKypbiZW3D7OsP23iV3SshmiqZzukhwZ4MzKhMOCvUJZmHQahSrd37ykwowoFWcvoTF3N2duTbaANM1V8lf"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelDescription", "g2iBLNBx9HRNufRSKcoMHPim1y40Hb8Q8YbQdwpo0jmLhvTJcuFrSsSbIgw5PwCMX2O4rRuNS2TWZQ5t9tnAQhRZGNrr3x8bFGhLnWwIJDrO4CA1BTCTob1OdGnzz1A7Z95Xoh2oqbkLxJLI26coLPpZSO4dNCuFvMTFugZu1zhBuIP2TDHJMN2dBEpKrZvqEITEsEeDhv9I4H3pIOJM00YXjB3pWT7fCmkLiHgPcI2JVCsrUQ3xuI7hag38zf4W1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "levelIcon", "TrRCpd9Ca33WuXghhRRTW3FFjm7ERg9c6u0bvWgje7rjAmKeMCtdCfIS31D381qh7kKZilTwD6JyqthDrNywVhahaLWFGu8HT6WKQOmwdMJaCStki7i9xSfhp4exggnJ7KR7rjNlxb3zBRfIYZlAufLjnjeyEwDrncU0ZGKQYen9BYdQInpJ9E0UWe6CQu5cmWcVpqFawL5niEYBU0ZCyuoWEsdGlczU09eB2Zepv6yxJXagCe1LPgBsUvxVD6Svi"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
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

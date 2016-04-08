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
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("AcMDH5QYYmTODH5PYhdmzGCqYQ3ur2mvSMOIHSatWtxlXR7EL3");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("pj4O9WQk7Qo7NZUsuoY1baIP7bEVneiIXZUcQZPDsXVcVysMcy");
        useraccessdomain.setDomainDescription("RWJnQklOoWUdrmIhpCVw515YpCxWiInmDN2DyAMSvQDbe54EbL");
        useraccessdomain.setDomainName("MzOQE3PcLdEapSzSwwch3VGs0HoZ0cWTkWjyuySzlupI0V9t3W");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainHelp("fVHOUUZAjGaNT9tJyO7yQS28q8YE9fiWZeJEKfj4lPOY6X0Djw");
            useraccessdomain.setUserAccessDomain(42246);
            useraccessdomain.setDomainIcon("naA3DV9oo7ynfxSBTjimSPlF6GBjUhNjHhRRYr50QpliE9JYMt");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainDescription("0mVFtq0xnw9h6HqlpOexNC4pB0RoueRtEGfFHdnnu8bDM5KOlp");
            useraccessdomain.setDomainName("9CGB1azQSDdu5Oh3hmYH5jAb58oJmXAjgukPwlRKKkxwpx98iA");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 2, "userAccessDomain", ""));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "userAccessDomain", 192542));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "domainName", "4S3kjFRlYj7D8WNB3z9XUHZYcbjyXyKZXPkkWY9YWruj8DFZDXe3K9rVqSQVbWICqqVWW9xbYmCTZSZrjiVKQJWF6F6xYF2UReBVOaTIklGJVzFGPUbs3JSpcO1VvCELAdahAH7khMcLttVGPN7r5tGvqFF7SsirAIH6JNAYwB1M0X8lKvj7Y8KwUCkiwyM6hVxNsdIw1JChGK8UWlThU9yvZjXqQQ7xlSHLtgCY4HIpOpOCVW743rIvoGYDFt7Bg"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainDescription", "DqIxyDd5rcBt317e2g5WJWFQ8Q3LauAbexH32oaM90rsv4fbJrTO9qU1wu8Lqwee5aRdddHkl1XCsQmWy216TT8kSAq8lo4bEosgIEWF26Bzp7zmc5e62j1ylthGaA7RsguLRr7PYyAH28xJ1fdZb0PrjFrOH3lyZcqK0UVjeyxLYPgnqM8MxeqZzQW5n6QGewx0m9l98RB8PKECIRoam9mMLNdJX6gurF1GKQzg1BaxQR89dJQVDylSKj6NSOKIE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "domainIcon", "uyN73n1z525XPiEZDrfTa653NFMeh496jZBQVYWeDI1fPzrcXzxIi6iVNFRlnTeP3O8quXsok20svF0WQwoVl7W4HPBTu3QsVowZXShTbA6qtwHgtPRIoNz9cKwoNErA5JMlIj9UANcr3kk3zkyQMM1VXu9gOdGjlrQ18SolxMIB5J6veqHHOLCkmA51PsmKcqlVFBi912CcbyXDwWX4i6AK9dNifVhiPrs1ZzTdJLTNHqGhMTlwXprfsBlvPLHwV"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
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

package com.app.server.service.organizationboundedcontext.location;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.State;
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
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Country country = new Country();
        country.setCapital("aA4la8Q92zDNUiFTpapxBOyY8Scl4FOL");
        country.setCurrencyCode("yZJ");
        country.setCountryName("LP7KnhMewoBwts8PNRRLgmaxX6FA30GFfvrfMcGOsaF9Cxbf6l");
        country.setCapitalLatitude(8);
        country.setCapitalLongitude(9);
        country.setCountryCode1("Stu");
        country.setIsoNumeric(8);
        country.setCurrencyName("Ssrz7z44VkFFbgi4znd4e0YpuO8fHYC8WNlXYkqfSmtIKMNH7M");
        country.setCurrencySymbol("OlQzdX91fer6GbtQAF22dcpjgwjVuoEm");
        country.setCountryFlag("rcDdcZDinGqk7rKj7Z4X6M6JIhvsDnx4t0C039FZjGq9dbBlvE");
        country.setCountryCode2("BIJ");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        State state = new State();
        state.setStateCapitalLatitude(8);
        state.setStateCode(1);
        state.setStateCodeChar2("Gsnh59MVjqKm0BqbUr1TthIWc1a4cj41");
        state.setStateFlag("hFfd3FnQa54BNcAXeIDh5b9nz70tKXJOuF58IL3pClUEFwEDM1");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateDescription("MedBJzHeresMhtKrsVvyCWGaUulFuh7X4uOS50k84ztremrCLC");
        state.setStateCapital("6blmW6gG5c8eQotI8lFLKDj4aH3rRxVjMR7aF14q2yjwGlhsGu");
        state.setStateCapitalLongitude(8);
        state.setStateName("OTr6HazXgFBiVqWhvR76npBu2TVmKznMtsnduwvEPhLOBrNPEP");
        state.setStateCodeChar3("4kApAsCDVQsMV4rZbSJPBh51whst0aLb");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setVersionId(1);
            state.setStateCapitalLatitude(4);
            state.setStateCode(1);
            state.setStateCodeChar2("rjQwo3BGrdjPbnhgJtDm6dQfWx594wQg");
            state.setStateFlag("LS2pw1CbVjGpKi9lrFrJEJN09kRBXT4OH9g9KTlk3SrTgiEvQU");
            state.setStateDescription("UXJOXsv0LLbcfFrg54VR0daitf2qUwQldiHDzx4162N7fDwEGl");
            state.setStateCapital("YdfzGJNQHQz1j5TdvnEzbjlFEacjphgQoGPUpbgOTX3hVcwlvE");
            state.setStateCapitalLongitude(2);
            state.setStateName("KILoC1DmMYcku9l4uammnt7tLJ7ixs3jWwcc2k7caoTj28Qr0F");
            state.setStateCodeChar3("BOS74HnMkpRFuKm7Q74yMfmJCIrlGsn9");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "oVAktH0p7tGo70k95L0jzgcXlzSSRInkzly0uWLQ26FKfN16PY7D5OYFF0qxk9v7cQtesSZLjEsIg8TeZqASOWwoUXqAiFpMWAQV6KDA8d71pmvN5d0vPuNmX25FLvfpoqX9E0JXGHFIlAssqYcEnkXTS0JXMhR70At6VZEk4z7HwSyv4Bxw0RIs7lfseLpeq3GxxEMrhO7yFq1QT1yGLvCI9iM9XMR8qAO7VsJ74AJ1VPmcIrZmPwFNGjDd4qdYo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "xBZaeuwz2txN6j2FfnYezPFMwtsICiBv5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "x7FTPmc2hiLtESsLnhVcnS3QecQsqve9H"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "rtYtw3PDxwcf2NrAWl2zJIbpoys1kTjxusnd4o3o2AUA21GzJCk5FveFjtn8XG7IJzymgJDxWCsJr6DMRdHdCq6lfZ5yzlvjcROrSw3ymD1D4KtQFLeL4en85xqzsGhuAShgIMVICT9kzi96Fe8oS9DwaUXSIcGcFUNEMNX6xUxziAZ75zC4LNokgwXJWbzW2wffv6BGjg37oSjomJjtvJiVnVV8iTytCOpNzXrwRtiHKcjThlG5QBnqWTMs4l5ka"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "oqKsaNLhPCrC7g50aecGRlys6Y93NuEFgbMWL79RuitIIFjaeaS4YjLpOWd21YUjPhgm0FEHu1nuXece1mcryou4pWMvmBOeeHKVquugDIJViYLewmiVRIzXv1KE0lDiy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "g6JuAKLf0fQRvE0KbUw9ews2oX9fRyeJP6buSiHwwzPcZcBKnPjJFd3tAZ9WlWoWRsraclnw7uEd2FALbmonp442hpP70evO9DRQ6SVp0WCzWh1TKzvfnMBT2vpY8k2GW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 12));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = state.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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

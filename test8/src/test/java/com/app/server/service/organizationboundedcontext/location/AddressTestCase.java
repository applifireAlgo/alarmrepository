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
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.Address;
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
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        State state = new State();
        state.setStateCapitalLatitude(4);
        state.setStateCode(1);
        state.setStateCodeChar2("HFrdqgK2QoTHdwpZNMaVIcIguquF2Bx1");
        state.setStateFlag("AEVklJtHqg0IpOOyGlkihtj1xFcXRC9HaUzF06Wxo43hPkQTBz");
        Country country = new Country();
        country.setCapital("HFvol3u9kPsIeHLvqVcpCMyfeonwHchH");
        country.setCurrencyCode("WTP");
        country.setCountryName("DAD4DC6IK6pWZukeukjH1UPHJKavHzHNX8KMr0F0AHXepT3MG4");
        country.setCapitalLatitude(2);
        country.setCapitalLongitude(2);
        country.setCountryCode1("Iew");
        country.setIsoNumeric(4);
        country.setCurrencyName("jBZtGcyiKDuh9wQpgX8Hf7LBDjaSDdfVacsFhi7RV2lffL89H1");
        country.setCurrencySymbol("cKhb8oiEBgx3mJBziGmsJ5xB8rkrcCOK");
        country.setCountryFlag("uf49VPVPBy18nuDSbkd492EZ2YQ4thDZMUTxNJOeAdviA9dOKZ");
        country.setCountryCode2("hB7");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        state.setStateCapitalLatitude(5);
        state.setStateCode(2);
        state.setStateCodeChar2("QVlC4BjNmodRVS6gOF785GmOsfcNTFxR");
        state.setStateFlag("yajgmTcCVurtrnKkrsAXrJIT3TVVxsFHDdQqWDAPU4YbTAk4PC");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("4UZkQaKIGZ9wpeefmh2SQzRBKpN7G8IoDy92BinVZhHZDRBgka");
        state.setStateCapital("KeTsoTKgmRLEx5bH7HM2QKILWtDMy4eaBK8Ljbc9ucXwXRMCyJ");
        state.setStateCapitalLongitude(1);
        state.setStateName("4TCwZGwQLUSsf8nbmtSjEke2CCsPZzCP1Az9IV8QzF12I36y9v");
        state.setStateCodeChar3("H7TapNSYss9ZboTf8ysi839xB43VFKzI");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        City city = new City();
        city.setCityName("OKpAsAWYOaqj6TJlmGpA16MJXCaZpw3XekYuUVwLCgOpJCV79b");
        city.setCityFlag("wGXF73dj287WOkNkDNKBl7tJTehG726G2EFgKRWZfxAj6tAtD8");
        city.setCityName("joaoF1jrhBzc7VbfnHrLQCscsJJPm1DUeStXYi4R815rIoqCiO");
        city.setCityFlag("l09B8XBoWLnLJiUC0Iw7nyi6E3qfTkQn7tXFxMuuRfeHNCFEBa");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("5kiUxmh2j2NLOdh3SgA7hSwCAT84E2htraUWuw73SUX91EHM8y");
        city.setCityLatitude(10);
        city.setCityCode(1);
        city.setCityLongitude(2);
        city.setCityCodeChar2("LZv6athS9jZmxYCykoWgJHH7w8S2nQyd");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("3lQLIFlZry72FO5agkkORmWs1BqP0TQqRNJQYcqOK2oe1GeN5o");
        addresstype.setAddressType("P4raN4oe3V5ejQ9SgooMSY4pt2VuKXtlRIapgoZJN90adFf64E");
        addresstype.setAddressTypeDesc("Lrk4iYTNwgLeaiKBOz6xYh3f9Ge1i4H8PYPvWUfRFF5le0nBLZ");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        Address address = new Address();
        address.setAddress2("fK0hZJ5Os1mJ8VRVY7rJtqMAFudzPYtiPmsIm9mpfdAZjcZqBA");
        address.setLongitude("DHzS7GFaUmMu4BLCkzZ6omSi1PZtxMuD5qYlTTKcShvj8zA2vi");
        address.setLatitude("81wn5pO9Q7VKE3w6mva2nYXeK3tRWsvX7Qu1Y8C3bk9XtNxrB7");
        address.setAddressLabel("BaCRe7HCzDV");
        address.setAddress3("NV1OLJOEFVi1OTPOEdwiSkIhI7llSsHEyAGsYx4CWdLIbpzRnB");
        address.setAddress1("JU58MDrCR8DIiPA7cGd88WFpwxccTqggKSih5D5MjJGNRXc39q");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setZipcode("X1eHVb");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress2("Lsf1zxhBbffE3pVIuTtbxlHgEPqDZcJb3QsVKkh518vmIy9Xx0");
            address.setLongitude("RiRiNO4lhy0FUFkCIF04O0BymzFgihZL4rXCdx1qkAouqiJiSR");
            address.setVersionId(1);
            address.setLatitude("LFRhkBoMzwxUxUXDqlCxxE66hQkiBpSejRnKDjW8qQ37Dt4pdo");
            address.setAddressLabel("05SkzyhgUzC");
            address.setAddress3("AaYitaeUG0gRiRylEt0io39jwgHBWLNvs6aZMU4B759bLf7MTK");
            address.setAddress1("pveklnYgdaalE8bexzPk7fV3PMLRhWbbczWh7UA7Uk75VXXe2H");
            address.setZipcode("5BkMNX");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "tUgiuNbEaCyM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "VwsaQ2n42k8i9cKEtNZR6WCcp0VxOLE8kiHwwzQdbMpcJentBUPrNxWqh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "MJcvLLYMeQbQfapOQ5QkURTeTRnR7PgZZn9qOlhnsDt7mJ9r0ZSCsYScj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "yJmF6XuZvrdZGsR2SySDjGA3vs6hVNfTlD95zBSXQRD2PBMifYORUu0RX"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "fNgtKjr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "59m4RBmZBprqlhAOSG3aQBFCftHZOcPvCKfHtQKp5MEzJQQOS1c1xGP5oFvPFWDWQ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "Wsi5lMEKeHZJzMx31ygV7SCQPU0DTxsJ2TAUZqfELHi8lqQmFE1owI2jCPG6uTBNa"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = address.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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

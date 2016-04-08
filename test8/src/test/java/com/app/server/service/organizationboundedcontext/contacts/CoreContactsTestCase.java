package com.app.server.service.organizationboundedcontext.contacts;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
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
import com.app.shared.organizationboundedcontext.location.Language;
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.contacts.Gender;
import com.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import com.app.shared.organizationboundedcontext.contacts.Title;
import com.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Language language = new Language();
        language.setLanguageType("0TSJfVzLNzgQCASeph13tzzXloT7wIf9");
        language.setAlpha3("JR5");
        language.setAlpha2("DF");
        language.setLanguageIcon("pt6mVdOUhep9io9X7ACuiklLh9feEralxT18p9y8DP5r52JDyT");
        language.setAlpha4("shfd");
        language.setLanguageDescription("YuZASI12cbCO9uO3ggAPI1ZtEAeGyyJIffu5JslFL7QMCRhdC4");
        language.setAlpha4parentid(10);
        language.setLanguage("7lPuJagY9kCslAtAXMbg34Ah5Ywl1qifK0tVQOFZ1OvCXdK4QO");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
        }
        map.put("LanguagePrimaryKey", language._getPrimarykey());
        Gender gender = new Gender();
        gender.setGender("45Fn6hrKwFTGkbOup5huK0mAVQg195xo9kwtdym6NYVROcVIr8");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
        }
        map.put("GenderPrimaryKey", gender._getPrimarykey());
        Title title = new Title();
        title.setTitles("1XrNoHetBsc9gjCxc0NSCsXYvBQERjLBM9GSvOCIJsjR384jsH");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
        }
        map.put("TitlePrimaryKey", title._getPrimarykey());
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("82BP4aUjeHOGIAG7cJvVnIOmh0Fto5qtGP06VKziIcCLs2Ur2B");
        timezone.setCountry("zfrRJbVjGab0yRBmyCZ4QQpiRqKmpAONtgjJJnO5gQWcZjsPD9");
        timezone.setGmtLabel("nGTvmloRZ806Qly7ZPKkYfeeJIdbTLEDIDMqFKjrl2l2VKyZ9D");
        timezone.setCities("OzZq0tePU9F9T3RftZiLrxPKFXne9QGShd9dneKNZMK3L5wArk");
        timezone.setUtcdifference(2);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeTitle("Y5V8C6y5cvVEmYQskQUC6Puz2b9L46UpwVvkK5UAGqQNzCsixx");
        corecontacts.setPhoneNumber("UxbQtMO3JXHYVLf5ZMzU");
        corecontacts.setLastName("XihJ9o39urpk72bRm1Na8jGF2Lng7URjik0Ho8XJ5KL2diMheg");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("RJpf4KK5dKoC2TH7AchQ1cVgdvicY9jDfofP4Ju7bCpUzqAPEB");
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460104250871l));
        corecontacts.setFirstName("bmqW5E7Y18bMhaM1ZrAPm2zZpob56I9eyuLxKuXcZ7aIj5IxY0");
        corecontacts.setEmailId("ZbDD67iebrLtUpQEvMs0fgCgy5nEV6YFl2IiyuYRdXepgzDIYs");
        corecontacts.setNativeLastName("C97tZPkYujn2e5WJc5y0GuIETxOqk0klOTmfkf6UNZha0LhdAO");
        corecontacts.setAge(17);
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460104250871l));
        corecontacts.setNativeMiddleName("658YxahvtIHQKiL6vSt5ZhjzqMYUZkCBZUCBBNvXuSppzoNdOB");
        corecontacts.setNativeFirstName("0Qc7fUi2acs5guDQ8r8eeFKJGgLjnhzEZdVHt3XtRcu3TOo04Y");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("Cw9Zaxy7nVICJuGJFLYSnT5tMjkVRigLY5t2RULCqwD1Yj1N3v");
        communicationgroup.setCommGroupName("KUxAnWHRKPvEEnb6nSUcteMyTGkRj759gnSilgfyHY287chm8V");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
        }
        map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("CjIlFCZourxaHYe7CKnpLVKIqoq4q87lZw4H27wfvLRzebFKRA");
        communicationtype.setCommTypeDescription("pqHGkYsxBXKaD4WM3diUpO7JWqpbwCaIs8OogqWZ7GP61CZMXB");
        communicationtype.setCommTypeName("fb7OOHRAmCuG6nGaVCHMxkKuBC22MMTy2LsZLlap1jb0xbiVEe");
        communicationtype.setCommTypeDescription("AYdVXPHfxwkCo8rfxHysOVtpp41MiVW3hgCY8dnBmYUAS0hUs3");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
        }
        map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress2("4rTqJmtUS5OBT8YmhQmL2aM5Oj611sImUEFobT26fox3hI0pcz");
        address.setLongitude("HxbR9iE2N5N1X0tlkOunB7N1hnvPu30hVg4p4X9ghr4NWIS89g");
        address.setLatitude("RsDrO3JFAhygWNU9ETRtlTXPOT2eGhI9eTqIMOrTDEiiMBcwwO");
        address.setAddressLabel("NukjrptxIbA");
        address.setAddress3("PuQtZU81uMWcH5zGdtCAe9W9C3PqNsdl8twDpCdsfUSrsaiaXZ");
        address.setAddress1("8ASqq5ddZm4oRoKMQhFSt4GBsNtolx1YxZpHsMut6LvD8PibGb");
        State state = new State();
        state.setStateCapitalLatitude(6);
        state.setStateCode(2);
        state.setStateCodeChar2("qp7s2nSc64DZggO0qD5NrYiZXWnB2vYo");
        state.setStateFlag("VdUf9LZY3rLY8ifFIOBzQOaf0tuIJjYoSEyyHJYAYGcRISfooj");
        Country country = new Country();
        country.setCapital("yYG5ZsT1Go6rFzLPyQTIo7PuLN7GTtEc");
        country.setCurrencyCode("U8W");
        country.setCountryName("yuMuU0C1eOpLDJ8QJBJQovXxRiPr3UhUzls3UFg2G4gHsLsfh2");
        country.setCapitalLatitude(1);
        country.setCapitalLongitude(10);
        country.setCountryCode1("4u8");
        country.setIsoNumeric(8);
        country.setCurrencyName("zOXDT6nD3rhwi7tE23kqNbuBU2oEhfbOK8pUqMsmR21XYtlXm3");
        country.setCurrencySymbol("NqFDLOWDN5Lx3NErUfJYf2LwGtgYEdas");
        country.setCountryFlag("XmMLWA9Bywdj6WwLwfUGk48bNe0ttDxIe2csXS2tm5TMSFICMV");
        country.setCountryCode2("tXk");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        state.setStateCapitalLatitude(6);
        state.setStateCode(1);
        state.setStateCodeChar2("32GFP4DgxhPqmUiE27gTfoDDY6zhp5TZ");
        state.setStateFlag("1BMlh8EltliBgIB6lBycH3ozTxyQ8OFEx3GN3kp3r11mHSCedA");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("lBAumHi76muxsXeUchY50APaO7fb3gqSUCbNgftCn4y3XOgrt6");
        state.setStateCapital("gK5uy3puAtWeOYWjqPs9tvtWI6hwLyaer0js8oyuFCxhjn1dz7");
        state.setStateCapitalLongitude(6);
        state.setStateName("KH4pQiqahD8eTp2AXMSFZiQsOsCJxhvR4yHyapOgJe0prAGj3Z");
        state.setStateCodeChar3("R6QLJgJ56mWC45fQ8CwLrKPVakvVjeJf");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        City city = new City();
        city.setCityName("SuLef3ax2VUpOynJsz3IfagLpefTiLNEhQwEmrXU7Hq7dHilid");
        city.setCityFlag("w1k5z4bRCWXSL23wrmiKp50VplrYxsiOa0gaBvlqXpLoEHD6fu");
        city.setCityName("xWIB2dp13xq4Nd59RNfuo0tFGUeDQfHJx0fAzRp5O8BpO6gdHI");
        city.setCityFlag("eVkHNb3MP2FC3Tm3yBEurywQp7Y2ir8KxMm55KBUWIg9En5nJz");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("GXYceuMYaWFcHWVfVzpVa6BVkBM72Fveh7N4dAmCzqfNO8TMEp");
        city.setCityLatitude(7);
        city.setCityCode(1);
        city.setCityLongitude(2);
        city.setCityCodeChar2("A5YTnP0roiBNgYgWRoLaGd8j1r3a5tGC");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("vPYU6LxVLiyRE9fg1xV2iL7lFjTlz2erBIElo34Vw6hsrnm8zn");
        addresstype.setAddressType("htiv5qHZhDNBpWq30gM4ZmNqBYjdUWdvsu2b5gll7edalgJ924");
        addresstype.setAddressTypeDesc("3CVPBn26VQYJUAL9hyPztAGPpBxX78ybOeryZmcuGOqufnleUZ");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        address.setAddress2("zrlnOVrNznoUeEKMHrZK95mAC2W3WzVQ3lbkiKKitq5TJFSJ5r");
        address.setLongitude("IjvPuy4ENJpgoZO5FNUqmcwisGTba4pyaacn9fUIc058w6adwy");
        address.setLatitude("5bXLsq118dMSybUSAbW8dwZZGnUlDmnSUtSgWYbd0FFeNxmRVd");
        address.setAddressLabel("SHcE4plC5Rk");
        address.setAddress3("maVVkoR7N2PZO0jYnFXeJLEt5rV7NJlwHim8RxjVVGrMSHjZjb");
        address.setAddress1("3NVmtpqUSZxv14SDnXP8Pn7EE4uDMGWMuk1YAAUjr28AUIejqE");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setZipcode("gVvAGw");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeTitle("ZPmIYZovaA4gTOLEuA2D1TYMyqM3RlChgM1QzmWUe793SwxpzO");
            corecontacts.setVersionId(1);
            corecontacts.setPhoneNumber("AlvdVsDAmSh6MiaHTBfS");
            corecontacts.setLastName("1GJMWwFzForzPkrcWIhugxZlFf9Jo7rznjhliceUDtEdGJWDlg");
            corecontacts.setMiddleName("Ake6RnekIAfDZgeLmI4L2vFuBxJDvlu2X6b9tRNkDddSQeVnX7");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1460104251319l));
            corecontacts.setFirstName("j2oIVyQ7Cww0jpjQ99DWsFGpvIZs8u0E6XqYBVD1JEfDpenEQM");
            corecontacts.setEmailId("mL01UscgUXkX2bSi31A2HnZ4TIipYWTJJl97hzcjRknblWuG8n");
            corecontacts.setNativeLastName("iBe7pxuuw61w9TgLidNfMDVeejvJmYIP7oJnlhyjj0nXeI6i75");
            corecontacts.setAge(61);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1460104251392l));
            corecontacts.setNativeMiddleName("5t5KyaEXxcxs5Zb0ilzXRmhYhGQrRJoBVEqf8vjnhu2tC4OmRz");
            corecontacts.setNativeFirstName("klhlfeIz0A6xFVHU8JbsGAFEBMkPgmNP2kKd7iI7mm6zVTI7M4");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "3yMp1ovL1Lze7lw9UqNsLx21OKjDkZitcOraBXeNudMP4dnynDXbOZx2nxzbt73OCDHvGxoCwMzhT7JWDJF4XSVm1I7TDiyk1NZjBwMipVScR6XLIPpVNYj944DvTUJKwLfcIEFzeNmljoutQ0sQsJfEzCXVFoO0Zx2A3qZxSpvnT4zHgte8upmL5vSsLSqBohEeYaf6varAxxPwGp9t6CvQB3A4SJJHo0bpftuTmZMIsvX3wm7BD4u8YfPTuraCK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Kf3LNR3orO25h72fhQwM1cKqXnEgRe5i1lOwGJPT37gaEspkmfqCTU9cEQMOD9570PNOi5Xq1Vu0U2BleyL8K1b25nXYzsUMM4Qu9XBk6NXchhnpV9xpE8o1T89yjTlCYN8iGq6YyTOD3JKRQqIDHmdYN9REpEzwlt9Jxh6cWlPDorYsrH86barZs4lL3QIiYKldFLrCcTQK82zTvldFWO1s51nXRCZ00DL62HP4vpqCDVwS04Dx75fnEp8DhEAh3"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "CM2gidjYELA7t1bFMbCi65ekCj3iE5m2dOVQyFxiIwUqltXHvtLoaw2pASFGaq8noH4sg1GIDayDYjnpjTHorl8y7rs6ezAxboC9ZBhNxyzyMdcYXs36ekTkn3KKOSWWhLl8HANeyTpas4Dp3lB7lV2x2W9s3AajAmjHypRuVEcVr46jQqDiGjhUnDjLRqDSYoGmTRuKcGMwqMqrnZFHR1m3KWJtbw5ib661ciahkMkviZkYR8Og1dLavf3timQVH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "G2S6O6mmDrIB1uqh2GT8SUu8pHAte6uTIszCbBO5hkp2oRfdAhyhp5urhsybSOsxz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "VUIBjSfbBzib2AQGC1YWqRNpu7n6xo3zFxNe8GLtoKG7NtUkZ6nXmsSN57VHWrhQkkvzd7J3d3h8CCHNUMDNpQocuyiUrGpZZb6EweSu3Q6hCj4yG2cp12twDmnrFChtgaGsXQBpFYVkPnArFoDBj9Nam78cLsBAponuUnwAsX1SbAvorNIGguQi47lHhxqoI78o0qA98SXQ2MmBx4dZbPvM5EJCUFYcri9IOnA1TVdwAkdpjnMU05itdz4RtjJbD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "b1pltOePmL12vFPLn8TeYBt479RpzozNjvo5pGIqfcPMsC5loPHkPPvF0F66sPabMgo9Mn6pf6c0cjfUGmHGCfiM9ezEQcDP43BmlqNhQWTnIBMKX4jfkvu5EfdNExW1dszv6bhUvDBYDQ2DVyCybIhzx3293Qtx36F0lLCuZLdvvYf628k8cKTgibLToTfwuOCFh89f0HDxGsWhe1flrzpgywhCjMY4MpG8YXu3Xn9ZrImSZ1hBTkVsTX4y628FJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "AzvsuJ9pBwK4SZKRMZj1RKSutaksdtsr5NJM6HHKJXIfkPrWSUbmrbwnmiaG9FxSzM1g2vl6dZK7yNryptpTP5Jkro1p2e7AzyffipO3yaN2oIf7UjZpaV7bHZmmsRONAGW7mnstaq5rs2ntBOyh74EWayAhoEk0Dxaq3s9OiObPfb7fd97JEo6InBcMKDNrQxOBHsaKRsSXFGrdx2NKiRe5UdyGa2vVkcLAa0zi6Bu4YlY0CSEefw1QICwwISzgO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 201));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "yktk5oh0EGNYE7qaw72NWiFNrNFqmCldFAFvpi1pPVTzMxGE636uRbKHvbBDsAmstZbrqjj0B7v8OwUcK0JP7K2nAanF7vp9f7E2gbb0I7ozEfaGjHRmMbD1MCsKpnol1Hn9Kd1gLGrQCNaK4XxYJvMecGBicycI6ioK4rKHZZfrAMWTYpn67H6KRpw9J9gyRnsxclqjQqlzLokZ1L8d6wnZB0kClcdDWP77p8wREaCsMMKPtxbqQYc6bDXxGMshG"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "oIXKaGSM8U9pral9jTdcF"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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

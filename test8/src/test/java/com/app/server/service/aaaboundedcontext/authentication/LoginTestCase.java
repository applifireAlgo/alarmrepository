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
import com.app.server.repository.aaaboundedcontext.authentication.LoginRepository;
import com.app.shared.aaaboundedcontext.authentication.Login;
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
import com.app.shared.aaaboundedcontext.authentication.User;
import com.app.server.repository.aaaboundedcontext.authentication.UserRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.PassRecovery;
import com.app.shared.aaaboundedcontext.authentication.Question;
import com.app.server.repository.aaaboundedcontext.authentication.QuestionRepository;
import com.app.shared.aaaboundedcontext.authentication.UserData;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        User user = new User();
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("sGZjxPk0K0rSM9McVLelxIt0m80jxGjDhBoft1MUrFo0k2tNli");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("QlxDA5yfM9HSl6qncjpCF4X7niaFQ8hRsNNM1uoztoMxxpruGj");
        useraccessdomain.setDomainDescription("sdgdGsvUnnayWuBhAuYzSk1qPPiTubTh3mkoxsxrklN5TxxOo9");
        useraccessdomain.setDomainName("tybcJqaP4R4qtbkhexvbxqOU7BThLfg8ZKjRnrNE5pHyHfQd5o");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
        }
        map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("JpkzFw2pFkIrJEa82Dxmuf1nMDoBwhxZM4HA39qycR7eZObI5C");
        useraccesslevel.setLevelDescription("YbrUtQhP3XXqPTlwLmZsmMjY0orRruGgKaTI7oIWHlXS1jFp7U");
        useraccesslevel.setLevelIcon("xiENZJ449mqi5lO5tpBjw87ZkY59oRoW9rSYfK9xCSc10nc7Nk");
        useraccesslevel.setLevelName("LKFDqWUaU1I0Tipdijn95vXWiga4UrWljUXVmukbSbkqgVWxuk");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
        }
        map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1460104257905l));
        user.setAllowMultipleLogin(1);
        user.setSessionTimeout(3049);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1460104257905l));
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setUserAccessCode(11248);
        user.setChangePasswordNextLogin(1);
        user.setGenTempOneTimePassword(1);
        user.setPasswordAlgo("xo6oHZcHN5Do2HGJcEUyINow5mSKHmTeNrJzToIk3e9roTr5ci");
        user.setMultiFactorAuthEnabled(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("9LBVDLHCwZTDdirPQDbQomhGPvvqeOSuMBdo3KLZYzyLysvcbG");
        question.setQuestion("d1q6Ftxl25QxNzQSfVwhOlrvldamXHhrsaKFfw00OT4NJ5HpWl");
        question.setQuestionDetails("7ZbtXZjJJX");
        question.setLevelid(6);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
        }
        map.put("QuestionPrimaryKey", question._getPrimarykey());
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("as9lipqAozNYNrIYEjGpg1efSlWMuqR3qen6FfFg0PiMpe5IEb");
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("4Yce3nf7BGzxvhf1BaB67HXrSDZvpGl4");
        userdata.setOneTimePassword("OfPN0RaLJPFVp7MMREFNGCsjQ90B5RKq");
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(9);
        userdata.setPassword("dtgBZ6wpYDZgvx7uE14ZHfXLnqtDkldeXgtkMsL3cAFSBuepnj");
        userdata.setLast5Passwords("VFtKwBsVn4726mbsRR8ZJBX4b4zZWsO9GsUvpnwtSXGYkie0V6");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1460104258173l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeTitle("RMZJ8uBsQmryZ2IzZFdqUr10KcTs5bb2puzTM7WGAfB5waUBCH");
        corecontacts.setPhoneNumber("IjgjjuDjkCFbevwrN9vN");
        corecontacts.setLastName("kmLxLf69izMTMQRdUY15lmeL7qwv2JfIvSRYZqEWNUTSQs6ONJ");
        Language language = new Language();
        language.setLanguageType("nXLLQB0uw8WVeSDBGzkxUyuZCGNpvH7I");
        language.setAlpha3("L3k");
        language.setAlpha2("RD");
        language.setLanguageIcon("HM98F2Jcp4T8aRN8KMetVzamDm4usJZDJ9lKcinY2jaHBvakNp");
        language.setAlpha4("tglH");
        language.setLanguageDescription("mqPpz8G4sHe3fxPOBERjUth6e3WzqVLVAKm2GwMJSVdGoZwyqG");
        language.setAlpha4parentid(11);
        language.setLanguage("K5xgZE0wexB5Wkm9CoJqZJrQQvBORf0UBbg0gqohor1KNE6mpK");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
        }
        map.put("LanguagePrimaryKey", language._getPrimarykey());
        Gender gender = new Gender();
        gender.setGender("fDbWLt2fmIeCNToQbjPkFrigNYyKG6c1Qb8iIdrAMptlxnTXAs");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
        }
        map.put("GenderPrimaryKey", gender._getPrimarykey());
        Title title = new Title();
        title.setTitles("Vw0dOVsgB4XUivpxVc2t5hHAbDhJsj2t4ljwfoP64M3DUxRyqi");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
        }
        map.put("TitlePrimaryKey", title._getPrimarykey());
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("dCRaIcTRKgwV5KDrj7fVAStEYrzajjCzRDmgJxhWbWf0J3TZs3");
        timezone.setCountry("ugjFrcZOf46oNBMzn8TX8pc3fXft2FiJS15rQT2xiL8rrU6tLW");
        timezone.setGmtLabel("UfmzALukmGgXrYmS0VtdA5Lqu2PzXqfrcNay5DXJovbQVwuaEb");
        timezone.setCities("ho3xREcYVGnAXhwFiNB8m2NsMwKTiTc9oX35ny4c84scWkxgaD");
        timezone.setUtcdifference(3);
        corecontacts.setNativeTitle("yAkW7oWZSHkOC2QVkypKs6gtimUSFHZFyx6mlSoeOnRVdvwpuo");
        corecontacts.setPhoneNumber("2KgnvhQr0MBc6PwWI4BB");
        corecontacts.setLastName("SU250q3ZVyEdgfN8dnVKxfABkJefxl8869isJqUDEPyrXVDcpW");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("zbu3MV8dAM3i5gWnxZuaJVzsvhC6LEwQPQUALFgOErGFWZE0sf");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460104258398l));
        corecontacts.setFirstName("EKo9mHrtcKHzxOhQnps6flRlx3kw54mm7MFMbhOLtSwvT4qzQa");
        corecontacts.setEmailId("B1svaF35QgMDNjQTg6GgDym2YFaxerWlzppntX9Lui8xVAXurV");
        corecontacts.setNativeLastName("8aH1n3ao5hBJ6F5j42kg70j1wt98q5rhSQpG9F6dXNsRFramzQ");
        corecontacts.setAge(70);
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460104258398l));
        corecontacts.setNativeMiddleName("4Y3S5KZzK78zmlwcVmtYjq8y9TDJegNNgl6YA2t16xvCf16AzK");
        corecontacts.setNativeFirstName("JPdHohSmguWw3yjbiBpAIEZXSBluPslYoGPXpWn6b3QNDYM5oo");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("n2dyJz3J98xDZNi5gsWtaTRKnf3rdy33H5pxhyauck03uYi46U");
        communicationgroup.setCommGroupName("kCT6mM7EyZEqzfgJ6qA8HIjHIy5eJAsZ3auHRULLFUhNPnlUk3");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
        }
        map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("ngDrfbtboGvAOvxoD7lozVA6PNZnVdVU39anj5zaidYDqAxt6I");
        communicationtype.setCommTypeDescription("cLcwbz1Dmum0Ug8Vg4mYA7kOv9fbEvbbceyV8BrwOySywr0BDQ");
        communicationtype.setCommTypeName("UOYpejP56iZYqXsBiiUjQuhemyFDHQitMJlgpzm4QzfKjqtPh2");
        communicationtype.setCommTypeDescription("k38rvnY8nSutGlYaTOlBrmS2f7xlHbgVec7i9KtsKHH74HfsQw");
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
        address.setAddress2("eCcEfNLIWTXNyKeE3nROvDtAjbcDBBvKS2ajSnJolNPs24LvTb");
        address.setLongitude("lh1BTF4dWbbs8uPiMjKYcL78ffPpR1jHHVfmAJLk1EwalZlqiz");
        address.setLatitude("2iz1ovDUTdwGSPKb4GnwCjAJH39V88iYRj7KpJTMMRIywcbpdd");
        address.setAddressLabel("Yq3fWqoEO2o");
        address.setAddress3("sD2ixFkhMjs3mRvrNTxnldMs7rALrWv3teq3WWSAV3nCCpsmr2");
        address.setAddress1("fre3wvV1KXjPkDR7bjvAaG9TcPYaE7lc4E3ky4w1VJHLbavlQg");
        State state = new State();
        state.setStateCapitalLatitude(5);
        state.setStateCode(2);
        state.setStateCodeChar2("7sDZ3VT6YcSCg0yytYqBBKwlsCtd9G2h");
        state.setStateFlag("2nSgzIK73nfjuvZDlO1xXn4Pg0c5QiKpHfTFl3PRYhtm5xru7x");
        Country country = new Country();
        country.setCapital("dC8pxP4Ay2BFob86UvGt2HzMG7nQW1AM");
        country.setCurrencyCode("mWf");
        country.setCountryName("QzlzZ3hwj3h0HibuNwElEOYzcFHAstkJ2XhefOvQ9z7n5UI4Pk");
        country.setCapitalLatitude(4);
        country.setCapitalLongitude(4);
        country.setCountryCode1("L9D");
        country.setIsoNumeric(5);
        country.setCurrencyName("vWRkZiAvpClRw9OmGIpQezQCOiDMLvZLVs12lvBVtBv9Nf3sp7");
        country.setCurrencySymbol("zlZwMbQQWJhvia8m27c2nd1puMA0bDcK");
        country.setCountryFlag("u3hbOcvUd8kpAyFyOFSarbdEftWSHLrrYciAy68uB4Rd5nrxOe");
        country.setCountryCode2("bag");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
        }
        map.put("CountryPrimaryKey", country._getPrimarykey());
        state.setStateCapitalLatitude(7);
        state.setStateCode(2);
        state.setStateCodeChar2("O44u2zPFghDWyiVwlqBjyMngYbYrGeg3");
        state.setStateFlag("RtRjiWieqTXy4FYvHxOAtaVfIeyGfqeOsqjqgNrimRwvws6JQZ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("PnPWvixUJuZopdmoXNjCR1Ngq8Hok8etVmaiG2AXXi4V4x4pH0");
        state.setStateCapital("xNjdrp5GWS2whrbR3R2uQojitLxqKNP9COxNVWspJWMhSXxcWV");
        state.setStateCapitalLongitude(7);
        state.setStateName("4xAiTMS1YkDlB57qKNJAyE4DlYRCcHAeuIhZn4rsxYa7dVmFIK");
        state.setStateCodeChar3("f450rlGjrBKBhbxXPmQ7tahjCiD9RpiC");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
        }
        map.put("StatePrimaryKey", state._getPrimarykey());
        City city = new City();
        city.setCityName("jWrdtNuSBx3qnSo9uxUn22yndCJdREVQDFsjACB5Q1Rw32Xc9h");
        city.setCityFlag("zK0iFmbglWeAY11LoWsC0m4soWJbrsG3RRzEfvshNdulgP7bVf");
        city.setCityName("f61lcIBDzXnT3WGJHwQbjkaUqzUHMMbbyRGcDEePA3u3m5jQ14");
        city.setCityFlag("9De0q32okjgffsrJYHXMnM4LxgCZQg4wiTTS3iEGnmty7DMaqM");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("b9sN9WBMyzrPqU5fJDLM8de7yIbeAgWuLRHpc2DPZGWkt6RCTM");
        city.setCityLatitude(11);
        city.setCityCode(1);
        city.setCityLongitude(9);
        city.setCityCodeChar2("8yB00AGkzFRZdNgZNKdpT331NqfSLFKs");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
        }
        map.put("CityPrimaryKey", city._getPrimarykey());
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("zyj9qRqXBpYjyUhEigkt2prfXLO87TD2oKYD19oPGSZEAotlww");
        addresstype.setAddressType("FvHPCdSsNkYAy1RLT2f1saUzsZgPn2ZKd5guMjSjy6bC5zuGKh");
        addresstype.setAddressTypeDesc("Hfoxd4PfhTntXIrigl2ofSBzHVtJ5PFYWu92NPy0zd67LPyZfI");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
        }
        map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        address.setAddress2("GLuvgtVPnEn9CuXjsBjzXCHkcACGZHkZ7GlzzoWGtvN6pNr75t");
        address.setLongitude("B0rcx1Eb81S3X2kkVs2IofnLVCYaYr1POrXbm0ycFjpBLkRrMa");
        address.setLatitude("LWIL6SMNyhnwEaiwyOAfoHh1s4nirBdRdPFRaZAOCuR19BOdU4");
        address.setAddressLabel("sbcXnVaBgrY");
        address.setAddress3("HA1pzMAx8IjGqemq3t2GR1p0WX066bUihXrn2rH7Xx77FNGOzw");
        address.setAddress1("pjKIwGo7vBuIgjnHU49qfWvwd7MplQKNb6qtilAss4kSe9kiER");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setZipcode("7W5szd");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setServerAuthText("FiWabXkSdBYI6ZQ9");
        login.setIsAuthenticated(true);
        login.setServerAuthImage("Ntnc3JrcsAUzaq73ZlifmfDeQGwMxn5u");
        login.setFailedLoginAttempts(8);
        user.setUserId(null);
        login.setUser(user);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setLoginId("nOh9rEDaEYt3iXMLfOw9B9SekRfOKhI9yH2Ui5iIlXJaXhWyqG");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthText("s5NykoxZPuVsLLUC");
            login.setServerAuthImage("XOyRwUKBtdYVIZLAfV32jq4DWtgR4wiB");
            login.setFailedLoginAttempts(3);
            login.setVersionId(1);
            login.setLoginId("8KHJyIDn8NlFTB5Dfk75HCwnYELqrcPxoPrrSYfYnqgKSMkvHj");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "R7eUXksKkC8mmOsIv9QWhvySDOk114uH9NIK57ZRzjPNatWszRS8Xf80vMjUtNZKQnFEVeJV5A5rdwxyHA3M26kkHGtd5NKFI3ifUjnZyYvc2nsfpjHNnpJFxyAg00MAfuq62PYO0h7QFajY50mY585bThFR05sQu87VnHx2r5uXW4pcrWtNdeT1hRYAMpW5BxJXUnbpr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "m7SN5fOvRCJCEilgFLh66bSbpuFT66aLN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "1WYpzF03FJf2YuMIV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = login.getClass().getDeclaredField(contraints.getFieldName());
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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

package com.elizabeth.exhibition.injector;

import com.elizabeth.exhibition.command.Command;
import com.elizabeth.exhibition.command.exhibition.ExhibitionCommand;
import com.elizabeth.exhibition.command.exhibition.IndexCommand;
import com.elizabeth.exhibition.command.exhibition.ProfileCommand;
import com.elizabeth.exhibition.command.purchase.BuyingCommand;
import com.elizabeth.exhibition.command.purchase.PayingCommand;
import com.elizabeth.exhibition.command.purchase.PurchaseCommand;
import com.elizabeth.exhibition.command.purchase.RemoveTicketCommand;
import com.elizabeth.exhibition.command.user.LoginCommand;
import com.elizabeth.exhibition.command.user.LogoutCommand;
import com.elizabeth.exhibition.command.user.SignUpCommand;
import com.elizabeth.exhibition.dao.ExpositionDao;
import com.elizabeth.exhibition.dao.UserDao;
import com.elizabeth.exhibition.dao.impl.ExhibitionDaoImpl;
import com.elizabeth.exhibition.dao.impl.ExpositionDaoImpl;
import com.elizabeth.exhibition.dao.impl.TicketDaoImpl;
import com.elizabeth.exhibition.dao.impl.UserDaoImpl;
import com.elizabeth.exhibition.domain.Exhibition;
import com.elizabeth.exhibition.domain.Exposition;
import com.elizabeth.exhibition.domain.Ticket;
import com.elizabeth.exhibition.domain.User;
import com.elizabeth.exhibition.entity.ExhibitionEntity;
import com.elizabeth.exhibition.entity.ExpositionEntity;
import com.elizabeth.exhibition.entity.TicketEntity;
import com.elizabeth.exhibition.entity.UserEntity;
import com.elizabeth.exhibition.service.ExhibitionService;
import com.elizabeth.exhibition.service.ExpositionService;
import com.elizabeth.exhibition.service.Localization;
import com.elizabeth.exhibition.service.PasswordEncryptor;
import com.elizabeth.exhibition.service.TicketService;
import com.elizabeth.exhibition.service.UserService;
import com.elizabeth.exhibition.service.impl.ExhibitionServiceImpl;
import com.elizabeth.exhibition.service.impl.ExpositionServiceImpl;
import com.elizabeth.exhibition.service.impl.TicketServiceImpl;
import com.elizabeth.exhibition.service.impl.UserServiceImpl;
import com.elizabeth.exhibition.service.mapper.ExhibitionMapper;
import com.elizabeth.exhibition.service.mapper.ExpositionMapper;
import com.elizabeth.exhibition.service.mapper.Mapper;
import com.elizabeth.exhibition.service.mapper.TicketMapper;
import com.elizabeth.exhibition.service.mapper.UserMapper;
import com.elizabeth.exhibition.service.validator.CreditCardValidator;
import com.elizabeth.exhibition.service.validator.UserValidator;
import com.elizabeth.exhibition.service.validator.impl.CreditCardValidatorImpl;
import com.elizabeth.exhibition.service.validator.impl.UserValidatorImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationInjector {
    private static final PasswordEncryptor PASSWORD_ENCRYPTOR = new PasswordEncryptor();
    private static final Localization LOCALIZATION = new Localization();
    public static final String[] LANGUAGES = new String[] {"ru", "en"};
    public static final String LANGUAGE_DEFAULT = "en";

    private static final UserDao USER_DAO = new UserDaoImpl();
    private static final ExhibitionDaoImpl EXHIBITION_DAO = new ExhibitionDaoImpl();
    private static final ExpositionDao EXPOSITION_DAO = new ExpositionDaoImpl();
    private static final TicketDaoImpl TICKET_DAO = new TicketDaoImpl();

    private static final UserValidator USER_USER_VALIDATOR = new UserValidatorImpl();
    private static final CreditCardValidator CREDIT_CARD_VALIDATOR= new CreditCardValidatorImpl();

    private static final Mapper<UserEntity, User> USER_MAPPER = new UserMapper(PASSWORD_ENCRYPTOR);
    private static final Mapper<ExhibitionEntity, Exhibition> EXHIBITION_MAPPER = new ExhibitionMapper();
    private static final Mapper<ExpositionEntity, Exposition> EXPOSITION_MAPPER = new ExpositionMapper();
    private static final Mapper<TicketEntity, Ticket> TICKET_MAPPER = new TicketMapper();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER);
    private static final ExhibitionService EXHIBITION_SERVICE = new ExhibitionServiceImpl(EXHIBITION_DAO, EXHIBITION_MAPPER);
    private static final ExpositionService EXPOSITION_SERVICE = new ExpositionServiceImpl(EXPOSITION_DAO, EXPOSITION_MAPPER);
    private static final TicketService TICKET_SERVICE = new TicketServiceImpl(TICKET_DAO, TICKET_MAPPER);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE, TICKET_SERVICE, LOCALIZATION);
    private static final Command SIGN_UP_COMMAND = new SignUpCommand(USER_SERVICE, LOCALIZATION);
    private static final Command LOGOUT_COMMAND = new LogoutCommand();
    private static final Command INDEX_COMMAND = new IndexCommand(EXHIBITION_SERVICE, LOCALIZATION);
    private static final Command EXHIBITION_COMMAND = new ExhibitionCommand(EXHIBITION_SERVICE, EXPOSITION_SERVICE, LOCALIZATION);
    private static final Command BUYING_COMMAND = new BuyingCommand(TICKET_SERVICE, LOCALIZATION);
    private static final Command PURCHASE_COMMAND = new PurchaseCommand(TICKET_SERVICE, EXHIBITION_SERVICE, LOCALIZATION);
    private static final Command REMOVE_TICKET_COMMAND = new RemoveTicketCommand(TICKET_SERVICE, LOCALIZATION);
    private static final Command PAYING_COMMAND = new PayingCommand(TICKET_SERVICE, LOCALIZATION);
    private static final Command PROFILE_COMMAND = new ProfileCommand(TICKET_SERVICE, EXHIBITION_SERVICE, LOCALIZATION);

    private static final Map<String, Command> COMMANDS = initCommands();

    private static Map<String, Command> initCommands() {
        Map<String, Command> authorizationCommands = new HashMap<>();
        authorizationCommands.put("/login", LOGIN_COMMAND);
        authorizationCommands.put("/logout", LOGOUT_COMMAND);
        authorizationCommands.put("/signUp", SIGN_UP_COMMAND);
        authorizationCommands.put("/index", INDEX_COMMAND);
        authorizationCommands.put("/exhibition", EXHIBITION_COMMAND);
        authorizationCommands.put("/buying", BUYING_COMMAND);
        authorizationCommands.put("/purchase", PURCHASE_COMMAND);
        authorizationCommands.put("/removeTicket", REMOVE_TICKET_COMMAND);
        authorizationCommands.put("/pay", PAYING_COMMAND);
        authorizationCommands.put("/profile", PROFILE_COMMAND);

        return Collections.unmodifiableMap(authorizationCommands);
    }

    private static ApplicationInjector applicationInjector;

    private ApplicationInjector() { }

    public static ApplicationInjector getInstance() {
        if(applicationInjector == null) {
            synchronized (ApplicationInjector.class) {
                if (applicationInjector == null) {
                    applicationInjector = new ApplicationInjector();
                }
            }
        }
        return applicationInjector;
    }

    public static Map<String, Command> getCommands() {
        return COMMANDS;
    }

    public static UserValidator getUserValidator() {
        return USER_USER_VALIDATOR;
    }
    public static CreditCardValidator getCreditCardValidator() { return CREDIT_CARD_VALIDATOR; }
}

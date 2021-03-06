package eu.getmangos.mapper;

import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import eu.getmangos.dto.AccountDTO;
import eu.getmangos.dto.AccountEventDTO;
import eu.getmangos.dto.Expansion;
import eu.getmangos.dto.Locale;
import eu.getmangos.dto.SecurityLevel;
import eu.getmangos.dto.srp.RegistrationDTO;
import eu.getmangos.entities.Account;

@Mapper(componentModel = "cdi")
public interface AccountMapper {

    AccountDTO map(Account account);

    @Mapping(target = "v", ignore = true)
    @Mapping(target = "s", ignore = true)
    @Mapping(target = "shaPassHash", ignore = true)
    Account map(AccountDTO dto);

    @Mapping(source = "salt", target = "s")
    @Mapping(source = "verifier", target = "v")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shaPassHash", ignore = true)
    @Mapping(target = "gmLevel", ignore = true)
    @Mapping(target = "sessionKey", ignore = true)
    @Mapping(target = "joinDate", ignore = true)
    @Mapping(target = "lastIP", ignore = true)
    @Mapping(target = "failedLogins", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "activeRealmId", ignore = true)
    @Mapping(target = "locale", ignore = true)
    @Mapping(target = "os", ignore = true)
    @Mapping(target = "mutetime", ignore = true)
    @Mapping(target = "playerBot", ignore = true)
    Account map(RegistrationDTO registration);

    List<AccountDTO> map(List<Account> list);

    @Mapping(target = "event", source = "event")
    AccountEventDTO map(Account account, AccountEventDTO.Event event);

    default Date map(Long value) {
        return new Date(value);
    }

    default Long map(Date date) {
        return date.getTime();
    }

    default SecurityLevel mapSecLevel(int level) {
        return SecurityLevel.convert(level);
    }

    default short mapSecLevelPrim(SecurityLevel level) {
        return (short) level.level;
    }

    default Expansion mapExp(int exp) {
        return Expansion.convert(exp);
    }

    default short mapExpPrim(Expansion exp) {
        return (short) exp.exp;
    }

    default Locale mapLocale(int loc) {
        return Locale.convert(loc);
    }

    default short mapLocalePrim(Locale loc) {
        return (short) loc.loc;
    }
}

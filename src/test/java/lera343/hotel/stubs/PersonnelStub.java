package lera343.hotel.stubs;

import lera343.hotel.entity.Personnel;
import java.util.Date;

public final class PersonnelStub {
    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PATRONIC = "patronic";
    public static final String EMAIL = "email";
    public static final String NUMBER = "number";
    public static final String POSITION = "position";
    public static final String DESCRIPTION = "description";
    public static final Date BIRTHDAY = new Date();
    public static final Date STARTEDWORK = new Date();
    public static Personnel getRandomPersonnel() {
        return Personnel.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .surname(SURNAME)
                .patronic(PATRONIC)
                .number(NUMBER)
                .position(POSITION)
                .birthday(BIRTHDAY)
                .email(EMAIL)
                .startedWork(STARTEDWORK)
                .build();
    }
}

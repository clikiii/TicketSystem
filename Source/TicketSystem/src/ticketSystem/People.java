package ticketSystem;

import ticketSystem.database.Database;

public interface People {
    public People register(Database db, String username, String password);
    public People login(Database db, String username, String password);
    public People changePwd(Database db, String username, String password);
    public Boolean deleteMe(Database db, String username, String password);
}

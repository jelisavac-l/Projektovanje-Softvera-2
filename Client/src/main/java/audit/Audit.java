package audit;

import domain.Evaluator;

/**
 *
 * @author luka
 */
public class Audit {
    
    private static Evaluator loggedUser;

    public static Evaluator getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(Evaluator e) {
        if(loggedUser == null) loggedUser = e;
    }
    
    private Audit() {
        
    }
    
    
    
}

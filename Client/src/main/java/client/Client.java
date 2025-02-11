package client;

import audit.Audit;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import comm.*;
import domain.*;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.FormLogIn;

public class Client {
    
    
    private static Socket socket;
    private static Sender sender;
    private static Receiver receiver;
    public static Dotenv dotenv;
    
    public static void main(String[] args) {
        
        dotenv = Dotenv.load();
        initConnection();
        initGui();
        
    }
    
    public static void popupInfo(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sistem", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void popupWarning(String msg) {
       JOptionPane.showMessageDialog(null, msg, "Sistem", JOptionPane.WARNING_MESSAGE); 
    }
    
    public static void popupError(String msg) {
       JOptionPane.showMessageDialog(null, msg, "Sistem", JOptionPane.ERROR_MESSAGE); 
    }

    private static void initGui() {
        FlatIntelliJLaf.setup();
        new FormLogIn().setVisible(true);
    }

    private static void initConnection() {
        try {
            socket = new Socket(dotenv.get("SERVER_IP"), Integer.parseInt(dotenv.get("SERVER_PORT")));
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            popupError("Konekcija neuspešna: " + ex.getMessage());
            System.exit(0);
        }
    }
    
    public static void endConnection() {
        try {
            Request request = new Request(Operation.STOP, 0);
            sender.send(request);
        } catch (Exception ex) {
            // Even in the event that this occurs, the exception is handled on
            // the server, allowing for continued execution.
            popupError("Nije moguće prekinuti konekciju (na civilizovan način): " + ex.getMessage());
        } finally {
            System.exit(0);
        }
    }
    
    public static Evaluator login(Evaluator evaluator) {
        Evaluator loggedIn = null;
        try {
            sender.send(new Request(Operation.LOGIN, evaluator));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            loggedIn = (Evaluator) response.getResult();
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return null;
        }
//        Audit.setLoggedUser(loggedIn);
        return loggedIn;
    }
    
    public static List<Evaluator> getEvaluatorList() {
        try {
            sender.send(new Request(Operation.EVALUATOR_GET, 1));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            return (List<Evaluator>) response.getResult();
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return null;
        }
        
    }
    
    
    public static List<Role> getEvaluatorRoles(Evaluator evaluator) {
        try{
            sender.send(new Request(Operation.EVALUATOR_GET_ROLES, evaluator));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            return (List<Role>) response.getResult();
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return null;
        }
        
    }
    
    public static void createEvaluator(Evaluator evaluator) {
        try{
            sender.send(new Request(Operation.REGISTER, evaluator));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            else popupInfo("OK!");
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return;
        }
    }
    
    public static void updateEvaluator(Evaluator e1, Evaluator e2) {
        List<Evaluator> evaluators = new LinkedList<>();
        evaluators.add(e1);
        evaluators.add(e2);
        try{
            sender.send(new Request(Operation.EVALUATOR_UPDATE, evaluators));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            else popupInfo("OK!");
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return;
        } 
    }
    
    public static List<Athlete> getAthleteList() {
        try {
            sender.send(new Request(Operation.ATHLETE_GET, 1));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            return (List<Athlete>) response.getResult();
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return null;
        }
        
    }

    public static List<Club> getClubList() {
        try {
            sender.send(new Request(Operation.CLUB_GET, 1));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            return (List<Club>) response.getResult();
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return null;
        }
    }
    
    public static void createClub(Club club) {
        try{
            sender.send(new Request(Operation.CLUB_NEW, club));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            else popupInfo("OK!");
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return;
        }
    }
    
    public static void updateClub(Club c1, Club c2) {
        List<Club> clubs = new LinkedList<>();
        clubs.add(c1);
        clubs.add(c2);
        try{
            sender.send(new Request(Operation.CLUB_UPDATE, clubs));
            Response response = (Response) receiver.receive();
            if(response.getException() != null) throw response.getException();
            else popupInfo("OK!");
        } catch (Exception ex) {
            popupError(ex.getMessage());
            return;
        } 
    }
    
    
}

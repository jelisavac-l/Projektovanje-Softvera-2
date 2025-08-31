import comm.*;
import controllers.*;
import domain.*;

import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread{

    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;
    private Evaluator loggedIn;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
        threadPrint("This thread is connected to: " + socket.getInetAddress());
        loggedIn = null;
    }

    private void threadPrint(String msg) {
        System.out.println("[THREAD " + this.getId() + "]\t" + msg);
    }

    @Override
    public void run() {
        while(true) {
            try {

                Request request = (Request) receiver.receive();

                if(request.getOperation() == Operation.STOP) {
                    threadPrint("Ending connection with: " + socket.getInetAddress());
                    socket.close();
                    return;
                }

                Response response = new Response();

                try {
                    response.setResult(handleRequest(request));
                } catch (Exception e) {
                    response.setException(e);
                } finally {
                    sender.send(response);
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * A method that processes the request and returns the result of the requested operation.
     * @param request A request from the client.
     * @return A value of 0 for operations that do not return a value (indicating success),
     * and an object for operations that return a value.
     * @throws Exception On operation failure. This exception should be sent to client.
     */
    private synchronized Object handleRequest(Request request) throws Exception {
        Object result = null;
        threadPrint("Client has requested " + request.getOperation());
        // Todo: proslediti svim operacijiama prazan response pa nek one popune
        switch (request.getOperation()) {

            case PING: {
                return "PONG";
            }
            
            case LOGIN: {
                Evaluator temp = (Evaluator) request.getArgument();
                return EvaluatorController.login(temp);
            }
            
            case ACTIVITY_GET: {
                // Za SVE zivo implementitrati getList(SearchCriteria sc :((((( )
                // SearchCriteria(User.class, u)
                return ActivityController.getList();
            }
            
            case ACTIVITY_NEW: {
                Activity temp = (Activity) request.getArgument();
                return ActivityController.add(temp);
            }
            
            case ACTIVITY_UPDATE: {
                Activity activity = (Activity) request.getArgument();
                return ActivityController.update(activity);
            }
            
            case ACTIVITY_DELETE: {
                Activity temp = (Activity) request.getArgument();
                return ActivityController.delete(temp);
            }
            
            case ATHLETE_GET: {
                return AthleteController.getList();
            }
            
            case ATHLETE_NEW: {
                Athlete temp = (Athlete) request.getArgument();
                return AthleteController.add(temp);
            }
            
            case ATHLETE_UPDATE: {
                Athlete athlete = (Athlete) request.getArgument();
                return AthleteController.update(athlete);
            }
            
            case ATHLETE_DELETE: {
                Athlete temp = (Athlete) request.getArgument();
                return AthleteController.delete(temp);
            }
            
            case CLUB_GET: {
                return ClubController.getList();
            }
            
            case CLUB_GET_BY_ID: {
                Long id = (Long) request.getArgument();
                return ClubController.getById(id);
            }
            
            case CLUB_NEW: {
               Club temp = (Club) request.getArgument();
               return ClubController.add(temp);
            }
            
            case CLUB_UPDATE: {
                Club club = (Club) request.getArgument();
                return ClubController.update(club);
            }
            
            case CLUB_DELETE: {
                Club club = (Club) request.getArgument();
                return ClubController.delete(club);
            }
            
            case ER_GET: {
                return ERController.getList();
            }
            
            case ER_NEW: {
                // FIXME: Not needed
                throw new UnsupportedOperationException("Not implemented!");
            }
            
            case ER_START: {
                @SuppressWarnings("unchecked")
                List<Object> args = (List<Object>) request.getArgument();
                Evaluator evaluator = (Evaluator) args.get(0);
                Role role = (Role) args.get(1);
                return EvaluatorController.startRole(evaluator, role);
            }
            
            case ER_END: {
                @SuppressWarnings("unchecked")
                List<Object> args = (List<Object>) request.getArgument();
                Evaluator evaluator = (Evaluator) args.get(0);
                Role role = (Role) args.get(1);
                return EvaluatorController.endRole(evaluator, role);
            }
            
            case EVALUATION_GET: {
                return EvaluationController.getList();
            }
            
            case EVALUATION_NEW: {
                Evaluation temp = (Evaluation) request.getArgument();
                return EvaluationController.add(temp);
            }
            
            case EVALUATION_UPDATE: {
//                @SuppressWarnings("unchecked")
//                List<Evaluation> evaluations = (List<Evaluation>) request.getArgument();
//                EvaluationController.update(evaluations.get(0), evaluations.get(1));
//                return 0;
                throw new UnsupportedOperationException("Not implemented!");
            }
            
            case EVALUATION_DELETE: {
//                Evaluation temp = (Evaluation) request.getArgument();
//                EvaluationController.delete(temp);
//                return 0;
                throw new UnsupportedOperationException("Not implemented!");
            }

            case EVALUATION_INVALIDATE: {
                Evaluation temp = (Evaluation) request.getArgument();
                return EvaluationController.invalidate(temp);
            }

            case EVALUATOR_GET: {
                return EvaluatorController.getList();
            }
            
            case EVALUATOR_UPDATE: {
                Evaluator evaluator = (Evaluator) request.getArgument();
                return EvaluatorController.update(evaluator);
            }
            
            case EVALUATOR_DELETE: {
                Evaluator temp = (Evaluator) request.getArgument();
                return EvaluatorController.delete(temp);
            }
            
            case EVALUATOR_GET_ROLES: {
                Evaluator temp = (Evaluator) request.getArgument();
                return EvaluatorController.getAllRoleList(temp);
            }
            
            case ROLE_GET: {
                return RoleController.getList();
            }
            
            case ROLE_NEW: {
                Role temp = (Role) request.getArgument();
                return RoleController.add(temp);
            }
            
            case ROLE_UPDATE: {
                Role role = (Role) request.getArgument();
                return RoleController.update(role);
            }
            
            case ROLE_DELETE: {
                Role temp = (Role) request.getArgument();
                return RoleController.delete(temp);
            }
        }
        return result;
    }
}

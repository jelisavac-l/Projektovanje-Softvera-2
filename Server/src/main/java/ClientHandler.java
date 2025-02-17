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
        switch (request.getOperation()) {

            case PING: {
                return "PONG";
            }
            
            case LOGIN: {
                Evaluator temp = (Evaluator) request.getArgument();
                Evaluator found = EvaluatorController.getByCredentials(temp);
                if(found == null) throw new Exception("User not found!");

                // Assign that user to this thread.
                loggedIn = found;

                return found;
            }
            
            case REGISTER: {
                Evaluator temp = (Evaluator) request.getArgument();
                EvaluatorController.add(temp);
                return 0;
            }
            
            case ACTIVITY_GET: {
                // Za SVE zivo implementitrati getList(SearchCriteria sc :((((( )
                // SearchCriteria(User.class, u)
                return ActivityController.getList();
            }
            
            case ACTIVITY_NEW: {
                Activity temp = (Activity) request.getArgument();
                ActivityController.add(temp);
                return 0;
            }
            
            case ACTIVITY_UPDATE: {
                @SuppressWarnings("unchecked")
                List<Activity> activities = (List<Activity>) request.getArgument();
                ActivityController.update(activities.get(0), activities.get(1));
                return 0;
            }
            
            case ACTIVITY_DELETE: {
                Activity temp = (Activity) request.getArgument();
                ActivityController.delete(temp);
                return 0;
            }
            
            case ATHLETE_GET: {
                return AthleteController.getList();
            }
            
            case ATHLETE_NEW: {
                Athlete temp = (Athlete) request.getArgument();
                AthleteController.add(temp);
                return 0;
            }
            
            case ATHLETE_UPDATE: {
                @SuppressWarnings("unchecked")
                List<Athlete> athletes = (List<Athlete>) request.getArgument();
                AthleteController.update(athletes.get(0), athletes.get(1));
                return 0;
            }
            
            case ATHLETE_DELETE: {
                Athlete temp = (Athlete) request.getArgument();
                AthleteController.delete(temp);
                return 0;
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
               ClubController.add(temp);
               return 0;
            }
            
            case CLUB_UPDATE: {
                @SuppressWarnings("unchecked")
                List<Club> clubs = (List<Club>) request.getArgument();
                ClubController.update(clubs.get(0), clubs.get(1));
                return 0;
            }
            
            case CLUB_DELETE: {
                // TODO: Implement here and in Controller
                throw new UnsupportedOperationException("Not implemented!");

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
                EvaluatorController.startRole(evaluator, role);
                return 0;
            }
            
            case ER_END: {
                @SuppressWarnings("unchecked")
                List<Object> args = (List<Object>) request.getArgument();
                Evaluator evaluator = (Evaluator) args.get(0);
                Role role = (Role) args.get(1);
                EvaluatorController.endRole(evaluator, role);
                return 0;
            }
            
            case EVALUATION_GET: {
                return EvaluationController.getList();
            }
            
            case EVALUATION_NEW: {
                Evaluation temp = (Evaluation) request.getArgument();
                EvaluationController.add(temp);
                return 0;
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
                EvaluationController.invalidate(temp);
                return 0;
            }

            case EVALUATOR_GET: {
                return EvaluatorController.getList();
            }
            
            case EVALUATOR_UPDATE: {
                @SuppressWarnings("unchecked")
                List<Evaluator> evaluators = (List<Evaluator>) request.getArgument();
                EvaluatorController.update(evaluators.get(0), evaluators.get(1));
                return 0;
            }
            
            case EVALUATOR_DELETE: {
                Evaluator temp = (Evaluator) request.getArgument();
                EvaluatorController.delete(temp);
                return 0;
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
                RoleController.add(temp);
                return 0;
            }
            
            case ROLE_UPDATE: {
                @SuppressWarnings("unchecked")
                List<Role> roles = (List<Role>) request.getArgument();
                RoleController.update(roles.get(0), roles.get(1));
                return 0;
            }
            
            case ROLE_DELETE: {
                Role temp = (Role) request.getArgument();
                RoleController.delete(temp);
                return 0;
            }
        }
        return result;
    }
}

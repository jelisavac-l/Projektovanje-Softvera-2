import comm.*;
import controllers.EvaluationController;
import controllers.EvaluatorController;
import domain.Evaluator;

import java.net.Socket;

public class ClientHandler extends Thread{

    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
        threadPrint("This thread is connected to: " + socket.getInetAddress());
    }

    private void threadPrint(String msg) {
        System.out.println("[ Th: " + this.getId() + " ] " + msg);
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
        
        switch (request.getOperation()) {
            
            case PING: {
                return "PONG";
            }
            
            case LOGIN: {
                Evaluator temp = (Evaluator) request.getArgument();
                Evaluator found = EvaluatorController.getByCredentials(temp);
                if(found == null) throw new Exception("User not found!");
                return found;
            }
            
            case REGISTER: {
                Evaluator temp = (Evaluator) request.getArgument();
                EvaluatorController.add(temp);
                return 0;
            }
            
            case ACTIVITY_GET: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ACTIVITY_NEW: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ACTIVITY_UPDATE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ACTIVITY_DELETE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ATHLETE_GET: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ATHLETE_NEW: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ATHLETE_UPDATE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ATHLETE_DELETE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case CLUB_GET: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case CLUB_GET_BY_ID: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case CLUB_NEW: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case CLUB_UPDATE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case CLUB_DELETE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ER_GET: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ER_NEW: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ER_START: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ER_END: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case EVALUATION_GET: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case EVALUATION_NEW: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case EVALUATION_UPDATE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case EVALUATION_DELETE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case EVALUATOR_GET: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case EVALUATOR_UPDATE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case EVALUATOR_DELETE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case EVALUATOR_GET_ROLES: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ROLE_GET: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ROLE_NEW: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ROLE_UPDATE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
            
            case ROLE_DELETE: {
                // TODO: Implement
                throw new UnsupportedOperationException("Not implemented!");
                
            }
        }
        return result;
    }
}

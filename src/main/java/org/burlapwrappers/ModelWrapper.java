package org.burlapwrappers;

import burlap.debugtools.DPrint;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.environment.EnvironmentOutcome;
import burlap.mdp.singleagent.model.SampleModel;
import org.deeplearning4j.gym.Client;
import org.deeplearning4j.gym.StepReply;

public class ModelWrapper implements SampleModel {

    int debugCode = 13093382;
    Client client;
    StateWrapperGenerator swg;
    boolean done =false;


    public ModelWrapper(Client client, StateWrapperGenerator swg){
        this.client = client;
        this.swg=swg;
    }

    public ModelWrapper(Client client){
        this.client = client;
        this.swg= new StateWrapperGenerator();
    }

    @Override
    public EnvironmentOutcome sample(State state, Action action) {
        DPrint.cl(debugCode, "The sample just accesses the environment disregarding the state passed to this method!!");
        StepReply sr = client.step(Integer.parseInt(action.actionName()));
        EnvironmentOutcome eo = new EnvironmentOutcome(swg.generateState(sr.getObservationOld()),action,swg.generateState(sr.getObservationNew()),sr.getReward(),sr.isDone());
        done=eo.terminated;
        return eo;
    }

    @Override
    public boolean terminal(State state) {
        DPrint.cl(debugCode, "The sample just checks if the environment has reached a termination state " +
                "disregarding the state passed to this method!!");
        return done;
    }
}
package org.burlapwrappers;

import burlap.debugtools.DPrint;
import burlap.mdp.auxiliary.DomainGenerator;
import burlap.mdp.core.Domain;
import burlap.mdp.core.TerminalFunction;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.action.UniversalActionType;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.environment.EnvironmentOutcome;
import burlap.mdp.singleagent.model.RewardFunction;
import burlap.mdp.singleagent.model.SampleModel;
import burlap.mdp.singleagent.oo.OOSADomain;
import org.deeplearning4j.gym.Client;
import org.deeplearning4j.rl4j.space.ActionSpace;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.gym.StepReply;

import java.util.ArrayList;
import java.util.List;

public class OOSADomainGeneratorWrapper implements DomainGenerator {

    Client client;

    protected int debugCode = 912349234;
    protected ModelWrapper mw;


    public OOSADomainGeneratorWrapper(Client client){
        this.client = client;
        this.mw = new ModelWrapper(client);
    }

    public OOSADomainGeneratorWrapper(Client client, StateWrapperGenerator swg){
        this.client = client;
        this.mw = new ModelWrapper(client, swg);
    }





    @Override
    public OOSADomain generateDomain() {

        OOSADomain d = new OOSADomain();
        DiscreteSpace as = (DiscreteSpace)client.getActionSpace();


        for(int i=0;i<as.getSize();i++){
            d.addActionType(new UniversalActionType(Integer.toString(i)));
        }

        d.setModel(this.mw);

        return d;
    }





}

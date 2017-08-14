package org.burlapdemo;

//import burlap.mdp.core.Domain;
import burlap.behavior.policy.Policy;
import burlap.behavior.policy.PolicyUtils;
import burlap.behavior.policy.RandomPolicy;
import burlap.behavior.singleagent.Episode;
import burlap.mdp.auxiliary.DomainGenerator;
import burlap.mdp.core.state.NullState;
import burlap.mdp.singleagent.environment.Environment;
import burlap.mdp.singleagent.environment.SimulatedEnvironment;
import burlap.mdp.singleagent.oo.OOSADomain;
import org.burlapwrappers.OOSADomainGeneratorWrapper;
import org.deeplearning4j.gym.Client;
import org.deeplearning4j.gym.ClientFactory;
import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.space.ActionSpace;
import org.deeplearning4j.rl4j.space.Box;
import org.deeplearning4j.rl4j.space.DiscreteSpace;

public class CartpoleTest {


    public static void main(String[] args) {
        Client<Box, Integer, DiscreteSpace> client = ClientFactory.build("CartPole-v0", true);

        client.monitorStart("/home/nakul/openai-gym-java/",true,
                true);

        client.reset();

        ActionSpace as = client.getActionSpace();

//        System.out.println(as.getSize());


        DomainGenerator dgen = new OOSADomainGeneratorWrapper(client);
        OOSADomain domain = (OOSADomain) dgen.generateDomain();

        System.out.println(client.getActionSpace().getSize());

        Policy p = new RandomPolicy(domain);

        Environment env = new SimulatedEnvironment(domain, NullState.instance);
//        env.resetEnvironment();

        Episode e = PolicyUtils.rollout(p, env,20);


        for(int i=0;i<e.actionSequence.size();i++){
            System.out.println("state: " + e.stateSequence.get(i).toString()+ ", action:" +
                    e.actionSequence.get(i).toString()+ ", next state: " + e.stateSequence.get(i+1).toString()
            + ", reward: " + e.rewardSequence.get(i));
        }



//        int i =0;
//        StepReply sr = null;
//
//        while(i<1000){
//            if(i==0){
//                sr = client.step((int)client.getActionSpace().randomAction());
//
//            }
//            else if(!sr.isDone()){
//
//                sr = client.step((int)client.getActionSpace().randomAction());
//
//            }
//            i++;
//
//        }

        client.monitorClose();

    }
}



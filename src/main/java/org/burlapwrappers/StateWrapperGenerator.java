package org.burlapwrappers;

import burlap.mdp.core.state.State;
import org.deeplearning4j.rl4j.space.Box;

import java.util.ArrayList;
import java.util.List;

public class StateWrapperGenerator{


    public StateWrapperGenerator(){
    }

    public State generateState(Object o) {
        return new StateWrapper(o);
    }


    public static class StateWrapper implements State{
        Object o;

        public StateWrapper(Object o){
            this.o=o;
        }

        @Override
        public List<Object> variableKeys() {
            List<Object> objects = new ArrayList<>();
            objects.add(o);
            return objects;
        }

        @Override
        public Object get(Object o) {
            return o;
        }

        @Override
            public State copy() {
            return new StateWrapper(o);
        }

        @Override
        public String toString() {
            double[] objectSpec = ((Box)o).toArray();
            String s ="";
            for(int i =0;i<objectSpec.length;i++){
                s+=objectSpec[i]+ ", ";
            }
            return s;
        }
    }



}
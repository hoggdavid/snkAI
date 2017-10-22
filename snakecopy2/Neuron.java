package snakecopy2;

import java.util.ArrayList;

public class Neuron {
	
	public ArrayList<Neuron> Predecessors;
	public ArrayList<Double> Weights;
	public double output;
	public double input;
	//public int schwelle;
	
	public Neuron(){
		Predecessors = new ArrayList<Neuron>();
		Weights = new ArrayList<Double>();
		output = 0;
		
		//schwelle = (int)(1*Math.random()+0.3);
	}
	
	public void connectTo(Neuron N, double weight){
		Predecessors.add(N);
		Weights.add(weight);
	}
	
	public void setInput(double newInput){
		input = newInput;
	}
	
	public double getOutputIn(){
		input = output;
		return output;
	}
	
	public double getOutput(){
		// stupid return input;
		return output;
	}	
	
	/*public void updateIn(){
		input = 0;
		int nPredecessors = Predecessors.size();
		
		// calculate input
		for (int i=0;i<nPredecessors;i++){
			input += Weights.get(i)*Predecessors.get(i).getOutput();
		}
		// calculate output
		output = input;
	}*/
	
	public void update(){
		input = output;
		int nPredecessors = Predecessors.size();
		
		// calculate input
		for (int i=0;i<nPredecessors;i++){
			input += Weights.get(i)*Predecessors.get(i).getOutput();
		}
		// calculate output
		output = input;
	}
	
}
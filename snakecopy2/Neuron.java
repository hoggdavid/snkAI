package snakecopy2;

import java.util.ArrayList;

public class Neuron {
	
	public ArrayList<Neuron> Predecessors;
	public ArrayList<Double> Weights;
	public double input;
	
	public Neuron(){
		Predecessors = new ArrayList<Neuron>();
		Weights = new ArrayList<Double>();
	}
	
	public void connectTo(Neuron N, double weight){
		Predecessors.add(N);
		Weights.add(weight);
	}
	
	public void setInput(double newInput){
		input = newInput;
	}
	
	public double getOutput(){
		//stupid
		return input;
	}
	

}

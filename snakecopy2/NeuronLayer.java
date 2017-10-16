package snakecopy2;

import java.util.ArrayList;

public class NeuronLayer {
	
	public ArrayList<Neuron> Neurons;
	
	public NeuronLayer(){
		Neurons = new ArrayList<Neuron>();
	}
	
	public int addNeuron(Neuron N){
		Neurons.add(N);
		return Neurons.size();
	}
	

}

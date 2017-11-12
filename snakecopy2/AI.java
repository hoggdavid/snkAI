package snakecopy2;
import snakecopy2.Neuron;
import snakecopy2.NeuronLayer;

public class AI implements Comparable<AI>{
	
	public int score;
	public double  mutationConst;
	public Neuron[] InputNeurons;
	//public static Neuron[] InputNeurons; ???
	public Neuron[] HiddenNeurons;
	public Neuron[] OutputNeurons;
	// public static Neuron[] OutputNeurons; ???
	public NeuronLayer[] Layers;
	
	/* Bias Neuron?
	 * input&output connect w int list inputs[] from Board 
	 */
	
	public void randomize(){
		for (int i=1;i<3;i++){
			for (int j=0;j<Layers[i].Neurons.size();j++){
				for (int k=0;k<Layers[i].Neurons.get(j).Weights.size();k++){
					double w = Layers[i].Neurons.get(j).Weights.get(k);
					w = w + mutationConst * (Math.random()*2-1);
					Layers[i].Neurons.get(j).Weights.set(k, w);
				}
			}
		}
	}
	
	public int compareTo(AI o) {
        int f1 = this.score;
        int f2 = o.score;
 
        if (f1 < f2)
            return 1;
        else if (f1 > f2)
            return -1;
        else
            return 0;
    }

	public void setInput(int num, double output){
		InputNeurons[num].setOutput(output);
	}
	
	public double getOutput(int num){
		return OutputNeurons[num].getOutput();
	}
	
	public void setScore(int newScore){
		score = newScore;
	}
	
	public void initialize(){
		
		int in, hid, out;
		
		/*for (in=0;in<100;in++){
			InputNeurons = new Neuron[]{
					new Neuron()
			}; 
		}
		
		for (hid=0;hid<5;hid++){
			HiddenNeurons = new Neuron[] {
					new Neuron()
			};
		}
		
		for (out=0;out<4;out++){
			OutputNeurons = new Neuron[]{
					new Neuron()
			};
		}*/
		
		//Error in AI.java:87 (initialize) & in GameCopy.java:108
		//Nichts funktioniert, Problem mit Neuronen korrekt in Liste einfügen
		
		InputNeurons = new Neuron[100];
		
		HiddenNeurons = new Neuron[5];
		
		OutputNeurons = new Neuron[4];
		
		// CONNECTING
		for (hid=0;hid<5;hid++){
			for (in=0;in<100;in++){
				HiddenNeurons[hid].connectTo(InputNeurons[in], 2*Math.random()-1.0);
			}
			
			Layers[1].addNeuron(HiddenNeurons[hid]);
			
			for (out=0;out<4;out++){
				OutputNeurons[out].connectTo(HiddenNeurons[hid], 2*Math.random()-1.0);
			}
		}
		
		for (in=0;in<100;in++){
			Layers[0].addNeuron(InputNeurons[in]);
		}
		
		for (out=0;out<4;out++){
			Layers[2].addNeuron(OutputNeurons[out]);
		}
	}
}
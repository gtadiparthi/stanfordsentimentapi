
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.util.Properties;
import java.io.*;


public class Sentiment {
	static StanfordCoreNLP pipeline;
	
	public static void init(){
		 Properties props = new Properties();
		  props.setProperty("annotators","tokenize, ssplit , parse, sentiment");
	      pipeline = new StanfordCoreNLP(props);
		
	}
	 public static String findSentiment(String line) {
   
	        int mainSentiment=0;

	        if(line != null && line.length()>0){
	            int longest = 0;
	            //System.out.println(line);
	            Annotation annotation = pipeline.process(line);
	            for (CoreMap sentence :annotation.get(CoreAnnotations.SentencesAnnotation.class )) {
	                Tree tree =	sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
	                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
	                String partText = sentence.toString();
	                if (partText.length() > longest) {
	                    mainSentiment = sentiment;
	                    //System.out.println(+sentiment+" "+partText.length());
	                    longest = partText.length();
	                }
	            }
	        }

	        switch (mainSentiment) {
	        case 0:
	            return "Very Negative";
	        case 1:
	            return "Negative";
	        case 2:
	            return "Neutral";
	        case 3:
	            return "Positive";
	        case 4:
	            return "Very Positive";
	        default:
	            return "";
	        }
	    }
	 
	 public static int findSentimentInt(String line) {

	       
	        int mainSentiment=0;

	        if(line != null && line.length()>0){
	            int longest = 0;
	            //System.out.println(line);
	            Annotation annotation = pipeline.process(line);
	            for (CoreMap sentence :annotation.get(CoreAnnotations.SentencesAnnotation.class )) {
	                Tree tree =	sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
	                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
	                String partText = sentence.toString();
	                if (partText.length() > longest) {
	                    mainSentiment = sentiment;
	                    //System.out.println(+sentiment+" "+partText.length());
	                    longest = partText.length();
	                }
	            }
	        }
	        return (mainSentiment);
	        }

}






  
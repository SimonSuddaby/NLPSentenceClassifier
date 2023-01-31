package com.nlpc.nlpsentenceclassifier;

import opennlp.tools.doccat.*;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class NaiveBayesClassifier {
    public static ObjectStream<DocumentSample> readTrainingData(){

        try{
            File file = new File("tweets-100000.train");
            InputStreamFactory trainingData = new MarkableFileInputStreamFactory(file);
            ObjectStream<String> lineStream = new PlainTextByLineStream(trainingData, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);
            return sampleStream;
        } catch (IOException e){
            System.out.println("Something went wrong reading the training data, please check the input");
            e.printStackTrace();
        }
        return null;
    }

    public static DoccatModel trainModel(){

        ObjectStream<DocumentSample> inputTrainingData = readTrainingData();

        TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.CUTOFF_PARAM, "1");
        params.put(TrainingParameters.ITERATIONS_PARAM, "50");

        try {
            DoccatFactory df = new DoccatFactory();
            DoccatModel model = DocumentCategorizerME.train("en", inputTrainingData, params, df);
            return model;
        }
        catch(IOException e){
            System.out.println("Something went wrong with training the model, please check the model");
            e.printStackTrace();
        }
        return null;
    }

    public static int classify(String[] inputText, DoccatModel model){
        DocumentCategorizerME classifier = new DocumentCategorizerME(model);
        double[] classifierCategory = classifier.categorize(inputText);
        String category = classifier.getBestCategory(classifierCategory);

        if (category.equalsIgnoreCase("4")){
            System.out.print("The text is positive");
            return 4;
        } else {
            System.out.print("The text is negative");
            return 0;
        }
    }
}

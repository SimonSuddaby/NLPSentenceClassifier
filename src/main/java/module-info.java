module com.nlpc.nlpsentenceclassifier {
    requires javafx.controls;
    requires javafx.fxml;
    requires opennlp.tools;


    opens com.nlpc.nlpsentenceclassifier to javafx.fxml;
    exports com.nlpc.nlpsentenceclassifier;
}
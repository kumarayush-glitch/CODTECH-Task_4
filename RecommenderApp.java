package com.mycompany.app;

import java.io.File;
import java.util.List;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.recommender.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecommenderApp {
    public static void main(String[] args) throws Exception {
        DataModel model = new FileDataModel(new File("ratings.csv"));

        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, model);

        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> recommendations = recommender.recommend(1, 3);

        System.out.println("Recommendations for user 1:");
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("  - Item: " + recommendation.getItemID() + ", Score: " + recommendation.getValue());
        }
    }
}
package com.hzz;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MahoutDemo {
    public static void main(String[] args) throws IOException, TasteException {
        DataModel dm = new FileDataModel(new File("data/mahout/data.csv"));
        UserSimilarity sim = new PearsonCorrelationSimilarity(dm);
        // 指定用户邻居数量，这里为2
        UserNeighborhood unb = new NearestNUserNeighborhood(2, sim, dm);
        Recommender recommender = new GenericUserBasedRecommender(dm, unb, sim);
        // 得到指定用户的推荐结果，这里是得到用户1的两个推荐
        List<RecommendedItem> list = recommender.recommend(1, 2);
        // 打印推荐结果
        for (RecommendedItem recommendation : list) {
            System.out.println(recommendation);
        }
    }
}

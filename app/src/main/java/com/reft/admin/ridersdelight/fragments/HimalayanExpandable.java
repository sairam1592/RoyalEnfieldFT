package com.reft.admin.ridersdelight.fragments;

import com.reft.admin.ridersdelight.misc.Constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by arun on 11/13/2015.
 */
public class HimalayanExpandable {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<>();

        List overview = new ArrayList();
        overview.add("Royal Enfield Himalayan has grabbed a lot of attention from its name. In fact by the name, " +
                "we could imagine something tough and sturdy; this is what the Indian manufacturer wants the audience to depict. " +
                "This bike is an upcoming one soon to be launched in the year 2015. The manufacturers are aiming at providing affordable bikes that could be used for general all purpose use or even for long rides.");


        List features = new ArrayList();
        features.add("The Himalayan is much of an Enfield in its heart. Though it is heard that the Enfield is slowly moving away from the brand, the core thing of Enfield is still maintained. " +
                "This bike is meant especially for adventures and bears a retro look and feel. The important features for an adventurous long ride and a bit of off roading could all be expected from this bike." +
                " The manufacturer has meticulously imbibed in this model a long travel suspension, a tough frame and a lot of upgrade options. The seating on this bike is comfortable and can easily carry two adults." +
                " The mounting points for pannier boxes, a tall ground clearance, powerful headlamps, a huge fuel tank, multiple trip meters, an upright riding position etc. makes your long ride even more pleasurable." +
                " The fairing on the bike keeps the wind blast away so as to give you the much needed concentration while riding.");


        List engine = new ArrayList();
        engine.add("It is heard that the engine used in this bike will also be a new one and this time the manufacturer is no more taking onto the beaten path. " +
                "The new power plant is expected to displace close to 380cc. However, the engine is expected to retain low end torque that the Enfield is known for. " +
                "A larger displacement engine of 600cc is also in talks for the model. With all these expected features, there is definitely hope in the audience.");


        List price = new ArrayList();
        price.add("The bike is expected to launch around mid this year at a price range of Rs 1.5 Lac to 1.7 Lac.");


        expandableListDetail.put(Constants.TAG_HIMOVERVIEW, overview);
        expandableListDetail.put(Constants.TAG_HIMFEATURES, features);
        expandableListDetail.put(Constants.TAG_HIMENGINE, engine);
        expandableListDetail.put(Constants.TAG_HIMPRICE, price);
        return expandableListDetail;
    }
}
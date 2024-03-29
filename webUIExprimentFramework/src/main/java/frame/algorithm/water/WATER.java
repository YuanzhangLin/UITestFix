package frame.algorithm.water;

import frame.datacollect.DomNodeInfo;
import frame.datacollect.PreDomNodeInfo;

import java.util.List;

public class WATER {

    public static PreDomNodeInfo getNodeByLocator(PreDomNodeInfo oldPreDomNodeInfo, List<PreDomNodeInfo> newPreDomNodeInfoList) {
        for (PreDomNodeInfo domNodeInfo : newPreDomNodeInfoList) {
            if (!oldPreDomNodeInfo.getId().isEmpty() && !domNodeInfo.getId().isEmpty() && oldPreDomNodeInfo.getId().equals(domNodeInfo.getId())) {
                return domNodeInfo;
            }
            if (!oldPreDomNodeInfo.getXpath().isEmpty() && !domNodeInfo.getXpath().isEmpty() && oldPreDomNodeInfo.getXpath().equals(domNodeInfo.getXpath())) {
                return domNodeInfo;
            }
            if (oldPreDomNodeInfo.getAttributes().get("class") != null && domNodeInfo.getAttributes().get("class") != null &&
                    oldPreDomNodeInfo.getAttributes().get("class").equals(domNodeInfo.getAttributes().get("class"))) {
                return domNodeInfo;
            }
            if (!oldPreDomNodeInfo.getText().isEmpty() && !domNodeInfo.getText().isEmpty() && oldPreDomNodeInfo.getText().equals(domNodeInfo.getText())) {
                return domNodeInfo;
            }
            if (!oldPreDomNodeInfo.getName().isEmpty() && !domNodeInfo.getName().isEmpty() && oldPreDomNodeInfo.getName().equals(domNodeInfo.getName())) {
                return domNodeInfo;
            }

        }
        for (PreDomNodeInfo domNodeInfo : newPreDomNodeInfoList) {

            if (getSimilarityScore(oldPreDomNodeInfo, domNodeInfo).compareTo(0.5) > 0) {
                return domNodeInfo;
            }
        }

        return null;
    }


    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int computeLevenshteinDistance(CharSequence str1, CharSequence str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++)
            distance[i][0] = i;
        for (int j = 0; j <= str2.length(); j++)
            distance[0][j] = j;

        for (int i = 1; i <= str1.length(); i++)
            for (int j = 1; j <= str2.length(); j++)
                distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
        return distance[str1.length()][str2.length()];
    }

    private static Double getSimilarityScore(PreDomNodeInfo a, PreDomNodeInfo b) {
        double alpha = 0.9;
        double rho, rho1, rho2 = 0;
        if (a.getTagName().equals(b.getTagName())) {
            double levDist = computeLevenshteinDistance(a.getXpath(), b.getXpath());
            rho1 = 1 - levDist / Math.max(a.getXpath().length(), b.getXpath().length());
            if (Math.abs(a.getX() - b.getX()) <= 5 && Math.abs((a.getX() + a.getWidth()) - (b.getX() - b.getWidth())) <= 5
                    && Math.abs(a.getY() - b.getY()) <= 5 && Math.abs((a.getY() + a.getHeight()) - (b.getY() - b.getHeight())) <= 5) {
                rho2 = rho2 + 1;
            }
            rho2 = rho2 / 2;
            rho = (rho1 * alpha + rho2 * (1 - alpha));
            return rho;
        }
        return 0.0;
    }

}

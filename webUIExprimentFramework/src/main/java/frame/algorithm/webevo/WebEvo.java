package frame.algorithm.webevo;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import frame.datacollect.DomNodeInfo;
import frame.datacollect.PreDomNodeInfo;

public class WebEvo {
    List<PreDomNodeInfo> oldEleWithText = new ArrayList<>();
    List<PreDomNodeInfo> oldEleWithOutText = new ArrayList<>();
    Map<PreDomNodeInfo, Double> elementsSim = new HashMap<>();
    String savePath;
    List<PreDomNodeInfo> newEleWithText = new ArrayList<>();
    List<PreDomNodeInfo> newEleWithOutText = new ArrayList<>();
    List<DomNodeInfo> oldDomNodeInfoList = new ArrayList<>();
    List<DomNodeInfo> newDomNodeInfoList = new ArrayList<>();
    Map<Integer, Integer> oldMapping = new HashMap<>();
    Map<Integer, Integer> newMapping = new HashMap<>();

    private Map<Integer, Integer> initMap(Map<Integer, Integer> map, List<DomNodeInfo> domNodeInfoList,
                                          List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getCoreElementId() < 0) continue;
            String xpath = preDomNodeInfoList.get(preDomNodeInfo.getCoreElementId()).getXpath();
            for (DomNodeInfo domNodeInfo : domNodeInfoList) {
                if (domNodeInfo.getXpath().equals(xpath)) {
                    map.put(preDomNodeInfo.getElementId(), domNodeInfo.getNewElementId());
                    break;
                }
            }
        }
        return map;
    }

    public WebEvo(List<DomNodeInfo> oldDomNodeInfoList, List<DomNodeInfo> newDomNodeInfoList,
                  List<PreDomNodeInfo> oldPreDomNodeInfoList, List<PreDomNodeInfo> newPreDomNodeInfoList,
                  String savePath) {
        this.oldDomNodeInfoList = oldDomNodeInfoList;
        this.newDomNodeInfoList = newDomNodeInfoList;
        initMap(oldMapping, oldDomNodeInfoList, oldPreDomNodeInfoList);
        initMap(newMapping, newDomNodeInfoList, newPreDomNodeInfoList);
        this.savePath = savePath;
        for (PreDomNodeInfo domNodeInfo : oldPreDomNodeInfoList) {
            if (domNodeInfo.getText().isEmpty()) {
                oldEleWithOutText.add(domNodeInfo);
            } else {
                oldEleWithText.add(domNodeInfo);
            }
        }
        for (PreDomNodeInfo domNodeInfo : newPreDomNodeInfoList) {
            if (domNodeInfo.getText().isEmpty()) {
                newEleWithOutText.add(domNodeInfo);
            } else {
                newEleWithText.add(domNodeInfo);
            }
        }
    }

    // 当前old，返回新版本的元素。
    public DomNodeInfo getNewXpath(DomNodeInfo oldDomNodeInfo) {
        PreDomNodeInfo preDomNodeInfo = match(oldDomNodeInfo, newEleWithOutText, newEleWithText, false);
        if (preDomNodeInfo == null) {
            return null;
        }
        return newDomNodeInfoList.get(newMapping.get(preDomNodeInfo.getElementId()));
    }

    public DomNodeInfo getOldXpath(DomNodeInfo newDomNodeInfo) {
        PreDomNodeInfo oldPreDomNodeINfo = match(newDomNodeInfo, oldEleWithOutText, oldEleWithText, true);
        if (oldPreDomNodeINfo == null) {
            return null;
        }
        return oldDomNodeInfoList.get(oldMapping.get(oldPreDomNodeINfo.getElementId()));
    }

    public PreDomNodeInfo match(DomNodeInfo originalDomNodeInfo, List<PreDomNodeInfo> eleWithOutText, List<PreDomNodeInfo> eleWithText, boolean isNew) {
        String pre = isNew ? "new" : "old";
        String post = isNew ? "old" : "new";
        Map<Integer, Integer> map
                = isNew ? oldMapping : newMapping;
        elementsSim = new HashMap<>();
        if (originalDomNodeInfo.getText().equals("")) {
            for (PreDomNodeInfo domNodeInfo : eleWithOutText) {
                boolean imgResult = compareImage(savePath + pre + File.separator +
                                originalDomNodeInfo.getNewElementId() + ".png"
                        , savePath + post + File.separator +
                                map.get(domNodeInfo.getElementId()) + ".png");
                if (imgResult) {
                    return domNodeInfo;
                }
            }
        } else {
            for (PreDomNodeInfo domNodeInfo : eleWithText) {
                double currentStringSim = StringSimilarity.getSimilarityRatio(originalDomNodeInfo.getText(),
                        domNodeInfo.getText());
                elementsSim.put(domNodeInfo, currentStringSim);
            }
            Map<PreDomNodeInfo, Double> hm1 = sortByValue(elementsSim);
            List<PreDomNodeInfo> hm2 = new ArrayList<>();
            int countC = 0;
            for (PreDomNodeInfo key : hm1.keySet()) {
                hm2.add(key);
                countC++;
                if (countC >= 5) {
                    break;
                }
            }
            for (PreDomNodeInfo domNodeInfo : hm2) {
                boolean imgResult = compareImage(savePath + pre + File.separator + originalDomNodeInfo.getNewElementId() + ".png"
                        , savePath + post + File.separator + map.get(domNodeInfo.getElementId()) + ".png");
                if (imgResult) {
                    return domNodeInfo;
                }
            }
        }
        return null;
    }

    public boolean compareImage(String targetPath, String candidatePath) {
        if (!new File(candidatePath).canRead() || new File(candidatePath).length() == 0) {
            return false;
        }
        try {
            FingerPrint fp1 = new FingerPrint(ImageIO.read(new File(targetPath)));
            FingerPrint fp2;
            fp2 = new FingerPrint(ImageIO.read(new File(candidatePath)));
            double fingerRes = fp1.compare(fp2);
            if (fingerRes >= 0.60) {
                return true;
            }
            fp1 = new FingerPrint(ConvertToGrey.convert(targetPath));
            fp2 = new FingerPrint(ConvertToGrey.convert(candidatePath));
            fingerRes = fp1.compare(fp2);
            if (fingerRes >= 0.60) {
                return true;
            }
            fp1 = new FingerPrint(ImgInverse.imgInverse(targetPath));
            fp2 = new FingerPrint(ImgInverse.imgInverse(candidatePath));
            fingerRes = fp1.compare(fp2);
            if (fingerRes >= 0.60) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public HashMap<PreDomNodeInfo, Double> sortByValue(Map<PreDomNodeInfo, Double> hm) {
        List<Map.Entry<PreDomNodeInfo, Double>> list = new LinkedList<Map.Entry<PreDomNodeInfo, Double>>(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<PreDomNodeInfo, Double>>() {
            public int compare(Map.Entry<PreDomNodeInfo, Double> o1, Map.Entry<PreDomNodeInfo, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        HashMap<PreDomNodeInfo, Double> temp = new LinkedHashMap<PreDomNodeInfo, Double>();
        for (Map.Entry<PreDomNodeInfo, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
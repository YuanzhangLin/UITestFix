package frame.experiment;

import frame.algorithm.element.CleanNode;
import frame.algorithm.sftm.SFTM;
import frame.algorithm.sftm2023.SFTM2023;
import frame.algorithm.vista.VISTA;
import frame.algorithm.water.WATER;
import frame.algorithm.webevo.WebEvo;
import frame.config.Settings;
import frame.datacollect.DomNodeInfo;
import frame.datacollect.JsonProcess;
import frame.datacollect.PreDomNodeInfo;
import frame.utils.UtilProperty;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    static Properties mainProperties;
    static int totalNumber = 0;
    static int correctNumber = 0;
    static double addtotal = 0.0;
    static double addcorrect = 0.0;
    static double deleteTotal = 0.0;
    static double deleteCorrect = 0.0;
    static double maxRate = 0.0;
    static double minRate = 100.0;
    static double time = 0.0;
    public static long idMatchTime = 0;
    public static long initSimilarityTime = 0;
    public static long matchTime = 0;

    public static void main(String[] args) throws IOException {
        mainProperties = UtilProperty.readProperties(Settings.rootPath + "src" + File.separator + "main" + File.separator + "java" +
                File.separator + "frame" + File.separator + "experiment" + File.separator + "main.properties");
        List<String> experimentList = Arrays.asList(mainProperties.getProperty("webevodata").trim().split(","));
        double fitness = 0.0;
        for (String experimentName : experimentList) {
            System.out.println(System.lineSeparator());
            System.out.println("experiment name:" + experimentName);
            fitness += runOneExperiment(Settings.rootPath + "data" + File.separator + experimentName + File.separator, experimentName);
            double rate = ((addcorrect + correctNumber + deleteCorrect) / (totalNumber + addtotal + deleteTotal));

        }
        System.out.println("time:" + time );
        System.out.println("idMatchTime:" + (idMatchTime / (experimentList.size())));
        System.out.println("initSimilarityTime:" + (initSimilarityTime / (experimentList.size())));
        System.out.println("matchTime:" + (matchTime / (experimentList.size())));
        System.out.println("maxRate" + maxRate);
        System.out.println("minRate" + minRate);

        System.out.println(System.lineSeparator());
        System.out.println(fitness);
        System.out.println(correctNumber + "/" + totalNumber + ":" + ((double) correctNumber / totalNumber));
        System.out.println("add" + addcorrect + "/" + addtotal + ":" + (addcorrect / addtotal));
        System.out.println("delete" + deleteCorrect + "/" + deleteTotal + ":" + (deleteCorrect / deleteTotal));
        System.out.println("total" +
                (addcorrect + correctNumber + deleteCorrect) + "/" +
                (totalNumber + addtotal + deleteTotal) + ":" + ((addcorrect + correctNumber + deleteCorrect) / (totalNumber + addtotal + deleteTotal)));
    }


    public static double runOneExperiment(String savePath, String experimentName) throws IOException {
        String[] methodNameList = mainProperties.getProperty("algorithm").trim().split(",");
        List<DomNodeInfo> oldDomNodeInfoList = JsonProcess.readDomInfoJson(savePath + "old" + File.separator + "domNodeInfo.json");
        List<DomNodeInfo> newDomNodeInfoList = JsonProcess.readDomInfoJson(savePath + "new" + File.separator + "domNodeInfo.json");
        List<PreDomNodeInfo> oldPreDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + "old" + File.separator + "preDomNodeInfo.json");
        List<PreDomNodeInfo> newPreDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + "new" + File.separator + "preDomNodeInfo.json");
        MatchResult matchResult = new MatchResult(savePath + "result" + File.separator + "result.json");
        double fitness = 0.0;
        for (String methodName : methodNameList) {
            System.out.println("algorithm name:" + methodName);
            MatchResult executeResult = runOneMethod(savePath, methodName, oldDomNodeInfoList, newDomNodeInfoList, oldPreDomNodeInfoList, newPreDomNodeInfoList, matchResult);
            fitness = report(executeResult, matchResult, oldDomNodeInfoList, newDomNodeInfoList);
        }
        return fitness;
    }

    public static MatchResult runOneMethod(String savePath, String methodName, List<DomNodeInfo> oldDomNodeInfoList, List<DomNodeInfo> newDomNodeInfoList,
                                           List<PreDomNodeInfo> oldPreDomNodeInfoList, List<PreDomNodeInfo> newPreDomNodeInfoList, MatchResult matchResult) throws IOException {
        MatchResult executeResult = null;
        switch (methodName) {
            case "WATER":
                executeResult = water(oldPreDomNodeInfoList, newPreDomNodeInfoList, oldDomNodeInfoList, newDomNodeInfoList,
                        savePath, matchResult);
                break;
            case "SFTM":
                try {
                    executeResult = sftm(oldDomNodeInfoList, newDomNodeInfoList, oldPreDomNodeInfoList, newPreDomNodeInfoList, savePath, matchResult);
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage() + "could not read html file.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "SFTM2023":
                try {
                    executeResult = sftm2023(oldDomNodeInfoList, newDomNodeInfoList, oldPreDomNodeInfoList, newPreDomNodeInfoList, savePath, matchResult);
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage() + "could not read html file.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "WebEvo":
                executeResult = webevo(oldDomNodeInfoList, newDomNodeInfoList,
                        oldPreDomNodeInfoList, newPreDomNodeInfoList, savePath, matchResult);
                break;
            case "VISTA":
                executeResult = vista(oldDomNodeInfoList, newDomNodeInfoList, savePath, matchResult);
                break;
        }
        return executeResult;
    }

    public static double report(MatchResult executeResult, MatchResult matchResult, List<DomNodeInfo> oldDomNodeInfoList, List<DomNodeInfo> newDomNodeInfoList) {
        System.out.println(executeResult);
        System.out.println(matchResult);
        double addCorrectNumber = 0.0;
        for (int index : matchResult.getAddList()) {
            if (newDomNodeInfoList.get(index).getElementType() == Settings.CONTAINER) continue;
            if (executeResult.getAddList().contains(index)) {
                addCorrectNumber++;
                addcorrect++;
            }
            addtotal++;
        }
        System.out.println("the correct rate of added element: " + (addCorrectNumber / matchResult.getAddList().size()));
        double deleteCorrectNumber = 0.0;
        for (int index : matchResult.getDeleteList()) {
            if (oldDomNodeInfoList.get(index).getElementType() == Settings.CONTAINER) continue;
            if (executeResult.getDeleteList().contains(index)) {
                deleteCorrectNumber++;
                deleteCorrect++;
            }
            deleteTotal++;
        }
        System.out.println("the correct rate of deleted element: " + (deleteCorrectNumber / matchResult.getDeleteList().size()));
        double matchCorrectNumber = 0.0;
        int totalMatch = 0;
        for (int index : matchResult.getMatchMap().keySet()) {
            if (oldDomNodeInfoList.get(index).getElementType() == Settings.CONTAINER ) continue;
            totalMatch++;
            if (matchResult.getMatchMap().get(index).equals(executeResult.getMatchMap().get(index))) {
                matchCorrectNumber++;

            } else {
                System.out.println(index + "-" + matchResult.getMatchMap().get(index) +
                        ":" + index + "-" + executeResult.getMatchMap().get(index));
            }
        }
        correctNumber += matchCorrectNumber;
        totalNumber += totalMatch;
        System.out.println("the accuracy of the leaf node：" + (matchCorrectNumber / totalMatch));
        System.out.println("the correct rate of matched element: " + (matchCorrectNumber / matchResult.getMatchMap().size()));
        double totalCorrectRate = ((matchCorrectNumber + deleteCorrectNumber + addCorrectNumber) /
                (matchResult.getMatchMap().size() + matchResult.getAddList().size() + matchResult.getDeleteList().size()));
        System.out.println("the correct rate of total element: " + totalCorrectRate);
        System.out.println("add+delete/total" +
                (double) (matchResult.getDeleteList().size() + matchResult.getAddList().size()) /
                        (matchResult.getDeleteList().size() + matchResult.getAddList().size() + matchResult.getMatchMap().size()));
        return totalCorrectRate;
    }


    public static MatchResult water(List<PreDomNodeInfo> oldPreDomNodeInfoList, List<PreDomNodeInfo> newPreDomNodeInfoList,
                                     List<DomNodeInfo> oldDomNodeInfoList, List<DomNodeInfo> newDomNodeInfoList,
                                     String rootPath,
                                     MatchResult matchResult) {
        oldPreDomNodeInfoList =
                new CleanNode(oldPreDomNodeInfoList, rootPath + "old" + File.separator + "old.html").preDomNodeInfoList;
        newPreDomNodeInfoList =
                new CleanNode(newPreDomNodeInfoList, rootPath + "new" + File.separator + "new.html").preDomNodeInfoList;
        MatchResult executeMatchResult = new MatchResult();
        // add element.
        for (int newIndex : matchResult.getAddList()) {
            long stime = System.currentTimeMillis();
            PreDomNodeInfo oldPreDomNodeInfo = WATER.getNodeByLocator(newPreDomNodeInfoList.get(
                    newDomNodeInfoList.get(newIndex).getElementId()
            ), oldPreDomNodeInfoList);
            long dtime = System.currentTimeMillis();
            time += (dtime - stime);
            if (oldPreDomNodeInfo == null) {
                executeMatchResult.getAddList().add(newIndex);
            } else if (oldPreDomNodeInfo.getCoreElementId() >= 0) {
                int elementId = -2;
                String coreElementXpath = oldPreDomNodeInfoList.get(oldPreDomNodeInfo.getCoreElementId()).getXpath();
                for (DomNodeInfo oldDomNodeInfo : oldDomNodeInfoList) {
                    if (oldDomNodeInfo.getXpath().equals(coreElementXpath)) {
                        elementId = oldDomNodeInfo.getNewElementId();
                        break;
                    }
                }
                executeMatchResult.getMatchMap().put(elementId, newIndex);
            }
        }
        // deleted element.
        for (int oldIndex : matchResult.getDeleteList()) {
            long stime = System.currentTimeMillis();
            PreDomNodeInfo newPreDomNodeINfo = WATER.getNodeByLocator(oldPreDomNodeInfoList.get(
                    oldDomNodeInfoList.get(oldIndex).getElementId()
            ), newPreDomNodeInfoList);
            long dtime = System.currentTimeMillis();
            time += (dtime - stime);
            if (newPreDomNodeINfo == null) {
                executeMatchResult.getDeleteList().add(oldIndex);
            } else if (newPreDomNodeINfo.getCoreElementId() >= 0) {
                int elementId = -2;
                String coreElementXpath = newPreDomNodeInfoList.get(newPreDomNodeINfo.getCoreElementId()).getXpath();
                for (DomNodeInfo newDomNodeINfo : newDomNodeInfoList) {
                    if (newDomNodeINfo.getXpath().equals(coreElementXpath)) {
                        elementId = newDomNodeINfo.getNewElementId();
                        break;
                    }
                }
                executeMatchResult.getMatchMap().put(oldIndex, elementId);
            }
        }
        // match element.
        for (int oldIndex : matchResult.getMatchMap().keySet()) {
            long stime = System.currentTimeMillis();
            PreDomNodeInfo newPreDomNodeInfo = WATER.getNodeByLocator(oldPreDomNodeInfoList.get(
                    oldDomNodeInfoList.get(oldIndex).getElementId()
            ), newPreDomNodeInfoList);
            long dtime = System.currentTimeMillis();
            time += (dtime - stime);
            if (newPreDomNodeInfo == null) {
                executeMatchResult.getDeleteList().add(oldIndex);
            } else if (newPreDomNodeInfo.getCoreElementId() >= 0) {
                int elementId = -2;
                String coreElementXpath = newPreDomNodeInfoList.get(newPreDomNodeInfo.getCoreElementId()).getXpath();
                for (DomNodeInfo newDomNodeINfo : newDomNodeInfoList) {
                    if (newDomNodeINfo.getXpath().equals(coreElementXpath)) {
                        elementId = newDomNodeINfo.getNewElementId();
                        break;
                    }
                }
                executeMatchResult.getMatchMap().put(oldIndex, elementId);
            } else {
                System.out.println(newPreDomNodeInfo.getElementId() + "+" + newPreDomNodeInfo.getText() + ":" + newPreDomNodeInfo.isDisplayed());
            }
        }
        return executeMatchResult;
    }



    /**
     * @param rootPath
     * @param matchResult
     * @return
     */
    public static MatchResult sftm2023(List<DomNodeInfo> oldDomNodeInfoList, List<DomNodeInfo> newDomNodeInfoList, List<PreDomNodeInfo> oldPreDomNodeInfoList,
                                       List<PreDomNodeInfo> newPreDomNodeInfoList, String rootPath, MatchResult matchResult) throws Exception {
        MatchResult executeMatchResult = new MatchResult();
        SFTM2023 sftm2023 = new SFTM2023();
        sftm2023.match(rootPath + "old" + File.separator + "old.html",
                rootPath + "new" + File.separator + "new.html");
        // added element.
        for (int newIndex : matchResult.getAddList()) {
            String xpath = sftm2023.getOldXpath(newDomNodeInfoList.get(newIndex).getXpath());
            if (xpath.equals("")) {
                executeMatchResult.getAddList().add(newIndex);
            } else {
                int oldIndex = -1;
                for (PreDomNodeInfo oldPreDomNodeInfo : oldPreDomNodeInfoList) {
                    if (oldPreDomNodeInfo.getXpath().equals(xpath)) {
                        // the matched element is not displayed.
                        if (!oldPreDomNodeInfo.isDisplayed()) {
                            oldIndex = -2;
                            break;
                        }
                        System.out.println(oldPreDomNodeInfo.getElementId());
                        String coreElementXpath = oldPreDomNodeInfoList.get(oldPreDomNodeInfo.getCoreElementId()).getXpath();
                        for (DomNodeInfo oldDomNodeInfo : oldDomNodeInfoList) {
                            if (oldDomNodeInfo.getXpath().equals(coreElementXpath)) {
                                oldIndex = oldDomNodeInfo.getNewElementId();
                                break;
                            }
                        }
                        break;
                    }
                }
                if (oldIndex == -1) {
                    executeMatchResult.getAddList().add(newIndex);
                } else if (oldIndex >= 0) {
                    executeMatchResult.getMatchMap().put(oldIndex, newIndex);
                }
            }
        }
        // deleted element.
        for (int oldIndex : matchResult.getDeleteList()) {
            String xpath = sftm2023.getNewXpath(oldDomNodeInfoList.get(oldIndex).getXpath());
            if (xpath.equals("")) {
                executeMatchResult.getDeleteList().add(oldIndex);
            } else {
                // if the algorithm output a matched element....
                int newIndex = -1;
                for (PreDomNodeInfo newPreDomNodeInfo : newPreDomNodeInfoList) {
                    if (newPreDomNodeInfo.getXpath().equals(xpath)) {
                        // if the matched element is not displayed.
                        if (!newPreDomNodeInfo.isDisplayed()) {
                            newIndex = -2;
                            break;
                        }
                        String coreElementXpath = newPreDomNodeInfoList.get(newPreDomNodeInfo.getCoreElementId()).getXpath();
                        for (DomNodeInfo newDomNodeInfo : newDomNodeInfoList) {
                            if (newDomNodeInfo.getXpath().equals(coreElementXpath)) {
                                newIndex = newDomNodeInfo.getNewElementId();
                                break;
                            }
                        }
                        break;
                    }
                }
                if (oldIndex == -1) {
                    executeMatchResult.getDeleteList().add(newIndex);
                } else if (oldIndex >= 0) {
                    executeMatchResult.getMatchMap().put(oldIndex, newIndex);
                }
            }
        }
        // matched element
        for (int oldIndex : matchResult.getMatchMap().keySet()) {
            String xpath = sftm2023.getNewXpath(oldDomNodeInfoList.get(oldIndex).getXpath());
            if (xpath.equals("")) {
                executeMatchResult.getDeleteList().add(oldIndex);
            } else {
                // if the algorithm output a matched element.
                int newIndex = -1;
                for (PreDomNodeInfo newPreDomNodeInfo : newPreDomNodeInfoList) {
                    if (newPreDomNodeInfo.getXpath().equals(xpath)) {
                        if (!newPreDomNodeInfo.isDisplayed()) {
                            newIndex = -2;
                            break;
                        }
                        String coreElementXpath = newPreDomNodeInfoList.get(newPreDomNodeInfo.getCoreElementId()).getXpath();
                        for (DomNodeInfo newDomNodeInfo : newDomNodeInfoList) {
                            if (newDomNodeInfo.getXpath().equals(coreElementXpath)) {
                                newIndex = newDomNodeInfo.getNewElementId();
                                break;
                            }
                        }
                        break;
                    }
                }
                if (newIndex == -1) {
                    executeMatchResult.getDeleteList().add(newIndex);
                } else if (newIndex >= 0) {
                    executeMatchResult.getMatchMap().put(oldIndex, newIndex);
                }
            }
        }
        return executeMatchResult;
    }

    /**
     * @param rootPath
     * @param matchResult
     * @return
     */
    public static MatchResult sftm(List<DomNodeInfo> oldDomNodeInfoList, List<DomNodeInfo> newDomNodeInfoList, List<PreDomNodeInfo> oldPreDomNodeInfoList,
                                   List<PreDomNodeInfo> newPreDomNodeInfoList, String rootPath, MatchResult matchResult) throws Exception {
        MatchResult executeMatchResult = new MatchResult();
        SFTM sftm = new SFTM();
        sftm.matchByPath(rootPath + "old" + File.separator + "old.html",
                rootPath + "new" + File.separator + "new.html");
        // added element.
        for (int newIndex : matchResult.getAddList()) {
            String xpath = sftm.getOldXpath(newDomNodeInfoList.get(newIndex).getXpath());
            if (xpath.equals("")) {
                executeMatchResult.getAddList().add(newIndex);
            } else {
                // if the algorithm output a matched element.
                int oldIndex = -1;
                for (PreDomNodeInfo oldPreDomNodeInfo : oldPreDomNodeInfoList) {
                    if (oldPreDomNodeInfo.getXpath().equals(xpath)) {
                        if (!oldPreDomNodeInfo.isDisplayed()) {
                            oldIndex = -2;
                            break;
                        }
                        System.out.println(oldPreDomNodeInfo.getElementId());
                        String coreElementXpath = oldPreDomNodeInfoList.get(oldPreDomNodeInfo.getCoreElementId()).getXpath();
                        for (DomNodeInfo oldDomNodeInfo : oldDomNodeInfoList) {
                            if (oldDomNodeInfo.getXpath().equals(coreElementXpath)) {
                                oldIndex = oldDomNodeInfo.getNewElementId();
                                break;
                            }
                        }
                        break;
                    }
                }
                if (oldIndex == -1) {
                    executeMatchResult.getAddList().add(newIndex);
                } else if (oldIndex >= 0) {
                    executeMatchResult.getMatchMap().put(oldIndex, newIndex);
                }
            }
        }
        // deleted element.
        for (int oldIndex : matchResult.getDeleteList()) {
            String xpath = sftm.getNewXpath(oldDomNodeInfoList.get(oldIndex).getXpath());
            if (xpath.equals("")) {
                executeMatchResult.getDeleteList().add(oldIndex);
            } else {
                int newIndex = -1;
                for (PreDomNodeInfo newPreDomNodeInfo : newPreDomNodeInfoList) {
                    if (newPreDomNodeInfo.getXpath().equals(xpath)) {
                        if (!newPreDomNodeInfo.isDisplayed()) {
                            newIndex = -2;
                            break;
                        }
                        String coreElementXpath = newPreDomNodeInfoList.get(newPreDomNodeInfo.getCoreElementId()).getXpath();
                        for (DomNodeInfo newDomNodeInfo : newDomNodeInfoList) {
                            if (newDomNodeInfo.getXpath().equals(coreElementXpath)) {
                                newIndex = newDomNodeInfo.getNewElementId();
                                break;
                            }
                        }
                        break;
                    }
                }
                if (oldIndex == -1) {
                    executeMatchResult.getDeleteList().add(newIndex);
                } else if (oldIndex >= 0) {
                    executeMatchResult.getMatchMap().put(oldIndex, newIndex);
                }
            }
        }
        // matched element.
        for (int oldIndex : matchResult.getMatchMap().keySet()) {
            String xpath = sftm.getNewXpath(oldDomNodeInfoList.get(oldIndex).getXpath());
            if (xpath.equals("")) {
                executeMatchResult.getDeleteList().add(oldIndex);
            } else {
                // if the algorithm output a matched element.
                int newIndex = -1;
                for (PreDomNodeInfo newPreDomNodeInfo : newPreDomNodeInfoList) {
                    if (newPreDomNodeInfo.getXpath().equals(xpath)) {
                        if (!newPreDomNodeInfo.isDisplayed()) {
                            newIndex = -2;
                            break;
                        }
                        String coreElementXpath = newPreDomNodeInfoList.get(newPreDomNodeInfo.getCoreElementId()).getXpath();
                        for (DomNodeInfo newDomNodeInfo : newDomNodeInfoList) {
                            if (newDomNodeInfo.getXpath().equals(coreElementXpath)) {
                                newIndex = newDomNodeInfo.getNewElementId();
                                break;
                            }
                        }
                        break;
                    }
                }
                if (newIndex == -1) {
                    executeMatchResult.getDeleteList().add(newIndex);
                } else if (newIndex >= 0) {
                    executeMatchResult.getMatchMap().put(oldIndex, newIndex);
                }
            }
        }
        return executeMatchResult;
    }

    public static MatchResult webevo(List<DomNodeInfo> oldDomNodeInfoList, List<DomNodeInfo> newDomNodeInfoList,
                                     List<PreDomNodeInfo> oldPreDomNodeInfoList, List<PreDomNodeInfo> newPreDomNodeInfoList,
                                     String rootPath, MatchResult matchResult) {
        oldPreDomNodeInfoList =
                new CleanNode(oldPreDomNodeInfoList, rootPath + "old" + File.separator + "old.html").preDomNodeInfoList;
        newPreDomNodeInfoList =
                new CleanNode(newPreDomNodeInfoList, rootPath + "new" + File.separator + "new.html").preDomNodeInfoList;
        WebEvo webEvo = new WebEvo(oldDomNodeInfoList, newDomNodeInfoList, oldPreDomNodeInfoList, newPreDomNodeInfoList, rootPath);

        MatchResult executeResult = new MatchResult();
        // added
        for (int newIndex : matchResult.getAddList()) {
            DomNodeInfo newDomNodeInfo = newDomNodeInfoList.get(newIndex);
            long stime = System.currentTimeMillis();
            DomNodeInfo oldDomNodeInfo = webEvo.getOldXpath(newDomNodeInfo);
            long dtime = System.currentTimeMillis();
            time +=(dtime - stime);
            if (oldDomNodeInfo == null) {
                executeResult.getAddList().add(newIndex);
            } else {
                executeResult.getMatchMap().put(oldDomNodeInfo.getNewElementId(), newIndex);
            }
        }
        // deleted
        for (int oldIndex : matchResult.getDeleteList()) {
            DomNodeInfo oldDomNodeInfo = oldDomNodeInfoList.get(oldIndex);
            long stime = System.currentTimeMillis();
            DomNodeInfo newDomNodeInfo = webEvo.getNewXpath(oldDomNodeInfo);
            long dtime = System.currentTimeMillis();
            time +=(dtime - stime);
            if (newDomNodeInfo == null) {
                executeResult.getDeleteList().add(oldIndex);
            } else {
                executeResult.getMatchMap().put(oldIndex, newDomNodeInfo.getNewElementId());
            }
        }

        // matched
        for (int oldIndex : matchResult.getMatchMap().keySet()) {
            DomNodeInfo oldDomNodeInfo = oldDomNodeInfoList.get(oldIndex);
            long stime = System.currentTimeMillis();
            DomNodeInfo newDomNodeInfo = webEvo.getNewXpath(oldDomNodeInfo);
            long dtime = System.currentTimeMillis();
            time +=(dtime - stime);
            if (newDomNodeInfo == null) {
                executeResult.getDeleteList().add(oldIndex);
            } else {
                executeResult.getMatchMap().put(oldIndex, newDomNodeInfo.getNewElementId());
            }
        }
        return executeResult;
    }

    public static MatchResult vista(List<DomNodeInfo> oldDomNodeInfoList, List<DomNodeInfo> newDomNodeInfoList, String rootPath, MatchResult matchResult) throws IOException {
        MatchResult executeResult = new MatchResult();
        // added
        for (int newIndex : matchResult.getAddList()) {
            DomNodeInfo newDomNodeInfo = newDomNodeInfoList.get(newIndex);
            DomNodeInfo oldDomNodeInfo = null;
            try {
                long stime = System.currentTimeMillis();
                oldDomNodeInfo = VISTA.retrieveDomNode(
                        rootPath + "new" + File.separator + "fullScreen.png",
                        rootPath + "old" + File.separator + "fullScreen.png", newDomNodeInfo,
                        newDomNodeInfoList
                );
                long dtime = System.currentTimeMillis();
                time += (dtime - stime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (oldDomNodeInfo == null) {
                executeResult.getAddList().add(newIndex);
            } else {
                executeResult.getMatchMap().put(oldDomNodeInfo.getNewElementId(), newIndex);
            }
        }
        // deleted
        for (int oldIndex : matchResult.getDeleteList()) {
            DomNodeInfo oldDomNodeInfo = oldDomNodeInfoList.get(oldIndex);
            DomNodeInfo newDomNodeInfo = null;
            try {
                long stime = System.currentTimeMillis();

                newDomNodeInfo = VISTA.retrieveDomNode(rootPath + "old" + File.separator + "fullScreen.png",
                        rootPath + "new" + File.separator + "fullScreen.png", oldDomNodeInfo, newDomNodeInfoList);
                long dtime = System.currentTimeMillis();
                time += (dtime - stime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (newDomNodeInfo == null) {
                executeResult.getDeleteList().add(oldIndex);
            } else {
                executeResult.getMatchMap().put(oldIndex, newDomNodeInfo.getNewElementId());
            }
        }

        // matched
        for (int oldIndex : matchResult.getMatchMap().keySet()) {
            DomNodeInfo oldDomNodeInfo = oldDomNodeInfoList.get(oldIndex);
            DomNodeInfo newDomNodeInfo = null;
            try {
                long stime = System.currentTimeMillis();

                newDomNodeInfo = VISTA.retrieveDomNode(rootPath + "old" + File.separator + "fullScreen.png",
                        rootPath + "new" + File.separator + "fullScreen.png", oldDomNodeInfo, newDomNodeInfoList);
                long dtime = System.currentTimeMillis();
                time += (dtime - stime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (newDomNodeInfo == null) {
                executeResult.getDeleteList().add(oldIndex);
            } else {
                executeResult.getMatchMap().put(oldIndex, newDomNodeInfo.getNewElementId());
            }
        }
        return executeResult;
    }

}

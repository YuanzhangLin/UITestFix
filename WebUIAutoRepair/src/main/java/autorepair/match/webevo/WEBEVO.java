package autorepair.match.webevo;

import autorepair.state.datacollect.PreDomNodeInfo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;


public class WEBEVO {
//    public PriorityQueue<newElement> beixuan;

    public String processingCandidatesImage(WebDriver driver, String candidateLocation
            , List<PreDomNodeInfo> newPreDomNodeInfoList, String targetText, String targetPath) {

        int count = 1;
//        beixuan = new PriorityQueue<newElement>(new Comparator<newElement>() {
//            @Override
//            public int compare(newElement o1, newElement o2) {
//                return (int) (o2.similarity - o1.similarity);
//            }
//        });
        String finalXpath = "";
        List<Integer> eleWithText = new ArrayList<>();
        List<Integer> eleWithOutText = new ArrayList<>();
        HashMap<Integer, Double> elementsSim = new HashMap<>();
        String targetName = "1";


        Double similarity = 0.0;
        Double maxSim = 0.0;
        int finalcount = 0;
        WebElement finalEle = null;
        List<WebElement> eletest = new ArrayList<>();
        List<String> Xpaths = new ArrayList<>();

        try {
            count = 0;
            for (PreDomNodeInfo domNodeInfo : newPreDomNodeInfoList) {
                String tempXpath = domNodeInfo.getXpath();
                String Xpath = tempXpath.replaceAll("'", "\"");
                try {
                    WebElement element = driver.findElement(By.xpath(Xpath));
                    count++;
                    eletest.add(element);
                    Xpaths.add(tempXpath);

                } catch (NoSuchElementException ex) {
                    System.out.println("could not find :" + domNodeInfo.getText());
                }

            }

            // with text

            // with no text

            for (int i = 0; i < eletest.size(); i++) {
                // System.out.println("test:"+eletest.get(i).getText());
                try {
                    if (eletest.get(i).getText().equals("")) {
                        eleWithOutText.add(i);
                    }
                    if (!eletest.get(i).getText().equals("")) {
                        eleWithText.add(i);
                    }
                } catch (StaleElementReferenceException ex) {
                    // ex.printStackTrace();
                    continue;
                }
            }

            count = 1;
            if (targetText.equals("")) {
                //System.out.println("Current target text is empty, comparing it with candidate with empty text...");
                for (int j : eleWithOutText) {
                    try {
                        try {
                            cut(new File(candidateLocation + "candidate" + "_" + (count) + ".png"), eletest.get(j));
                        } catch (Exception e) {
                            continue;
                        }
                        //截图方法更换，避免分辨率影响截图
                        String tempTargetPath = candidateLocation
                                + "target" + ".png";
                        copyFile(targetPath, tempTargetPath);

                        //if (new File(targetPath).length() != 0) {
                        String temp1 = candidateLocation
                                + "temp1" + ".png";
                        String temp2 = candidateLocation
                                + "temp2" + ".png";
                        similarity = compareImage(tempTargetPath, candidateLocation + "candidate" + "_" + (count) + ".png", temp1, temp2);
                        if (similarity >= 0.60) {
                            return Xpaths.get(j);
//                            if (similarity > maxSim) {
//                                maxSim = similarity;
//                                finalEle = eletest.get(j);
//                                finalXpath = Xpaths.get(j);
//                                finalcount = count;
//                            }
                        }
                        File file = new File(tempTargetPath);
                        file.delete();
                        count++;
                    } catch (StaleElementReferenceException ex) {
                        ex.printStackTrace();
                        continue;
                    }

                }
            } else {
                //System.out.println("current target text is not empty, comparing...");

                for (int j : eleWithText) {
                    try {
                        double currentStringSim = StringSimilarity.getSimilarityRatio(eletest.get(j).getText(),
                                targetText);
                        elementsSim.put(j, currentStringSim);
                    } catch (StaleElementReferenceException ex) {
                        continue;
                    }
                }
                Map<Integer, Double> hm1 = sortByValue(elementsSim);
                List<Integer> hm2 = new ArrayList<>();
                int countC = 0;
                for (int key : hm1.keySet()) {
                    //System.out.println(key + ", " + hm1.get(key));
                    // System.out.println(eletest.get(key).getText());
                    hm2.add(key);
                    countC++;
                    if (countC >= 5) {
                        break;
                    }
                }
                for (int key : hm2) {
                    try {
                        try {
                            cut(new File(candidateLocation + "candidate" + "_" + (count) + ".png"), eletest.get(key));
                        } catch (Exception e) {
                            continue;
                        }
                        //截图方法更换，避免分辨率影响截图
                        String tempTargetPath = candidateLocation
                                + "target" + ".png";
                        copyFile(targetPath, tempTargetPath);
                        String temp1 = candidateLocation
                                + "temp1" + ".png";
                        String temp2 = candidateLocation
                                + "temp2" + ".png";
                        similarity = compareImage(tempTargetPath, candidateLocation + "candidate" + "_" + (count) + ".png", temp1, temp2);
                        if (similarity >= 0.60) {
                            return Xpaths.get(key);
//                            newElement newelement = new newElement(eletest.get(key), similarity);
//                            beixuan.add(newelement);
//                            if (similarity > maxSim) {
//                                maxSim = similarity;
//                                finalEle = eletest.get(key);
//                                finalXpath = Xpaths.get(key);
//                                finalcount = count;
//                            }
                        }
                        File file = new File(tempTargetPath);
                        file.delete();
                        count++;
                    } catch (StaleElementReferenceException ex) {
                        ex.printStackTrace();
                        continue;
                    }

                }

            }
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");

        } catch (Exception e) {
            System.out.println("errors when finding candidates images!");
            System.out.println(e.toString());
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        String tempTargetPath = candidateLocation
                + "result" + "_" + finalcount + ".png";
        String finalC = candidateLocation + "candidate" + "_" + (finalcount) + ".png";
        if (finalcount != 0) {
            copyFile(finalC, tempTargetPath);
        } else {
            System.out.println("webevo failed");
        }


        return finalXpath;
    }

    public static double compareImage(String targetPath, String candidatePath, String temp1, String temp2) {
        //System.out.println("original path :"+targetPath);
        //System.out.println("new path: "+candidatePath);
        double fingerRes = 0;

        if (!new File(targetPath).canRead() || new File(targetPath).length() == 0) {
            //    System.out.println("Original element's path might be wrong, can't open the original picture. Can't compare the images.");
            return 0;
        }
        if (!new File(candidatePath).canRead() || new File(candidatePath).length() == 0) {
            //    System.out.println("New element's path might be wrong, can't open the new picture.  Can't compare the images.");
            return 0;
        }
        //int totalCandidate = new File(candidateLocation).list().length;
        try {

            //System.out.println("Comparing two pics...");
            FingerPrint fp1 = new FingerPrint(ImageIO.read(new File(targetPath)));
            FingerPrint fp2;
            fp2 = new FingerPrint(ImageIO.read(new File(candidatePath)));

            fingerRes = fp1.compare(fp2);
            // double cvRes=OpenCVImageCompare.CompareAndMarkDiff(targetPath,
            // candidatePath);
            //    System.out.println("Compaing current two images...");
            // double temp=fingerRes>cvRes?fingerRes:cvRes;;
            if (fingerRes >= 0.60) {

                // System.out.println(temp);
                //      System.out.println("The similarity is more than 0.6, condidered the element is updated and we found it. " );
                //    System.out.println("original images similaity: " + fingerRes);
                //finalCandidate = "candidate " + candidatePath;
                //finalCandidate = candidatePath;
                return fingerRes;
            }
            //System.out.println("original images similaity: " + fingerRes);
            // grey scale
            //System.out.println("Grey Scaling the target img and candidate img...");
            converToGrey.convert(targetPath, temp1);
            converToGrey.convert(candidatePath, temp2);
            //System.out.println("Done Grey scale for target and candiate");

            fp1 = new FingerPrint(ImageIO.read(new File(temp1)));
            fp2 = new FingerPrint(ImageIO.read(new File(temp2)));

            fingerRes = fp1.compare(fp2);
            // cvRes=OpenCVImageCompare.CompareAndMarkDiff(targetPath, candidatePath);
            //System.out.println("Compaing current two images...");
            // temp=fingerRes>cvRes?fingerRes:cvRes;;
            if (fingerRes >= 0.60) {

                // System.out.println(temp);
                //System.out.println("After grey convert, The similarity is more than 0.6, condidered the element is updated and we found it. " );
                //finalCandidate = "candidate " + candidatePath;
                //finalCandidate = candidatePath;
                return fingerRes;
            }
            //System.out.println("Current simility is " + fingerRes + ", not consider as same pics");
            // System.out.println("Trying reverse colors for candidate...");
            ImgInverse.imgeInverse(targetPath, temp1);
            ImgInverse.imgeInverse(candidatePath, temp2);
            //System.out.println("Done reverse color for candidate");

            // reverse color
            //System.out.println("Comparing two pics...");
            fp1 = new FingerPrint(ImageIO.read(new File(temp1)));
            fp2 = new FingerPrint(ImageIO.read(new File(temp2)));
            fingerRes = fp1.compare(fp2);
            // cvRes=OpenCVImageCompare.CompareAndMarkDiff(targetPath, candidatePath);
            // temp=fingerRes>cvRes?fingerRes:cvRes;;
            if (fingerRes >= 0.60) {

                // System.out.println(temp);
                //System.out.println("After color reverse, The similarity is more than 0.6, condidered the element is updated and we found it. " );
                // System.out.println("the similarity is :"+fingerRes);
                //finalCandidate = "candidate " + candidatePath;
                //finalCandidate = candidatePath;
                return fingerRes;
            }
            // System.out.println("reversed images similaity: " + fingerRes +" the original elements might be changed significantly or is deleted.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            System.out.println("can not read target image!");
        }
        return fingerRes;
    }

    public static class newElement {
        WebElement webElement = null;
        double similarity = 0.0;

        public newElement(WebElement webElement, double similarity) {
            this.webElement = webElement;
            this.similarity = similarity;
        }
    }

    public static void cut(File output, WebElement element) {
        File sourceFile = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void copyFile(String src, String target) {
        File srcFile = new File(src);
        File targetFile = new File(target);
        try {
            InputStream in = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(targetFile);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println("succeed copied pic");

    }

    public static HashMap<Integer, Double> sortByValue(Map<Integer, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}

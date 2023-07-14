//package Sub_Project_02;
//import edu.princeton.cs.algs4.*;
//import java.io.*;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//public class CombineProperties
//{
//    public static void main(String[] args) throws IOException
//    {
//        String directoryPath = "C:\\Users\\ayush\\Desktop\\DSA_Project_Clone\\Graph_Data_Structure_Project\\src\\Sub_Project_02\\Datasets"; //directory path - replace with your directory path
//        // Replace with the directory path
//        String outPath = "C:\\Users\\ayush\\Desktop\\DSA_Project_Clone\\Graph_Data_Structure_Project\\src\\Sub_Project_02\\Datasets"; //output directory - replace with your output directory path
//        File directory = new File(directoryPath);
//        File outputdir = new File(outPath);
//        File[] filesList = directory.listFiles();
//        Workbook workbook = new XSSFWorkbook();
//        String[] sheetname = {"Sheet1","Sheet2","Sheet3"};
//        if (filesList == null) {
//            System.out.println("Specified directory is empty or does not exist.");
//            return;
//        }
//        for (int k = 0 ;k < filesList.length; k++)
//        {
//            File file = filesList[k];
//            Sheet sheet = workbook.createSheet(sheetname[k]);
//            if (file.isFile())
//            {
//                In in1 = new In(file);
//                EdgeWeightedDigraph G = new EdgeWeightedDigraph(in1);
//                for (int i = 0; i < G.V(); i++)
//                {
//                    Row dataRow = sheet.createRow(i);
//                    int s = i;
//                    DijkstraSP sp = new DijkstraSP(G, s);
//                    float sum = 0.0f;
//                    int ct=0;
//                    for (int t = 0; t < G.V(); t++)
//                        if (sp.hasPathTo(t)) {
//                            sum += sp.distTo(t);
//                            ct++;
//                        }
//                    sum /= ct;
//                    dataRow.createCell(0).setCellValue(sum);
//                }
//                in1.close();
//            }
//        }
//        FileOutputStream outputStream = new FileOutputStream("Results.csv");
//        workbook.write(outputStream);
//    }
//}
//

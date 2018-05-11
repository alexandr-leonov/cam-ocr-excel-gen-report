package finance.core;

import java.util.List;

public interface GenerateReportService {

    void generate(String imageFileName, String reportName);

    default void generate(List<String> imageParts,String reportName){}
}

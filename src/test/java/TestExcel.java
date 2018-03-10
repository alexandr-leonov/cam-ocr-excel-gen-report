import finance.basics.ExcelBasic;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.IOException;

import static org.powermock.api.mockito.PowerMockito.when;


//@RunWith(PowerMockRunner.class)
@PrepareForTest(ExcelBasic.class)
public class TestExcel {
    private ExcelBasic excelServiceImpl;

    @Mock
    private ExcelBasic mockUp;

    @Before
    public void start() throws Exception {
        excelServiceImpl=new ExcelBasic();
        when(mockUp.test()).thenReturn("mock up!");
      }

    @Ignore
    public void first() {
        System.out.println(excelServiceImpl.test());
        System.out.println(mockUp.test());
    }

    @Ignore
    public void write() throws IOException {
        excelServiceImpl.writeIntoExcel("D://dev.xls","NUMBER");
    }

    @Ignore
    public void read() throws IOException {
        excelServiceImpl.readFromExcel("D://dev.xls","NUMBER");
    }

    @After
    public void end() {
        excelServiceImpl = null;
    }
}

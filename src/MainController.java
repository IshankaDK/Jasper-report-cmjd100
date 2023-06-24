import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class MainController {

    @FXML
    private TextField txtMyName;

    @FXML
    void btnOnAction(ActionEvent event) {
        try {

            // load jrxml as input stream
            InputStream is = this.getClass().getResourceAsStream("Customer_Report.jrxml");

            // compile the jrxml file
            JasperReport jReport = JasperCompileManager.compileReport(is);

            // create hash map to fill values
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("myName", txtMyName.getText().trim());

            // fill the compiled report.
            JasperPrint jp = JasperFillManager.fillReport(jReport, hashMap,
                    DBConnection.getInstance().getConnection());

            // view the jasper report
            JasperViewer.viewReport(jp, false);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

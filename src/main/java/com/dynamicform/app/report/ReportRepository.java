package com.dynamicform.app.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.dynamicform.app.base.BaseRepository;
import com.dynamicform.app.employee.EmployeeEntity;
import com.dynamicform.app.employee.EmployeeRepository;
import com.dynamicform.app.util.Response;

@Repository
public class ReportRepository extends BaseRepository{
	
	@Autowired
	private CoreJasperService coreJasperService;
	@Autowired
	EmployeeRepository employeeRepository;
	
	@SuppressWarnings("unchecked")
	public CusJasperReportDef salarySheet(String reqObj) throws IOException {

//		Object obj = new JSONParser().parse(new FileReader("billDtl.json"));

			JSONObject json = new JSONObject(reqObj);
			List<EmployeeEntity> employeeList = new ArrayList<EmployeeEntity>();

			Response res=employeeRepository.list();
			if (res.getItems() !=null && res.getItems().size()>0) {
				employeeList=res.getItems() ;
			}
			



			Map<String, Object> parameterMap = new HashMap<String, Object>();
			
//			splOtSummaryReportPrintDtoList.add(splOtSummaryReportPrintDto);

			CusJasperReportDef report = new CusJasperReportDef();

			report.setReportName("salarySheet");
			report.setReportDir(getResoucePath("/report/salarySheet") + "/");
			report.setReportFormat(JasperExportFormat.PDF_FORMAT);
			report.setParameters(parameterMap);
			report.setReportData(employeeList);

			ByteArrayOutputStream baos = null;
			try {
				baos = coreJasperService.generateReport(report);
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				baos.close();
			}

			report.setContent(baos.toByteArray());
			return report;
		}
	
	
	public static String getResoucePath(String filePath) {
		Resource resource = new ClassPathResource(filePath);
		try {
			return resource.getFile().getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

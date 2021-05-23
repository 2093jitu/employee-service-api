package com.dynamicform.app.report;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

	@Autowired
	ReportRepository reportRepository;
public CusJasperReportDef salarySheet(String reqObj) throws IOException  {
		
    	return reportRepository.salarySheet(reqObj);

	}
}

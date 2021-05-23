package com.dynamicform.app.report;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/report")
public class ReportController {
	@Autowired
	ReportService reportService;
	
	@PostMapping(value = "/salarySheet")
	public ResponseEntity<byte[]> salarySheet(@RequestBody String reqObj) throws IOException {
		CusJasperReportDef report = reportService.salarySheet(reqObj);
		
		return respondReportOutputWithoutHeader(report, false);
	}
	
	public   ResponseEntity<byte[]> respondReportOutputWithoutHeader(CusJasperReportDef jasperReport, boolean forceDownload) throws IOException {
		if (jasperReport == null || jasperReport.getContent() == null) {
			throw new FileNotFoundException("jasper Report Not found");
		} else {
			String outputFileName = (jasperReport.getOutputFilename()) + "." + jasperReport.getReportFormat().getExtension();
			String contentDisposition = forceDownload == true ? "attachment;filename=\""+outputFileName+"\"": "filename=\""+outputFileName+"\"";
		    return ResponseEntity
		  	      .ok()
		  	      .header("Content-Type",  jasperReport.getReportFormat().getMimeType()+";charset=UTF-8")
		  	      .header("Content-Disposition", contentDisposition)
		  	      .body(jasperReport.getContent());
			
		}
	}
}

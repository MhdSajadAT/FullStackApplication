package com.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Customers;
import com.entity.ServicesCategory;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.service.DashBoardService;
import com.service.OrderRequest;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class DashBoardController {

	@Autowired
	private DashBoardService dashBoardService;

	 
	@PostMapping("/createCustomer")
	public Customers createCustomer(@RequestBody Customers customer) {
		return dashBoardService.createCustomer(customer);
	}
	
	@PostMapping("/createOrder")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
	    dashBoardService.createOrder(orderRequest);
	    return ResponseEntity.status(HttpStatus.OK).build(); // Responding with HTTP 201 status and the response body
	}

	@PostMapping("/createServices")
	public ServicesCategory createServices(@RequestBody ServicesCategory services) {
		return dashBoardService.createServices(services);
	}
	
	@GetMapping("/exportAllUser")
	public ResponseEntity<byte[]> exportAllUser(HttpServletResponse response) throws DocumentException {
		
		
		List<Customers>cuList= dashBoardService.getALLCustomer();
        byte[] pdfData = generateUserPDF(cuList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.attachment().filename("users.pdf").build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfData);

	}
	private byte[] generateUserPDF(List<Customers> users) throws DocumentException {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);
		document.open();
		
		PdfPTable table = new PdfPTable(3);
		table.addCell("Name");
		table.addCell("Mobile");
		table.addCell("Email");
		for (Customers cu : users) {
			
			table.addCell(cu.getName());
			table.addCell(cu.getMobile());
			table.addCell(cu.getEmail());
		
		}	
		document.add(table);
		document.close();
		
		return out.toByteArray();
	}
}

package com.cozentusrt.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentusrt.entity.RequestsEntity;
import com.cozentusrt.repository.DepartmentRepository;
import com.cozentusrt.repository.RequestRepository;

@Service
public class RequestService {
	
	
	@Autowired
	RequestRepository reqRepo;
	
	@Autowired
	DepartmentRepository deptRepo;
	
	
	public int saveRequest(RequestsEntity req) {
		int status;
		
		System.out.println(req);
        System.out.print(req.getRequestTitle() + req.getRequestDept() + req.getRequestNumber());

        
        //        deptRepo.findById(req.getRequestDept()).get().getDeptCode();
        
        
        req.setRequestNumber(this.generateReqNumber(deptRepo.findById(req.getRequestDept().getDid()).get().getDcode(), RequestsEntity.getSeqNum()));
        
        try {
        	reqRepo.save(req);
        	
        	status = 1;
        } catch(Exception e) {
        	System.out.println(e.toString());
        	status = 0;
        }
		
		return status;
	}
	
	public String generateReqNumber(String requestDept, String seqNum) {
		String requestNumber = "";
		
		System.out.println("Requests Constructor");
		if(seqNum.compareTo("100000") == 0) {
			seqNum = "00001";
		}

		requestNumber = requestDept + seqNum ;
		System.out.println("REQ:"+requestNumber);


		int index;
		for(index = 0; index < seqNum.length(); index++) {
			if(seqNum.charAt(index) != '0') {
				System.out.println(index);
				break;
			}
		}

		String str = seqNum;
		String substr = str.substring(index,seqNum.length());
		String substr1 = str.substring(0,index);
		System.out.println("range :"+ substr1);
		System.out.println("number = " + substr);
		int val=Integer.parseInt(substr);
		val++;
		substr=Integer.toString(val);

		seqNum = substr1 + substr;
		
		System.out.println("SEQ:"+seqNum);
		RequestsEntity.setSeqNum(seqNum);
		return requestNumber;

	}
	
	public List<RequestsEntity> getAllRequests() {
		List<RequestsEntity> allRequests = this.reqRepo.findAll();
		return allRequests;
	}
}

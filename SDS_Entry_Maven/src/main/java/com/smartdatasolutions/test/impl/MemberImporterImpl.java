package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MemberImporterImpl implements MemberImporter {

	@Override
	public List< Member > importMembers( File inputFile ) throws Exception {
		List<Member> members = new ArrayList<>();
		/*
		 * Implement the missing logic
		 */

		try (BufferedReader br = new BufferedReader( new FileReader( inputFile ) )) {
			String line;
			while ((line = br.readLine()) != null) {
				String id = line.substring(0, 12).trim();
				String lastName = line.substring(12, 37).trim();
				String firstName = line.substring(37, 62).trim();
				String address = line.substring(62, 92).trim();
				String city = line.substring(92, 112).trim();
				String state = line.substring(112, 116).trim();
				String zip = line.substring(116).trim();
				Member addMember = new Member();
				addMember.setId(id);
				addMember.setFirstName(firstName);
				addMember.setLastName(lastName);
				addMember.setAddress(address);
				addMember.setCity(city);
				addMember.setZip(zip);
				members.add(addMember);
			}
		}
		return members;
	}
	
	

}

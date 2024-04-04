package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		// TODO
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		// TODO
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {

		List<Member> nonDuplicateMembers = new ArrayList<>();
		Map<String, Boolean> seenMembers = new HashMap<>();
		for (Member member : membersFromFile) {
			String memberId = member.getId();
			if (!seenMembers.containsKey(memberId) || !seenMembers.get(memberId)) {
				nonDuplicateMembers.add(member);
				seenMembers.put(memberId, true);
			}
		}
		return nonDuplicateMembers;
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		Map<String, List<Member>> membersByState = new HashMap<>();
		for (Member member : validMembers) {
			String state = member.getState();
			if (!membersByState.containsKey(state)) {
				membersByState.put(state, new ArrayList<>());
			}
			membersByState.get(state).add(member);
		}
		return membersByState;
	}

	public static void main( String[] args ) {
		Main main = new Main();
		File inputFile = new File("Members.txt");
		String outputFilePath = "output";
		String outputFileName = "NY_outputFile.csv";

		try {
			createDirectory(outputFilePath);
			main.convert(inputFile, outputFilePath, outputFileName);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	// Create output directory if it doesn't exist
	private static void createDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			if (!directory.mkdirs()) {
				System.err.println("Failed to create output directory: " + directoryPath);
			}
		}
	}

}

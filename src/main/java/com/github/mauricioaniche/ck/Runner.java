package com.github.mauricioaniche.ck;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class Runner {

	public static void main(String[] args) throws FileNotFoundException {
		if(args==null || args.length < 2) {
			System.out.println("Usage java -jar ck.jar <path to project> <path to csv>");
			System.exit(1);
		}
		
		String projectName = args[0];
		String path = args[1];
		String commitAsJSon = args[2];
		
		CKReport report = new CK().calculate(path);
		
		PrintStream ps = System.out;
		ps.print("[");
        Iterator<CKNumber> interactor = report.all().iterator();
		while(interactor.hasNext()){
            CKNumber result = interactor.next();
			if(result.isError()) continue;
			
			ps.print(
				"{"+
						"\"file\": \""+result.getFile() + "\"," +
						"\"className\": \""+result.getClassName() + "\"," +
						"\"type\": \""+result.getType() +  "\"," +
						"\"cbo\": \""+result.getCbo() +  "\"," +
						"\"wmc\": \""+result.getWmc() +  "\"," +
						"\"dit\": \""+result.getDit() +  "\"," +
						"\"noc\": \""+result.getNoc() +  "\"," +
						"\"rfc\": \""+result.getRfc() +  "\"," +
						"\"lcom\": \""+result.getLcom() +  "\"," +
						"\"ncom\": \""+result.getNom() +  "\"," +
						"\"nopm\": \""+result.getNopm() +  "\"," + 
						"\"npsm\": \""+result.getNosm() +  "\"," +
						"\"nof\": \""+result.getNof() +  "\"," +
						"\"nopf\": \""+result.getNopf() +  "\"," + 
						"\"nosf\": \""+result.getNosf() +  "\"," +
						"\"nosi\": \""+result.getNosi() +  "\"," +
						"\"loc\": \""+result.getLoc()+  "\"" +
				"}"
			);
            if(interactor.hasNext()) {
                ps.print(',');
            }
		}
		ps.print("]");
		
	}
}

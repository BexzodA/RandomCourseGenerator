import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class RandomCourseGenerator {
	
	
	private final int nStudents;
	private final Random rng = new Random();
	private final File FIRST = new File(".\\src\\first.txt");
	private final File LAST = new File(".\\src\\last.txt");
	private final File dest;
	private FileWriter writer;
	
	public RandomCourseGenerator(String path, String fileName, int nStudents) {
		this.nStudents = nStudents;
		dest = new File(path + "\\" + fileName);
		try {
			if(!dest.exists()) {
				dest.createNewFile();
			}
			writer = new FileWriter(dest);
			MakeCourse();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getFirstName() {
		try {
			String test = new String(Files.readAllBytes(Paths.get(FIRST.getAbsolutePath())));
			
			int start = rng.nextInt(test.length() - 1);
			
			if(start < 7) {
				start += 7 - start;
			}
			else if(start > (test.length() - 9)) {
				start -= start - (test.length() - 9);
			}
			
			for(;test.charAt(start) != 10; start++);
			start++;
			int end = start;
			for(;test.charAt(end) != 13; end++);
			
			return test.substring(start,end);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String getLastName() {
		try {
			String test = new String(Files.readAllBytes(Paths.get(LAST.getAbsolutePath())));
			
			int start = rng.nextInt(test.length() - 1);
			
			if(start < 7) {
				start += 7 - start;
			}
			else if(start > (test.length() - 8)) {
				start -= start - (test.length() - 8);
			}
			
			for(;test.charAt(start) != 10; start++);
			start++;
			int end = start;
			for(;test.charAt(end) != 13; end++);
			
			return test.substring(start,end);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private void MakeCourse() {
		for(int i = 0; i < nStudents; i++) {
			try {
				writer.write(getFirstName() + " " + getLastName() + " " + (rng.nextInt(51) + 50) + " " + (rng.nextInt(51) + 50) + " " + (rng.nextInt(51) + 50) + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
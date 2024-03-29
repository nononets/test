package test;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.regex.*;

public class Test {

	public static void main(String[] args) {
			URL url;
			BufferedReader br = null;

			String path;
			BufferedWriter bw = null;

			String line = "";
		try {
			url = new URL("https://www.naver.com/");
			br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

			path = new String("D:\\spring\\source\\test\\src\\test\\ex.html");
			bw = new BufferedWriter(new FileWriter(path));

			while((line = br.readLine()) != null) {
				Pattern p = Pattern.compile("[/]");
				Matcher m = p.matcher(line);

				if (m.find()) {
					bw.write(line + "\r\n");
				}
			}

			bw.flush();

			System.out.println("load...");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownload {
	final static int size = 1024;

	/*
	 * fileUrl connects to specific url provided and downloads the file to
	 * destination directory provided
	 */
	public static void fileUrl(String fAddress, String localFileName,
			String destinationDir) {
		OutputStream out = null;

		InputStream is = null;
		try {
			URL Url;
			byte[] buf;
			int ByteRead, ByteWritten = 0;
			Url = new URL(fAddress);
			out = new BufferedOutputStream(new FileOutputStream(destinationDir
					+ "\\" + localFileName));

			HttpURLConnection uCon = (HttpURLConnection) Url.openConnection();
			uCon.addRequestProperty("User-Agent", "Mozilla/4.76");
			is = uCon.getInputStream();
			buf = new byte[size];
			while ((ByteRead = is.read(buf)) != -1) {
				out.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}
			System.out.println("Downloaded Successfully.");
			System.out.println("File name:\"" + localFileName
					+ "\"\nNo ofbytes :" + ByteWritten);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * fileDownload takes the image as input and destination for storing images
	 * calls the fileUrl() with the secific parameters for processing
	 */
	public static void fileDownload(String[] fAddress, String destinationDir) {
		for (int i = 0; i < fAddress.length; i++) {
			int slashIndex = fAddress[i].lastIndexOf('/');
			int dotIndex = fAddress[i].lastIndexOf('.');

			String fileName = fAddress[i].substring(slashIndex + 1);

			if (dotIndex >= 1 && slashIndex >= 0
					&& slashIndex < fAddress[i].length() - 1) {
				fileUrl(fAddress[i], fileName, destinationDir);
			} else {
				System.err.println("path or file name.");
			}
		}
	}

}

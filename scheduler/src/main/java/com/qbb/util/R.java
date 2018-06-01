package com.qbb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class R {

	public static void byte2File(byte[] data, String filePath) throws IOException {
		File file = new File(filePath);
		byte2File(data, file);
	}

	public static void byte2File(byte[] data, File file) throws IOException {
		FileOutputStream out = null;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new FileOutputStream(file);
			out.write(data, 0, data.length);
		} finally {
			closeStream(out);
		}
	}

	public static void closeStream(OutputStream out) {
		try {
			if (out != null) {
				out.close();
			}
		} catch (Exception e) {
		}
	}
}

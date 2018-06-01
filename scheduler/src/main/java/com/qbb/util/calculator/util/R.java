package com.qbb.util.calculator.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

public final class R {

	public static InputStream stream(String uri) {
		return R.class.getResourceAsStream(uri);
	}

	public static String path(String uri) {
		return R.class.getResource(uri) != null ? R.class.getResource(uri).toExternalForm() : "";
	}

	public static URL url(String uri) {
		return R.class.getResource(uri);
	}

	public static URL httpUrl(String url) throws MalformedURLException {
		return new URL(url);
	}

	public static byte[] file2Byte(String filePath) {
		return file2Byte(new File(filePath));
	}

	public static byte[] file2Byte(File file) {
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		byte[] buffer = null;
		try {
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(bos);
			closeStream(fis);
		}
		return buffer;
	}

	public static boolean byte2File(byte[] data, String filePath) {
		FileOutputStream out = null;
		try {
			filePath = filePath.replaceAll("\\\\", "/");
			File file = new File(filePath.substring(0, filePath.lastIndexOf("/") + 1));
			if (!file.exists()) {
				file.mkdirs();
			}
			out = new FileOutputStream(filePath);
			out.write(data, 0, data.length);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(out);
		}
		return false;
	}

	public static boolean byte2File(byte[] data, File file) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(data, 0, data.length);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(out);
		}
		return false;
	}

	public static InputStream file2Stream(String filePath) {
		InputStream fis = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fis;
	}

	public static String filePath(String file) {
		File f = new File(file);
		if (f.exists() && f.isFile()) {
			try {
				return f.toURI().toURL().toExternalForm();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static byte[] stream2Byte(InputStream fis) {
		byte[] buffer = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(bos);
			closeStream(fis);
		}
		return buffer;
	}

	public static void copy(String source, String dest) {
		File s = new File(source);
		FileInputStream in = null;
		FileOutputStream output = null;
		try {
			in = new FileInputStream(s);
			output = new FileOutputStream(dest);
			byte[] b = new byte[1024];
			int n;
			while ((n = in.read(b)) != -1) {
				output.write(b, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(in);
			closeStream(output);
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param source
	 */
	public static void delete(File source) {
		if (source.exists()) {
			source.delete();
		}
	}

	public static void move(String source, String dest) {
		File s = new File(source);
		if (s.exists()) {
			copy(source, dest);
			s.delete();
		}
	}

	public final static String MD5(byte[] s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(s);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return "";
		}
	}

	public static void closeStream(InputStream in) {
		try {
			if (in != null) {
				in.close();
			}
		} catch (Exception e) {
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

	public static void closeStream(Writer out) {
		try {
			if (out != null) {
				out.close();
			}
		} catch (Exception e) {
		}
	}
}

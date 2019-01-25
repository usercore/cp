package com.bc.protocol.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

/**
 * <p>
 * Title: 封装JDom
 * </p>
 * <p>
 * Description: 封装JDom，提供常用方法。
 * </p>
 * @author hanmin
 * @version 1.0
 */

public class JDomUtil implements Serializable {
	private static final long serialVersionUID = -5062472810095190753L;

	protected Document xmlDoc = null;
	
	public static final String RECORDNAME = "Record";

	public JDomUtil() {
	}

	public JDomUtil(Document xmlDoc) {
		this.xmlDoc = xmlDoc;
	}

	public Document getXmlDoc() {
		return xmlDoc;
	}

	public void setXmlDoc(Document xmlDoc) {
		this.xmlDoc = xmlDoc;
	}

	/**
	 * 根据XML 字符串 建立JDom的Document对象
	 * 
	 * @param xmlString
	 *            XML格式的字符串
	 * @return Document 返回建立的JDom的Document对象，建立不成功返回null 。
	 */
	public static Document getDocument(String xmlString) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder
					.build(new StringReader(xmlString));
			return anotherDocument;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据XML 字符串 建立JDom的Document对象
	 * 
	 * @param xmlString
	 *            XML格式的字符串
	 * @return Document 返回建立的JDom的Document对象，建立不成功返回null 。
	 */
	public static Document getDocument(File file) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(file);
			return anotherDocument;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据XML流 建立JDom的Document对象
	 * 
	 * @param ins
	 *            InputStream XML字符流
	 * @return Document 返回建立的JDom的Document对象，建立不成功返回null 。
	 */
	public static Document getDocument(InputStream ins) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(ins);
			return anotherDocument;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据URL链接 建立JDom的Document对象
	 * 
	 * @param urlStr
	 *            String URL链接
	 * @return Document 返回建立的JDom的Document对象，建立不成功返回null 。
	 */
	public static Document getDocumentByURL(String urlStr) {
		try {
			URL url = new URL(urlStr);
			InputStream ins = url.openStream();
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(ins);
			return anotherDocument;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据XML 文件名 建立JDom的Document对象
	 * 
	 * @param fileName
	 *            XML格式的字符串
	 * @return Document 返回建立的JDom的Document对象，建立不成功返回null 。
	 */
	public static Document getDocumentByFile(String fileName) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(new File(fileName));
			return anotherDocument;
		} catch (Exception e) {
			System.out.println("parse file error:" + fileName);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Map对象转换成Element。
	 * 
	 * @param paramMap
	 * @return
	 */
	public static Element toRecord(Map paramMap) {
		Element ret = new Element(RECORDNAME);
		Iterator keys = paramMap.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = (String) paramMap.get(key);
			ret.addContent(new Element(key).setText(value));
		}
		return ret;
	}

	/**
	 * 这个方法将JDom Element对象转换字符串。
	 * 
	 * @param xmlDoc
	 *            将要被转换的JDom对象
	 * @return String
	 */
	public static String toXML(Element xmlDoc) {
		return toXML(xmlDoc, "gb2312", false);
	}

	/**
	 * 这个方法将JDom Element对象转换字符串。
	 * 
	 * @param xmlDoc
	 *            将要被转换的JDom对象
	 * @param encoding
	 *            输出字符串使用的编码
	 * @param newline
	 *            是否换行
	 * @return String
	 */
	public static String toXML(Element xmlDoc, String encoding, boolean newline) {
		ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
		Format format = Format.getPrettyFormat();
		format.setEncoding(encoding);
		XMLOutputter docWriter = new XMLOutputter(format);
		try {
			docWriter.output(xmlDoc, byteRep);
		} catch (Exception e) {
		}
		return byteRep.toString();
	}

	/**
	 * 这个方法将JDom Document对象转换字符串。
	 * 
	 * @param xmlDoc
	 *            将要被转换的JDom对象
	 * @return String
	 */
	public static String toXML(Document xmlDoc) {
		return toXML(xmlDoc, "gb2312", false);
	}

	/**
	 * 这个方法将JDom Document对象转换字符串。
	 * 
	 * @param xmlDoc
	 *            将要被转换的JDom对象
	 * @param encoding
	 *            输出字符串使用的编码
	 * @param newline
	 *            是否换行
	 * @return String
	 */
	public static String toXML(Document xmlDoc, String encoding, boolean newline) {
		ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
		Format format = Format.getPrettyFormat();
		format.setEncoding(encoding);
		XMLOutputter docWriter = new XMLOutputter(format);
		try {
			docWriter.output(xmlDoc, byteRep);
		} catch (Exception e) {
		}
		return byteRep.toString();
	}

	/**
	 * 取出List中的数据为数组
	 * 
	 * @param list
	 *            List
	 * @return String[]
	 */
	public static String[] getListText(java.util.List list) {
		String result[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = ((Element) list.get(i)).getText().trim();
		}
		return result;
	}

	/**
	 * 查找或创建节点<br>
	 * 如父级节点不存在，也包括创建父级节点
	 * 
	 * @param doc
	 *            xml包
	 * @param path
	 *            XPATH
	 * @return 如果指定xpath的节点存在，则返回该节点。否则创建该节点。
	 * @throws JDOMException
	 */
	public static Element createPath(Document doc, String path)
			throws JDOMException {
		Element ret = null;
		if (doc == null)
			return null;
		if (path == null || path.trim().equals("") || !path.startsWith("/"))
			return null;
		if ((ret = (Element) XPath.selectSingleNode(doc, path)) != null)
			return ret;
		String[] paths = path.split("/");
		StringBuffer xpath = new StringBuffer();
		for (int i = 1; i < paths.length - 1; i++) {
			if (paths[i].trim().equals(""))
				continue;
			xpath.append("/").append(paths[i].trim());
		}
		StringTokenizer token = new StringTokenizer(paths[paths.length - 1],
				"[");
		String name = token.nextToken();
		ret = new Element(name);
		// Object parent = null;
		if (xpath.length() > 0)
			createPath(doc, xpath.toString()).addContent(ret);
		else
			doc.addContent(ret);
		return ret;
	}

	/**
	 * 取节点或属性的字符串
	 * 
	 * @param node
	 * @return
	 */
	public static String getNodeString(Object node) {
		String outstr = null;
		if (node != null) {
			if (node instanceof Element) {
				outstr = ((Element) node).getText();
			} else if (node instanceof Attribute) {
				outstr = ((Attribute) node).getValue();
			} else
				outstr = node.toString();
		}
		return outstr;
	}

	/**
	 * 设置节点属性文本<br>
	 * 如果节点不存在则创建
	 * 
	 * @param doc
	 * @param path
	 * @param attName
	 * @param text
	 * @return
	 * @throws JDOMException
	 */
	public static Element setAttr(Document doc, String path, String attName,
			String text) throws JDOMException {
		Element de = createPath(doc, path);
		if (de == null)
			return null;
		if (text != null)
			de.setAttribute(attName, text);
		return de;
	}

	/**
	 * 设置节点内容为文本值<br>
	 * 如果节点不存在则创建
	 * 
	 * @param doc
	 * @param path
	 * @param text
	 * @return
	 * @throws JDOMException
	 */
	public static Element setText(Document doc, String path, String text)
			throws JDOMException {
		Element de = createPath(doc, path);
		if (de == null)
			return null;
		try {
			de.setText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return de;
	}

	/**
	 * 设置节点文本值
	 * 
	 * @param path
	 * @param text
	 * @return
	 * @throws JDOMException
	 */
	public Element setText(String path, String text) throws JDOMException {
		return setText(this.xmlDoc, path, text);
	}

	/**
	 * 设置xpath节点的属性值
	 * 
	 * @param path
	 * @param attName
	 * @param text
	 * @return
	 * @throws JDOMException
	 */
	public Element setAttr(String path, String attName, String text)
			throws JDOMException {
		return setAttr(this.xmlDoc, path, attName, text);
	}

	/**
	 * 取符合xpath条件的所有节点
	 * 
	 * @param path
	 * @return
	 * @throws JDOMException
	 */
	public List getNodes(String path) throws JDOMException {
		return XPath.selectNodes(this.xmlDoc, path);
	}

	/**
	 * 删除符合xpath条件的所有节点
	 * 
	 * @param path
	 * @throws JDOMException
	 */
	public void removeNodes(String path) throws JDOMException {
		List nodes = getNodes(path);
		for (int i = 0; nodes != null && nodes.size() > i; i++) {
			Object node = nodes.get(i);
			this.xmlDoc.removeContent((Content) node);
		}
	}

	/**
	 * 返回节点或属性
	 * 
	 * @param path
	 * @return Object
	 * @throws JDOMException
	 */
	public Object getSingleNode(String path) throws JDOMException {
		return XPath.selectSingleNode(this.xmlDoc, path);
	}

	/**
	 * 返回单个节点或节点属性的文本值，带缺省值
	 * 
	 * @param path
	 * @param def
	 * @return
	 * @throws JDOMException
	 */
	public String getSingleNodeString(String path, String def)
			throws JDOMException {
		// Object node = null;
		String output = null;

		output = getNodeString(getSingleNode(path));
		if (output == null)
			output = def;
		return output;
	}

	/**
	 * 返回单个节点或节点属性的文本值,缺省值=空字符串
	 * 
	 * @param path
	 * @return
	 * @throws JDOMException
	 */
	public String getSingleNodeString(String path) throws JDOMException {
		return getSingleNodeString(path, "");
	}

	/**
	 * 将节点及节点下级转换为MAP对象<br>
	 * 如有重名的节点则用ArrayList保存值
	 * 
	 * @param ele
	 * @return
	 */
	public static Map toMap(Element ele) {
		Map ret = new HashMap();
		if (ele == null)
			return ret;
		List childs = ele.getChildren();
		for (Iterator iter = childs.iterator(); iter.hasNext();) {
			Element child = (Element) iter.next();
			String name = child.getName();
			Object value = child.getText();
			if(child.getChildren().size()>0){
				value = toMap(child);
			}
			if (ret.get(name) != null) {
				Object existsValue = ret.get(name);
				ArrayList valarrary = new ArrayList();
				if (existsValue instanceof ArrayList) {
					valarrary = (ArrayList) existsValue;
				} else
					valarrary.add(existsValue);
				valarrary.add(value);
				ret.put(name, valarrary);
			} else
				ret.put(name, value);
		}
		return ret;
	}
	
	/**
	 * 将整个XML转换为Map对象
	 * @return
	 */
	public Map getMap(){
		if(xmlDoc==null) return new HashMap();
		return JDomUtil.toMap(xmlDoc.getRootElement());
	}

	/**
	 * 将JDom Document对象转换为字符串。
	 */
	public String toString() {
		return toString("GBK", true);
	}

	/**
	 * 将JDom Document对象转换为字符串。
	 * 
	 * @param encoding
	 * @param newline
	 * @return
	 */
	private String toString(String encoding, boolean newline) {
		if (this.xmlDoc == null)
			return null;
		return JDomUtil.toXML(xmlDoc, encoding, newline);
	}
}

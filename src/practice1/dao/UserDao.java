package practice1.dao;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import practice1.domain.User;

public class UserDao {
	private String path = "D:\\users.xml";
	
	public void addUser(User user) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(path);
			Element root = doc.getRootElement();
			Element userEle = root.addElement("user");
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());
			//�����ĵ�
			//���������ʽ
			OutputFormat format = new OutputFormat("\t",true);//������ʹ�û���
			format.setTrimText(true);//���ԭ�л�������
			//
			XMLWriter writer = new XMLWriter(
									new OutputStreamWriter(
											(new FileOutputStream(path)),"UTF-8"),format);
			writer.write(doc);//����document�Զ���
			writer.close();
		}catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public User findByName(String username) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(path);
			//xpath��ѯ�õ�Element
			Element ele = (Element)doc.selectSingleNode("//user[@username='" + username + "']");
			if(ele == null) return null;
			//��ele���ݷ�װ��User����
			User user = new User();
			user.setUsername(ele.attributeValue("username"));
			user.setPassword(ele.attributeValue("password"));
			return user;
		} catch (DocumentException e) {
			throw new RuntimeException();
		}
	}
		
}

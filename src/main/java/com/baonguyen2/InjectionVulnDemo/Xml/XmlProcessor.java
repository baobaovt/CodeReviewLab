package com.baonguyen2.InjectionVulnDemo.Xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.baonguyen2.InjectionVulnDemo.Model.User;
import com.baonguyen2.InjectionVulnDemo.Repo.UserRepo;



@Component
public class XmlProcessor {

    @Autowired
    UserRepo userRepo;

    private static final Logger logger = LogManager.getLogger(XmlProcessor.class);

    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser saxParser;
    private DefaultHandler handler;
    private List<String> messages = new ArrayList<>();




    public XmlProcessor() {
        try {

            saxParser = factory.newSAXParser();
            handler = new DefaultHandler() {

                boolean user = false;
                boolean bfname = false;
                boolean blname = false;
                boolean buname = false;
                boolean bpword = false;
                boolean bcomment = false;

                String firstName = "";
                String lastName = "";
                String userName = "";
                String password = "";
                String comment = "";



                Map<String, String> values = new HashMap<>();

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("USER")) user = true;
                    if (qName.equalsIgnoreCase("FIRSTNAME")) bfname = true;
                    if (qName.equalsIgnoreCase("LASTNAME")) blname = true;
                    if (qName.equalsIgnoreCase("USERNAME")) buname = true;
                    if (qName.equalsIgnoreCase("PASSWORD")) bpword = true;
                    if (qName.equalsIgnoreCase("COMMENT")) bcomment = true;
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (bfname && user) {
                        firstName += new String(ch, start, length);
                    }

                    if (blname && user) {
                        lastName += new String(ch, start, length);
                    }

                    if (buname && user) {
                        userName += new String(ch, start, length);
                    }

                    if (bpword && user) {
                        password += new String(ch, start, length);
                    }

                    if (bcomment && user) {
                        comment += new String(ch, start, length);
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("FIRSTNAME")) bfname = false;
                    if (qName.equalsIgnoreCase("LASTNAME")) blname = false;
                    if (qName.equalsIgnoreCase("USERNAME")) buname = false;
                    if (qName.equalsIgnoreCase("PASSWORD")) bpword = false;
                    if (qName.equalsIgnoreCase("COMMENT")) bcomment = false;



                    if (qName.equalsIgnoreCase("USER")) {
                        User newUser = new User(firstName, lastName, userName, password, comment);
                        userRepo.save(newUser);
                        messages.add("created user " + newUser);
                        reset();
                    }

                }

                private void reset() {
                    user = false;
                    bfname = false;
                    blname = false;
                    buname = false;
                    bpword = false;
                    bcomment = false;

                    firstName = "";
                    lastName = "";
                    userName = "";
                    password = "";
                    comment = "";
                }
            };
        } catch (ParserConfigurationException | SAXException e) {
            logger.error(e);
        }
    }

    public List<String> parseXML(File f) {
        messages.clear();
        try {
            saxParser.parse(f,handler);
			/* Secure Code
			 * factory.setFeature("http://xml.org/sax/features/external-general-entities",
			 * false); saxParser.getXMLReader().setFeature(
			 * "http://xml.org/sax/features/external-general-entities", false);
			 * factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",
			 * true);
			 */
        } catch (SAXException | IOException e) {
            messages.add(e.getMessage());
        }
        return messages;
    }




}

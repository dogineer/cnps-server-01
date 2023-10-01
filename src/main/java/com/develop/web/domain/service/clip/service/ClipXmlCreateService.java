package com.develop.web.domain.service.clip.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@Service
public class ClipXmlCreateService {

    public String create(String videoName, String filePath) throws ParserConfigurationException, TransformerException {
        // XML document 생성
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = dbf.newDocumentBuilder().newDocument();

        // xmeml root element 생성
        Element xmeml = document.createElement("xmeml");
        xmeml.setAttribute("version", "4");
        document.appendChild(xmeml);

        // clip element 생성
        Element clip = document.createElement("clip");
        clip.setAttribute("id", "masterclip-0");
        clip.setAttribute("explodedTracks", "true");
        xmeml.appendChild(clip);

        /* ====== rate ====== */
            // rate element 생성
            Element rate = document.createElement("rate");
            clip.appendChild(rate);

            // timebase 생성
            Element timebaseElement = document.createElement("timebase");
            Text timebaseText = document.createTextNode("30");
            timebaseElement.appendChild(timebaseText);
            rate.appendChild(timebaseElement);

            // ntsc 생성
            Element ntscElement = document.createElement("ntsc");
            Text ntscText = document.createTextNode("TRUE");
            ntscElement.appendChild(ntscText);
            rate.appendChild(ntscElement);
        /* ====== rate ====== */

        // name element 생성
        Element name = document.createElement("name");
        Text nameText = document.createTextNode(videoName);
        name.appendChild(nameText);
        clip.appendChild(name);

        // media element 생성
        Element media = document.createElement("media");
        clip.appendChild(media);

        // video element 생성
        Element video = document.createElement("video");
        media.appendChild(video);

        // track element 생성
        Element trackElement = document.createElement("track");
        video.appendChild(trackElement);

        // clipitem element 생성
        Element clipitem = document.createElement("clipitem");
        clipitem.setAttribute("id", "clipitem-0");
        trackElement.appendChild(clipitem);

        // file element 생성
        Element file = document.createElement("file");
        file.setAttribute("id", "file-0");
        clipitem.appendChild(file);

        // pathurl element 생성
        Element pathUrl = document.createElement("pathurl");
        Text pathUrlText = document.createTextNode(filePath);
        pathUrl.appendChild(pathUrlText);
        file.appendChild(pathUrl);

        // media data element 생성
        Element mediadata = document.createElement("media");
        file.appendChild(mediadata);

        // video element 생성
        Element videoElement2 = document.createElement("video");
        mediadata.appendChild(videoElement2);

        // video sample characteristics element 생성
        Element videoSampleCharElement = document.createElement("samplecharacteristics");
        videoElement2.appendChild(videoSampleCharElement);

        Element widthElement = document.createElement("width");
        Text widthText = document.createTextNode("1920");
        widthElement.appendChild(widthText);
        videoSampleCharElement.appendChild(widthElement);

        Element heightElement = document.createElement("height");
        Text heightText = document.createTextNode("1080");
        heightElement.appendChild(heightText);
        videoSampleCharElement.appendChild(heightElement);

        // audio element 생성
        Element audio = document.createElement("audio");
        mediadata.appendChild(audio);

        // audio sample characteristics element 생성
        Element audioSampleCharElement = document.createElement("samplecharacteristics");
        audio.appendChild(audioSampleCharElement);

        Element depthElement = document.createElement("depth");
        Text depthText = document.createTextNode("2");


        Element samplerateElement = document.createElement("samplerate");
        Text samplerateText = document.createTextNode("48000");
        samplerateElement.appendChild(samplerateText);
        audioSampleCharElement.appendChild(samplerateElement);

        Element channelcountElement = document.createElement("channelcount");
        Text channelcountText = document.createTextNode("4");
        channelcountElement.appendChild(channelcountText);
        audio.appendChild(channelcountElement);

        // XML 문자열 생성
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));

        return writer.getBuffer().toString();
    }
}